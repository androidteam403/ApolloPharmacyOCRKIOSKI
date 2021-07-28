package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itemdetail_new {
    @SerializedName("ItemID")
    @Expose
    private String itemID;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("Qty")
    @Expose
    private Integer qty;
    @SerializedName("MOU")
    @Expose
    private Integer mOU;
    @SerializedName("Pack")
    @Expose
    private String pack;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Remarks")
    @Expose
    private String remarks;

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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getMOU() {
        return mOU;
    }

    public void setMOU(Integer mOU) {
        this.mOU = mOU;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
