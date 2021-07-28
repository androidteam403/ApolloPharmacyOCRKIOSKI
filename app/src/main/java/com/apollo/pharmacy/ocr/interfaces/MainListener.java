package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.Global_api_response;

public interface MainListener {

    void onSuccessGlobalApiResponse(Global_api_response response);

    void onFailure(String message);
}
