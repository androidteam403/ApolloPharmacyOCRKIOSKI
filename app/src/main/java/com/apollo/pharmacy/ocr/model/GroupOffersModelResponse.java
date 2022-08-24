package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GroupOffersModelResponse implements Serializable {

    @SerializedName("Offers")
    @Expose
    private List<Offer> offers = null;
    private final static long serialVersionUID = 5283227745982775966L;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public class Offer implements Serializable {

        @SerializedName("promoBanner")
        @Expose
        private String promoBanner;
        @SerializedName("promoTitle")
        @Expose
        private String promoTitle;
        @SerializedName("promoBannerOrder")
        @Expose
        private Integer promoBannerOrder;
        @SerializedName("promoType")
        @Expose
        private String promoType;
        @SerializedName("promoAction")
        @Expose
        private String promoAction;
        @SerializedName("promoItemSelection")
        @Expose
        private Integer promoItemSelection;
        @SerializedName("promoItemSelectionType")
        @Expose
        private String promoItemSelectionType;
        @SerializedName("promoItems")
        @Expose
        private List<PromoItem> promoItems = null;
        private final static long serialVersionUID = 8184107440190675110L;

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

        public List<PromoItem> getPromoItems() {
            return promoItems;
        }

        public void setPromoItems(List<PromoItem> promoItems) {
            this.promoItems = promoItems;
        }

        public class PromoItem implements Serializable {

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
            private Integer qty;
            private final static long serialVersionUID = 7954219702409242821L;

            private int offerCount;

            private boolean isSelected;

            private String medicineType;
            private String artName;
            private boolean outOfStock;

            public boolean isOutOfStock() {
                return outOfStock;
            }

            public void setOutOfStock(boolean outOfStock) {
                this.outOfStock = outOfStock;
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

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public int getOfferCount() {
                return offerCount;
            }

            public void setOfferCount(int offerCount) {
                this.offerCount = offerCount;
            }

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

            public Integer getQty() {
                return qty;
            }

            public void setQty(Integer qty) {
                this.qty = qty;
            }

        }

    }


}

