package com.freesia.metatradepublisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alibaba.fastjson.JSONObject;
import com.freesia.metatradepublisher.jni.JniSigner;
import com.freesia.metatradepublisher.model.CronTrade;
import com.freesia.metatradepublisher.model.CronTradeRequest;
import com.freesia.metatradepublisher.model.CronTradeResponse;
import com.freesia.metatradepublisher.model.ItemInfo;
import com.freesia.metatradepublisher.model.SimpleTradeRequest;
import com.freesia.metatradepublisher.model.SimpleTradeResponse;
import com.freesia.metatradepublisher.model.StoreInfo;
import com.freesia.metatradepublisher.model.Trade;
import com.freesia.metatradepublisher.rpc.FakeTradeClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/meta-trade")
public class MetaTradePublishController {
    private final FakeTradeClient client;
    private final MetaTradePublishStore config;
    private final MetatradeCronTradePool tradePool;

    @Autowired
    public MetaTradePublishController(){
        log.info("Read stores ...");
        config = new MetaTradePublishStore();
        log.info("Read stores completed");

        log.info("Start rpc client ...");
        client = new FakeTradeClient("127.0.0.1", 8456);
        log.info("Rpc client init completed");

        log.info("Read local cronjobs ...");
        tradePool = new MetatradeCronTradePool(client, config);
        log.info("Read local cronjobs completed");

        log.info("Start cron service...");
        tradePool.Init();
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET, produces = "application/json")
    public List<StoreInfo> getStoreInfoList(){
        return config.getStoreInfoList();
    }

    @RequestMapping(value = "/store/{address}/info", method = RequestMethod.GET, produces = "application/json")
    public StoreInfo getStoreInfo(@PathVariable("address") String address){
        var res = config.getStoreInfoByAddress(address);
        if(res != null){
            return res;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
        }
    }

    @RequestMapping(value = "/store/{address}/items", method = RequestMethod.GET, produces = "application/json")
    public List<ItemInfo> getItemInfoList(@PathVariable("address") String address){
        var res = config.getItemInfoList(address);
        if(res != null){
            return res;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
        }
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}", method = RequestMethod.GET, produces = "application/json")
    public ItemInfo getItemInfo(@PathVariable("address") String address, @PathVariable("item_id") String item_id){
        var res = config.getItemById(address, item_id);
        if(res != null){
            return res;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store, Item not found");
        }
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}/simple-trade", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public SimpleTradeResponse postSimpleTrade(@PathVariable("address") String address, @PathVariable("item_id") String item_id, @RequestBody SimpleTradeRequest request){
        var info = config.getStoreInfoByAddress(address);
        var info2 = config.getItemById(address, item_id);
        if(info != null && info2 != null){
            Trade trade = null;
            if(item_id.equals("0")){
                trade = new Trade(address, request.receiverAddress(), request.amount(), 
                    0, System.currentTimeMillis(), "", info.pubkey(), "");
            }
            else{
                JSONObject object = new JSONObject();
                object.put("id", item_id);
                object.put("amount", request.amount());
                
                trade = new Trade(address, request.receiverAddress(), 0, 
                    0, System.currentTimeMillis(), "", info.pubkey(), object.toJSONString());  
            }
            JniSigner signer = new JniSigner();
            String signature = signer.SignTrade(trade.getHash(), info.prikey());
            trade.setSignature(signature);
            client.RequestFakeTradeSync(trade);

            return new SimpleTradeResponse("ok");
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store or item not found");
        }
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}/cron-trade", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public CronTradeResponse postCronTrade(@PathVariable("address") String address, @PathVariable("item_id") String item_id, @RequestBody CronTradeRequest request){
        var info = config.getItemById(address, item_id);
        if(info != null){
            if(!CronExpression.isValidExpression(request.cron())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unvalid cron expression");
            }
            
            CronTrade cronTrade = new CronTrade(request.cron(), info.store_address(), request.receiverAddress(), info.id(), request.amount());
            return new CronTradeResponse(tradePool.RegisterCronTrade(cronTrade));
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store or item not found");
        }
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}/cron-trade/{key}", method = RequestMethod.DELETE)
    public void deleteCronTrade(@PathVariable("address") String address, @PathVariable("item_id") String item_id, @PathVariable("key") String key){
        var info = config.getItemById(address, item_id);
        if(info != null){
            if(!tradePool.UnregisterCronTrade(key)){
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Key not found");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Store, Item not found");
        }
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}/cron-trade/{key}", method = RequestMethod.PUT)
    public void putCronTrade(@PathVariable("address") String address, @PathVariable("item_id") String item_id, @PathVariable("key") String key, @RequestBody CronTradeRequest request){
        var info = config.getItemById(address, item_id);
        if(info != null){
            if(!CronExpression.isValidExpression(request.cron())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unvalid cron expression");
            }

            CronTrade cronTrade = new CronTrade(request.cron(), info.store_address(), request.receiverAddress(), info.id(), request.amount());

            if(!tradePool.UpdateCronTrade(key, cronTrade)){
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Key not found");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Store, Item not found");
        }
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}/cron-trade/{key}", method = RequestMethod.GET, produces = "application/json")
    public CronTrade getCronTrade(@PathVariable("address") String address, @PathVariable("item_id") String item_id, @PathVariable("key") String key){
        var info = config.getItemById(address, item_id);
        if(info != null){
            var res = tradePool.getCronTrade(key);
            if(res != null){
                return res;
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Key not found");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store, Item not found");
        }
    }
}
