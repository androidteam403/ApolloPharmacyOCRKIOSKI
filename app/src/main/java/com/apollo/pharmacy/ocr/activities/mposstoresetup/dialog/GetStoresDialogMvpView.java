package com.apollo.pharmacy.ocr.activities.mposstoresetup.dialog;

import com.apollo.pharmacy.ocr.activities.mposstoresetup.model.StoreListResponseModel;

public interface GetStoresDialogMvpView {
    void dismissDialog();

    void onClickListener(StoreListResponseModel.StoreListObj item);
}
