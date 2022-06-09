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
import com.apollo.pharmacy.ocr.utility.Utils;

import java.util.ArrayList;
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
            if (dataList.get(i).getArtCode().contains(",")){
                reOrderController.searchItemProducts(dataList.get(i).getArtCode().substring(0, dataList.get(i).getArtCode().indexOf(",")).toString(), i);
            }else {
                reOrderController.searchItemProducts(dataList.get(i).getArtCode().toString(), i);
            }

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

    private List<OCRToDigitalMedicineResponse> dummyDataList = new ArrayList<>();
    float overallBatchQuantity;

    int requiredBalanceQty;

    @Override
    public void setSuccessBatchList(BatchListResponse batchListResponse, int position, ItemSearchResponse.Item iteSearchData) {
        if (batchListResponse != null && batchListResponse.getBatchList() != null && batchListResponse.getBatchList().size() > 0) {
            for (int i = 0; i < batchListResponse.getBatchList().size(); i++) {
                overallBatchQuantity += Float.parseFloat(batchListResponse.getBatchList().get(i).getQOH());
            }
            flag = 0;
            for (int i = 0; i < batchListResponse.getBatchList().size(); i++) {
                if (Float.parseFloat(batchListResponse.getBatchList().get(i).getQOH()) >= Float.parseFloat(String.valueOf(dataList.get(position).getQty()))) {
                    flag = 1;
//                    dataList.get(position).setArtprice(String.valueOf(batchListResponse.getBatchList().get(i).getMrp()));

                    OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
                    data.setArtName(iteSearchData.getDescription());
                    data.setArtCode(iteSearchData.getArtCode() + "," + batchListResponse.getBatchList().get(i).getBatchNo());
                    data.setBatchId(batchListResponse.getBatchList().get(i).getBatchNo());
                    data.setArtprice(String.valueOf(batchListResponse.getBatchList().get(i).getMrp()));
                    data.setContainer("");
                    data.setQty(dataList.get(position).getQty());
                    data.setMedicineType(iteSearchData.getCategory());
                    dummyDataList.add(data);
                    break;
                } else if (overallBatchQuantity >= dataList.get(position).getQty()) {
                    if (dataList.get(position).getQty() > 0) {
                        OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
                        data.setArtName(iteSearchData.getDescription());
                        data.setArtCode(iteSearchData.getArtCode() + "," + batchListResponse.getBatchList().get(i).getBatchNo());
                        data.setBatchId(batchListResponse.getBatchList().get(i).getBatchNo());
                        data.setArtprice(String.valueOf(batchListResponse.getBatchList().get(i).getMrp()));
                        data.setContainer("");
                        data.setQty((int) Float.parseFloat(batchListResponse.getBatchList().get(i).getQOH()));
                        requiredBalanceQty = dataList.get(position).getQty() - 1;
                        dataList.get(position).setQty(requiredBalanceQty);
                        data.setMedicineType(iteSearchData.getCategory());
                        dummyDataList.add(data);
                    }
                }
            }
            if (flag == 0) {
//                dataList.get(position).setOutOfStock(true);

                OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
                data.setArtName(iteSearchData.getDescription());
                data.setArtCode(iteSearchData.getArtCode() + "," + batchListResponse.getBatchList().get(position).getBatchNo());
                data.setBatchId(batchListResponse.getBatchList().get(position).getBatchNo());
                data.setArtprice(String.valueOf(batchListResponse.getBatchList().get(position).getMrp()));
                data.setContainer("");
                data.setQty((int) Float.parseFloat(batchListResponse.getBatchList().get(position).getQOH()));
                dataList.get(position).setOutOfStock(true);
                data.setMedicineType(iteSearchData.getCategory());
                requiredBalanceQty = dataList.get(position).getQty() - 1;
                dataList.get(position).setQty(requiredBalanceQty);
                dummyDataList.add(data);


            }
        } else {
//            Toast.makeText(getContext(), "Product Out Of Stock", Toast.LENGTH_LONG).show();
//            dataList.get(position).setOutOfStock(true);

            OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
            data.setArtName(iteSearchData.getDescription());
            data.setArtCode(iteSearchData.getArtCode() + "," + batchListResponse.getBatchList().get(position).getBatchNo());
            data.setBatchId(batchListResponse.getBatchList().get(position).getBatchNo());
            data.setArtprice(String.valueOf(batchListResponse.getBatchList().get(position).getMrp()));
            data.setContainer("");
            data.setQty((int) Float.parseFloat(batchListResponse.getBatchList().get(position).getQOH()));
            dataList.get(position).setOutOfStock(true);
            data.setMedicineType(iteSearchData.getCategory());
            requiredBalanceQty = dataList.get(position).getQty() - 1;
            dataList.get(position).setQty(requiredBalanceQty);
            dummyDataList.add(data);
        }

//        if (dataList.size() - 1 == position) {
        alertDialogBoxBinding.medicinesRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        trackInfoAdaptor = new ReorderAdapter(context, dummyDataList);
        alertDialogBoxBinding.loadingPanel.setVisibility(View.GONE);
        alertDialogBoxBinding.medicinesRecyclerView.setAdapter(trackInfoAdaptor);
//        }
    }

    public List<OCRToDigitalMedicineResponse> dataListLatest() {
        for (int i = 0; i < dummyDataList.size(); i++) {
            if (dummyDataList.get(i).isOutOfStock()) {
                dummyDataList.remove(dummyDataList.get(i));
                i--;
            }
        }
        return dummyDataList;
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
            if (dataList.size()== position+1){
                dialog.dismiss();
                Utils.showSnackbarMessage(context, dialog.getWindow().getDecorView(), "No Item Found");
                alertDialogBoxBinding.loadingPanel.setVisibility(View.GONE);
            }
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
