package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.ServicabilityResponse;

public interface AddressAddListener {

    void onSuccessSaveDeliveryAddress();

    void onSuccessPlaceOrder(PlaceOrderResModel model);

    void onFailureService(String message);

    void onSuccessServiceability(ServicabilityResponse response);

    void onFailureServiceability(String message);
}
