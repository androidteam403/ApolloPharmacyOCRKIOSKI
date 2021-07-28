package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaceOrderResModel {
    @Expose
    @SerializedName("ordersResult")
    private OrdersResultEntity ordersResult;

    public OrdersResultEntity getOrdersResult() {
        return ordersResult;
    }

    public static class OrdersResultEntity implements Serializable {
        @Expose
        @SerializedName("Status")
        private boolean Status;
        @Expose
        @SerializedName("SiteId")
        private String SiteId;
        @Expose
        @SerializedName("OrderNo")
        private String OrderNo;
        @Expose
        @SerializedName("Message")
        private String Message;
        @Expose
        @SerializedName("ApOrderNo")
        private String ApOrderNo;

        public boolean getStatus() {
            return Status;
        }

        public String getSiteId() {
            return SiteId;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public String getMessage() {
            return Message;
        }

        public String getApOrderNo() {
            return ApOrderNo;
        }
    }
}
