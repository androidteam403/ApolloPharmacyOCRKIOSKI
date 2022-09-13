package com.apollo.pharmacy.ocr.activities.checkout;

import android.content.Context;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.paymentoptions.model.ExpressCheckoutTransactionApiRequest;
import com.apollo.pharmacy.ocr.activities.paymentoptions.model.ExpressCheckoutTransactionApiResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivityController {
    private Context mContext;
    private CheckoutListener mListener;

    public CheckoutActivityController(Context mContext, CheckoutListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public void expressCheckoutTransactionApiCall(ExpressCheckoutTransactionApiRequest expressCheckoutTransactionApiRequest) {
        ApiInterface apiInterface = ApiClient.getApiService();
        Gson gson = new Gson();
        String json = gson.toJson(expressCheckoutTransactionApiRequest);
        Utils.showDialog(mContext, "Please wait...");
        System.out.println("EXPRESS_CHECKOUT_TRANSACTION_API_CALL_RQUEST===================" + json);
        Call<ExpressCheckoutTransactionApiResponse> call = apiInterface.EXPRESS_CHECKOUT_TRANSACTION_API_CALL(expressCheckoutTransactionApiRequest);
        call.enqueue(new Callback<ExpressCheckoutTransactionApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<ExpressCheckoutTransactionApiResponse> call, @NotNull Response<ExpressCheckoutTransactionApiResponse> response) {
                Utils.dismissDialog();
                if (response.body() != null) {
                    String jsonResponse = gson.toJson(response.body());
                    System.out.println("EXPRESS_CHECKOUT_TRANSACTION_API_CALL_RESPONSE===================" + jsonResponse);
                    if (response.body().getRequestStatus() == 0 || response.body().getRequestStatus() == 2) {
                        mListener.onSuccessexpressCheckoutTransactionApiCall(response.body());
                    } else {
                        mListener.onFailuremessage(response.body().getReturnMessage());
                    }
                } else {
                    mListener.onFailuremessage(mContext.getResources().getString(R.string.label_something_went_wrong));
                    Utils.dismissDialog();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ExpressCheckoutTransactionApiResponse> call, @NotNull Throwable t) {
                mListener.onFailuremessage(mContext.getResources().getString(R.string.label_something_went_wrong));
                Utils.dismissDialog();

            }
        });
    }
}
