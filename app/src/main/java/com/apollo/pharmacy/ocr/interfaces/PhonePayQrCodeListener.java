package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;

public interface PhonePayQrCodeListener {
    void onSuccessGetPhonePayQrCodeUpi(PhonePayQrCodeResponse phonePayQrCodeResponse);

    void onSuccessPlaceOrder(PlaceOrderResModel body);

    void onFailureService(String string);
}
