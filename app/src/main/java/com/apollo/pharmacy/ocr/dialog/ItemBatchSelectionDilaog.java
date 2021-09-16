package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

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

import java.util.ArrayList;
import java.util.List;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class ItemBatchSelectionDilaog implements AdapterItemBatchSelection.OnItemBatchClick, MposBatchListListener {

    private Dialog dialog;
    private DialogItemBatchSelectionBinding dialogItemBatchSelectionBinding;

    private boolean negativeExist = false;


    public ItemBatchSelectionDilaog(Product product, Context context, int position, List<OCRToDigitalMedicineResponse> datalist, CartCountListener cartCountListener) {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogItemBatchSelectionBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_item_batch_selection, null, false);
        dialog.setContentView(dialogItemBatchSelectionBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

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
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
//        batchSelection();

        MposBatchListController batchListController = new MposBatchListController(this, context);
        batchListController.getBatchList(articalCode);
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

        dialog.show();
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

    private double itemPrice;

    @Override
    public void onItemBatchClickData(int position, BatchListResponse.Batch itemBatchSelectionData) {
        dialogItemBatchSelectionBinding.dialogItemBatchInnerParentLayout.setBackground(getContext().getResources().getDrawable(R.drawable.dialog_background));
        dialogItemBatchSelectionBinding.recyclerViewLay.setVisibility(View.GONE);
        dialogItemBatchSelectionBinding.date.setText(itemBatchSelectionData.getExpDate());
        dialogItemBatchSelectionBinding.price.setText(String.valueOf(itemBatchSelectionData.getPrice()));
        this.itemPrice = itemBatchSelectionData.getPrice();


    }


    @Override
    public void setSuccessBatchList(BatchListResponse batchListResponse) {
        if (batchListResponse != null && batchListResponse.getBatchList() != null && batchListResponse.getBatchList().size() > 0) {
            dialogItemBatchSelectionBinding.date.setText(batchListResponse.getBatchList().get(0).getExpDate());
            dialogItemBatchSelectionBinding.price.setText(String.valueOf(batchListResponse.getBatchList().get(0).getPrice()));
            this.itemPrice = batchListResponse.getBatchList().get(0).getPrice();
            Utils.dismissDialog();
            dialogItemBatchSelectionBinding.batchSelectionData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogItemBatchSelectionBinding.dialogItemBatchInnerParentLayout.setBackground(getContext().getResources().getDrawable(R.drawable.dialog_item_batch_inner_parent_layout_bg));
                    dialogItemBatchSelectionBinding.recyclerViewLay.setVisibility(View.VISIBLE);
                    adapterItemBatchSelection = new AdapterItemBatchSelection(getContext(), batchListResponse.getBatchList(), ItemBatchSelectionDilaog.this);
                    RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    dialogItemBatchSelectionBinding.batchListRecycle.setLayoutManager(mLayoutManager2);
                    dialogItemBatchSelectionBinding.batchListRecycle.setItemAnimator(new DefaultItemAnimator());
                    dialogItemBatchSelectionBinding.batchListRecycle.setAdapter(adapterItemBatchSelection);
                    dialogItemBatchSelectionBinding.batchListRecycle.setNestedScrollingEnabled(false);
                }
            });
        }
    }
    public double getItemProice() {
        return itemPrice;
    }
    @Override
    public void onFailureBatchList() {

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


}
