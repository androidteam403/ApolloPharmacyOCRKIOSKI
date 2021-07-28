package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetpointsResult implements Serializable {
    @Expose
    private String availablepoints;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("mobileno")
    @Expose
    private String mobileno;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("tier")
    @Expose
    private String tier;

    public String getAvailablepoints() {
        return availablepoints;
    }

    public void setAvailablepoints(String availablepoints) {
        this.availablepoints = availablepoints;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
}
