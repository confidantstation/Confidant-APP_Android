package com.stratagile.pnrouter.entity;

/**
 * Created by zl on 2018/11/08.
 */

public class CryptoBoxKeypair {
    private String privateKey;
    private String publicKey;
    private String userName;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


