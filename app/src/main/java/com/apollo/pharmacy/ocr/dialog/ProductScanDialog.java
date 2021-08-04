package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.DialogProductScanBinding;

public class ProductScanDialog {
    private Dialog dialog;
    private DialogProductScanBinding alertDialogBoxBinding;

    private boolean negativeExist = false;

    public ProductScanDialog(Context context) {
        dialog = new Dialog(context);
        alertDialogBoxBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_product_scan, null, false);
        dialog.setContentView(alertDialogBoxBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
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

    public void setTitle(String title) {
        alertDialogBoxBinding.title.setText(title);
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