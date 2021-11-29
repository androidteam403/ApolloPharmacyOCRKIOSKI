package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhonePayQrCodeRequest implements Serializable {

    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("expiresIn")
    @Expose
    private Integer expiresIn;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("originalTransactionId")
    @Expose
    private String originalTransactionId;
    @SerializedName("providerReferenceId")
    @Expose
    private String providerReferenceId;
    @SerializedName("reqType")
    @Expose
    private String reqType;
    @SerializedName("storeId")
    @Expose
    private String storeId;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = 6716098372723787894L;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOriginalTransactionId() {
        return originalTransactionId;
    }

    public void setOriginalTransactionId(String originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }

    public String getProviderReferenceId() {
        return providerReferenceId;
    }

    public void setProviderReferenceId(String providerReferenceId) {
        this.providerReferenceId = providerReferenceId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;

    }
}
