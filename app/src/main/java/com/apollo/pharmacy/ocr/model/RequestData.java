package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {
    @SerializedName("StoreId")
    @Expose
    private String storeId;
    @SerializedName("DocNum")
    @Expose
    private String docNum;
    @SerializedName("MobileNum")
    @Expose
    private String mobileNum;
    @SerializedName("ReqBy")
    @Expose
    private String reqBy;
    @SerializedName("Points")
    @Expose
    private String points;
    @SerializedName("RRNO")
    @Expose
    private String rRNO;
    @SerializedName("OTP")
    @Expose
    private String oTP;
    @SerializedName("Action")
    @Expose
    private String action;
    @SerializedName("Coupon")
    @Expose
    private String coupon;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("CustomerID")
    @Expose
    private String customerID;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getReqBy() {
        return reqBy;
    }

    public void setReqBy(String reqBy) {
        this.reqBy = reqBy;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getRRNO() {
        return rRNO;
    }

    public void setRRNO(String rRNO) {
        this.rRNO = rRNO;
    }

    public String getOTP() {
        return oTP;
    }

    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

}
