package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.yourorderstatus.YourOrderStatusActivity;
import com.apollo.pharmacy.ocr.databinding.DialogChooseDeliveryTypeBinding;

public class ChooseDeliveryType {
    private Dialog dialog;
    private DialogChooseDeliveryTypeBinding dialogChooseDeliveryTypeBinding;
    private boolean chooseDeliveryType;

    public ChooseDeliveryType(Context context) {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogChooseDeliveryTypeBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_choose_delivery_type, null, false);
        dialog.setContentView(dialogChooseDeliveryTypeBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent_color);
        dialog.setCancelable(false);

        dialogChooseDeliveryTypeBinding.homeDeliveryLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDeliveryType = true;
                dialogChooseDeliveryTypeBinding.homeDeliveryLay.setBackgroundResource(R.drawable.choose_deliverytype_selectedbg);
                dialogChooseDeliveryTypeBinding.payPickLay.setBackgroundResource(R.drawable.choose_deliverytype_unselectedbg);
                dialogChooseDeliveryTypeBinding.continueButon.setAlpha((float) 0.9);
            }
        });
        dialogChooseDeliveryTypeBinding.payPickLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDeliveryType = true;
                dialogChooseDeliveryTypeBinding.payPickLay.setBackgroundResource(R.drawable.choose_deliverytype_selectedbg);
                dialogChooseDeliveryTypeBinding.homeDeliveryLay.setBackgroundResource(R.drawable.choose_deliverytype_unselectedbg);
                dialogChooseDeliveryTypeBinding.continueButon.setAlpha((float) 0.9);
            }
        });
        dialogChooseDeliveryTypeBinding.continueButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chooseDeliveryType) {
                    Toast.makeText(context, "Delivery Type Selected", Toast.LENGTH_SHORT).show();
                    dismiss();
                    Intent intent=new Intent(context, YourOrderStatusActivity.class);

                }
            }
        });
    }

    public void setPositiveListener(View.OnClickListener okListener) {
        dialogChooseDeliveryTypeBinding.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        dialogChooseDeliveryTypeBinding.dialogButtonNO.setOnClickListener(okListener);
    }

    public void show() {

        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
        dialogChooseDeliveryTypeBinding.title.setText(title);
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
