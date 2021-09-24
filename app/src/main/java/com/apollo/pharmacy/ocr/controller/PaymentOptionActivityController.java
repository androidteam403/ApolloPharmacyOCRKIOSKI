package com.apollo.pharmacy.ocr.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.PaymentSelectionListener;
import com.apollo.pharmacy.ocr.model.BatchListRequest;
import com.apollo.pharmacy.ocr.model.BatchListResponse;
import com.apollo.pharmacy.ocr.model.OrderPushingRequest;
import com.apollo.pharmacy.ocr.model.OrderPushingRequest_new;
import com.apollo.pharmacy.ocr.model.OrderPushingResponse;
import com.apollo.pharmacy.ocr.model.OrderPushingResponse_new;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeRequest;
import com.apollo.pharmacy.ocr.model.PinelabTransaction_cancelresponse;
import com.apollo.pharmacy.ocr.model.Pinelabs_paymenttransaction_response;
import com.apollo.pharmacy.ocr.model.Pinelabs_transaction_payment_request;
import com.apollo.pharmacy.ocr.model.Pinelabsrequest;
import com.apollo.pharmacy.ocr.model.Pinelabtransaction_cancelrequest;
import com.apollo.pharmacy.ocr.model.PinepayrequestResult;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.ApplicationConstant;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentOptionActivityController {

    PaymentSelectionListener paymentSelectionListener;

    public PaymentOptionActivityController(PaymentSelectionListener listInterface) {
        paymentSelectionListener = listInterface;
    }

    public void OrderPushingmethod(Context context, OrderPushingRequest request, PaymentSelectionListener paymentInterface) {
        Utils.showDialog(context, context.getResources().getString(R.string.label_please_wait));
        ApiInterface apiInterface = ApiClient.getApiService(ApplicationConstant.OrderPush_Url);
        Call<OrderPushingResponse> call = apiInterface.orderpushing(request);
        call.enqueue(new CallbackWithRetry<OrderPushingResponse>(call) {
            @Override
            public void onResponse(@NonNull Call<OrderPushingResponse> call, @NonNull Response<OrderPushingResponse> response) {
                Utils.dismissDialog();
                if (response.isSuccessful()) {
                    paymentInterface.onSuccessOrderPush(response.body());
                } else {
                    paymentInterface.onFailure("Cannot place the order. Please try again later...");
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderPushingResponse> call, @NonNull Throwable t) {
                Utils.dismissDialog();
                paymentInterface.onFailure("Cannot place the order. Please try again later...");
            }
        });
    }

    //new order push api.............................
    public static void OrderPushingmethod_new(Context context, OrderPushingRequest_new request, PaymentSelectionListener paymentInterface) {
        Utils.showDialog(context, context.getResources().getString(R.string.label_please_wait));
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Order_Pushing_Api);
        Call<OrderPushingResponse_new> call = apiInterface.orderpushing_new(request, "9f15bdd0fcd5423190c2e877ba0228APM");
        ;
        call.enqueue(new CallbackWithRetry<OrderPushingResponse_new>(call) {
            @Override
            public void onResponse(@NonNull Call<OrderPushingResponse_new> call, @NonNull Response<OrderPushingResponse_new> response) {
                Utils.dismissDialog();
                if (response.isSuccessful()) {
                    paymentInterface.onSuccessOrderPushNew(response.body());
                } else {
                    paymentInterface.onFailure("Cannot place the order. Please try again later...");
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderPushingResponse_new> call, @NonNull Throwable t) {
                Utils.dismissDialog();
                paymentInterface.onFailure("Cannot place the order. Please try again later...");
            }
        });
    }

    public void paytmtransactionMethod(Context context, String siteid, PaymentSelectionListener paymentInterface) {
        Utils.showDialog(context, context.getResources().getString(R.string.label_please_wait));
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Paytm_Payment_Transaction);
        Call<Object> call = apiInterface.paytmtransactionapi(siteid);
        call.enqueue(new CallbackWithRetry<Object>(call) {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                Utils.dismissDialog();
                if (response.isSuccessful()) {
                    paymentInterface.onSuccessPaytmTransaction(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                Utils.dismissDialog();
                paymentInterface.onFailurePaytmTransaction(t.getMessage());
            }
        });
    }

    public static void cardswipefunctionality(Context context, PaymentSelectionListener paymentInterface, Pinelabsrequest request) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Pinelab_Upload_Transaction);
        Call<PinepayrequestResult> call = apiInterface.pinelabstransaction(request);
        call.enqueue(new CallbackWithRetry<PinepayrequestResult>(call) {
            @Override
            public void onResponse(@NonNull Call<PinepayrequestResult> call, @NonNull Response<PinepayrequestResult> response) {
                paymentInterface.onSuccessPinelabsTransaction(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<PinepayrequestResult> call, @NonNull Throwable t) {
                paymentInterface.onFailure(t.getMessage());
            }
        });
    }

    public static void paymentstatusrequest(Context context, PaymentSelectionListener paymentInterface, Pinelabs_transaction_payment_request request) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Pinelab_Get_Cloud_Bases_Transaction);
        Call<Pinelabs_paymenttransaction_response> call = apiInterface.pinelabstpaymenttransaction(request);
        call.enqueue(new CallbackWithRetry<Pinelabs_paymenttransaction_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Pinelabs_paymenttransaction_response> call, @NonNull Response<Pinelabs_paymenttransaction_response> response) {
                paymentInterface.onSuccessPinelabsPaymentTransaction(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Pinelabs_paymenttransaction_response> call, @NonNull Throwable t) {
                paymentInterface.onFailure(t.getMessage());
            }
        });
    }

    public static void pinelabcanceltransaction(Context context, PaymentSelectionListener paymentInterface, Pinelabtransaction_cancelrequest request) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Pinelab_Cancel_Transaction);
        Call<PinelabTransaction_cancelresponse> call = apiInterface.pinelabscanceltransaction(request);
        call.enqueue(new CallbackWithRetry<PinelabTransaction_cancelresponse>(call) {
            @Override
            public void onResponse(@NonNull Call<PinelabTransaction_cancelresponse> call, @NonNull Response<PinelabTransaction_cancelresponse> response) {
                paymentInterface.OnonSuccessPinelabsCancelTransaction(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<PinelabTransaction_cancelresponse> call, @NonNull Throwable t) {
                paymentInterface.onFailure(t.getMessage());
            }
        });
    }
}
