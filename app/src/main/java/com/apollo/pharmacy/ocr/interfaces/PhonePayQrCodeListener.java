package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;

public interface PhonePayQrCodeListener {
    void onSuccessGetPhonePayQrCodeUpi(PhonePayQrCodeResponse phonePayQrCodeResponse);
}
