package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerData implements Serializable {

    @SerializedName("LCIN")
    private String LCIN;
    @SerializedName("Name")
    private String Name;
    @SerializedName("MobileNumber")
    private String MobileNumber;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Gender")
    private String Gender;
    @SerializedName("DOB")
    private String DOB;
    @SerializedName("EmailCommunication")
    private boolean EmailCommunication;
    @SerializedName("SMSCommunication")
    private boolean SMSCommunication;
    @SerializedName("FamilyId")
    private String FamilyId;
    @SerializedName("FamilyRelation")
    private String FamilyRelation;
    @SerializedName("Age")
    private float Age;
    @SerializedName("SourceBusinessUnit")
    private String SourceBusinessUnit;
    @SerializedName("SourceStore")
    private String SourceStore;
    @SerializedName("Tier")
    private String Tier;
    @SerializedName("TierChangeDate")
    private String TierChangeDate;
    @SerializedName("EarnedCredits")
    private float EarnedCredits;
    @SerializedName("AvailableCredits")
    private float AvailableCredits;
    @SerializedName("BurnedCredits")
    private float BurnedCredits;
    @SerializedName("ExpiredCredits")
    private float ExpiredCredits;
    @SerializedName("BlockedCredits")
    private float BlockedCredits;
    @SerializedName("MigratedCredits")
    private float MigratedCredits;
    @SerializedName("Address")
    private String Address;
    @SerializedName("City")
    private String City;
    @SerializedName("Region")
    private String Region;
    @SerializedName("State")
    private String State;
    @SerializedName("Country")
    private String Country;
    @SerializedName("Pincode")
    private String Pincode;
    @SerializedName("CanBurnCredits")
    private boolean CanBurnCredits;
    @SerializedName("TierBenefits")
    private String TierBenefits;

    public String getLCIN() {
        return LCIN;
    }

    public void setLCIN(String LCIN) {
        this.LCIN = LCIN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public boolean isEmailCommunication() {
        return EmailCommunication;
    }

    public void setEmailCommunication(boolean emailCommunication) {
        EmailCommunication = emailCommunication;
    }

    public boolean isSMSCommunication() {
        return SMSCommunication;
    }

    public void setSMSCommunication(boolean SMSCommunication) {
        this.SMSCommunication = SMSCommunication;
    }

    public String getFamilyId() {
        return FamilyId;
    }

    public void setFamilyId(String familyId) {
        FamilyId = familyId;
    }

    public String getFamilyRelation() {
        return FamilyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        FamilyRelation = familyRelation;
    }

    public float getAge() {
        return Age;
    }

    public void setAge(float age) {
        Age = age;
    }

    public String getSourceBusinessUnit() {
        return SourceBusinessUnit;
    }

    public void setSourceBusinessUnit(String sourceBusinessUnit) {
        SourceBusinessUnit = sourceBusinessUnit;
    }

    public String getSourceStore() {
        return SourceStore;
    }

    public void setSourceStore(String sourceStore) {
        SourceStore = sourceStore;
    }

    public String getTier() {
        return Tier;
    }

    public void setTier(String tier) {
        Tier = tier;
    }

    public String getTierChangeDate() {
        return TierChangeDate;
    }

    public void setTierChangeDate(String tierChangeDate) {
        TierChangeDate = tierChangeDate;
    }

    public float getEarnedCredits() {
        return EarnedCredits;
    }

    public void setEarnedCredits(float earnedCredits) {
        EarnedCredits = earnedCredits;
    }

    public float getAvailableCredits() {
        return AvailableCredits;
    }

    public void setAvailableCredits(float availableCredits) {
        AvailableCredits = availableCredits;
    }

    public float getBurnedCredits() {
        return BurnedCredits;
    }

    public void setBurnedCredits(float burnedCredits) {
        BurnedCredits = burnedCredits;
    }

    public float getExpiredCredits() {
        return ExpiredCredits;
    }

    public void setExpiredCredits(float expiredCredits) {
        ExpiredCredits = expiredCredits;
    }

    public float getBlockedCredits() {
        return BlockedCredits;
    }

    public void setBlockedCredits(float blockedCredits) {
        BlockedCredits = blockedCredits;
    }

    public float getMigratedCredits() {
        return MigratedCredits;
    }

    public void setMigratedCredits(float migratedCredits) {
        MigratedCredits = migratedCredits;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public boolean isCanBurnCredits() {
        return CanBurnCredits;
    }

    public void setCanBurnCredits(boolean canBurnCredits) {
        CanBurnCredits = canBurnCredits;
    }

    public String getTierBenefits() {
        return TierBenefits;
    }

    public void setTierBenefits(String tierBenefits) {
        TierBenefits = tierBenefits;
    }


    @Override
    public String toString() {
        return "CustomerData{" +
                "LCIN='" + LCIN + '\'' +
                ", Name='" + Name + '\'' +
                ", MobileNumber='" + MobileNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", Gender='" + Gender + '\'' +
                ", DOB='" + DOB + '\'' +
                ", EmailCommunication=" + EmailCommunication +
                ", SMSCommunication=" + SMSCommunication +
                ", FamilyId='" + FamilyId + '\'' +
                ", FamilyRelation='" + FamilyRelation + '\'' +
                ", Age=" + Age +
                ", SourceBusinessUnit='" + SourceBusinessUnit + '\'' +
                ", SourceStore='" + SourceStore + '\'' +
                ", Tier='" + Tier + '\'' +
                ", TierChangeDate='" + TierChangeDate + '\'' +
                ", EarnedCredits=" + EarnedCredits +
                ", AvailableCredits=" + AvailableCredits +
                ", BurnedCredits=" + BurnedCredits +
                ", ExpiredCredits=" + ExpiredCredits +
                ", BlockedCredits=" + BlockedCredits +
                ", MigratedCredits=" + MigratedCredits +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", Region='" + Region + '\'' +
                ", State='" + State + '\'' +
                ", Country='" + Country + '\'' +
                ", Pincode='" + Pincode + '\'' +
                ", CanBurnCredits=" + CanBurnCredits +
                ", TierBenefits='" + TierBenefits + '\'' +
                '}';
    }
}
