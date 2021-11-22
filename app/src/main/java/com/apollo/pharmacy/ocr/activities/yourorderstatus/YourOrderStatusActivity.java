package com.apollo.pharmacy.ocr.activities.yourorderstatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.HomeActivity;
import com.apollo.pharmacy.ocr.activities.MySearchActivity;
import com.apollo.pharmacy.ocr.databinding.ActivityYourorderStatusBinding;
import com.apollo.pharmacy.ocr.utility.SessionManager;

public class YourOrderStatusActivity extends AppCompatActivity implements YourOrderStatusActivityListener {

    private ActivityYourorderStatusBinding activityYourorderStatusBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityYourorderStatusBinding = DataBindingUtil.setContentView(this, R.layout.activity_yourorder_status);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityYourorderStatusBinding.setCallback(this);
        String orderNo = null;
        String deliveryTypeName = null;
        if (getIntent() != null) {
            orderNo = (String) getIntent().getStringExtra("orderNo");
            deliveryTypeName = (String) getIntent().getStringExtra("deliveryTypeName");
        }
        activityYourorderStatusBinding.orderNo.setText(orderNo);
        activityYourorderStatusBinding.userNamePhoneNumber.setText(SessionManager.INSTANCE.getMobilenumber() != null ?  SessionManager.INSTANCE.getMobilenumber() : "--");
        activityYourorderStatusBinding.deliveryTypeName.setText(deliveryTypeName);
    }

    @Override
    public void onClickContinueShopping() {
        Intent intent = new Intent(YourOrderStatusActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}
