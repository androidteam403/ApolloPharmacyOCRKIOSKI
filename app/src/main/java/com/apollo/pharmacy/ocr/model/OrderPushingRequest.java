package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPushingRequest {
    @SerializedName("tpdetails")
    @Expose
    private Tpdetails tpdetails;

    public Tpdetails getTpdetails() {
        return tpdetails;
    }

    public void setTpdetails(Tpdetails tpdetails) {
        this.tpdetails = tpdetails;
    }
}
