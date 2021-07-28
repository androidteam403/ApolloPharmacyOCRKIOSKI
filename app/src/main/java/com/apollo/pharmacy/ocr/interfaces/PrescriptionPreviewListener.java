package com.apollo.pharmacy.ocr.interfaces;

public interface PrescriptionPreviewListener {

    void onViewClick(int position, String imagePath);

    void onClosePrescriptionClick(int position);
}
