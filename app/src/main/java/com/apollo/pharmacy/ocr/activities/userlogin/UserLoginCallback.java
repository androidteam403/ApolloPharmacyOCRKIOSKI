package com.apollo.pharmacy.ocr.activities.userlogin;

import com.apollo.pharmacy.ocr.activities.userlogin.model.GetGlobalConfigurationResponse;

public interface UserLoginCallback {

    void onSuccessGlobalConfigurationApiCall(GetGlobalConfigurationResponse getGlobalConfigurationResponse);

    void onFailureMessage(String message);
}
