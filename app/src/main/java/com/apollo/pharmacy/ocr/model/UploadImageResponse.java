package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {
    @Expose
    @SerializedName("RequestMessage")
    private String RequestMessage;
    @Expose
    @SerializedName("RequestStatus")
    private boolean RequestStatus;

    public String getRequestMessage() {
        return RequestMessage;
    }

    public boolean getRequestStatus() {
        return RequestStatus;
    }
}
