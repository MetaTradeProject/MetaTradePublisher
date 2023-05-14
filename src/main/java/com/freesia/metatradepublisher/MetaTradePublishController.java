package com.freesia.metatradepublisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freesia.metatradepublisher.model.ItemInfo;
import com.freesia.metatradepublisher.model.StoreInfo;
import com.freesia.metatradepublisher.rpc.FakeTradeClient;

@RestController
@RequestMapping(value = "/meta-trade")
public class MetaTradePublishController {
    private final FakeTradeClient client;

    @Autowired
    public MetaTradePublishController(){
        client = new FakeTradeClient(null, 8456);
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET, produces = "application/json")
    public List<StoreInfo> getStoreInfoList(){
        return null;
    }

    @RequestMapping(value = "/store/{address}/info", method = RequestMethod.GET, produces = "application/json")
    public List<StoreInfo> getStoreInfo(@PathVariable("address") String address){
        return null;
    }

    @RequestMapping(value = "/store/{address}/items", method = RequestMethod.GET, produces = "application/json")
    public List<ItemInfo> getItemInfoList(@PathVariable("address") String address){
        return null;
    }

    @RequestMapping(value = "/store/{address}/item/{item_id}", method = RequestMethod.GET, produces = "application/json")
    public ItemInfo getItemInfo(@RequestParam("address") String address, @RequestParam("item_id") String item_id){
        return null;
    }
}
