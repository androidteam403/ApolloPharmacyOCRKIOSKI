package com.apollo.pharmacy.ocr.controller;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.apollo.pharmacy.ocr.interfaces.MposBatchListListener;
import com.apollo.pharmacy.ocr.model.BatchListRequest;
import com.apollo.pharmacy.ocr.model.BatchListResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

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
        ApiInterface api = ApiClient.getApiServiceMposBaseUrl(SessionManager.INSTANCE.getEposUrl());
        BatchListRequest batchListRequest = new BatchListRequest();
        batchListRequest.setArticleCode(artcode);
        batchListRequest.setCustomerState("");
        batchListRequest.setDataAreaId(SessionManager.INSTANCE.getDataAreaId());
        batchListRequest.setSez(0);
        batchListRequest.setSearchType(1);
        batchListRequest.setStoreId(SessionManager.INSTANCE.getStoreId());
        batchListRequest.setStoreState("TS");
        batchListRequest.setExpiryDays("30");
        batchListRequest.setTerminalId(SessionManager.INSTANCE.getTerminalId());
        Call<BatchListResponse> call = api.GET_BATCH_LIST(batchListRequest);
        call.enqueue(new Callback<BatchListResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Call<BatchListResponse> call, @NotNull Response<BatchListResponse> response) {
                if (response.body() != null) {
                    List<BatchListResponse.Batch> batchList = response.body().getBatchList()
                            .stream()
                            .filter(batchItem -> !batchItem.getNearByExpiry())
                            .collect(Collectors.toList());

                    response.body().setBatchList(batchList);
                    mposBatchListListener.setSuccessBatchList(response.body());
                } else {
                    Utils.dismissDialog();
                }
            }

            @Override
            public void onFailure(@NotNull Call<BatchListResponse> call, @NotNull Throwable t) {
                Utils.dismissDialog();
            }
        });
    }

}
