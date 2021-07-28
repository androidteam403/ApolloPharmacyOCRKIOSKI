package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Searchsuggestionresponse {
    @SerializedName("products")
    @Expose
    private List<Suggestion_Product> products = null;
    @SerializedName("product_count")
    @Expose
    private Integer productCount;

    public List<Suggestion_Product> getProducts() {
        return products;
    }

    public void setProducts(List<Suggestion_Product> products) {
        this.products = products;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
