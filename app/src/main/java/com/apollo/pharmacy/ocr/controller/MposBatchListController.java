package com.apollo.pharmacy.ocr.controller;

import android.app.Activity;
import android.content.Context;

import com.apollo.pharmacy.ocr.interfaces.MposBatchListListener;
import com.apollo.pharmacy.ocr.model.BatchList;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MposBatchListController {
    MposBatchListListener mposBatchListListener;
    Context activity;

    public MposBatchListController(MposBatchListListener mposBatchListListener, Context activity) {
        this.mposBatchListListener = mposBatchListListener;
        this.activity = activity;
    }

    public void getBatchList() {
        Utils.showDialog(activity, "Loading…");
        ApiInterface api = ApiClient.getApiService();
        Call<BatchList> call = api.GET_BATCH_LIST();
        call.enqueue(new Callback<BatchList>() {
            @Override
            public void onResponse(@NotNull Call<BatchList> call, @NotNull Response<BatchList> response) {
                Utils.dismissDialog();
                if (response.body() != null) {
                    mposBatchListListener.setSuccessBatchList(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<BatchList> call, @NotNull Throwable t) {
                Utils.dismissDialog();
            }
        });
    }

}
