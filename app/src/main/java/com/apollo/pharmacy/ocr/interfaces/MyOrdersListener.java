package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.OrderHistoryResponse;
import com.apollo.pharmacy.ocr.model.PricePrescriptionResponse;

import java.util.List;

public interface MyOrdersListener {

    void onPricePrescription(PricePrescriptionResponse response);

    void onOrderHistorySuccess(List<OrderHistoryResponse> response);

    void onOrderHistoryFailure(String error);

    void onDeletePrescriptionSuccess(Meta m);

    void onReorderClick(List<OCRToDigitalMedicineResponse> dataList);
}
