package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.Reddemponits_getpoints_response;
import com.apollo.pharmacy.ocr.model.Reddemponits_sendotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_checkvoucher_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_redeemvoucher_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_resendotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_retry_response;

public interface OrderSummaryListener {

    void onFailure(String error);

    void onSuccessGetRedeemPoints(Reddemponits_getpoints_response response);

    void onSuccessRedeemPointsSendOtp(Reddemponits_sendotp_response response);

    void onSuccessRedeemPointsResendOtp(Redeempoints_resendotp_response response);

    void onSuccessRedeemPointsValidateOtp(Redeempoints_validateotp_response response);

    void onSuccessRedeemPointsValidateOtpRetry(Redeempoints_validateotp_retry_response response);

    void onSuccessRedeemPointsCheckVoucher(Redeempoints_checkvoucher_response response);

    void onSuccessRedeemPointsRedeemVoucher(Redeempoints_redeemvoucher_response response);
}
