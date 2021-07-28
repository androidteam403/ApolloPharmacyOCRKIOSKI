package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicabilityResponse {

    @Expose
    @SerializedName("DeviceDetails")
    private List<DeviceDetailsEntity> DeviceDetails;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("status")
    private boolean status;

    public List<DeviceDetailsEntity> getDeviceDetails() {
        return DeviceDetails;
    }

    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }

    public static class DeviceDetailsEntity {
        @Expose
        @SerializedName("SERVECIBILITY_AVALABILE")
        private boolean SERVECIBILITY_AVALABILE;
        @Expose
        @SerializedName("CITY")
        private String CITY;
        @Expose
        @SerializedName("STATE")
        private String STATE;
        @Expose
        @SerializedName("STATE_CODE")
        private String STATE_CODE;
        @Expose
        @SerializedName("PINCODE")
        private String PINCODE;
        @Expose
        @SerializedName("VENDOR")
        private String VENDOR;

        public boolean getSERVECIBILITY_AVALABILE() {
            return SERVECIBILITY_AVALABILE;
        }

        public String getCITY() {
            return CITY;
        }

        public String getSTATE() {
            return STATE;
        }

        public String getSTATE_CODE() {
            return STATE_CODE;
        }

        public String getPINCODE() {
            return PINCODE;
        }

        public String getVENDOR() {
            return VENDOR;
        }
    }
}
