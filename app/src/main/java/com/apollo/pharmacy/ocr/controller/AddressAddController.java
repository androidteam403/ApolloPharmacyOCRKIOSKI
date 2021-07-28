package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.AddressAddListener;
import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.ServicabilityResponse;
import com.apollo.pharmacy.ocr.model.ServiceAvailabilityRequest;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressAddController {
    AddressAddListener addressAddListener;

    public AddressAddController(AddressAddListener listInterface) {
        addressAddListener = listInterface;
    }

    public void handleSaveDeliveryAddress(UserAddress userAddress) {
        ApiInterface api = ApiClient.getApiService(Constants.Add_User_Delivery_Address);
        Call<Meta> call = api.addAddress(userAddress);
        call.enqueue(new CallbackWithRetry<Meta>(call) {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Utils.dismissDialog();
                if (response.body() != null) {
                    Meta m = (Meta) response.body();
                    if (m.getStatusCode() == 200) {
                        addressAddListener.onSuccessSaveDeliveryAddress();
                    } else {
                        addressAddListener.onFailureService(((Meta) response.body()).getStatusMsg());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                t.printStackTrace();
                addressAddListener.onFailureService(t.getMessage());
            }
        });
    }

    public void handleOrderPlaceService(Context context, PlaceOrderReqModel placeOrderReqModel) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Order_Place_With_Prescription_API);
        Call<PlaceOrderResModel> call = apiInterface.PLACE_ORDER_SERVICE_CALL(Constants.Order_Place_With_Prescription_Token, placeOrderReqModel);
        call.enqueue(new Callback<PlaceOrderResModel>() {
            @Override
            public void onResponse(@NotNull Call<PlaceOrderResModel> call, @NotNull Response<PlaceOrderResModel> response) {
                assert response.body() != null;
                if (response.body().getOrdersResult().getStatus()) {
                    addressAddListener.onSuccessPlaceOrder(response.body());
                } else {
                    addressAddListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
                }
            }

            @Override
            public void onFailure(@NotNull Call<PlaceOrderResModel> call, @NotNull Throwable t) {
                addressAddListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
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
                    addressAddListener.onSuccessServiceability(response.body());
                } else {
                    addressAddListener.onFailureServiceability(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServicabilityResponse> call, @NonNull Throwable t) {
                addressAddListener.onFailureServiceability(context.getApplicationContext().getResources().getString(R.string.try_again_later));
            }
        });
    }
}
