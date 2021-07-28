package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itemdetail {
    @Expose
    @SerializedName("ItemID")
    private String itemID;
    @Expose
    @SerializedName("ItemName")
    private String itemName;
    @Expose
    @SerializedName("Qty")
    private String qty;
    @Expose
    @SerializedName("Price")
    private float pricemrp;
    @Expose
    @SerializedName("Orderdate")
    private String orderdate;
    @Expose
    @SerializedName("MOU")
    private String mou;

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public float getPricemrp() {
        return pricemrp;
    }

    public void setPricemrp(float pricemrp) {
        this.pricemrp = pricemrp;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getMou() {
        return mou;
    }

    public void setMou(String mou) {
        this.mou = mou;
    }
}
