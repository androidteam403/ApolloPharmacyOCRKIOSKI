package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPushingRequest_new {
    @SerializedName("tpdetails")
    @Expose
    private Tpdetails_new tpdetails;

    public Tpdetails_new getTpdetails() {
        return tpdetails;
    }

    public void setTpdetails_new(Tpdetails_new tpdetails) {
        this.tpdetails = tpdetails;
    }


}
