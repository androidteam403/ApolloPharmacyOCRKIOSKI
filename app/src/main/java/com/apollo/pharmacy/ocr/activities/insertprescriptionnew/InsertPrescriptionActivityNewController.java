package com.apollo.pharmacy.ocr.activities.insertprescriptionnew;

import android.content.Context;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertPrescriptionActivityNewController {
    private Context context;
    private InsertPrescriptionActivityNewListener mListener;

    public InsertPrescriptionActivityNewController(Context context, InsertPrescriptionActivityNewListener mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    public void handleOrderPlaceService(Context context, PlaceOrderReqModel placeOrderReqModel) {
        Utils.showDialog(context, "Please wait");
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Order_Place_With_Prescription_API);
        Call<PlaceOrderResModel> call = apiInterface.PLACE_ORDER_SERVICE_CALL(Constants.New_Order_Place_With_Prescription_Token, placeOrderReqModel);
        call.enqueue(new Callback<PlaceOrderResModel>() {
            @Override
            public void onResponse(@NotNull Call<PlaceOrderResModel> call, @NotNull Response<PlaceOrderResModel> response) {
                Utils.dismissDialog();
                assert response.body() != null;
                if (response.body().getOrdersResult().getStatus()) {
                    mListener.onSuccessPlaceOrder(response.body());
                } else {
                    mListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
                }
            }

            @Override
            public void onFailure(@NotNull Call<PlaceOrderResModel> call, @NotNull Throwable t) {
                Utils.dismissDialog();
                mListener.onFailureService(context.getResources().getString(R.string.label_something_went_wrong));
            }
        });
    }
}
