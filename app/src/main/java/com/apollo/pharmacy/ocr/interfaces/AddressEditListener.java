package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.ServicabilityResponse;

public interface AddressEditListener {

    void onSuccessEditAddress(Meta meta);

    void onFailureService(String message);

    void onSuccessServiceability(ServicabilityResponse response);

    void onFailureServiceability(String message);
}
