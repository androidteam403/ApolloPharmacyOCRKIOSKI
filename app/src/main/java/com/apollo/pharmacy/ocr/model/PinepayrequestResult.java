package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PinepayrequestResult implements Serializable {
    @SerializedName("PlutusTransactionReferenceID")
    @Expose
    private int plutusTransactionReferenceID;
    @SerializedName("ResponseCode")
    @Expose
    private int responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;

    public int getPlutusTransactionReferenceID() {
        return plutusTransactionReferenceID;
    }

    public void setPlutusTransactionReferenceID(int plutusTransactionReferenceID) {
        this.plutusTransactionReferenceID = plutusTransactionReferenceID;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
