package com.apollo.pharmacy.ocr.controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStoreInfoRequest {
    @Expose
    @SerializedName("MakeInActive")
    private boolean MakeInActive;
    @Expose
    @SerializedName("DeviceId")
    private String DeviceId;

    public void setMakeInActive(boolean MakeInActive) {
        this.MakeInActive = MakeInActive;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }
}
