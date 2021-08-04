package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.DialogItemBatchSelectionBinding;
import com.apollo.pharmacy.ocr.databinding.DialogProductScanBinding;

public class ItemBatchSelectionDilaog {

    private Dialog dialog;
    private DialogItemBatchSelectionBinding dialogItemBatchSelectionBinding;

    private boolean negativeExist = false;

    public ItemBatchSelectionDilaog(Context context) {
        dialog=new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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
