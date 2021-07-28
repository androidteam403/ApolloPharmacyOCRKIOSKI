package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tpdetails {
    @SerializedName("Orderid")
    @Expose
    private String orderid;
    @SerializedName("Orderdate")
    @Expose
    private String orderdate;
    @SerializedName("Shopid")
    @Expose
    private String shopid;
    @SerializedName("ShippingMethod")
    @Expose
    private String shippingMethod;
    @SerializedName("PaymentMethod")
    @Expose
    private String paymentMethod;
    @SerializedName("Vendorname")
    @Expose
    private String vendorname;
    @SerializedName("DotorName")
    @Expose
    private String dotorName;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("Ordertype")
    @Expose
    private String ordertype;
    @SerializedName("Customerdetails")
    @Expose
    private Customerdetails customerdetails;
    @SerializedName("Paymentdetails")
    @Expose
    private Paymentdetails paymentdetails;
    @SerializedName("itemdetails")
    @Expose
    private List<Itemdetail> itemdetails = null;
    @SerializedName("PrescImage")
    @Expose
    private List<Object> prescImage = null;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getDotorName() {
        return dotorName;
    }

    public void setDotorName(String dotorName) {
        this.dotorName = dotorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public Customerdetails getCustomerdetails() {
        return customerdetails;
    }

    public void setCustomerdetails(Customerdetails customerdetails) {
        this.customerdetails = customerdetails;
    }

    public Paymentdetails getPaymentdetails() {
        return paymentdetails;
    }

    public void setPaymentdetails(Paymentdetails paymentdetails) {
        this.paymentdetails = paymentdetails;
    }

    public List<Itemdetail> getItemdetails() {
        return itemdetails;
    }

    public void setItemdetails(List<Itemdetail> itemdetails) {
        this.itemdetails = itemdetails;
    }

    public List<Object> getPrescImage() {
        return prescImage;
    }

    public void setPrescImage(List<Object> prescImage) {
        this.prescImage = prescImage;
    }
}
