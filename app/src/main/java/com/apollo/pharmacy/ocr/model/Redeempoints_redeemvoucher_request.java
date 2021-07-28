package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Redeempoints_redeemvoucher_request {
   /* @SerializedName("OneApolloProcessResult")
    @Expose
    private OneApolloProcessResult oneApolloProcessResult;

    public OneApolloProcessResult getOneApolloProcessResult() {
        return oneApolloProcessResult;
    }

    public void setOneApolloProcessResult(OneApolloProcessResult oneApolloProcessResult) {
        this.oneApolloProcessResult = oneApolloProcessResult;
    }*/

    @SerializedName("RequestData")
    @Expose
    private RequestData requestData;

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }


}
