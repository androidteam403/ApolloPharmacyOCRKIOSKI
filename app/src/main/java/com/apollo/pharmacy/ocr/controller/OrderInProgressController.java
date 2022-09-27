package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import com.apollo.pharmacy.ocr.interfaces.OrderinProgressListener;
import com.apollo.pharmacy.ocr.model.PdfModelRequest;
import com.apollo.pharmacy.ocr.model.PdfModelResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderInProgressController {
    private Context mContext;
    private OrderinProgressListener orderinProgressListener;


    public OrderInProgressController(Context mContext, OrderinProgressListener orderinProgressListener) {
        this.mContext = mContext;
        this.orderinProgressListener = orderinProgressListener;
    }

    public void downloadPdf(String orderId) {

//        if (getMvpView().isNetworkConnected()) {
//            getMvpView().showLoading();
//            getMvpView().hideKeyboard();
        Utils.showDialog(mContext, "Please Wait...");
        ApiInterface apiInterface = ApiClient.getApiService(SessionManager.INSTANCE.getEposUrl());
        PdfModelRequest reqModel = new PdfModelRequest();
        reqModel.setStoreCode(SessionManager.INSTANCE.getStoreId());
        reqModel.setTerminalID(SessionManager.INSTANCE.getTerminalId());
        reqModel.setDataAreaID(SessionManager.INSTANCE.getDataAreaId());
        reqModel.setRequestStatus(0);
        reqModel.setReturnMessage("");
        reqModel.setTransactionId(orderId);
        reqModel.setBillingType(5);
        reqModel.setDigitalReceiptRequired(true);
        Call<PdfModelResponse> call = apiInterface.DOWNLOAD_PDF(reqModel);
        call.enqueue(new Callback<PdfModelResponse>() {
            @Override
            public void onResponse(Call<PdfModelResponse> call, Response<PdfModelResponse> response) {
//                    getMvpView().hideLoading();
                Utils.dismissDialog();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        orderinProgressListener.onSuccessPdfResponse(response.body());
                    }
                } else {
                    orderinProgressListener.onFailurePdfResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<PdfModelResponse> call, Throwable t) {
                Utils.dismissDialog();
                orderinProgressListener.onFailureMessage(t.getMessage());
//                    getMvpView().hideLoading();
//                    handleApiError(t);
            }
        });


//        }
    }
}
