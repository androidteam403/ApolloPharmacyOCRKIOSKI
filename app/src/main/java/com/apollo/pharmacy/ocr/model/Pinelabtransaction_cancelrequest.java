package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pinelabtransaction_cancelrequest implements Serializable {
    @SerializedName("MerchantID")
    @Expose
    private int merchantID;
    @SerializedName("SecurityToken")
    @Expose
    private String securityToken;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    @SerializedName("MerchantStorePosCode")
    @Expose
    private String merchantStorePosCode;
    @SerializedName("PlutusTransactionReferenceID")
    @Expose
    private int plutusTransactionReferenceID;
    @SerializedName("Amount")
    @Expose
    private int amount;

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getIMEI() {
        return iMEI;
    }

    public void setIMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public String getMerchantStorePosCode() {
        return merchantStorePosCode;
    }

    public void setMerchantStorePosCode(String merchantStorePosCode) {
        this.merchantStorePosCode = merchantStorePosCode;
    }

    public int getPlutusTransactionReferenceID() {
        return plutusTransactionReferenceID;
    }

    public void setPlutusTransactionReferenceID(int plutusTransactionReferenceID) {
        this.plutusTransactionReferenceID = plutusTransactionReferenceID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
