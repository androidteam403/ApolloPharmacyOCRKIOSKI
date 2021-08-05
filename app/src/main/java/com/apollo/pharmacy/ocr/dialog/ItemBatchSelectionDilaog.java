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

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.MyCartActivity;
import com.apollo.pharmacy.ocr.databinding.DialogItemBatchSelectionBinding;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import java.util.ArrayList;
import java.util.List;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class ItemBatchSelectionDilaog {

    private Dialog dialog;
    private DialogItemBatchSelectionBinding dialogItemBatchSelectionBinding;

    private boolean negativeExist = false;


    public ItemBatchSelectionDilaog(Product product, Context context, int position, List<OCRToDigitalMedicineResponse> datalist, ArrayList<Product> offersArrayList, CartCountListener cartCountListener) {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogItemBatchSelectionBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_item_batch_selection, null, false);
        dialog.setContentView(dialogItemBatchSelectionBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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


        boolean product_avilable = false;
        if (null != datalist) {
            int count = 0;
            for (OCRToDigitalMedicineResponse data : datalist) {
                String product_sku = product.getSku();
                if (product_sku.equalsIgnoreCase(data.getArtCode())) {
                    product_avilable = true;
                    int qty = data.getQty();
//                    qty = qty + 1;
                    datalist.remove(count);

                    OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                    object1.setArtName(offersArrayList.get(position).getName());
                    object1.setArtCode(offersArrayList.get(position).getSku());
                    if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                        object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
                    } else {
                        object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
                    }
                    object1.setMou(offersArrayList.get(position).getMou());
                    object1.setQty(qty);
                    object1.setContainer("Strip");
                    datalist.add(object1);
                    SessionManager.INSTANCE.setDataList(datalist);
                    break;
                } else {
                    product_avilable = false;
                }
                count = count + 1;
            }

            if (!product_avilable) {
                OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                object1.setArtName(offersArrayList.get(position).getName());
                object1.setArtCode(offersArrayList.get(position).getSku());
                if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                    object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
                } else {
                    object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
                }
                object1.setMou(offersArrayList.get(position).getMou());
                object1.setQty(1);
                object1.setContainer("Strip");
                datalist.add(object1);
                SessionManager.INSTANCE.setDataList(datalist);
                cartCountListener.cartCount(datalist.size());
                dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf("1"));
            }
        }
        if (context instanceof MyCartActivity) {
            ((MyCartActivity) context).message_string = "addtocart";
            ((MyCartActivity) context).updatecartlist();
        }
//            itemAddToCartLayout.setVisibility(View.GONE);
//            inc_dec_card.setVisibility(View.VISIBLE);

        dialogItemBatchSelectionBinding.unitIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(dialogItemBatchSelectionBinding.unitCount.getText().toString());
                qty = qty + 1;
                String product_sku = offersArrayList.get(position).getSku();
                int count = 0;
                for (OCRToDigitalMedicineResponse data : datalist) {
                    if (product_sku.equalsIgnoreCase(data.getArtCode())) {
                        datalist.remove(count);
                        break;
                    }
                    count = count + 1;
                }

                OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                object1.setArtName(offersArrayList.get(position).getName());
                object1.setArtCode(offersArrayList.get(position).getSku());
                if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                    object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
                } else {
                    object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
                }
                object1.setMou(offersArrayList.get(position).getMou());
                object1.setQty(qty);
                object1.setContainer("Strip");
                datalist.add(object1);
                dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf(qty));
                cartCountListener.cartCount(datalist.size());
                SessionManager.INSTANCE.setDataList(datalist);
                if (context instanceof MyCartActivity) {
                    ((MyCartActivity) context).message_string = "cartupdate";
                }
                if (context instanceof MyCartActivity) {
                    ((MyCartActivity) context).updatecartlist();
                }
            }
        });

        dialogItemBatchSelectionBinding.unitDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(dialogItemBatchSelectionBinding.unitCount.getText().toString());
                if (qty > 1) {
                    qty = qty - 1;
                    int count = 0;
                    String product_code = offersArrayList.get(position).getSku();
                    for (OCRToDigitalMedicineResponse data : datalist) {
                        if (product_code.equalsIgnoreCase(data.getArtCode())) {
                            datalist.remove(count);
                            break;
                        }
                        count = count + 1;
                    }
                    OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                    object1.setArtName(offersArrayList.get(position).getName());
                    object1.setArtCode(offersArrayList.get(position).getSku());
                    if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                        object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
                    } else {
                        object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
                    }
                    object1.setMou(offersArrayList.get(position).getMou());
                    object1.setQty(qty);
                    object1.setContainer("Strip");
                    datalist.add(object1);

                    SessionManager.INSTANCE.setDataList(datalist);
                    dialogItemBatchSelectionBinding.unitCount.setText(String.valueOf(qty));
                    if (context instanceof MyCartActivity) {
                        ((MyCartActivity) context).message_string = "cartupdate";
                    }
                } else {
                    int qty1 = Integer.parseInt(dialogItemBatchSelectionBinding.unitCount.getText().toString());
                    qty1 = qty1 - 1;
                    int count = 0;
                    String product_code = offersArrayList.get(position).getSku();
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
                    if (context instanceof MyCartActivity) {
                        ((MyCartActivity) context).message_string = "cartdelete";
                        ((MyCartActivity) context).delete_item_sku = product_code;
                    }
                }
                if (context instanceof MyCartActivity) {
                    ((MyCartActivity) context).updatecartlist();
                }
            }
        });
    }

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

    }

    public ItemBatchSelectionDilaog(Context context) {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogItemBatchSelectionBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_item_batch_selection, null, false);
        dialog.setContentView(dialogItemBatchSelectionBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
    }


    public void setPositiveListener(View.OnClickListener okListener) {
        dialogItemBatchSelectionBinding.dialogButtonOK.setOnClickListener(okListener);
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


//    public void setPositiveLabel(String positive) {
//        alertDialogBoxBinding.btnYes.setText(positive);
//    }
//
//    public void setNegativeLabel(String negative) {
//        negativeExist = true;
//        alertDialogBoxBinding.btnNo.setText(negative);
//    }

}
