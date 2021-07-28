package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Redeempoints_validateotp_retry_response {
    @SerializedName("OneApolloProcessResult")
    @Expose
    private OneApolloProcessResult oneApolloProcessResult;

    public OneApolloProcessResult getOneApolloProcessResult() {
        return oneApolloProcessResult;
    }

    public void setOneApolloProcessResult(OneApolloProcessResult oneApolloProcessResult) {
        this.oneApolloProcessResult = oneApolloProcessResult;
    }
}
