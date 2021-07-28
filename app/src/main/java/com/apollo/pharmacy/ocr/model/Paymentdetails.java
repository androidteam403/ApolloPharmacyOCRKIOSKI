package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paymentdetails {
    @SerializedName("Totalamount")
    @Expose
    private Object totalamount;
    @SerializedName("Paymentsource")
    @Expose
    private Object paymentsource;
    @SerializedName("Paymentstatus")
    @Expose
    private Object paymentstatus;
    @SerializedName("Paymentorderid")
    @Expose
    private Object paymentorderid;

    public Object getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Object totalamount) {
        this.totalamount = totalamount;
    }

    public Object getPaymentsource() {
        return paymentsource;
    }

    public void setPaymentsource(Object paymentsource) {
        this.paymentsource = paymentsource;
    }

    public Object getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(Object paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public Object getPaymentorderid() {
        return paymentorderid;
    }

    public void setPaymentorderid(Object paymentorderid) {
        this.paymentorderid = paymentorderid;
    }

}
