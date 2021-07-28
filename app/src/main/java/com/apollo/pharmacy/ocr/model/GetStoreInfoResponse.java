package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetStoreInfoResponse {
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
        @SerializedName("PaymentMode")
        private PaymentModeEntity PaymentMode;
        @Expose
        @SerializedName("DeliveryMode")
        private DeliveryModeEntity DeliveryMode;
        @Expose
        @SerializedName("IS_ACTIVE")
        private boolean IS_ACTIVE;
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
        @SerializedName("ADDRESS")
        private String ADDRESS;
        @Expose
        @SerializedName("STORE")
        private String STORE;
        @Expose
        @SerializedName("KIOSK_NAME")
        private String KIOSK_NAME;
        @Expose
        @SerializedName("KIOSK_ID")
        private String KIOSK_ID;
        @Expose
        @SerializedName("AT_KiOSK_DELIVERABLE_TIME")
        private String AT_KiOSK_DELIVERABLE_TIME;
        @Expose
        @SerializedName("AT_HOME_DELIVERABLE_TIME")
        private String AT_HOME_DELIVERABLE_TIME;

        public PaymentModeEntity getPaymentMode() {
            return PaymentMode;
        }

        public DeliveryModeEntity getDeliveryMode() {
            return DeliveryMode;
        }

        public boolean getIS_ACTIVE() {
            return IS_ACTIVE;
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

        public String getADDRESS() {
            return ADDRESS;
        }

        public String getSTORE() {
            return STORE;
        }

        public String getKIOSK_NAME() {
            return KIOSK_NAME;
        }

        public String getKIOSK_ID() {
            return KIOSK_ID;
        }

        public String getAT_KiOSK_DELIVERABLE_TIME() {
            return AT_KiOSK_DELIVERABLE_TIME;
        }

        public String getAT_HOME_DELIVERABLE_TIME() {
            return AT_HOME_DELIVERABLE_TIME;
        }
    }

    public static class PaymentModeEntity {
        @Expose
        @SerializedName("Paytm")
        private boolean Paytm;
        @Expose
        @SerializedName("COD")
        private boolean COD;
        @Expose
        @SerializedName("Card")
        private boolean Card;

        public boolean getPaytm() {
            return Paytm;
        }

        public boolean getCOD() {
            return COD;
        }

        public boolean getCard() {
            return Card;
        }
    }

    public static class DeliveryModeEntity {
        @Expose
        @SerializedName("AtKiosk")
        private boolean AtKiosk;
        @Expose
        @SerializedName("StorePickup")
        private boolean StorePickup;
        @Expose
        @SerializedName("HomeDelivery")
        private boolean HomeDelivery;

        public boolean getAtKiosk() {
            return AtKiosk;
        }

        public boolean getStorePickup() {
            return StorePickup;
        }

        public boolean getHomeDelivery() {
            return HomeDelivery;
        }
    }
}
