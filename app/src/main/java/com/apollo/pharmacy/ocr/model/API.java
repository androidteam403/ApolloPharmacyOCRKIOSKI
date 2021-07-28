package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API {
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("TOKEN")
    @Expose
    private String tOKEN;

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public String getNAME() {
        return nAME;
    }

    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    public String getTOKEN() {
        return tOKEN;
    }

    public void setTOKEN(String tOKEN) {
        this.tOKEN = tOKEN;
    }
}
