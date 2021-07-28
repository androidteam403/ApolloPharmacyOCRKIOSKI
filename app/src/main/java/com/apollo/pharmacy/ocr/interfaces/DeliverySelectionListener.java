package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.StoreLocatorResponse;
import com.apollo.pharmacy.ocr.model.UserAddress;

import java.util.ArrayList;
import java.util.List;

public interface DeliverySelectionListener {

    void onFailure(String error);

    void onSuccessGetStoreLocators(List<StoreLocatorResponse> response);

    void onSuccessGetAddressDetails(ArrayList<UserAddress> response);
}
