package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.controller.MposBatchListController;
import com.apollo.pharmacy.ocr.databinding.DialogItemBatchSelectionBinding;
import com.apollo.pharmacy.ocr.dialog.adapter.AdapterItemBatchSelection;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.interfaces.MposBatchListListener;
import com.apollo.pharmacy.ocr.model.BatchListResponse;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class ItemBatchSelectionDilaog implements AdapterItemBatchSelection.OnItemBatchClick, MposBatchListListener {

    private Dialog dialog;
    private DialogItemBatchSelectionBinding dialogItemBatchSelectionBinding;
    private boolean negativeExist = false;
    private ConstraintLayout constraintLayout;
    private Context context;

    public ItemBatchSelectionDilaog(Product product, Context context, int position, List<OCRToDigitalMedicineResponse> datalist, CartCountListener cartCountListener) {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogItemBatchSelectionBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_item_batch_selection, null, false);
        dialog.setContentView(dialogItemBatchSelectionBinding.getRoot());

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        batchlistVisbiblityHandling();
        for (OCRToDigitalMedicineResponse data : datalist) {
            if (data.getArtCode() != null) {
                if (data.getArtCode().equalsIgnoreCase(product.getSku())) {
//                    itemAddToCartLayout.setVisibility(View.VISIBLE);
//                    inc_dec_card.setVisibility(View.VISIBLE);
                    dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf(data.getQty()));
                    cartCountListener.cartCount(datalist.size());
                }
            }
        }

//        if (cartCountListener == null) {
//                Utils.showSnackbarLinear(mContext, parentLayout, "Add Cart null reference ");
//                return;
//            }
//            itemAddToCartLayout.setVisibility(View.GONE);
//            inc_dec_card.setVisibility(View.VISIBLE);
        dialogItemBatchSelectionBinding.unitCount.setText("1");

        boolean product_avilable = false;
        if (null != datalist) {
            int count = 0;
            for (OCRToDigitalMedicineResponse data : datalist) {
                if (data.getArtCode() != null) {
                    if (data.getArtCode().equalsIgnoreCase(product.getSku())) {
                        product_avilable = true;
                        int qty = data.getQty();
//                            qty = qty + 1;
                        datalist.remove(count);
                        OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                        object1.setArtName(product.getName());
                        object1.setArtCode(product.getSku());
                        if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                            object1.setArtprice(product.getSpecialPrice());
                        } else {
                            object1.setArtprice(String.valueOf(product.getPrice()));
                        }
                        object1.setQty(qty);
                        object1.setMou(product.getMou());
                        object1.setContainer("Strip");
                        datalist.add(object1);
                        dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf(qty));
                        SessionManager.INSTANCE.setDataList(datalist);
                        break;
                    } else {
                        product_avilable = false;
                    }
                }
                count = count + 1;
            }
            if (!product_avilable) {
                OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                object1.setArtName(product.getName());
                object1.setArtCode(product.getSku());
                if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                    object1.setArtprice(product.getSpecialPrice());
                } else {
                    object1.setArtprice(String.valueOf(product.getPrice()));
                }
                object1.setQty(1);
                object1.setMou(product.getMou());
                object1.setContainer("Strip");
                datalist.add(object1);
                SessionManager.INSTANCE.setDataList(datalist);
                cartCountListener.cartCount(datalist.size());
                dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf("1"));
            }
        }

        Intent intent = new Intent("UpdateCartItemCount");
        intent.putExtra("message", "CartCount");
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

