package com.apollo.pharmacy.ocr.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.ActivityPaymentOptionsBinding;
import com.apollo.pharmacy.ocr.databinding.ActivityYourorderStatusBinding;

public class YourOrderStatusActivity extends AppCompatActivity {

    private ActivityYourorderStatusBinding activityYourorderStatusBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityYourorderStatusBinding = DataBindingUtil.setContentView(this, R.layout.activity_yourorder_status);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
