package com.apollo.pharmacy.ocr.activities.insertprescriptionnew;

import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;

public interface InsertPrescriptionActivityNewListener {
    void onClickPrescription();

    void onCloseFullviewPrescription();

    void onClickZoomIn();

    void onClickZoomOut();

    void onClickScanAnotherPrescription();

    void onClickPrescription(String prescriptionPath);

    void onClickItemDelete(int position);

    void onClickBackPressed();

    void onClickContinue();

    void onSuccessPlaceOrder(PlaceOrderResModel model);

    void onFailureService(String message);

}