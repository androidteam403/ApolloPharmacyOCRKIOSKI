package com.apollo.pharmacy.ocr.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.controller.PhonePayQrCodeController;
import com.apollo.pharmacy.ocr.databinding.ActivityPaymentOptionsBinding;
import com.apollo.pharmacy.ocr.interfaces.PhonePayQrCodeListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.StateCodes;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class PaymentOptionsActivity extends AppCompatActivity implements PhonePayQrCodeListener {
    private ActivityPaymentOptionsBinding activityPaymentOptionsBinding;
    private double pharmaTotalData = 0.0;
    private List<OCRToDigitalMedicineResponse> dataList;
    private String customerDeliveryAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentOptionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_options);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        OrderDetailsuiModel orderDetailsuiModel = new OrderDetailsuiModel();
        if (getIntent() != null) {
            pharmaTotalData = (double) getIntent().getDoubleExtra("fmcgTotal", 0.0);
            orderDetailsuiModel.setPharmaHomeDelivery(getIntent().getBooleanExtra("isPharmaHomeDelivery", false));
            orderDetailsuiModel.setFmcgHomeDelivery(getIntent().getBooleanExtra("isFmcgHomeDelivery", false));
            customerDeliveryAddress = (String) getIntent().getStringExtra("customerDeliveryAddress");
        }
        if (null != SessionManager.INSTANCE.getDataList())
            this.dataList = SessionManager.INSTANCE.getDataList();
        if (dataList != null && dataList.size() > 0) {
            int pharmaMedicineCount = 0;
            int fmcgMedicineCount = 0;
            double pharmaTotal = 0.0;
            double fmcgTotal = 0.0;
            boolean isFmcg = false;
            boolean isPharma = false;
            for (OCRToDigitalMedicineResponse data : dataList) {
                if (data.getMedicineType() != null) {
                    if (data.getMedicineType().equals("PHARMA")) {
                        isPharma = true;
                        pharmaMedicineCount++;
                        pharmaTotal = pharmaTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                    } else {
                        isFmcg = true;
                        fmcgMedicineCount++;
                        fmcgTotal = fmcgTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                    }
                }
            }
//            fmcgToatalPass = fmcgTotal;
            orderDetailsuiModel.setPharmaCount(String.valueOf(pharmaMedicineCount));
            orderDetailsuiModel.setFmcgCount(String.valueOf(fmcgMedicineCount));
            orderDetailsuiModel.setPharmaTotal(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotal));
            orderDetailsuiModel.setFmcgTotal(getResources().getString(R.string.rupee) + String.valueOf(fmcgTotal));
            orderDetailsuiModel.setTotalMedicineCount(String.valueOf(dataList.size()));
            orderDetailsuiModel.setMedicineTotal(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotal + fmcgTotal));
            orderDetailsuiModel.setFmcgPharma(isPharma && isFmcg);
            orderDetailsuiModel.setFmcg(isFmcg);
            orderDetailsuiModel.setPharma(isPharma);
            activityPaymentOptionsBinding.setModel(orderDetailsuiModel);
        }
        activityPaymentOptionsBinding.pharmaTotal.setText(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotalData));

        unselectedBgClors();
        activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
        PaymentInfoLayoutsHandlings();
        activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.VISIBLE);
        Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
        PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
        phonePayQrCodeController.getPhonePayQrCodeGeneration(scanPay);

        listeners();
    }

    boolean scanPay = true;

    private void listeners() {
        activityPaymentOptionsBinding.scanToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                scanPay = true;
                activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.VISIBLE);
                Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
                PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
                phonePayQrCodeController.getPhonePayQrCodeGeneration(scanPay);
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

                activityPaymentOptionsBinding.tickPhonePay.setImageResource(0);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_untick_bg);

            }
        });
        activityPaymentOptionsBinding.cashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.cashOnDelivery.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.cashOnDeliveryInfoLay.setVisibility(View.VISIBLE);
                if (customerDeliveryAddress != null) {
                    activityPaymentOptionsBinding.deliveryAddress.setText(customerDeliveryAddress);
                }
            }
        });
        activityPaymentOptionsBinding.backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
                scanPay = false;
                activityPaymentOptionsBinding.tickPhonePay.setImageResource(R.drawable.tick_mark);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_tick_bg);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);

                activityPaymentOptionsBinding.tickUpi.setImageResource(0);
                activityPaymentOptionsBinding.tickBhim.setImageResource(0);

                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);

                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_untick_bg);
                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_untick_bg);

                Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
                PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
                phonePayQrCodeController.getPhonePayQrCodeGeneration(scanPay);
            }
        });
    }

    PhonePayQrCodeResponse phonePayQrCodeResponse;

    @Override
    public void onSuccessGetPhonePayQrCodeUpi(PhonePayQrCodeResponse phonePayQrCodeResponse, boolean scanpay) {
        this.phonePayQrCodeResponse = phonePayQrCodeResponse;
        if (!scanpay) {
            activityPaymentOptionsBinding.idIVQrcode.setVisibility(View.VISIBLE);
            activityPaymentOptionsBinding.qrCodeDisplay.setVisibility(View.GONE);
        } else {
            activityPaymentOptionsBinding.qrCodeDisplay.setVisibility(View.VISIBLE);
            activityPaymentOptionsBinding.idIVQrcode.setVisibility(View.GONE);
        }
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
            if (!scanpay) {
                activityPaymentOptionsBinding.idIVQrcode.setImageBitmap(bitmap);
                activityPaymentOptionsBinding.qrLogoPhonepay.setVisibility(View.VISIBLE);
                activityPaymentOptionsBinding.qrLogo.setVisibility(View.GONE);
                Utils.dismissDialog();
            } else {
                activityPaymentOptionsBinding.qrCodeDisplay.setImageBitmap(bitmap);
                activityPaymentOptionsBinding.qrLogoPhonepay.setVisibility(View.GONE);
                activityPaymentOptionsBinding.qrLogo.setVisibility(View.VISIBLE);
                Utils.dismissDialog();
            }

        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }
    }

    @Override
    public void onSuccessPlaceOrder(PlaceOrderResModel body) {
        if (body.getOrdersResult().getStatus()) {
            Intent intent = new Intent(PaymentOptionsActivity.this, OrderinProgressActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        }
    }

    @Override
    public void onFailureService(String string) {

    }

    @Override
    public void onSuccessPhonepePaymentDetails(PhonePayQrCodeResponse phonePayQrCodeResponse, String transactionId) {

        if (phonePayQrCodeResponse.getStatus()) {
            Toast.makeText(this, "Payment is successfully done", Toast.LENGTH_SHORT).show();
            activityPaymentOptionsBinding.placeAnOrderScanpayPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    placeOrder();
                }
            });
            activityPaymentOptionsBinding.placeAnOrderPhonePay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    placeOrder();
                }
            });
        } else {
            PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
            phonePayQrCodeController.getPhonePayPaymentSuccess(transactionId);
        }
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

    public class OrderDetailsuiModel {
        private String pharmaCount;
        private String fmcgCount;
        private String fmcgTotal;
        private String pharmaTotal;
        private String totalMedicineCount;
        private String medicineTotal;
        private boolean isFmcgPharma;
        private boolean isFmcg;
        private boolean isPharma;
        private boolean isPharmaHomeDelivery;
        private boolean isFmcgHomeDelivery;

        public String getPharmaCount() {
            return pharmaCount;
        }

        public void setPharmaCount(String pharmaCount) {
            this.pharmaCount = pharmaCount;
        }

        public String getFmcgCount() {
            return fmcgCount;
        }

        public void setFmcgCount(String fmcgCount) {
            this.fmcgCount = fmcgCount;
        }

        public String getFmcgTotal() {
            return fmcgTotal;
        }

        public void setFmcgTotal(String fmcgTotal) {
            this.fmcgTotal = fmcgTotal;
        }

        public String getPharmaTotal() {
            return pharmaTotal;
        }

        public void setPharmaTotal(String pharmaTotal) {
            this.pharmaTotal = pharmaTotal;
        }

        public String getTotalMedicineCount() {
            return totalMedicineCount;
        }

        public void setTotalMedicineCount(String totalMedicineCount) {
            this.totalMedicineCount = totalMedicineCount;
        }

        public String getMedicineTotal() {
            return medicineTotal;
        }

        public void setMedicineTotal(String medicineTotal) {
            this.medicineTotal = medicineTotal;
        }

        public boolean isFmcgPharma() {
            return isFmcgPharma;
        }

        public void setFmcgPharma(boolean fmcgPharma) {
            isFmcgPharma = fmcgPharma;
        }

        public boolean isFmcg() {
            return isFmcg;
        }

        public void setFmcg(boolean fmcg) {
            isFmcg = fmcg;
        }

        public boolean isPharma() {
            return isPharma;
        }

        public void setPharma(boolean pharma) {
            isPharma = pharma;
        }

        public boolean isPharmaHomeDelivery() {
            return isPharmaHomeDelivery;
        }

        public void setPharmaHomeDelivery(boolean pharmaHomeDelivery) {
            isPharmaHomeDelivery = pharmaHomeDelivery;
        }

        public boolean isFmcgHomeDelivery() {
            return isFmcgHomeDelivery;
        }

        public void setFmcgHomeDelivery(boolean fmcgHomeDelivery) {
            isFmcgHomeDelivery = fmcgHomeDelivery;
        }
    }
}