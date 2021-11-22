package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.List;

public class OrderHistoryResponse {

    @SerializedName("patName")
    @Expose
    private String patName;
    @SerializedName("patAddr")
    @Expose
    private String patAddr;
    @SerializedName("ordSrc")
    @Expose
    private String ordSrc;
    @SerializedName("payMode")
    @Expose
    private String payMode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ordno")
    @Expose
    private String ordno;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("Items")
    @Expose
    private List<Item> items = null;
    @SerializedName("StatusHistory")
    @Expose
    private List<StatusHistory> statusHistory = null;
    @SerializedName("UploadedPrescDocs")
    @Expose
    private List<UploadedPrescDoc> uploadedPrescDocs = null;

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatAddr() {
        return patAddr;
    }

    public void setPatAddr(String patAddr) {
        this.patAddr = patAddr;
    }

    public String getOrdSrc() {
        return ordSrc;
    }

    public void setOrdSrc(String ordSrc) {
        this.ordSrc = ordSrc;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdno() {
        return ordno;
    }

    public void setOrdno(String ordno) {
        this.ordno = ordno;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<StatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<StatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public List<UploadedPrescDoc> getUploadedPrescDocs() {
        return uploadedPrescDocs;
    }

    public void setUploadedPrescDocs(List<UploadedPrescDoc> uploadedPrescDocs) {
        this.uploadedPrescDocs = uploadedPrescDocs;
    }


    public static class StatusHistory {

        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("DateTime")
        @Expose
        private String dateTime;
        private Time time;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public Time getTime() {
            return time;
        }

        public void setTime(Time time) {
            this.time = time;
        }
    }

    public static class Item {

        @SerializedName("artCode")
        @Expose
        private String artCode;
        @SerializedName("artName")
        @Expose
        private String artName;
        @SerializedName("qty")
        @Expose
        private Integer qty;
        @SerializedName("mrp")
        @Expose
        private Double mrp;

        public String getArtCode() {
            return artCode;
        }

        public void setArtCode(String artCode) {
            this.artCode = artCode;
        }

        public String getArtName() {
            return artName;
        }

        public void setArtName(String artName) {
            this.artName = artName;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public Double getMrp() {
            return mrp;
        }

        public void setMrp(Double mrp) {
            this.mrp = mrp;
        }
    }

//public class OrderHistoryResponse {
//
//    @SerializedName("patName")
//    @Expose
//    private String patName;
//    @SerializedName("patAddr")
//    @Expose
//    private String patAddr;
//    @SerializedName("ordSrc")
//    @Expose
//    private String ordSrc;
//    @SerializedName("payMode")
//    @Expose
//    private String payMode;
//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("ordno")
//    @Expose
//    private String ordno;
//    @SerializedName("remarks")
//    @Expose
//    private String remarks;
//    @SerializedName("Items")
//    @Expose
//    private List<Item> items = null;
//    @SerializedName("StatusHistory")
//    @Expose
//    private List<StatusHistory> statusHistory = null;
//    @SerializedName("UploadedPrescDocs")
//    @Expose
//    private List<UploadedPrescDoc> uploadedPrescDocs = null;
//    private boolean isSelected = false;
//
//    public boolean isSelected() {
//        return isSelected;
//    }
//
//    public void setSelected(boolean selected) {
//        isSelected = selected;
//    }
//
//    public String getPatName() {
//        return patName;
//    }
//
//    public void setPatName(String patName) {
//        this.patName = patName;
//    }
//
//    public String getPatAddr() {
//        return patAddr;
//    }
//
//    public void setPatAddr(String patAddr) {
//        this.patAddr = patAddr;
//    }
//
//    public String getOrdSrc() {
//        return ordSrc;
//    }
//
//    public void setOrdSrc(String ordSrc) {
//        this.ordSrc = ordSrc;
//    }
//
//    public String getPayMode() {
//        return payMode;
//    }
//
//    public void setPayMode(String payMode) {
//        this.payMode = payMode;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getOrdno() {
//        return ordno;
//    }
//
//    public void setOrdno(String ordno) {
//        this.ordno = ordno;
//    }
//
//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }
//
//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }
//
//    public List<StatusHistory> getStatusHistory() {
//        return statusHistory;
//    }
//
//    public void setStatusHistory(List<StatusHistory> statusHistory) {
//        this.statusHistory = statusHistory;
//    }
//    public List<UploadedPrescDoc> getUploadedPrescDocs() {
//        return uploadedPrescDocs;
//    }
//
//    public void setUploadedPrescDocs(List<UploadedPrescDoc> uploadedPrescDocs) {
//        this.uploadedPrescDocs = uploadedPrescDocs;
//    }


    public static class UploadedPrescDoc {

        @SerializedName("displayURL")
        @Expose
        private String displayURL;
        @SerializedName("prescURL")
        @Expose
        private String prescURL;

        public String getDisplayURL() {
            return displayURL;
        }

        public void setDisplayURL(String displayURL) {
            this.displayURL = displayURL;
        }

        public String getPrescURL() {
            return prescURL;
        }

        public void setPrescURL(String prescURL) {
            this.prescURL = prescURL;
        }

    }
}
