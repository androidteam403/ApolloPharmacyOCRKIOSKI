package com.apollo.pharmacy.ocr.activities.yourorderstatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.MySearchActivity;
import com.apollo.pharmacy.ocr.databinding.ActivityYourorderStatusBinding;

public class YourOrderStatusActivity extends AppCompatActivity implements YourOrderStatusActivityListener {

    private ActivityYourorderStatusBinding activityYourorderStatusBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityYourorderStatusBinding = DataBindingUtil.setContentView(this, R.layout.activity_yourorder_status);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityYourorderStatusBinding.setCallback(this);
    }

    @Override
    public void onClickContinueShopping() {
        Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(YourOrderStatusActivity.this, MySearchActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
    }
}
