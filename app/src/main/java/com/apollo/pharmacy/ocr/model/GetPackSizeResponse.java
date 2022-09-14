package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPackSizeResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("itemsdetails")
    @Expose
    private List<Itemsdetail> itemsdetails = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Itemsdetail> getItemsdetails() {
        return itemsdetails;
    }

    public void setItemsdetails(List<Itemsdetail> itemsdetails) {
        this.itemsdetails = itemsdetails;
    }

    public class Itemsdetail {

        @SerializedName("itemid")
        @Expose
        private String itemid;
        @SerializedName("packsize")
        @Expose
        private Integer packsize;

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public Integer getPacksize() {
            return packsize;
        }

        public void setPacksize(Integer packsize) {
            this.packsize = packsize;
        }

    }
}
