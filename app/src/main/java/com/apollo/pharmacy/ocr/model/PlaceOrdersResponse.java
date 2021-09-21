package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrdersResponse {

    @SerializedName("ordersResult")
    @Expose
    private OrdersResult ordersResult;

    public OrdersResult getOrdersResult() {
        return ordersResult;
    }

    public void setOrdersResult(OrdersResult ordersResult) {
        this.ordersResult = ordersResult;
    }

    public class OrdersResult {

        @SerializedName("ApOrderNo")
        @Expose
        private String apOrderNo;
        @SerializedName("Message")
        @Expose
        private String message;
        @SerializedName("OrderNo")
        @Expose
        private String orderNo;
        @SerializedName("SiteId")
        @Expose
        private String siteId;
        @SerializedName("Status")
        @Expose
        private Boolean status;

        public String getApOrderNo() {
            return apOrderNo;
        }

        public void setApOrderNo(String apOrderNo) {
            this.apOrderNo = apOrderNo;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }
    }
}
