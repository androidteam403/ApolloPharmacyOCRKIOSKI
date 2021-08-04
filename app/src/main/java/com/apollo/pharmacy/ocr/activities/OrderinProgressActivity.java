package com.apollo.pharmacy.ocr.activities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.ActivityOrderinProgressBinding;
import com.apollo.pharmacy.ocr.interfaces.OrderinProgressListener;

public class OrderinProgressActivity extends AppCompatActivity implements OrderinProgressListener {

    private ActivityOrderinProgressBinding orderinProgressBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderinProgressBinding = DataBindingUtil.setContentView(this, R.layout.activity_orderin_progress);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        orderinProgressBinding.setCallback(this);
    }

    @Override
    public void onClickContinueShopping() {
        Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show();
    }
}