//            itemAddToCartLayout.setVisibility(View.GONE);
//            inc_dec_card.setVisibility(View.VISIBLE);


        dialogItemBatchSelectionBinding.unitIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(dialogItemBatchSelectionBinding.unitCount.getText().toString());
                qty = qty + 1;
                String product_sku = product.getSku();
                int count = 0;
                for (OCRToDigitalMedicineResponse data : datalist) {
                    if (product_sku.equalsIgnoreCase(data.getArtCode())) {
                        datalist.remove(count);
                        break;
                    }
                    count = count + 1;
                }
                OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                object1.setArtName(product.getName());
                object1.setArtCode(product.getSku());
                if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                    object1.setArtprice(product.getSpecialPrice());
                } else {
                    object1.setArtprice(String.valueOf(product.getPrice()));
                }
                object1.setQty(qty);
                object1.setMou(product.getMou());
                object1.setContainer("Strip");
                datalist.add(object1);
                dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf(qty));
                cartCountListener.cartCount(datalist.size());
                SessionManager.INSTANCE.setDataList(datalist);

                Intent intent = new Intent("UpdateCartItemCount");
                intent.putExtra("message", "CartCount");
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
            }
        });
        dialogItemBatchSelectionBinding.unitDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(dialogItemBatchSelectionBinding.unitCount.getText().toString());
                if (qty > 1) {
                    qty = qty - 1;
                    int count = 0;
                    String product_code = product.getSku();
                    for (OCRToDigitalMedicineResponse data : datalist) {
                        if (product_code.equalsIgnoreCase(data.getArtCode())) {
                            datalist.remove(count);
                            break;
                        }
                        count = count + 1;
                    }
                    OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                    object1.setArtName(product.getName());
                    object1.setArtCode(product.getSku());
                    if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                        object1.setArtprice(product.getSpecialPrice());
                    } else {
                        object1.setArtprice(String.valueOf(product.getPrice()));
                    }
                    object1.setQty(qty);
                    object1.setMou(product.getMou());
                    object1.setContainer("Strip");
                    datalist.add(object1);

                    SessionManager.INSTANCE.setDataList(datalist);
                    Intent intent = new Intent("UpdateCartItemCount");
                    intent.putExtra("message", "CartCount");
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                    dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf(qty));
                } else {
                    int count = 0;
                    String product_code = product.getSku();
                    for (OCRToDigitalMedicineResponse data : datalist) {
                        if (product_code.equalsIgnoreCase(data.getArtCode())) {
                            datalist.remove(count);
                            break;
                        }
                        count = count + 1;
                    }
                    SessionManager.INSTANCE.setDataList(datalist);
//                    itemAddToCartLayout.setVisibility(View.VISIBLE);
//                    inc_dec_card.setVisibility(View.GONE);
//                    if (itemAddToCartLayout.getVisibility() == View.VISIBLE) {
//                        cartCountListener.cartCount(datalist.size());
//                    }
                    Intent intent = new Intent("UpdateCartItemCount");
                    intent.putExtra("message", "CartCount");
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                }
            }
        });
//        batchSelection();

        MposBatchListController batchListController = new MposBatchListController(this, context);
