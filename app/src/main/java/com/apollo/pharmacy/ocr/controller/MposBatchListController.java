package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import com.apollo.pharmacy.ocr.interfaces.MposBatchListListener;
import com.apollo.pharmacy.ocr.model.BatchListRequest;
import com.apollo.pharmacy.ocr.model.BatchListResponse;
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

    public void getBatchList(String artcode) {
        Utils.showDialog(activity, "Loading…");
        ApiInterface api = ApiClient.getApiService();
        BatchListRequest batchListRequest = new BatchListRequest();
        batchListRequest.setArticleCode(artcode);
        batchListRequest.setCustomerState("");
        batchListRequest.setDataAreaId("ahel");
        batchListRequest.setSez(0);
        batchListRequest.setSearchType(1);
        batchListRequest.setStoreId("16001");
        batchListRequest.setStoreState("AP");
        batchListRequest.setTerminalId("005");
        Call<BatchListResponse> call = api.GET_BATCH_LIST(batchListRequest);
        call.enqueue(new Callback<BatchListResponse>() {
            @Override
            public void onResponse(@NotNull Call<BatchListResponse> call, @NotNull Response<BatchListResponse> response) {
                if (response.body() != null) {
                    mposBatchListListener.setSuccessBatchList(response.body());
                }
                Utils.dismissDialog();
            }

            @Override
            public void onFailure(@NotNull Call<BatchListResponse> call, @NotNull Throwable t) {
                Utils.dismissDialog();
            }
        });
    }

}
