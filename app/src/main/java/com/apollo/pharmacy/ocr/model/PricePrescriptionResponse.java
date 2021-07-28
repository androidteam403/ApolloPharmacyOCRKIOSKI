package com.apollo.pharmacy.ocr.model;

import java.util.List;

public class PricePrescriptionResponse {
    private List<ProductPricePrescription> products;

    public List<ProductPricePrescription> getProducts() {
        return products;
    }

    public void setProducts(List<ProductPricePrescription> products) {
        this.products = products;
    }
}
