package com.apollo.pharmacy.ocr.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.ActivityReorderBinding;

public class ReOrderActivity extends AppCompatActivity {
    ActivityReorderBinding activityReorderBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReorderBinding = DataBindingUtil.setContentView(this, R.layout.activity_reorder);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
