package com.apollo.pharmacy.ocr.service;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.apollo.pharmacy.ocr.model.ScannedData;
import com.apollo.pharmacy.ocr.utility.ApplicationConstant;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.Map;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage == null)
            return;

        Utils.printMessage(TAG, "Data Payload :: " + remoteMessage.getData().toString());
        if (remoteMessage.getNotification() != null) {
            redirectPage(remoteMessage);
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Utils.printMessage(TAG, "Data Payload :: " + remoteMessage.getData().toString());
            redirectPage(remoteMessage);
        }
    }

    public void redirectPage(RemoteMessage remoteMessage) {
        Map<String, String> d = remoteMessage.getData();
        Utils.printMessage(TAG, "IMAGE_UPLOAD_ID :: " + d.get(ApplicationConstant.IMAGE_UPLOAD_ID));

        if (null != d.get(ApplicationConstant.IMAGE_UPLOAD_ID)) {
            SessionManager.INSTANCE.setImageUploadId(d.get(ApplicationConstant.IMAGE_UPLOAD_ID));
            Intent intent = new Intent("PrescriptionReceived");
            intent.putExtra("message", "ImageName");
            intent.putExtra("data", d.get("imageName"));
            intent.putExtra("imageId", d.get("imageUploadId"));
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        } else {
            ScannedData fcmMedicine = new Gson().fromJson(d.get("scanData"), ScannedData.class);
            Utils.printMessage(TAG, "onMessageReceived :: " + new Gson().toJson(fcmMedicine));
            if (SessionManager.INSTANCE.getImageUploadId().equals(fcmMedicine.getPrescriptionImageId())) {
                Utils.printMessage(TAG, "Session Manager Status --> " + SessionManager.INSTANCE.getCurrentPage());
                if (SessionManager.INSTANCE.getCurrentPage().equals(ApplicationConstant.ACTIVITY_ADDMORE)) {
                    Intent intent = new Intent("MedicineReciverAddMore");
                    intent.putExtra("message", "MedicinedataAddMore");
                    intent.putExtra("MedininesNames", new Gson().toJson(fcmMedicine));
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                } else if (SessionManager.INSTANCE.getCurrentPage().equals(ApplicationConstant.ACTIVITY_CHECKOUT)) {
                    Intent intent = new Intent("MedicineReciverCheckout");
                    intent.putExtra("message", "MedicinedataCheckout");
                    intent.putExtra("MedininesNames", new Gson().toJson(fcmMedicine));
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                } else {
                    SessionManager.INSTANCE.setFcmMedicineReceived(true);
                    Intent intent = new Intent("MedicineReciver");
                    intent.putExtra("message", "Medicinedata");
                    intent.putExtra("MedininesNames", new Gson().toJson(fcmMedicine));
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                }
            }
        }
    }
}
