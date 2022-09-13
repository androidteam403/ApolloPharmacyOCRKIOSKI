package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPackSizeRequest {

    @SerializedName("itemsdetails")
    @Expose
    private List<Itemsdetail> itemsdetails = null;

    public List<Itemsdetail> getItemsdetails() {
        return itemsdetails;
    }

    public void setItemsdetails(List<Itemsdetail> itemsdetails) {
        this.itemsdetails = itemsdetails;
    }

    public static class Itemsdetail {

        @SerializedName("itemid")
        @Expose
        private String itemid;
        @SerializedName("packsze")
        @Expose
        private Integer packsze;

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public Integer getPacksze() {
            return packsze;
        }

        public void setPacksze(Integer packsze) {
            this.packsze = packsze;
        }

    }
}
