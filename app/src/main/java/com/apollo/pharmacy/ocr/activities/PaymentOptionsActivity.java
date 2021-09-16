package com.apollo.pharmacy.ocr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.ActivityPaymentOptionsBinding;

public class PaymentOptionsActivity extends AppCompatActivity {
    private ActivityPaymentOptionsBinding activityPaymentOptionsBinding;
    private double pharmaTotalData = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentOptionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_options);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getIntent() != null) {
            pharmaTotalData = (double) getIntent().getDoubleExtra("fmcgTotal", 0.0);
        }

        activityPaymentOptionsBinding.pharmaTotal.setText(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotalData));

        listeners();
    }

    private void listeners() {
        activityPaymentOptionsBinding.scanToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.recievePaymentSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.recievePaymentSms.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.receivePaymentSmsInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.upi.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.upiInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.cashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.cashOnDelivery.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.cashOnDeliveryInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.scanToPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentOptionsActivity.this, OrderinProgressActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            }
        });
        paymentTicksUnticksHandlings();
    }

    private void unselectedBgClors() {
        activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
        activityPaymentOptionsBinding.recievePaymentSms.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
        activityPaymentOptionsBinding.upi.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
        activityPaymentOptionsBinding.cashOnDelivery.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
    }

    private void PaymentInfoLayoutsHandlings() {
        activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.GONE);
        activityPaymentOptionsBinding.receivePaymentSmsInfoLay.setVisibility(View.GONE);
        activityPaymentOptionsBinding.upiInfoLay.setVisibility(View.GONE);
        activityPaymentOptionsBinding.cashOnDeliveryInfoLay.setVisibility(View.GONE);
    }

    private void paymentTicksUnticksHandlings() {
        activityPaymentOptionsBinding.tickBhimLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPaymentOptionsBinding.tickBhim.setImageResource(R.drawable.tick_mark);
                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_tick_bg);
                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);

                activityPaymentOptionsBinding.tickUpi.setImageResource(0);
                activityPaymentOptionsBinding.tickPhonePay.setImageResource(0);


                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);

                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_untick_bg);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_untick_bg);

            }
        });
        activityPaymentOptionsBinding.tickUpiLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPaymentOptionsBinding.tickUpi.setImageResource(R.drawable.tick_mark);
                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_tick_bg);
                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);

                activityPaymentOptionsBinding.tickPhonePay.setImageResource(0);
                activityPaymentOptionsBinding.tickBhim.setImageResource(0);


                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);

                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_untick_bg);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_untick_bg);
            }
        });
        activityPaymentOptionsBinding.tickPhonePayLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPaymentOptionsBinding.tickPhonePay.setImageResource(R.drawable.tick_mark);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_tick_bg);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);

                activityPaymentOptionsBinding.tickUpi.setImageResource(0);
                activityPaymentOptionsBinding.tickBhim.setImageResource(0);

                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);

                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_untick_bg);
                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_untick_bg);

                activityPaymentOptionsBinding.upiInfoLay.setVisibility(View.GONE);
                activityPaymentOptionsBinding.scanPhonePayQrPayLay.setVisibility(View.VISIBLE);

            }
        });
    }
}