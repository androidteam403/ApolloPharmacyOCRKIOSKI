package com.apollo.pharmacy.ocr.model;

public class pharmacylocatorFilterlist {
    private String storeid;
    private String storename;
    private String storetype;
    private String storeaddress;
    private String storenumber;
    private String storelatitude;
    private String storelongtude;
    private String storecity;
    private String storestate;
    private String storepincode;
    private String storedistance;

    //setter methods for store locator values........
    public void setStoreid(String storeid1) {
        this.storeid = storeid1;
    }

    public void setStorename(String storename1) {
        this.storename = storename1;
    }

    public void setStoretype(String storetype1) {
        this.storetype = storetype1;
    }

    public void setStoreaddress(String storeaddress1) {
        this.storeaddress = storeaddress1;
    }

    public void setStorenumber(String storenumber1) {
        this.storenumber = storenumber1;

    }

    public void setStorelatitude(String storelatitude1) {
        this.storelatitude = storelatitude1;
    }

    public void setStorelongtude(String storelongtude1) {
        this.storelongtude = storelongtude1;
    }

    public void setStorecity(String storecity1) {
        this.storecity = storecity1;
    }

    public void setStorestate(String storestate1) {
        this.storestate = storestate1;
    }

    public void setStorepincode(String storepincode1) {
        this.storepincode = storepincode1;
    }

    public void setStoredistance(String storedistance1) {
        this.storedistance = storedistance1;
    }


    //getter methods for Store Details..........
    public String getStoreid() {
        return storeid;
    }

    public String getStorename() {
        return storename;
    }

    public String getStoretype() {
        return storetype;
    }

    public String getStoreaddress() {
        return storeaddress;
    }

    public String getStorenumber() {
        return storenumber;
    }

    public String getStorelatitude() {
        return storelatitude;
    }

    public String getStorelongtude() {
        return storelongtude;
    }

    public String getStorecity() {
        return storecity;
    }

    public String getStorestate() {
        return storestate;
    }

    public String getStorepincode() {
        return storepincode;
    }

    public String getStoredistance() {
        return storedistance;
    }
}
