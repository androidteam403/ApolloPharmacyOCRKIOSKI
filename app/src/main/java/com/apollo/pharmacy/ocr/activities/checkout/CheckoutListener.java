package com.apollo.pharmacy.ocr.activities.checkout;

import com.apollo.pharmacy.ocr.activities.paymentoptions.model.ExpressCheckoutTransactionApiResponse;

public interface CheckoutListener {
    void onClickNeedHomeDelivery();

    void onClickPayandCollectatCounter();

    void onClickNeedHomeDelivery1();

    void onClickPayhereandCarry();

    void onClickBack();

    void onClickPaynow();

    void onSuccessexpressCheckoutTransactionApiCall(ExpressCheckoutTransactionApiResponse expressCheckoutTransactionApiResponse);

    void onFailuremessage(String message);
}
