package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.PincodeValidateResponse;

import java.util.List;

public interface PincodeValidateListener {
    void onSuccessPincodeValidate(List<PincodeValidateResponse> body);

    void onFailurePincode(List<PincodeValidateResponse> body);
}
