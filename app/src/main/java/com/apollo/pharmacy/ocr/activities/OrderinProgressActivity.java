package com.apollo.pharmacy.ocr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.ActivityOrderinProgressBinding;
import com.apollo.pharmacy.ocr.interfaces.OrderinProgressListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderinProgressActivity extends AppCompatActivity implements OrderinProgressListener {

    private ActivityOrderinProgressBinding orderinProgressBinding;
    private List<OCRToDigitalMedicineResponse> dataList;
    private String fmcgOrderId;
    private String pharmaOrderId;
    private boolean onlineAmountPaid;
    private boolean isPharmadeliveryType;
    private boolean isFmcgDeliveryType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderinProgressBinding = DataBindingUtil.setContentView(this, R.layout.activity_orderin_progress);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        orderinProgressBinding.setCallback(this);
        setUp();
    }

    private void setUp() {
        if (getIntent() != null) {
            fmcgOrderId = (String) getIntent().getStringExtra("FmcgOrderPlacedData");
            pharmaOrderId = (String) getIntent().getStringExtra("PharmaOrderPlacedData");
            onlineAmountPaid = (boolean) getIntent().getBooleanExtra("OnlineAmountPaid", false);
            isPharmadeliveryType = (boolean) getIntent().getBooleanExtra("pharma_delivery_type", false);
            isFmcgDeliveryType = (boolean) getIntent().getBooleanExtra("fmcg_delivery_type", false);
        }
        orderinProgressBinding.fmcgRequestId.setText(fmcgOrderId);
        orderinProgressBinding.pharmaRequestId.setText(pharmaOrderId);
        if (onlineAmountPaid) {
            orderinProgressBinding.payTxt.setText("You Paid");
        } else {
            orderinProgressBinding.payTxt.setText("You Pay");
        }
        OrderinProgresssuiModel orderinProgresssuiModel = new OrderinProgresssuiModel();
        if (null != SessionManager.INSTANCE.getDataList())
            this.dataList = SessionManager.INSTANCE.getDataList();
        if (dataList != null && dataList.size() > 0) {

            List<OCRToDigitalMedicineResponse> countUniques = new ArrayList<>();
            countUniques.addAll(dataList);

            for (int i = 0; i < countUniques.size(); i++) {
                for (int j = 0; j < countUniques.size(); j++) {
                    if (i != j && countUniques.get(i).getArtName().equals(countUniques.get(j).getArtName())) {
                        countUniques.remove(j);
                        j--;
                    }
                }
            }

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
//                        pharmaMedicineCount++;
                        pharmaTotal = pharmaTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                    } else {
                        isFmcg = true;
//                        fmcgMedicineCount++;
                        fmcgTotal = fmcgTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                    }
                }
            }

            for (int i = 0; i < dataList.size(); i++) {
                for (int j = 0; j < countUniques.size(); j++) {
                    if (dataList.get(i).getArtName().equalsIgnoreCase(countUniques.get(j).getArtName())) {
                        if (countUniques.get(j).getMedicineType().equals("FMCG")) {
                            fmcgMedicineCount++;
                            countUniques.remove(j);
                            j--;
                        } else {
                            pharmaMedicineCount++;
                            countUniques.remove(j);
                            j--;
                        }
                    }
                }
            }

//            fmcgToatalPass = fmcgTotal;
            orderinProgresssuiModel.setPharmaCount(String.valueOf(pharmaMedicineCount));
            orderinProgresssuiModel.setFmcgCount(String.valueOf(fmcgMedicineCount));
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            String pharmaformatted = formatter.format(pharmaTotal);
            String fmcgFormatted = formatter.format(fmcgTotal);
            orderinProgresssuiModel.setPharmaTotal(getResources().getString(R.string.rupee) + String.valueOf(pharmaformatted));
            orderinProgresssuiModel.setFmcgTotal(getResources().getString(R.string.rupee) + String.valueOf(fmcgFormatted));
            orderinProgresssuiModel.setTotalMedicineCount(String.valueOf(dataList.size()));
            String totalprodAmt = formatter.format(pharmaTotal + fmcgTotal);

            orderinProgresssuiModel.setMedicineTotal(getResources().getString(R.string.rupee) + String.valueOf(totalprodAmt));
            orderinProgresssuiModel.setFmcgPharma(isPharma && isFmcg);
            orderinProgresssuiModel.setFmcg(isFmcg);
            orderinProgresssuiModel.setPharma(isPharma);
            orderinProgresssuiModel.setPharmaHomeDelivery(isPharmadeliveryType);
            orderinProgresssuiModel.setFmcgHomeDelivery(isFmcgDeliveryType);
            orderinProgressBinding.setModel(orderinProgresssuiModel);
            if (!isPharma && isFmcg) {
                orderinProgressBinding.orderisinProgressText.setText("Your order is Completed");
            }
            if (isPharma && isFmcg || isPharma) {
                orderinProgressBinding.orderisinProgressText.setText("Your order is in progress");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClickContinueShopping();
    }

    @Override
    public void onClickContinueShopping() {
        List<OCRToDigitalMedicineResponse> dataList = new ArrayList<>();
        SessionManager.INSTANCE.setDataList(dataList);
        Intent intent = new Intent(OrderinProgressActivity.this, MySearchActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        finish();
    }

    public class OrderinProgresssuiModel {
        private String pharmaCount;
        private String fmcgCount;
        private String fmcgTotal;
        private String pharmaTotal;
        private String totalMedicineCount;
        private String medicineTotal;
        private boolean isFmcgPharma;
        private boolean isFmcg;
        private boolean isPharma;
        private boolean isPharmaHomeDelivery;
        private boolean isFmcgHomeDelivery;

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

        public boolean isPharmaHomeDelivery() {
            return isPharmaHomeDelivery;
        }

        public void setPharmaHomeDelivery(boolean pharmaHomeDelivery) {
            isPharmaHomeDelivery = pharmaHomeDelivery;
        }

        public boolean isFmcgHomeDelivery() {
            return isFmcgHomeDelivery;
        }

        public void setFmcgHomeDelivery(boolean fmcgHomeDelivery) {
            isFmcgHomeDelivery = fmcgHomeDelivery;
        }
    }
}
