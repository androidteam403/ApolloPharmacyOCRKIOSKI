package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UpCellCrossCellResponse implements Serializable {
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("user_active_status")
    @Expose
    private String userActiveStatus;
    @SerializedName("user_chronic_active_status")
    @Expose
    private String userChronicActiveStatus;
    @SerializedName("billval_3months_band")
    @Expose
    private String billval3monthsBand;
    @SerializedName("upselling")
    @Expose
    private List<Upselling> upselling = null;
    @SerializedName("crossselling")
    @Expose
    private List<Crossselling> crossselling = null;
    private final static long serialVersionUID = 3876352385122103243L;

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getUserActiveStatus() {
        return userActiveStatus;
    }

    public void setUserActiveStatus(String userActiveStatus) {
        this.userActiveStatus = userActiveStatus;
    }

    public String getUserChronicActiveStatus() {
        return userChronicActiveStatus;
    }

    public void setUserChronicActiveStatus(String userChronicActiveStatus) {
        this.userChronicActiveStatus = userChronicActiveStatus;
    }

    public String getBillval3monthsBand() {
        return billval3monthsBand;
    }

    public void setBillval3monthsBand(String billval3monthsBand) {
        this.billval3monthsBand = billval3monthsBand;
    }

    public List<Upselling> getUpselling() {
        return upselling;
    }

    public void setUpselling(List<Upselling> upselling) {
        this.upselling = upselling;
    }

    public List<Crossselling> getCrossselling() {
        return crossselling;
    }

    public void setCrossselling(List<Crossselling> crossselling) {
        this.crossselling = crossselling;
    }

    public class Crossselling implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("itemid")
        @Expose
        private String itemid;
        @SerializedName("itemname")
        @Expose
        private String itemname;
        @SerializedName("reason")
        @Expose
        private String reason;

        private String offerPrice;


        private final static long serialVersionUID = 3686291619582335535L;

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

    }

    public class Upselling implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("itemid")
        @Expose
        private String itemid;
        @SerializedName("itemname")
        @Expose
        private String itemname;
        @SerializedName("reason")
        @Expose
        private String reason;

        private String offerPrice;

        private final static long serialVersionUID = 7652799238025050487L;

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

    }

}
