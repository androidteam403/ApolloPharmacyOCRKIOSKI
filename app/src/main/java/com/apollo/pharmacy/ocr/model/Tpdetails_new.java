package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tpdetails_new {

    @SerializedName("OrderId")
    @Expose
    private String orderId;
    @SerializedName("ShopId")
    @Expose
    private String shopId;
    @SerializedName("ShippingMethod")
    @Expose
    private String shippingMethod;
    @SerializedName("PaymentMethod")
    @Expose
    private String paymentMethod;
    @SerializedName("VendorName")
    @Expose
    private String vendorName;
    @SerializedName("DotorName")
    @Expose
    private String dotorName;
    @SerializedName("StateCode")
    @Expose
    private String stateCode;
    @SerializedName("TAT")
    @Expose
    private String tAT;
    @SerializedName("OrderType")
    @Expose
    private String orderType;
    @SerializedName("CouponCode")
    @Expose
    private String couponCode;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("RequestType")
    @Expose
    private String requestType;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("CustomerDetails")
    @Expose
    private Customerdetails_new customerDetails;
    @SerializedName("PaymentDetails")
    @Expose
    private Paymentdetails_new paymentDetails;
    @SerializedName("ItemDetails")
    @Expose
    private List<Itemdetail_new> itemDetails = null;
    @SerializedName("PrescUrl")
    @Expose
    private List<PrescUrl> prescUrl = null;

    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDotorName() {
        return dotorName;
    }

    public void setDotorName(String dotorName) {
        this.dotorName = dotorName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getTAT() {
        return tAT;
    }

    public void setTAT(String tAT) {
        this.tAT = tAT;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Customerdetails_new getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerdetails_new(Customerdetails_new customerDetails) {
        this.customerDetails = customerDetails;
    }

    public Paymentdetails_new getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentdetails_new(Paymentdetails_new paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<Itemdetail_new> getItemDetails() {
        return itemDetails;
    }

    public void setItemdetails_new(List<Itemdetail_new> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<PrescUrl> getPrescUrl() {
        return prescUrl;
    }

    public void setPrescUrl(List<PrescUrl> prescUrl) {
        this.prescUrl = prescUrl;
    }
   /* @SerializedName("Orderid")
    @Expose
    private String orderid;
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
    @SerializedName("DoctorName")
    @Expose
    private String doctorName;
    @SerializedName("Prescription")
    @Expose
    private Object prescription;
    @SerializedName("Ordertype")
    @Expose
    private Object ordertype;
    @SerializedName("Customerdetails")
    @Expose
    private Customerdetails_new customerdetails;
    @SerializedName("Paymentdetails")
    @Expose
    private Paymentdetails_new paymentdetails;
    @SerializedName("itemdetails")
    @Expose
    private List<Itemdetail_new> itemdetails = null;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Object getPrescription() {
        return prescription;
    }

    public void setPrescription(Object prescription) {
        this.prescription = prescription;
    }

    public Object getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Object ordertype) {
        this.ordertype = ordertype;
    }

    public Customerdetails_new getCustomerdetails() {
        return customerdetails;
    }

    public void setCustomerdetails_new(Customerdetails_new customerdetails) {
        this.customerdetails = customerdetails;
    }

    public Paymentdetails_new getPaymentdetails() {
        return paymentdetails;
    }

    public void setPaymentdetails_new(Paymentdetails_new paymentdetails) {
        this.paymentdetails = paymentdetails;
    }

    public List<Itemdetail_new> getItemdetails() {
        return itemdetails;
    }

    public void setItemdetails_new(List<Itemdetail_new> itemdetails) {
        this.itemdetails = itemdetails;
    }*/
}
