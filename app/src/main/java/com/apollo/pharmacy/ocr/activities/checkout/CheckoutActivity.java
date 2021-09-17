package com.apollo.pharmacy.ocr.activities.checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.PaymentOptionsActivity;
import com.apollo.pharmacy.ocr.databinding.ActivityCheckoutBinding;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;

import java.io.Serializable;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements CheckoutListener {
    private ActivityCheckoutBinding activityCheckoutBinding;
    private List<OCRToDigitalMedicineResponse> dataList;

    public static Intent getStartIntent(Context context, List<OCRToDigitalMedicineResponse> dataList) {
        Intent intent = new Intent(context, CheckoutActivity.class);
        intent.putExtra("dataList", (Serializable) dataList);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCheckoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityCheckoutBinding.setCallback(this);
        setUp();

    }

    double fmcgToatalPass = 0.0;

    private void setUp() {
        if (getIntent() != null) {
            dataList = (List<OCRToDigitalMedicineResponse>) getIntent().getSerializableExtra("dataList");
            if (dataList != null && dataList.size() > 0) {
                int pharmaMedicineCount = 0;
                int fmcgMedicineCount = 0;
                double pharmaTotal = 0.0;
                double fmcgTotal = 0.0;
                for (OCRToDigitalMedicineResponse data : dataList) {
                    if (data.getMedicineType() != null) {
                        if (data.getMedicineType().equals("PHARMA")) {
                            pharmaMedicineCount++;
                            pharmaTotal = pharmaTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        } else {
                            fmcgMedicineCount++;
                            fmcgTotal = fmcgTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        }
                    }
                }
                fmcgToatalPass = fmcgTotal;
                CheckoutuiModel checkoutuiModel = new CheckoutuiModel();
                checkoutuiModel.setPharmaCount(String.valueOf(pharmaMedicineCount));
                checkoutuiModel.setFmcgCount(String.valueOf(fmcgMedicineCount));
                checkoutuiModel.setPharmaTotal(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotal));
                checkoutuiModel.setFmcgTotal(getResources().getString(R.string.rupee) + String.valueOf(fmcgTotal));
                checkoutuiModel.setTotalMedicineCount(String.valueOf(dataList.size()));
                checkoutuiModel.setMedicineTotal(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotal + fmcgTotal));
                activityCheckoutBinding.setModel(checkoutuiModel);
            }
        }
    }

    @Override
    public void onClickNeedHomeDelivery() {
//        deliveryModeHandle();
        activityCheckoutBinding.payandCollectatCounter.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.payandCollectatCounterText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.payandCollectatCounterImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

        activityCheckoutBinding.needHomeDelivery.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.needHomeDeliveryText.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.needHomeDeliveryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));

    }

    @Override
    public void onClickPayandCollectatCounter() {
//        deliveryModeHandle();
        activityCheckoutBinding.needHomeDelivery.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.needHomeDeliveryText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.needHomeDeliveryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

        activityCheckoutBinding.payandCollectatCounter.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.payandCollectatCounterText.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.payandCollectatCounterImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));

    }

    @Override
    public void onClickNeedHomeDelivery1() {
//        deliveryModeHandle();
        activityCheckoutBinding.payhereandCarry.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.payHereAndcarryText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.payHereAndcarryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

        activityCheckoutBinding.needHomeDelivery1.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.needHomeDelivery1Text.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.needHomeDelivery1Img.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));
    }

    @Override
    public void onClickPayhereandCarry() {
//        deliveryModeHandle();
        activityCheckoutBinding.needHomeDelivery1.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.needHomeDelivery1Text.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.needHomeDelivery1Img.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

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
        Intent intent = new Intent(CheckoutActivity.this, PaymentOptionsActivity.class);
        intent.putExtra("fmcgTotal", fmcgToatalPass);
        startActivity(intent);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
    }

    private void deliveryModeHandle() {
        activityCheckoutBinding.needHomeDelivery.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
//        activityCheckoutBinding.payandCollectatCounter.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.needHomeDelivery1.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.payhereandCarry.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));

        activityCheckoutBinding.needHomeDeliveryText.setTextColor(getResources().getColor(R.color.grey_color));
//        activityCheckoutBinding.payandCollectatCounterText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.needHomeDelivery1Text.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.payHereAndcarryText.setTextColor(getResources().getColor(R.color.grey_color));

        activityCheckoutBinding.needHomeDeliveryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));
//        activityCheckoutBinding.payandCollectatCounterImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));
        activityCheckoutBinding.needHomeDelivery1Img.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));
        activityCheckoutBinding.payHereAndcarryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

    }

    public class CheckoutuiModel {
        private String pharmaCount;
        private String fmcgCount;
        private String fmcgTotal;
        private String pharmaTotal;
        private String totalMedicineCount;
        private String medicineTotal;

        public String getPharmaCount() {
            return pharmaCount;
        }

        public void setPharmaCount(String pharmaCount) {
            this.pharmaCount = pharmaCount;
        }

        public String getFmcgCount() {
            return fmcgCount;
        }

        public void setFmcgCount(String fmcgCount) {
            this.fmcgCount = fmcgCount;
        }

        public String getFmcgTotal() {
            return fmcgTotal;
        }

        public void setFmcgTotal(String fmcgTotal) {
            this.fmcgTotal = fmcgTotal;
        }

        public String getPharmaTotal() {
            return pharmaTotal;
        }

        public void setPharmaTotal(String pharmaTotal) {
            this.pharmaTotal = pharmaTotal;
        }

        public String getTotalMedicineCount() {
            return totalMedicineCount;
        }

        public void setTotalMedicineCount(String totalMedicineCount) {
            this.totalMedicineCount = totalMedicineCount;
        }

        public String getMedicineTotal() {
            return medicineTotal;
        }

        public void setMedicineTotal(String medicineTotal) {
            this.medicineTotal = medicineTotal;
        }
    }
}
/*  double amount = Double.parseDouble(String.valueOf(getProducts.getOfferprice()));
 *DecimalFormat formatter = new DecimalFormat("#,###.00");
 *String formatted = formatter.format(amount);*/