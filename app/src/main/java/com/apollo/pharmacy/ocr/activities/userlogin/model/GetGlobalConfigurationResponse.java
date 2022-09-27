package com.apollo.pharmacy.ocr.activities.userlogin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetGlobalConfigurationResponse {

    @SerializedName("ADJISCategoryPositiveValueAllowed")
    @Expose
    private Boolean aDJISCategoryPositiveValueAllowed;
    @SerializedName("ADJISGenericAllowed")
    @Expose
    private Boolean aDJISGenericAllowed;
    @SerializedName("ADJISPositiveValue")
    @Expose
    private Boolean aDJISPositiveValue;
    @SerializedName("ADJIsOfferAllowed")
    @Expose
    private Boolean aDJIsOfferAllowed;
    @SerializedName("AGERange")
    @Expose
    private List<String> aGERange = null;
    @SerializedName("AHELDemergerDate")
    @Expose
    private String aHELDemergerDate;
    @SerializedName("AXInventoryURL")
    @Expose
    private String aXInventoryURL;
    @SerializedName("AXServiceDomain")
    @Expose
    private String aXServiceDomain;
    @SerializedName("AXServicePassword")
    @Expose
    private String aXServicePassword;
    @SerializedName("AXServiceURL")
    @Expose
    private String aXServiceURL;
    @SerializedName("AXServiceUsername")
    @Expose
    private String aXServiceUsername;
    @SerializedName("AdjustmentIssuesOTPRequired")
    @Expose
    private Boolean adjustmentIssuesOTPRequired;
    @SerializedName("AuthenticationKey")
    @Expose
    private String authenticationKey;
    @SerializedName("BTNotificationFrequency")
    @Expose
    private Integer bTNotificationFrequency;
    @SerializedName("BackupPath")
    @Expose
    private String backupPath;
    @SerializedName("BajajFinanceCorpCode")
    @Expose
    private String bajajFinanceCorpCode;
    @SerializedName("BranchTransferOTPRequired")
    @Expose
    private Boolean branchTransferOTPRequired;
    @SerializedName("BulkDiscLimitAllowed")
    @Expose
    private Boolean bulkDiscLimitAllowed;
    @SerializedName("BulkDiscMinValue")
    @Expose
    private Integer bulkDiscMinValue;
    @SerializedName("BulkDiscQohCheck")
    @Expose
    private Boolean bulkDiscQohCheck;
    @SerializedName("Channel")
    @Expose
    private String channel;
    @SerializedName("CircleplanCorpCode")
    @Expose
    private String circleplanCorpCode;
    @SerializedName("ClusterCode")
    @Expose
    private String clusterCode;
    @SerializedName("CodCorpCodeEnableList")
    @Expose
    private List<String> codCorpCodeEnableList = null;
    @SerializedName("CreditCardIntegration")
    @Expose
    private Boolean creditCardIntegration;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("CustomerFeedbackStatusAllowed")
    @Expose
    private Boolean customerFeedbackStatusAllowed;
    @SerializedName("CustomerFeedbackStatusURL")
    @Expose
    private String customerFeedbackStatusURL;
    @SerializedName("CustomerSearchAXUrl")
    @Expose
    private String customerSearchAXUrl;
    @SerializedName("CustomerSearchOneApolloUrl")
    @Expose
    private String customerSearchOneApolloUrl;
    @SerializedName("DCReturnWithRefOTPRequired")
    @Expose
    private Boolean dCReturnWithRefOTPRequired;
    @SerializedName("DCReturnWithoutRefOTPRequired")
    @Expose
    private Boolean dCReturnWithoutRefOTPRequired;
    @SerializedName("DSBillingURL")
    @Expose
    private String dSBillingURL;
    @SerializedName("DSOTPRequired")
    @Expose
    private Boolean dSOTPRequired;
    @SerializedName("DataAreaID")
    @Expose
    private String dataAreaID;
    @SerializedName("DefaultCustomerAccount")
    @Expose
    private String defaultCustomerAccount;
    @SerializedName("DigitalReceiptRequired")
    @Expose
    private Boolean digitalReceiptRequired;
    @SerializedName("DigitalReceiptRequiredAlways")
    @Expose
    private Boolean digitalReceiptRequiredAlways;
    @SerializedName("DigitalScratchCardUrl")
    @Expose
    private String digitalScratchCardUrl;
    @SerializedName("DisplayStockItems")
    @Expose
    private Boolean displayStockItems;
    @SerializedName("DoctorSearchUrl")
    @Expose
    private String doctorSearchUrl;
    @SerializedName("EmployeeSearchURL")
    @Expose
    private String employeeSearchURL;
    @SerializedName("EzetapWIFIUrl")
    @Expose
    private String ezetapWIFIUrl;
    @SerializedName("FMCGReturnsOTPRequired")
    @Expose
    private Boolean fMCGReturnsOTPRequired;
    @SerializedName("FMCGReturnsStore")
    @Expose
    private String fMCGReturnsStore;
    @SerializedName("FunctionalityProfile")
    @Expose
    private String functionalityProfile;
    @SerializedName("HDOrderCODEnableCorp")
    @Expose
    private List<String> hDOrderCODEnableCorp = null;
    @SerializedName("HealingCardActivationMinAmount")
    @Expose
    private Integer healingCardActivationMinAmount;
    @SerializedName("HealingCardCorpId")
    @Expose
    private String healingCardCorpId;
    @SerializedName("HealingCardItemId")
    @Expose
    private String healingCardItemId;
    @SerializedName("HealingCardMaxAmount")
    @Expose
    private Integer healingCardMaxAmount;
    @SerializedName("HealingCardOtpProcessUrl")
    @Expose
    private String healingCardOtpProcessUrl;
    @SerializedName("HealingCardReloadMinAmount")
    @Expose
    private Integer healingCardReloadMinAmount;
    @SerializedName("HealingCardURL")
    @Expose
    private String healingCardURL;
    @SerializedName("HomeDeliveryCode")
    @Expose
    private String homeDeliveryCode;
    @SerializedName("ISA5BillPrinter")
    @Expose
    private Boolean iSA5BillPrinter;
    @SerializedName("ISAdvancePaymentAllowed")
    @Expose
    private Boolean iSAdvancePaymentAllowed;
    @SerializedName("ISAgeGenderPopupRequired")
    @Expose
    private Boolean iSAgeGenderPopupRequired;
    @SerializedName("ISAllTerminalReturn")
    @Expose
    private Boolean iSAllTerminalReturn;
    @SerializedName("ISBillingPaymentAllowed")
    @Expose
    private Boolean iSBillingPaymentAllowed;
    @SerializedName("ISCancelBillReprint")
    @Expose
    private Boolean iSCancelBillReprint;
    @SerializedName("ISCardBilling")
    @Expose
    private Boolean iSCardBilling;
    @SerializedName("ISCircleBenefitPopupAllowed")
    @Expose
    private Boolean iSCircleBenefitPopupAllowed;
    @SerializedName("ISCreditTenderEnable")
    @Expose
    private Boolean iSCreditTenderEnable;
    @SerializedName("ISDC")
    @Expose
    private Boolean isdc;
    @SerializedName("ISDCReturnWithoutRef")
    @Expose
    private Boolean iSDCReturnWithoutRef;
    @SerializedName("ISDVDCEnabled")
    @Expose
    private String iSDVDCEnabled;
    @SerializedName("ISDoctorLoadSiteWise")
    @Expose
    private Boolean iSDoctorLoadSiteWise;
    @SerializedName("ISEzetapActive")
    @Expose
    private Boolean iSEzetapActive;
    @SerializedName("ISHBPStore")
    @Expose
    private Boolean iSHBPStore;
    @SerializedName("ISHDAddress")
    @Expose
    private Boolean iSHDAddress;
    @SerializedName("ISHDPaymentChange")
    @Expose
    private Boolean iSHDPaymentChange;
    @SerializedName("ISHO")
    @Expose
    private Boolean isho;
    @SerializedName("ISMBQtyRequired")
    @Expose
    private Boolean iSMBQtyRequired;
    @SerializedName("ISMobileNo")
    @Expose
    private Boolean iSMobileNo;
    @SerializedName("ISNoRefReturnAllowed")
    @Expose
    private Boolean iSNoRefReturnAllowed;
    @SerializedName("ISNotification")
    @Expose
    private Boolean iSNotification;
    @SerializedName("ISOMSOrderTabDefault")
    @Expose
    private Boolean iSOMSOrderTabDefault;
    @SerializedName("ISONGCSms")
    @Expose
    private Boolean iSONGCSms;
    @SerializedName("ISOPReturnWithoutRef")
    @Expose
    private Boolean iSOPReturnWithoutRef;
    @SerializedName("ISPickListAllowed")
    @Expose
    private Boolean iSPickListAllowed;
    @SerializedName("ISReligareWallet")
    @Expose
    private String iSReligareWallet;
    @SerializedName("ISRemainder")
    @Expose
    private Boolean iSRemainder;
    @SerializedName("ISReturnOTPRequired")
    @Expose
    private Boolean iSReturnOTPRequired;
    @SerializedName("ISServiceRecoveryHomeDelivery")
    @Expose
    private Boolean iSServiceRecoveryHomeDelivery;
    @SerializedName("ISSplitBillActive")
    @Expose
    private Boolean iSSplitBillActive;
    @SerializedName("ISStoreCallOrdersOtp")
    @Expose
    private Boolean iSStoreCallOrdersOtp;
    @SerializedName("ISTPASeller")
    @Expose
    private Boolean iSTPASeller;
    @SerializedName("ISUpsellingEnabled")
    @Expose
    private Boolean iSUpsellingEnabled;
    @SerializedName("ISVDCFR")
    @Expose
    private Boolean isvdcfr;
    @SerializedName("ISVDCPR")
    @Expose
    private Boolean isvdcpr;
    @SerializedName("IsAdjustmentEnable")
    @Expose
    private Boolean isAdjustmentEnable;
    @SerializedName("IsAirportOrderTabAllowed")
    @Expose
    private Boolean isAirportOrderTabAllowed;
    @SerializedName("IsBTNotification")
    @Expose
    private Boolean isBTNotification;
    @SerializedName("IsBranchTransferEnable")
    @Expose
    private Boolean isBranchTransferEnable;
    @SerializedName("IsBulkBillingAllowed")
    @Expose
    private Boolean isBulkBillingAllowed;
    @SerializedName("IsBulkDiscountAllowed")
    @Expose
    private Boolean isBulkDiscountAllowed;
    @SerializedName("IsClientSearch")
    @Expose
    private String isClientSearch;
    @SerializedName("IsDCReturnWithRefEnable")
    @Expose
    private Boolean isDCReturnWithRefEnable;
    @SerializedName("IsDuplicateReceiptLogAllowed")
    @Expose
    private Boolean isDuplicateReceiptLogAllowed;
    @SerializedName("IsFeedbackNotification")
    @Expose
    private Boolean isFeedbackNotification;
    @SerializedName("IsIITDelhi")
    @Expose
    private Boolean isIITDelhi;
    @SerializedName("IsIPPatientOtpRequired")
    @Expose
    private Boolean isIPPatientOtpRequired;
    @SerializedName("IsInterCompany")
    @Expose
    private Boolean isInterCompany;
    @SerializedName("IsKribhkoStore")
    @Expose
    private Boolean isKribhkoStore;
    @SerializedName("IsNITStockUplaod")
    @Expose
    private Boolean isNITStockUplaod;
    @SerializedName("IsNPSMsgTrigger")
    @Expose
    private Boolean isNPSMsgTrigger;
    @SerializedName("IsNonFMCGItemDisplay")
    @Expose
    private Boolean isNonFMCGItemDisplay;
    @SerializedName("IsOMSBatchProcess")
    @Expose
    private Boolean isOMSBatchProcess;
    @SerializedName("IsOMSHomeDeliveryConversation")
    @Expose
    private Boolean isOMSHomeDeliveryConversation;
    @SerializedName("IsOPReturnWithRefEnable")
    @Expose
    private Boolean isOPReturnWithRefEnable;
    @SerializedName("IsPinelabActive")
    @Expose
    private Boolean isPinelabActive;
    @SerializedName("IsSalesTab")
    @Expose
    private Boolean isSalesTab;
    @SerializedName("IsStationaryStockCheck")
    @Expose
    private Boolean isStationaryStockCheck;
    @SerializedName("LastBillDate")
    @Expose
    private String lastBillDate;
    @SerializedName("LiveUrl")
    @Expose
    private String liveUrl;
    @SerializedName("LooseDamagAmount")
    @Expose
    private Integer looseDamagAmount;
    @SerializedName("LooseDamagSite")
    @Expose
    private String looseDamagSite;
    @SerializedName("MPOSMaxOrderAllowed")
    @Expose
    private Integer mPOSMaxOrderAllowed;
    @SerializedName("MPOSVersion")
    @Expose
    private String mPOSVersion;
    @SerializedName("ManualBillModification")
    @Expose
    private Boolean manualBillModification;
    @SerializedName("ManualBillingAllowHours")
    @Expose
    private Integer manualBillingAllowHours;
    @SerializedName("MaxBulkOMSOrders")
    @Expose
    private Integer maxBulkOMSOrders;
    @SerializedName("MaxDonationAllowed")
    @Expose
    private Integer maxDonationAllowed;
    @SerializedName("MaxDonationRequiredAlways")
    @Expose
    private Boolean maxDonationRequiredAlways;
    @SerializedName("MediassistCancelDays")
    @Expose
    private Integer mediassistCancelDays;
    @SerializedName("MinOrderAmount")
    @Expose
    private Integer minOrderAmount;
    @SerializedName("NormalSale")
    @Expose
    private String normalSale;
    @SerializedName("NotificationFrequecy")
    @Expose
    private Integer notificationFrequecy;
    @SerializedName("OMSDefaultOtpValue")
    @Expose
    private String oMSDefaultOtpValue;
    @SerializedName("OMSDefaultOtpValueAllowed")
    @Expose
    private Boolean oMSDefaultOtpValueAllowed;
    @SerializedName("OMSExpiryDays")
    @Expose
    private Integer oMSExpiryDays;
    @SerializedName("OMSPopupDashBoardFrequency")
    @Expose
    private Integer oMSPopupDashBoardFrequency;
    @SerializedName("OMSPopupDashBoardNotification")
    @Expose
    private Boolean oMSPopupDashBoardNotification;
    @SerializedName("OMSTatApiKey")
    @Expose
    private String oMSTatApiKey;
    @SerializedName("OMSTatURL")
    @Expose
    private String oMSTatURL;
    @SerializedName("OMSUrl")
    @Expose
    private String oMSUrl;
    @SerializedName("OPReturnWithoutRefOTPRequired")
    @Expose
    private Boolean oPReturnWithoutRefOTPRequired;
    @SerializedName("OPReturnsOTPRequired")
    @Expose
    private Boolean oPReturnsOTPRequired;
    @SerializedName("OTPURL")
    @Expose
    private String otpurl;
    @SerializedName("OneApolloURL")
    @Expose
    private String oneApolloURL;
    @SerializedName("OnlineCancelUpdateURL")
    @Expose
    private String onlineCancelUpdateURL;
    @SerializedName("OnlineGetBatchURL")
    @Expose
    private String onlineGetBatchURL;
    @SerializedName("OnlineGetItemURL")
    @Expose
    private String onlineGetItemURL;
    @SerializedName("OnlineOrderURL")
    @Expose
    private String onlineOrderURL;
    @SerializedName("POSExpiryDays")
    @Expose
    private Integer pOSExpiryDays;
    @SerializedName("ParkTransactionAllowed")
    @Expose
    private Boolean parkTransactionAllowed;
    @SerializedName("PasswordExpiry")
    @Expose
    private Integer passwordExpiry;
    @SerializedName("PayBackCorpCode")
    @Expose
    private String payBackCorpCode;
    @SerializedName("PaybackRoundOffPaisa")
    @Expose
    private Double paybackRoundOffPaisa;
    @SerializedName("PharmaReturnsOTPRequired")
    @Expose
    private Boolean pharmaReturnsOTPRequired;
    @SerializedName("PharmaReturnsStore")
    @Expose
    private String pharmaReturnsStore;
    @SerializedName("PlutusWIFIUrl")
    @Expose
    private String plutusWIFIUrl;
    @SerializedName("PriceChangeURL")
    @Expose
    private String priceChangeURL;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("RemoveAddTender")
    @Expose
    private String removeAddTender;
    @SerializedName("ReprintDateAllowed")
    @Expose
    private String reprintDateAllowed;
    @SerializedName("RequestStatus")
    @Expose
    private Integer requestStatus;
    @SerializedName("RestrictedItemsURL")
    @Expose
    private String restrictedItemsURL;
    @SerializedName("ReturnAllowedDays")
    @Expose
    private Integer returnAllowedDays;
    @SerializedName("ReturnMessage")
    @Expose
    private String returnMessage;
    @SerializedName("SCRATCHCARDURL")
    @Expose
    private String scratchcardurl;
    @SerializedName("SMSAPI")
    @Expose
    private String smsapi;
    @SerializedName("SMSPayRequestType")
    @Expose
    private String sMSPayRequestType;
    @SerializedName("SMSPayURL")
    @Expose
    private String sMSPayURL;
    @SerializedName("STOCKURL")
    @Expose
    private String stockurl;
    @SerializedName("SalesTrackingURL")
    @Expose
    private String salesTrackingURL;
    @SerializedName("SendGlobalMessageURL")
    @Expose
    private String sendGlobalMessageURL;
    @SerializedName("SplitBillAmount")
    @Expose
    private Integer splitBillAmount;
    @SerializedName("StateCode")
    @Expose
    private String stateCode;
    @SerializedName("StationaryStore")
    @Expose
    private String stationaryStore;
    @SerializedName("StoreID")
    @Expose
    private String storeID;
    @SerializedName("StoreName")
    @Expose
    private String storeName;
    @SerializedName("SuspendDeleteDays")
    @Expose
    private Integer suspendDeleteDays;
    @SerializedName("TerminalID")
    @Expose
    private Object terminalID;
    @SerializedName("UHIDHBPURL")
    @Expose
    private String uhidhbpurl;
    @SerializedName("UserCode")
    @Expose
    private Object userCode;
    @SerializedName("_HBPConfig")
    @Expose
    private HBPConfig hBPConfig;
    @SerializedName("_OMSCustomerType")
    @Expose
    private List<OMSCustomerType> oMSCustomerType = null;
    @SerializedName("_OMSVendorWiseConfigration")
    @Expose
    private List<OMSVendorWiseConfigration> oMSVendorWiseConfigration = null;
    @SerializedName("_TenderTypeList")
    @Expose
    private Object tenderTypeList;
    @SerializedName("_TrackingConfigration")
    @Expose
    private Object trackingConfigration;

    public Boolean getADJISCategoryPositiveValueAllowed() {
        return aDJISCategoryPositiveValueAllowed;
    }

    public void setADJISCategoryPositiveValueAllowed(Boolean aDJISCategoryPositiveValueAllowed) {
        this.aDJISCategoryPositiveValueAllowed = aDJISCategoryPositiveValueAllowed;
    }

    public Boolean getADJISGenericAllowed() {
        return aDJISGenericAllowed;
    }

    public void setADJISGenericAllowed(Boolean aDJISGenericAllowed) {
        this.aDJISGenericAllowed = aDJISGenericAllowed;
    }

    public Boolean getADJISPositiveValue() {
        return aDJISPositiveValue;
    }

    public void setADJISPositiveValue(Boolean aDJISPositiveValue) {
        this.aDJISPositiveValue = aDJISPositiveValue;
    }

    public Boolean getADJIsOfferAllowed() {
        return aDJIsOfferAllowed;
    }

    public void setADJIsOfferAllowed(Boolean aDJIsOfferAllowed) {
        this.aDJIsOfferAllowed = aDJIsOfferAllowed;
    }

    public List<String> getAGERange() {
        return aGERange;
    }

    public void setAGERange(List<String> aGERange) {
        this.aGERange = aGERange;
    }

    public String getAHELDemergerDate() {
        return aHELDemergerDate;
    }

    public void setAHELDemergerDate(String aHELDemergerDate) {
        this.aHELDemergerDate = aHELDemergerDate;
    }

    public String getAXInventoryURL() {
        return aXInventoryURL;
    }

    public void setAXInventoryURL(String aXInventoryURL) {
        this.aXInventoryURL = aXInventoryURL;
    }

    public String getAXServiceDomain() {
        return aXServiceDomain;
    }

    public void setAXServiceDomain(String aXServiceDomain) {
        this.aXServiceDomain = aXServiceDomain;
    }

    public String getAXServicePassword() {
        return aXServicePassword;
    }

    public void setAXServicePassword(String aXServicePassword) {
        this.aXServicePassword = aXServicePassword;
    }

    public String getAXServiceURL() {
        return aXServiceURL;
    }

    public void setAXServiceURL(String aXServiceURL) {
        this.aXServiceURL = aXServiceURL;
    }

    public String getAXServiceUsername() {
        return aXServiceUsername;
    }

    public void setAXServiceUsername(String aXServiceUsername) {
        this.aXServiceUsername = aXServiceUsername;
    }

    public Boolean getAdjustmentIssuesOTPRequired() {
        return adjustmentIssuesOTPRequired;
    }

    public void setAdjustmentIssuesOTPRequired(Boolean adjustmentIssuesOTPRequired) {
        this.adjustmentIssuesOTPRequired = adjustmentIssuesOTPRequired;
    }

    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    public Integer getBTNotificationFrequency() {
        return bTNotificationFrequency;
    }

    public void setBTNotificationFrequency(Integer bTNotificationFrequency) {
        this.bTNotificationFrequency = bTNotificationFrequency;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    public String getBajajFinanceCorpCode() {
        return bajajFinanceCorpCode;
    }

    public void setBajajFinanceCorpCode(String bajajFinanceCorpCode) {
        this.bajajFinanceCorpCode = bajajFinanceCorpCode;
    }

    public Boolean getBranchTransferOTPRequired() {
        return branchTransferOTPRequired;
    }

    public void setBranchTransferOTPRequired(Boolean branchTransferOTPRequired) {
        this.branchTransferOTPRequired = branchTransferOTPRequired;
    }

    public Boolean getBulkDiscLimitAllowed() {
        return bulkDiscLimitAllowed;
    }

    public void setBulkDiscLimitAllowed(Boolean bulkDiscLimitAllowed) {
        this.bulkDiscLimitAllowed = bulkDiscLimitAllowed;
    }

    public Integer getBulkDiscMinValue() {
        return bulkDiscMinValue;
    }

    public void setBulkDiscMinValue(Integer bulkDiscMinValue) {
        this.bulkDiscMinValue = bulkDiscMinValue;
    }

    public Boolean getBulkDiscQohCheck() {
        return bulkDiscQohCheck;
    }

    public void setBulkDiscQohCheck(Boolean bulkDiscQohCheck) {
        this.bulkDiscQohCheck = bulkDiscQohCheck;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCircleplanCorpCode() {
        return circleplanCorpCode;
    }

    public void setCircleplanCorpCode(String circleplanCorpCode) {
        this.circleplanCorpCode = circleplanCorpCode;
    }

    public String getClusterCode() {
        return clusterCode;
    }

    public void setClusterCode(String clusterCode) {
        this.clusterCode = clusterCode;
    }

    public List<String> getCodCorpCodeEnableList() {
        return codCorpCodeEnableList;
    }

    public void setCodCorpCodeEnableList(List<String> codCorpCodeEnableList) {
        this.codCorpCodeEnableList = codCorpCodeEnableList;
    }

    public Boolean getCreditCardIntegration() {
        return creditCardIntegration;
    }

    public void setCreditCardIntegration(Boolean creditCardIntegration) {
        this.creditCardIntegration = creditCardIntegration;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getCustomerFeedbackStatusAllowed() {
        return customerFeedbackStatusAllowed;
    }

    public void setCustomerFeedbackStatusAllowed(Boolean customerFeedbackStatusAllowed) {
        this.customerFeedbackStatusAllowed = customerFeedbackStatusAllowed;
    }

    public String getCustomerFeedbackStatusURL() {
        return customerFeedbackStatusURL;
    }

    public void setCustomerFeedbackStatusURL(String customerFeedbackStatusURL) {
        this.customerFeedbackStatusURL = customerFeedbackStatusURL;
    }

    public String getCustomerSearchAXUrl() {
        return customerSearchAXUrl;
    }

    public void setCustomerSearchAXUrl(String customerSearchAXUrl) {
        this.customerSearchAXUrl = customerSearchAXUrl;
    }

    public String getCustomerSearchOneApolloUrl() {
        return customerSearchOneApolloUrl;
    }

    public void setCustomerSearchOneApolloUrl(String customerSearchOneApolloUrl) {
        this.customerSearchOneApolloUrl = customerSearchOneApolloUrl;
    }

    public Boolean getDCReturnWithRefOTPRequired() {
        return dCReturnWithRefOTPRequired;
    }

    public void setDCReturnWithRefOTPRequired(Boolean dCReturnWithRefOTPRequired) {
        this.dCReturnWithRefOTPRequired = dCReturnWithRefOTPRequired;
    }

    public Boolean getDCReturnWithoutRefOTPRequired() {
        return dCReturnWithoutRefOTPRequired;
    }

    public void setDCReturnWithoutRefOTPRequired(Boolean dCReturnWithoutRefOTPRequired) {
        this.dCReturnWithoutRefOTPRequired = dCReturnWithoutRefOTPRequired;
    }

    public String getDSBillingURL() {
        return dSBillingURL;
    }

    public void setDSBillingURL(String dSBillingURL) {
        this.dSBillingURL = dSBillingURL;
    }

    public Boolean getDSOTPRequired() {
        return dSOTPRequired;
    }

    public void setDSOTPRequired(Boolean dSOTPRequired) {
        this.dSOTPRequired = dSOTPRequired;
    }

    public String getDataAreaID() {
        return dataAreaID;
    }

    public void setDataAreaID(String dataAreaID) {
        this.dataAreaID = dataAreaID;
    }

    public String getDefaultCustomerAccount() {
        return defaultCustomerAccount;
    }

    public void setDefaultCustomerAccount(String defaultCustomerAccount) {
        this.defaultCustomerAccount = defaultCustomerAccount;
    }

    public Boolean getDigitalReceiptRequired() {
        return digitalReceiptRequired;
    }

    public void setDigitalReceiptRequired(Boolean digitalReceiptRequired) {
        this.digitalReceiptRequired = digitalReceiptRequired;
    }

    public Boolean getDigitalReceiptRequiredAlways() {
        return digitalReceiptRequiredAlways;
    }

    public void setDigitalReceiptRequiredAlways(Boolean digitalReceiptRequiredAlways) {
        this.digitalReceiptRequiredAlways = digitalReceiptRequiredAlways;
    }

    public String getDigitalScratchCardUrl() {
        return digitalScratchCardUrl;
    }

    public void setDigitalScratchCardUrl(String digitalScratchCardUrl) {
        this.digitalScratchCardUrl = digitalScratchCardUrl;
    }

    public Boolean getDisplayStockItems() {
        return displayStockItems;
    }

    public void setDisplayStockItems(Boolean displayStockItems) {
        this.displayStockItems = displayStockItems;
    }

    public String getDoctorSearchUrl() {
        return doctorSearchUrl;
    }

    public void setDoctorSearchUrl(String doctorSearchUrl) {
        this.doctorSearchUrl = doctorSearchUrl;
    }

    public String getEmployeeSearchURL() {
        return employeeSearchURL;
    }

    public void setEmployeeSearchURL(String employeeSearchURL) {
        this.employeeSearchURL = employeeSearchURL;
    }

    public String getEzetapWIFIUrl() {
        return ezetapWIFIUrl;
    }

    public void setEzetapWIFIUrl(String ezetapWIFIUrl) {
        this.ezetapWIFIUrl = ezetapWIFIUrl;
    }

    public Boolean getFMCGReturnsOTPRequired() {
        return fMCGReturnsOTPRequired;
    }

    public void setFMCGReturnsOTPRequired(Boolean fMCGReturnsOTPRequired) {
        this.fMCGReturnsOTPRequired = fMCGReturnsOTPRequired;
    }

    public String getFMCGReturnsStore() {
        return fMCGReturnsStore;
    }

    public void setFMCGReturnsStore(String fMCGReturnsStore) {
        this.fMCGReturnsStore = fMCGReturnsStore;
    }

    public String getFunctionalityProfile() {
        return functionalityProfile;
    }

    public void setFunctionalityProfile(String functionalityProfile) {
        this.functionalityProfile = functionalityProfile;
    }

    public List<String> getHDOrderCODEnableCorp() {
        return hDOrderCODEnableCorp;
    }

    public void setHDOrderCODEnableCorp(List<String> hDOrderCODEnableCorp) {
        this.hDOrderCODEnableCorp = hDOrderCODEnableCorp;
    }

    public Integer getHealingCardActivationMinAmount() {
        return healingCardActivationMinAmount;
    }

    public void setHealingCardActivationMinAmount(Integer healingCardActivationMinAmount) {
        this.healingCardActivationMinAmount = healingCardActivationMinAmount;
    }

    public String getHealingCardCorpId() {
        return healingCardCorpId;
    }

    public void setHealingCardCorpId(String healingCardCorpId) {
        this.healingCardCorpId = healingCardCorpId;
    }

    public String getHealingCardItemId() {
        return healingCardItemId;
    }

    public void setHealingCardItemId(String healingCardItemId) {
        this.healingCardItemId = healingCardItemId;
    }

    public Integer getHealingCardMaxAmount() {
        return healingCardMaxAmount;
    }

    public void setHealingCardMaxAmount(Integer healingCardMaxAmount) {
        this.healingCardMaxAmount = healingCardMaxAmount;
    }

    public String getHealingCardOtpProcessUrl() {
        return healingCardOtpProcessUrl;
    }

    public void setHealingCardOtpProcessUrl(String healingCardOtpProcessUrl) {
        this.healingCardOtpProcessUrl = healingCardOtpProcessUrl;
    }

    public Integer getHealingCardReloadMinAmount() {
        return healingCardReloadMinAmount;
    }

    public void setHealingCardReloadMinAmount(Integer healingCardReloadMinAmount) {
        this.healingCardReloadMinAmount = healingCardReloadMinAmount;
    }

    public String getHealingCardURL() {
        return healingCardURL;
    }

    public void setHealingCardURL(String healingCardURL) {
        this.healingCardURL = healingCardURL;
    }

    public String getHomeDeliveryCode() {
        return homeDeliveryCode;
    }

    public void setHomeDeliveryCode(String homeDeliveryCode) {
        this.homeDeliveryCode = homeDeliveryCode;
    }

    public Boolean getISA5BillPrinter() {
        return iSA5BillPrinter;
    }

    public void setISA5BillPrinter(Boolean iSA5BillPrinter) {
        this.iSA5BillPrinter = iSA5BillPrinter;
    }

    public Boolean getISAdvancePaymentAllowed() {
        return iSAdvancePaymentAllowed;
    }

    public void setISAdvancePaymentAllowed(Boolean iSAdvancePaymentAllowed) {
        this.iSAdvancePaymentAllowed = iSAdvancePaymentAllowed;
    }

    public Boolean getISAgeGenderPopupRequired() {
        return iSAgeGenderPopupRequired;
    }

    public void setISAgeGenderPopupRequired(Boolean iSAgeGenderPopupRequired) {
        this.iSAgeGenderPopupRequired = iSAgeGenderPopupRequired;
    }

    public Boolean getISAllTerminalReturn() {
        return iSAllTerminalReturn;
    }

    public void setISAllTerminalReturn(Boolean iSAllTerminalReturn) {
        this.iSAllTerminalReturn = iSAllTerminalReturn;
    }

    public Boolean getISBillingPaymentAllowed() {
        return iSBillingPaymentAllowed;
    }

    public void setISBillingPaymentAllowed(Boolean iSBillingPaymentAllowed) {
        this.iSBillingPaymentAllowed = iSBillingPaymentAllowed;
    }

    public Boolean getISCancelBillReprint() {
        return iSCancelBillReprint;
    }

    public void setISCancelBillReprint(Boolean iSCancelBillReprint) {
        this.iSCancelBillReprint = iSCancelBillReprint;
    }

    public Boolean getISCardBilling() {
        return iSCardBilling;
    }

    public void setISCardBilling(Boolean iSCardBilling) {
        this.iSCardBilling = iSCardBilling;
    }

    public Boolean getISCircleBenefitPopupAllowed() {
        return iSCircleBenefitPopupAllowed;
    }

    public void setISCircleBenefitPopupAllowed(Boolean iSCircleBenefitPopupAllowed) {
        this.iSCircleBenefitPopupAllowed = iSCircleBenefitPopupAllowed;
    }

    public Boolean getISCreditTenderEnable() {
        return iSCreditTenderEnable;
    }

    public void setISCreditTenderEnable(Boolean iSCreditTenderEnable) {
        this.iSCreditTenderEnable = iSCreditTenderEnable;
    }

    public Boolean getIsdc() {
        return isdc;
    }

    public void setIsdc(Boolean isdc) {
        this.isdc = isdc;
    }

    public Boolean getISDCReturnWithoutRef() {
        return iSDCReturnWithoutRef;
    }

    public void setISDCReturnWithoutRef(Boolean iSDCReturnWithoutRef) {
        this.iSDCReturnWithoutRef = iSDCReturnWithoutRef;
    }

    public String getISDVDCEnabled() {
        return iSDVDCEnabled;
    }

    public void setISDVDCEnabled(String iSDVDCEnabled) {
        this.iSDVDCEnabled = iSDVDCEnabled;
    }

    public Boolean getISDoctorLoadSiteWise() {
        return iSDoctorLoadSiteWise;
    }

    public void setISDoctorLoadSiteWise(Boolean iSDoctorLoadSiteWise) {
        this.iSDoctorLoadSiteWise = iSDoctorLoadSiteWise;
    }

    public Boolean getISEzetapActive() {
        return iSEzetapActive;
    }

    public void setISEzetapActive(Boolean iSEzetapActive) {
        this.iSEzetapActive = iSEzetapActive;
    }

    public Boolean getISHBPStore() {
        return iSHBPStore;
    }

    public void setISHBPStore(Boolean iSHBPStore) {
        this.iSHBPStore = iSHBPStore;
    }

    public Boolean getISHDAddress() {
        return iSHDAddress;
    }

    public void setISHDAddress(Boolean iSHDAddress) {
        this.iSHDAddress = iSHDAddress;
    }

    public Boolean getISHDPaymentChange() {
        return iSHDPaymentChange;
    }

    public void setISHDPaymentChange(Boolean iSHDPaymentChange) {
        this.iSHDPaymentChange = iSHDPaymentChange;
    }

    public Boolean getIsho() {
        return isho;
    }

    public void setIsho(Boolean isho) {
        this.isho = isho;
    }

    public Boolean getISMBQtyRequired() {
        return iSMBQtyRequired;
    }

    public void setISMBQtyRequired(Boolean iSMBQtyRequired) {
        this.iSMBQtyRequired = iSMBQtyRequired;
    }

    public Boolean getISMobileNo() {
        return iSMobileNo;
    }

    public void setISMobileNo(Boolean iSMobileNo) {
        this.iSMobileNo = iSMobileNo;
    }

    public Boolean getISNoRefReturnAllowed() {
        return iSNoRefReturnAllowed;
    }

    public void setISNoRefReturnAllowed(Boolean iSNoRefReturnAllowed) {
        this.iSNoRefReturnAllowed = iSNoRefReturnAllowed;
    }

    public Boolean getISNotification() {
        return iSNotification;
    }

    public void setISNotification(Boolean iSNotification) {
        this.iSNotification = iSNotification;
    }

    public Boolean getISOMSOrderTabDefault() {
        return iSOMSOrderTabDefault;
    }

    public void setISOMSOrderTabDefault(Boolean iSOMSOrderTabDefault) {
        this.iSOMSOrderTabDefault = iSOMSOrderTabDefault;
    }

    public Boolean getISONGCSms() {
        return iSONGCSms;
    }

    public void setISONGCSms(Boolean iSONGCSms) {
        this.iSONGCSms = iSONGCSms;
    }

    public Boolean getISOPReturnWithoutRef() {
        return iSOPReturnWithoutRef;
    }

    public void setISOPReturnWithoutRef(Boolean iSOPReturnWithoutRef) {
        this.iSOPReturnWithoutRef = iSOPReturnWithoutRef;
    }

    public Boolean getISPickListAllowed() {
        return iSPickListAllowed;
    }

    public void setISPickListAllowed(Boolean iSPickListAllowed) {
        this.iSPickListAllowed = iSPickListAllowed;
    }

    public String getISReligareWallet() {
        return iSReligareWallet;
    }

    public void setISReligareWallet(String iSReligareWallet) {
        this.iSReligareWallet = iSReligareWallet;
    }

    public Boolean getISRemainder() {
        return iSRemainder;
    }

    public void setISRemainder(Boolean iSRemainder) {
        this.iSRemainder = iSRemainder;
    }

    public Boolean getISReturnOTPRequired() {
        return iSReturnOTPRequired;
    }

    public void setISReturnOTPRequired(Boolean iSReturnOTPRequired) {
        this.iSReturnOTPRequired = iSReturnOTPRequired;
    }

    public Boolean getISServiceRecoveryHomeDelivery() {
        return iSServiceRecoveryHomeDelivery;
    }

    public void setISServiceRecoveryHomeDelivery(Boolean iSServiceRecoveryHomeDelivery) {
        this.iSServiceRecoveryHomeDelivery = iSServiceRecoveryHomeDelivery;
    }

    public Boolean getISSplitBillActive() {
        return iSSplitBillActive;
    }

    public void setISSplitBillActive(Boolean iSSplitBillActive) {
        this.iSSplitBillActive = iSSplitBillActive;
    }

    public Boolean getISStoreCallOrdersOtp() {
        return iSStoreCallOrdersOtp;
    }

    public void setISStoreCallOrdersOtp(Boolean iSStoreCallOrdersOtp) {
        this.iSStoreCallOrdersOtp = iSStoreCallOrdersOtp;
    }

    public Boolean getISTPASeller() {
        return iSTPASeller;
    }

    public void setISTPASeller(Boolean iSTPASeller) {
        this.iSTPASeller = iSTPASeller;
    }

    public Boolean getISUpsellingEnabled() {
        return iSUpsellingEnabled;
    }

    public void setISUpsellingEnabled(Boolean iSUpsellingEnabled) {
        this.iSUpsellingEnabled = iSUpsellingEnabled;
    }

    public Boolean getIsvdcfr() {
        return isvdcfr;
    }

    public void setIsvdcfr(Boolean isvdcfr) {
        this.isvdcfr = isvdcfr;
    }

    public Boolean getIsvdcpr() {
        return isvdcpr;
    }

    public void setIsvdcpr(Boolean isvdcpr) {
        this.isvdcpr = isvdcpr;
    }

    public Boolean getIsAdjustmentEnable() {
        return isAdjustmentEnable;
    }

    public void setIsAdjustmentEnable(Boolean isAdjustmentEnable) {
        this.isAdjustmentEnable = isAdjustmentEnable;
    }

    public Boolean getIsAirportOrderTabAllowed() {
        return isAirportOrderTabAllowed;
    }

    public void setIsAirportOrderTabAllowed(Boolean isAirportOrderTabAllowed) {
        this.isAirportOrderTabAllowed = isAirportOrderTabAllowed;
    }

    public Boolean getIsBTNotification() {
        return isBTNotification;
    }

    public void setIsBTNotification(Boolean isBTNotification) {
        this.isBTNotification = isBTNotification;
    }

    public Boolean getIsBranchTransferEnable() {
        return isBranchTransferEnable;
    }

    public void setIsBranchTransferEnable(Boolean isBranchTransferEnable) {
        this.isBranchTransferEnable = isBranchTransferEnable;
    }

    public Boolean getIsBulkBillingAllowed() {
        return isBulkBillingAllowed;
    }

    public void setIsBulkBillingAllowed(Boolean isBulkBillingAllowed) {
        this.isBulkBillingAllowed = isBulkBillingAllowed;
    }

    public Boolean getIsBulkDiscountAllowed() {
        return isBulkDiscountAllowed;
    }

    public void setIsBulkDiscountAllowed(Boolean isBulkDiscountAllowed) {
        this.isBulkDiscountAllowed = isBulkDiscountAllowed;
    }

    public String getIsClientSearch() {
        return isClientSearch;
    }

    public void setIsClientSearch(String isClientSearch) {
        this.isClientSearch = isClientSearch;
    }

    public Boolean getIsDCReturnWithRefEnable() {
        return isDCReturnWithRefEnable;
    }

    public void setIsDCReturnWithRefEnable(Boolean isDCReturnWithRefEnable) {
        this.isDCReturnWithRefEnable = isDCReturnWithRefEnable;
    }

    public Boolean getIsDuplicateReceiptLogAllowed() {
        return isDuplicateReceiptLogAllowed;
    }

    public void setIsDuplicateReceiptLogAllowed(Boolean isDuplicateReceiptLogAllowed) {
        this.isDuplicateReceiptLogAllowed = isDuplicateReceiptLogAllowed;
    }

    public Boolean getIsFeedbackNotification() {
        return isFeedbackNotification;
    }

    public void setIsFeedbackNotification(Boolean isFeedbackNotification) {
        this.isFeedbackNotification = isFeedbackNotification;
    }

    public Boolean getIsIITDelhi() {
        return isIITDelhi;
    }

    public void setIsIITDelhi(Boolean isIITDelhi) {
        this.isIITDelhi = isIITDelhi;
    }

    public Boolean getIsIPPatientOtpRequired() {
        return isIPPatientOtpRequired;
    }

    public void setIsIPPatientOtpRequired(Boolean isIPPatientOtpRequired) {
        this.isIPPatientOtpRequired = isIPPatientOtpRequired;
    }

    public Boolean getIsInterCompany() {
        return isInterCompany;
    }

    public void setIsInterCompany(Boolean isInterCompany) {
        this.isInterCompany = isInterCompany;
    }

    public Boolean getIsKribhkoStore() {
        return isKribhkoStore;
    }

    public void setIsKribhkoStore(Boolean isKribhkoStore) {
        this.isKribhkoStore = isKribhkoStore;
    }

    public Boolean getIsNITStockUplaod() {
        return isNITStockUplaod;
    }

    public void setIsNITStockUplaod(Boolean isNITStockUplaod) {
        this.isNITStockUplaod = isNITStockUplaod;
    }

    public Boolean getIsNPSMsgTrigger() {
        return isNPSMsgTrigger;
    }

    public void setIsNPSMsgTrigger(Boolean isNPSMsgTrigger) {
        this.isNPSMsgTrigger = isNPSMsgTrigger;
    }

    public Boolean getIsNonFMCGItemDisplay() {
        return isNonFMCGItemDisplay;
    }

    public void setIsNonFMCGItemDisplay(Boolean isNonFMCGItemDisplay) {
        this.isNonFMCGItemDisplay = isNonFMCGItemDisplay;
    }

    public Boolean getIsOMSBatchProcess() {
        return isOMSBatchProcess;
    }

    public void setIsOMSBatchProcess(Boolean isOMSBatchProcess) {
        this.isOMSBatchProcess = isOMSBatchProcess;
    }

    public Boolean getIsOMSHomeDeliveryConversation() {
        return isOMSHomeDeliveryConversation;
    }

    public void setIsOMSHomeDeliveryConversation(Boolean isOMSHomeDeliveryConversation) {
        this.isOMSHomeDeliveryConversation = isOMSHomeDeliveryConversation;
    }

    public Boolean getIsOPReturnWithRefEnable() {
        return isOPReturnWithRefEnable;
    }

    public void setIsOPReturnWithRefEnable(Boolean isOPReturnWithRefEnable) {
        this.isOPReturnWithRefEnable = isOPReturnWithRefEnable;
    }

    public Boolean getIsPinelabActive() {
        return isPinelabActive;
    }

    public void setIsPinelabActive(Boolean isPinelabActive) {
        this.isPinelabActive = isPinelabActive;
    }

    public Boolean getIsSalesTab() {
        return isSalesTab;
    }

    public void setIsSalesTab(Boolean isSalesTab) {
        this.isSalesTab = isSalesTab;
    }

    public Boolean getIsStationaryStockCheck() {
        return isStationaryStockCheck;
    }

    public void setIsStationaryStockCheck(Boolean isStationaryStockCheck) {
        this.isStationaryStockCheck = isStationaryStockCheck;
    }

    public String getLastBillDate() {
        return lastBillDate;
    }

    public void setLastBillDate(String lastBillDate) {
        this.lastBillDate = lastBillDate;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public Integer getLooseDamagAmount() {
        return looseDamagAmount;
    }

    public void setLooseDamagAmount(Integer looseDamagAmount) {
        this.looseDamagAmount = looseDamagAmount;
    }

    public String getLooseDamagSite() {
        return looseDamagSite;
    }

    public void setLooseDamagSite(String looseDamagSite) {
        this.looseDamagSite = looseDamagSite;
    }

    public Integer getMPOSMaxOrderAllowed() {
        return mPOSMaxOrderAllowed;
    }

    public void setMPOSMaxOrderAllowed(Integer mPOSMaxOrderAllowed) {
        this.mPOSMaxOrderAllowed = mPOSMaxOrderAllowed;
    }

    public String getMPOSVersion() {
        return mPOSVersion;
    }

    public void setMPOSVersion(String mPOSVersion) {
        this.mPOSVersion = mPOSVersion;
    }

    public Boolean getManualBillModification() {
        return manualBillModification;
    }

    public void setManualBillModification(Boolean manualBillModification) {
        this.manualBillModification = manualBillModification;
    }

    public Integer getManualBillingAllowHours() {
        return manualBillingAllowHours;
    }

    public void setManualBillingAllowHours(Integer manualBillingAllowHours) {
        this.manualBillingAllowHours = manualBillingAllowHours;
    }

    public Integer getMaxBulkOMSOrders() {
        return maxBulkOMSOrders;
    }

    public void setMaxBulkOMSOrders(Integer maxBulkOMSOrders) {
        this.maxBulkOMSOrders = maxBulkOMSOrders;
    }

    public Integer getMaxDonationAllowed() {
        return maxDonationAllowed;
    }

    public void setMaxDonationAllowed(Integer maxDonationAllowed) {
        this.maxDonationAllowed = maxDonationAllowed;
    }

    public Boolean getMaxDonationRequiredAlways() {
        return maxDonationRequiredAlways;
    }

    public void setMaxDonationRequiredAlways(Boolean maxDonationRequiredAlways) {
        this.maxDonationRequiredAlways = maxDonationRequiredAlways;
    }

    public Integer getMediassistCancelDays() {
        return mediassistCancelDays;
    }

    public void setMediassistCancelDays(Integer mediassistCancelDays) {
        this.mediassistCancelDays = mediassistCancelDays;
    }

    public Integer getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(Integer minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public String getNormalSale() {
        return normalSale;
    }

    public void setNormalSale(String normalSale) {
        this.normalSale = normalSale;
    }

    public Integer getNotificationFrequecy() {
        return notificationFrequecy;
    }

    public void setNotificationFrequecy(Integer notificationFrequecy) {
        this.notificationFrequecy = notificationFrequecy;
    }

    public String getOMSDefaultOtpValue() {
        return oMSDefaultOtpValue;
    }

    public void setOMSDefaultOtpValue(String oMSDefaultOtpValue) {
        this.oMSDefaultOtpValue = oMSDefaultOtpValue;
    }

    public Boolean getOMSDefaultOtpValueAllowed() {
        return oMSDefaultOtpValueAllowed;
    }

    public void setOMSDefaultOtpValueAllowed(Boolean oMSDefaultOtpValueAllowed) {
        this.oMSDefaultOtpValueAllowed = oMSDefaultOtpValueAllowed;
    }

    public Integer getOMSExpiryDays() {
        return oMSExpiryDays;
    }

    public void setOMSExpiryDays(Integer oMSExpiryDays) {
        this.oMSExpiryDays = oMSExpiryDays;
    }

    public Integer getOMSPopupDashBoardFrequency() {
        return oMSPopupDashBoardFrequency;
    }

    public void setOMSPopupDashBoardFrequency(Integer oMSPopupDashBoardFrequency) {
        this.oMSPopupDashBoardFrequency = oMSPopupDashBoardFrequency;
    }

    public Boolean getOMSPopupDashBoardNotification() {
        return oMSPopupDashBoardNotification;
    }

    public void setOMSPopupDashBoardNotification(Boolean oMSPopupDashBoardNotification) {
        this.oMSPopupDashBoardNotification = oMSPopupDashBoardNotification;
    }

    public String getOMSTatApiKey() {
        return oMSTatApiKey;
    }

    public void setOMSTatApiKey(String oMSTatApiKey) {
        this.oMSTatApiKey = oMSTatApiKey;
    }

    public String getOMSTatURL() {
        return oMSTatURL;
    }

    public void setOMSTatURL(String oMSTatURL) {
        this.oMSTatURL = oMSTatURL;
    }

    public String getOMSUrl() {
        return oMSUrl;
    }

    public void setOMSUrl(String oMSUrl) {
        this.oMSUrl = oMSUrl;
    }

    public Boolean getOPReturnWithoutRefOTPRequired() {
        return oPReturnWithoutRefOTPRequired;
    }

    public void setOPReturnWithoutRefOTPRequired(Boolean oPReturnWithoutRefOTPRequired) {
        this.oPReturnWithoutRefOTPRequired = oPReturnWithoutRefOTPRequired;
    }

    public Boolean getOPReturnsOTPRequired() {
        return oPReturnsOTPRequired;
    }

    public void setOPReturnsOTPRequired(Boolean oPReturnsOTPRequired) {
        this.oPReturnsOTPRequired = oPReturnsOTPRequired;
    }

    public String getOtpurl() {
        return otpurl;
    }

    public void setOtpurl(String otpurl) {
        this.otpurl = otpurl;
    }

    public String getOneApolloURL() {
        return oneApolloURL;
    }

    public void setOneApolloURL(String oneApolloURL) {
        this.oneApolloURL = oneApolloURL;
    }

    public String getOnlineCancelUpdateURL() {
        return onlineCancelUpdateURL;
    }

    public void setOnlineCancelUpdateURL(String onlineCancelUpdateURL) {
        this.onlineCancelUpdateURL = onlineCancelUpdateURL;
    }

    public String getOnlineGetBatchURL() {
        return onlineGetBatchURL;
    }

    public void setOnlineGetBatchURL(String onlineGetBatchURL) {
        this.onlineGetBatchURL = onlineGetBatchURL;
    }

    public String getOnlineGetItemURL() {
        return onlineGetItemURL;
    }

    public void setOnlineGetItemURL(String onlineGetItemURL) {
        this.onlineGetItemURL = onlineGetItemURL;
    }

    public String getOnlineOrderURL() {
        return onlineOrderURL;
    }

    public void setOnlineOrderURL(String onlineOrderURL) {
        this.onlineOrderURL = onlineOrderURL;
    }

    public Integer getPOSExpiryDays() {
        return pOSExpiryDays;
    }

    public void setPOSExpiryDays(Integer pOSExpiryDays) {
        this.pOSExpiryDays = pOSExpiryDays;
    }

    public Boolean getParkTransactionAllowed() {
        return parkTransactionAllowed;
    }

    public void setParkTransactionAllowed(Boolean parkTransactionAllowed) {
        this.parkTransactionAllowed = parkTransactionAllowed;
    }

    public Integer getPasswordExpiry() {
        return passwordExpiry;
    }

    public void setPasswordExpiry(Integer passwordExpiry) {
        this.passwordExpiry = passwordExpiry;
    }

    public String getPayBackCorpCode() {
        return payBackCorpCode;
    }

    public void setPayBackCorpCode(String payBackCorpCode) {
        this.payBackCorpCode = payBackCorpCode;
    }

    public Double getPaybackRoundOffPaisa() {
        return paybackRoundOffPaisa;
    }

    public void setPaybackRoundOffPaisa(Double paybackRoundOffPaisa) {
        this.paybackRoundOffPaisa = paybackRoundOffPaisa;
    }

    public Boolean getPharmaReturnsOTPRequired() {
        return pharmaReturnsOTPRequired;
    }

    public void setPharmaReturnsOTPRequired(Boolean pharmaReturnsOTPRequired) {
        this.pharmaReturnsOTPRequired = pharmaReturnsOTPRequired;
    }

    public String getPharmaReturnsStore() {
        return pharmaReturnsStore;
    }

    public void setPharmaReturnsStore(String pharmaReturnsStore) {
        this.pharmaReturnsStore = pharmaReturnsStore;
    }

    public String getPlutusWIFIUrl() {
        return plutusWIFIUrl;
    }

    public void setPlutusWIFIUrl(String plutusWIFIUrl) {
        this.plutusWIFIUrl = plutusWIFIUrl;
    }

    public String getPriceChangeURL() {
        return priceChangeURL;
    }

    public void setPriceChangeURL(String priceChangeURL) {
        this.priceChangeURL = priceChangeURL;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRemoveAddTender() {
        return removeAddTender;
    }

    public void setRemoveAddTender(String removeAddTender) {
        this.removeAddTender = removeAddTender;
    }

    public String getReprintDateAllowed() {
        return reprintDateAllowed;
    }

    public void setReprintDateAllowed(String reprintDateAllowed) {
        this.reprintDateAllowed = reprintDateAllowed;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRestrictedItemsURL() {
        return restrictedItemsURL;
    }

    public void setRestrictedItemsURL(String restrictedItemsURL) {
        this.restrictedItemsURL = restrictedItemsURL;
    }

    public Integer getReturnAllowedDays() {
        return returnAllowedDays;
    }

    public void setReturnAllowedDays(Integer returnAllowedDays) {
        this.returnAllowedDays = returnAllowedDays;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getScratchcardurl() {
        return scratchcardurl;
    }

    public void setScratchcardurl(String scratchcardurl) {
        this.scratchcardurl = scratchcardurl;
    }

    public String getSmsapi() {
        return smsapi;
    }

    public void setSmsapi(String smsapi) {
        this.smsapi = smsapi;
    }

    public String getSMSPayRequestType() {
        return sMSPayRequestType;
    }

    public void setSMSPayRequestType(String sMSPayRequestType) {
        this.sMSPayRequestType = sMSPayRequestType;
    }

    public String getSMSPayURL() {
        return sMSPayURL;
    }

    public void setSMSPayURL(String sMSPayURL) {
        this.sMSPayURL = sMSPayURL;
    }

    public String getStockurl() {
        return stockurl;
    }

    public void setStockurl(String stockurl) {
        this.stockurl = stockurl;
    }

    public String getSalesTrackingURL() {
        return salesTrackingURL;
    }

    public void setSalesTrackingURL(String salesTrackingURL) {
        this.salesTrackingURL = salesTrackingURL;
    }

    public String getSendGlobalMessageURL() {
        return sendGlobalMessageURL;
    }

    public void setSendGlobalMessageURL(String sendGlobalMessageURL) {
        this.sendGlobalMessageURL = sendGlobalMessageURL;
    }

    public Integer getSplitBillAmount() {
        return splitBillAmount;
    }

    public void setSplitBillAmount(Integer splitBillAmount) {
        this.splitBillAmount = splitBillAmount;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStationaryStore() {
        return stationaryStore;
    }

    public void setStationaryStore(String stationaryStore) {
        this.stationaryStore = stationaryStore;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getSuspendDeleteDays() {
        return suspendDeleteDays;
    }

    public void setSuspendDeleteDays(Integer suspendDeleteDays) {
        this.suspendDeleteDays = suspendDeleteDays;
    }

    public Object getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(Object terminalID) {
        this.terminalID = terminalID;
    }

    public String getUhidhbpurl() {
        return uhidhbpurl;
    }

    public void setUhidhbpurl(String uhidhbpurl) {
        this.uhidhbpurl = uhidhbpurl;
    }

    public Object getUserCode() {
        return userCode;
    }

    public void setUserCode(Object userCode) {
        this.userCode = userCode;
    }

    public HBPConfig getHBPConfig() {
        return hBPConfig;
    }

    public void setHBPConfig(HBPConfig hBPConfig) {
        this.hBPConfig = hBPConfig;
    }

    public List<OMSCustomerType> getOMSCustomerType() {
        return oMSCustomerType;
    }

    public void setOMSCustomerType(List<OMSCustomerType> oMSCustomerType) {
        this.oMSCustomerType = oMSCustomerType;
    }

    public List<OMSVendorWiseConfigration> getOMSVendorWiseConfigration() {
        return oMSVendorWiseConfigration;
    }

    public void setOMSVendorWiseConfigration(List<OMSVendorWiseConfigration> oMSVendorWiseConfigration) {
        this.oMSVendorWiseConfigration = oMSVendorWiseConfigration;
    }

    public Object getTenderTypeList() {
        return tenderTypeList;
    }

    public void setTenderTypeList(Object tenderTypeList) {
        this.tenderTypeList = tenderTypeList;
    }

    public Object getTrackingConfigration() {
        return trackingConfigration;
    }

    public void setTrackingConfigration(Object trackingConfigration) {
        this.trackingConfigration = trackingConfigration;
    }

    public class HBPConfig {

        @SerializedName("BillReturnOTPMobileNo")
        @Expose
        private Integer billReturnOTPMobileNo;
        @SerializedName("BillReturnOTPRequired")
        @Expose
        private Boolean billReturnOTPRequired;
        @SerializedName("BillReturnReason")
        @Expose
        private Boolean billReturnReason;
        @SerializedName("ISAutoNewCustRegAllowed")
        @Expose
        private Boolean iSAutoNewCustRegAllowed;
        @SerializedName("ISNormalSalesTrackingRefAllowed")
        @Expose
        private Boolean iSNormalSalesTrackingRefAllowed;
        @SerializedName("ISPatientRequired")
        @Expose
        private Boolean iSPatientRequired;
        @SerializedName("ISSOMSCustomerEnable")
        @Expose
        private Boolean iSSOMSCustomerEnable;
        @SerializedName("ISSVPStore")
        @Expose
        private Boolean iSSVPStore;
        @SerializedName("ISTerminalCardBilling")
        @Expose
        private Boolean iSTerminalCardBilling;
        @SerializedName("ISTerminalCardPayment")
        @Expose
        private Boolean iSTerminalCardPayment;
        @SerializedName("ISTerminalCashDrawer")
        @Expose
        private Boolean iSTerminalCashDrawer;
        @SerializedName("ISTokenSystem")
        @Expose
        private Boolean iSTokenSystem;
        @SerializedName("ISValidatePassword")
        @Expose
        private Boolean iSValidatePassword;
        @SerializedName("PLPromoAmount")
        @Expose
        private Integer pLPromoAmount;
        @SerializedName("PLPromoCorpCode")
        @Expose
        private Object pLPromoCorpCode;
        @SerializedName("PLPromoOffer")
        @Expose
        private Boolean pLPromoOffer;
        @SerializedName("PatientTypeMaster")
        @Expose
        private Boolean patientTypeMaster;
        @SerializedName("RequestStatus")
        @Expose
        private Integer requestStatus;
        @SerializedName("ReturnMessage")
        @Expose
        private String returnMessage;
        @SerializedName("UHIDBilling")
        @Expose
        private Boolean uHIDBilling;

        public Integer getBillReturnOTPMobileNo() {
            return billReturnOTPMobileNo;
        }

        public void setBillReturnOTPMobileNo(Integer billReturnOTPMobileNo) {
            this.billReturnOTPMobileNo = billReturnOTPMobileNo;
        }

        public Boolean getBillReturnOTPRequired() {
            return billReturnOTPRequired;
        }

        public void setBillReturnOTPRequired(Boolean billReturnOTPRequired) {
            this.billReturnOTPRequired = billReturnOTPRequired;
        }

        public Boolean getBillReturnReason() {
            return billReturnReason;
        }

        public void setBillReturnReason(Boolean billReturnReason) {
            this.billReturnReason = billReturnReason;
        }

        public Boolean getISAutoNewCustRegAllowed() {
            return iSAutoNewCustRegAllowed;
        }

        public void setISAutoNewCustRegAllowed(Boolean iSAutoNewCustRegAllowed) {
            this.iSAutoNewCustRegAllowed = iSAutoNewCustRegAllowed;
        }

        public Boolean getISNormalSalesTrackingRefAllowed() {
            return iSNormalSalesTrackingRefAllowed;
        }

        public void setISNormalSalesTrackingRefAllowed(Boolean iSNormalSalesTrackingRefAllowed) {
            this.iSNormalSalesTrackingRefAllowed = iSNormalSalesTrackingRefAllowed;
        }

        public Boolean getISPatientRequired() {
            return iSPatientRequired;
        }

        public void setISPatientRequired(Boolean iSPatientRequired) {
            this.iSPatientRequired = iSPatientRequired;
        }

        public Boolean getISSOMSCustomerEnable() {
            return iSSOMSCustomerEnable;
        }

        public void setISSOMSCustomerEnable(Boolean iSSOMSCustomerEnable) {
            this.iSSOMSCustomerEnable = iSSOMSCustomerEnable;
        }

        public Boolean getISSVPStore() {
            return iSSVPStore;
        }

        public void setISSVPStore(Boolean iSSVPStore) {
            this.iSSVPStore = iSSVPStore;
        }

        public Boolean getISTerminalCardBilling() {
            return iSTerminalCardBilling;
        }

        public void setISTerminalCardBilling(Boolean iSTerminalCardBilling) {
            this.iSTerminalCardBilling = iSTerminalCardBilling;
        }

        public Boolean getISTerminalCardPayment() {
            return iSTerminalCardPayment;
        }

        public void setISTerminalCardPayment(Boolean iSTerminalCardPayment) {
            this.iSTerminalCardPayment = iSTerminalCardPayment;
        }

        public Boolean getISTerminalCashDrawer() {
            return iSTerminalCashDrawer;
        }

        public void setISTerminalCashDrawer(Boolean iSTerminalCashDrawer) {
            this.iSTerminalCashDrawer = iSTerminalCashDrawer;
        }

        public Boolean getISTokenSystem() {
            return iSTokenSystem;
        }

        public void setISTokenSystem(Boolean iSTokenSystem) {
            this.iSTokenSystem = iSTokenSystem;
        }

        public Boolean getISValidatePassword() {
            return iSValidatePassword;
        }

        public void setISValidatePassword(Boolean iSValidatePassword) {
            this.iSValidatePassword = iSValidatePassword;
        }

        public Integer getPLPromoAmount() {
            return pLPromoAmount;
        }

        public void setPLPromoAmount(Integer pLPromoAmount) {
            this.pLPromoAmount = pLPromoAmount;
        }

        public Object getPLPromoCorpCode() {
            return pLPromoCorpCode;
        }

        public void setPLPromoCorpCode(Object pLPromoCorpCode) {
            this.pLPromoCorpCode = pLPromoCorpCode;
        }

        public Boolean getPLPromoOffer() {
            return pLPromoOffer;
        }

        public void setPLPromoOffer(Boolean pLPromoOffer) {
            this.pLPromoOffer = pLPromoOffer;
        }

        public Boolean getPatientTypeMaster() {
            return patientTypeMaster;
        }

        public void setPatientTypeMaster(Boolean patientTypeMaster) {
            this.patientTypeMaster = patientTypeMaster;
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

        public Boolean getUHIDBilling() {
            return uHIDBilling;
        }

        public void setUHIDBilling(Boolean uHIDBilling) {
            this.uHIDBilling = uHIDBilling;
        }

    }

    public class OMSCustomerType {

        @SerializedName("Checked")
        @Expose
        private Boolean checked;
        @SerializedName("CustomerType")
        @Expose
        private String customerType;

        public Boolean getChecked() {
            return checked;
        }

        public void setChecked(Boolean checked) {
            this.checked = checked;
        }

        public String getCustomerType() {
            return customerType;
        }

        public void setCustomerType(String customerType) {
            this.customerType = customerType;
        }

    }

    public class OMSVendorWiseConfigration {

        @SerializedName("AllowBulkBilling")
        @Expose
        private Boolean allowBulkBilling;
        @SerializedName("AllowCancellation")
        @Expose
        private Boolean allowCancellation;
        @SerializedName("AllowChangeQTY")
        @Expose
        private Boolean allowChangeQTY;
        @SerializedName("AllowLineCancellation")
        @Expose
        private Boolean allowLineCancellation;
        @SerializedName("AllowMultiBatch")
        @Expose
        private Boolean allowMultiBatch;
        @SerializedName("AllowOMSPriceBilling")
        @Expose
        private Boolean allowOMSPriceBilling;
        @SerializedName("AllowPartialQTY")
        @Expose
        private Boolean allowPartialQTY;
        @SerializedName("AllowPickedOrderBilling")
        @Expose
        private Boolean allowPickedOrderBilling;
        @SerializedName("AllowProductAdd")
        @Expose
        private Boolean allowProductAdd;
        @SerializedName("AllowShippingLabelPrint")
        @Expose
        private Boolean allowShippingLabelPrint;
        @SerializedName("AllowSubstitude")
        @Expose
        private Boolean allowSubstitude;
        @SerializedName("AllowVoidProduct")
        @Expose
        private Boolean allowVoidProduct;
        @SerializedName("AllowVoidTender")
        @Expose
        private Boolean allowVoidTender;
        @SerializedName("CorpCode")
        @Expose
        private String corpCode;
        @SerializedName("RequestStatus")
        @Expose
        private Integer requestStatus;
        @SerializedName("ReturnMessage")
        @Expose
        private String returnMessage;

        public Boolean getAllowBulkBilling() {
            return allowBulkBilling;
        }

        public void setAllowBulkBilling(Boolean allowBulkBilling) {
            this.allowBulkBilling = allowBulkBilling;
        }

        public Boolean getAllowCancellation() {
            return allowCancellation;
        }

        public void setAllowCancellation(Boolean allowCancellation) {
            this.allowCancellation = allowCancellation;
        }

        public Boolean getAllowChangeQTY() {
            return allowChangeQTY;
        }

        public void setAllowChangeQTY(Boolean allowChangeQTY) {
            this.allowChangeQTY = allowChangeQTY;
        }

        public Boolean getAllowLineCancellation() {
            return allowLineCancellation;
        }

        public void setAllowLineCancellation(Boolean allowLineCancellation) {
            this.allowLineCancellation = allowLineCancellation;
        }

        public Boolean getAllowMultiBatch() {
            return allowMultiBatch;
        }

        public void setAllowMultiBatch(Boolean allowMultiBatch) {
            this.allowMultiBatch = allowMultiBatch;
        }

        public Boolean getAllowOMSPriceBilling() {
            return allowOMSPriceBilling;
        }

        public void setAllowOMSPriceBilling(Boolean allowOMSPriceBilling) {
            this.allowOMSPriceBilling = allowOMSPriceBilling;
        }

        public Boolean getAllowPartialQTY() {
            return allowPartialQTY;
        }

        public void setAllowPartialQTY(Boolean allowPartialQTY) {
            this.allowPartialQTY = allowPartialQTY;
        }

        public Boolean getAllowPickedOrderBilling() {
            return allowPickedOrderBilling;
        }

        public void setAllowPickedOrderBilling(Boolean allowPickedOrderBilling) {
            this.allowPickedOrderBilling = allowPickedOrderBilling;
        }

        public Boolean getAllowProductAdd() {
            return allowProductAdd;
        }

        public void setAllowProductAdd(Boolean allowProductAdd) {
            this.allowProductAdd = allowProductAdd;
        }

        public Boolean getAllowShippingLabelPrint() {
            return allowShippingLabelPrint;
        }

        public void setAllowShippingLabelPrint(Boolean allowShippingLabelPrint) {
            this.allowShippingLabelPrint = allowShippingLabelPrint;
        }

        public Boolean getAllowSubstitude() {
            return allowSubstitude;
        }

        public void setAllowSubstitude(Boolean allowSubstitude) {
            this.allowSubstitude = allowSubstitude;
        }

        public Boolean getAllowVoidProduct() {
            return allowVoidProduct;
        }

        public void setAllowVoidProduct(Boolean allowVoidProduct) {
            this.allowVoidProduct = allowVoidProduct;
        }

        public Boolean getAllowVoidTender() {
            return allowVoidTender;
        }

        public void setAllowVoidTender(Boolean allowVoidTender) {
            this.allowVoidTender = allowVoidTender;
        }

        public String getCorpCode() {
            return corpCode;
        }

        public void setCorpCode(String corpCode) {
            this.corpCode = corpCode;
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

    }
}
