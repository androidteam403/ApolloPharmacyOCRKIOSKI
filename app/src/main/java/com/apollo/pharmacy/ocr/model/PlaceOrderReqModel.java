package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceOrderReqModel {
    @Expose
    @SerializedName("tpdetails")
    private TpdetailsEntity tpdetails;

    public void setTpdetails(TpdetailsEntity tpdetails) {
        this.tpdetails = tpdetails;
    }

    public static class TpdetailsEntity {
        @Expose
        @SerializedName("PrescUrl")
        private List<PrescUrlEntity> PrescUrl;
        @Expose
        @SerializedName("ItemDetails")
        private List<ItemDetailsEntity> ItemDetails;
        @Expose
        @SerializedName("PaymentDetails")
        private PaymentDetailsEntity PaymentDetails;
        @Expose
        @SerializedName("CustomerDetails")
        private CustomerDetailsEntity CustomerDetails;
        @Expose
        @SerializedName("RequestType")
        private String RequestType;
        @Expose
        @SerializedName("OrderDate")
        private String OrderDate;
        @Expose
        @SerializedName("CouponCode")
        private String CouponCode;
        @Expose
        @SerializedName("OrderType")
        private String OrderType;
        @Expose
        @SerializedName("TAT")
        private String TAT;
        @Expose
        @SerializedName("UserId")
        private String UserId;
        @Expose
        @SerializedName("StateCode")
        private String StateCode;
        @Expose
        @SerializedName("DotorName")
        private String DotorName;
        @Expose
        @SerializedName("VendorName")
        private String VendorName;
        @Expose
        @SerializedName("PaymentMethod")
        private String PaymentMethod;
        @Expose
        @SerializedName("ShippingMethod")
        private String ShippingMethod;
        @Expose
        @SerializedName("ShopId")
        private String ShopId;
        @Expose
        @SerializedName("OrderId")
        private String OrderId;

        public void setPrescUrl(List<PrescUrlEntity> PrescUrl) {
            this.PrescUrl = PrescUrl;
        }

        public void setItemDetails(List<ItemDetailsEntity> ItemDetails) {
            this.ItemDetails = ItemDetails;
        }

        public void setPaymentDetails(PaymentDetailsEntity PaymentDetails) {
            this.PaymentDetails = PaymentDetails;
        }

        public void setCustomerDetails(CustomerDetailsEntity CustomerDetails) {
            this.CustomerDetails = CustomerDetails;
        }

        public void setRequestType(String RequestType) {
            this.RequestType = RequestType;
        }

        public void setOrderDate(String OrderDate) {
            this.OrderDate = OrderDate;
        }

        public void setCouponCode(String CouponCode) {
            this.CouponCode = CouponCode;
        }

        public void setOrderType(String OrderType) {
            this.OrderType = OrderType;
        }

        public void setTAT(String TAT) {
            this.TAT = TAT;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public void setStateCode(String StateCode) {
            this.StateCode = StateCode;
        }

        public void setDotorName(String DotorName) {
            this.DotorName = DotorName;
        }

        public void setVendorName(String VendorName) {
            this.VendorName = VendorName;
        }

        public void setPaymentMethod(String PaymentMethod) {
            this.PaymentMethod = PaymentMethod;
        }

        public void setShippingMethod(String ShippingMethod) {
            this.ShippingMethod = ShippingMethod;
        }

        public void setShopId(String ShopId) {
            this.ShopId = ShopId;
        }

        public void setOrderId(String OrderId) {
            this.OrderId = OrderId;
        }
    }

    public static class PrescUrlEntity {
        @Expose
        @SerializedName("url")
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ItemDetailsEntity {
        @Expose
        @SerializedName("Status")
        private boolean Status;
        @Expose
        @SerializedName("Price")
        private double Price;
        @Expose
        @SerializedName("Pack")
        private String Pack;
        @Expose
        @SerializedName("MOU")
        private int MOU;
        @Expose
        @SerializedName("Qty")
        private int Qty;
        @Expose
        @SerializedName("ItemName")
        private String ItemName;
        @Expose
        @SerializedName("ItemID")
        private String ItemID;

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double price) {
            Price = price;
        }

        public void setPack(String Pack) {
            this.Pack = Pack;
        }

        public void setMOU(int MOU) {
            this.MOU = MOU;
        }

        public void setQty(int Qty) {
            this.Qty = Qty;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public void setItemID(String ItemID) {
            this.ItemID = ItemID;
        }
    }

    public static class PaymentDetailsEntity {
        @Expose
        @SerializedName("PaymentOrderId")
        private String PaymentOrderId;
        @Expose
        @SerializedName("PaymentStatus")
        private String PaymentStatus;
        @Expose
        @SerializedName("PaymentSource")
        private String PaymentSource;
        @Expose
        @SerializedName("TotalAmount")
        private String TotalAmount;

        public void setPaymentOrderId(String PaymentOrderId) {
            this.PaymentOrderId = PaymentOrderId;
        }

        public void setPaymentStatus(String PaymentStatus) {
            this.PaymentStatus = PaymentStatus;
        }

        public void setPaymentSource(String PaymentSource) {
            this.PaymentSource = PaymentSource;
        }

        public void setTotalAmount(String TotalAmount) {
            this.TotalAmount = TotalAmount;
        }
    }

    public static class CustomerDetailsEntity {
        @Expose
        @SerializedName("PatientName")
        private String PatientName;
        @Expose
        @SerializedName("CardNo")
        private String CardNo;
        @Expose
        @SerializedName("Age")
        private int Age;
        @Expose
        @SerializedName("MailId")
        private String MailId;
        @Expose
        @SerializedName("PostCode")
        private String PostCode;
        @Expose
        @SerializedName("City")
        private String City;
        @Expose
        @SerializedName("UHID")
        private String UHID;
        @Expose
        @SerializedName("LastName")
        private String LastName;
        @Expose
        @SerializedName("FirstName")
        private String FirstName;
        @Expose
        @SerializedName("Del_addr")
        private String Del_addr;
        @Expose
        @SerializedName("Comm_addr")
        private String Comm_addr;
        @Expose
        @SerializedName("MobileNo")
        private String MobileNo;

        public void setPatientName(String PatientName) {
            this.PatientName = PatientName;
        }

        public void setCardNo(String CardNo) {
            this.CardNo = CardNo;
        }

        public void setAge(int Age) {
            this.Age = Age;
        }

        public void setMailId(String MailId) {
            this.MailId = MailId;
        }

        public void setPostCode(String PostCode) {
            this.PostCode = PostCode;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public void setUHID(String UHID) {
            this.UHID = UHID;
        }

        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        public void setDel_addr(String Del_addr) {
            this.Del_addr = Del_addr;
        }

        public void setComm_addr(String Comm_addr) {
            this.Comm_addr = Comm_addr;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }
    }
}
