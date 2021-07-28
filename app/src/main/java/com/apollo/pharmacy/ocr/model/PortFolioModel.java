package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PortFolioModel implements Serializable {

    @SerializedName("Success")
    private boolean Success;
    @SerializedName("CustomerData")
    CustomerData CustomerDataObject;


    // Getter Methods

    public boolean getSuccess() {
        return Success;
    }

    public CustomerData getCustomerData() {
        return CustomerDataObject;
    }

    // Setter Methods

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public void setCustomerData(CustomerData CustomerDataObject) {
        this.CustomerDataObject = CustomerDataObject;
    }

}
