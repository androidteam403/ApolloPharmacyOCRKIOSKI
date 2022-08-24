package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelfOrderHistoryRequest {

    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("Key")
    @Expose
    private String key;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("UserId")
    @Expose
    private String userId;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}