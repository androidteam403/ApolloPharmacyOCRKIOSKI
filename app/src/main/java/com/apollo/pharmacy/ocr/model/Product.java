package com.apollo.pharmacy.ocr.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable, Parcelable {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("sku")
    private String sku;
    @Expose
    @SerializedName("price")
    private float price;
    @Expose
    @SerializedName("special_price")
    private String specialPrice;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("status")
    private int status;
    @Expose
    @SerializedName("type_id")
    private String typeId;
    @Expose
    @SerializedName("is_in_stock")
    private int isInStock;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("small_image")
    private String small_image;
    @Expose
    @SerializedName("thumbnail")
    private String thumbnail;
    @Expose
    @SerializedName("is_prescription_required")
    private String isPrescriptionRequired;
    @Expose
    @SerializedName("mou")
    private String mou;

    public String type;
    private String product_offerprice;
    private String product_pricetype;

    public void setProduct_pricetype(String product_pricetype1) {
        this.product_pricetype = product_pricetype1;
    }

    public String getProduct_pricetype() {
        return product_pricetype;
    }

    public void setProduct_offerprice(String product_offerprice1) {
        this.product_offerprice = product_offerprice1;
    }

    public String getProduct_offerprice() {
        return product_offerprice;
    }

    public Product(String type) {
        this.type = type;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public String getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getMou() {
        return mou;
    }

    public void setMou(String mou) {
        this.mou = mou;
    }

    public String getIsPrescriptionRequired() {
        return isPrescriptionRequired;
    }

    public void setIsPrescriptionRequired(String isPrescriptionRequired) {
        this.isPrescriptionRequired = isPrescriptionRequired;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.sku);
        dest.writeFloat(this.price);
        dest.writeString(this.specialPrice);
        dest.writeString(this.name);
        dest.writeInt(this.status);
        dest.writeString(this.typeId);
        dest.writeInt(this.isInStock);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.small_image);
        dest.writeString(this.thumbnail);
        dest.writeString(this.isPrescriptionRequired);
        dest.writeString(this.mou);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.sku = in.readString();
        this.price = in.readFloat();
        this.specialPrice = in.readString();
        this.name = in.readString();
        this.status = in.readInt();
        this.typeId = in.readString();
        this.isInStock = in.readInt();
        this.description = in.readString();
        this.image = in.readString();
        this.small_image = in.readString();
        this.thumbnail = in.readString();
        this.isPrescriptionRequired = in.readString();
        this.mou = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
