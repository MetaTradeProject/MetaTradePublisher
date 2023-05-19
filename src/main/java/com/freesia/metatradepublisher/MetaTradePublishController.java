package com.freesia.metatradepublisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alibaba.fastjson.JSONObject;
import com.freesia.metatradepublisher.jni.JniSigner;
import com.freesia.metatradepublisher.model.ItemInfo;
import com.freesia.metatradepublisher.model.SimpleTradeRequest;
import com.freesia.metatradepublisher.model.SimpleTradeResponse;
import com.freesia.metatradepublisher.model.StoreInfo;
import com.freesia.metatradepublisher.model.Trade;
import com.freesia.metatradepublisher.rpc.FakeTradeClient;

@RestController
@RequestMapping(value = "/meta-trade")
public class MetaTradePublishController {
    private final FakeTradeClient client;
    private final MetaTradePublishConfig config;

    @Autowired
    public MetaTradePublishController(){
        config = new MetaTradePublishConfig();
        client = new FakeTradeClient("127.0.0.1", 8456);
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET, produces = "application/json")
    public List<StoreInfo> getStoreInfoList(){
        return config.getStoreInfoList();
    }

    @RequestMapping(value = "/store/{address}/info", method = RequestMethod.GET, produces = "application/json")
    public StoreInfo getStoreInfo(@PathVariable("address") String address){
        return config.getStoreInfoByAddress(address);
    }

    @RequestMapping(value = "/store/{address}/items", method = RequestMethod.GET, produces = "application/json")
    public List<ItemInfo> getItemInfoList(@PathVariable("address") String address){
        return config.getItemInfoList(address);
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}", method = RequestMethod.GET, produces = "application/json")
    public ItemInfo getItemInfo(@PathVariable("address") String address, @PathVariable("item_id") String item_id){
        return config.getItemById(address, item_id);
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}/trade", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public SimpleTradeResponse sendSimpleTrade(@PathVariable("address") String address, @PathVariable("item_id") String item_id, @RequestBody SimpleTradeRequest request){
        var info = config.getStoreInfoByAddress(address);
        if(info != null){
            if(item_id.equals("0")){
                Trade trade = new Trade(address, request.receiverAddress(), request.amount(), 0, System.currentTimeMillis(), "", info.pubkey(), "");
                JniSigner signer = new JniSigner();
                String signature = signer.SignTrade(trade.getHash(), info.prikey());
                trade.setSignature(signature);
                client.RequestFakeTradeSync(trade);
            }
            else{
                JSONObject object = new JSONObject();
                object.put("id", item_id);
                object.put("amount", request.amount());
                
                Trade trade = new Trade(address, request.receiverAddress(), 0, 0, System.currentTimeMillis(), "", info.pubkey(), object.toJSONString());
                JniSigner signer = new JniSigner();
                String signature = signer.SignTrade(trade.getHash(), info.prikey());
                trade.setSignature(signature);
                client.RequestFakeTradeSync(trade);
            }
            return new SimpleTradeResponse("ok");
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
        }
    }
}
