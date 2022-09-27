package com.apollo.pharmacy.ocr.activities.userlogin;

import android.content.Context;

import com.apollo.pharmacy.ocr.activities.userlogin.model.GetGlobalConfigurationResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginController {
    private Context mContext;
    private UserLoginCallback mListener;

    public UserLoginController(Context mContext, UserLoginCallback mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }

    private void getGlobalConfigurationApiCall() {
        Utils.showDialog(mContext, "Loading…");
        ApiInterface api = ApiClient.getApiService2();
        Call<GetGlobalConfigurationResponse> call = api.GET_GLOBAL_CONFING_API_CALL(SessionManager.INSTANCE.getStoreId(), SessionManager.INSTANCE.getTerminalId(), SessionManager.INSTANCE.getDataAreaId(), new Object());
        call.enqueue(new Callback<GetGlobalConfigurationResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetGlobalConfigurationResponse> call, @NotNull Response<GetGlobalConfigurationResponse> response) {
                Utils.dismissDialog();
                if (response.body() != null && response.body().getRequestStatus() == 0) {
                    mListener.onSuccessGlobalConfigurationApiCall(response.body());
                } else if (response.body() != null) {
                    mListener.onFailureMessage(response.body().getReturnMessage());
                }
            }

            @Override
            public void onFailure(@NotNull Call<GetGlobalConfigurationResponse> call, @NotNull Throwable t) {
                Utils.dismissDialog();
                mListener.onFailureMessage(t.getMessage());
            }
        });
    }
}
