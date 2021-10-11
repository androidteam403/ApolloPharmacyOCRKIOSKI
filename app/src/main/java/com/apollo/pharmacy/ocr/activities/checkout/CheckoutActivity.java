package com.apollo.pharmacy.ocr.activities.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.PaymentOptionsActivity;
import com.apollo.pharmacy.ocr.databinding.ActivityCheckoutBinding;
import com.apollo.pharmacy.ocr.dialog.DeliveryAddressDialog;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import java.text.DecimalFormat;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements CheckoutListener {
    private ActivityCheckoutBinding activityCheckoutBinding;
    private List<OCRToDigitalMedicineResponse> dataList;
    private boolean isPharmaHomeDelivery = false;
    private boolean isFmcgHomeDelivery = false;


//    public static Intent getStartIntent(Context context, List<OCRToDigitalMedicineResponse> dataList) {
//        Intent intent = new Intent(context, CheckoutActivity.class);
//        intent.putExtra("dataList", (Serializable) dataList);
//        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        return intent;
//    }

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
        dataList = SessionManager.INSTANCE.getDataList();
        if (dataList != null && dataList.size() > 0) {
//            dataList = (List<OCRToDigitalMedicineResponse>) getIntent().getSerializableExtra("dataList");
            if (dataList != null && dataList.size() > 0) {
                int pharmaMedicineCount = 0;
                int fmcgMedicineCount = 0;
                double pharmaTotal = 0.0;
                double fmcgTotal = 0.0;
                boolean isFmcg = false;
                boolean isPharma = false;
                for (OCRToDigitalMedicineResponse data : dataList) {
                    if (data.getMedicineType() != null) {
                        if (data.getMedicineType().equals("PHARMA")) {
                            isPharma = true;
                            pharmaMedicineCount++;
                            pharmaTotal = pharmaTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        } else {
                            isFmcg = true;
                            fmcgMedicineCount++;
                            fmcgTotal = fmcgTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        }
                    }
                }
                fmcgToatalPass = fmcgTotal;
                CheckoutuiModel checkoutuiModel = new CheckoutuiModel();
                checkoutuiModel.setPharmaCount(String.valueOf(pharmaMedicineCount));
                checkoutuiModel.setFmcgCount(String.valueOf(fmcgMedicineCount));
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                String pharmaformatted = formatter.format(pharmaTotal);
                String fmcgFormatted = formatter.format(fmcgTotal);
                checkoutuiModel.setPharmaTotal(getResources().getString(R.string.rupee) + String.valueOf(pharmaformatted));
                checkoutuiModel.setFmcgTotal(getResources().getString(R.string.rupee) + String.valueOf(fmcgFormatted));
                checkoutuiModel.setTotalMedicineCount(String.valueOf(dataList.size()));
                String totalprodAmt = formatter.format(pharmaTotal + fmcgTotal);
                checkoutuiModel.setMedicineTotal(getResources().getString(R.string.rupee) + String.valueOf(totalprodAmt));
                checkoutuiModel.setFmcgPharma(isPharma && isFmcg);
                checkoutuiModel.setFmcg(isFmcg);
                checkoutuiModel.setPharma(isPharma);
                activityCheckoutBinding.setModel(checkoutuiModel);
            }
        }

        activityCheckoutBinding.reviewCartPharma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
            }
        });
        activityCheckoutBinding.reviewCartFmcg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
            }
        });
    }

    String address;
    String name, singleAdd, pincode, city, state;

    @Override
    public void onClickNeedHomeDelivery() {
//        deliveryModeHandle();
        isPharmaHomeDelivery = true;
        activityCheckoutBinding.payandCollectatCounter.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.payandCollectatCounterText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.payandCollectatCounterImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

        activityCheckoutBinding.needHomeDelivery.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.needHomeDeliveryText.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.needHomeDeliveryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));

        if (address == null) {
            DeliveryAddressDialog deliveryAddressDialog = new DeliveryAddressDialog(CheckoutActivity.this);
            deliveryAddressDialog.setPositiveListener(view -> {
                if (deliveryAddressDialog.validations()) {
                    address = deliveryAddressDialog.getAddressData();
                    name = deliveryAddressDialog.getName();
                    singleAdd = deliveryAddressDialog.getAddress();
                    pincode = deliveryAddressDialog.getPincode();
                    city = deliveryAddressDialog.getCity();
                    state = deliveryAddressDialog.getState();
                    deliveryAddressDialog.dismiss();
                }
            });
            deliveryAddressDialog.setNegativeListener(view -> {
                deliveryAddressDialog.dismiss();
            });
            deliveryAddressDialog.show();
        }
    }

    @Override
    public void onClickPayandCollectatCounter() {
//        deliveryModeHandle();
        isPharmaHomeDelivery = false;
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
        isFmcgHomeDelivery = true;
        activityCheckoutBinding.payhereandCarry.setBackground(getResources().getDrawable(R.drawable.bg_lite_grey));
        activityCheckoutBinding.payHereAndcarryText.setTextColor(getResources().getColor(R.color.grey_color));
        activityCheckoutBinding.payHereAndcarryImg.setImageDrawable(getResources().getDrawable(R.drawable.tick_white));

        activityCheckoutBinding.needHomeDelivery1.setBackground(getResources().getDrawable(R.drawable.blackstroke_yellowsolid));
        activityCheckoutBinding.needHomeDelivery1Text.setTextColor(getResources().getColor(R.color.black));
        activityCheckoutBinding.needHomeDelivery1Img.setImageDrawable(getResources().getDrawable(R.drawable.tick_green));
        if (address == null) {
            DeliveryAddressDialog deliveryAddressDialog = new DeliveryAddressDialog(CheckoutActivity.this);
            deliveryAddressDialog.setPositiveListener(view -> {
                if (deliveryAddressDialog.validations()) {
                    address = deliveryAddressDialog.getAddressData();
                    name = deliveryAddressDialog.getName();
                    singleAdd = deliveryAddressDialog.getAddress();
                    pincode = deliveryAddressDialog.getPincode();
                    city = deliveryAddressDialog.getCity();
                    state = deliveryAddressDialog.getState();
                    deliveryAddressDialog.dismiss();
                }
            });
            deliveryAddressDialog.setNegativeListener(view -> {
                deliveryAddressDialog.dismiss();
            });
            deliveryAddressDialog.show();
        }
    }

    @Override
    public void onClickPayhereandCarry() {
//        deliveryModeHandle();
        isFmcgHomeDelivery = false;
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
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    @Override
    public void onClickPaynow() {
        if (isPharmaHomeDelivery || isFmcgHomeDelivery) {
            if (address != null) {
                Intent intent = new Intent(CheckoutActivity.this, PaymentOptionsActivity.class);
                intent.putExtra("fmcgTotal", fmcgToatalPass);
                intent.putExtra("isPharmaHomeDelivery", isPharmaHomeDelivery);
                intent.putExtra("isFmcgHomeDelivery", isFmcgHomeDelivery);
                intent.putExtra("customerDeliveryAddress", address);
                intent.putExtra("name", name);
                intent.putExtra("singleAdd", singleAdd);
                intent.putExtra("pincode", pincode);
                intent.putExtra("city", city);
                intent.putExtra("state", state);
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            } else {
//                Toast.makeText(this, "Please Fill Address Form", Toast.LENGTH_SHORT).show();

                if (address == null) {
                    DeliveryAddressDialog deliveryAddressDialog = new DeliveryAddressDialog(CheckoutActivity.this);
                    deliveryAddressDialog.setPositiveListener(view -> {
                        if (deliveryAddressDialog.validations()) {
                            address = deliveryAddressDialog.getAddressData();
                            name = deliveryAddressDialog.getName();
                            singleAdd = deliveryAddressDialog.getAddress();
                            pincode = deliveryAddressDialog.getPincode();
                            city = deliveryAddressDialog.getCity();
                            state = deliveryAddressDialog.getState();
                            deliveryAddressDialog.dismiss();
                        }
                    });
                    deliveryAddressDialog.setNegativeListener(view -> {
                        deliveryAddressDialog.dismiss();
                    });
                    deliveryAddressDialog.show();
                }

            }
        } else {
            Intent intent = new Intent(CheckoutActivity.this, PaymentOptionsActivity.class);
            intent.putExtra("fmcgTotal", fmcgToatalPass);
            intent.putExtra("isPharmaHomeDelivery", isPharmaHomeDelivery);
            intent.putExtra("isFmcgHomeDelivery", isFmcgHomeDelivery);
            intent.putExtra("customerDeliveryAddress", address);
            intent.putExtra("name", name);
            intent.putExtra("singleAdd", singleAdd);
            intent.putExtra("pincode", pincode);
            intent.putExtra("city", city);
            intent.putExtra("state", state);
            startActivity(intent);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        }
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
        private boolean isFmcgPharma;
        private boolean isFmcg;
        private boolean isPharma;

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

        public boolean isFmcgPharma() {
            return isFmcgPharma;
        }

        public void setFmcgPharma(boolean fmcgPharma) {
            isFmcgPharma = fmcgPharma;
        }

        public boolean isFmcg() {
            return isFmcg;
        }

        public void setFmcg(boolean fmcg) {
            isFmcg = fmcg;
        }

        public boolean isPharma() {
            return isPharma;
        }

        public void setPharma(boolean pharma) {
            isPharma = pharma;
        }
    }
}
/*  double amount = Double.parseDouble(String.valueOf(getProducts.getOfferprice()));
 *DecimalFormat formatter = new DecimalFormat("#,###.00");
 *String formatted = formatter.format(amount);*/