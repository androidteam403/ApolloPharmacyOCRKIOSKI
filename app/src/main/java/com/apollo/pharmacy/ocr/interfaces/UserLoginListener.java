package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.Global_api_response;

public interface UserLoginListener {

    void onSendSmsSuccess();

    void onSendSmsFailure();

    void onFailure(String message);

    void onSuccessGlobalApiResponse(Global_api_response list);
}
