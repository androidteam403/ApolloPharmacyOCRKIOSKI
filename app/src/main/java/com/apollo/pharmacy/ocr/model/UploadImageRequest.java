package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UploadImageRequest {
    @Expose
    @SerializedName("imageurl")
    private ArrayList<ImageurlEntity> imageurl;
    @Expose
    @SerializedName("Presc_id")
    private String Presc_id;
    @Expose
    @SerializedName("storeid")
    private String storeid;
    @Expose
    @SerializedName("Kioskid")
    private String Kioskid;
    @Expose
    @SerializedName("lastname")
    private String lastname;
    @Expose
    @SerializedName("firstname")
    private String firstname;
    @Expose
    @SerializedName("mobileno")
    private String mobileno;

    public void setImageurl(ArrayList<ImageurlEntity> imageurl) {
        this.imageurl = imageurl;
    }

    public void setPresc_id(String Presc_id) {
        this.Presc_id = Presc_id;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public void setKioskid(String Kioskid) {
        this.Kioskid = Kioskid;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public static class ImageurlEntity {
        @Expose
        @SerializedName("url")
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
