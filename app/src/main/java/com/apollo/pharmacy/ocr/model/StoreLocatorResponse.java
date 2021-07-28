package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreLocatorResponse implements Serializable {
    @SerializedName("storeid")
    @Expose
    private String storeid;
    @SerializedName("storename")
    @Expose
    private String storename;
    @SerializedName("branchtype")
    @Expose
    private String branchtype;
    @SerializedName("baddress")
    @Expose
    private String baddress;
    @SerializedName("phno")
    @Expose
    private String phno;
    @SerializedName("plat")
    @Expose
    private String plat;
    @SerializedName("plon")
    @Expose
    private String plon;
    @SerializedName("pcity")
    @Expose
    private String pcity;
    @SerializedName("staten")
    @Expose
    private String staten;
    @SerializedName("pincode")
    @Expose
    private String pincode;

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getBranchtype() {
        return branchtype;
    }

    public void setBranchtype(String branchtype) {
        this.branchtype = branchtype;
    }

    public String getBaddress() {
        return baddress;
    }

    public void setBaddress(String baddress) {
        this.baddress = baddress;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getPlon() {
        return plon;
    }

    public void setPlon(String plon) {
        this.plon = plon;
    }

    public String getPcity() {
        return pcity;
    }

    public void setPcity(String pcity) {
        this.pcity = pcity;
    }

    public String getStaten() {
        return staten;
    }

    public void setStaten(String staten) {
        this.staten = staten;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

}
