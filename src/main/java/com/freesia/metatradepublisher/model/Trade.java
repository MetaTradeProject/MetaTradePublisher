package com.freesia.metatradepublisher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freesia.metatradepublisher.CryptoUtils;

public class Trade {
    private String senderAddress;
    private String receiverAddress;
    private double amount;
    private double commission;
    private long timestamp;
    private String signature;
    private String senderPublicKey;
    private String description;

    public Trade(String senderAddress, String receiverAddress, double amount, double commission, long timestamp, String signature, String senderPublicKey, String description) {
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.amount = amount;
        this.commission = commission;
        this.timestamp = timestamp;
        this.signature = signature;
        this.senderPublicKey = senderPublicKey;
        this.description = description;
    }

    
    
    public String getSenderAddress() {
        return senderAddress;
    }



    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }



    public String getReceiverAddress() {
        return receiverAddress;
    }



    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }



    public double getAmount() {
        return amount;
    }



    public void setAmount(double amount) {
        this.amount = amount;
    }



    public double getCommission() {
        return commission;
    }



    public void setCommission(double commission) {
        this.commission = commission;
    }



    public long getTimestamp() {
        return timestamp;
    }



    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }



    public String getSignature() {
        return signature;
    }



    public void setSignature(String signature) {
        this.signature = signature;
    }



    public String getSenderPublicKey() {
        return senderPublicKey;
    }



    public void setSenderPublicKey(String senderPublicKey) {
        this.senderPublicKey = senderPublicKey;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public String getHash() {
        return CryptoUtils.getSHA256(senderAddress + receiverAddress + amount + commission + timestamp);
    }
}
