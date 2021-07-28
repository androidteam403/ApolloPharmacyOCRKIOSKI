package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Send_Sms_Request {

    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isOtp")
    @Expose
    private Boolean isOtp;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("apiType")
    @Expose
    private String apiType;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsOtp() {
        return isOtp;
    }

    public void setIsOtp(Boolean isOtp) {
        this.isOtp = isOtp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

}
