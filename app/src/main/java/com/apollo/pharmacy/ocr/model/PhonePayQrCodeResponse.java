package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhonePayQrCodeResponse implements Serializable {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("QrCode")
    @Expose
    private Object qrCode;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("providerReferenceId")
    @Expose
    private String providerReferenceId;
    private final static long serialVersionUID = -2187061808453914291L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getQrCode() {
        return qrCode;
    }

    public void setQrCode(Object qrCode) {
        this.qrCode = qrCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getProviderReferenceId() {
        return providerReferenceId;
    }

    public void setProviderReferenceId(String providerReferenceId) {
        this.providerReferenceId = providerReferenceId;
    }
}
