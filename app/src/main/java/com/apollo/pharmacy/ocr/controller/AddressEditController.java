package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.AddressEditListener;
import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.ServicabilityResponse;
import com.apollo.pharmacy.ocr.model.ServiceAvailabilityRequest;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;

import retrofit2.Call;
import retrofit2.Response;

public class AddressEditController {
    AddressEditListener addressEditListener;

    public AddressEditController(AddressEditListener listInterface) {
        addressEditListener = listInterface;
    }

    public void handleEditAddress(UserAddress userAddress) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Edit_User_Delivery_Address);
        Call<Meta> call = apiInterface.editAddress(userAddress);
        call.enqueue(new CallbackWithRetry<Meta>(call) {
            @Override
            public void onResponse(@NonNull Call<Meta> call, @NonNull Response<Meta> response) {
                addressEditListener.onSuccessEditAddress(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Meta> call, @NonNull Throwable t) {
                addressEditListener.onFailureService(t.getMessage());
            }
        });
    }

    public void checkServiceAvailability(Context context, String inputPincode) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.CheckServiceAvailability);
        ServiceAvailabilityRequest serviceAvailabilityRequest = new ServiceAvailabilityRequest();
        serviceAvailabilityRequest.setVendorName("KIOSK");
        serviceAvailabilityRequest.setPincode(inputPincode);
        Call<ServicabilityResponse> call = apiInterface.get_servicability_api(serviceAvailabilityRequest);
        call.enqueue(new CallbackWithRetry<ServicabilityResponse>(call) {
            @Override
            public void onResponse(@NonNull Call<ServicabilityResponse> call, @NonNull Response<ServicabilityResponse> response) {
                assert response.body() != null;
                if (response.body().getStatus()) {
                    addressEditListener.onSuccessServiceability(response.body());
                } else {
                    addressEditListener.onFailureServiceability(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServicabilityResponse> call, @NonNull Throwable t) {
                addressEditListener.onFailureServiceability(context.getApplicationContext().getResources().getString(R.string.try_again_later));
            }
        });
    }
}
