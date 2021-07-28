package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inventory implements Serializable {
    @SerializedName("siteid")
    @Expose
    private String siteid;
    @SerializedName("artcode")
    @Expose
    private String artcode;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("qty")
    @Expose
    private int qty;

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getArtcode() {
        return artcode;
    }

    public void setArtcode(String artcode) {
        this.artcode = artcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
