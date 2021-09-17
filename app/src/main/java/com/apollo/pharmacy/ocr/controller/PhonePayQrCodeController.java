package com.apollo.pharmacy.ocr.controller;

import android.app.Activity;
import android.content.Context;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.PhonePayQrCodeListener;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeRequest;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
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
        phonePayQrCodeRequest.setAmount(10.0);
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
}
