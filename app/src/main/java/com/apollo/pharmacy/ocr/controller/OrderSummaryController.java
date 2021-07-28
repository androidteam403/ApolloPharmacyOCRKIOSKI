package com.apollo.pharmacy.ocr.controller;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.interfaces.OrderSummaryListener;
import com.apollo.pharmacy.ocr.model.Reddemponits_getpoints_response;
import com.apollo.pharmacy.ocr.model.Reddemponits_sendotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_checkvoucher_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_checkvoucher_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_getpoints_requst;
import com.apollo.pharmacy.ocr.model.Redeempoints_redeemvoucher_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_redeemvoucher_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_resendotp_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_resendotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_sendotp_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_retry_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_retry_response;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.network.CallbackWithRetry;
import com.apollo.pharmacy.ocr.utility.Constants;

import retrofit2.Call;
import retrofit2.Response;

public class OrderSummaryController {

    private OrderSummaryListener listener;

    public OrderSummaryController(OrderSummaryListener listener) {
        this.listener = listener;
    }

    public void getredeempoints(Redeempoints_getpoints_requst requst) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Get_Redeem_Points);
        Call<Reddemponits_getpoints_response> call = apiInterface.getredeempoints(requst);
        call.enqueue(new CallbackWithRetry<Reddemponits_getpoints_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Reddemponits_getpoints_response> call, @NonNull Response<Reddemponits_getpoints_response> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessGetRedeemPoints(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Reddemponits_getpoints_response> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void redeempoints_sendotp(Redeempoints_sendotp_request requst) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Redeem_points_Send_Otp);
        Call<Reddemponits_sendotp_response> call = apiInterface.redeempoints_sendotp(requst);
        call.enqueue(new CallbackWithRetry<Reddemponits_sendotp_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Reddemponits_sendotp_response> call, @NonNull Response<Reddemponits_sendotp_response> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessRedeemPointsSendOtp(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Reddemponits_sendotp_response> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void redeempoints_resendotp(Redeempoints_resendotp_request requst) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Redeem_Points_Resend_Otp);
        Call<Redeempoints_resendotp_response> call = apiInterface.redeempoints_resendotp(requst);
        call.enqueue(new CallbackWithRetry<Redeempoints_resendotp_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Redeempoints_resendotp_response> call, @NonNull Response<Redeempoints_resendotp_response> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessRedeemPointsResendOtp(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Redeempoints_resendotp_response> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void redeempoints_validateotp(Redeempoints_validateotp_request requst) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Redeem_Points_Validate_Otp);
        Call<Redeempoints_validateotp_response> call = apiInterface.redeempoints_validateotp(requst);
        call.enqueue(new CallbackWithRetry<Redeempoints_validateotp_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Redeempoints_validateotp_response> call, @NonNull Response<Redeempoints_validateotp_response> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessRedeemPointsValidateOtp(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Redeempoints_validateotp_response> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void redeempoints_validateotp_retry(Redeempoints_validateotp_retry_request requst) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Redeem_points_Retry_Validate_Otp);
        Call<Redeempoints_validateotp_retry_response> call = apiInterface.redeempoints_validateotp_retry(requst);
        call.enqueue(new CallbackWithRetry<Redeempoints_validateotp_retry_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Redeempoints_validateotp_retry_response> call, @NonNull Response<Redeempoints_validateotp_retry_response> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessRedeemPointsValidateOtpRetry(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Redeempoints_validateotp_retry_response> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void redeempoints_checkvoucher(Redeempoints_checkvoucher_request requst) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Redeem_Points_Check_Voucher);
        Call<Redeempoints_checkvoucher_response> call = apiInterface.redeempoints_checkvoucher(requst);
        call.enqueue(new CallbackWithRetry<Redeempoints_checkvoucher_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Redeempoints_checkvoucher_response> call, @NonNull Response<Redeempoints_checkvoucher_response> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessRedeemPointsCheckVoucher(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Redeempoints_checkvoucher_response> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void redeempoints_redeemvoucher(Redeempoints_redeemvoucher_request requst) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.Redeem_Voucher);
        Call<Redeempoints_redeemvoucher_response> call = apiInterface.redeempoints_redeemvoucher(requst);
        call.enqueue(new CallbackWithRetry<Redeempoints_redeemvoucher_response>(call) {
            @Override
            public void onResponse(@NonNull Call<Redeempoints_redeemvoucher_response> call, @NonNull Response<Redeempoints_redeemvoucher_response> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessRedeemPointsRedeemVoucher(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Redeempoints_redeemvoucher_response> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
