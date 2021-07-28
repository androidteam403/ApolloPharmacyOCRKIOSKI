package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneApolloProcessResult {
    @SerializedName("Action")
    @Expose
    private String action;
    @SerializedName("AvailablePoints")
    @Expose
    private String availablePoints;
    @SerializedName("DiscountAmount")
    @Expose
    private Object discountAmount;
    @SerializedName("DiscountPercentage")
    @Expose
    private Object discountPercentage;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("MobileNum")
    @Expose
    private String mobileNum;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("OTP")
    @Expose
    private Object oTP;
    @SerializedName("RRNO")
    @Expose
    private Object rRNO;
    @SerializedName("RedeemPoints")
    @Expose
    private Object redeemPoints;
    @SerializedName("Tier")
    @Expose
    private String tier;
    @SerializedName("VoucherCode")
    @Expose
    private Object voucherCode;
    @SerializedName("status")
    @Expose
    private String status;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(String availablePoints) {
        this.availablePoints = availablePoints;
    }

    public Object getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Object discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Object getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Object discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getOTP() {
        return oTP;
    }

    public void setOTP(Object oTP) {
        this.oTP = oTP;
    }

    public Object getRRNO() {
        return rRNO;
    }

    public void setRRNO(Object rRNO) {
        this.rRNO = rRNO;
    }

    public Object getRedeemPoints() {
        return redeemPoints;
    }

    public void setRedeemPoints(Object redeemPoints) {
        this.redeemPoints = redeemPoints;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Object getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(Object voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
