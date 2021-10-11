package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.controller.ReOrderController;
import com.apollo.pharmacy.ocr.databinding.ActivityReorderBinding;
import com.apollo.pharmacy.ocr.dialog.adapter.ReorderAdapter;
import com.apollo.pharmacy.ocr.interfaces.ReOrderListener;
import com.apollo.pharmacy.ocr.model.BatchListResponse;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;

import java.util.List;

public class ReOrderDilaog implements ReOrderListener {
    private Dialog dialog;
    private ActivityReorderBinding alertDialogBoxBinding;

    private boolean negativeExist = false;
    private ReorderAdapter trackInfoAdaptor;
    private List<OCRToDigitalMedicineResponse> dataList;
    private Context context;

    public ReOrderDilaog(Context context, List<OCRToDigitalMedicineResponse> dataList) {
        dialog = new Dialog(context);
        this.context = context;
        this.dataList = dataList;
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        alertDialogBoxBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.activity_reorder, null, false);
        dialog.setContentView(alertDialogBoxBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        ReOrderController reOrderController = new ReOrderController(this, context);
        alertDialogBoxBinding.loadingPanel.setVisibility(View.VISIBLE);
        for (int i = 0; i < dataList.size(); i++) {
            reOrderController.searchItemProducts(dataList.get(i).getArtCode().toString(), i);
        }
    }


    public void setPositiveListener(View.OnClickListener okListener) {
        alertDialogBoxBinding.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        alertDialogBoxBinding.dialogButtonNO.setOnClickListener(okListener);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    private int flag = 0;

    @Override
    public void setSuccessBatchList(BatchListResponse batchListResponse, int position) {
        if (batchListResponse != null && batchListResponse.getBatchList() != null && batchListResponse.getBatchList().size() > 0) {
            for (int i = 0; i < batchListResponse.getBatchList().size(); i++) {
                if (Float.parseFloat(batchListResponse.getBatchList().get(i).getQOH()) >= Float.parseFloat(String.valueOf(dataList.get(position).getQty()))) {
                    flag = 1;
                    dataList.get(position).setArtprice(String.valueOf(batchListResponse.getBatchList().get(i).getMrp()));
                }
            }
            if (flag == 0) {
                dataList.get(position).setOutOfStock(true);
            }
        } else {
//            Toast.makeText(getContext(), "Product Out Of Stock", Toast.LENGTH_LONG).show();
            dataList.get(position).setOutOfStock(true);
        }

        if (dataList.size() - 1 == position) {
            alertDialogBoxBinding.medicinesRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            trackInfoAdaptor = new ReorderAdapter(context, dataList);
            alertDialogBoxBinding.loadingPanel.setVisibility(View.GONE);
            alertDialogBoxBinding.medicinesRecyclerView.setAdapter(trackInfoAdaptor);
        }
    }

    public List<OCRToDigitalMedicineResponse> dataListLatest() {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).isOutOfStock()) {
                dataList.remove(dataList.get(i));
                i--;
            }
        }
        return dataList;
    }

    @Override
    public void onSuccessSearchItemApi(ItemSearchResponse itemSearchResponse, int position) {
        if (itemSearchResponse != null && itemSearchResponse.getItemList() != null && itemSearchResponse.getItemList().size() > 0) {
            for (int i = 0; i < itemSearchResponse.getItemList().size(); i++) {
                dataList.get(position).setMedicineType(String.valueOf(itemSearchResponse.getItemList().get(i).getCategory()));
                dataList.get(position).setArtName(String.valueOf(itemSearchResponse.getItemList().get(i).getDescription()));
            }
        } else {
            dataList.get(position).setOutOfStock(true);
        }
    }

    @Override
    public void onSearchFailure(String message) {

    }


//    public void setTitle(String title) {
//        alertDialogBoxBinding.title.setText(title);
//    }
//    public void setPositiveLabel(String positive) {
//        alertDialogBoxBinding.btnYes.setText(positive);
//    }
//
//    public void setNegativeLabel(String negative) {
//        negativeExist = true;
//        alertDialogBoxBinding.btnNo.setText(negative);
//    }

}
