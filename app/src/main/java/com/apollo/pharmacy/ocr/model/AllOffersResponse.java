package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOffersResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("promoBanner")
        @Expose
        private String promoBanner;
        @SerializedName("promoTitle")
        @Expose
        private String promoTitle;
        @SerializedName("promocode")
        @Expose
        private String promocode;
        @SerializedName("promoBannerOrder")
        @Expose
        private Integer promoBannerOrder;
        @SerializedName("promoType")
        @Expose
        private String promoType;
        @SerializedName("promoAction")
        @Expose
        private String promoAction;
        @SerializedName("PromoStartDate")
        @Expose
        private String promoStartDate;
        @SerializedName("PromoEndDate")
        @Expose
        private String promoEndDate;
        @SerializedName("promoItemSelection")
        @Expose
        private Integer promoItemSelection;
        @SerializedName("promoItemSelectionType")
        @Expose
        private String promoItemSelectionType;
        @SerializedName("user_email")
        @Expose
        private String userEmail;
        @SerializedName("promoItems")
        @Expose
        private List<PromoItem> promoItems = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPromoBanner() {
            return promoBanner;
        }

        public void setPromoBanner(String promoBanner) {
            this.promoBanner = promoBanner;
        }

        public String getPromoTitle() {
            return promoTitle;
        }

        public void setPromoTitle(String promoTitle) {
            this.promoTitle = promoTitle;
        }

        public String getPromocode() {
            return promocode;
        }

        public void setPromocode(String promocode) {
            this.promocode = promocode;
        }

        public Integer getPromoBannerOrder() {
            return promoBannerOrder;
        }

        public void setPromoBannerOrder(Integer promoBannerOrder) {
            this.promoBannerOrder = promoBannerOrder;
        }

        public String getPromoType() {
            return promoType;
        }

        public void setPromoType(String promoType) {
            this.promoType = promoType;
        }

        public String getPromoAction() {
            return promoAction;
        }

        public void setPromoAction(String promoAction) {
            this.promoAction = promoAction;
        }

        public String getPromoStartDate() {
            return promoStartDate;
        }

        public void setPromoStartDate(String promoStartDate) {
            this.promoStartDate = promoStartDate;
        }

        public String getPromoEndDate() {
            return promoEndDate;
        }

        public void setPromoEndDate(String promoEndDate) {
            this.promoEndDate = promoEndDate;
        }

        public Integer getPromoItemSelection() {
            return promoItemSelection;
        }

        public void setPromoItemSelection(Integer promoItemSelection) {
            this.promoItemSelection = promoItemSelection;
        }

        public String getPromoItemSelectionType() {
            return promoItemSelectionType;
        }

        public void setPromoItemSelectionType(String promoItemSelectionType) {
            this.promoItemSelectionType = promoItemSelectionType;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public List<PromoItem> getPromoItems() {
            return promoItems;
        }

        public void setPromoItems(List<PromoItem> promoItems) {
            this.promoItems = promoItems;
        }

    }

    public class PromoItem {

        @SerializedName("artImage")
        @Expose
        private String artImage;
        @SerializedName("artCode")
        @Expose
        private String artCode;
        @SerializedName("productName")
        @Expose
        private String productName;
        @SerializedName("qty")
        @Expose
        private String qty;
        private int offerCount;

        private boolean isSelected;

        private String medicineType;
        private String artName;
        private boolean outOfStock;
        public String getArtImage() {
            return artImage;
        }

        public void setArtImage(String artImage) {
            this.artImage = artImage;
        }

        public String getArtCode() {
            return artCode;
        }

        public void setArtCode(String artCode) {
            this.artCode = artCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public int getOfferCount() {
            return offerCount;
        }

        public void setOfferCount(int offerCount) {
            this.offerCount = offerCount;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getMedicineType() {
            return medicineType;
        }

        public void setMedicineType(String medicineType) {
            this.medicineType = medicineType;
        }

        public String getArtName() {
            return artName;
        }

        public void setArtName(String artName) {
            this.artName = artName;
        }

        public boolean isOutOfStock() {
            return outOfStock;
        }

        public void setOutOfStock(boolean outOfStock) {
            this.outOfStock = outOfStock;
        }
    }
}
