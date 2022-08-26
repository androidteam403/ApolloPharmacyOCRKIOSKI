package com.apollo.pharmacy.ocr.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelfOrderHistoryResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Orders")
    @Expose
    private List<Order> orders = null;

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public class Item {

        @SerializedName("ItemId")
        @Expose
        private String itemId;
        @SerializedName("itemName")
        @Expose
        private String itemName;
        @SerializedName("ReqQty")
        @Expose
        private String reqQty;
        @SerializedName("BillQty")
        @Expose
        private String billQty;
        @SerializedName("Mrp")
        @Expose
        private String mrp;
        @SerializedName("PackSize")
        @Expose
        private String packSize;

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

        public String getReqQty() {
            return reqQty;
        }

        public void setReqQty(String reqQty) {
            this.reqQty = reqQty;
        }

        public String getBillQty() {
            return billQty;
        }

        public void setBillQty(String billQty) {
            this.billQty = billQty;
        }

        public String getMrp() {
            return mrp;
        }

        public void setMrp(String mrp) {
            this.mrp = mrp;
        }

        public String getPackSize() {
            return packSize;
        }

        public void setPackSize(String packSize) {
            this.packSize = packSize;
        }

    }

    public class Order {

        @SerializedName("ApolloSource")
        @Expose
        private String apolloSource;
        @SerializedName("StatusName")
        @Expose
        private String statusName;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("VendorSource")
        @Expose
        private String vendorSource;
        @SerializedName("BillDate")
        @Expose
        private String billDate;
        @SerializedName("DelDate")
        @Expose
        private String delDate;
        @SerializedName("CancelDate")
        @Expose
        private String cancelDate;
        @SerializedName("OrderDate")
        @Expose
        private String orderDate;
        @SerializedName("BillValue")
        @Expose
        private String billValue;
        @SerializedName("CreditValue")
        @Expose
        private String creditValue;
        @SerializedName("CashValue")
        @Expose
        private String cashValue;
        @SerializedName("TotalAmount")
        @Expose
        private String totalAmount;
        @SerializedName("StoreId")
        @Expose
        private String storeId;
        @SerializedName("STOREADDRESS")
        @Expose
        private String storeaddress;
        @SerializedName("UHID")
        @Expose
        private String uhid;
        @SerializedName("CustomerName")
        @Expose
        private String customerName;
        @SerializedName("ContactNumber")
        @Expose
        private String contactNumber;
        @SerializedName("OrderAmount")
        @Expose
        private String orderAmount;
        @SerializedName("DeliveryMode")
        @Expose
        private String deliveryMode;
        @SerializedName("PaymentMode")
        @Expose
        private String paymentMode;
        @SerializedName("Address")
        @Expose
        private String address;
        @SerializedName("Pincode")
        @Expose
        private String pincode;
        @SerializedName("VendorSourceOrderId")
        @Expose
        private String vendorSourceOrderId;
        @SerializedName("ISINVOICE")
        @Expose
        private String isinvoice;
        @SerializedName("INVOICEURL")
        @Expose
        private String invoiceurl;
        @SerializedName("OrderJourney")
        @Expose
        private List<OrderJourney> orderJourney = null;
        @SerializedName("Items")
        @Expose
        private List<Item> items = null;

        private boolean isSelected = false;

        public String getApolloSource() {
            return apolloSource;
        }

        public void setApolloSource(String apolloSource) {
            this.apolloSource = apolloSource;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVendorSource() {
            return vendorSource;
        }

        public void setVendorSource(String vendorSource) {
            this.vendorSource = vendorSource;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getDelDate() {
            return delDate;
        }

        public void setDelDate(String delDate) {
            this.delDate = delDate;
        }

        public String getCancelDate() {
            return cancelDate;
        }

        public void setCancelDate(String cancelDate) {
            this.cancelDate = cancelDate;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getBillValue() {
            return billValue;
        }

        public void setBillValue(String billValue) {
            this.billValue = billValue;
        }

        public String getCreditValue() {
            return creditValue;
        }

        public void setCreditValue(String creditValue) {
            this.creditValue = creditValue;
        }

        public String getCashValue() {
            return cashValue;
        }

        public void setCashValue(String cashValue) {
            this.cashValue = cashValue;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoreaddress() {
            return storeaddress;
        }

        public void setStoreaddress(String storeaddress) {
            this.storeaddress = storeaddress;
        }

        public String getUhid() {
            return uhid;
        }

        public void setUhid(String uhid) {
            this.uhid = uhid;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getDeliveryMode() {
            return deliveryMode;
        }

        public void setDeliveryMode(String deliveryMode) {
            this.deliveryMode = deliveryMode;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getVendorSourceOrderId() {
            return vendorSourceOrderId;
        }

        public void setVendorSourceOrderId(String vendorSourceOrderId) {
            this.vendorSourceOrderId = vendorSourceOrderId;
        }

        public String getIsinvoice() {
            return isinvoice;
        }

        public void setIsinvoice(String isinvoice) {
            this.isinvoice = isinvoice;
        }

        public String getInvoiceurl() {
            return invoiceurl;
        }

        public void setInvoiceurl(String invoiceurl) {
            this.invoiceurl = invoiceurl;
        }

        public List<OrderJourney> getOrderJourney() {
            return orderJourney;
        }

        public void setOrderJourney(List<OrderJourney> orderJourney) {
            this.orderJourney = orderJourney;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

    public class OrderJourney {

        @SerializedName("OrderState")
        @Expose
        private String orderState;
        @SerializedName("Date")
        @Expose
        private String date;

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

    }
}