package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductSearch implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("is_in_stock")
    @Expose
    private int isInStock;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("small_image")
    @Expose
    private String smallImage;
    @SerializedName("special_price")
    @Expose
    Float specialPrice;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_prescription_required")
    @Expose
    private int isPrescriptionRequired;

    @SerializedName("mou")
    @Expose
    private String mou;
    private String medicineType;
    private Integer qty;

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getMou() {
        return mou;
    }

    public void setMou(String mou) {
        this.mou = mou;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(int isInStock) {
        this.isInStock = isInStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public float getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Float specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsPrescriptionRequired() {
        return isPrescriptionRequired;
    }

    public void setIsPrescriptionRequired(int isPrescriptionRequired) {
        this.isPrescriptionRequired = isPrescriptionRequired;
    }
}
