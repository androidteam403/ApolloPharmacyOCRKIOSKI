package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class ItemSearchResponse implements Serializable {

    @SerializedName("ItemList")
    @Expose
    private List<Item> itemList = null;
    @SerializedName("RequestStatus")
    @Expose
    private Integer requestStatus;
    @SerializedName("ReturnMessage")
    @Expose
    private String returnMessage;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public static class Item implements Serializable {

        @SerializedName("ArtCode")
        @Expose
        private String artCode;
        @SerializedName("Category")
        @Expose
        private String category;
        @SerializedName("CategoryCode")
        @Expose
        private String categoryCode;
        @SerializedName("DPCO")
        @Expose
        private Boolean dpco;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("DiseaseType")
        @Expose
        private String diseaseType;
        @SerializedName("GenericName")
        @Expose
        private String genericName;
        @SerializedName("Hsncode_In")
        @Expose
        private String hsncodeIn;
        @SerializedName("IsGeneric")
        @Expose
        private Boolean isGeneric;
        @SerializedName("MRP")
        @Expose
        private String mrp;
        @SerializedName("Manufacture")
        @Expose
        private String manufacture;
        @SerializedName("ManufactureCode")
        @Expose
        private String manufactureCode;
        @SerializedName("MaxQtyAllowed")
        @Expose
        private Integer maxQtyAllowed;
        @SerializedName("ProductRecID")
        @Expose
        private String productRecID;
        @SerializedName("RackId")
        @Expose
        private String rackId;
        @SerializedName("RetailCategoryRecID")
        @Expose
        private String retailCategoryRecID;
        @SerializedName("RetailMainCategoryRecID")
        @Expose
        private String retailMainCategoryRecID;
        @SerializedName("RetailSubCategoryRecID")
        @Expose
        private String retailSubCategoryRecID;
        @SerializedName("SI_NO")
        @Expose
        private String siNo;
        @SerializedName("STOCKQTY")
        @Expose
        private Integer stockqty;
        @SerializedName("Sch_Catg")
        @Expose
        private String schCatg;
        @SerializedName("Sch_Catg_Code")
        @Expose
        private String schCatgCode;
        @SerializedName("SubCategory")
        @Expose
        private String subCategory;
        @SerializedName("MedicineType")
        @Expose
        private String medicineType;
        @SerializedName("SubClassification")
        @Expose
        private String subClassification;
        private Integer qty = 1;

        public String getArtCode() {
            return artCode;
        }

        public void setArtCode(String artCode) {
            this.artCode = artCode;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCategoryCode() {
            return categoryCode;
        }

        public void setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
        }

        public Boolean getDpco() {
            return dpco;
        }

        public void setDpco(Boolean dpco) {
            this.dpco = dpco;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDiseaseType() {
            return diseaseType;
        }

        public void setDiseaseType(String diseaseType) {
            this.diseaseType = diseaseType;
        }

        public String getGenericName() {
            return genericName;
        }

        public void setGenericName(String genericName) {
            this.genericName = genericName;
        }

        public String getHsncodeIn() {
            return hsncodeIn;
        }

        public void setHsncodeIn(String hsncodeIn) {
            this.hsncodeIn = hsncodeIn;
        }

        public Boolean getIsGeneric() {
            return isGeneric;
        }

        public void setIsGeneric(Boolean isGeneric) {
            this.isGeneric = isGeneric;
        }

        public String getMrp() {
            return mrp;
        }

        public void setMrp(String mrp) {
            this.mrp = mrp;
        }

        public String getManufacture() {
            return manufacture;
        }

        public void setManufacture(String manufacture) {
            this.manufacture = manufacture;
        }

        public String getManufactureCode() {
            return manufactureCode;
        }

        public void setManufactureCode(String manufactureCode) {
            this.manufactureCode = manufactureCode;
        }

        public Integer getMaxQtyAllowed() {
            return maxQtyAllowed;
        }

        public void setMaxQtyAllowed(Integer maxQtyAllowed) {
            this.maxQtyAllowed = maxQtyAllowed;
        }

        public String getProductRecID() {
            return productRecID;
        }

        public void setProductRecID(String productRecID) {
            this.productRecID = productRecID;
        }

        public String getRackId() {
            return rackId;
        }

        public void setRackId(String rackId) {
            this.rackId = rackId;
        }

        public String getRetailCategoryRecID() {
            return retailCategoryRecID;
        }

        public void setRetailCategoryRecID(String retailCategoryRecID) {
            this.retailCategoryRecID = retailCategoryRecID;
        }

        public String getRetailMainCategoryRecID() {
            return retailMainCategoryRecID;
        }

        public void setRetailMainCategoryRecID(String retailMainCategoryRecID) {
            this.retailMainCategoryRecID = retailMainCategoryRecID;
        }

        public String getRetailSubCategoryRecID() {
            return retailSubCategoryRecID;
        }

        public void setRetailSubCategoryRecID(String retailSubCategoryRecID) {
            this.retailSubCategoryRecID = retailSubCategoryRecID;
        }

        public String getSiNo() {
            return siNo;
        }

        public void setSiNo(String siNo) {
            this.siNo = siNo;
        }

        public Integer getStockqty() {
            return stockqty;
        }

        public void setStockqty(Integer stockqty) {
            this.stockqty = stockqty;
        }

        public String getSchCatg() {
            return schCatg;
        }

        public void setSchCatg(String schCatg) {
            this.schCatg = schCatg;
        }

        public String getSchCatgCode() {
            return schCatgCode;
        }

        public void setSchCatgCode(String schCatgCode) {
            this.schCatgCode = schCatgCode;
        }

        public String getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(String subCategory) {
            this.subCategory = subCategory;
        }

        public String getMedicineType() {
            return medicineType;
        }

        public void setMedicineType(String medicineType) {
            this.medicineType = medicineType;
        }

        public String getSubClassification() {
            return subClassification;
        }

        public void setSubClassification(String subClassification) {
            this.subClassification = subClassification;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }
    }
}
