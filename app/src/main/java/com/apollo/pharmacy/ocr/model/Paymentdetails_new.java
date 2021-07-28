package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paymentdetails_new {
    @SerializedName("TotalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("PaymentSource")
    @Expose
    private String paymentSource;
    @SerializedName("PaymentStatus")
    @Expose
    private String paymentStatus;
    @SerializedName("PaymentOrderId")
    @Expose
    private String paymentOrderId;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentSource() {
        return paymentSource;
    }

    public void setPaymentSource(String paymentSource) {
        this.paymentSource = paymentSource;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(String paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }
}
