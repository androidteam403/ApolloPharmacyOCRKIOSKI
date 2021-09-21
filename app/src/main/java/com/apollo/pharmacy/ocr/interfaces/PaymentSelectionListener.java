package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.OrderPushingResponse;
import com.apollo.pharmacy.ocr.model.OrderPushingResponse_new;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.model.PincodeValidationResponse;
import com.apollo.pharmacy.ocr.model.PinelabTransaction_cancelresponse;
import com.apollo.pharmacy.ocr.model.Pinelabs_paymenttransaction_response;
import com.apollo.pharmacy.ocr.model.Pinelabstransaction_response;
import com.apollo.pharmacy.ocr.model.PinepayrequestResult;

public interface PaymentSelectionListener {

    void onFailure(String error);

    void onSuccessOrderPush(OrderPushingResponse response);

    void onSuccessOrderPushNew(OrderPushingResponse_new response);

    void onSuccessPaytmTransaction(Object response);

    void onFailurePaytmTransaction(String error);

    void onSuccessPinelabsTransaction(PinepayrequestResult response);

    void OnonSuccessPinelabsCancelTransaction(PinelabTransaction_cancelresponse response);

    void onSuccessPinelabsPaymentTransaction(Pinelabs_paymenttransaction_response response);
}
