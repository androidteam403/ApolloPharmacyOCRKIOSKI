package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPushingResponse {

    @SerializedName("ordersResult")
    @Expose
    private OrdersResult ordersResult;

    public OrdersResult getOrdersResult() {
        return ordersResult;
    }

    public void setOrdersResult(OrdersResult ordersResult) {
        this.ordersResult = ordersResult;
    }
}
