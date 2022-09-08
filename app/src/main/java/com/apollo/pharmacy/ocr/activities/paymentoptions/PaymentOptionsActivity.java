package com.apollo.pharmacy.ocr.activities.paymentoptions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.BaseActivity;
import com.apollo.pharmacy.ocr.activities.MySearchActivity;
import com.apollo.pharmacy.ocr.activities.OrderinProgressActivity;
import com.apollo.pharmacy.ocr.activities.paymentoptions.model.ExpressCheckoutTransactionApiRequest;
import com.apollo.pharmacy.ocr.activities.paymentoptions.model.ExpressCheckoutTransactionApiResponse;
import com.apollo.pharmacy.ocr.controller.PhonePayQrCodeController;
import com.apollo.pharmacy.ocr.databinding.ActivityPaymentOptionsBinding;
import com.apollo.pharmacy.ocr.dialog.DeliveryAddressDialog;
import com.apollo.pharmacy.ocr.interfaces.PhonePayQrCodeListener;
import com.apollo.pharmacy.ocr.model.GetPackSizeResponse;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.StateCodes;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.zxing.WriterException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class PaymentOptionsActivity extends BaseActivity implements PhonePayQrCodeListener {
    private ActivityPaymentOptionsBinding activityPaymentOptionsBinding;
    private double pharmaTotalData = 0.0;
    private List<OCRToDigitalMedicineResponse> dataList;
    private String customerDeliveryAddress, name, singleAdd, pincode, city, state;
    private double grandTotalAmountFmcg = 0.0;
    private double grandTotalAmountPharma = 0.0;
    private boolean isPharmaOrder;
    private boolean isFmcgOrder;
    private boolean isPharmadeliveryType, isFmcgDeliveryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentOptionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_options);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityPaymentOptionsBinding.pharmaTotalInclOffer.setPaintFlags(activityPaymentOptionsBinding.pharmaTotalInclOffer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        activityPaymentOptionsBinding.fmcgTotalInclOffer.setPaintFlags(activityPaymentOptionsBinding.fmcgTotalInclOffer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (SessionManager.INSTANCE.getMobilenumber() != null) {
            activityPaymentOptionsBinding.userNum.setText(SessionManager.INSTANCE.getMobilenumber());
        }

        OrderDetailsuiModel orderDetailsuiModel = new OrderDetailsuiModel();
        if (getIntent() != null) {
            pharmaTotalData = (double) getIntent().getDoubleExtra("fmcgTotal", 0.0);
            isFmcgDeliveryType = getIntent().getBooleanExtra("isFmcgHomeDelivery", false);
            isPharmadeliveryType = getIntent().getBooleanExtra("isPharmaHomeDelivery", false);
            orderDetailsuiModel.setPharmaHomeDelivery(getIntent().getBooleanExtra("isPharmaHomeDelivery", false));
            orderDetailsuiModel.setFmcgHomeDelivery(getIntent().getBooleanExtra("isFmcgHomeDelivery", false));
            customerDeliveryAddress = (String) getIntent().getStringExtra("customerDeliveryAddress");
            name = (String) getIntent().getStringExtra("name");
            singleAdd = (String) getIntent().getStringExtra("singleAdd");
            pincode = (String) getIntent().getStringExtra("pincode");
            city = (String) getIntent().getStringExtra("city");
            state = (String) getIntent().getStringExtra("state");
        }

        if (isFmcgDeliveryType) {
            activityPaymentOptionsBinding.cashOnDelivery.setVisibility(View.VISIBLE);
        } else {
            activityPaymentOptionsBinding.cashOnDelivery.setVisibility(View.GONE);
        }

        if (null != SessionManager.INSTANCE.getDataList())
            this.dataList = SessionManager.INSTANCE.getDataList();
        if (dataList != null && dataList.size() > 0) {

            List<OCRToDigitalMedicineResponse> countUniques = new ArrayList<>();
            countUniques.addAll(dataList);

            for (int i = 0; i < countUniques.size(); i++) {
                for (int j = 0; j < countUniques.size(); j++) {
                    if (i != j && countUniques.get(i).getArtName().equals(countUniques.get(j).getArtName())) {
                        countUniques.remove(j);
                        j--;
                    }
                }
            }


            int pharmaMedicineCount = 0;
            int fmcgMedicineCount = 0;
            double pharmaTotal = 0.0;
            double fmcgTotal = 0.0;
            boolean isFmcg = false;
            boolean isPharma = false;
            double pharmaTotalOffer = 0.0;
            double fmcgTotalOffer = 0.0;
            for (OCRToDigitalMedicineResponse data : dataList) {
                if (data.getMedicineType() != null) {
                    if (data.getMedicineType().equals("PHARMA")) {
                        isPharma = true;
                        isPharmaOrder = true;
                        if (data.getNetAmountInclTax() != null && !data.getNetAmountInclTax().isEmpty()) {
                            pharmaTotal = pharmaTotal + (Double.parseDouble(data.getNetAmountInclTax()));
                            pharmaTotalOffer = pharmaTotalOffer + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        } else {
                            pharmaTotal = pharmaTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                            pharmaTotalOffer = pharmaTotalOffer + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        }

                    } else {
                        isFmcg = true;
                        isFmcgOrder = true;
                        if (data.getNetAmountInclTax() != null && !data.getNetAmountInclTax().isEmpty()) {
                            fmcgTotal = fmcgTotal + (Double.parseDouble(data.getNetAmountInclTax()));
                            fmcgTotalOffer = fmcgTotalOffer + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        } else {
                            fmcgTotal = fmcgTotal + (Double.parseDouble(data.getArtprice()) * data.getQty());
                            fmcgTotalOffer = fmcgTotalOffer + (Double.parseDouble(data.getArtprice()) * data.getQty());
                        }

                    }
                }
            }

            for (int i = 0; i < dataList.size(); i++) {
                for (int j = 0; j < countUniques.size(); j++) {
                    if (dataList.get(i).getArtName().equalsIgnoreCase(countUniques.get(j).getArtName())) {
                        if (countUniques.get(j).getMedicineType().equals("FMCG")) {
                            fmcgMedicineCount++;
                            countUniques.remove(j);
                            j--;
                        } else if (countUniques.get(j).getMedicineType().equals("PHARMA")) {
                            pharmaMedicineCount++;
                            countUniques.remove(j);
                            j--;
                        } else {
                            fmcgMedicineCount++;
                            countUniques.remove(j);
                            j--;
                        }
                    }
                }
            }


//            fmcgToatalPass = fmcgTotal;
            orderDetailsuiModel.setPharmaCount(String.valueOf(pharmaMedicineCount));
            orderDetailsuiModel.setFmcgCount(String.valueOf(fmcgMedicineCount));

            DecimalFormat formatter = new DecimalFormat("#,###.00");
            String pharmaformatted = formatter.format(pharmaTotal);
            String fmcgFormatted = formatter.format(fmcgTotal);
            String pharmaOfferformatted = formatter.format(pharmaTotalOffer);
            String fmcgOfferFormatted = formatter.format(fmcgTotalOffer);
            orderDetailsuiModel.setPharmaTotalOffer(getResources().getString(R.string.rupee) + pharmaOfferformatted);
            orderDetailsuiModel.setFmcgTotalOffer(getResources().getString(R.string.rupee) + fmcgOfferFormatted);

            orderDetailsuiModel.setPharmaTotal(getResources().getString(R.string.rupee) + String.valueOf(pharmaformatted));
            orderDetailsuiModel.setFmcgTotal(getResources().getString(R.string.rupee) + String.valueOf(fmcgFormatted));
            orderDetailsuiModel.setTotalMedicineCount(String.valueOf(dataList.size()));
            String totalprodAmt = formatter.format(pharmaTotal + fmcgTotal);
            orderDetailsuiModel.setMedicineTotal(getResources().getString(R.string.rupee) + String.valueOf(totalprodAmt));

            orderDetailsuiModel.setFmcgPharma(isPharma && isFmcg);
            orderDetailsuiModel.setFmcg(isFmcg);
            orderDetailsuiModel.setPharma(isPharma);
            grandTotalAmountFmcg = fmcgTotal;
//            DecimalFormat formatter1 = new DecimalFormat("#,###.00");
//            String formatted = formatter1.format(pharmaTotal);
//            grandTotalAmountPharma = Double.parseDouble(formatted);
            grandTotalAmountPharma = pharmaTotal;
            activityPaymentOptionsBinding.setModel(orderDetailsuiModel);
        }
        if (orderDetailsuiModel.isPharma && !orderDetailsuiModel.isFmcg) {
            activityPaymentOptionsBinding.paymentHeaderParent.setVisibility(View.GONE);
            activityPaymentOptionsBinding.confirmOnlyPharmaOrder.setVisibility(View.VISIBLE);
            activityPaymentOptionsBinding.confirmOnlyPharmaOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (activityPaymentOptionsBinding.deliveryAddress.getText().toString() != null &&
//                            !activityPaymentOptionsBinding.deliveryAddress.getText().toString().isEmpty()) {
//                    placeOrder();

                    if (isFmcgOrder) {
                        isFmcgOrder = false;
//                        new PhonePayQrCodeController(PaymentOptionsActivity.this, PaymentOptionsActivity.this).expressCheckoutTransactionApiCall(getExpressCheckoutTransactionApiRequest());
                        placeOrderFmcg();
                    } else {
                        isPharmaOrder = false;
                        placeOrderPharma();
                    }

//                    } else {
//                        DeliveryAddressDialog deliveryAddressDialog = new DeliveryAddressDialog(PaymentOptionsActivity.this);
//                        deliveryAddressDialog.setPositiveListener(view1 -> {
//                            if (deliveryAddressDialog.validations()) {
//                                customerDeliveryAddress = deliveryAddressDialog.getAddressData();
//                                if (customerDeliveryAddress != null) {
//                                    activityPaymentOptionsBinding.deliveryAddress.setText(customerDeliveryAddress);
//
//                                    name = deliveryAddressDialog.getName();
//                                    singleAdd = deliveryAddressDialog.getAddress();
//                                    pincode = deliveryAddressDialog.getPincode();
//                                    city = deliveryAddressDialog.getCity();
//                                    state = deliveryAddressDialog.getState();
//
//                                }
//                                deliveryAddressDialog.dismiss();
//                            }
//                        });
//                        deliveryAddressDialog.setNegativeListener(view2 -> {
//                            deliveryAddressDialog.dismiss();
//                        });
//                        deliveryAddressDialog.show();
//                    }
                }
            });
        }

        activityPaymentOptionsBinding.pharmaTotal.setText(getResources().getString(R.string.rupee) + String.valueOf(pharmaTotalData));

        unselectedBgClors();
        activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
        PaymentInfoLayoutsHandlings();
        activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.VISIBLE);
        if (grandTotalAmountFmcg > 0) {
            Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
            PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
            phonePayQrCodeController.getPhonePayQrCodeGeneration(scanPay, grandTotalAmountFmcg);
        }
        activityPaymentOptionsBinding.changeDeliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryAddressDialog deliveryAddressDialog = new DeliveryAddressDialog(PaymentOptionsActivity.this);
                if (name != null && singleAdd != null && pincode != null && city != null && state != null) {
                    deliveryAddressDialog.setDeliveryAddress(name, singleAdd, pincode, city, state);
                }
                deliveryAddressDialog.setPositiveListener(view1 -> {
                    if (deliveryAddressDialog.validations()) {
                        customerDeliveryAddress = deliveryAddressDialog.getAddressData();
                        if (customerDeliveryAddress != null) {
                            activityPaymentOptionsBinding.deliveryAddress.setText(customerDeliveryAddress);

                            name = deliveryAddressDialog.getName();
                            singleAdd = deliveryAddressDialog.getAddress();
                            pincode = deliveryAddressDialog.getPincode();
                            city = deliveryAddressDialog.getCity();
                            state = deliveryAddressDialog.getState();

                        }
                        deliveryAddressDialog.dismiss();
                    }
                });
                deliveryAddressDialog.setNegativeListener(view2 -> {
                    deliveryAddressDialog.dismiss();
                });
                deliveryAddressDialog.show();
            }
        });

        listeners();
        if (dataList != null && dataList.size() > 0) {
            new PhonePayQrCodeController(this, this).getPackSizeApiCall(dataList);

        }
    }

    boolean scanPay = true;


    private void listeners() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(PaymentOptionsActivity.this, R.anim.sample_fade_in);
        activityPaymentOptionsBinding.scanToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                scanPay = true;
                activityPaymentOptionsBinding.scanToPay.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.scanToPayInfoLay.setVisibility(View.VISIBLE);
                activityPaymentOptionsBinding.scanToPayInfoLay.startAnimation(fadeInAnimation);
                Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
                if (!qrCodeFirstTimeHandel) {
                    PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
                    phonePayQrCodeController.getPhonePayQrCodeGeneration(scanPay, grandTotalAmountFmcg);
                } else {
                    qrCodeData(qrCode, scanPay);
                }
            }
        });
        activityPaymentOptionsBinding.recievePaymentSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.recievePaymentSms.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.receivePaymentSmsInfoLay.setVisibility(View.VISIBLE);
                activityPaymentOptionsBinding.receivePaymentSmsInfoLay.startAnimation(fadeInAnimation);
            }
        });
        activityPaymentOptionsBinding.upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanPay = false;
                unselectedBgClors();
                activityPaymentOptionsBinding.upi.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.upiInfoLay.setVisibility(View.VISIBLE);
                activityPaymentOptionsBinding.upiInfoLay.startAnimation(fadeInAnimation);

                activityPaymentOptionsBinding.tickPhonePay.setImageResource(0);
                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_untick_bg);
                Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
                if (!qrCodeFirstTimeHandel) {
                    PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
                    phonePayQrCodeController.getPhonePayQrCodeGeneration(scanPay, grandTotalAmountFmcg);
                } else {
                    qrCodeData(qrCode, scanPay);
                }
            }
        });
        activityPaymentOptionsBinding.cashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselectedBgClors();
                activityPaymentOptionsBinding.cashOnDelivery.setBackgroundResource(R.drawable.ic_payment_methods_selectebg);
                PaymentInfoLayoutsHandlings();
                activityPaymentOptionsBinding.cashOnDeliveryInfoLay.setVisibility(View.VISIBLE);
                activityPaymentOptionsBinding.cashOnDeliveryInfoLay.startAnimation(fadeInAnimation);
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
        activityPaymentOptionsBinding.placeAnOrderScanpayPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentOptionsActivity.this, "Payment is not done", Toast.LENGTH_SHORT).show();
            }
        });
        activityPaymentOptionsBinding.placeAnOrderPhonePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentOptionsActivity.this, "Payment is not done", Toast.LENGTH_SHORT).show();
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
                if (activityPaymentOptionsBinding.deliveryAddress.getText().toString() != null &&
                        !activityPaymentOptionsBinding.deliveryAddress.getText().toString().isEmpty()) {
//                    placeOrder();

                    if (isFmcgOrder) {
                        isFmcgOrder = false;
                        placeOrderFmcg();
                    } else {
                        isPharmaOrder = false;
                        placeOrderPharma();
                    }

                } else {
                    DeliveryAddressDialog deliveryAddressDialog = new DeliveryAddressDialog(PaymentOptionsActivity.this);
                    deliveryAddressDialog.setPositiveListener(view1 -> {
                        if (deliveryAddressDialog.validations()) {
                            customerDeliveryAddress = deliveryAddressDialog.getAddressData();
                            if (customerDeliveryAddress != null) {
                                activityPaymentOptionsBinding.deliveryAddress.setText(customerDeliveryAddress);

                                name = deliveryAddressDialog.getName();
                                singleAdd = deliveryAddressDialog.getAddress();
                                pincode = deliveryAddressDialog.getPincode();
                                city = deliveryAddressDialog.getCity();
                                state = deliveryAddressDialog.getState();

                            }
                            deliveryAddressDialog.dismiss();
                        }
                    });
                    deliveryAddressDialog.setNegativeListener(view2 -> {
                        deliveryAddressDialog.dismiss();
                    });
                    deliveryAddressDialog.show();
                }
            }
        });
        activityPaymentOptionsBinding.parentInner.setOnClickListener(v -> startActivity(new Intent(PaymentOptionsActivity.this, MySearchActivity.class)));

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
//                scanPay = false;
//                activityPaymentOptionsBinding.tickPhonePay.setImageResource(R.drawable.tick_mark);
//                activityPaymentOptionsBinding.tickPhonePay.setBackgroundResource(R.drawable.round_tick_bg);
//                activityPaymentOptionsBinding.tickPhonePayLay.setBackgroundResource(R.drawable.upi_payment_selected_bg);
//
//                activityPaymentOptionsBinding.tickUpi.setImageResource(0);
//                activityPaymentOptionsBinding.tickBhim.setImageResource(0);
//
//                activityPaymentOptionsBinding.tickUpiLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
//                activityPaymentOptionsBinding.tickBhimLay.setBackgroundResource(R.drawable.upi_payment_unselected_bg);
//
//                activityPaymentOptionsBinding.tickUpi.setBackgroundResource(R.drawable.round_untick_bg);
//                activityPaymentOptionsBinding.tickBhim.setBackgroundResource(R.drawable.round_untick_bg);
//
//                Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
//                if (!firstQrCodePhonePay) {
//                    PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
//                    phonePayQrCodeController.getPhonePayQrCodeGeneration(scanPay, grandTotalAmount);
//                    firstQrCodePhonePay = true;
//                }
            }
        });
    }


    String qrCode;
    boolean qrCodeFirstTimeHandel;

    @Override
    public void onSuccessGetPhonePayQrCodeUpi(PhonePayQrCodeResponse phonePayQrCodeResponse, boolean scanpay) {
        qrCodeFirstTimeHandel = true;
        qrCode = (String) phonePayQrCodeResponse.getQrCode();
        qrCodeData((String) phonePayQrCodeResponse.getQrCode(), scanpay);
    }

    private void qrCodeData(String qrcode, boolean scanpay) {
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
        qrgEncoder = new QRGEncoder((String) qrcode, null, QRGContents.Type.TEXT, dimen);
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

    String fmcgOrderId;
    String pharmaOrderId;
    boolean onlineAmountPaid;

    @Override
    public void onSuccessPlaceOrderFMCG(PlaceOrderResModel body) {
        if (body.getOrdersResult().getStatus()) {
            if (!isFmcgOrder && !isPharmaOrder) {
                if (pharmaOrderId == null) {
                    pharmaOrderId = body.getOrdersResult().getOrderNo().toString();
                    paymentSuccess = false;
                    Utils.dismissDialog();
                }
                if (fmcgOrderId == null) {
                    fmcgOrderId = body.getOrdersResult().getOrderNo().toString();
                    paymentSuccess = false;
                    Utils.dismissDialog();
                }
            }
            if (isFmcgOrder) {
                pharmaOrderId = body.getOrdersResult().getOrderNo().toString();
                placeOrderFmcg();
                isFmcgOrder = false;
            } else if (isPharmaOrder) {
                fmcgOrderId = body.getOrdersResult().getOrderNo().toString();
                placeOrderPharma();
                isPharmaOrder = false;
            }
            if (!isFmcgOrder && !isPharmaOrder) {
                if (pharmaOrderId != null && fmcgOrderId != null) {
                    Intent intent = new Intent(PaymentOptionsActivity.this, OrderinProgressActivity.class);
                    intent.putExtra("PharmaOrderPlacedData", pharmaOrderId);
                    intent.putExtra("FmcgOrderPlacedData", fmcgOrderId);
                    intent.putExtra("OnlineAmountPaid", onlineAmountPaid);
                    intent.putExtra("pharma_delivery_type", isPharmadeliveryType);
                    intent.putExtra("fmcg_delivery_type", isFmcgDeliveryType);
                    startActivity(intent);
                    overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
                }
            }
        } else {
            Toast.makeText(this, "Order is not Placed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailureService(String string) {

    }


    @Override
    public void onSuccessPhonepePaymentDetails(PhonePayQrCodeResponse phonePayQrCodeResponse, String transactionId) {

        if (phonePayQrCodeResponse.getStatus()) {
            Toast.makeText(this, "Payment is successfully done", Toast.LENGTH_SHORT).show();
            onlineAmountPaid = true;
            if (isFmcgOrder) {
                isFmcgOrder = false;
                placeOrderFmcg();
            } else {
                isPharmaOrder = false;
                placeOrderPharma();
            }
        } else {
            if (paymentSuccess) {
                PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
                phonePayQrCodeController.getPhonePayPaymentSuccess(transactionId);
            }
        }
    }

    @Override
    public void onSuccessGetPackSizeApi(GetPackSizeResponse getPackSizeResponse) {
        if (getPackSizeResponse.getItemsdetails() != null && getPackSizeResponse.getItemsdetails().size() > 0) {
            for (GetPackSizeResponse.Itemsdetail itemsdetail : getPackSizeResponse.getItemsdetails()) {
                if (dataList != null && dataList.size() > 0) {
                    for (int i = 0; i < dataList.size(); i++) {
                        if (itemsdetail.getItemid().equalsIgnoreCase(dataList.get(i).getArtCode().contains(",") ? dataList.get(i).getArtCode().substring(0, dataList.get(i).getArtCode().indexOf(",")) : dataList.get(i).getArtCode())) {
                            dataList.get(i).setPack(String.valueOf(itemsdetail.getPacksize()));
                        }
                    }
                }
            }
            assert dataList != null;
            SessionManager.INSTANCE.setDataList(dataList);
        }
    }

    @Override
    public void onFailureGetPackSizeApi(String message) {
        Utils.showSnackbarDialog(this, findViewById(android.R.id.content), message);
    }

    @Override
    public void onSuccessexpressCheckoutTransactionApiCall(ExpressCheckoutTransactionApiResponse expressCheckoutTransactionApiResponse) {
        if (expressCheckoutTransactionApiResponse.getRequestStatus() != null && expressCheckoutTransactionApiResponse.getRequestStatus().equals("0")) {

        }
    }

    boolean paymentSuccess = true;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
        paymentSuccess = false;
    }

    private boolean loader;

    private void placeOrderFmcg() {
        String userSelectedAdd = "";
        String selectedStateCode = "";
        UserAddress userAddress = new UserAddress();
        if (isFmcgDeliveryType) {
            userAddress.setMappingMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setName(name);
            userAddress.setMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setAddress1(singleAdd);
            userAddress.setAddress2("");
            userAddress.setAddress3("");
            userAddress.setCity(city);
            userAddress.setState(state);
            userAddress.setIsDefault(1);
            userAddress.setAddressType("");
            userAddress.setIsDeleted(0);
            userAddress.setPincode(pincode);
            userAddress.setItemSelected(true);
        } else {
            userAddress.setMappingMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setName(SessionManager.INSTANCE.getUseraddress().getName());
            userAddress.setMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setAddress1(SessionManager.INSTANCE.getUseraddress().getAddress1());
            userAddress.setAddress2("");
            userAddress.setAddress3("");
            userAddress.setCity(SessionManager.INSTANCE.getUseraddress().getCity());
            userAddress.setState("Telangana");
            userAddress.setIsDefault(1);
            userAddress.setAddressType("");
            userAddress.setIsDeleted(0);
            userAddress.setPincode(SessionManager.INSTANCE.getUseraddress().getPincode());
            userAddress.setItemSelected(true);
        }
//        UserAddress userAddress = SessionManager.INSTANCE.getUseraddress();
        if (null != userAddress.getAddress1() && userAddress.getAddress1().length() > 0) {
            String address = userAddress.getAddress1().toUpperCase() + ", " + userAddress.getCity().toUpperCase() + ", " + userAddress.getState().toUpperCase() + userAddress.getPincode();
            address = address.replace("null", "");
            userSelectedAdd = address;
        } else {
            if (null != userAddress.getCity() && null != userAddress.getState()) {
                String address = userAddress.getCity().toUpperCase() + ", " + userAddress.getState().toUpperCase() + userAddress.getPincode();
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
        tpDetailsEntity.setShopId(SessionManager.INSTANCE.getStoreId());
        if (isFmcgDeliveryType) {
            tpDetailsEntity.setShippingMethod("HOME DELIVERY");
        } else {
            tpDetailsEntity.setShippingMethod("Pay here and Carry");
        }
        if (onlineAmountPaid) {
            tpDetailsEntity.setPaymentMethod("Online Payment");
        } else {
            tpDetailsEntity.setPaymentMethod("COD");
        }
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

        paymentDetailsEntity.setTotalAmount(String.format(Locale.US, "%.2f", grandTotalAmountFmcg));//String.valueOf(grandTotalAmountFmcg)
        if (onlineAmountPaid) {
            paymentDetailsEntity.setPaymentSource("Online Payment");
        } else {
            paymentDetailsEntity.setPaymentSource("COD");
        }
        paymentDetailsEntity.setPaymentStatus("");
        paymentDetailsEntity.setPaymentOrderId("");
        tpDetailsEntity.setPaymentDetails(paymentDetailsEntity);

        ArrayList<PlaceOrderReqModel.ItemDetailsEntity> itemDetailsArr = new ArrayList<>();
        for (int i = 0; i < SessionManager.INSTANCE.getDataList().size(); i++) {
            PlaceOrderReqModel.ItemDetailsEntity itemDetailsEntity = new PlaceOrderReqModel.ItemDetailsEntity();
            if (SessionManager.INSTANCE.getDataList().get(i).getMedicineType().equals("FMCG") || SessionManager.INSTANCE.getDataList().get(i).getMedicineType().equals("PRIVATE LABEL")) {
                itemDetailsEntity.setItemID(SessionManager.INSTANCE.getDataList().get(i).getArtCode().contains(",") ? SessionManager.INSTANCE.getDataList().get(i).getArtCode().substring(0, SessionManager.INSTANCE.getDataList().get(i).getArtCode().indexOf(",")) : SessionManager.INSTANCE.getDataList().get(i).getArtCode());
                itemDetailsEntity.setItemName(SessionManager.INSTANCE.getDataList().get(i).getArtName());
                itemDetailsEntity.setMOU(SessionManager.INSTANCE.getDataList().get(i).getQty() * Integer.parseInt(SessionManager.INSTANCE.getDataList().get(i).getPack()));
                itemDetailsEntity.setPack(String.valueOf(SessionManager.INSTANCE.getDataList().get(i).getPack()));
                itemDetailsEntity.setQty(SessionManager.INSTANCE.getDataList().get(i).getQty());
                itemDetailsEntity.setPrice(Double.parseDouble(SessionManager.INSTANCE.getDataList().get(i).getArtprice()));
                itemDetailsEntity.setStatus(true);
                itemDetailsArr.add(itemDetailsEntity);
            }
        }
        tpDetailsEntity.setItemDetails(itemDetailsArr);
        tpDetailsEntity.setDotorName("Apollo");
        tpDetailsEntity.setStateCode(selectedStateCode);
        tpDetailsEntity.setTAT("");
        tpDetailsEntity.setUserId(SessionManager.INSTANCE.getKioskSetupResponse().getKIOSK_ID());
        tpDetailsEntity.setOrderType("fmcg");
        tpDetailsEntity.setCouponCode("MED10");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        String strDate = formatter.format(date);
        tpDetailsEntity.setOrderDate(strDate);
        tpDetailsEntity.setRequestType("CART");
        ArrayList<PlaceOrderReqModel.PrescUrlEntity> prescEntityArray = new ArrayList<>();
        PlaceOrderReqModel.PrescUrlEntity pue = new PlaceOrderReqModel.PrescUrlEntity();
//        pue.setUrl("http://172.16.2.251:8033/ApolloKMD/apolloMedBuddy/Medibuddy/WalletCheck");
        prescEntityArray.add(pue);
        tpDetailsEntity.setPrescUrl(prescEntityArray);
        placeOrderReqModel.setTpdetails(tpDetailsEntity);
        PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
        if (!loader) {
            loader = true;
            Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
        }
        phonePayQrCodeController.handleOrderPlaceService(PaymentOptionsActivity.this, placeOrderReqModel);
    }

    private void placeOrderPharma() {
        String userSelectedAdd = "";
        String selectedStateCode = "";
        UserAddress userAddress = new UserAddress();
        if (isPharmadeliveryType) {
            userAddress.setMappingMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setName(name);
            userAddress.setMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setAddress1(singleAdd);
            userAddress.setAddress2("");
            userAddress.setAddress3("");
            userAddress.setCity(city);
            userAddress.setState(state);
            userAddress.setIsDefault(1);
            userAddress.setAddressType("");
            userAddress.setIsDeleted(0);
            userAddress.setPincode(pincode);
            userAddress.setItemSelected(true);
        } else {
            userAddress.setMappingMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setName(SessionManager.INSTANCE.getUseraddress().getName());
            userAddress.setMobile(SessionManager.INSTANCE.getMobilenumber());
            userAddress.setAddress1(SessionManager.INSTANCE.getUseraddress().getAddress1());
            userAddress.setAddress2("");
            userAddress.setAddress3("");
            userAddress.setCity(SessionManager.INSTANCE.getUseraddress().getCity());
            userAddress.setState("Telangana");
            userAddress.setIsDefault(1);
            userAddress.setAddressType("");
            userAddress.setIsDeleted(0);
            userAddress.setPincode(SessionManager.INSTANCE.getUseraddress().getPincode());
            userAddress.setItemSelected(true);
        }
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
        tpDetailsEntity.setShopId(SessionManager.INSTANCE.getStoreId());
        if (isPharmadeliveryType) {
            tpDetailsEntity.setShippingMethod("HOME DELIVERY");
        } else {
            tpDetailsEntity.setShippingMethod("Pay and collect at counter");
        }
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
        paymentDetailsEntity.setTotalAmount(String.format(Locale.US, "%.2f", grandTotalAmountPharma));
        paymentDetailsEntity.setPaymentSource("COD");
        paymentDetailsEntity.setPaymentStatus("");
        paymentDetailsEntity.setPaymentOrderId("");
        tpDetailsEntity.setPaymentDetails(paymentDetailsEntity);

        ArrayList<PlaceOrderReqModel.ItemDetailsEntity> itemDetailsArr = new ArrayList<>();
        for (int i = 0; i < SessionManager.INSTANCE.getDataList().size(); i++) {
            PlaceOrderReqModel.ItemDetailsEntity itemDetailsEntity = new PlaceOrderReqModel.ItemDetailsEntity();
            if (SessionManager.INSTANCE.getDataList().get(i).getMedicineType().equals("PHARMA")) {
                itemDetailsEntity.setItemID(SessionManager.INSTANCE.getDataList().get(i).getArtCode().contains(",") ? SessionManager.INSTANCE.getDataList().get(i).getArtCode().substring(0, SessionManager.INSTANCE.getDataList().get(i).getArtCode().indexOf(",")) : SessionManager.INSTANCE.getDataList().get(i).getArtCode());
                itemDetailsEntity.setItemName(SessionManager.INSTANCE.getDataList().get(i).getArtName());
                itemDetailsEntity.setMOU(SessionManager.INSTANCE.getDataList().get(i).getQty() * Integer.parseInt(SessionManager.INSTANCE.getDataList().get(i).getPack()));
                itemDetailsEntity.setPack(String.valueOf(SessionManager.INSTANCE.getDataList().get(i).getPack()));
                itemDetailsEntity.setQty(SessionManager.INSTANCE.getDataList().get(i).getQty());
                itemDetailsEntity.setPrice(Double.parseDouble(SessionManager.INSTANCE.getDataList().get(i).getArtprice()));
                itemDetailsEntity.setStatus(true);
                itemDetailsArr.add(itemDetailsEntity);
            }
        }
        tpDetailsEntity.setItemDetails(itemDetailsArr);
        tpDetailsEntity.setDotorName("Apollo");
        tpDetailsEntity.setStateCode(selectedStateCode);
        tpDetailsEntity.setTAT("");
        tpDetailsEntity.setUserId(SessionManager.INSTANCE.getKioskSetupResponse().getKIOSK_ID());
        tpDetailsEntity.setOrderType("Pharma");
        tpDetailsEntity.setCouponCode("MED10");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        String strDate = formatter.format(date);
        tpDetailsEntity.setOrderDate(strDate);
        tpDetailsEntity.setRequestType("CART");
        ArrayList<PlaceOrderReqModel.PrescUrlEntity> prescEntityArray = new ArrayList<>();
        PlaceOrderReqModel.PrescUrlEntity pue = new PlaceOrderReqModel.PrescUrlEntity();
        prescEntityArray.add(pue);
        tpDetailsEntity.setPrescUrl(prescEntityArray);
        placeOrderReqModel.setTpdetails(tpDetailsEntity);
        PhonePayQrCodeController phonePayQrCodeController = new PhonePayQrCodeController(getApplicationContext(), PaymentOptionsActivity.this);
        if (!loader) {
            loader = true;
            Utils.showDialog(PaymentOptionsActivity.this, "Loading…");
        }
        phonePayQrCodeController.handleOrderPlaceService(PaymentOptionsActivity.this, placeOrderReqModel);
    }

    public class OrderDetailsuiModel {
        private String pharmaCount;
        private String fmcgCount;
        private String fmcgTotal;
        private String pharmaTotal;
        private String pharmaTotalOffer;
        private String fmcgTotalOffer;
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

        public String getPharmaTotalOffer() {
            return pharmaTotalOffer;
        }

        public void setPharmaTotalOffer(String pharmaTotalOffer) {
            this.pharmaTotalOffer = pharmaTotalOffer;
        }

        public String getFmcgTotalOffer() {
            return fmcgTotalOffer;
        }

        public void setFmcgTotalOffer(String fmcgTotalOffer) {
            this.fmcgTotalOffer = fmcgTotalOffer;
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


    public ExpressCheckoutTransactionApiRequest getExpressCheckoutTransactionApiRequest() {
        ExpressCheckoutTransactionApiRequest expressCheckoutTransactionApiRequest = new ExpressCheckoutTransactionApiRequest();
        expressCheckoutTransactionApiRequest.setCorpCode("");//Need Clarify
        expressCheckoutTransactionApiRequest.setMobileNO("");//Need Clarify
        expressCheckoutTransactionApiRequest.setCustomerName("");//Need Clarify
        expressCheckoutTransactionApiRequest.setDoctorName("");//Need Clarify
        expressCheckoutTransactionApiRequest.setDoctorCode("");//Need Clarify
        expressCheckoutTransactionApiRequest.setTrackingRef("");//Need Clarify
        expressCheckoutTransactionApiRequest.setStaff("");//Need Clarify
        expressCheckoutTransactionApiRequest.setStore("");//Need Clarify
        expressCheckoutTransactionApiRequest.setState("");//Need Clarify
        expressCheckoutTransactionApiRequest.setTerminal("");//Need Clarify
        expressCheckoutTransactionApiRequest.setDataAreaId("");//Need Clarify
        expressCheckoutTransactionApiRequest.setIsStockCheck(true);//Need Clarify
        expressCheckoutTransactionApiRequest.setExpiryDays(30);//Need Clarify

        List<ExpressCheckoutTransactionApiRequest.SalesLine> salesLineList = new ArrayList<>();
        // do add saleslines to salesLineList

        for (int i = 0; i < SessionManager.INSTANCE.getDataList().size(); i++) {
            ExpressCheckoutTransactionApiRequest.SalesLine salesLine = new ExpressCheckoutTransactionApiRequest.SalesLine();
            if (SessionManager.INSTANCE.getDataList().get(i).getMedicineType().equals("FMCG") || SessionManager.INSTANCE.getDataList().get(i).getMedicineType().equals("PRIVATE LABEL")) {
                salesLine.setLineNo(i + 1);
                salesLine.setItemId(SessionManager.INSTANCE.getDataList().get(i).getArtCode().contains(",") ? SessionManager.INSTANCE.getDataList().get(i).getArtCode().substring(0, SessionManager.INSTANCE.getDataList().get(i).getArtCode().indexOf(",")) : SessionManager.INSTANCE.getDataList().get(i).getArtCode());
                salesLine.setItemName(SessionManager.INSTANCE.getDataList().get(i).getArtName());
                salesLine.setQty(SessionManager.INSTANCE.getDataList().get(i).getQty());
                salesLine.setMrp(Double.parseDouble(SessionManager.INSTANCE.getDataList().get(i).getArtprice()));
                salesLine.setDiscAmount(Double.parseDouble(SessionManager.INSTANCE.getDataList().get(i).getNetAmountInclTax()));//Need Clarify
                salesLine.setTotalDiscAmount(0.0); //Need Clarify
                salesLine.setTotalDiscPct(5);//Need Clarify
                salesLine.setUnitPrice(SessionManager.INSTANCE.getDataList().get(i).getQty() * Double.parseDouble(SessionManager.INSTANCE.getDataList().get(i).getArtprice()));//Need Clarify
                salesLine.setUnitQty(SessionManager.INSTANCE.getDataList().get(i).getQty());//Need Clarify
                salesLine.setDiscId("EXPRESSCHECKOUT");//Need Clarify
                salesLine.setLineDiscPercentage(5);//Need Clarify

                salesLineList.add(salesLine);
            }
        }

        expressCheckoutTransactionApiRequest.setSalesLine(salesLineList);

        List<ExpressCheckoutTransactionApiRequest.TenderLine> tenderLineList = new ArrayList<>();
        // do add TenderLines to tenderLineList


        expressCheckoutTransactionApiRequest.setTenderLine(tenderLineList);


        return expressCheckoutTransactionApiRequest;
    }
}