//        batchListController.getBatchList();
    }

    public ItemBatchSelectionDilaog(Context context, String articalCode) {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogItemBatchSelectionBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_item_batch_selection, null, false);
        dialog.setContentView(dialogItemBatchSelectionBinding.getRoot());
        this.context = context;
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
//        batchSelection();
        batchlistVisbiblityHandling();
        MposBatchListController batchListController = new MposBatchListController(this, context);
        dialogItemBatchSelectionBinding.loadingPanel.setVisibility(View.VISIBLE);
        batchListController.getBatchList(articalCode);

        dialogItemBatchSelectionBinding.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = dialog.getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(dialog.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

    }

    private AdapterItemBatchSelection adapterItemBatchSelection;
    private List<ItemBatchSelectionData> itemBatchSelectionDataList = new ArrayList<>();

    private void batchSelection() {
        ItemBatchSelectionData itemBatchSelectionData = new ItemBatchSelectionData();
        itemBatchSelectionData.setDate("06 jan 2021");
        itemBatchSelectionData.setPrice("123.55");
        itemBatchSelectionDataList.add(itemBatchSelectionData);

        itemBatchSelectionData = new ItemBatchSelectionData();
        itemBatchSelectionData.setDate("26 feb 2021");
        itemBatchSelectionData.setPrice("453.65");
        itemBatchSelectionDataList.add(itemBatchSelectionData);

        itemBatchSelectionData = new ItemBatchSelectionData();
        itemBatchSelectionData.setDate("22 jul 2021");
        itemBatchSelectionData.setPrice("53.65");
        itemBatchSelectionDataList.add(itemBatchSelectionData);

        itemBatchSelectionData = new ItemBatchSelectionData();
        itemBatchSelectionData.setDate("30 feb 2021");
        itemBatchSelectionData.setPrice("153.32");
        itemBatchSelectionDataList.add(itemBatchSelectionData);

//        dialogItemBatchSelectionBinding.batchSelectionData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogItemBatchSelectionBinding.recyclerViewLay.setVisibility(View.VISIBLE);
//                adapterItemBatchSelection = new AdapterItemBatchSelection(getContext(), itemBatchSelectionDataList,ItemBatchSelectionDilaog.this);
//                RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                dialogItemBatchSelectionBinding.batchListRecycle.setLayoutManager(mLayoutManager2);
//                dialogItemBatchSelectionBinding.batchListRecycle.setItemAnimator(new DefaultItemAnimator());
//                dialogItemBatchSelectionBinding.batchListRecycle.setAdapter(adapterItemBatchSelection);
//                dialogItemBatchSelectionBinding.batchListRecycle.setNestedScrollingEnabled(false);
//            }
//        });
    }

    public BatchListResponse getBatchAvilableData() {
        return batchListResponse;
    }

    public void setPositiveListener(View.OnClickListener okListener) {
        dialogItemBatchSelectionBinding.dialogButtonOK.setOnClickListener(okListener);
    }

    public void setUnitIncreaseListener(View.OnClickListener setIncreaseListener) {
        dialogItemBatchSelectionBinding.unitIncrease.setOnClickListener(setIncreaseListener);
    }

    public void setUnitDecreaseListener(View.OnClickListener setDecreaseListener) {
        dialogItemBatchSelectionBinding.unitDecrease.setOnClickListener(setDecreaseListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        dialogItemBatchSelectionBinding.dialogButtonNO.setOnClickListener(okListener);
    }

    public void show() {
       if (dialog!=null){
           dialog.show();
       }
    }

    public void dismiss() {
        dialog.dismiss();
    }


    public void setTitle(String title) {
        dialogItemBatchSelectionBinding.title.setText(title);
    }

    public void setQtyCount(String count) {
        dialogItemBatchSelectionBinding.unitCount.setText(count);
    }

    public String getQtyCount() {
        return dialogItemBatchSelectionBinding.unitCount.getText().toString();
    }

    public BatchListResponse.Batch getItemBatchSelectionDataQty() {
        return itemBatchSelectionDataQtyCompare;
    }

    private double itemPrice;

    BatchListResponse.Batch itemBatchSelectionDataQtyCompare;

    @Override
    public void onItemBatchClickData(int position, BatchListResponse.Batch itemBatchSelectionData) {
        if (!itemBatchSelectionData.getNearByExpiry()) {
            itemBatchSelectionDataQtyCompare = itemBatchSelectionData;
            dialogItemBatchSelectionBinding.dialogItemBatchInnerParentLayout.setBackground(getContext().getResources().getDrawable(R.drawable.dialog_background));
            dialogItemBatchSelectionBinding.recyclerViewLay.setVisibility(View.GONE);
            dialogItemBatchSelectionBinding.date.setText(itemBatchSelectionData.getExpDate());
            dialogItemBatchSelectionBinding.price.setText(String.valueOf(itemBatchSelectionData.getPrice()));
            dialogItemBatchSelectionBinding.itemId.setText(itemBatchSelectionData.getItemID());
            dialogItemBatchSelectionBinding.batchNo.setText(itemBatchSelectionData.getBatchNo());

            this.itemPrice = itemBatchSelectionData.getPrice();
        }
//        else {
//            View view = dialog.getWindow().getDecorView();
//            Utils.showSnackbarDialog(context, view, "Item not available in batch list");
//        }
    }

    public float getTotalBatchQty() {
        return overallBatchQuantity;
    }

    private BatchListResponse batchListResponse;
    float overallBatchQuantity;

    @Override
    public void setSuccessBatchList(BatchListResponse batchListResponse) {
        if (batchListResponse != null && batchListResponse.getBatchList() != null && batchListResponse.getBatchList().size() > 0) {
            this.batchListResponse = batchListResponse;

            for (int i = 0; i < batchListResponse.getBatchList().size(); i++) {
                overallBatchQuantity += Float.parseFloat(batchListResponse.getBatchList().get(i).getQOH());
            }

            if (batchListResponse != null && batchListResponse.getBatchList() != null && batchListResponse.getBatchList().size() > 0) {
                if (SessionManager.INSTANCE.getBatchId() != null && !SessionManager.INSTANCE.getBatchId().isEmpty()) {
                    for (int i = 0; i < batchListResponse.getBatchList().size(); i++) {
                        if (batchListResponse.getBatchList().get(i).getBatchNo().equals(SessionManager.INSTANCE.getBatchId())) {
                            dialogItemBatchSelectionBinding.date.setText(batchListResponse.getBatchList().get(i).getExpDate());
                            dialogItemBatchSelectionBinding.price.setText(String.valueOf(batchListResponse.getBatchList().get(i).getPrice()));
                            dialogItemBatchSelectionBinding.itemId.setText(batchListResponse.getBatchList().get(i).getItemID());
                            dialogItemBatchSelectionBinding.batchNo.setText(batchListResponse.getBatchList().get(i).getBatchNo());
                            this.itemPrice = batchListResponse.getBatchList().get(i).getPrice();
                            this.itemBatchSelectionDataQtyCompare = batchListResponse.getBatchList().get(i);
                        }
                    }
                } else {
                    dialogItemBatchSelectionBinding.date.setText(batchListResponse.getBatchList().get(0).getExpDate());
                    dialogItemBatchSelectionBinding.price.setText(String.valueOf(batchListResponse.getBatchList().get(0).getPrice()));
                    dialogItemBatchSelectionBinding.itemId.setText(batchListResponse.getBatchList().get(0).getItemID());
                    dialogItemBatchSelectionBinding.batchNo.setText(batchListResponse.getBatchList().get(0).getBatchNo());
                    this.itemPrice = batchListResponse.getBatchList().get(0).getPrice();
                    this.itemBatchSelectionDataQtyCompare = batchListResponse.getBatchList().get(0);
                }
                dialogItemBatchSelectionBinding.loadingPanel.setVisibility(View.GONE);
                dialogItemBatchSelectionBinding.batchSelectionData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                    dialogItemBatchSelectionBinding.dialogItemBatchInnerParentLayout.setBackground(getContext().getResources().getDrawable(R.drawable.dialog_item_batch_inner_parent_layout_bg));
                        dialogItemBatchSelectionBinding.recyclerViewLay.setVisibility(View.VISIBLE);
                        adapterItemBatchSelection = new AdapterItemBatchSelection(getContext(), batchListResponse.getBatchList(), ItemBatchSelectionDilaog.this);
                        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        dialogItemBatchSelectionBinding.batchListRecycle.setLayoutManager(mLayoutManager2);
                        dialogItemBatchSelectionBinding.batchListRecycle.setItemAnimator(new DefaultItemAnimator());
                        dialogItemBatchSelectionBinding.batchListRecycle.setAdapter(adapterItemBatchSelection);
                        dialogItemBatchSelectionBinding.batchListRecycle.setNestedScrollingEnabled(false);
                    }
                });
                Utils.dismissDialog();
            } else {
                Utils.dismissDialog();
                dialog.dismiss();
                View view = dialog.getWindow().getDecorView();
                Utils.showSnackbarDialog(context, view, "Product Out Of Stock");
                dialogItemBatchSelectionBinding.loadingPanel.setVisibility(View.GONE);
            }
        } else {
            View view = dialog.getWindow().getDecorView();
            Utils.showSnackbarDialog(context, view, "Item not available in batch list");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Utils.dismissDialog();
                    dialog.dismiss();
                    dialogItemBatchSelectionBinding.loadingPanel.setVisibility(View.GONE);
                }
            }, 1500);
        }
    }

    public double getItemProice() {
        return itemPrice;
    }

    @Override
    public void onFailureBatchList() {

    }

    private void batchlistVisbiblityHandling() {
        dialogItemBatchSelectionBinding.itemselectBatchlistDialogParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dialogItemBatchSelectionBinding.recyclerViewLay.setVisibility(View.GONE);

                return false;
            }
        });
    }

