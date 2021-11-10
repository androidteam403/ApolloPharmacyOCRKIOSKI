package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.controller.PincodeValidateController;
import com.apollo.pharmacy.ocr.databinding.DialogDeliveryAddressBinding;
import com.apollo.pharmacy.ocr.interfaces.PincodeValidateListener;
import com.apollo.pharmacy.ocr.model.PincodeValidateResponse;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import java.util.List;

public class DeliveryAddressDialog implements PincodeValidateListener {

    private Dialog dialog;
    private DialogDeliveryAddressBinding deliveryAddressDialog;

    private boolean negativeExist = false;
    Context context;

    public DeliveryAddressDialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        deliveryAddressDialog = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_delivery_address, null, false);
        dialog.setContentView(deliveryAddressDialog.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        if (SessionManager.INSTANCE.getMobilenumber() != null && !SessionManager.INSTANCE.getMobilenumber().isEmpty()) {
            deliveryAddressDialog.number.setText(SessionManager.INSTANCE.getMobilenumber());
        }

        deliveryAddressDialog.zipCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 6) {
                    PincodeValidateController pincodeValidateController = new PincodeValidateController(context, DeliveryAddressDialog.this);
                    pincodeValidateController.onPincodeValidateApi(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });


        deliveryAddressDialog.constraintLayout.setOnClickListener(new View.OnClickListener() {
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
//        deliveryAddressDialog.title.setText(title);
    }

    String userAddress;

    public String getAddressData() {
        userAddress = deliveryAddressDialog.address.getText().toString() + "," + deliveryAddressDialog.zipCode.getText().toString() + "," + deliveryAddressDialog.city.getText().toString() + "," +
                deliveryAddressDialog.state.getText().toString();
        return userAddress;
    }

    public String getName() {
        String name = deliveryAddressDialog.name.getText().toString();
        return name;
    }

    public String getAddress() {
        String address = deliveryAddressDialog.address.getText().toString();
        return address;
    }

    public String getPincode() {
        String pincode = deliveryAddressDialog.zipCode.getText().toString();
        return pincode;
    }

    public String getCity() {
        String city = deliveryAddressDialog.city.getText().toString();
        return city;
    }

    public String getState() {
        String state = deliveryAddressDialog.state.getText().toString();
        return state;
    }

    public void setDeliveryAddress(String name, String userAddress, String pincode, String city, String state) {
        deliveryAddressDialog.name.setText(name.toString());
        deliveryAddressDialog.address.setText(userAddress.toString());
        deliveryAddressDialog.number.setText(SessionManager.INSTANCE.getMobilenumber().toString());
        deliveryAddressDialog.zipCode.setText(pincode);
        PincodeValidateController pincodeValidateController = new PincodeValidateController(context, DeliveryAddressDialog.this);
        pincodeValidateController.onPincodeValidateApi(pincode.toString());
    }

    public boolean validations() {
        String name = deliveryAddressDialog.name.getText().toString();
//        String number = Objects.requireNonNull(deliveryAddressDialog.number.getText()).toString();
//        String emailAddress = Objects.requireNonNull(deliveryAddressDialog.email.getText()).toString();
        String address = deliveryAddressDialog.address.getText().toString().trim();
        String zipCode = deliveryAddressDialog.zipCode.getText().toString().trim();
//        String city = deliveryAddressDialog.city.getText().toString().trim();
//        String state = deliveryAddressDialog.state.getText().toString().trim();

        if (name.isEmpty()) {
            deliveryAddressDialog.name.setError("Name should not empty");
            deliveryAddressDialog.name.requestFocus();
            return false;
//        } else if (number.isEmpty()) {
//            deliveryAddressDialog.number.setError("Phone Number should not empty");
//            deliveryAddressDialog.number.requestFocus();
//            return false;
//        } else if (deliveryAddressDialog.number.getText().length() < 10 || deliveryAddressDialog.number.getText().length() > 10) {
//            deliveryAddressDialog.number.setError("phone number must be 10 digits");
//            deliveryAddressDialog.number.requestFocus();
//            return false;
//        } else if (emailAddress.isEmpty()) {
//            deliveryAddressDialog.email.setError("Enter Valid Email");
//            deliveryAddressDialog.email.requestFocus();
//            return false;
//        } else if (!Utils.isValidEmail(emailAddress)) {
//            deliveryAddressDialog.email.setError("Enter Valid Email");
//            deliveryAddressDialog.email.requestFocus();
//            return false;
        } else if (address.isEmpty()) {
            deliveryAddressDialog.address.setError("Address not empty");
            deliveryAddressDialog.address.requestFocus();
            return false;
        } else if (zipCode.isEmpty()) {
            deliveryAddressDialog.zipCode.setError("Pin Code should not empty");
            deliveryAddressDialog.zipCode.requestFocus();
            return false;
//        } else if (city.isEmpty()) {
//            deliveryAddressDialog.city.setError("City should not empty");
//            deliveryAddressDialog.city.requestFocus();
//            return false;
//        } else if (state.isEmpty()) {
//            deliveryAddressDialog.state.setError("State should not empty");
//            deliveryAddressDialog.state.requestFocus();
//            return false;
        } else if (deliveryAddressDialog.zipCode.getText().toString().length() < 6) {
            deliveryAddressDialog.zipCode.setError("Pin Code should not empty");
            deliveryAddressDialog.zipCode.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onSuccessPincodeValidate(List<PincodeValidateResponse> body) {
        deliveryAddressDialog.city.setText(body.get(0).getCity().toString());
        deliveryAddressDialog.state.setText(body.get(0).getState().toString());
        View view1 = dialog.getCurrentFocus();
        if (view1 != null) {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(dialog.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onFailurePincode(List<PincodeValidateResponse> body) {
        deliveryAddressDialog.zipCode.setText("");
        deliveryAddressDialog.city.setText("");
        deliveryAddressDialog.state.setText("");

        Toast.makeText(context, "Please enter a valid pincode", Toast.LENGTH_SHORT).show();

        View view1 = dialog.getCurrentFocus();
        if (view1 != null) {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(dialog.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
