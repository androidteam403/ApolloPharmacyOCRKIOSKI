package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceAvailabilityRequest {
    @Expose
    @SerializedName("VendorName")
    private String VendorName;
    @Expose
    @SerializedName("Pincode")
    private String Pincode;

    public void setVendorName(String VendorName) {
        this.VendorName = VendorName;
    }

    public void setPincode(String Pincode) {
        this.Pincode = Pincode;
    }
}
