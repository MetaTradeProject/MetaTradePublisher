package com.freesia.metatradepublisher.model;

public record CronTrade(String cron, String senderAddress, String receiverAddress, String id, long amount) {
    
}
