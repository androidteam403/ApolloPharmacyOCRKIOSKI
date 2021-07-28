package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InventoryBasedonSiteid implements Serializable {
    @SerializedName("artCode")
    @Expose
    private String artcode;
    @SerializedName("qoh")
    @Expose
    private int qty;

    public String getArtcode() {
        return artcode;
    }

    public void setArtcode(String artcode) {
        this.artcode = artcode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
