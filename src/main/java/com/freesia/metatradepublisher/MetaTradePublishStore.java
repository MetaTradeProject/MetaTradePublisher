package com.freesia.metatradepublisher;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.alibaba.fastjson.JSONReader;
import com.freesia.metatradepublisher.model.ItemInfo;
import com.freesia.metatradepublisher.model.StoreInfo;

public class MetaTradePublishStore {
    Map<String, StoreInfo> store_address_info_map;
    Map<String, Map<String, ItemInfo>> store_address_item_id_info_map;

    public MetaTradePublishStore(){
        store_address_info_map =  new ConcurrentHashMap<>();
        store_address_item_id_info_map = new ConcurrentHashMap<>();

        InitFromConfig();
    }

    private void InitFromConfig() {
        String itemsPath;
        String storesPath;
        if (MetaTradePublisherApplication.class.getResource("").getProtocol().equals("jar")) {
            itemsPath = System.getProperty("user.dir") + "/config/item_list.json";
            storesPath = System.getProperty("user.dir") + "/config/store_list.json";
        }
        else{
            itemsPath = MetaTradePublishStore.class.getClassLoader().getResource("//").getPath() + "item_list.json";
            storesPath = MetaTradePublishStore.class.getClassLoader().getResource("//").getPath() + "store_list.json";
        }
        
        try (JSONReader reader = new JSONReader(new FileReader(storesPath))) {
            reader.startArray();
            while(reader.hasNext()) {
                var storeInfo = reader.readObject(StoreInfo.class);
                store_address_info_map.put(storeInfo.address(), storeInfo);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (JSONReader reader = new JSONReader(new FileReader(itemsPath))) {
            reader.startArray();
            while(reader.hasNext()) {
                var itemInfo = reader.readObject(ItemInfo.class);
                
                String storeAddress = itemInfo.store_address();

                if(store_address_item_id_info_map.containsKey(storeAddress)){
                    store_address_item_id_info_map.get(storeAddress).put(itemInfo.id(), itemInfo);
                }
                else{
                    store_address_item_id_info_map.put(storeAddress, new ConcurrentHashMap<>(Map.of(itemInfo.id(), itemInfo)));
                }
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StoreInfo getStoreInfoByAddress(String address){
        return store_address_info_map.get(address);
    }

    public ItemInfo getItemById(String address, String item_id){
        var m = store_address_item_id_info_map.get(address);
        if(null == m) return null;
        else return m.get(item_id);
    }

    public List<StoreInfo> getStoreInfoList(){
        return store_address_info_map.values().stream().toList();
    }

    public List<ItemInfo> getItemInfoList(String address){
        var m = store_address_item_id_info_map.get(address);
        if (null == m) return null;
        else return m.values().stream().toList();
    }
}
