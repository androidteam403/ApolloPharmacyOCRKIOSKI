package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Global_api_response {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("DeviceDetails")
    @Expose
    private DeviceDetails deviceDetails;
    @SerializedName("BUILDDETAILS")
    @Expose
    private BUILDDETAILS bUILDDETAILS;
    @SerializedName("APIS")
    @Expose
    private List<API> aPIS = null;

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

    public DeviceDetails getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public BUILDDETAILS getBUILDDETAILS() {
        return bUILDDETAILS;
    }

    public void setBUILDDETAILS(BUILDDETAILS bUILDDETAILS) {
        this.bUILDDETAILS = bUILDDETAILS;
    }

    public List<API> getAPIS() {
        return aPIS;
    }

    public void setAPIS(List<API> aPIS) {
        this.aPIS = aPIS;
    }

    public static class BUILDDETAILS {
        @SerializedName("APPAVALIBALITY")
        @Expose
        private Boolean aPPAVALIBALITY;
        @SerializedName("AVABILITYMESSAGE")
        @Expose
        private Object aVABILITYMESSAGE;
        @SerializedName("BUILDVERSION")
        @Expose
        private String bUILDVERSION;
        @SerializedName("FORCEDOWNLOAD")
        @Expose
        private Boolean fORCEDOWNLOAD;
        @SerializedName("DOWNLOADURL")
        @Expose
        private Object dOWNLOADURL;
        @SerializedName("BUILDMESSAGE")
        @Expose
        private String bUILDMESSAGE;
        @SerializedName("APOLLOTEXT")
        @Expose
        private Object aPOLLOTEXT;
        @SerializedName("VENDORTEXT")
        @Expose
        private Object vENDORTEXT;
        @SerializedName("APOLLOIMAGE")
        @Expose
        private Object aPOLLOIMAGE;
        @SerializedName("VENDORIMAGE")
        @Expose
        private Object vENDORIMAGE;
        @SerializedName("EMAILVALIDATION")
        @Expose
        private Boolean eMAILVALIDATION;

        public Boolean getAPPAVALIBALITY() {
            return aPPAVALIBALITY;
        }

        public void setAPPAVALIBALITY(Boolean aPPAVALIBALITY) {
            this.aPPAVALIBALITY = aPPAVALIBALITY;
        }

        public Object getAVABILITYMESSAGE() {
            return aVABILITYMESSAGE;
        }

        public void setAVABILITYMESSAGE(Object aVABILITYMESSAGE) {
            this.aVABILITYMESSAGE = aVABILITYMESSAGE;
        }

        public String getBUILDVERSION() {
            return bUILDVERSION;
        }

        public void setBUILDVERSION(String bUILDVERSION) {
            this.bUILDVERSION = bUILDVERSION;
        }

        public Boolean getFORCEDOWNLOAD() {
            return fORCEDOWNLOAD;
        }

        public void setFORCEDOWNLOAD(Boolean fORCEDOWNLOAD) {
            this.fORCEDOWNLOAD = fORCEDOWNLOAD;
        }

        public Object getDOWNLOADURL() {
            return dOWNLOADURL;
        }

        public void setDOWNLOADURL(Object dOWNLOADURL) {
            this.dOWNLOADURL = dOWNLOADURL;
        }

        public String getBUILDMESSAGE() {
            return bUILDMESSAGE;
        }

        public void setBUILDMESSAGE(String bUILDMESSAGE) {
            this.bUILDMESSAGE = bUILDMESSAGE;
        }

        public Object getAPOLLOTEXT() {
            return aPOLLOTEXT;
        }

        public void setAPOLLOTEXT(Object aPOLLOTEXT) {
            this.aPOLLOTEXT = aPOLLOTEXT;
        }

        public Object getVENDORTEXT() {
            return vENDORTEXT;
        }

        public void setVENDORTEXT(Object vENDORTEXT) {
            this.vENDORTEXT = vENDORTEXT;
        }

        public Object getAPOLLOIMAGE() {
            return aPOLLOIMAGE;
        }

        public void setAPOLLOIMAGE(Object aPOLLOIMAGE) {
            this.aPOLLOIMAGE = aPOLLOIMAGE;
        }

        public Object getVENDORIMAGE() {
            return vENDORIMAGE;
        }

        public void setVENDORIMAGE(Object vENDORIMAGE) {
            this.vENDORIMAGE = vENDORIMAGE;
        }

        public Boolean getEMAILVALIDATION() {
            return eMAILVALIDATION;
        }

        public void setEMAILVALIDATION(Boolean eMAILVALIDATION) {
            this.eMAILVALIDATION = eMAILVALIDATION;
        }
    }

    public static class DeviceDetails {
        @SerializedName("DELIVERYMODE")
        @Expose
        private Object dELIVERYMODE;
        @SerializedName("PAYMETTYPE")
        @Expose
        private Object pAYMETTYPE;
        @SerializedName("PRESCRIPTION")
        @Expose
        private Boolean pRESCRIPTION;
        @SerializedName("SEQUENCENUMBER")
        @Expose
        private String sEQUENCENUMBER;
        @SerializedName("VENDORNAME")
        @Expose
        private String vENDORNAME;
        @SerializedName("UHID")
        @Expose
        private Boolean uHID;
        @SerializedName("UHIDMAXLENGTH")
        @Expose
        private String uHIDMAXLENGTH;
        @SerializedName("CHANGEPASSWORD")
        @Expose
        private Boolean cHANGEPASSWORD;
        @SerializedName("CUSTOMERREGISTRATION")
        @Expose
        private Object cUSTOMERREGISTRATION;
        @SerializedName("ADDCUSTOMER")
        @Expose
        private Object aDDCUSTOMER;

        public Object getDELIVERYMODE() {
            return dELIVERYMODE;
        }

        public void setDELIVERYMODE(Object dELIVERYMODE) {
            this.dELIVERYMODE = dELIVERYMODE;
        }

        public Object getPAYMETTYPE() {
            return pAYMETTYPE;
        }

        public void setPAYMETTYPE(Object pAYMETTYPE) {
            this.pAYMETTYPE = pAYMETTYPE;
        }

        public Boolean getPRESCRIPTION() {
            return pRESCRIPTION;
        }

        public void setPRESCRIPTION(Boolean pRESCRIPTION) {
            this.pRESCRIPTION = pRESCRIPTION;
        }

        public String getSEQUENCENUMBER() {
            return sEQUENCENUMBER;
        }

        public void setSEQUENCENUMBER(String sEQUENCENUMBER) {
            this.sEQUENCENUMBER = sEQUENCENUMBER;
        }

        public String getVENDORNAME() {
            return vENDORNAME;
        }

        public void setVENDORNAME(String vENDORNAME) {
            this.vENDORNAME = vENDORNAME;
        }

        public Boolean getUHID() {
            return uHID;
        }

        public void setUHID(Boolean uHID) {
            this.uHID = uHID;
        }

        public String getUHIDMAXLENGTH() {
            return uHIDMAXLENGTH;
        }

        public void setUHIDMAXLENGTH(String uHIDMAXLENGTH) {
            this.uHIDMAXLENGTH = uHIDMAXLENGTH;
        }

        public Boolean getCHANGEPASSWORD() {
            return cHANGEPASSWORD;
        }

        public void setCHANGEPASSWORD(Boolean cHANGEPASSWORD) {
            this.cHANGEPASSWORD = cHANGEPASSWORD;
        }

        public Object getCUSTOMERREGISTRATION() {
            return cUSTOMERREGISTRATION;
        }

        public void setCUSTOMERREGISTRATION(Object cUSTOMERREGISTRATION) {
            this.cUSTOMERREGISTRATION = cUSTOMERREGISTRATION;
        }

        public Object getADDCUSTOMER() {
            return aDDCUSTOMER;
        }

        public void setADDCUSTOMER(Object aDDCUSTOMER) {
            this.aDDCUSTOMER = aDDCUSTOMER;
        }

    }
}
