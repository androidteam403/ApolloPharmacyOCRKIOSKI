package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customerdetails {
    @SerializedName("UHID")
    @Expose
    private Object uHID;
    @SerializedName("Mobileno")
    @Expose
    private String mobileno;
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
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Postcode")
    @Expose
    private String postcode;
    @SerializedName("Mailid")
    @Expose
    private String mailid;
    @SerializedName("Age")
    @Expose
    private int age;
    @SerializedName("Cardno")
    @Expose
    private String cardno;
    @SerializedName("PatientName")
    @Expose
    private String patientName;

    public Object getUHID() {
        return uHID;
    }

    public void setUHID(Object uHID) {
        this.uHID = uHID;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