//    public void setPositiveLabel(String positive) {
//        alertDialogBoxBinding.btnYes.setText(positive);
//    }
//
//    public void setNegativeLabel(String negative) {
//        negativeExist = true;
//        alertDialogBoxBinding.btnNo.setText(negative);
//    }


    public class ItemBatchSelectionData {
        private String date;
        private String price;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    List<OCRToDigitalMedicineResponse> dummyDataList = new ArrayList<>();
    private float balanceQty;
    boolean checkduplicate1 = false;
    boolean checkduplicate2 = false;
    boolean checkduplicate3 = false;

    public void globalBatchListHandlings(String itemName, String itemId, float balanceQtying, List<OCRToDigitalMedicineResponse> dummyDataListing, Context context, String medicineType) {

        if (!getItemBatchSelectionDataQty().getNearByExpiry()) {
            if (getBatchAvilableData() != null && getBatchAvilableData().getBatchList() != null && getBatchAvilableData().getBatchList().size() > 0) {
                if (getQtyCount() != null && !getQtyCount().isEmpty() && Integer.parseInt(getQtyCount()) > 0) {
                    if (getTotalBatchQty() >= Float.parseFloat(getQtyCount())) {
                        if (Float.parseFloat(getItemBatchSelectionDataQty().getQOH()) >= Float.parseFloat(getQtyCount())) {
                            OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
                            data.setArtName(TextUtils.isEmpty(itemName) ? "-" : itemName);
                            data.setArtCode(itemId + "," + getItemBatchSelectionDataQty().getBatchNo());
                            data.setBatchId(getItemBatchSelectionDataQty().getBatchNo());
                            data.setArtprice(String.valueOf(getItemBatchSelectionDataQty().getPrice()));
                            data.setContainer("");
                            data.setQty(Integer.parseInt(getQtyCount().toString()));
                            if (SessionManager.INSTANCE.getDataList() != null) {
                                for (OCRToDigitalMedicineResponse proQtyIncorg : Objects.requireNonNull(SessionManager.INSTANCE.getDataList())) {
                                    if (proQtyIncorg.getArtCode().equals(data.getArtCode())) {
                                        data.setQty(proQtyIncorg.getQty() + Integer.parseInt(getQtyCount().toString()));
                                    }
                                }
                            }
                            data.setMedicineType(medicineType);
                            dummyDataList.add(data);

                            if (null != SessionManager.INSTANCE.getDataList()) {
                                if (SessionManager.INSTANCE.getDataList().size() > 0) {
                                    List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                                    tempCartItemList = SessionManager.INSTANCE.getDataList();
                                    for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
                                        boolean isItemEqual = false;
                                        for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
                                            if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
                                                isItemEqual = true;
                                            }
                                        }
                                        if (!isItemEqual)
                                            dummyDataList.add(listItem);
                                    }
                                }
                            }
                            SessionManager.INSTANCE.setDataList(dummyDataList);
                            Intent intent = new Intent("OrderhistoryCardReciver");
                            intent.putExtra("message", "OrderNow");
                            intent.putExtra("MedininesNames", new Gson().toJson(dummyDataList));
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                            if (itemBatchListDialogListener != null) {
                                itemBatchListDialogListener.onDismissDialog();
                            }
                            dismiss();

                        } else {
                            OCRToDigitalMedicineResponse data1 = new OCRToDigitalMedicineResponse();

                            data1.setArtName(TextUtils.isEmpty(itemName) ? "-" : itemName);
                            data1.setArtCode(itemId + "," + getItemBatchSelectionDataQty().getBatchNo());
                            data1.setBatchId(getItemBatchSelectionDataQty().getBatchNo());
                            data1.setArtprice(String.valueOf(getItemBatchSelectionDataQty().getPrice()));
                            data1.setContainer("");
                            data1.setQty((int) Float.parseFloat(getItemBatchSelectionDataQty().getQOH().toString()));
                            balanceQty = Float.parseFloat(getQtyCount()) - Float.parseFloat(String.valueOf(data1.getQty()));
                            data1.setMedicineType(medicineType);
                            getItemBatchSelectionDataQty().setBatchQtySelected(true);

                            for (OCRToDigitalMedicineResponse proQtyIncorg : SessionManager.INSTANCE.getDataList()) {
                                if (proQtyIncorg.getArtCode().equals(data1.getArtCode())) {
                                    data1.setQty(proQtyIncorg.getQty() + Integer.parseInt(String.valueOf(data1.getQty())));
                                }
                            }
                            if (dummyDataList != null && dummyDataList.size() > 0) {
                                for (int k = 0; k < dummyDataList.size(); k++) {
                                    if (dummyDataList.get(k).getArtCode().equalsIgnoreCase(data1.getArtCode())) {
                                        checkduplicate1 = true;
                                        dummyDataList.get(k).setQty(data1.getQty());
                                        dummyDataList.set(k, dummyDataList.get(k));
                                    }
                                }
                            }
                            if (!checkduplicate1) {
                                dummyDataList.add(data1);
                            }

                            if (null != SessionManager.INSTANCE.getDataList()) {
                                if (SessionManager.INSTANCE.getDataList().size() > 0) {
                                    List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                                    tempCartItemList = SessionManager.INSTANCE.getDataList();
                                    for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
                                        boolean isItemEqual = false;
                                        for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
                                            if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
                                                isItemEqual = true;
                                            }
                                        }
                                        if (!isItemEqual)
                                            dummyDataList.add(listItem);
                                    }
                                }
                            }

                            for (int i = 0; i < getBatchAvilableData().getBatchList().size(); i++) {
                                if (balanceQty != 0) {
                                    if (Float.parseFloat(getBatchAvilableData().getBatchList().get(i).getQOH()) >= balanceQty && !getBatchAvilableData().getBatchList().get(i).isBatchQtySelected()) {
                                        OCRToDigitalMedicineResponse data2 = new OCRToDigitalMedicineResponse();

                                        data2.setArtName(TextUtils.isEmpty(itemName) ? "-" : itemName);
                                        data2.setArtCode(itemId + "," + getBatchAvilableData().getBatchList().get(i).getBatchNo());
                                        data2.setBatchId(getBatchAvilableData().getBatchList().get(i).getBatchNo());
                                        data2.setArtprice(String.valueOf(getBatchAvilableData().getBatchList().get(i).getPrice()));
                                        data2.setContainer("");

                                        if (balanceQty >= Float.parseFloat(getBatchAvilableData().getBatchList().get(i).getQOH())) {
                                            data2.setQty((int) Float.parseFloat(getBatchAvilableData().getBatchList().get(i).getQOH().toString()));
                                        } else {
                                            data2.setQty((int) balanceQty);
                                            balanceQty = 0;
                                        }
                                        balanceQty = balanceQty - Float.parseFloat(String.valueOf(data2.getQty()));
                                        data2.setMedicineType(medicineType);

                                        for (OCRToDigitalMedicineResponse proQtyIncorg : SessionManager.INSTANCE.getDataList()) {
                                            if (proQtyIncorg.getArtCode().equals(data2.getArtCode())) {
                                                data2.setQty(proQtyIncorg.getQty() + Integer.parseInt(String.valueOf(data2.getQty())));
                                            }
                                        }
                                        if (dummyDataList != null && dummyDataList.size() > 0) {
                                            for (int k = 0; k < dummyDataList.size(); k++) {
                                                if (dummyDataList.get(k).getArtCode().equalsIgnoreCase(data2.getArtCode())) {
                                                    checkduplicate2 = true;
                                                    dummyDataList.get(k).setQty(data2.getQty());
                                                    dummyDataList.set(k, dummyDataList.get(k));
                                                }
                                            }
                                        }
                                        if (!checkduplicate2) {
                                            dummyDataList.add(data2);
                                        }


                                        if (null != SessionManager.INSTANCE.getDataList()) {
                                            if (SessionManager.INSTANCE.getDataList().size() > 0) {
                                                List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                                                tempCartItemList = SessionManager.INSTANCE.getDataList();
                                                for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
                                                    boolean isItemEqual = false;
                                                    for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
                                                        if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
                                                            isItemEqual = true;
                                                        }
                                                    }
                                                    if (!isItemEqual)
                                                        dummyDataList.add(listItem);
                                                }
                                            }
                                        }
                                        break;
                                    } else {
                                        if (!getBatchAvilableData().getBatchList().get(i).isBatchQtySelected() && balanceQty != 0) {
                                            OCRToDigitalMedicineResponse data3 = new OCRToDigitalMedicineResponse();
                                            data3.setArtName(TextUtils.isEmpty(itemName) ? "-" : itemName);
                                            data3.setArtCode(itemId + "," + getBatchAvilableData().getBatchList().get(i).getBatchNo());
                                            data3.setBatchId(getBatchAvilableData().getBatchList().get(i).getBatchNo());
                                            data3.setArtprice(String.valueOf(getBatchAvilableData().getBatchList().get(i).getPrice()));
                                            data3.setContainer("");
                                            if (balanceQty >= Float.parseFloat(getBatchAvilableData().getBatchList().get(i).getQOH())) {
                                                data3.setQty((int) Float.parseFloat(getBatchAvilableData().getBatchList().get(i).getQOH().toString()));
                                            } else {
                                                data3.setQty((int) balanceQty);
                                                balanceQty = 0;
                                            }
                                            balanceQty = balanceQty - Float.parseFloat(String.valueOf(data3.getQty()));
                                            data3.setMedicineType(medicineType);

                                            for (OCRToDigitalMedicineResponse proQtyIncorg : SessionManager.INSTANCE.getDataList()) {
                                                if (proQtyIncorg.getArtCode().equals(data3.getArtCode())) {
                                                    data3.setQty(proQtyIncorg.getQty() + Integer.parseInt(String.valueOf(data3.getQty())));
                                                }
                                            }
                                            if (dummyDataList != null && dummyDataList.size() > 0) {
                                                for (int k = 0; k < dummyDataList.size(); k++) {
                                                    if (dummyDataList.get(k).getArtCode().equalsIgnoreCase(data3.getArtCode())) {
                                                        checkduplicate3 = true;
                                                        dummyDataList.get(k).setQty(data3.getQty());
                                                        dummyDataList.set(k, dummyDataList.get(k));
                                                    }
                                                }
                                            }
                                            if (!checkduplicate3) {
                                                dummyDataList.add(data3);
                                            }


                                            if (null != SessionManager.INSTANCE.getDataList()) {
                                                if (SessionManager.INSTANCE.getDataList().size() > 0) {
                                                    List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                                                    tempCartItemList = SessionManager.INSTANCE.getDataList();
                                                    for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
                                                        boolean isItemEqual = false;
                                                        for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
                                                            if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
                                                                isItemEqual = true;
                                                            }
                                                        }
                                                        if (!isItemEqual)
                                                            dummyDataList.add(listItem);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            SessionManager.INSTANCE.setDataList(dummyDataList);
                            Intent intent = new Intent("OrderhistoryCardReciver");
                            intent.putExtra("message", "OrderNow");
                            intent.putExtra("MedininesNames", new Gson().toJson(dummyDataList));
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                            if (itemBatchListDialogListener != null) {
                                itemBatchListDialogListener.onDismissDialog();
                            }
                            dismiss();
                        }
                    } else {
                        View view = dialog.getWindow().getDecorView();
                        dialogItemBatchSelectionBinding.snackText.setVisibility(View.VISIBLE);
                        dialogItemBatchSelectionBinding.snackText.setText("Selected quantity is not available in batch");

//                        Utils.showSnackbarDialog(context, view, "Selected quantity is not available in batch");
                    }
                } else {
                    dialogItemBatchSelectionBinding.snackText.setVisibility(View.VISIBLE);
                    dialogItemBatchSelectionBinding.snackText.setText("Please enter product quantity");
                    View view = dialog.getWindow().getDecorView();
//                    Utils.showSnackbarDialog(context, view, "Please enter product quantity");
                }
            } else {
                dialogItemBatchSelectionBinding.snackText.setVisibility(View.VISIBLE);
                dialogItemBatchSelectionBinding.snackText.setText("Product Out of Stock");
//                View view = dialog.getWindow().getDecorView();
//                Utils.showSnackbarDialog(context, view, "Product Out of Stock");
                if (itemBatchListDialogListener != null) {
                    itemBatchListDialogListener.onDismissDialog();
                }
                dismiss();
            }
        } else {
            dialogItemBatchSelectionBinding.snackText.setVisibility(View.VISIBLE);
            dialogItemBatchSelectionBinding.snackText.setText("The selected batch Id has expired.");
//            View view = dialog.getWindow().getDecorView();
//            Utils.showSnackbarDialog(context, view, "The selected batch Id has expired.");
        }
    }

    private ItemBatchListDialogListener itemBatchListDialogListener;

    public void setItemBatchListDialogListener(ItemBatchListDialogListener itemBatchListDialogListener) {
        this.itemBatchListDialogListener = itemBatchListDialogListener;
    }

    public interface ItemBatchListDialogListener {
        void onDismissDialog();
    }
}
