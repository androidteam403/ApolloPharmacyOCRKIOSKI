package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.HomeListener;
import com.apollo.pharmacy.ocr.model.Categorylist_Response;
import com.apollo.pharmacy.ocr.model.PortFolioModel;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivityController {
    HomeListener homeListener;

    public HomeActivityController(HomeListener listInterface) {
        homeListener = listInterface;
    }

    public void handleRedeemPoints(Context context) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Get_Portfolio_of_the_User);
        Call<PortFolioModel> call = apiInterface.getPortFolio(SessionManager.INSTANCE.getMobilenumber(), "true", "Apollo pharmacy");
        call.enqueue(new CallbackWithRetry<PortFolioModel>(call) {
            @Override
            public void onResponse(@NotNull Call<PortFolioModel> call, @NotNull Response<PortFolioModel> response) {
                if (response.isSuccessful()) {
                    handleGetCategoryListService(context);
                    assert response.body() != null;
                    homeListener.onSuccessRedeemPoints(response.body());
                } else {
                    homeListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PortFolioModel> call, @NonNull Throwable t) {
                Utils.dismissDialog();
                homeListener.onFailureService(t.getMessage());
            }
        });
    }

    public void handleGetCategoryListService(Context context) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Categorylist_Url + "/");
        Call<Categorylist_Response> call = apiInterface.getCategorylist(Constants.Categorylist_Url);
        call.enqueue(new Callback<Categorylist_Response>() {
            @Override
            public void onResponse(@NotNull Call<Categorylist_Response> call, @NotNull Response<Categorylist_Response> response) {
                Utils.dismissDialog();
                if (response.body() != null) {
                    homeListener.categoryListSuccessRes(response.body());
                } else {
                    homeListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
                }
            }

            @Override
            public void onFailure(@NotNull Call<Categorylist_Response> call, @NotNull Throwable t) {
                Utils.dismissDialog();
                homeListener.onFailureService(t.getMessage());
            }
        });
    }
}
