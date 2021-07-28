package com.apollo.pharmacy.ocr.interfaces;

public interface CheckPrescriptionListener {

    void onClick(int position);

    void onCount(int count, boolean flag);

    void onPrescriptionClick(int position, boolean flag);
}
