package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pinelabstransaction_response implements Serializable {
    @SerializedName("pinepayrequestResult")
    @Expose
    private PinepayrequestResult pinepayrequestResult;

    public PinepayrequestResult getPinepayrequestResult() {
        return pinepayrequestResult;
    }

    public void setPinepayrequestResult(PinepayrequestResult pinepayrequestResult) {
        this.pinepayrequestResult = pinepayrequestResult;
    }
}
