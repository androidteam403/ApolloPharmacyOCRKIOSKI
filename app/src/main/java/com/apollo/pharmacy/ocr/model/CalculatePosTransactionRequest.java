package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalculatePosTransactionRequest {

    @SerializedName("AmounttoAccount")
    @Expose
    private Integer amounttoAccount;
    @SerializedName("BatchTerminalid")
    @Expose
    private String batchTerminalid;
    @SerializedName("BillingCity")
    @Expose
    private String billingCity;
    @SerializedName("BusinessDate")
    @Expose
    private String businessDate;
    @SerializedName("CancelReasonCode")
    @Expose
    private String cancelReasonCode;
    @SerializedName("Channel")
    @Expose
    private String channel;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("CorpCode")
    @Expose
    private String corpCode;
    @SerializedName("CouponCode")
    @Expose
    private String couponCode;
    @SerializedName("CreatedonPosTerminal")
    @Expose
    private String createdonPosTerminal;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("CurrentSalesLine")
    @Expose
    private Integer currentSalesLine;
    @SerializedName("CustAccount")
    @Expose
    private String custAccount;
    @SerializedName("CustAddress")
    @Expose
    private String custAddress;
    @SerializedName("CustDiscamount")
    @Expose
    private Integer custDiscamount;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerState")
    @Expose
    private String customerState;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("DataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("DiscAmount")
    @Expose
    private Integer discAmount;
    @SerializedName("DoctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("DoctorName")
    @Expose
    private String doctorName;
    @SerializedName("EntryStatus")
    @Expose
    private Integer entryStatus;
    @SerializedName("Gender")
    @Expose
    private Integer gender;
    @SerializedName("GrossAmount")
    @Expose
    private Double grossAmount;
    @SerializedName("IPNO")
    @Expose
    private String ipno;
    @SerializedName("IPSerialNO")
    @Expose
    private String iPSerialNO;
    @SerializedName("ISAdvancePayment")
    @Expose
    private Boolean iSAdvancePayment;
    @SerializedName("ISBatchModifiedAllowed")
    @Expose
    private Boolean iSBatchModifiedAllowed;
    @SerializedName("ISOMSOrder")
    @Expose
    private Boolean iSOMSOrder;
    @SerializedName("ISPosted")
    @Expose
    private Boolean iSPosted;
    @SerializedName("ISPrescibeDiscount")
    @Expose
    private Boolean iSPrescibeDiscount;
    @SerializedName("ISReserved")
    @Expose
    private Boolean iSReserved;
    @SerializedName("ISReturnAllowed")
    @Expose
    private Boolean iSReturnAllowed;
    @SerializedName("IsManualBill")
    @Expose
    private Boolean isManualBill;
    @SerializedName("IsReturn")
    @Expose
    private Boolean isReturn;
    @SerializedName("IsStockCheck")
    @Expose
    private Boolean isStockCheck;
    @SerializedName("IsVoid")
    @Expose
    private Boolean isVoid;
    @SerializedName("MobileNO")
    @Expose
    private String mobileNO;
    @SerializedName("NetAmount")
    @Expose
    private Double netAmount;
    @SerializedName("NetAmountInclTax")
    @Expose
    private Double netAmountInclTax;
    @SerializedName("NumberofItemLines")
    @Expose
    private Integer numberofItemLines;
    @SerializedName("NumberofItems")
    @Expose
    private Integer numberofItems;
    @SerializedName("OrderSource")
    @Expose
    private String orderSource;
    @SerializedName("OrderType")
    @Expose
    private String orderType;
    @SerializedName("PaymentSource")
    @Expose
    private String paymentSource;
    @SerializedName("Pincode")
    @Expose
    private String pincode;
    @SerializedName("PosEvent")
    @Expose
    private Integer posEvent;
    @SerializedName("REFNO")
    @Expose
    private String refno;
    @SerializedName("ReciptId")
    @Expose
    private String reciptId;
    @SerializedName("RegionCode")
    @Expose
    private String regionCode;
    @SerializedName("Remainingamount")
    @Expose
    private Double remainingamount;
    @SerializedName("ReminderDays")
    @Expose
    private Integer reminderDays;
    @SerializedName("RequestStatus")
    @Expose
    private Integer requestStatus;
    @SerializedName("ReturnMessage")
    @Expose
    private String returnMessage;
    @SerializedName("ReturnReceiptId")
    @Expose
    private String returnReceiptId;
    @SerializedName("ReturnStore")
    @Expose
    private String returnStore;
    @SerializedName("ReturnTerminal")
    @Expose
    private String returnTerminal;
    @SerializedName("ReturnTransactionId")
    @Expose
    private String returnTransactionId;
    @SerializedName("ReturnType")
    @Expose
    private Integer returnType;
    @SerializedName("RoundedAmount")
    @Expose
    private Integer roundedAmount;
    @SerializedName("SEZ")
    @Expose
    private Integer sez;
    @SerializedName("SalesLine")
    @Expose
    private List<SalesLine> salesLine = null;
    @SerializedName("SalesOrigin")
    @Expose
    private String salesOrigin;
    @SerializedName("ShippingMethod")
    @Expose
    private String shippingMethod;
    @SerializedName("Staff")
    @Expose
    private String staff;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Store")
    @Expose
    private String store;
    @SerializedName("StoreName")
    @Expose
    private String storeName;
    @SerializedName("TenderLine")
    @Expose
    private List<Object> tenderLine = null;
    @SerializedName("Terminal")
    @Expose
    private String terminal;
    @SerializedName("TimewhenTransClosed")
    @Expose
    private Integer timewhenTransClosed;
    @SerializedName("TotalDiscAmount")
    @Expose
    private Integer totalDiscAmount;
    @SerializedName("TotalMRP")
    @Expose
    private Double totalMRP;
    @SerializedName("TotalManualDiscountAmount")
    @Expose
    private Integer totalManualDiscountAmount;
    @SerializedName("TotalManualDiscountPercentage")
    @Expose
    private Integer totalManualDiscountPercentage;
    @SerializedName("TotalTaxAmount")
    @Expose
    private Double totalTaxAmount;
    @SerializedName("TrackingRef")
    @Expose
    private String trackingRef;
    @SerializedName("TransDate")
    @Expose
    private String transDate;
    @SerializedName("TransType")
    @Expose
    private Integer transType;
    @SerializedName("TransactionId")
    @Expose
    private String transactionId;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("VendorId")
    @Expose
    private String vendorId;
    @SerializedName("OMSCreditAmount")
    @Expose
    private Double oMSCreditAmount;
    @SerializedName("ShippingCharges")
    @Expose
    private Double shippingCharges;

    public Integer getAmounttoAccount() {
        return amounttoAccount;
    }

    public void setAmounttoAccount(Integer amounttoAccount) {
        this.amounttoAccount = amounttoAccount;
    }

    public String getBatchTerminalid() {
        return batchTerminalid;
    }

    public void setBatchTerminalid(String batchTerminalid) {
        this.batchTerminalid = batchTerminalid;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getCancelReasonCode() {
        return cancelReasonCode;
    }

    public void setCancelReasonCode(String cancelReasonCode) {
        this.cancelReasonCode = cancelReasonCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCreatedonPosTerminal() {
        return createdonPosTerminal;
    }

    public void setCreatedonPosTerminal(String createdonPosTerminal) {
        this.createdonPosTerminal = createdonPosTerminal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getCurrentSalesLine() {
        return currentSalesLine;
    }

    public void setCurrentSalesLine(Integer currentSalesLine) {
        this.currentSalesLine = currentSalesLine;
    }

    public String getCustAccount() {
        return custAccount;
    }

    public void setCustAccount(String custAccount) {
        this.custAccount = custAccount;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public Integer getCustDiscamount() {
        return custDiscamount;
    }

    public void setCustDiscamount(Integer custDiscamount) {
        this.custDiscamount = custDiscamount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public void setDataAreaId(String dataAreaId) {
        this.dataAreaId = dataAreaId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getDiscAmount() {
        return discAmount;
    }

    public void setDiscAmount(Integer discAmount) {
        this.discAmount = discAmount;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Integer entryStatus) {
        this.entryStatus = entryStatus;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getIpno() {
        return ipno;
    }

    public void setIpno(String ipno) {
        this.ipno = ipno;
    }

    public String getIPSerialNO() {
        return iPSerialNO;
    }

    public void setIPSerialNO(String iPSerialNO) {
        this.iPSerialNO = iPSerialNO;
    }

    public Boolean getISAdvancePayment() {
        return iSAdvancePayment;
    }

    public void setISAdvancePayment(Boolean iSAdvancePayment) {
        this.iSAdvancePayment = iSAdvancePayment;
    }

    public Boolean getISBatchModifiedAllowed() {
        return iSBatchModifiedAllowed;
    }

    public void setISBatchModifiedAllowed(Boolean iSBatchModifiedAllowed) {
        this.iSBatchModifiedAllowed = iSBatchModifiedAllowed;
    }

    public Boolean getISOMSOrder() {
        return iSOMSOrder;
    }

    public void setISOMSOrder(Boolean iSOMSOrder) {
        this.iSOMSOrder = iSOMSOrder;
    }

    public Boolean getISPosted() {
        return iSPosted;
    }

    public void setISPosted(Boolean iSPosted) {
        this.iSPosted = iSPosted;
    }

    public Boolean getISPrescibeDiscount() {
        return iSPrescibeDiscount;
    }

    public void setISPrescibeDiscount(Boolean iSPrescibeDiscount) {
        this.iSPrescibeDiscount = iSPrescibeDiscount;
    }

    public Boolean getISReserved() {
        return iSReserved;
    }

    public void setISReserved(Boolean iSReserved) {
        this.iSReserved = iSReserved;
    }

    public Boolean getISReturnAllowed() {
        return iSReturnAllowed;
    }

    public void setISReturnAllowed(Boolean iSReturnAllowed) {
        this.iSReturnAllowed = iSReturnAllowed;
    }

    public Boolean getIsManualBill() {
        return isManualBill;
    }

    public void setIsManualBill(Boolean isManualBill) {
        this.isManualBill = isManualBill;
    }

    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public Boolean getIsStockCheck() {
        return isStockCheck;
    }

    public void setIsStockCheck(Boolean isStockCheck) {
        this.isStockCheck = isStockCheck;
    }

    public Boolean getIsVoid() {
        return isVoid;
    }

    public void setIsVoid(Boolean isVoid) {
        this.isVoid = isVoid;
    }

    public String getMobileNO() {
        return mobileNO;
    }

    public void setMobileNO(String mobileNO) {
        this.mobileNO = mobileNO;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getNetAmountInclTax() {
        return netAmountInclTax;
    }

    public void setNetAmountInclTax(Double netAmountInclTax) {
        this.netAmountInclTax = netAmountInclTax;
    }

    public Integer getNumberofItemLines() {
        return numberofItemLines;
    }

    public void setNumberofItemLines(Integer numberofItemLines) {
        this.numberofItemLines = numberofItemLines;
    }

    public Integer getNumberofItems() {
        return numberofItems;
    }

    public void setNumberofItems(Integer numberofItems) {
        this.numberofItems = numberofItems;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPaymentSource() {
        return paymentSource;
    }

    public void setPaymentSource(String paymentSource) {
        this.paymentSource = paymentSource;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getPosEvent() {
        return posEvent;
    }

    public void setPosEvent(Integer posEvent) {
        this.posEvent = posEvent;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getReciptId() {
        return reciptId;
    }

    public void setReciptId(String reciptId) {
        this.reciptId = reciptId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Double getRemainingamount() {
        return remainingamount;
    }

    public void setRemainingamount(Double remainingamount) {
        this.remainingamount = remainingamount;
    }

    public Integer getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(Integer reminderDays) {
        this.reminderDays = reminderDays;
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

    public String getReturnReceiptId() {
        return returnReceiptId;
    }

    public void setReturnReceiptId(String returnReceiptId) {
        this.returnReceiptId = returnReceiptId;
    }

    public String getReturnStore() {
        return returnStore;
    }

    public void setReturnStore(String returnStore) {
        this.returnStore = returnStore;
    }

    public String getReturnTerminal() {
        return returnTerminal;
    }

    public void setReturnTerminal(String returnTerminal) {
        this.returnTerminal = returnTerminal;
    }

    public String getReturnTransactionId() {
        return returnTransactionId;
    }

    public void setReturnTransactionId(String returnTransactionId) {
        this.returnTransactionId = returnTransactionId;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getRoundedAmount() {
        return roundedAmount;
    }

    public void setRoundedAmount(Integer roundedAmount) {
        this.roundedAmount = roundedAmount;
    }

    public Integer getSez() {
        return sez;
    }

    public void setSez(Integer sez) {
        this.sez = sez;
    }

    public List<SalesLine> getSalesLine() {
        return salesLine;
    }

    public void setSalesLine(List<SalesLine> salesLine) {
        this.salesLine = salesLine;
    }

    public String getSalesOrigin() {
        return salesOrigin;
    }

    public void setSalesOrigin(String salesOrigin) {
        this.salesOrigin = salesOrigin;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Object> getTenderLine() {
        return tenderLine;
    }

    public void setTenderLine(List<Object> tenderLine) {
        this.tenderLine = tenderLine;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Integer getTimewhenTransClosed() {
        return timewhenTransClosed;
    }

    public void setTimewhenTransClosed(Integer timewhenTransClosed) {
        this.timewhenTransClosed = timewhenTransClosed;
    }

    public Integer getTotalDiscAmount() {
        return totalDiscAmount;
    }

    public void setTotalDiscAmount(Integer totalDiscAmount) {
        this.totalDiscAmount = totalDiscAmount;
    }

    public Double getTotalMRP() {
        return totalMRP;
    }

    public void setTotalMRP(Double totalMRP) {
        this.totalMRP = totalMRP;
    }

    public Integer getTotalManualDiscountAmount() {
        return totalManualDiscountAmount;
    }

    public void setTotalManualDiscountAmount(Integer totalManualDiscountAmount) {
        this.totalManualDiscountAmount = totalManualDiscountAmount;
    }

    public Integer getTotalManualDiscountPercentage() {
        return totalManualDiscountPercentage;
    }

    public void setTotalManualDiscountPercentage(Integer totalManualDiscountPercentage) {
        this.totalManualDiscountPercentage = totalManualDiscountPercentage;
    }

    public Double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public String getTrackingRef() {
        return trackingRef;
    }

    public void setTrackingRef(String trackingRef) {
        this.trackingRef = trackingRef;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Double getOMSCreditAmount() {
        return oMSCreditAmount;
    }

    public void setOMSCreditAmount(Double oMSCreditAmount) {
        this.oMSCreditAmount = oMSCreditAmount;
    }

    public Double getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(Double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public static class SalesLine {

        @SerializedName("Additionaltax")
        @Expose
        private Double additionaltax;
        @SerializedName("ApplyDiscount")
        @Expose
        private Boolean applyDiscount;
        @SerializedName("Barcode")
        @Expose
        private String barcode;
        @SerializedName("BaseAmount")
        @Expose
        private Double baseAmount;
        @SerializedName("CESSPerc")
        @Expose
        private Double cESSPerc;
        @SerializedName("CESSTaxCode")
        @Expose
        private String cESSTaxCode;
        @SerializedName("CGSTPerc")
        @Expose
        private Double cGSTPerc;
        @SerializedName("CGSTTaxCode")
        @Expose
        private String cGSTTaxCode;
        @SerializedName("Category")
        @Expose
        private String category;
        @SerializedName("CategoryCode")
        @Expose
        private String categoryCode;
        @SerializedName("CategoryReference")
        @Expose
        private String categoryReference;
        @SerializedName("Comment")
        @Expose
        private String comment;
        @SerializedName("DPCO")
        @Expose
        private Boolean dpco;
        @SerializedName("DiscAmount")
        @Expose
        private Double discAmount;
        @SerializedName("DiscId")
        @Expose
        private String discId;
        @SerializedName("DiscOfferId")
        @Expose
        private String discOfferId;
        @SerializedName("DiscountStructureType")
        @Expose
        private Double discountStructureType;
        @SerializedName("DiscountType")
        @Expose
        private String discountType;
        @SerializedName("DiseaseType")
        @Expose
        private String diseaseType;
        @SerializedName("Expiry")
        @Expose
        private String expiry;
        @SerializedName("Hsncode_In")
        @Expose
        private String hsncodeIn;
        @SerializedName("IGSTPerc")
        @Expose
        private Double iGSTPerc;
        @SerializedName("IGSTTaxCode")
        @Expose
        private String iGSTTaxCode;
        @SerializedName("ISPrescribed")
        @Expose
        private Double iSPrescribed;
        @SerializedName("ISReserved")
        @Expose
        private Boolean iSReserved;
        @SerializedName("ISStockAvailable")
        @Expose
        private Boolean iSStockAvailable;
        @SerializedName("InventBatchId")
        @Expose
        private String inventBatchId;
        @SerializedName("IsChecked")
        @Expose
        private Boolean isChecked;
        @SerializedName("IsGeneric")
        @Expose
        private Boolean isGeneric;
        @SerializedName("IsPriceOverride")
        @Expose
        private Boolean isPriceOverride;
        @SerializedName("IsSubsitute")
        @Expose
        private Boolean isSubsitute;
        @SerializedName("IsVoid")
        @Expose
        private Boolean isVoid;
        @SerializedName("ItemId")
        @Expose
        private String itemId;
        @SerializedName("ItemName")
        @Expose
        private String itemName;
        @SerializedName("LineDiscPercentage")
        @Expose
        private Double lineDiscPercentage;
        @SerializedName("LineManualDiscountAmount")
        @Expose
        private Double lineManualDiscountAmount;
        @SerializedName("LineManualDiscountPercentage")
        @Expose
        private Double lineManualDiscountPercentage;
        @SerializedName("LineNo")
        @Expose
        private Integer lineNo;
        @SerializedName("LinedscAmount")
        @Expose
        private Double linedscAmount;
        @SerializedName("MMGroupId")
        @Expose
        private String mMGroupId;
        @SerializedName("MRP")
        @Expose
        private Double mrp;
        @SerializedName("ManufacturerCode")
        @Expose
        private String manufacturerCode;
        @SerializedName("ManufacturerName")
        @Expose
        private String manufacturerName;
        @SerializedName("MixMode")
        @Expose
        private Boolean mixMode;
        @SerializedName("ModifyBatchId")
        @Expose
        private String modifyBatchId;
        @SerializedName("NetAmount")
        @Expose
        private Double netAmount;
        @SerializedName("NetAmountInclTax")
        @Expose
        private Double netAmountInclTax;
        @SerializedName("OfferAmount")
        @Expose
        private Double offerAmount;
        @SerializedName("OfferDiscountType")
        @Expose
        private Double offerDiscountType;
        @SerializedName("OfferDiscountValue")
        @Expose
        private Double offerDiscountValue;
        @SerializedName("OfferQty")
        @Expose
        private Double offerQty;
        @SerializedName("OfferType")
        @Expose
        private Double offerType;
        @SerializedName("OmsLineID")
        @Expose
        private Double omsLineID;
        @SerializedName("OmsLineRECID")
        @Expose
        private Double omsLineRECID;
        @SerializedName("OrderStatus")
        @Expose
        private Double orderStatus;
        @SerializedName("OriginalPrice")
        @Expose
        private Double originalPrice;
        @SerializedName("PeriodicDiscAmount")
        @Expose
        private Double periodicDiscAmount;
        @SerializedName("PhysicalMRP")
        @Expose
        private Double physicalMRP;
        @SerializedName("PreviewText")
        @Expose
        private String previewText;
        @SerializedName("Price")
        @Expose
        private Double price;
        @SerializedName("ProductRecID")
        @Expose
        private String productRecID;
        @SerializedName("Qty")
        @Expose
        private Integer qty;
        @SerializedName("RemainderDays")
        @Expose
        private Double remainderDays;
        @SerializedName("RemainingQty")
        @Expose
        private Double remainingQty;
        @SerializedName("Resqtyflag")
        @Expose
        private Boolean resqtyflag;
        @SerializedName("RetailCategoryRecID")
        @Expose
        private String retailCategoryRecID;
        @SerializedName("RetailMainCategoryRecID")
        @Expose
        private String retailMainCategoryRecID;
        @SerializedName("RetailSubCategoryRecID")
        @Expose
        private String retailSubCategoryRecID;
        @SerializedName("ReturnQty")
        @Expose
        private Double returnQty;
        @SerializedName("SGSTPerc")
        @Expose
        private Double sGSTPerc;
        @SerializedName("SGSTTaxCode")
        @Expose
        private String sGSTTaxCode;
        @SerializedName("ScheduleCategory")
        @Expose
        private String scheduleCategory;
        @SerializedName("ScheduleCategoryCode")
        @Expose
        private String scheduleCategoryCode;
        @SerializedName("StockQty")
        @Expose
        private Double stockQty;
        @SerializedName("SubCategory")
        @Expose
        private String subCategory;
        @SerializedName("SubCategoryCode")
        @Expose
        private String subCategoryCode;
        @SerializedName("SubClassification")
        @Expose
        private String subClassification;
        @SerializedName("SubstitudeItemId")
        @Expose
        private String substitudeItemId;
        @SerializedName("Tax")
        @Expose
        private Double tax;
        @SerializedName("TaxAmount")
        @Expose
        private Double taxAmount;
        @SerializedName("Total")
        @Expose
        private Double total;
        @SerializedName("TotalDiscAmount")
        @Expose
        private Double totalDiscAmount;
        @SerializedName("TotalDiscPct")
        @Expose
        private Double totalDiscPct;
        @SerializedName("TotalRoundedAmount")
        @Expose
        private Double totalRoundedAmount;
        @SerializedName("TotalTax")
        @Expose
        private Double totalTax;
        @SerializedName("Unit")
        @Expose
        private String unit;
        @SerializedName("UnitPrice")
        @Expose
        private Double unitPrice;
        @SerializedName("UnitQty")
        @Expose
        private Double unitQty;
        @SerializedName("VariantId")
        @Expose
        private String variantId;
        @SerializedName("isReturnClick")
        @Expose
        private Boolean isReturnClick;
        @SerializedName("isSelectedReturnItem")
        @Expose
        private Boolean isSelectedReturnItem;
        private String batchNo;

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public Double getAdditionaltax() {
            return additionaltax;
        }

        public void setAdditionaltax(Double additionaltax) {
            this.additionaltax = additionaltax;
        }

        public Boolean getApplyDiscount() {
            return applyDiscount;
        }

        public void setApplyDiscount(Boolean applyDiscount) {
            this.applyDiscount = applyDiscount;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public Double getBaseAmount() {
            return baseAmount;
        }

        public void setBaseAmount(Double baseAmount) {
            this.baseAmount = baseAmount;
        }

        public Double getCESSPerc() {
            return cESSPerc;
        }

        public void setCESSPerc(Double cESSPerc) {
            this.cESSPerc = cESSPerc;
        }

        public String getCESSTaxCode() {
            return cESSTaxCode;
        }

        public void setCESSTaxCode(String cESSTaxCode) {
            this.cESSTaxCode = cESSTaxCode;
        }

        public Double getCGSTPerc() {
            return cGSTPerc;
        }

        public void setCGSTPerc(Double cGSTPerc) {
            this.cGSTPerc = cGSTPerc;
        }

        public String getCGSTTaxCode() {
            return cGSTTaxCode;
        }

        public void setCGSTTaxCode(String cGSTTaxCode) {
            this.cGSTTaxCode = cGSTTaxCode;
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

        public String getCategoryReference() {
            return categoryReference;
        }

        public void setCategoryReference(String categoryReference) {
            this.categoryReference = categoryReference;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Boolean getDpco() {
            return dpco;
        }

        public void setDpco(Boolean dpco) {
            this.dpco = dpco;
        }

        public Double getDiscAmount() {
            return discAmount;
        }

        public void setDiscAmount(Double discAmount) {
            this.discAmount = discAmount;
        }

        public String getDiscId() {
            return discId;
        }

        public void setDiscId(String discId) {
            this.discId = discId;
        }

        public String getDiscOfferId() {
            return discOfferId;
        }

        public void setDiscOfferId(String discOfferId) {
            this.discOfferId = discOfferId;
        }

        public Double getDiscountStructureType() {
            return discountStructureType;
        }

        public void setDiscountStructureType(Double discountStructureType) {
            this.discountStructureType = discountStructureType;
        }

        public String getDiscountType() {
            return discountType;
        }

        public void setDiscountType(String discountType) {
            this.discountType = discountType;
        }

        public String getDiseaseType() {
            return diseaseType;
        }

        public void setDiseaseType(String diseaseType) {
            this.diseaseType = diseaseType;
        }

        public String getExpiry() {
            return expiry;
        }

        public void setExpiry(String expiry) {
            this.expiry = expiry;
        }

        public String getHsncodeIn() {
            return hsncodeIn;
        }

        public void setHsncodeIn(String hsncodeIn) {
            this.hsncodeIn = hsncodeIn;
        }

        public Double getIGSTPerc() {
            return iGSTPerc;
        }

        public void setIGSTPerc(Double iGSTPerc) {
            this.iGSTPerc = iGSTPerc;
        }

        public String getIGSTTaxCode() {
            return iGSTTaxCode;
        }

        public void setIGSTTaxCode(String iGSTTaxCode) {
            this.iGSTTaxCode = iGSTTaxCode;
        }

        public Double getISPrescribed() {
            return iSPrescribed;
        }

        public void setISPrescribed(Double iSPrescribed) {
            this.iSPrescribed = iSPrescribed;
        }

        public Boolean getISReserved() {
            return iSReserved;
        }

        public void setISReserved(Boolean iSReserved) {
            this.iSReserved = iSReserved;
        }

        public Boolean getISStockAvailable() {
            return iSStockAvailable;
        }

        public void setISStockAvailable(Boolean iSStockAvailable) {
            this.iSStockAvailable = iSStockAvailable;
        }

        public String getInventBatchId() {
            return inventBatchId;
        }

        public void setInventBatchId(String inventBatchId) {
            this.inventBatchId = inventBatchId;
        }

        public Boolean getIsChecked() {
            return isChecked;
        }

        public void setIsChecked(Boolean isChecked) {
            this.isChecked = isChecked;
        }

        public Boolean getIsGeneric() {
            return isGeneric;
        }

        public void setIsGeneric(Boolean isGeneric) {
            this.isGeneric = isGeneric;
        }

        public Boolean getIsPriceOverride() {
            return isPriceOverride;
        }

        public void setIsPriceOverride(Boolean isPriceOverride) {
            this.isPriceOverride = isPriceOverride;
        }

        public Boolean getIsSubsitute() {
            return isSubsitute;
        }

        public void setIsSubsitute(Boolean isSubsitute) {
            this.isSubsitute = isSubsitute;
        }

        public Boolean getIsVoid() {
            return isVoid;
        }

        public void setIsVoid(Boolean isVoid) {
            this.isVoid = isVoid;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Double getLineDiscPercentage() {
            return lineDiscPercentage;
        }

        public void setLineDiscPercentage(Double lineDiscPercentage) {
            this.lineDiscPercentage = lineDiscPercentage;
        }

        public Double getLineManualDiscountAmount() {
            return lineManualDiscountAmount;
        }

        public void setLineManualDiscountAmount(Double lineManualDiscountAmount) {
            this.lineManualDiscountAmount = lineManualDiscountAmount;
        }

        public Double getLineManualDiscountPercentage() {
            return lineManualDiscountPercentage;
        }

        public void setLineManualDiscountPercentage(Double lineManualDiscountPercentage) {
            this.lineManualDiscountPercentage = lineManualDiscountPercentage;
        }

        public Integer getLineNo() {
            return lineNo;
        }

        public void setLineNo(Integer lineNo) {
            this.lineNo = lineNo;
        }

        public Double getLinedscAmount() {
            return linedscAmount;
        }

        public void setLinedscAmount(Double linedscAmount) {
            this.linedscAmount = linedscAmount;
        }

        public String getMMGroupId() {
            return mMGroupId;
        }

        public void setMMGroupId(String mMGroupId) {
            this.mMGroupId = mMGroupId;
        }

        public Double getMrp() {
            return mrp;
        }

        public void setMrp(Double mrp) {
            this.mrp = mrp;
        }

        public String getManufacturerCode() {
            return manufacturerCode;
        }

        public void setManufacturerCode(String manufacturerCode) {
            this.manufacturerCode = manufacturerCode;
        }

        public String getManufacturerName() {
            return manufacturerName;
        }

        public void setManufacturerName(String manufacturerName) {
            this.manufacturerName = manufacturerName;
        }

        public Boolean getMixMode() {
            return mixMode;
        }

        public void setMixMode(Boolean mixMode) {
            this.mixMode = mixMode;
        }

        public String getModifyBatchId() {
            return modifyBatchId;
        }

        public void setModifyBatchId(String modifyBatchId) {
            this.modifyBatchId = modifyBatchId;
        }

        public Double getNetAmount() {
            return netAmount;
        }

        public void setNetAmount(Double netAmount) {
            this.netAmount = netAmount;
        }

        public Double getNetAmountInclTax() {
            return netAmountInclTax;
        }

        public void setNetAmountInclTax(Double netAmountInclTax) {
            this.netAmountInclTax = netAmountInclTax;
        }

        public Double getOfferAmount() {
            return offerAmount;
        }

        public void setOfferAmount(Double offerAmount) {
            this.offerAmount = offerAmount;
        }

        public Double getOfferDiscountType() {
            return offerDiscountType;
        }

        public void setOfferDiscountType(Double offerDiscountType) {
            this.offerDiscountType = offerDiscountType;
        }

        public Double getOfferDiscountValue() {
            return offerDiscountValue;
        }

        public void setOfferDiscountValue(Double offerDiscountValue) {
            this.offerDiscountValue = offerDiscountValue;
        }

        public Double getOfferQty() {
            return offerQty;
        }

        public void setOfferQty(Double offerQty) {
            this.offerQty = offerQty;
        }

        public Double getOfferType() {
            return offerType;
        }

        public void setOfferType(Double offerType) {
            this.offerType = offerType;
        }

        public Double getOmsLineID() {
            return omsLineID;
        }

        public void setOmsLineID(Double omsLineID) {
            this.omsLineID = omsLineID;
        }

        public Double getOmsLineRECID() {
            return omsLineRECID;
        }

        public void setOmsLineRECID(Double omsLineRECID) {
            this.omsLineRECID = omsLineRECID;
        }

        public Double getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(Double orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(Double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public Double getPeriodicDiscAmount() {
            return periodicDiscAmount;
        }

        public void setPeriodicDiscAmount(Double periodicDiscAmount) {
            this.periodicDiscAmount = periodicDiscAmount;
        }

        public Double getPhysicalMRP() {
            return physicalMRP;
        }

        public void setPhysicalMRP(Double physicalMRP) {
            this.physicalMRP = physicalMRP;
        }

        public String getPreviewText() {
            return previewText;
        }

        public void setPreviewText(String previewText) {
            this.previewText = previewText;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getProductRecID() {
            return productRecID;
        }

        public void setProductRecID(String productRecID) {
            this.productRecID = productRecID;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public Double getRemainderDays() {
            return remainderDays;
        }

        public void setRemainderDays(Double remainderDays) {
            this.remainderDays = remainderDays;
        }

        public Double getRemainingQty() {
            return remainingQty;
        }

        public void setRemainingQty(Double remainingQty) {
            this.remainingQty = remainingQty;
        }

        public Boolean getResqtyflag() {
            return resqtyflag;
        }

        public void setResqtyflag(Boolean resqtyflag) {
            this.resqtyflag = resqtyflag;
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

        public Double getReturnQty() {
            return returnQty;
        }

        public void setReturnQty(Double returnQty) {
            this.returnQty = returnQty;
        }

        public Double getSGSTPerc() {
            return sGSTPerc;
        }

        public void setSGSTPerc(Double sGSTPerc) {
            this.sGSTPerc = sGSTPerc;
        }

        public String getSGSTTaxCode() {
            return sGSTTaxCode;
        }

        public void setSGSTTaxCode(String sGSTTaxCode) {
            this.sGSTTaxCode = sGSTTaxCode;
        }

        public String getScheduleCategory() {
            return scheduleCategory;
        }

        public void setScheduleCategory(String scheduleCategory) {
            this.scheduleCategory = scheduleCategory;
        }

        public String getScheduleCategoryCode() {
            return scheduleCategoryCode;
        }

        public void setScheduleCategoryCode(String scheduleCategoryCode) {
            this.scheduleCategoryCode = scheduleCategoryCode;
        }

        public Double getStockQty() {
            return stockQty;
        }

        public void setStockQty(Double stockQty) {
            this.stockQty = stockQty;
        }

        public String getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(String subCategory) {
            this.subCategory = subCategory;
        }

        public String getSubCategoryCode() {
            return subCategoryCode;
        }

        public void setSubCategoryCode(String subCategoryCode) {
            this.subCategoryCode = subCategoryCode;
        }

        public String getSubClassification() {
            return subClassification;
        }

        public void setSubClassification(String subClassification) {
            this.subClassification = subClassification;
        }

        public String getSubstitudeItemId() {
            return substitudeItemId;
        }

        public void setSubstitudeItemId(String substitudeItemId) {
            this.substitudeItemId = substitudeItemId;
        }

        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public Double getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(Double taxAmount) {
            this.taxAmount = taxAmount;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Double getTotalDiscAmount() {
            return totalDiscAmount;
        }

        public void setTotalDiscAmount(Double totalDiscAmount) {
            this.totalDiscAmount = totalDiscAmount;
        }

        public Double getTotalDiscPct() {
            return totalDiscPct;
        }

        public void setTotalDiscPct(Double totalDiscPct) {
            this.totalDiscPct = totalDiscPct;
        }

        public Double getTotalRoundedAmount() {
            return totalRoundedAmount;
        }

        public void setTotalRoundedAmount(Double totalRoundedAmount) {
            this.totalRoundedAmount = totalRoundedAmount;
        }

        public Double getTotalTax() {
            return totalTax;
        }

        public void setTotalTax(Double totalTax) {
            this.totalTax = totalTax;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Double getUnitQty() {
            return unitQty;
        }

        public void setUnitQty(Double unitQty) {
            this.unitQty = unitQty;
        }

        public String getVariantId() {
            return variantId;
        }

        public void setVariantId(String variantId) {
            this.variantId = variantId;
        }

        public Boolean getIsReturnClick() {
            return isReturnClick;
        }

        public void setIsReturnClick(Boolean isReturnClick) {
            this.isReturnClick = isReturnClick;
        }

        public Boolean getIsSelectedReturnItem() {
            return isSelectedReturnItem;
        }

        public void setIsSelectedReturnItem(Boolean isSelectedReturnItem) {
            this.isSelectedReturnItem = isSelectedReturnItem;
        }

    }
}