package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PincodeValidationResponse {
    @SerializedName("availability")
    @Expose
    private String avialability;

    @SerializedName("remarks")
    @Expose
    private String remarks;


    public String getAvialability() {
        return avialability;
    }

    public void setAvialability(String avialability) {
        this.avialability = avialability;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
