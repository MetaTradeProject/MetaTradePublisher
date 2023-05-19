package com.freesia.metatradepublisher.model;

public record CronTradeRequest(String cron, String receiverAddress, long amount) {
    
}
