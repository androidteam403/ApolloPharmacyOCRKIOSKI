package com.apollo.pharmacy.ocr.controller;

import com.apollo.pharmacy.ocr.interfaces.UploadBgImageListener;
import com.apollo.pharmacy.ocr.model.UploadImageRequest;
import com.apollo.pharmacy.ocr.model.UploadImageResponse;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadBgImageController {

    UploadBgImageListener uploadBgImageListener;

    public UploadBgImageController(UploadBgImageListener listInterface) {
        uploadBgImageListener = listInterface;
    }

    public void handleUploadImageService(String imagePath) {
        ApiInterface apiInterface = ApiClient.getApiService(Constants.UPLOAD_IMAGE_URL + "/");
        UploadImageRequest request = new UploadImageRequest();
        request.setMobileno(SessionManager.INSTANCE.getMobilenumber());
        String userName = SessionManager.INSTANCE.getLoggedUserName();
        if (userName.isEmpty()) {
            userName = "Guest";
        }
        request.setFirstname(userName);
        request.setLastname(userName);
        request.setKioskid(SessionManager.INSTANCE.getKioskSetupResponse().getKIOSK_ID());
        request.setStoreid(SessionManager.INSTANCE.getKioskSetupResponse().getSTORE());
        //request.setPresc_id(SessionManager.INSTANCE.getDynamicOrderId());
        SessionManager.INSTANCE.setDynamicOrderId(Utils.getOrderedID());
        request.setPresc_id(SessionManager.INSTANCE.getDynamicOrderId());
        ArrayList<UploadImageRequest.ImageurlEntity> imageUrlEntityArr = new ArrayList<>();
        UploadImageRequest.ImageurlEntity imageUrlEntity = new UploadImageRequest.ImageurlEntity();
        imageUrlEntity.setUrl(imagePath);
        imageUrlEntityArr.add(imageUrlEntity);
        request.setImageurl(imageUrlEntityArr);
        Call<UploadImageResponse> call = apiInterface.getUploadPrescriptionService(Constants.UPLOAD_IMAGE_URL, request);
        call.enqueue(new Callback<UploadImageResponse>() {
            @Override
            public void onResponse(@NotNull Call<UploadImageResponse> call, @NotNull Response<UploadImageResponse> response) {
                Utils.dismissDialog();
                uploadBgImageListener.onSuccessUploadBgImage();
            }

            @Override
            public void onFailure(@NotNull Call<UploadImageResponse> call, @NotNull Throwable t) {
                Utils.dismissDialog();
            }
        });
    }
}
