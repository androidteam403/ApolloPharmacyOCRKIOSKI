package com.apollo.pharmacy.ocr.activities.insertprescriptionnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.yourorderstatus.YourOrderStatusActivity;
import com.apollo.pharmacy.ocr.databinding.ActivityNewInsertPrescriptionBinding;
import com.apollo.pharmacy.ocr.dialog.ChooseDeliveryType;

public class InsertPrescriptionActivityNew extends AppCompatActivity implements InsertPrescriptionActivityNewListener {
    private ActivityNewInsertPrescriptionBinding activityNewInsertPrescriptionBinding;
//    private ScaleGestureDetector mScaleGestureDetector;
//    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewInsertPrescriptionBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_insert_prescription);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityNewInsertPrescriptionBinding.setCallback(this);
//        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.apollo_prescription_image);
        activityNewInsertPrescriptionBinding.prescriptionFullviewImg.setImageBitmap(bImage);
        activityNewInsertPrescriptionBinding.prescriptionFullviewImg.setMaxZoom(4f);
        listeners();
    }

    private void listeners() {
        activityNewInsertPrescriptionBinding.scanStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backCountTimer();
            }
        });
        activityNewInsertPrescriptionBinding.plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yourCountDownTimer != null) {
                    yourCountDownTimer.cancel();
                }
                activityNewInsertPrescriptionBinding.scanStart.setVisibility(View.VISIBLE);
                activityNewInsertPrescriptionBinding.timeStart.setVisibility(View.GONE);
                activityNewInsertPrescriptionBinding.startPrescriptionLay.setVisibility(View.VISIBLE);
                activityNewInsertPrescriptionBinding.scannedLay.setVisibility(View.GONE);
            }
        });
        activityNewInsertPrescriptionBinding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDeliveryType chooseDeliveryType = new ChooseDeliveryType(InsertPrescriptionActivityNew.this);

                chooseDeliveryType.setPositiveListener(view1 -> {
                    activityNewInsertPrescriptionBinding.transColorId.setVisibility(View.GONE);
                    Toast.makeText(InsertPrescriptionActivityNew.this, "Delivery Type Selected", Toast.LENGTH_SHORT).show();
                    chooseDeliveryType.dismiss();
                    Intent intent = new Intent(InsertPrescriptionActivityNew.this, YourOrderStatusActivity.class);
                    startActivity(intent);
                });
                chooseDeliveryType.setNegativeListener(v -> {
                    activityNewInsertPrescriptionBinding.transColorId.setVisibility(View.GONE);
                    chooseDeliveryType.dismiss();
                });
                chooseDeliveryType.show();
            }
        });
    }


    CountDownTimer yourCountDownTimer;

    public void backCountTimer() {
        yourCountDownTimer = new CountDownTimer(30000, 1000) {

            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                activityNewInsertPrescriptionBinding.scanStart.setVisibility(View.GONE);
                activityNewInsertPrescriptionBinding.timeStart.setVisibility(View.VISIBLE);
                activityNewInsertPrescriptionBinding.timeStart.setText("" + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {
                activityNewInsertPrescriptionBinding.startPrescriptionLay.setVisibility(View.GONE);
                activityNewInsertPrescriptionBinding.scannedLay.setVisibility(View.VISIBLE);
            }
        }.start();

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return mScaleGestureDetector.onTouchEvent(event);
//    }
//
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//
//        // when a scale gesture is detected, use it to resize the image
//        @Override
//        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
//            mScaleFactor *= scaleGestureDetector.getScaleFactor();
//            activityNewInsertPrescriptionBinding.prescriptionFullviewImg.setScaleX(mScaleFactor);
//            activityNewInsertPrescriptionBinding.prescriptionFullviewImg.setScaleY(mScaleFactor);
//            return true;
//        }
//    }

    @Override
    public void onClickPrescription() {
        activityNewInsertPrescriptionBinding.closeFullviewPrescription.setVisibility(View.VISIBLE);
        activityNewInsertPrescriptionBinding.scannedLay.setVisibility(View.GONE);
        activityNewInsertPrescriptionBinding.prescriptionFullviewLayout.setVisibility(View.VISIBLE);
        activityNewInsertPrescriptionBinding.zoominZoomoutLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCloseFullviewPrescription() {
        activityNewInsertPrescriptionBinding.scannedLay.setVisibility(View.VISIBLE);
        activityNewInsertPrescriptionBinding.closeFullviewPrescription.setVisibility(View.GONE);
        activityNewInsertPrescriptionBinding.prescriptionFullviewLayout.setVisibility(View.GONE);
        activityNewInsertPrescriptionBinding.zoominZoomoutLayout.setVisibility(View.GONE);

    }

    @Override
    public void onClickZoomIn() {
        activityNewInsertPrescriptionBinding.prescriptionFullviewImg.zoomIn();
    }

    @Override
    public void onClickZoomOut() {
        activityNewInsertPrescriptionBinding.prescriptionFullviewImg.zoomOut();
    }
}
