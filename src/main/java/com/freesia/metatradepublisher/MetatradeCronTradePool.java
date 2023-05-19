package com.freesia.metatradepublisher;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.freesia.metatradepublisher.jni.JniSigner;
import com.freesia.metatradepublisher.model.CronTrade;
import com.freesia.metatradepublisher.model.Trade;
import com.freesia.metatradepublisher.rpc.FakeTradeClient;

public class MetatradeCronTradePool {
    private Map<String, CronTrade> registerTrades;
    private Map<String, ScheduledFuture<?>> runningTasks;
    private final ThreadPoolTaskScheduler scheduler;
    private final FakeTradeClient client;
    private final MetaTradePublishStore store;

    public MetatradeCronTradePool(FakeTradeClient client, MetaTradePublishStore store){
        registerTrades = new ConcurrentHashMap<>();
        runningTasks = new ConcurrentHashMap<>();
        scheduler = new ThreadPoolTaskScheduler();
        this.client = client;
        this.store = store;

        LoadLocalJobs();
    }

    private void LoadLocalJobs(){
        String configPath;
        if (MetaTradePublisherApplication.class.getResource("").getProtocol().equals("jar")) {
            configPath = System.getProperty("user.dir") + "/jobs/cron_jobs.json";
        }
        else{
            configPath = MetaTradePublishStore.class.getClassLoader().getResource("//").getPath() + "cron_jobs.json";
        }

        try (JSONReader reader = new JSONReader(new FileReader(configPath))) {
            reader.startArray();
            while(reader.hasNext()) {
                var cron_trade = reader.readObject(CronTrade.class);
                registerTrades.put(UUID.randomUUID().toString().replace("-", ""), cron_trade);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Init() {
        registerTrades.forEach((k, v) -> {
            var info = store.getStoreInfoByAddress(v.senderAddress());
            if(info != null){
                runningTasks.put(k, scheduler.schedule(new Runnable() {
                    @Override
                    public void run() {
                        var info = store.getStoreInfoByAddress(v.senderAddress());
                        if(info != null){
                            Trade trade = null;
                            if(v.id().equals("0")){
                                trade = new Trade(v.senderAddress(), v.receiverAddress(), v.amount(), 
                                    0, System.currentTimeMillis(), "", info.pubkey(), "");
                            }
                            else{
                                JSONObject object = new JSONObject();
                                object.put("id", v.id());
                                object.put("amount", v.amount());
                                trade = new Trade(v.senderAddress(), v.receiverAddress(), 0, 
                                    0, System.currentTimeMillis(), "", info.pubkey(), object.toJSONString());
                            }
    
                            JniSigner signer = new JniSigner();
                            String signature = signer.SignTrade(trade.getHash(), info.prikey());
                            trade.setSignature(signature);
                            client.RequestFakeTradeSync(trade);
                        }
                    }
                }, new CronTrigger(v.cron())));   
            }
        });
    }

    public String RegisterCronTrade(CronTrade cronTrade){
        var info = store.getStoreInfoByAddress(cronTrade.senderAddress());
        if(info == null){
            return "";
        }

        String key = UUID.randomUUID().toString();
        registerTrades.put(key, cronTrade);
        runningTasks.put(key, scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                var info = store.getStoreInfoByAddress(cronTrade.senderAddress());
                if(info != null){
                    Trade trade = null;
                    if(cronTrade.id().equals("0")){
                        trade = new Trade(cronTrade.senderAddress(), cronTrade.receiverAddress(), cronTrade.amount(), 
                            0, System.currentTimeMillis(), "", info.pubkey(), "");
                    }
                    else{
                        JSONObject object = new JSONObject();
                        object.put("id", cronTrade.id());
                        object.put("amount", cronTrade.amount());
                        trade = new Trade(cronTrade.senderAddress(), cronTrade.receiverAddress(), 0, 
                            0, System.currentTimeMillis(), "", info.pubkey(), object.toJSONString());
                    }

                    JniSigner signer = new JniSigner();
                    String signature = signer.SignTrade(trade.getHash(), info.prikey());
                    trade.setSignature(signature);
                    client.RequestFakeTradeSync(trade);
                }
            }
        }, new CronTrigger(cronTrade.cron())));

        return key;
    }

    public boolean UnregisterCronTrade(String key){
        if(registerTrades.get(key) == null){
            return false;
        }

        runningTasks.get(key).cancel(true);
        registerTrades.remove(key);
        runningTasks.remove(key);
        return true;
    }

    public boolean UpdateCronTrade(String key, CronTrade cronTrade){
        if(registerTrades.get(key) == null){
            return false;
        }

        runningTasks.get(key).cancel(true);
        registerTrades.put(key, cronTrade);
        runningTasks.put(key, scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                var info = store.getStoreInfoByAddress(cronTrade.senderAddress());
                if(info != null){
                    Trade trade = null;
                    if(cronTrade.id().equals("0")){
                        trade = new Trade(cronTrade.senderAddress(), cronTrade.receiverAddress(), cronTrade.amount(), 
                            0, System.currentTimeMillis(), "", info.pubkey(), "");
                    }
                    else{
                        JSONObject object = new JSONObject();
                        object.put("id", cronTrade.id());
                        object.put("amount", cronTrade.amount());
                        trade = new Trade(cronTrade.senderAddress(), cronTrade.receiverAddress(), 0, 
                            0, System.currentTimeMillis(), "", info.pubkey(), object.toJSONString());
                    }

                    JniSigner signer = new JniSigner();
                    String signature = signer.SignTrade(trade.getHash(), info.prikey());
                    trade.setSignature(signature);
                    client.RequestFakeTradeSync(trade);
                }
            }
        }, new CronTrigger(cronTrade.cron())));

        return true;
    }

    public CronTrade getCronTrade(String key){
        return registerTrades.get(key);
    }
}
