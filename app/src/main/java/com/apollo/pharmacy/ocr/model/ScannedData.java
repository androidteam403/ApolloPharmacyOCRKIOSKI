package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScannedData {

    @SerializedName("prescriptionImage")
    @Expose
    private String prescriptionImage;

    @SerializedName("prescriptionImageId")
    @Expose
    private String prescriptionImageId;
    @SerializedName("medicineList")
    @Expose
    private List<ScannedMedicine> medicineList = null;


    private boolean selectionFlag;

    public String getPrescriptionImage() {
        return prescriptionImage;
    }

    public void setPrescriptionImage(String prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
    }

    public List<ScannedMedicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<ScannedMedicine> medicineList) {
        this.medicineList = medicineList;
    }

    public String getPrescriptionImageId() {
        return prescriptionImageId;
    }

    public void setPrescriptionImageId(String prescriptionImageId) {
        this.prescriptionImageId = prescriptionImageId;
    }

    public boolean isSelectionFlag() {
        return selectionFlag;
    }

    public void setSelectionFlag(boolean selectionFlag) {
        this.selectionFlag = selectionFlag;
    }


}
