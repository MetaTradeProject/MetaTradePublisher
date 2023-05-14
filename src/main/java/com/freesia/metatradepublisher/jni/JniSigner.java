package com.freesia.metatradegateway.jni;

import com.freesia.metatradegateway.MetaTradeGatewayApplication;

public class JniSigner{
    static {
        if (MetaTradeGatewayApplication.class.getResource("").getProtocol().equals("jar")) {
            String path = System.getProperty("user.dir") + "/lib/libsigner.so";
            System.load(path);
        }
        else{
            String path = JniSigner.class.getClassLoader().getResource("//").getPath();
            System.load(path + "libsigner.so");
        }
        
    }
    public native String SignTrade(String hash, String privateKey);
}