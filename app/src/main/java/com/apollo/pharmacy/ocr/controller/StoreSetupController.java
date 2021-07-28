package com.apollo.pharmacy.ocr.controller;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.StoreSetupListener;
import com.apollo.pharmacy.ocr.model.GetStoreInfoResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.Utils;

import retrofit2.Call;
import retrofit2.Response;

public class StoreSetupController {
    StoreSetupListener storeSetupListener;

    public StoreSetupController(StoreSetupListener setupInterface) {
        storeSetupListener = setupInterface;
    }

    public void getStoreListDetails(String selectedKioskId, boolean makeInActive) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.GetDeviceDetails + "/");
        GetStoreInfoRequest storeInfoRequest = new GetStoreInfoRequest();
        storeInfoRequest.setDeviceId(selectedKioskId);
        storeInfoRequest.setMakeInActive(makeInActive);
        Call<GetStoreInfoResponse> call = apiInterface.getStoreListService(Constants.GetDeviceDetails, storeInfoRequest);
        call.enqueue(new CallbackWithRetry<GetStoreInfoResponse>(call) {
            @Override
            public void onResponse(@NonNull Call<GetStoreInfoResponse> call, @NonNull Response<GetStoreInfoResponse> response) {
                Utils.dismissDialog();
                if (response.isSuccessful()) {
                    if (selectedKioskId.isEmpty()) {
                        if (response.body().getStatus()) {
                            storeSetupListener.onSuccessGetStoreList(response.body());
                        } else {
                            storeSetupListener.onFailure(response.body().getMessage());
                        }
                    } else {
                        if (response.body().getStatus()) {
                            storeSetupListener.onSuccessDeviceRegister(response.body());
                        } else {
                            storeSetupListener.onFailure(response.body().getMessage());
                        }
                    }
                } else {
                    storeSetupListener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetStoreInfoResponse> call, @NonNull Throwable t) {
                Utils.dismissDialog();
                storeSetupListener.onFailure(t.getMessage());
            }
        });
    }
}
