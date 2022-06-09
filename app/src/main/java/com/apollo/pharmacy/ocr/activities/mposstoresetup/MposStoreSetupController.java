package com.apollo.pharmacy.ocr.activities.mposstoresetup;

import android.app.Activity;

import com.apollo.pharmacy.ocr.activities.mposstoresetup.model.StoreListResponseModel;
import com.apollo.pharmacy.ocr.model.DeviceRegistrationRequest;
import com.apollo.pharmacy.ocr.model.DeviceRegistrationResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MposStoreSetupController {

    StoreSetupMvpView mposStoreSetupListener;
    Activity activity;

    public MposStoreSetupController(StoreSetupMvpView mposStoreSetupListener, Activity activity) {
        this.mposStoreSetupListener = mposStoreSetupListener;
        this.activity = activity;
    }

    public void getStoreList() {

        Utils.showDialog(activity, "Loading…");
        ApiInterface api = ApiClient.getApiService2();
        Call<StoreListResponseModel> call = api.GET_STORES_LIST();
        call.enqueue(new Callback<StoreListResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<StoreListResponseModel> call, @NotNull Response<StoreListResponseModel> response) {
                Utils.dismissDialog();
                if (response.body() != null) {
                    mposStoreSetupListener.setStoresList(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<StoreListResponseModel> call, @NotNull Throwable t) {
                Utils.dismissDialog();
            }
        });
    }

    public void getDeviceRegistrationDetails(String deviceDate, String deviceType, String fireBaseId, String lattitude, String longitude, String macId, String storeId,
                                             String terminalId, String userId) {

        Utils.showDialog(activity, "Loading…");
        ApiInterface api = ApiClient.getApiService2();
        DeviceRegistrationRequest deviceRegistrationRequest = new DeviceRegistrationRequest();
        deviceRegistrationRequest.setDevicedate(deviceDate);
        deviceRegistrationRequest.setDevicetype(deviceType);
        deviceRegistrationRequest.setFcmkey(fireBaseId);
        deviceRegistrationRequest.setLatitude(lattitude);
        deviceRegistrationRequest.setLogitude(longitude);
        deviceRegistrationRequest.setMacid(macId);
        deviceRegistrationRequest.setStoreid(storeId);
        deviceRegistrationRequest.setTerminalid(terminalId);
        deviceRegistrationRequest.setUserid(userId);

        Call<DeviceRegistrationResponse> call = api.deviceRegistration(deviceRegistrationRequest);
        call.enqueue(new Callback<DeviceRegistrationResponse>() {
            @Override
            public void onResponse(@NotNull Call<DeviceRegistrationResponse> call, @NotNull Response<DeviceRegistrationResponse> response) {
                Utils.dismissDialog();
                if (response.body() != null) {
                    mposStoreSetupListener.getDeviceRegistrationDetails(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<DeviceRegistrationResponse> call, @NotNull Throwable t) {
                Utils.dismissDialog();
            }
        });
    }
}
