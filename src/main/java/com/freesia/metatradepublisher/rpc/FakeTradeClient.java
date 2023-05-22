package com.freesia.metatradepublisher.rpc;

import com.freesia.metatradepublisher.model.Trade;
import com.freesia.metatradepublisher.rpc.FakeTradeGrpc.FakeTradeBlockingStub;
import com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeTradeClient {
    private final FakeTradeBlockingStub blockingStub;
    
    public FakeTradeClient(String host, int port) {
        Channel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = FakeTradeGrpc.newBlockingStub(channel);
    }

    public boolean RequestFakeTradeSync(Trade trade){
        log.info("gRPC Client: Receiver a fake trade, send to Server.");
        FakeTradeMessage message = FakeTradeMessage.newBuilder().setSenderAddress(trade.getSenderAddress())
                                                                .setReceiverAddress(trade.getReceiverAddress())
                                                                .setAmount(trade.getAmount())
                                                                .setTimestamp(trade.getTimestamp())
                                                                .setSignature(trade.getSignature())
                                                                .setSenderPublicKey(trade.getSenderPublicKey())
                                                                .setDescription(trade.getDescription())
                                                                .build();
        var res = blockingStub.submitFakeTrade(message);
        return res.getResult();
    }
}
