package com.apollo.pharmacy.ocr.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OCRToDigitalMedicineResponse implements Serializable {

    @Expose
    @SerializedName("artCode")
    private String artCode;
    @Expose
    @SerializedName("artName")
    private String artName;
    @Expose
    @SerializedName("container")
    private String container;
    @Expose
    @SerializedName("artprice")
    private String artprice;
    @Expose
    @SerializedName("qty")
    private int qty;
    @Expose
    @SerializedName("imageurl")
    private String imageurl;
    @Expose
    @SerializedName("mou")
    private String mou;
    private String medicineType;
    private boolean outOfStock;
    private String batchId;

    private boolean expandStatus;

    private int labelAvgQty;

    private int duplicateCount;

    private float labelPrice;

    private String labelName;

    private float labelAveragePrice;

    public float getLabelAveragePrice() {
        return labelAveragePrice;
    }

    public void setLabelAveragePrice(float labelAveragePrice) {
        this.labelAveragePrice = labelAveragePrice;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getLabelAvgQty() {
        return labelAvgQty;
    }

    public void setLabelAvgQty(int labelAvgQty) {
        this.labelAvgQty = labelAvgQty;
    }

    public int getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(int duplicateCount) {
        this.duplicateCount = duplicateCount;
    }

    public float getLabelPrice() {
        return labelPrice;
    }

    public void setLabelPrice(float labelPrice) {
        this.labelPrice = labelPrice;
    }

    public boolean isExpandStatus() {
        return expandStatus;
    }

    public void setExpandStatus(boolean expandStatus) {
        this.expandStatus = expandStatus;
    }

    public OCRToDigitalMedicineResponse() {

    }

    public OCRToDigitalMedicineResponse(Parcel source) {
        artCode = source.readString();
        artName = source.readString();
        container = source.readString();
        qty = source.readInt();
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getArtprice() {
        return artprice;
    }

    public void setArtprice(String artprice) {
        this.artprice = artprice;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public String getArtCode() {
        return artCode;
    }

    public void setArtCode(String artCode) {
        this.artCode = artCode;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setImageurl(String url) {
        this.imageurl = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getMou() {
        return mou;
    }

    public void setMou(String mou) {
        this.mou = mou;
    }
}

