package com.apollo.pharmacy.ocr.model;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PastPrescriptionResponse {

    @SerializedName("prescriptionList")
    @Expose
    private List<ScannedData> prescriptionList;

    public List<ScannedData> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<ScannedData> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    private Drawable prescriptionImage;
    private int no;
    private boolean selectionFlag;

    public Drawable getPrescriptionImage() {
        return prescriptionImage;
    }

    public void setPrescriptionImage(Drawable prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean isSelectionFlag() {
        return selectionFlag;
    }

    public void setSelectionFlag(boolean selectionFlag) {
        this.selectionFlag = selectionFlag;
    }
}
