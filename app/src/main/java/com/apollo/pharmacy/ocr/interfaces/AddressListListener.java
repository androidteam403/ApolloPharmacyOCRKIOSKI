package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.UserAddress;

import java.util.ArrayList;

public interface AddressListListener {

    void onFailure(String error);

    void onSuccessGetAddressList(ArrayList<UserAddress> response);

    void onSuccessDeleteAddress(Meta response);

    void onSuccessPlaceOrder(PlaceOrderResModel model);
}
