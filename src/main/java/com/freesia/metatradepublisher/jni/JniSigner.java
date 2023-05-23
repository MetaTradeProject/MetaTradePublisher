package com.freesia.metatradepublisher.jni;

import com.freesia.metatradepublisher.MetaTradePublisherApplication;

public class JniSigner{
    static {
        if (MetaTradePublisherApplication.class.getResource("").getProtocol().equals("jar")) {
            String path = System.getProperty("user.dir") + "/lib/libsigner_pub.so";
            System.load(path);
        }
        else{
            String path = JniSigner.class.getClassLoader().getResource("//").getPath();
            System.load(path + "libsigner_pub.so");
        }
        
    }
    public native String SignTrade(String hash, String privateKey);
}