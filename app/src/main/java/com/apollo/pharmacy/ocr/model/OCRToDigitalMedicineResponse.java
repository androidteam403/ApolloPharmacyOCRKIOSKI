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

    public OCRToDigitalMedicineResponse() {

    }

    public OCRToDigitalMedicineResponse(Parcel source) {
        artCode = source.readString();
        artName = source.readString();
        container = source.readString();
        qty = source.readInt();
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

