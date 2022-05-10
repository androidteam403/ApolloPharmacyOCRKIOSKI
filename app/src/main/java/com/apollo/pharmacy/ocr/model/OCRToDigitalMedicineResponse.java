package com.apollo.pharmacy.ocr.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OCRToDigitalMedicineResponse implements Serializable {

    @Expose
    @SerializedName("artCode")
    private String artCode;
    @Expose
    @SerializedName("artName")
    private String artName;
    @Expose
    @SerializedName("container")
    private String container;
    @Expose
    @SerializedName("artprice")
    private String artprice;
    @Expose
    @SerializedName("qty")
    private int qty;
    @Expose
    @SerializedName("imageurl")
    private String imageurl;
    @Expose
    @SerializedName("mou")
    private String mou;
    private String medicineType;
    private boolean outOfStock;
    private String batchId;
    private String netAmountInclTax;
    private boolean expandStatus;

    private int labelAvgQty;

    private int duplicateCount;

    private float labelPrice;

    private String labelName;

    private float labelAveragePrice;

    private float labelMaxPrice;

    public float getLabelMaxPrice() {
        return labelMaxPrice;
    }

    public void setLabelMaxPrice(float labelMaxPrice) {
        this.labelMaxPrice = labelMaxPrice;
    }

    public float getLabelAveragePrice() {
        return labelAveragePrice;
    }

    public void setLabelAveragePrice(float labelAveragePrice) {
        this.labelAveragePrice = labelAveragePrice;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getLabelAvgQty() {
        return labelAvgQty;
    }

    public void setLabelAvgQty(int labelAvgQty) {
        this.labelAvgQty = labelAvgQty;
    }

    public int getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(int duplicateCount) {
        this.duplicateCount = duplicateCount;
    }

    public float getLabelPrice() {
        return labelPrice;
    }

    public void setLabelPrice(float labelPrice) {
        this.labelPrice = labelPrice;
    }

    public boolean isExpandStatus() {
        return expandStatus;
    }

    public void setExpandStatus(boolean expandStatus) {
        this.expandStatus = expandStatus;
    }

    public OCRToDigitalMedicineResponse() {

    }

    public OCRToDigitalMedicineResponse(Parcel source) {
        artCode = source.readString();
        artName = source.readString();
        container = source.readString();
        qty = source.readInt();
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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

    public String getArtprice() {
        return artprice;
    }

    public void setArtprice(String artprice) {
        this.artprice = artprice;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public String getArtCode() {
        return artCode;
    }

    public void setArtCode(String artCode) {
        this.artCode = artCode;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setImageurl(String url) {
        this.imageurl = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getMou() {
        return mou;
    }

    public void setMou(String mou) {
        this.mou = mou;
    }


    private CalculatePosTransactionRequest.SalesLine salesLine = null;


    public CalculatePosTransactionRequest.SalesLine getSalesLine() {
        return salesLine;
    }

    public void setSalesLine(CalculatePosTransactionRequest.SalesLine salesLine) {
        this.salesLine = salesLine;
    }

    public String getNetAmountInclTax() {
        return netAmountInclTax;
    }

    public void setNetAmountInclTax(String netAmountInclTax) {
        this.netAmountInclTax = netAmountInclTax;
    }

//    public static class SalesLine {
//
//        @SerializedName("Additionaltax")
//        @Expose
//        private Double additionaltax;
//        @SerializedName("ApplyDiscount")
//        @Expose
//        private Boolean applyDiscount;
//        @SerializedName("Barcode")
//        @Expose
//        private String barcode;
//        @SerializedName("BaseAmount")
//        @Expose
//        private Double baseAmount;
//        @SerializedName("CESSPerc")
//        @Expose
//        private Double cESSPerc;
//        @SerializedName("CESSTaxCode")
//        @Expose
//        private String cESSTaxCode;
//        @SerializedName("CGSTPerc")
//        @Expose
//        private Double cGSTPerc;
//        @SerializedName("CGSTTaxCode")
//        @Expose
//        private String cGSTTaxCode;
//        @SerializedName("Category")
//        @Expose
//        private String category;
//        @SerializedName("CategoryCode")
//        @Expose
//        private String categoryCode;
//        @SerializedName("CategoryReference")
//        @Expose
//        private String categoryReference;
//        @SerializedName("Comment")
//        @Expose
//        private String comment;
//        @SerializedName("DPCO")
//        @Expose
//        private Boolean dpco;
//        @SerializedName("DiscAmount")
//        @Expose
//        private Double discAmount;
//        @SerializedName("DiscId")
//        @Expose
//        private String discId;
//        @SerializedName("DiscOfferId")
//        @Expose
//        private String discOfferId;
//        @SerializedName("DiscountStructureType")
//        @Expose
//        private Double discountStructureType;
//        @SerializedName("DiscountType")
//        @Expose
//        private String discountType;
//        @SerializedName("DiseaseType")
//        @Expose
//        private String diseaseType;
//        @SerializedName("Expiry")
//        @Expose
//        private String expiry;
//        @SerializedName("Hsncode_In")
//        @Expose
//        private String hsncodeIn;
//        @SerializedName("IGSTPerc")
//        @Expose
//        private Double iGSTPerc;
//        @SerializedName("IGSTTaxCode")
//        @Expose
//        private String iGSTTaxCode;
//        @SerializedName("ISPrescribed")
//        @Expose
//        private Double iSPrescribed;
//        @SerializedName("ISReserved")
//        @Expose
//        private Boolean iSReserved;
//        @SerializedName("ISStockAvailable")
//        @Expose
//        private Boolean iSStockAvailable;
//        @SerializedName("InventBatchId")
//        @Expose
//        private String inventBatchId;
//        @SerializedName("IsChecked")
//        @Expose
//        private Boolean isChecked;
//        @SerializedName("IsGeneric")
//        @Expose
//        private Boolean isGeneric;
//        @SerializedName("IsPriceOverride")
//        @Expose
//        private Boolean isPriceOverride;
//        @SerializedName("IsSubsitute")
//        @Expose
//        private Boolean isSubsitute;
//        @SerializedName("IsVoid")
//        @Expose
//        private Boolean isVoid;
//        @SerializedName("ItemId")
//        @Expose
//        private String itemId;
//        @SerializedName("ItemName")
//        @Expose
//        private String itemName;
//        @SerializedName("LineDiscPercentage")
//        @Expose
//        private Double lineDiscPercentage;
//        @SerializedName("LineManualDiscountAmount")
//        @Expose
//        private Double lineManualDiscountAmount;
//        @SerializedName("LineManualDiscountPercentage")
//        @Expose
//        private Double lineManualDiscountPercentage;
//        @SerializedName("LineNo")
//        @Expose
//        private Integer lineNo;
//        @SerializedName("LinedscAmount")
//        @Expose
//        private Double linedscAmount;
//        @SerializedName("MMGroupId")
//        @Expose
//        private String mMGroupId;
//        @SerializedName("MRP")
//        @Expose
//        private Double mrp;
//        @SerializedName("ManufacturerCode")
//        @Expose
//        private String manufacturerCode;
//        @SerializedName("ManufacturerName")
//        @Expose
//        private String manufacturerName;
//        @SerializedName("MixMode")
//        @Expose
//        private Boolean mixMode;
//        @SerializedName("ModifyBatchId")
//        @Expose
//        private String modifyBatchId;
//        @SerializedName("NetAmount")
//        @Expose
//        private Double netAmount;
//        @SerializedName("NetAmountInclTax")
//        @Expose
//        private Double netAmountInclTax;
//        @SerializedName("OfferAmount")
//        @Expose
//        private Double offerAmount;
//        @SerializedName("OfferDiscountType")
//        @Expose
//        private Double offerDiscountType;
//        @SerializedName("OfferDiscountValue")
//        @Expose
//        private Double offerDiscountValue;
//        @SerializedName("OfferQty")
//        @Expose
//        private Double offerQty;
//        @SerializedName("OfferType")
//        @Expose
//        private Double offerType;
//        @SerializedName("OmsLineID")
//        @Expose
//        private Double omsLineID;
//        @SerializedName("OmsLineRECID")
//        @Expose
//        private Double omsLineRECID;
//        @SerializedName("OrderStatus")
//        @Expose
//        private Double orderStatus;
//        @SerializedName("OriginalPrice")
//        @Expose
//        private Double originalPrice;
//        @SerializedName("PeriodicDiscAmount")
//        @Expose
//        private Double periodicDiscAmount;
//        @SerializedName("PhysicalMRP")
//        @Expose
//        private Double physicalMRP;
//        @SerializedName("PreviewText")
//        @Expose
//        private String previewText;
//        @SerializedName("Price")
//        @Expose
//        private Double price;
//        @SerializedName("ProductRecID")
//        @Expose
//        private String productRecID;
//        @SerializedName("Qty")
//        @Expose
//        private Integer qty;
//        @SerializedName("RemainderDays")
//        @Expose
//        private Double remainderDays;
//        @SerializedName("RemainingQty")
//        @Expose
//        private Double remainingQty;
//        @SerializedName("Resqtyflag")
//        @Expose
//        private Boolean resqtyflag;
//        @SerializedName("RetailCategoryRecID")
//        @Expose
//        private String retailCategoryRecID;
//        @SerializedName("RetailMainCategoryRecID")
//        @Expose
//        private String retailMainCategoryRecID;
//        @SerializedName("RetailSubCategoryRecID")
//        @Expose
//        private String retailSubCategoryRecID;
//        @SerializedName("ReturnQty")
//        @Expose
//        private Double returnQty;
//        @SerializedName("SGSTPerc")
//        @Expose
//        private Double sGSTPerc;
//        @SerializedName("SGSTTaxCode")
//        @Expose
//        private String sGSTTaxCode;
//        @SerializedName("ScheduleCategory")
//        @Expose
//        private String scheduleCategory;
//        @SerializedName("ScheduleCategoryCode")
//        @Expose
//        private String scheduleCategoryCode;
//        @SerializedName("StockQty")
//        @Expose
//        private Double stockQty;
//        @SerializedName("SubCategory")
//        @Expose
//        private String subCategory;
//        @SerializedName("SubCategoryCode")
//        @Expose
//        private String subCategoryCode;
//        @SerializedName("SubClassification")
//        @Expose
//        private String subClassification;
//        @SerializedName("SubstitudeItemId")
//        @Expose
//        private String substitudeItemId;
//        @SerializedName("Tax")
//        @Expose
//        private Double tax;
//        @SerializedName("TaxAmount")
//        @Expose
//        private Double taxAmount;
//        @SerializedName("Total")
//        @Expose
//        private Double total;
//        @SerializedName("TotalDiscAmount")
//        @Expose
//        private Double totalDiscAmount;
//        @SerializedName("TotalDiscPct")
//        @Expose
//        private Double totalDiscPct;
//        @SerializedName("TotalRoundedAmount")
//        @Expose
//        private Double totalRoundedAmount;
//        @SerializedName("TotalTax")
//        @Expose
//        private Double totalTax;
//        @SerializedName("Unit")
//        @Expose
//        private String unit;
//        @SerializedName("UnitPrice")
//        @Expose
//        private Double unitPrice;
//        @SerializedName("UnitQty")
//        @Expose
//        private Double unitQty;
//        @SerializedName("VariantId")
//        @Expose
//        private String variantId;
//        @SerializedName("isReturnClick")
//        @Expose
//        private Boolean isReturnClick;
//        @SerializedName("isSelectedReturnItem")
//        @Expose
//        private Boolean isSelectedReturnItem;
//
//        private String batchNo;
//
//        public String getBatchNo() {
//            return batchNo;
//        }
//
//        public void setBatchNo(String batchNo) {
//            this.batchNo = batchNo;
//        }
//
//        public Double getAdditionaltax() {
//            return additionaltax;
//        }
//
//        public void setAdditionaltax(Double additionaltax) {
//            this.additionaltax = additionaltax;
//        }
//
//        public Boolean getApplyDiscount() {
//            return applyDiscount;
//        }
//
//        public void setApplyDiscount(Boolean applyDiscount) {
//            this.applyDiscount = applyDiscount;
//        }
//
//        public String getBarcode() {
//            return barcode;
//        }
//
//        public void setBarcode(String barcode) {
//            this.barcode = barcode;
//        }
//
//        public Double getBaseAmount() {
//            return baseAmount;
//        }
//
//        public void setBaseAmount(Double baseAmount) {
//            this.baseAmount = baseAmount;
//        }
//
//        public Double getCESSPerc() {
//            return cESSPerc;
//        }
//
//        public void setCESSPerc(Double cESSPerc) {
//            this.cESSPerc = cESSPerc;
//        }
//
//        public String getCESSTaxCode() {
//            return cESSTaxCode;
//        }
//
//        public void setCESSTaxCode(String cESSTaxCode) {
//            this.cESSTaxCode = cESSTaxCode;
//        }
//
//        public Double getCGSTPerc() {
//            return cGSTPerc;
//        }
//
//        public void setCGSTPerc(Double cGSTPerc) {
//            this.cGSTPerc = cGSTPerc;
//        }
//
//        public String getCGSTTaxCode() {
//            return cGSTTaxCode;
//        }
//
//        public void setCGSTTaxCode(String cGSTTaxCode) {
//            this.cGSTTaxCode = cGSTTaxCode;
//        }
//
//        public String getCategory() {
//            return category;
//        }
//
//        public void setCategory(String category) {
//            this.category = category;
//        }
//
//        public String getCategoryCode() {
//            return categoryCode;
//        }
//
//        public void setCategoryCode(String categoryCode) {
//            this.categoryCode = categoryCode;
//        }
//
//        public String getCategoryReference() {
//            return categoryReference;
//        }
//
//        public void setCategoryReference(String categoryReference) {
//            this.categoryReference = categoryReference;
//        }
//
//        public String getComment() {
//            return comment;
//        }
//
//        public void setComment(String comment) {
//            this.comment = comment;
//        }
//
//        public Boolean getDpco() {
//            return dpco;
//        }
//
//        public void setDpco(Boolean dpco) {
//            this.dpco = dpco;
//        }
//
//        public Double getDiscAmount() {
//            return discAmount;
//        }
//
//        public void setDiscAmount(Double discAmount) {
//            this.discAmount = discAmount;
//        }
//
//        public String getDiscId() {
//            return discId;
//        }
//
//        public void setDiscId(String discId) {
//            this.discId = discId;
//        }
//
//        public String getDiscOfferId() {
//            return discOfferId;
//        }
//
//        public void setDiscOfferId(String discOfferId) {
//            this.discOfferId = discOfferId;
//        }
//
//        public Double getDiscountStructureType() {
//            return discountStructureType;
//        }
//
//        public void setDiscountStructureType(Double discountStructureType) {
//            this.discountStructureType = discountStructureType;
//        }
//
//        public String getDiscountType() {
//            return discountType;
//        }
//
//        public void setDiscountType(String discountType) {
//            this.discountType = discountType;
//        }
//
//        public String getDiseaseType() {
//            return diseaseType;
//        }
//
//        public void setDiseaseType(String diseaseType) {
//            this.diseaseType = diseaseType;
//        }
//
//        public String getExpiry() {
//            return expiry;
//        }
//
//        public void setExpiry(String expiry) {
//            this.expiry = expiry;
//        }
//
//        public String getHsncodeIn() {
//            return hsncodeIn;
//        }
//
//        public void setHsncodeIn(String hsncodeIn) {
//            this.hsncodeIn = hsncodeIn;
//        }
//
//        public Double getIGSTPerc() {
//            return iGSTPerc;
//        }
//
//        public void setIGSTPerc(Double iGSTPerc) {
//            this.iGSTPerc = iGSTPerc;
//        }
//
//        public String getIGSTTaxCode() {
//            return iGSTTaxCode;
//        }
//
//        public void setIGSTTaxCode(String iGSTTaxCode) {
//            this.iGSTTaxCode = iGSTTaxCode;
//        }
//
//        public Double getISPrescribed() {
//            return iSPrescribed;
//        }
//
//        public void setISPrescribed(Double iSPrescribed) {
//            this.iSPrescribed = iSPrescribed;
//        }
//
//        public Boolean getISReserved() {
//            return iSReserved;
//        }
//
//        public void setISReserved(Boolean iSReserved) {
//            this.iSReserved = iSReserved;
//        }
//
//        public Boolean getISStockAvailable() {
//            return iSStockAvailable;
//        }
//
//        public void setISStockAvailable(Boolean iSStockAvailable) {
//            this.iSStockAvailable = iSStockAvailable;
//        }
//
//        public String getInventBatchId() {
//            return inventBatchId;
//        }
//
//        public void setInventBatchId(String inventBatchId) {
//            this.inventBatchId = inventBatchId;
//        }
//
//        public Boolean getIsChecked() {
//            return isChecked;
//        }
//
//        public void setIsChecked(Boolean isChecked) {
//            this.isChecked = isChecked;
//        }
//
//        public Boolean getIsGeneric() {
//            return isGeneric;
//        }
//
//        public void setIsGeneric(Boolean isGeneric) {
//            this.isGeneric = isGeneric;
//        }
//
//        public Boolean getIsPriceOverride() {
//            return isPriceOverride;
//        }
//
//        public void setIsPriceOverride(Boolean isPriceOverride) {
//            this.isPriceOverride = isPriceOverride;
//        }
//
//        public Boolean getIsSubsitute() {
//            return isSubsitute;
//        }
//
//        public void setIsSubsitute(Boolean isSubsitute) {
//            this.isSubsitute = isSubsitute;
//        }
//
//        public Boolean getIsVoid() {
//            return isVoid;
//        }
//
//        public void setIsVoid(Boolean isVoid) {
//            this.isVoid = isVoid;
//        }
//
//        public String getItemId() {
//            return itemId;
//        }
//
//        public void setItemId(String itemId) {
//            this.itemId = itemId;
//        }
//
//        public String getItemName() {
//            return itemName;
//        }
//
//        public void setItemName(String itemName) {
//            this.itemName = itemName;
//        }
//
//        public Double getLineDiscPercentage() {
//            return lineDiscPercentage;
//        }
//
//        public void setLineDiscPercentage(Double lineDiscPercentage) {
//            this.lineDiscPercentage = lineDiscPercentage;
//        }
//
//        public Double getLineManualDiscountAmount() {
//            return lineManualDiscountAmount;
//        }
//
//        public void setLineManualDiscountAmount(Double lineManualDiscountAmount) {
//            this.lineManualDiscountAmount = lineManualDiscountAmount;
//        }
//
//        public Double getLineManualDiscountPercentage() {
//            return lineManualDiscountPercentage;
//        }
//
//        public void setLineManualDiscountPercentage(Double lineManualDiscountPercentage) {
//            this.lineManualDiscountPercentage = lineManualDiscountPercentage;
//        }
//
//        public Integer getLineNo() {
//            return lineNo;
//        }
//
//        public void setLineNo(Integer lineNo) {
//            this.lineNo = lineNo;
//        }
//
//        public Double getLinedscAmount() {
//            return linedscAmount;
//        }
//
//        public void setLinedscAmount(Double linedscAmount) {
//            this.linedscAmount = linedscAmount;
//        }
//
//        public String getMMGroupId() {
//            return mMGroupId;
//        }
//
//        public void setMMGroupId(String mMGroupId) {
//            this.mMGroupId = mMGroupId;
//        }
//
//        public Double getMrp() {
//            return mrp;
//        }
//
//        public void setMrp(Double mrp) {
//            this.mrp = mrp;
//        }
//
//        public String getManufacturerCode() {
//            return manufacturerCode;
//        }
//
//        public void setManufacturerCode(String manufacturerCode) {
//            this.manufacturerCode = manufacturerCode;
//        }
//
//        public String getManufacturerName() {
//            return manufacturerName;
//        }
//
//        public void setManufacturerName(String manufacturerName) {
//            this.manufacturerName = manufacturerName;
//        }
//
//        public Boolean getMixMode() {
//            return mixMode;
//        }
//
//        public void setMixMode(Boolean mixMode) {
//            this.mixMode = mixMode;
//        }
//
//        public String getModifyBatchId() {
//            return modifyBatchId;
//        }
//
//        public void setModifyBatchId(String modifyBatchId) {
//            this.modifyBatchId = modifyBatchId;
//        }
//
//        public Double getNetAmount() {
//            return netAmount;
//        }
//
//        public void setNetAmount(Double netAmount) {
//            this.netAmount = netAmount;
//        }
//
//        public Double getNetAmountInclTax() {
//            return netAmountInclTax;
//        }
//
//        public void setNetAmountInclTax(Double netAmountInclTax) {
//            this.netAmountInclTax = netAmountInclTax;
//        }
//
//        public Double getOfferAmount() {
//            return offerAmount;
//        }
//
//        public void setOfferAmount(Double offerAmount) {
//            this.offerAmount = offerAmount;
//        }
//
//        public Double getOfferDiscountType() {
//            return offerDiscountType;
//        }
//
//        public void setOfferDiscountType(Double offerDiscountType) {
//            this.offerDiscountType = offerDiscountType;
//        }
//
//        public Double getOfferDiscountValue() {
//            return offerDiscountValue;
//        }
//
//        public void setOfferDiscountValue(Double offerDiscountValue) {
//            this.offerDiscountValue = offerDiscountValue;
//        }
//
//        public Double getOfferQty() {
//            return offerQty;
//        }
//
//        public void setOfferQty(Double offerQty) {
//            this.offerQty = offerQty;
//        }
//
//        public Double getOfferType() {
//            return offerType;
//        }
//
//        public void setOfferType(Double offerType) {
//            this.offerType = offerType;
//        }
//
//        public Double getOmsLineID() {
//            return omsLineID;
//        }
//
//        public void setOmsLineID(Double omsLineID) {
//            this.omsLineID = omsLineID;
//        }
//
//        public Double getOmsLineRECID() {
//            return omsLineRECID;
//        }
//
//        public void setOmsLineRECID(Double omsLineRECID) {
//            this.omsLineRECID = omsLineRECID;
//        }
//
//        public Double getOrderStatus() {
//            return orderStatus;
//        }
//
//        public void setOrderStatus(Double orderStatus) {
//            this.orderStatus = orderStatus;
//        }
//
//        public Double getOriginalPrice() {
//            return originalPrice;
//        }
//
//        public void setOriginalPrice(Double originalPrice) {
//            this.originalPrice = originalPrice;
//        }
//
//        public Double getPeriodicDiscAmount() {
//            return periodicDiscAmount;
//        }
//
//        public void setPeriodicDiscAmount(Double periodicDiscAmount) {
//            this.periodicDiscAmount = periodicDiscAmount;
//        }
//
//        public Double getPhysicalMRP() {
//            return physicalMRP;
//        }
//
//        public void setPhysicalMRP(Double physicalMRP) {
//            this.physicalMRP = physicalMRP;
//        }
//
//        public String getPreviewText() {
//            return previewText;
//        }
//
//        public void setPreviewText(String previewText) {
//            this.previewText = previewText;
//        }
//
//        public Double getPrice() {
//            return price;
//        }
//
//        public void setPrice(Double price) {
//            this.price = price;
//        }
//
//        public String getProductRecID() {
//            return productRecID;
//        }
//
//        public void setProductRecID(String productRecID) {
//            this.productRecID = productRecID;
//        }
//
//        public Integer getQty() {
//            return qty;
//        }
//
//        public void setQty(Integer qty) {
//            this.qty = qty;
//        }
//
//        public Double getRemainderDays() {
//            return remainderDays;
//        }
//
//        public void setRemainderDays(Double remainderDays) {
//            this.remainderDays = remainderDays;
//        }
//
//        public Double getRemainingQty() {
//            return remainingQty;
//        }
//
//        public void setRemainingQty(Double remainingQty) {
//            this.remainingQty = remainingQty;
//        }
//
//        public Boolean getResqtyflag() {
//            return resqtyflag;
//        }
//
//        public void setResqtyflag(Boolean resqtyflag) {
//            this.resqtyflag = resqtyflag;
//        }
//
//        public String getRetailCategoryRecID() {
//            return retailCategoryRecID;
//        }
//
//        public void setRetailCategoryRecID(String retailCategoryRecID) {
//            this.retailCategoryRecID = retailCategoryRecID;
//        }
//
//        public String getRetailMainCategoryRecID() {
//            return retailMainCategoryRecID;
//        }
//
//        public void setRetailMainCategoryRecID(String retailMainCategoryRecID) {
//            this.retailMainCategoryRecID = retailMainCategoryRecID;
//        }
//
//        public String getRetailSubCategoryRecID() {
//            return retailSubCategoryRecID;
//        }
//
//        public void setRetailSubCategoryRecID(String retailSubCategoryRecID) {
//            this.retailSubCategoryRecID = retailSubCategoryRecID;
//        }
//
//        public Double getReturnQty() {
//            return returnQty;
//        }
//
//        public void setReturnQty(Double returnQty) {
//            this.returnQty = returnQty;
//        }
//
//        public Double getSGSTPerc() {
//            return sGSTPerc;
//        }
//
//        public void setSGSTPerc(Double sGSTPerc) {
//            this.sGSTPerc = sGSTPerc;
//        }
//
//        public String getSGSTTaxCode() {
//            return sGSTTaxCode;
//        }
//
//        public void setSGSTTaxCode(String sGSTTaxCode) {
//            this.sGSTTaxCode = sGSTTaxCode;
//        }
//
//        public String getScheduleCategory() {
//            return scheduleCategory;
//        }
//
//        public void setScheduleCategory(String scheduleCategory) {
//            this.scheduleCategory = scheduleCategory;
//        }
//
//        public String getScheduleCategoryCode() {
//            return scheduleCategoryCode;
//        }
//
//        public void setScheduleCategoryCode(String scheduleCategoryCode) {
//            this.scheduleCategoryCode = scheduleCategoryCode;
//        }
//
//        public Double getStockQty() {
//            return stockQty;
//        }
//
//        public void setStockQty(Double stockQty) {
//            this.stockQty = stockQty;
//        }
//
//        public String getSubCategory() {
//            return subCategory;
//        }
//
//        public void setSubCategory(String subCategory) {
//            this.subCategory = subCategory;
//        }
//
//        public String getSubCategoryCode() {
//            return subCategoryCode;
//        }
//
//        public void setSubCategoryCode(String subCategoryCode) {
//            this.subCategoryCode = subCategoryCode;
//        }
//
//        public String getSubClassification() {
//            return subClassification;
//        }
//
//        public void setSubClassification(String subClassification) {
//            this.subClassification = subClassification;
//        }
//
//        public String getSubstitudeItemId() {
//            return substitudeItemId;
//        }
//
//        public void setSubstitudeItemId(String substitudeItemId) {
//            this.substitudeItemId = substitudeItemId;
//        }
//
//        public Double getTax() {
//            return tax;
//        }
//
//        public void setTax(Double tax) {
//            this.tax = tax;
//        }
//
//        public Double getTaxAmount() {
//            return taxAmount;
//        }
//
//        public void setTaxAmount(Double taxAmount) {
//            this.taxAmount = taxAmount;
//        }
//
//        public Double getTotal() {
//            return total;
//        }
//
//        public void setTotal(Double total) {
//            this.total = total;
//        }
//
//        public Double getTotalDiscAmount() {
//            return totalDiscAmount;
//        }
//
//        public void setTotalDiscAmount(Double totalDiscAmount) {
//            this.totalDiscAmount = totalDiscAmount;
//        }
//
//        public Double getTotalDiscPct() {
//            return totalDiscPct;
//        }
//
//        public void setTotalDiscPct(Double totalDiscPct) {
//            this.totalDiscPct = totalDiscPct;
//        }
//
//        public Double getTotalRoundedAmount() {
//            return totalRoundedAmount;
//        }
//
//        public void setTotalRoundedAmount(Double totalRoundedAmount) {
//            this.totalRoundedAmount = totalRoundedAmount;
//        }
//
//        public Double getTotalTax() {
//            return totalTax;
//        }
//
//        public void setTotalTax(Double totalTax) {
//            this.totalTax = totalTax;
//        }
//
//        public String getUnit() {
//            return unit;
//        }
//
//        public void setUnit(String unit) {
//            this.unit = unit;
//        }
//
//        public Double getUnitPrice() {
//            return unitPrice;
//        }
//
//        public void setUnitPrice(Double unitPrice) {
//            this.unitPrice = unitPrice;
//        }
//
//        public Double getUnitQty() {
//            return unitQty;
//        }
//
//        public void setUnitQty(Double unitQty) {
//            this.unitQty = unitQty;
//        }
//
//        public String getVariantId() {
//            return variantId;
//        }
//
//        public void setVariantId(String variantId) {
//            this.variantId = variantId;
//        }
//
//        public Boolean getIsReturnClick() {
//            return isReturnClick;
//        }
//
//        public void setIsReturnClick(Boolean isReturnClick) {
//            this.isReturnClick = isReturnClick;
//        }
//
//        public Boolean getIsSelectedReturnItem() {
//            return isSelectedReturnItem;
//        }
//
//        public void setIsSelectedReturnItem(Boolean isSelectedReturnItem) {
//            this.isSelectedReturnItem = isSelectedReturnItem;
//        }
//
//    }
}

