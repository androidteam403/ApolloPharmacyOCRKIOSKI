package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.interfaces.MainListener;
import com.apollo.pharmacy.ocr.model.AddFCMTokenRequest;
import com.apollo.pharmacy.ocr.model.Global_api_request;
import com.apollo.pharmacy.ocr.model.Global_api_response;
import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.ApplicationConstant;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivityController {
    MainListener mainListener;

    public MainActivityController(MainListener listInterface) {
        mainListener = listInterface;
    }

    public void handleFCMTokenRegistration(String token) {
        AddFCMTokenRequest request = new AddFCMTokenRequest(token, SessionManager.INSTANCE.getKioskSetupResponse().getKIOSK_ID());
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Add_FCM_Token);
        Call<Meta> call = apiInterface.addFcmToken(request);
        call.enqueue(new CallbackWithRetry<Meta>(call) {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Meta m = (Meta) response.body();
//                assert m != null;
                if (m!=null && m.getStatusCode() == 200) {
                    SessionManager.INSTANCE.addFcmLog();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getGlobalApiList(Context context){
        Global_api_request request = new Global_api_request();
        request.setDEVICEID(Utils.getDeviceId(context));
        request.setKEY("2028");
        ApiInterface apiInterface = ApiClient.getApiService(ApplicationConstant.Global_api_url);
        Call<Global_api_response> call = apiInterface.get_global_apis(request);
        call.enqueue(new CallbackWithRetry<Global_api_response>(call) {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.isSuccessful()) {
                    mainListener.onSuccessGlobalApiResponse((Global_api_response) response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mainListener.onFailure(t.getMessage());
            }
        });
    }
}
