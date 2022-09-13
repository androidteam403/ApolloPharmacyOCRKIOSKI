package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.PdfModelResponse;

public interface OrderinProgressListener {
    void onClickContinueShopping();


    void onSuccessPdfResponse(PdfModelResponse body);

    void onFailurePdfResponse(PdfModelResponse body);

    void onClickPrintReceipt();
}
