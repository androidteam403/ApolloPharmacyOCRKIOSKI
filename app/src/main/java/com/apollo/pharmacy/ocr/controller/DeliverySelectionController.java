package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.DeliverySelectionListener;
import com.apollo.pharmacy.ocr.model.StoreLocatorResponse;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DeliverySelectionController {
    DeliverySelectionListener deliverySelectionListener;

    public DeliverySelectionController(DeliverySelectionListener listInterface) {
        deliverySelectionListener = listInterface;
    }

    public void getStoreLocators(Context context, DeliverySelectionListener productListInterface) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Get_Store_Locator);
        Call<List<StoreLocatorResponse>> call = apiInterface.getStoreLocatorApi();
        call.enqueue(new CallbackWithRetry<List<StoreLocatorResponse>>(call) {
            @Override
            public void onResponse(@NonNull Call<List<StoreLocatorResponse>> call, @NonNull Response<List<StoreLocatorResponse>> response) {
                productListInterface.onSuccessGetStoreLocators(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<StoreLocatorResponse>> call, @NonNull Throwable t) {
                productListInterface.onFailure(context.getResources().getString(R.string.label_something_went_wrong));
            }
        });
    }

    public void getAddressList(Context context, DeliverySelectionListener productListInterface) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Get_User_Delivery_Address_List);
        Call<ArrayList<UserAddress>> call = apiInterface.getUserAddressList(SessionManager.INSTANCE.getMobilenumber());
        call.enqueue(new CallbackWithRetry<ArrayList<UserAddress>>(call) {
            @Override
            public void onResponse(@NonNull Call<ArrayList<UserAddress>> call, @NonNull Response<ArrayList<UserAddress>> response) {
                Utils.dismissDialog();
                if (response.isSuccessful()) {
                    productListInterface.onSuccessGetAddressDetails(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<UserAddress>> call, @NonNull Throwable t) {
                Utils.dismissDialog();
                productListInterface.onFailure(context.getResources().getString(R.string.label_something_went_wrong));
            }
        });
    }
}
