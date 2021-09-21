package com.apollo.pharmacy.ocr.controller;

import android.app.Activity;
import android.content.Context;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.PhonePayQrCodeListener;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeRequest;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhonePayQrCodeController {

    PhonePayQrCodeListener phonePayQrCodeListener;
    Context activity;

    public PhonePayQrCodeController(Context activity, PhonePayQrCodeListener phonePayQrCodeListener) {
        this.activity = activity;
        this.phonePayQrCodeListener = phonePayQrCodeListener;
    }

    public void getPhonePayQrCodeGeneration() {
//        Utils.showDialog(activity, "Loading…");
        ApiInterface api = ApiClient.getApiService();
        PhonePayQrCodeRequest phonePayQrCodeRequest = new PhonePayQrCodeRequest();
        phonePayQrCodeRequest.setAmount(1.0);
        phonePayQrCodeRequest.setExpiresIn(2000);
        phonePayQrCodeRequest.setMessage("");
        phonePayQrCodeRequest.setOriginalTransactionId("");
        phonePayQrCodeRequest.setProviderReferenceId("");
        phonePayQrCodeRequest.setReqType("GENERATEQRCODE");
        phonePayQrCodeRequest.setStoreId("16001");
        phonePayQrCodeRequest.setTransactionId(Utils.getOrderedID());
        phonePayQrCodeRequest.setUrl("http://172.16.2.251:8033/PHONEPEUAT/APOLLO/PhonePe");
        Call<PhonePayQrCodeResponse> call = api.GET_PhonePay_Qr_Code(phonePayQrCodeRequest);
        call.enqueue(new Callback<PhonePayQrCodeResponse>() {
            @Override
            public void onResponse(@NotNull Call<PhonePayQrCodeResponse> call, @NotNull Response<PhonePayQrCodeResponse> response) {
                if (response.body() != null) {
//                    Utils.dismissDialog();
                    phonePayQrCodeListener.onSuccessGetPhonePayQrCodeUpi(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<PhonePayQrCodeResponse> call, @NotNull Throwable t) {
//                Utils.dismissDialog();
            }
        });
    }
    public void handleOrderPlaceService(Context context, PlaceOrderReqModel placeOrderReqModel) {
        //        Utils.showDialog(activity, "Loading…");

        ApiInterface apiInterface = ApiClient.getApiService(Constants.Order_Place_With_Prescription_API);
        Call<PlaceOrderResModel> call = apiInterface.PLACE_ORDER_SERVICE_CALL(Constants.New_Order_Place_With_Prescription_Token, placeOrderReqModel);
        call.enqueue(new Callback<PlaceOrderResModel>() {
            @Override
            public void onResponse(@NotNull Call<PlaceOrderResModel> call, @NotNull Response<PlaceOrderResModel> response) {
                assert response.body() != null;
                if (response.body().getOrdersResult().getStatus()) {
                    phonePayQrCodeListener.onSuccessPlaceOrder(response.body());
                } else {
                    phonePayQrCodeListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
                }
                //                Utils.dismissDialog();

            }

            @Override
            public void onFailure(@NotNull Call<PlaceOrderResModel> call, @NotNull Throwable t) {
                phonePayQrCodeListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
                //                Utils.dismissDialog();

            }
        });
    }

    public void getPhonePayPaymentSuccess(PhonePayQrCodeResponse response) {
        Utils.showDialog(activity, "Loading…");
        ApiInterface api = ApiClient.getApiService();
        PhonePayQrCodeResponse responsseAsRequest = new PhonePayQrCodeResponse();
        responsseAsRequest.setMessage(response.getMessage());
        responsseAsRequest.setProviderReferenceId(response.getProviderReferenceId());
        responsseAsRequest.setQrCode(response.getQrCode());
        responsseAsRequest.setStatus(response.getStatus());

        Call<PhonePayQrCodeResponse> call = api.GET_PhonePay_Qr_payment_Success(responsseAsRequest);
        call.enqueue(new Callback<PhonePayQrCodeResponse>() {
            @Override
            public void onResponse(@NotNull Call<PhonePayQrCodeResponse> call, @NotNull Response<PhonePayQrCodeResponse> response) {
                if (response.body() != null) {
                    Utils.dismissDialog();
//                    phonePayQrCodeListener.onSuccessGetPhonePayQrCodeUpi(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<PhonePayQrCodeResponse> call, @NotNull Throwable t) {
                Utils.dismissDialog();
            }
        });
    }
}