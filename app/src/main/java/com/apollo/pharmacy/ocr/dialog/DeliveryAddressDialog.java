package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.DialogDeliveryAddressBinding;
import com.apollo.pharmacy.ocr.utility.Utils;

import java.util.Objects;

public class DeliveryAddressDialog {

    private Dialog dialog;
    private DialogDeliveryAddressBinding deliveryAddressDialog;

    private boolean negativeExist = false;

    public DeliveryAddressDialog(Context context) {
        dialog = new Dialog(context);
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        deliveryAddressDialog = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_delivery_address, null, false);
        dialog.setContentView(deliveryAddressDialog.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
    }


    public void setPositiveListener(View.OnClickListener okListener) {
        deliveryAddressDialog.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        deliveryAddressDialog.dialogButtonNO.setOnClickListener(okListener);
    }

    public void show() {

        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
        deliveryAddressDialog.title.setText(title);
    }

    String userAddress;

    public String getAddressData() {
        userAddress = deliveryAddressDialog.address.getText().toString() + deliveryAddressDialog.zipCode.getText().toString() + deliveryAddressDialog.city.getText().toString() +
                deliveryAddressDialog.state.getText().toString();
        return userAddress;
    }

    public boolean validations() {
        String name = deliveryAddressDialog.name.getText().toString();
        String number = Objects.requireNonNull(deliveryAddressDialog.number.getText()).toString();
        String emailAddress = Objects.requireNonNull(deliveryAddressDialog.email.getText()).toString();
        String address = deliveryAddressDialog.address.getText().toString().trim();
        String zipCode = deliveryAddressDialog.zipCode.getText().toString().trim();
        String city = deliveryAddressDialog.city.getText().toString().trim();
        String state = deliveryAddressDialog.state.getText().toString().trim();

        if (name.isEmpty()) {
            deliveryAddressDialog.name.setError("Name should not empty");
            deliveryAddressDialog.name.requestFocus();
            return false;
        } else if (number.isEmpty()) {
            deliveryAddressDialog.number.setError("Phone Number should not empty");
            deliveryAddressDialog.number.requestFocus();
            return false;
        } else if (deliveryAddressDialog.number.getText().length() < 10 || deliveryAddressDialog.number.getText().length() > 10) {
            deliveryAddressDialog.number.setError("phone number must be 10 digits");
            deliveryAddressDialog.number.requestFocus();
            return false;
        } else if (emailAddress.isEmpty()) {
            deliveryAddressDialog.email.setError("Enter Valid Email");
            deliveryAddressDialog.email.requestFocus();
            return false;
        } else if (!Utils.isValidEmail(emailAddress)) {
            deliveryAddressDialog.email.setError("Enter Valid Email");
            deliveryAddressDialog.email.requestFocus();
            return false;
        } else if (address.isEmpty()) {
            deliveryAddressDialog.address.setError("Address not empty");
            deliveryAddressDialog.address.requestFocus();
            return false;
        } else if (zipCode.isEmpty()) {
            deliveryAddressDialog.zipCode.setError("Pin Code should not empty");
            deliveryAddressDialog.zipCode.requestFocus();
            return false;
        } else if (city.isEmpty()) {
            deliveryAddressDialog.city.setError("City should not empty");
            deliveryAddressDialog.city.requestFocus();
            return false;
        } else if (state.isEmpty()) {
            deliveryAddressDialog.state.setError("State should not empty");
            deliveryAddressDialog.state.requestFocus();
            return false;
        }
        return true;
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
