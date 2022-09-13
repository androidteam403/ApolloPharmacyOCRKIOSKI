package com.apollo.pharmacy.ocr.controller;

import com.apollo.pharmacy.ocr.interfaces.OrderinProgressListener;
import com.apollo.pharmacy.ocr.model.PdfModelRequest;
import com.apollo.pharmacy.ocr.model.PdfModelResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderInProgressController {
    private OrderinProgressListener orderinProgressListener;


    public OrderInProgressController(OrderinProgressListener orderinProgressListener) {
        this.orderinProgressListener = orderinProgressListener;
    }

    public void downloadPdf(String orderId) {

//        if (getMvpView().isNetworkConnected()) {
//            getMvpView().showLoading();
//            getMvpView().hideKeyboard();
        ApiInterface apiInterface = ApiClient.getApiService();
        PdfModelRequest reqModel = new PdfModelRequest();
        reqModel.setStoreCode(SessionManager.INSTANCE.getStoreId());
        reqModel.setTerminalID(SessionManager.INSTANCE.getTerminalId());
        reqModel.setDataAreaID("AHEL");
        reqModel.setRequestStatus(0);
        reqModel.setReturnMessage("");
        reqModel.setTransactionId(orderId);
        reqModel.setBillingType(5);
        reqModel.setDigitalReceiptRequired(false);
        Call<PdfModelResponse> call = apiInterface.DOWNLOAD_PDF(reqModel);
        call.enqueue(new Callback<PdfModelResponse>() {
            @Override
            public void onResponse(Call<PdfModelResponse> call, Response<PdfModelResponse> response) {
//                    getMvpView().hideLoading();
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
//                    getMvpView().hideLoading();
//                    handleApiError(t);
            }
        });


//        }
    }
}
