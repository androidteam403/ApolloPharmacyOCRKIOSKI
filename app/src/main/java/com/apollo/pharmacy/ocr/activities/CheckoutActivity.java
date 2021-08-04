package com.apollo.pharmacy.ocr.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.ActivityCheckoutBinding;
import com.apollo.pharmacy.ocr.interfaces.CheckoutListener;

public class CheckoutActivity extends AppCompatActivity implements CheckoutListener {
    private ActivityCheckoutBinding activityCheckoutBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CheckoutActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCheckoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityCheckoutBinding.setCallback(this);

    }

    @Override
    public void onClickNeedHomeDelivery() {
        deliveryModeHandle();
        activityCheckoutBinding.needHomeDelivery.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.needHomeDeliveryText.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.needHomeDeliveryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));

    }

    @Override
    public void onClickPayandCollectatCounter() {
        deliveryModeHandle();
        activityCheckoutBinding.payandCollectatCounter.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.payandCollectatCounterText.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.payandCollectatCounterImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));

    }

    @Override
    public void onClickNeedHomeDelivery1() {
        deliveryModeHandle();
        activityCheckoutBinding.needHomeDelivery1.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.needHomeDelivery1Text.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.needHomeDelivery1Img.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));
    }

    @Override
    public void onClickPayhereandCarry() {
        deliveryModeHandle();
        activityCheckoutBinding.payhereandCarry.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.payHereAndcarryText.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.payHereAndcarryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onClickPaynow() {
        Toast.makeText(this, "You clicked on Paynow", Toast.LENGTH_SHORT).show();
    }

    private void deliveryModeHandle() {
        activityCheckoutBinding.needHomeDelivery.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.payandCollectatCounter.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.needHomeDelivery1.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.payhereandCarry.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));

        activityCheckoutBinding.needHomeDeliveryText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.payandCollectatCounterText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.needHomeDelivery1Text.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.payHereAndcarryText.setTextColor(getResources().getColor(R.color.grey_color));

        activityCheckoutBinding.needHomeDeliveryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));
        activityCheckoutBinding.payandCollectatCounterImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));
        activityCheckoutBinding.needHomeDelivery1Img.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));
        activityCheckoutBinding.payHereAndcarryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

    }
}
