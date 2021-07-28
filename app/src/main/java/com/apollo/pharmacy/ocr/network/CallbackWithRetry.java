package com.apollo.pharmacy.ocr.network;

import com.apollo.pharmacy.ocr.utility.Utils;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class CallbackWithRetry<T> implements Callback<T> {

    private static final int TOTAL_RETRIES = 3;
    private static final String TAG = CallbackWithRetry.class.getSimpleName();
    private final Call<T> call;
    private int retryCount = 0;

    public CallbackWithRetry(Call<T> call) {
        this.call = call;
    }

    public void onFailure(Throwable t) {
        Utils.printMessage(TAG, t.getLocalizedMessage());
        if (retryCount++ < TOTAL_RETRIES) {
            retry();
        }
    }

    private void retry() {
        call.clone().enqueue(this);
    }
}

