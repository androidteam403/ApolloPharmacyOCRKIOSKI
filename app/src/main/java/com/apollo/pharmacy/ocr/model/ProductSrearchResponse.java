package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductSrearchResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("producttype")
    @Expose
    private String producttype;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("offerprice")
    @Expose
    private Integer offerprice;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("url_key")
    @Expose
    private String urlKey;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("stock")
    @Expose
    private Boolean stock;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("Assured")
    @Expose
    private String assured;
    @SerializedName("Attr360View")
    @Expose
    private String attr360View;
    @SerializedName("view_link")
    @Expose
    private String viewLink;
    @SerializedName("categoryIds")
    @Expose
    private List<String> categoryIds = null;
    @SerializedName("is_salable")
    @Expose
    private Boolean isSalable;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("small_image")
    @Expose
    private String smallImage;
    private final static long serialVersionUID = -3206487149369234521L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getOfferprice() {
        return offerprice;
    }

    public void setOfferprice(Integer offerprice) {
        this.offerprice = offerprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getAssured() {
        return assured;
    }

    public void setAssured(String assured) {
        this.assured = assured;
    }

    public String getAttr360View() {
        return attr360View;
    }

    public void setAttr360View(String attr360View) {
        this.attr360View = attr360View;
    }

    public String getViewLink() {
        return viewLink;
    }

    public void setViewLink(String viewLink) {
        this.viewLink = viewLink;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Boolean getIsSalable() {
        return isSalable;
    }

    public void setIsSalable(Boolean isSalable) {
        this.isSalable = isSalable;
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

}