package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPushingResponse_new {
  /*  @SerializedName("ordersResult")
    @Expose
    private OrdersResult_new ordersResult;

    public OrdersResult_new getOrdersResult() {
        return ordersResult;
    }

    public void setOrdersResult_new(OrdersResult_new ordersResult) {
        this.ordersResult = ordersResult;
    }*/

    @SerializedName("ordersResult")
    @Expose
    private OrdersResult_new ordersResult;

    public OrdersResult_new getOrdersResult() {
        return ordersResult;
    }

    public void setOrdersResult_new(OrdersResult_new ordersResult) {
        this.ordersResult = ordersResult;
    }

}
