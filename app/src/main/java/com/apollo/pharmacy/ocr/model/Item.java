package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("artCode")
    @Expose
    private String artCode;
    @SerializedName("artName")
    @Expose
    private String artName;
    @SerializedName("qty")
    @Expose
    private int qty;

    @SerializedName("mrp")
    @Expose
    private float mrp;

    private String batchId;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setMrp(float mrp1) {
        this.mrp = mrp1;
    }

    public float getMrp() {
        return mrp;
    }

    public String getArtCode() {
        return artCode;
    }

    public void setArtCode(String artCode) {
        this.artCode = artCode;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
