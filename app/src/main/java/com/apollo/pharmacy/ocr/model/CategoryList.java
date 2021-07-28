package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryList {
    @SerializedName("KEY")
    @Expose
    private String kEY;
    @SerializedName("VALUE")
    @Expose
    private Integer vALUE;

    public String getKEY() {
        return kEY;
    }

    public void setKEY(String kEY) {
        this.kEY = kEY;
    }

    public Integer getVALUE() {
        return vALUE;
    }

    public void setVALUE(Integer vALUE) {
        this.vALUE = vALUE;
    }

}
