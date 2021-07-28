package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pinelabsrequest implements Serializable {
    @SerializedName("TransactionNumber")
    @Expose
    private String transactionNumber;
    @SerializedName("SequenceNumber")
    @Expose
    private int sequenceNumber;
    @SerializedName("AllowedPaymentMode")
    @Expose
    private String allowedPaymentMode;
    @SerializedName("MerchantStorePosCode")
    @Expose
    private String merchantStorePosCode;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("MerchantID")
    @Expose
    private int merchantID;
    @SerializedName("SecurityToken")
    @Expose
    private String securityToken;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getAllowedPaymentMode() {
        return allowedPaymentMode;
    }

    public void setAllowedPaymentMode(String allowedPaymentMode) {
        this.allowedPaymentMode = allowedPaymentMode;
    }

    public String getMerchantStorePosCode() {
        return merchantStorePosCode;
    }

    public void setMerchantStorePosCode(String merchantStorePosCode) {
        this.merchantStorePosCode = merchantStorePosCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

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
}
