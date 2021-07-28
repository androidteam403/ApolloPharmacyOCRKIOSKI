package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customerdetails_new {
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
    private String uHID;
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

    public String getUHID() {
        return uHID;
    }

    public void setUHID(String uHID) {
        this.uHID = uHID;
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
