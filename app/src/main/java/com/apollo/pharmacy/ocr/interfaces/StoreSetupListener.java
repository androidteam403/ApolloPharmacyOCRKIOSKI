package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.GetStoreInfoResponse;

public interface StoreSetupListener {

    void onSuccessGetStoreList(GetStoreInfoResponse storeInfoResponse);

    void onFailure(String error);

    void onSuccessDeviceRegister(GetStoreInfoResponse response);

    void onClickListener(GetStoreInfoResponse.DeviceDetailsEntity item);
}
