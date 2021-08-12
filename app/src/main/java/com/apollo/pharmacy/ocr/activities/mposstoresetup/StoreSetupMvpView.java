package com.apollo.pharmacy.ocr.activities.mposstoresetup;

import com.apollo.pharmacy.ocr.activities.mposstoresetup.model.StoreListResponseModel;

public interface StoreSetupMvpView {
    void dialogCloseListiner();
    void onSelectStore(StoreListResponseModel.StoreListObj item);

    void setStoresList(StoreListResponseModel storesList);

    void onSelectStoreSearch();

    void onCancelBtnClick();

    void handleStoreSetupService();

    void getStoreList();

    void insertAdminLoginDetails();

    void checkConfingApi();

    void onVerifyClick();
}
