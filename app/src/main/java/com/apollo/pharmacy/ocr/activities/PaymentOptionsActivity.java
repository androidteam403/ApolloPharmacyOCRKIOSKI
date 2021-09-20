package com.apollo.pharmacy.ocr.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.controller.PhonePayQrCodeController;
import com.apollo.pharmacy.ocr.databinding.ActivityPaymentOptionsBinding;
import com.apollo.pharmacy.ocr.interfaces.PhonePayQrCodeListener;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.StateCodes;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.utility.NetworkUtils;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.zxing.WriterException;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class PaymentOptionsActivity extends AppCompatActivity implements PhonePayQrCodeListener {
    private ActivityPaymentOptionsBinding activityPaymentOptionsBinding;
    private double pharmaTotalData = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentOptionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_options);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getIntent() != null) {
            pharmaTotalData = (double) getIntent().getDoubleExtra("fmcgTotal", 0.0);
        }

        activityPaymentOptionsBinding.pharmaTotal.setText(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotalData));

        listeners();
    }

    private void listeners() {
        activityPaymentOptionsBinding.scanToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.recievePaymentSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.recievePaymentSms.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.receivePaymentSmsInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.upi.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.upiInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.cashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.cashOnDelivery.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.cashOnDeliveryInfoLay.setVisibility(View.VISIBLE);
            }
        });
        activityPaymentOptionsBinding.scanToPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(PaymentOptionsActivity.this, OrderinProgressActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            }
        });
        activityPaymentOptionsBinding.placeAnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
        paymentTicksUnticksHandlings();
    }

    private void unselectedBgClors() {
        activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
        activityPaymentOptionsBinding.recievePaymentSms.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
        activityPaymentOptionsBinding.upi.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
        activityPaymentOptionsBinding.cashOnDelivery.setBackgroundResource(R.drawable.ic_payment_methods_unselectebg);
    }

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    private void PaymentInfoLayoutsHandlings() {
        activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.GONE);
        activityPaymentOptionsBinding.receivePaymentSmsInfoLay.setVisibility(View.GONE);
        activityPaymentOptionsBinding.upiInfoLay.setVisibility(View.GONE);
        activityPaymentOptionsBinding.cashOnDeliveryInfoLay.setVisibility(View.GONE);
    }

    private void paymentTicksUnticksHandlings() {
        activityPaymentOptionsBinding.tickBhimLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPaymentOptionsBinding.tickBhim.setImageResource(R.drawable.tick_mark);
                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_tick_bg);
                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);

                activityPaymentOptionsBinding.tickUpi.setImageResource(0);
                activityPaymentOptionsBinding.tickPhonePay.setImageResource(0);


                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);

                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_untick_bg);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_untick_bg);

            }
        });
        activityPaymentOptionsBinding.tickUpiLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPaymentOptionsBinding.tickUpi.setImageResource(R.drawable.tick_mark);
                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_tick_bg);
                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);

                activityPaymentOptionsBinding.tickPhonePay.setImageResource(0);
                activityPaymentOptionsBinding.tickBhim.setImageResource(0);


                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);

                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_untick_bg);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_untick_bg);
            }
        });

        activityPaymentOptionsBinding.tickPhonePayLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPaymentOptionsBinding.tickPhonePay.setImageResource(R.drawable.tick_mark);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_tick_bg);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);

                activityPaymentOptionsBinding.tickUpi.setImageResource(0);
                activityPaymentOptionsBinding.tickBhim.setImageResource(0);

                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);

                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_untick_bg);
                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_untick_bg);


                PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
                phonePayQrCodeController.getPhonePayQrCodeGeneration();
            }
        });
    }

    @Override
    public void onSuccessGetPhonePayQrCodeUpi(PhonePayQrCodeResponse phonePayQrCodeResponse) {

        activityPaymentOptionsBinding.upiInfoLay.setVisibility(View.GONE);
        activityPaymentOptionsBinding.scanPhonePayQrPayLay.setVisibility(View.VISIBLE);
// below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder((String) phonePayQrCodeResponse.getQrCode(), null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            activityPaymentOptionsBinding.idIVQrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }
    }

    @Override
    public void onSuccessPlaceOrder(PlaceOrderResModel body) {
        if (body.getOrdersResult().getStatus()){
            Intent intent = new Intent(PaymentOptionsActivity.this, OrderinProgressActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        }
    }

    @Override
    public void onFailureService(String string) {

    }

    private void placeOrder() {
        String userSelectedAdd = "";
        String selectedStateCode = "";
        UserAddress userAddress = new UserAddress();
        userAddress.setMappingMobile(SessionManager.INSTANCE.getMobilenumber());
        userAddress.setName("User");
        userAddress.setMobile(SessionManager.INSTANCE.getMobilenumber());
        userAddress.setAddress1("Hyderabad");
        userAddress.setAddress2("");
        userAddress.setAddress3("");
        userAddress.setCity("Hyderabad");
        userAddress.setState("Telangana");
        userAddress.setIsDefault(1);
        userAddress.setAddressType("");
        userAddress.setIsDeleted(0);
        userAddress.setPincode("500032");
        userAddress.setItemSelected(true);
//        UserAddress userAddress = SessionManager.INSTANCE.getUseraddress();
        if (null != userAddress.getAddress1() && userAddress.getAddress1().length() > 0) {
            String address = userAddress.getAddress1().toUpperCase() + ", " + userAddress.getCity().toUpperCase() + ", " + userAddress.getState().toUpperCase();
            address = address.replace("null", "");
            userSelectedAdd = address;
        } else {
            if (null != userAddress.getCity() && null != userAddress.getState()) {
                String address = userAddress.getCity().toUpperCase() + ", " + userAddress.getState().toUpperCase();
                address = address.replace("null", "");
                userSelectedAdd = address;
            }
        }


        String stateName = Utils.removeAllDigit(userAddress.getState().trim());
        for (StateCodes codes : Utils.getStoreListFromAssets(PaymentOptionsActivity.this)) {
            String codestate_name = codes.getName().trim();
            if (codestate_name.equalsIgnoreCase(stateName)) {
                selectedStateCode = codes.getCode();
            }
        }

        PlaceOrderReqModel placeOrderReqModel = new PlaceOrderReqModel();
        PlaceOrderReqModel.TpdetailsEntity tpDetailsEntity = new PlaceOrderReqModel.TpdetailsEntity();
        tpDetailsEntity.setOrderId(Utils.getTransactionGeneratedId());
        tpDetailsEntity.setShopId("16001");
        tpDetailsEntity.setShippingMethod("HOME DELIVERY");
        tpDetailsEntity.setPaymentMethod("COD");
        tpDetailsEntity.setVendorName(SessionManager.INSTANCE.getKioskSetupResponse().getKIOSK_ID());
        PlaceOrderReqModel.CustomerDetailsEntity customerDetailsEntity = new PlaceOrderReqModel.CustomerDetailsEntity();
        customerDetailsEntity.setMobileNo(SessionManager.INSTANCE.getMobilenumber());
        customerDetailsEntity.setComm_addr(userSelectedAdd);
        customerDetailsEntity.setDel_addr(userSelectedAdd);
        customerDetailsEntity.setFirstName(userAddress.getName());
        customerDetailsEntity.setLastName(userAddress.getName());
        customerDetailsEntity.setUHID("");
        customerDetailsEntity.setCity(stateName);
        customerDetailsEntity.setPostCode(userAddress.getPincode());
        customerDetailsEntity.setMailId("");
        customerDetailsEntity.setAge(25);
        customerDetailsEntity.setCardNo("");
        customerDetailsEntity.setPatientName(userAddress.getName());
        tpDetailsEntity.setCustomerDetails(customerDetailsEntity);

        PlaceOrderReqModel.PaymentDetailsEntity paymentDetailsEntity = new PlaceOrderReqModel.PaymentDetailsEntity();
        paymentDetailsEntity.setTotalAmount("0");
        paymentDetailsEntity.setPaymentSource("COD");
        paymentDetailsEntity.setPaymentStatus("");
        paymentDetailsEntity.setPaymentOrderId("");
        tpDetailsEntity.setPaymentDetails(paymentDetailsEntity);

        ArrayList<PlaceOrderReqModel.ItemDetailsEntity> itemDetailsArr = new ArrayList<>();
        tpDetailsEntity.setItemDetails(itemDetailsArr);
        tpDetailsEntity.setDotorName("Apollo");
        tpDetailsEntity.setStateCode(selectedStateCode);
        tpDetailsEntity.setTAT("");
        tpDetailsEntity.setUserId(SessionManager.INSTANCE.getKioskSetupResponse().getKIOSK_ID());
        tpDetailsEntity.setOrderType("Pharma");
        tpDetailsEntity.setCouponCode("MED10");
        tpDetailsEntity.setOrderDate(Utils.getOrderedID());
        tpDetailsEntity.setRequestType("NONCART");
        ArrayList<PlaceOrderReqModel.PrescUrlEntity> prescEntityArray = new ArrayList<>();
        PlaceOrderReqModel.PrescUrlEntity pue = new PlaceOrderReqModel.PrescUrlEntity();
        pue.setUrl("http://172.16.2.251:8033/ApolloKMD/apolloMedBuddy/Medibuddy/WalletCheck");
        prescEntityArray.add(pue);
        tpDetailsEntity.setPrescUrl(prescEntityArray);
        placeOrderReqModel.setTpdetails(tpDetailsEntity);
        PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
        phonePayQrCodeController.handleOrderPlaceService(PaymentOptionsActivity.this, placeOrderReqModel);
    }

}