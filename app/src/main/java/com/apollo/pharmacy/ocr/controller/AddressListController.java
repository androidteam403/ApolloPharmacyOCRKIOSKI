package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.AddressListListener;
import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressListController {

    AddressListListener addressListListener;

    public AddressListController(AddressListListener listInterface) {
        addressListListener = listInterface;
    }

    public void handleGetAddressListService(AddressListListener addressListListener) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Get_User_Delivery_Address_List);
        Call<ArrayList<UserAddress>> call = apiInterface.getUserAddressList(SessionManager.INSTANCE.getMobilenumber());
        call.enqueue(new CallbackWithRetry<ArrayList<UserAddress>>(call) {
            @Override
            public void onResponse(@NonNull Call<ArrayList<UserAddress>> call, @NonNull Response<ArrayList<UserAddress>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        addressListListener.onSuccessGetAddressList(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<UserAddress>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void handleDeleteAddressService(Integer id, AddressListListener addressListListener) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Delete_User_Delivery_address);
        Call<Meta> call = apiInterface.deleteAddress(id);
        call.enqueue(new CallbackWithRetry<Meta>(call) {
            @Override
            public void onResponse(@NonNull Call<Meta> call, @NonNull Response<Meta> response) {
                Meta m = (Meta) response.body();
                assert m != null;
                if (m.getStatusCode() == 200)
                    addressListListener.onSuccessDeleteAddress(m);
            }

            @Override
            public void onFailure(@NonNull Call<Meta> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void handlePlaceOrderService(Context context, PlaceOrderReqModel placeOrderReqModel) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Order_Place_With_Prescription_API);
        Call<PlaceOrderResModel> call = apiInterface.PLACE_ORDER_SERVICE_CALL(Constants.Order_Place_With_Prescription_Token, placeOrderReqModel);
        call.enqueue(new Callback<PlaceOrderResModel>() {
            @Override
            public void onResponse(@NotNull Call<PlaceOrderResModel> call, @NotNull Response<PlaceOrderResModel> response) {
                Utils.dismissDialog();
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        if (response.body().getOrdersResult().getStatus()) {
                            addressListListener.onSuccessPlaceOrder(response.body());
                        } else {
                            addressListListener.onFailure(context.getResources().getString(R.string.label_something_went_wrong));
                        }
                    }
                } else {
                    addressListListener.onFailure(context.getResources().getString(R.string.label_something_went_wrong));
                }
            }

            @Override
            public void onFailure(@NotNull Call<PlaceOrderResModel> call, @NotNull Throwable t) {
                Utils.dismissDialog();
                addressListListener.onFailure(context.getResources().getString(R.string.label_something_went_wrong));
            }
        });
    }
}
