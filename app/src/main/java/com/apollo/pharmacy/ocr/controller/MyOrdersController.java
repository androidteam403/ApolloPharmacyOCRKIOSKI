package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.MyOrdersListener;
import com.apollo.pharmacy.ocr.model.OrderHistoryRequest;
import com.apollo.pharmacy.ocr.model.OrderHistoryResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MyOrdersController {
    MyOrdersListener myOrdersListener;

    public MyOrdersController(MyOrdersListener myOrdersListener) {
        this.myOrdersListener = myOrdersListener;
    }

    public void getOrderHistory(Context context) {
        OrderHistoryRequest request = new OrderHistoryRequest();
        SessionManager.INSTANCE.getMobilenumber();
        request.setMobNo(SessionManager.INSTANCE.getMobilenumber());//SessionManager.INSTANCE.getMobilenumber()
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Get_Order_History_For_User);
        Utils.showDialog(context, context.getResources().getString(R.string.label_fetching_order_history));
        Call<List<OrderHistoryResponse>> call = apiInterface.getOrderHistory("YXV0aF91c2VyOnN1cGVyc2VjcmV0X3Rhd", request);
        call.enqueue(new CallbackWithRetry<List<OrderHistoryResponse>>(call) {
            @Override

            public void onResponse(@NonNull Call<List<OrderHistoryResponse>> call, @NonNull Response<List<OrderHistoryResponse>> response) {
                Utils.dismissDialog();
                myOrdersListener.onOrderHistorySuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<OrderHistoryResponse>> call, @NonNull Throwable throwable) {
                Utils.dismissDialog();
                myOrdersListener.onOrderHistoryFailure(throwable.getMessage());
            }
        });
    }
}
