package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PlaceOrdersRequest {

    @SerializedName("tpdetails")
    @Expose
    private Tpdetails tpdetails;

    public Tpdetails getTpdetails() {
        return tpdetails;
    }

    public void setTpdetails(Tpdetails tpdetails) {
        this.tpdetails = tpdetails;
    }

    public class CustomerDetails {

        @SerializedName("MobileNo")
        @Expose
        private String mobileNo;
        @SerializedName("Comm_addr")
        @Expose
        private String commAddr;
        @SerializedName("Del_addr")
        @Expose
        private String delAddr;
        @SerializedName("FirstName")
        @Expose
        private String firstName;
        @SerializedName("LastName")
        @Expose
        private String lastName;
        @SerializedName("UHID")
        @Expose
        private String uhid;
        @SerializedName("City")
        @Expose
        private String city;
        @SerializedName("PostCode")
        @Expose
        private String postCode;
        @SerializedName("MailId")
        @Expose
        private String mailId;
        @SerializedName("Age")
        @Expose
        private Integer age;
        @SerializedName("CardNo")
        @Expose
        private String cardNo;
        @SerializedName("PatientName")
        @Expose
        private String patientName;
        @SerializedName("Latitude")
        @Expose
        private Integer latitude;
        @SerializedName("Longitude")
        @Expose
        private Integer longitude;

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getCommAddr() {
            return commAddr;
        }

        public void setCommAddr(String commAddr) {
            this.commAddr = commAddr;
        }

        public String getDelAddr() {
            return delAddr;
        }

        public void setDelAddr(String delAddr) {
            this.delAddr = delAddr;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUhid() {
            return uhid;
        }

        public void setUhid(String uhid) {
            this.uhid = uhid;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getMailId() {
            return mailId;
        }

        public void setMailId(String mailId) {
            this.mailId = mailId;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public Integer getLatitude() {
            return latitude;
        }

        public void setLatitude(Integer latitude) {
            this.latitude = latitude;
        }

        public Integer getLongitude() {
            return longitude;
        }

        public void setLongitude(Integer longitude) {
            this.longitude = longitude;
        }

    }

    public static class ItemDetail {

        @SerializedName("ItemID")
        @Expose
        private String itemID;
        @SerializedName("ItemName")
        @Expose
        private String itemName;
        @SerializedName("Qty")
        @Expose
        private Integer qty;
        @SerializedName("MOU")
        @Expose
        private Integer mou;
        @SerializedName("Pack")
        @Expose
        private String pack;
        @SerializedName("Price")
        @Expose
        private Double price;
        @SerializedName("Status")
        @Expose
        private Boolean status;
        @SerializedName("Remarks")
        @Expose
        private String remarks;

        public String getItemID() {
            return itemID;
        }

        public void setItemID(String itemID) {
            this.itemID = itemID;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public Integer getMou() {
            return mou;
        }

        public void setMou(Integer mou) {
            this.mou = mou;
        }

        public String getPack() {
            return pack;
        }

        public void setPack(String pack) {
            this.pack = pack;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

    }

    public class PaymentDetails {

        @SerializedName("TotalAmount")
        @Expose
        private String totalAmount;
        @SerializedName("PaymentSource")
        @Expose
        private String paymentSource;
        @SerializedName("PaymentStatus")
        @Expose
        private String paymentStatus;
        @SerializedName("PaymentOrderId")
        @Expose
        private String paymentOrderId;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getPaymentSource() {
            return paymentSource;
        }

        public void setPaymentSource(String paymentSource) {
            this.paymentSource = paymentSource;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getPaymentOrderId() {
            return paymentOrderId;
        }

        public void setPaymentOrderId(String paymentOrderId) {
            this.paymentOrderId = paymentOrderId;
        }

    }

    public class PrescUrl {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class Tpdetails {

        @SerializedName("OrderId")
        @Expose
        private String orderId;
        @SerializedName("ShopId")
        @Expose
        private String shopId;
        @SerializedName("ShippingMethod")
        @Expose
        private String shippingMethod;
        @SerializedName("PaymentMethod")
        @Expose
        private String paymentMethod;
        @SerializedName("VendorName")
        @Expose
        private String vendorName;
        @SerializedName("DotorName")
        @Expose
        private String dotorName;
        @SerializedName("StateCode")
        @Expose
        private String stateCode;
        @SerializedName("TAT")
        @Expose
        private String tat;
        @SerializedName("OrderType")
        @Expose
        private String orderType;
        @SerializedName("CouponCode")
        @Expose
        private String couponCode;
        @SerializedName("OrderDate")
        @Expose
        private String orderDate;
        @SerializedName("RequestType")
        @Expose
        private String requestType;
        @SerializedName("UserId")
        @Expose
        private String userId;
        @SerializedName("CustomerDetails")
        @Expose
        private CustomerDetails customerDetails;
        @SerializedName("PaymentDetails")
        @Expose
        private PaymentDetails paymentDetails;
        @SerializedName("ItemDetails")
        @Expose
        private List<ItemDetail> itemDetails = null;
        @SerializedName("PrescUrl")
        @Expose
        private List<com.apollo.pharmacy.ocr.model.PrescUrl> prescUrl = null;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShippingMethod() {
            return shippingMethod;
        }

        public void setShippingMethod(String shippingMethod) {
            this.shippingMethod = shippingMethod;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getVendorName() {
            return vendorName;
        }

        public void setVendorName(String vendorName) {
            this.vendorName = vendorName;
        }

        public String getDotorName() {
            return dotorName;
        }

        public void setDotorName(String dotorName) {
            this.dotorName = dotorName;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public String getTat() {
            return tat;
        }

        public void setTat(String tat) {
            this.tat = tat;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getRequestType() {
            return requestType;
        }

        public void setRequestType(String requestType) {
            this.requestType = requestType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public CustomerDetails getCustomerDetails() {
            return customerDetails;
        }

        public void setCustomerDetails(CustomerDetails customerDetails) {
            this.customerDetails = customerDetails;
        }

        public PaymentDetails getPaymentDetails() {
            return paymentDetails;
        }

        public void setPaymentDetails(PaymentDetails paymentDetails) {
            this.paymentDetails = paymentDetails;
        }

        public List<ItemDetail> getItemDetails() {
            return itemDetails;
        }

        public void setItemDetails(List<ItemDetail> itemDetails) {
            this.itemDetails = itemDetails;
        }

        public List<com.apollo.pharmacy.ocr.model.PrescUrl> getPrescUrl() {
            return prescUrl;
        }

        public void setPrescUrl(List<com.apollo.pharmacy.ocr.model.PrescUrl> prescUrl) {
            this.prescUrl = prescUrl;
        }
    }
}


