package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pinelabs_paymenttransaction_response implements Serializable {
    @SerializedName("ResponseCode")
    @Expose
    private int responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("PlutusTransactionReferenceID")
    @Expose
    private int plutusTransactionReferenceID;
    @SerializedName("TransactionData")
    @Expose
    private List<TransactionDatum> transactionData = null;

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

    public int getPlutusTransactionReferenceID() {
        return plutusTransactionReferenceID;
    }

    public void setPlutusTransactionReferenceID(int plutusTransactionReferenceID) {
        this.plutusTransactionReferenceID = plutusTransactionReferenceID;
    }

    public List<TransactionDatum> getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(List<TransactionDatum> transactionData) {
        this.transactionData = transactionData;
    }

}
