package com.apollo.pharmacy.ocr.activities;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.checkout.CheckoutActivity;
import com.apollo.pharmacy.ocr.adapters.CrossCellAdapter;
import com.apollo.pharmacy.ocr.adapters.MyCartListAdapter;
import com.apollo.pharmacy.ocr.adapters.PromotionsAdapter;
import com.apollo.pharmacy.ocr.adapters.TrendingNowAdapter;
import com.apollo.pharmacy.ocr.adapters.UpCellAdapter;
import com.apollo.pharmacy.ocr.controller.MyCartController;
import com.apollo.pharmacy.ocr.controller.UploadBgImageController;
import com.apollo.pharmacy.ocr.dialog.CartDeletedItemsDialog;
import com.apollo.pharmacy.ocr.dialog.ItemBatchSelectionDilaog;
import com.apollo.pharmacy.ocr.dialog.ScannedPrescriptionViewDialog;
import com.apollo.pharmacy.ocr.enums.ViewMode;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.interfaces.CheckPrescriptionListener;
import com.apollo.pharmacy.ocr.interfaces.MyCartListener;
import com.apollo.pharmacy.ocr.interfaces.OnItemClickListener;
import com.apollo.pharmacy.ocr.interfaces.UploadBgImageListener;
import com.apollo.pharmacy.ocr.model.GetImageRes;
import com.apollo.pharmacy.ocr.model.GetProductListResponse;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.model.ProductSearch;
import com.apollo.pharmacy.ocr.model.ScannedData;
import com.apollo.pharmacy.ocr.model.ScannedMedicine;
import com.apollo.pharmacy.ocr.model.UpCellCrossCellResponse;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.receiver.ConnectivityReceiver;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.NetworkUtils;
import com.apollo.pharmacy.ocr.utility.Session;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.apollo.pharmacy.ocr.zebrasdk.BaseActivity;
import com.apollo.pharmacy.ocr.zebrasdk.helper.ScannerAppEngine;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.zebra.scannercontrol.FirmwareUpdateEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class MyCartActivity extends BaseActivity implements OnItemClickListener, CartCountListener, MyCartListener, ItemBatchSelectionDilaog.ItemBatchListDialogListener,
        ConnectivityReceiver.ConnectivityReceiverListener, CheckPrescriptionListener, UploadBgImageListener, ScannerAppEngine.IScannerAppEngineDevEventsDelegate {

    private ImageView checkOutImage;
    private PromotionsAdapter promotionadaptor;
    private TrendingNowAdapter trendingNowAdapter;
    private ArrayList<Product> outOfStockArrayList, inStockArrayList, outOfStockArrayList1, inStockArrayList1;
    private ArrayList<Product> product_list_array, product_list_array1;
    private List<OCRToDigitalMedicineResponse> dataList;

    private List<OCRToDigitalMedicineResponse> dataComparingList = new ArrayList<>();
    private List<OCRToDigitalMedicineResponse> expandholdedDataList = new ArrayList<>();


    private List<OCRToDigitalMedicineResponse> deletedataList;
    private Boolean existelemnt;
    private List<String> SpecialOfferList;
    private int totalPage = 1, currentPage = 1;
    private int totalPage1 = 1, currentPage1 = 1;
    boolean isEmpty = false;
    boolean isEmpty1 = false;
    private TextView total_itemcount_textview;
    private LinearLayout cartItemCountLayout;
    private RelativeLayout addAgainLayout;
    private ConstraintLayout constraint_Layout;
    private CountDownTimer countdown_timer = null;
    public String message_string;
    public String delete_item_sku;
    private TextView removedItemsCount;
    private TextView grandTotalPrice;
    private String grandTotalPriceValue = "";
    private TextView myCartCount;
    private LinearLayout curationViewLayout;
    private LinearLayout curationProcessLayout;
    private final List<ScannedMedicine> dialogMedicineList = new ArrayList<>();
    private RelativeLayout bottomSheetLayout;
    private BottomSheetBehavior sheetBehaviour;
    private final ScannedData presSelectedItem = null;
    private CartDeletedItemsDialog cartDeleteItemDialog;
    private boolean curationFlag = false, curationDoneFlag = false;
    private long fixedtime_period_millisec = 300000;
    private long fixedScanTime = 300000;
    private long timeperiod_1_millisce;
    private long timeperiod_2_millisec;
    private Session session;
    private MyCartListAdapter cartListAdapter;
    private RecyclerView cartItemRecyclerView;
    private TextView scannedImageHeader;
    private FrameLayout scannedImageLayout;
    private ImageView scannedImage;
    private MyCartController myCartController;
    private UploadBgImageController uploadBgImageController;
    private static final String TAG = MyCartActivity.class.getSimpleName();
    private TextView timerHeaderText, timerChildText;
    private long minVal = 0, secVal = 0;
    RecyclerView crossCell_recycle, upcell_recycle;
    TextView noDataFound, noDataFoundCrosssel, noDataFoundUpsel;
    private boolean isDialogShow = false;

    @Override
    public void onSuccessProductList(HashMap<String, GetProductListResponse> productList) {
        if (productList != null && productList.size() > 0) {
            if (NetworkUtils.isNetworkConnected(MyCartActivity.this)) {
                SpecialOfferList = myCartController.getSpecialOffersCategoryList();
            } else {
                Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
            }
            if (null != SpecialOfferList && SpecialOfferList.size() > 0) {
                for (String s : SpecialOfferList) {
                    if (s.equalsIgnoreCase("Promotions")) {
                        totalPage = (productList.get(s).getProductCount() / 20);
                        if (productList.get(s).getProductCount() % 3 != 0) {
                            totalPage++;
                        }
                        ArrayList<Product> products = (ArrayList<Product>) productList.get(s).getProducts();
                        for (Product d : products) {
                            Product load_obj = new Product();
                            load_obj.setName(d.getName());
                            load_obj.setSku(d.getSku());
                            load_obj.setPrice(d.getPrice());
                            load_obj.setImage(d.getImage());
                            load_obj.setSpecialPrice(d.getSpecialPrice());
                            load_obj.setProduct_pricetype("offer");
                            load_obj.setIsInStock(d.getIsInStock());
                            load_obj.setMou(d.getMou());
                            load_obj.setId(d.getId());
                            if (d.getIsInStock() == 1) {
                                inStockArrayList.add(load_obj);
                            } else {
                                outOfStockArrayList.add(load_obj);
                            }
                        }
                        ArrayList<Product> sessionOfferList = new ArrayList<>();
                        if (inStockArrayList.size() >= 3) {
                            product_list_array.add(inStockArrayList.get(0));
                            product_list_array.add(inStockArrayList.get(1));
                            product_list_array.add(inStockArrayList.get(2));

                            sessionOfferList.clear();
                            sessionOfferList.add(inStockArrayList.get(0));
                            sessionOfferList.add(inStockArrayList.get(1));
                            sessionOfferList.add(inStockArrayList.get(2));
                            sessionOfferList.add(inStockArrayList.get(3));
                            sessionOfferList.add(inStockArrayList.get(4));
                            sessionOfferList.add(inStockArrayList.get(5));
                            SessionManager.INSTANCE.setOfferList(sessionOfferList);
                        } else {
                            product_list_array.addAll(inStockArrayList);
                        }
                        if (totalPage > currentPage) {
                            product_list_array.remove(0);
                        }
                        promotionadaptor.notifyDataSetChanged();
                    } else {
                        totalPage1 = (productList.get(s).getProductCount() / 20);
                        if (productList.get(s).getProductCount() % 3 != 0) {
                            totalPage1++;
                        }
                        ArrayList<Product> products = (ArrayList<Product>) productList.get(s).getProducts();
                        for (Product d : products) {
                            Product load_obj = new Product();
                            load_obj.setName(d.getName());
                            load_obj.setSku(d.getSku());
                            load_obj.setPrice(d.getPrice());
                            load_obj.setImage(d.getImage());
                            load_obj.setSpecialPrice(d.getSpecialPrice());
                            load_obj.setProduct_pricetype("trendingnow");
                            load_obj.setIsInStock(d.getIsInStock());
                            load_obj.setMou(d.getMou());
                            load_obj.setId(d.getId());
                            if (d.getIsInStock() == 1) {
                                inStockArrayList1.add(load_obj);
                            } else {
                                outOfStockArrayList1.add(load_obj);
                            }
                        }
                        ArrayList<Product> sessionTrendingList = new ArrayList<>();
                        if (inStockArrayList1.size() >= 3) {
                            product_list_array1.add(inStockArrayList1.get(0));
                            product_list_array1.add(inStockArrayList1.get(1));
                            product_list_array1.add(inStockArrayList1.get(2));
                            sessionTrendingList.clear();
                            sessionTrendingList.add(inStockArrayList1.get(0));
                            sessionTrendingList.add(inStockArrayList1.get(1));
                            sessionTrendingList.add(inStockArrayList1.get(2));
                            SessionManager.INSTANCE.setTrendingList(sessionTrendingList);
                        } else {
                            product_list_array1.addAll(inStockArrayList1);
                        }
                        if (totalPage1 > currentPage1) {
                            product_list_array1.remove(0);
                        }
                        trendingNowAdapter.notifyDataSetChanged();
                    }
                }
            }
        } else {
            Utils.showCustomAlertDialog(this, getApplicationContext().getResources().getString(R.string.try_again_later), false, getApplicationContext().getResources().getString(R.string.label_ok), "");
        }
    }

    @Override
    public void onDeleteItemClicked(OCRToDigitalMedicineResponse ocrToDigitalMedicineResponse, int position) {
        cartDeleteItemDialog.dismiss();
        deletedataList.remove(position);
        String s1 = ocrToDigitalMedicineResponse.getArtCode();
        String[] words = s1.split(",");
        String itemId = words[0];
//        dataList.add(ocrToDigitalMedicineResponse);
//        SessionManager.INSTANCE.setDataList(dataList);
        SessionManager.INSTANCE.setDeletedDataList(deletedataList);
        total_itemcount_textview.setText(String.valueOf(dataList.size()));
        cartCount(dataList.size());

        myCartController.searchItemProducts(itemId, 0, ocrToDigitalMedicineResponse.getQty());

//        if (dataList.size() > 0) {
//            float grandTotalVal = 0;
//            for (int i = 0; i < dataList.size(); i++) {
//                if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
//                    float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
//                    grandTotalVal = grandTotalVal + totalPrice;
//                }
//            }
//            String rupeeSymbol = "\u20B9";
//            grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
//            grandTotalPriceValue = grandTotalPrice.getText().toString();
//            cartListAdapter.notifyDataSetChanged();
//            checkOutImage.setImageResource(R.drawable.checkout_cart);
//        }
        if (SessionManager.INSTANCE.getDeletedDataList().size() > 0) {
            addAgainLayout.setVisibility(View.VISIBLE);
            removedItemsCount.setText(String.valueOf(SessionManager.INSTANCE.getDeletedDataList().size()));
        } else {
            addAgainLayout.setVisibility(View.GONE);
        }
        if (SessionManager.INSTANCE.getCurationStatus()) {
            curationProcessLayout.setVisibility(View.VISIBLE);
        } else {
            curationProcessLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceivers);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiverNew);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiverCheckOut);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mPrescriptionMessageReceiver);
        super.onPause();
        Constants.getInstance().setConnectivityListener(null);
    }

    private final BroadcastReceiver mMessageReceiverCheckOut = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equals("Medicinedata")) {
                if (SessionManager.INSTANCE.getFcmMedicineReceived()) {
                    SessionManager.INSTANCE.setFcmMedicineReceived(false);
                    final Dialog dialog = new Dialog(MyCartActivity.this);
                    dialog.setContentView(R.layout.dialog_custom_alert);
                    dialog.setCancelable(false);
                    if (dialog.getWindow() != null)
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    TextView dialogTitleText = dialog.findViewById(R.id.dialog_info);
                    Button okButton = dialog.findViewById(R.id.dialog_ok);
                    Button declineButton = dialog.findViewById(R.id.dialog_cancel);
                    declineButton.setVisibility(View.GONE);
                    dialogTitleText.setText(getResources().getString(R.string.label_curation_completed_alert));
                    okButton.setText(getResources().getString(R.string.label_ok));
                    declineButton.setText(getResources().getString(R.string.label_no));
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            SessionManager.INSTANCE.setCurationStatus(false);
                            curationDoneFlag = true;
                            curationViewLayout.setVisibility(View.GONE);
                            curationProcessLayout.setVisibility(View.GONE);
                            if (countdown_timer != null) {
                                countdown_timer.cancel();
                            }
                            ScannedData fcmMedicine = new Gson().fromJson(intent.getStringExtra("MedininesNames"), ScannedData.class);
                            List<ScannedMedicine> scannedList = new ArrayList<>();
                            for (ScannedMedicine m : fcmMedicine.getMedicineList()) {
                                if (Double.parseDouble(Objects.requireNonNull(m.getArtprice())) > 0) {
                                    OCRToDigitalMedicineResponse d = new OCRToDigitalMedicineResponse();
                                    d.setArtName(m.getArtName());
                                    d.setArtCode(m.getArtCode());
                                    d.setQty(m.getQty());
                                    d.setArtprice(m.getArtprice());
                                    d.setContainer("");
                                    d.setMou(m.getMou());
                                    dataList.add(d);
                                    scannedList.add(m);
                                }
                            }
                            dataList = removeDuplicate(dataList);
                            SessionManager.INSTANCE.setScannedPrescriptionItems(scannedList);
                            if (null != dataList && dataList.size() > 0) {
                                cartItemCountLayout.setVisibility(View.VISIBLE);
                                total_itemcount_textview.setText(String.valueOf(dataList.size()));
                                checkOutImage.setImageResource(R.drawable.checkout_cart);
                            } else {
                                cartItemCountLayout.setVisibility(View.GONE);
                                checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
                            }
                            if (dataList.size() > 0) {
                                float grandTotalVal = 0;
                                for (int i = 0; i < dataList.size(); i++) {
                                    if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                                        float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                                        grandTotalVal = grandTotalVal + totalPrice;
                                    }
                                }
                                String rupeeSymbol = "\u20B9";
                                grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
                                grandTotalPriceValue = grandTotalPrice.getText().toString();
                            }
                            SessionManager.INSTANCE.setDataList(dataList);
                            cartCount(dataList.size());

                            groupingDuplicates();

                            cartListAdapter.notifyDataSetChanged();
                            curationViewLayout.setVisibility(View.GONE);
                            if (countdown_timer != null) {
                                countdown_timer.cancel();
                            }
                        }
                    });
                    declineButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        }
    };

    private final BroadcastReceiver mPrescriptionMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equalsIgnoreCase("ImageName")) {
                //SessionManager.INSTANCE.setDynamicOrderId(intent.getStringExtra("imageId"));
                myCartController.handleImageService(intent.getStringExtra("data"));
            }
        }
    };

    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equalsIgnoreCase("itemAdded")) {
                String product_sku = intent.getStringExtra("product_sku");
                if (dataList != null && dataList.size() > 0) {
                    for (int n = 0; n < dataList.size(); n++) {
                        OCRToDigitalMedicineResponse object = dataList.get(n);
                        if (object.getArtCode().equalsIgnoreCase(product_sku)) {
                            existelemnt = true;
                        }
                    }
                    if (existelemnt == true) {
                        existelemnt = false;
                        Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_item_already_added));
                    } else {
                        String product_name = intent.getStringExtra("product_name");
                        String product_quantyty = intent.getStringExtra("product_quantyty");
                        String product_container = intent.getStringExtra("product_container");
                        OCRToDigitalMedicineResponse loadobject = new OCRToDigitalMedicineResponse();
                        loadobject.setArtCode(product_sku);
                        loadobject.setArtName(product_name);
                        loadobject.setQty(Integer.parseInt(product_quantyty));
                        loadobject.setContainer(product_container);
                        dataList.add(loadobject);
                        existelemnt = false;
                        SessionManager.INSTANCE.setDataList(dataList);
                        if (dataList.size() > 0) {
                            cartItemCountLayout.setVisibility(View.VISIBLE);
                            total_itemcount_textview.setText(String.valueOf(dataList.size()));
                            checkOutImage.setImageResource(R.drawable.checkout_cart);
                            float grandTotalVal = 0;
                            for (int i = 0; i < dataList.size(); i++) {
                                if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                                    Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                                    grandTotalVal = grandTotalVal + totalPrice;
                                }
                            }
                            String rupeeSymbol = "\u20B9";
                            grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
                            grandTotalPriceValue = grandTotalPrice.getText().toString();
                        } else {
                            cartItemCountLayout.setVisibility(View.GONE);
                            checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
                        }

                        groupingDuplicates();

                        cartListAdapter.notifyDataSetChanged();
                    }
                    curationViewLayout.setVisibility(View.GONE);
                    if (countdown_timer != null) {
                        countdown_timer.cancel();
                    }
                } else {
                    String product_name = intent.getStringExtra("product_name");
                    String product_quantyty = intent.getStringExtra("product_quantyty");
                    String product_container = intent.getStringExtra("product_container");
                    OCRToDigitalMedicineResponse loadobject = new OCRToDigitalMedicineResponse();
                    loadobject.setArtCode(product_sku);
                    loadobject.setArtName(product_name);
                    loadobject.setQty(Integer.parseInt(product_quantyty));
                    loadobject.setContainer(product_container);
                    dataList.add(loadobject);
                    SessionManager.INSTANCE.setDataList(dataList);
                    if (null != dataList && dataList.size() > 0) {
                        cartItemCountLayout.setVisibility(View.VISIBLE);
                        total_itemcount_textview.setText(String.valueOf(dataList.size()));
                        checkOutImage.setImageResource(R.drawable.checkout_cart);
                        float grandTotalVal = 0;
                        for (int i = 0; i < dataList.size(); i++) {
                            if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                                Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                                grandTotalVal = grandTotalVal + totalPrice;
                            }
                        }
                        String rupeeSymbol = "\u20B9";
                        grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
                        grandTotalPriceValue = grandTotalPrice.getText().toString();
                    } else {
                        cartItemCountLayout.setVisibility(View.GONE);
                        checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
                    }

                    groupingDuplicates();

                    cartListAdapter.notifyDataSetChanged();
                    curationViewLayout.setVisibility(View.GONE);
                }
            } else if (message.equalsIgnoreCase("addagainitem")) {
                cartDeleteItemDialog.dismiss();
                String product_position = intent.getStringExtra("product_position");
                int int_position = Integer.parseInt(product_position);
                deletedataList.remove(int_position);
                String product_sku = intent.getStringExtra("product_sku");
                String product_name = intent.getStringExtra("product_name");
                String product_quantyty = intent.getStringExtra("product_quantyty");
                String product_container = intent.getStringExtra("product_container");
                String product_price = intent.getStringExtra("product_price");

                OCRToDigitalMedicineResponse loadobject = new OCRToDigitalMedicineResponse();
                loadobject.setArtCode(product_sku);
                loadobject.setArtName(product_name);
                loadobject.setQty(Integer.parseInt(product_quantyty));
                loadobject.setContainer(product_container);
                loadobject.setArtprice(product_price);
                dataList.add(loadobject);
                SessionManager.INSTANCE.setDataList(dataList);
                SessionManager.INSTANCE.setDeletedDataList(deletedataList);
                if (dataList.size() > 0) {
                    float grandTotalVal = 0;
                    for (int i = 0; i < dataList.size(); i++) {
                        if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                            Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                            grandTotalVal = grandTotalVal + totalPrice;
                        }
                    }
                    String rupeeSymbol = "\u20B9";
                    grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
                    grandTotalPriceValue = grandTotalPrice.getText().toString();

                    groupingDuplicates();

                    cartListAdapter.notifyDataSetChanged();
                }
                if (SessionManager.INSTANCE.getDeletedDataList().size() > 0) {
                    addAgainLayout.setVisibility(View.VISIBLE);
                    removedItemsCount.setText(String.valueOf(SessionManager.INSTANCE.getDeletedDataList().size()));
                } else {
                    addAgainLayout.setVisibility(View.GONE);
                }
                if (SessionManager.INSTANCE.getCurationStatus()) {
                    curationProcessLayout.setVisibility(View.VISIBLE);
                } else {
                    curationProcessLayout.setVisibility(View.GONE);
                }
            } else if (message.equalsIgnoreCase("OrderNow")) {
                Intent intent1 = new Intent(context, MyCartActivity.class);
                intent.putExtra("activityname", "MyOrdersActivity");
                startActivity(intent1);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        addDevEventsDelegate(this);
        MyCartActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        existelemnt = false;
        Constants.getInstance().setConnectivityListener(this);
        if (!ConnectivityReceiver.isConnected()) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }
    }

    private void initLeftMenu() {
        RelativeLayout mySearchLayout = findViewById(R.id.mySearchLayout);
        RelativeLayout myCartLayout = findViewById(R.id.myCartLayout);
        RelativeLayout myOrdersLayout = findViewById(R.id.myOrdersLayout);
        RelativeLayout myOffersLayout = findViewById(R.id.myOffersLayout);
        RelativeLayout myProfileLayout = findViewById(R.id.myProfileLayout);

        ImageView dashboardSearchIcon = findViewById(R.id.dashboardSearchIcon);
        ImageView dashboardMyCartIcon = findViewById(R.id.dashboardMyCartIcon);
        ImageView dashboardMyOrdersIcon = findViewById(R.id.dashboardMyOrdersIcon);
        ImageView dashboardMyOffersIcon = findViewById(R.id.dashboardMyOffersIcon);
        ImageView dashboardMyProfileIcon = findViewById(R.id.dashboardMyProfileIcon);

        TextView dashboardMySearch = findViewById(R.id.dashboardMySearch);
        TextView dashboardMySearchText = findViewById(R.id.dashboardMySearchText);
        TextView dashboardMyCart = findViewById(R.id.dashboardMyCart);
        TextView dashboardMyCartText = findViewById(R.id.dashboardMyCartText);
        TextView dashboardMyOrders = findViewById(R.id.dashboardMyOrders);
        TextView dashboardMyOrdersText = findViewById(R.id.dashboardMyOrdersText);
        TextView dashboardMyOffers = findViewById(R.id.dashboardMyOffers);
        TextView dashboardMyOffersText = findViewById(R.id.dashboardMyOffersText);
        TextView dashboardMyProfile = findViewById(R.id.dashboardMyProfile);
        TextView dashboardMyProfileText = findViewById(R.id.dashboardMyProfileText);
        ImageView userLogout = findViewById(R.id.userLogout);
        userLogout.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(MyCartActivity.this);
            dialog.setContentView(R.layout.dialog_custom_alert);
            if (dialog.getWindow() != null)
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            TextView dialogTitleText = dialog.findViewById(R.id.dialog_info);
            Button okButton = dialog.findViewById(R.id.dialog_ok);
            Button declineButton = dialog.findViewById(R.id.dialog_cancel);
            dialogTitleText.setText(getResources().getString(R.string.label_logout_alert));
            okButton.setText(getResources().getString(R.string.label_ok));
            declineButton.setText(getResources().getString(R.string.label_cancel_text));
            okButton.setOnClickListener(v1 -> {
                dialog.dismiss();
                SessionManager.INSTANCE.logoutUser();

                Intent intent = new Intent(MyCartActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
                finishAffinity();
            });
            declineButton.setOnClickListener(v12 -> dialog.dismiss());
        });

        ImageView dashboardApolloIcon = findViewById(R.id.apollo_logo);
        dashboardApolloIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            finishAffinity();
        });

        myCartLayout.setBackgroundResource(R.color.selected_menu_color);
        dashboardMyCartIcon.setImageResource(R.drawable.dashboard_cart_hover);
        dashboardMyCart.setTextColor(getResources().getColor(R.color.selected_text_color));
        dashboardMyCartText.setTextColor(getResources().getColor(R.color.selected_text_color));
        myCartLayout.setClickable(false);

        mySearchLayout.setOnClickListener(v -> {
            mySearchLayout.setBackgroundResource(R.color.selected_menu_color);
            dashboardSearchIcon.setImageResource(R.drawable.dashboard_search_hover);
            dashboardMySearch.setTextColor(getResources().getColor(R.color.selected_text_color));
            dashboardMySearchText.setTextColor(getResources().getColor(R.color.selected_text_color));

            myCartLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyCartIcon.setImageResource(R.drawable.dashboard_cart);
            dashboardMyCart.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyCartText.setTextColor(getResources().getColor(R.color.colorWhite));

            myOrdersLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyOrdersIcon.setImageResource(R.drawable.dashboard_orders);
            dashboardMyOrders.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyOrdersText.setTextColor(getResources().getColor(R.color.colorWhite));

            myOffersLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyOffersIcon.setImageResource(R.drawable.dashboard_offers);
            dashboardMyOffers.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyOffersText.setTextColor(getResources().getColor(R.color.colorWhite));

            myProfileLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyProfileIcon.setImageResource(R.drawable.dashboard_profile);
            dashboardMyProfile.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyProfileText.setTextColor(getResources().getColor(R.color.colorWhite));

            session.setfixedtimeperiod(fixedtime_period_millisec);
            session.settimeperiod1(System.currentTimeMillis());

            Intent intent = new Intent(MyCartActivity.this, MySearchActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });

        myOrdersLayout.setOnClickListener(v -> {
            mySearchLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardSearchIcon.setImageResource(R.drawable.dashboard_search);
            dashboardMySearch.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMySearchText.setTextColor(getResources().getColor(R.color.colorWhite));

            myCartLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyCartIcon.setImageResource(R.drawable.dashboard_cart);
            dashboardMyCart.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyCartText.setTextColor(getResources().getColor(R.color.colorWhite));

            myOrdersLayout.setBackgroundResource(R.color.selected_menu_color);
            dashboardMyOrdersIcon.setImageResource(R.drawable.dashboard_orders_hover);
            dashboardMyOrders.setTextColor(getResources().getColor(R.color.selected_text_color));
            dashboardMyOrdersText.setTextColor(getResources().getColor(R.color.selected_text_color));

            myOffersLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyOffersIcon.setImageResource(R.drawable.dashboard_offers);
            dashboardMyOffers.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyOffersText.setTextColor(getResources().getColor(R.color.colorWhite));

            myProfileLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyProfileIcon.setImageResource(R.drawable.dashboard_profile);
            dashboardMyProfile.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyProfileText.setTextColor(getResources().getColor(R.color.colorWhite));

            session.setfixedtimeperiod(fixedtime_period_millisec);
            session.settimeperiod1(System.currentTimeMillis());
            Intent intent1 = new Intent(MyCartActivity.this, MyOrdersActivity.class);
            startActivity(intent1);
            finish();
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });

        myOffersLayout.setOnClickListener(v -> {
            mySearchLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardSearchIcon.setImageResource(R.drawable.dashboard_search);
            dashboardMySearch.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMySearchText.setTextColor(getResources().getColor(R.color.colorWhite));

            myCartLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyCartIcon.setImageResource(R.drawable.dashboard_cart);
            dashboardMyCart.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyCartText.setTextColor(getResources().getColor(R.color.colorWhite));

            myOrdersLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyOrdersIcon.setImageResource(R.drawable.dashboard_orders);
            dashboardMyOrders.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyOrdersText.setTextColor(getResources().getColor(R.color.colorWhite));

            myOffersLayout.setBackgroundResource(R.color.selected_menu_color);
            dashboardMyOffersIcon.setImageResource(R.drawable.dashboard_offers_hover);
            dashboardMyOffers.setTextColor(getResources().getColor(R.color.selected_text_color));
            dashboardMyOffersText.setTextColor(getResources().getColor(R.color.selected_text_color));

            myProfileLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyProfileIcon.setImageResource(R.drawable.dashboard_profile);
            dashboardMyProfile.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyProfileText.setTextColor(getResources().getColor(R.color.colorWhite));

            session.setfixedtimeperiod(fixedtime_period_millisec);
            session.settimeperiod1(System.currentTimeMillis());
            Intent intent = new Intent(MyCartActivity.this, MyOffersActivity.class);
            intent.putExtra("categoryname", "Promotions");
            startActivity(intent);
            finish();
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });

        myProfileLayout.setOnClickListener(v -> {
            mySearchLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardSearchIcon.setImageResource(R.drawable.dashboard_search);
            dashboardMySearch.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMySearchText.setTextColor(getResources().getColor(R.color.colorWhite));

            myCartLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyCartIcon.setImageResource(R.drawable.dashboard_cart);
            dashboardMyCart.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyCartText.setTextColor(getResources().getColor(R.color.colorWhite));

            myOrdersLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyOrdersIcon.setImageResource(R.drawable.dashboard_orders);
            dashboardMyOrders.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyOrdersText.setTextColor(getResources().getColor(R.color.colorWhite));

            myOffersLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardMyOffersIcon.setImageResource(R.drawable.dashboard_offers);
            dashboardMyOffers.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMyOffersText.setTextColor(getResources().getColor(R.color.colorWhite));

            myProfileLayout.setBackgroundResource(R.color.selected_menu_color);
            dashboardMyProfileIcon.setImageResource(R.drawable.dashboard_profile_hover);
            dashboardMyProfile.setTextColor(getResources().getColor(R.color.selected_text_color));
            dashboardMyProfileText.setTextColor(getResources().getColor(R.color.selected_text_color));

            session.setfixedtimeperiod(fixedtime_period_millisec);
            session.settimeperiod1(System.currentTimeMillis());
            Intent intent1 = new Intent(MyCartActivity.this, MyProfileActivity.class);
            startActivity(intent1);
            finish();
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        addDevEventsDelegate(this);

        dataList = new ArrayList<>();
        deletedataList = new ArrayList<>();
        initLeftMenu();

        grandTotalPrice = findViewById(R.id.grand_total_price);
        myCartCount = findViewById(R.id.lblCartCnt);
        constraint_Layout = findViewById(R.id.constraint_layout);

        LinearLayout faqLayout = findViewById(R.id.help_layout);
        TextView helpText = findViewById(R.id.help_text);
        helpText.setText(getResources().getString(R.string.faq));
        faqLayout.setOnClickListener(view -> startActivity(new Intent(MyCartActivity.this, FAQActivity.class)));


        ImageView customerCareImg = findViewById(R.id.customer_care_icon);
        LinearLayout customerHelpLayout = findViewById(R.id.customer_help_layout);
        customerHelpLayout.setVisibility(View.GONE);
        customerCareImg.setOnClickListener(v -> {
            if (customerHelpLayout.getVisibility() == View.VISIBLE) {
                customerCareImg.setBackgroundResource(R.drawable.icon_help_circle);
                TranslateAnimation animate = new TranslateAnimation(0, customerHelpLayout.getWidth(), 0, 0);
                animate.setDuration(2000);
                animate.setFillAfter(true);
                customerHelpLayout.startAnimation(animate);
                customerHelpLayout.setVisibility(View.GONE);
            } else {
                customerCareImg.setBackgroundResource(R.drawable.icon_help_circle);
                TranslateAnimation animate = new TranslateAnimation(customerHelpLayout.getWidth(), 0, 0, 0);
                animate.setDuration(2000);
                animate.setFillAfter(true);
                customerHelpLayout.startAnimation(animate);
                customerHelpLayout.setVisibility(View.VISIBLE);
            }
        });

        RecyclerView recycleView = findViewById(R.id.recycle_view_prescription);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyCartActivity.this, LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layoutManager);
        product_list_array = new ArrayList<Product>();
        product_list_array1 = new ArrayList<Product>();
        total_itemcount_textview = findViewById(R.id.total_itemcount_textview);
        cartItemCountLayout = findViewById(R.id.cart_item_count_layout);
        checkOutImage = findViewById(R.id.checkout_image);
        outOfStockArrayList = new ArrayList<Product>();
        inStockArrayList = new ArrayList<Product>();
        outOfStockArrayList1 = new ArrayList<Product>();
        inStockArrayList1 = new ArrayList<Product>();
        TextView promotionViewAll = findViewById(R.id.promotion_viewAll);
        TextView trendingNowViewAll = findViewById(R.id.trending_now_viewAll);
        curationViewLayout = findViewById(R.id.curation_view_layout);
        TextView curationProcessText = findViewById(R.id.curation_process_text);
        curationProcessLayout = findViewById(R.id.curation_process_layout);
        TextView curationText = findViewById(R.id.curation_text);
        addAgainLayout = findViewById(R.id.add_again_layout);
        removedItemsCount = findViewById(R.id.removed_items_count);

        scannedImageHeader = findViewById(R.id.scanned_image_header);
        scannedImageLayout = findViewById(R.id.scanned_image_layout);
        scannedImage = findViewById(R.id.scanned_prescription_image);
        myCartController = new MyCartController(this);
        uploadBgImageController = new UploadBgImageController(this);
        timerHeaderText = findViewById(R.id.timer_header_text);
        timerChildText = findViewById(R.id.timer_child_text);

        handleScannedImageView();

        ImageView offersCloseImage = findViewById(R.id.offers_close_image);
        ImageView tendingCloseImage = findViewById(R.id.tending_close_image);
        ImageView cartBottomImage = findViewById(R.id.cart_bottom_image);
        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        sheetBehaviour = BottomSheetBehavior.from(bottomSheetLayout);
        sheetBehaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        cartBottomImage.setRotation(0);
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        cartBottomImage.setRotation(180);
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        bottomSheetLayout.setOnClickListener(v -> {
            if (sheetBehaviour.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                cartBottomImage.setRotation(0);
                sheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                cartBottomImage.setRotation(180);
                sheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        offersCloseImage.setOnClickListener(v -> {
            bottomSheetLayout.setVisibility(View.GONE);
        });
        tendingCloseImage.setOnClickListener(v -> {
            bottomSheetLayout.setVisibility(View.GONE);
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String rupeeSymbol = "\u20B9";
        grandTotalPrice.setText(rupeeSymbol + "00.00");

        if (ConnectivityReceiver.isConnected()) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }

        myCartController = new MyCartController(this);
        myCartController.upcellCrosscellList("7353910637", MyCartActivity.this);
        upcell_recycle = findViewById(R.id.up_cell_data_recycle);
        crossCell_recycle = findViewById(R.id.cross_cell_data_recycle);
        noDataFound = findViewById(R.id.no_data);
        noDataFoundCrosssel = findViewById(R.id.no_data_found_crosssell);
        noDataFoundUpsel = findViewById(R.id.no_data_found_upsell);
        RecyclerView promotionproductrecycleerview = findViewById(R.id.promotionsRecyclerView);
        promotionproductrecycleerview.setLayoutManager(new GridLayoutManager(MyCartActivity.this, 3));
        RecyclerView trendingnowproductrecycleerview = findViewById(R.id.trendingnowproductrecycleerview);
        trendingnowproductrecycleerview.setLayoutManager(new GridLayoutManager(MyCartActivity.this, 3));
        if (SessionManager.INSTANCE.getOrderCompletionStatus()) {
            curationViewLayout.setVisibility(View.GONE);
            curationProcessLayout.setVisibility(View.GONE);
        }
        if (SessionManager.INSTANCE.getCurationStatus()) {
            curationFlag = true;
            curationViewLayout.setVisibility(View.VISIBLE);
            curationProcessLayout.setVisibility(View.GONE);
        } else {
            curationFlag = false;
            curationViewLayout.setVisibility(View.GONE);
            curationProcessLayout.setVisibility(View.GONE);
        }

        if (null != getIntent().getExtras()) {
            String activityname = this.getIntent().getExtras().getString("activityname");
            if (activityname.equalsIgnoreCase("DeliverySelectionActivity")) {
                if (SessionManager.INSTANCE.getDataList() != null) {
                    dataList = SessionManager.INSTANCE.getDataList();
                }
                if (null != dataList && dataList.size() > 0) {
                    cartItemCountLayout.setVisibility(View.VISIBLE);
                    total_itemcount_textview.setText(String.valueOf(dataList.size()));
                    checkOutImage.setImageResource(R.drawable.checkout_cart);
                    curationViewLayout.setVisibility(View.GONE);
                    curationProcessText.setVisibility(View.GONE);
                    if (dataList.size() > 0) {
                        float grandTotalVal = 0;
                        for (int i = 0; i < dataList.size(); i++) {
                            if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                                Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                                grandTotalVal = grandTotalVal + totalPrice;
                            }
                        }
                        grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
                        grandTotalPriceValue = grandTotalPrice.getText().toString();
                    }
                } else {
                    cartItemCountLayout.setVisibility(View.GONE);
                    checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
                }
            } else if (activityname.equalsIgnoreCase("AddMoreActivity")) {
                dataList = SessionManager.INSTANCE.getDataList();
                if (null != dataList && dataList.size() > 0) {
                    cartItemCountLayout.setVisibility(View.VISIBLE);
                    total_itemcount_textview.setText(String.valueOf(dataList.size()));
                    checkOutImage.setImageResource(R.drawable.checkout_cart);
                    curationViewLayout.setVisibility(View.GONE);
                    if (SessionManager.INSTANCE.getCurationStatus()) {
                        curationProcessLayout.setVisibility(View.VISIBLE);
                    } else {
                        curationProcessLayout.setVisibility(View.VISIBLE);
                    }
                    if (dataList.size() > 0) {
                        float grandTotalVal = 0;
                        for (int i = 0; i < dataList.size(); i++) {
                            if (dataList.get(i).getArtprice() != null) {
                                if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                                    Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                                    grandTotalVal = grandTotalVal + totalPrice;
                                }
                            }
                        }
                        grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
                        grandTotalPriceValue = grandTotalPrice.getText().toString();
                    }
                    if (countdown_timer != null) {
                        countdown_timer.cancel();
                    }
                } else {
                    cartItemCountLayout.setVisibility(View.GONE);
                    checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
                }
            } else if (activityname.equalsIgnoreCase("MyOrdersActivity")) {
                dataList = SessionManager.INSTANCE.getDataList();
                if (null != dataList && dataList.size() > 0) {
                    cartItemCountLayout.setVisibility(View.VISIBLE);
                    total_itemcount_textview.setText(String.valueOf(dataList.size()));
                    checkOutImage.setImageResource(R.drawable.checkout_cart);
                    curationViewLayout.setVisibility(View.GONE);
                    if (SessionManager.INSTANCE.getCurationStatus()) {
                        curationProcessLayout.setVisibility(View.VISIBLE);
                    } else {
                        curationProcessLayout.setVisibility(View.GONE);
                    }
                    if (countdown_timer != null) {
                        countdown_timer.cancel();
                    }
                } else {
                    cartItemCountLayout.setVisibility(View.GONE);
                    checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
                }
            } else {
                if (null != dataList && dataList.size() > 0) {
                    curationViewLayout.setVisibility(View.GONE);
                    if (SessionManager.INSTANCE.getCurationStatus()) {
                        curationProcessLayout.setVisibility(View.VISIBLE);
                    } else {
                        curationProcessLayout.setVisibility(View.GONE);
                    }
                    if (countdown_timer != null) {
                        countdown_timer.cancel();
                    }
                }
            }
        }
        boolean curationstatus = SessionManager.INSTANCE.getCurationStatus();
        if (!curationstatus) {
            curationProcessLayout.setVisibility(View.GONE);
        }

        checkOutImage.setOnClickListener(arg0 -> {
            if (dataList != null && dataList.size() > 0) {
//                startActivity(CheckoutActivity.getStartIntent(this, dataList));
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            } else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No items added to cart", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.color_yellow_button));
                snackbar.show();
            }
//            if (curationViewLayout.getVisibility() == View.GONE) {
//                if (dataList.size() > 0) {
//                    if (curationFlag) {
//                        if (curationDoneFlag) {
//                            checkDeliveryOption();
//                        } else {
//                            final Dialog dialog = new Dialog(MyCartActivity.this);
//                            dialog.setContentView(R.layout.view_curation_alert_dialog);
//                            if (dialog.getWindow() != null)
//                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                            dialog.show();
//                            TextView dialogTitleText = dialog.findViewById(R.id.dialog_info);
//                            Button okButton = dialog.findViewById(R.id.dialog_ok);
//                            Button declineButton = dialog.findViewById(R.id.dialog_cancel);
//                            dialogTitleText.setText(getResources().getString(R.string.label_curation_in_progress_text));
//                            okButton.setText(getResources().getString(R.string.label_ok));
//                            declineButton.setText(getResources().getString(R.string.label_cancel_text));
//                            okButton.setOnClickListener(v -> {
//                                dialog.dismiss();
//                                if (countdown_timer != null)
//                                    countdown_timer.cancel();
//                                checkDeliveryOption();
//                            });
//                            declineButton.setOnClickListener(v -> dialog.dismiss());
//                        }
//                    } else {
//                        checkDeliveryOption();
//                    }
//                } else {
//                    Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_min_order_item_alert));
//                }
//            } else {
//                Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_curation_progress_wait));
//            }
        });

        promotionViewAll.setOnClickListener(arg0 -> {
            SessionManager.INSTANCE.setCartItems(dataList);
            SessionManager.INSTANCE.setDataList(dataList);
            session.setfixedtimeperiod(fixedtime_period_millisec);
            session.settimeperiod1(System.currentTimeMillis());
            Intent intent = new Intent(MyCartActivity.this, MyOffersActivity.class);
            intent.putExtra("categoryname", "Promotions");
            startActivity(intent);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });

        trendingNowViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SessionManager.INSTANCE.setCartItems(dataList);
                SessionManager.INSTANCE.setDataList(dataList);
                session.setfixedtimeperiod(fixedtime_period_millisec);
                session.settimeperiod1(System.currentTimeMillis());
                Intent intent = new Intent(MyCartActivity.this, MyOffersActivity.class);
                intent.putExtra("categoryname", "TrendingNow");
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            }
        });

        cartItemRecyclerView = findViewById(R.id.cartRecyclerView);
        dataList = new ArrayList<>();
        cartListAdapter = new MyCartListAdapter(MyCartActivity.this, dataComparingList, this, expandholdedDataList);
        cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartItemRecyclerView.setAdapter(cartListAdapter);
        cartListAdapter.notifyDataSetChanged();

        if (SessionManager.INSTANCE.getDataList() != null) {
            if (SessionManager.INSTANCE.getDataList().size() > 0) {
                dataList.clear();
                dataList.addAll(SessionManager.INSTANCE.getDataList());

//                dataComparingList.addAll(SessionManager.INSTANCE.getDataList());
//                for (int i = 0; i < dataComparingList.size(); i++) {
//                    for (int j = 0; j < dataComparingList.size(); j++) {
//                        if (i != j && dataComparingList.get(i).getArtName().equals(dataComparingList.get(j).getArtName())) {
//                            expandholdedDataList.add(dataComparingList.get(j));
//                            dataComparingList.remove(j);
//                            j--;
//                        }
//                    }
//                }

                groupingDuplicates();

                cartListAdapter.notifyDataSetChanged();
                float totalMrpPrice = 0;
                for (int i = 0; i < dataList.size(); i++) {
                    if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                        totalMrpPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty() + totalMrpPrice;
                    }
                }
                grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", totalMrpPrice));
                cartCount(dataList.size());
            }
        }

        if (SessionManager.INSTANCE.getDeletedDataList() != null) {
            if (SessionManager.INSTANCE.getDeletedDataList().size() > 0) {
                addAgainLayout.setVisibility(View.VISIBLE);
                removedItemsCount.setText(String.valueOf(SessionManager.INSTANCE.getDeletedDataList().size()));
                deletedataList.addAll(SessionManager.INSTANCE.getDeletedDataList());
            } else {
                addAgainLayout.setVisibility(View.GONE);
            }
        } else {
            addAgainLayout.setVisibility(View.GONE);
        }

        ObjectAnimator textColorAnim = ObjectAnimator.ofInt(curationProcessText, "textColor", Color.WHITE, Color.TRANSPARENT);
        textColorAnim.setDuration(1000);
        textColorAnim.setEvaluator(new ArgbEvaluator());
        textColorAnim.setRepeatCount(ValueAnimator.INFINITE);
        textColorAnim.setRepeatMode(ValueAnimator.REVERSE);
        textColorAnim.start();

        ObjectAnimator textCurationAnim = ObjectAnimator.ofInt(curationText, "textColor", Color.WHITE, Color.TRANSPARENT);
        textCurationAnim.setDuration(1000);
        textCurationAnim.setEvaluator(new ArgbEvaluator());
        textCurationAnim.setRepeatCount(ValueAnimator.INFINITE);
        textCurationAnim.setRepeatMode(ValueAnimator.REVERSE);
        textCurationAnim.start();

        ArrayList<Product> offerList = SessionManager.INSTANCE.getOfferList();
        ArrayList<Product> trendingList = SessionManager.INSTANCE.getTrendingList();
        if (offerList == null || trendingList == null) {
            if (NetworkUtils.isNetworkConnected(MyCartActivity.this)) {
                myCartController.getProductList("SpecialOffers", this);
            } else {
                Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
            }
        }
        if (offerList != null && offerList.size() > 0) {
            product_list_array.clear();
            product_list_array.add(offerList.get(0));
            product_list_array.add(offerList.get(1));
            product_list_array.add(offerList.get(2));
        }
        promotionadaptor = new PromotionsAdapter(MyCartActivity.this, product_list_array);
        promotionadaptor.setViewMode(ViewMode.GRID);
        promotionproductrecycleerview.setAdapter(promotionadaptor);
        if (product_list_array.size() == 0) {
            isEmpty = true;
            product_list_array.add(new Product("load"));
        }
        promotionadaptor.notifyDataChanged();
        if (trendingList != null && trendingList.size() > 0) {
            trendingNowAdapter = new TrendingNowAdapter(MyCartActivity.this, trendingList);
        } else {
            trendingNowAdapter = new TrendingNowAdapter(MyCartActivity.this, product_list_array1);
        }
        trendingNowAdapter.setViewMode(ViewMode.GRID);
        trendingnowproductrecycleerview.setAdapter(trendingNowAdapter);
        if (product_list_array1.size() == 0) {
            isEmpty1 = true;
            product_list_array1.add(new Product("load"));
        }
        trendingNowAdapter.notifyDataChanged();
        SessionManager.INSTANCE.setCurrentPage("");

        session = new Session(MyCartActivity.this);
        if (SessionManager.INSTANCE.gettimerlogin()) {
            fixedtime_period_millisec = fixedScanTime;
            startcountertimer();
        } else {
            if (SessionManager.INSTANCE.gettimerstart_status()) {
                timeperiod_2_millisec = System.currentTimeMillis();
                timeperiod_1_millisce = session.getimeperiod1();
                Long temptime = timeperiod_2_millisec - timeperiod_1_millisce;
                fixedtime_period_millisec = session.getfixedtimeperiod() - temptime;
                if (countdown_timer != null) {
                    countdown_timer.cancel();
                }
                startcountertimer();
            }
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverCheckOut, new IntentFilter("MedicineReciver"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mPrescriptionMessageReceiver, new IntentFilter("PrescriptionReceived"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("cardReceiverCartItems"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceivers, new IntentFilter("cardReceiver"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverNew, new IntentFilter("OrderhistoryCardReciver"));
        Constants.getInstance().setConnectivityListener(this);

        Constants.getInstance().setConnectivityListener(this);

        addAgainLayout.setOnClickListener(v -> {
            cartDeleteItemDialog = new CartDeletedItemsDialog(MyCartActivity.this, SessionManager.INSTANCE.getDeletedDataList(), this);
            cartDeleteItemDialog.show();
            Window window = cartDeleteItemDialog.getWindow();
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
            if (window != null) {
                window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
//        checkOutImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MyCartActivity.this, PaymentOptionsActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
//            }
//        });
    }

    private void groupingDuplicates() {
        dataComparingList.clear();
        expandholdedDataList.clear();

        dataComparingList.addAll(SessionManager.INSTANCE.getDataList());
        for (int i = 0; i < dataComparingList.size(); i++) {
            for (int j = 0; j < dataComparingList.size(); j++) {
                if (i != j && dataComparingList.get(i).getArtName().equals(dataComparingList.get(j).getArtName())) {
                    expandholdedDataList.add(dataComparingList.get(j));
                    dataComparingList.remove(j);
                    j--;
                }
            }
        }
    }

    private void startcountertimer() {
        SessionManager.INSTANCE.settimerlogin(false);
        SessionManager.INSTANCE.settimerstart_status(true);
        countdown_timer = new CountDownTimer(fixedtime_period_millisec, 1000) {
            public void onTick(long millisUntilFinished) {
                fixedtime_period_millisec = millisUntilFinished;
                if (SessionManager.INSTANCE.gettimerstart_status()) {
                    int remainingTime = (int) (fixedScanTime - fixedtime_period_millisec);
                    minVal = (int) (remainingTime) / 60000;
                    secVal = (int) (remainingTime - minVal * 60000) / 1000;
                }
                secVal++;
                if (secVal == 60) {
                    minVal = minVal + 1;
                    secVal = 0;
                }
                int mins = (int) minVal;
                int secs = (int) secVal;
                String asText = mins + "M " + String.format("%02d", secs) + "S";
                timerHeaderText.setText(asText);
                timerChildText.setText(asText);
            }

            public void onFinish() {
                SessionManager.INSTANCE.setCurationStatus(false);
                SessionManager.INSTANCE.settimerlogin(false);
                SessionManager.INSTANCE.settimerstart_status(false);
                if (SessionManager.INSTANCE.getFcmMedicineReceived()) {
                    stopcontdowntimer();
                } else {
                    if (countdown_timer != null)
                        countdown_timer.cancel();
                }
//                stopcontdowntimer();
            }
        }.start();
    }

    private void stopcontdowntimer() {
        Dialog mDialog = Utils.getDialog(MyCartActivity.this, R.layout.dialog_curation_failure);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);

        ImageView closeImage = mDialog.findViewById(R.id.close_image);
        LinearLayout yesLayout = mDialog.findViewById(R.id.yes_layout);
        LinearLayout noLayout = mDialog.findViewById(R.id.no_layout);
        yesLayout.setOnClickListener(v -> {
            mDialog.dismiss();
            if (countdown_timer != null) {
                countdown_timer.cancel();
            }
            curationViewLayout.setVisibility(View.GONE);
            curationProcessLayout.setVisibility(View.GONE);

            Dialog mobileNotifyDialog = Utils.getDialog(MyCartActivity.this, R.layout.dialog_mobile_number_prescription);
            mobileNotifyDialog.setCancelable(false);
            mobileNotifyDialog.setCanceledOnTouchOutside(false);

            ImageView closeNotifyImage = mobileNotifyDialog.findViewById(R.id.close_image);
            LinearLayout okLayout = mobileNotifyDialog.findViewById(R.id.ok_layout);
            TextView userMobileNumber = mobileNotifyDialog.findViewById(R.id.mobile_no_txt);
            userMobileNumber.setText("" + SessionManager.INSTANCE.getMobilenumber());
            closeNotifyImage.setOnClickListener(view -> {
                mobileNotifyDialog.dismiss();
            });

            okLayout.setOnClickListener(view -> {
                mobileNotifyDialog.dismiss();
                finish();
                Intent intent = new Intent(MyCartActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            });
            mobileNotifyDialog.show();
        });

        noLayout.setOnClickListener(v -> {
            mDialog.dismiss();
            if (countdown_timer != null) {
                countdown_timer.cancel();
            }
            curationViewLayout.setVisibility(View.GONE);
            curationProcessLayout.setVisibility(View.GONE);
            SessionManager.INSTANCE.setCurationStatus(false);
            SessionManager.INSTANCE.setDataList(dataList);
            finish();
            Intent intent1 = new Intent(MyCartActivity.this, MySearchActivity.class);
            intent1.putExtra("activityname", "MyCartActivity");
            startActivity(intent1);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });

        if (!((Activity) MyCartActivity.this).isFinishing()) {
            mDialog.show();
        }
    }

    public void updatecartlist() {
        curationViewLayout.setVisibility(View.GONE);
        if (SessionManager.INSTANCE.getCurationStatus()) {
            curationProcessLayout.setVisibility(View.VISIBLE);
            curationViewLayout.setVisibility(View.GONE);
        } else {
            curationProcessLayout.setVisibility(View.GONE);
            curationViewLayout.setVisibility(View.GONE);
        }
        if (countdown_timer != null) {
            countdown_timer.cancel();
        }

        if (SessionManager.INSTANCE.getDataList() != null) {
            List<OCRToDigitalMedicineResponse> tempdata = SessionManager.INSTANCE.getDataList();
            if (message_string.equalsIgnoreCase("addtocart") || message_string.equalsIgnoreCase("cartupdate")) {
                for (OCRToDigitalMedicineResponse data : tempdata) {
                    String product_sku = data.getArtCode();
                    int count = 0;
                    for (OCRToDigitalMedicineResponse tempdata1 : new ArrayList<OCRToDigitalMedicineResponse>(dataList)) {
                        String product_sku1 = tempdata1.getArtCode();
                        if (product_sku1.equalsIgnoreCase(product_sku)) {
                            dataList.remove(count);
                        }
                        count++;
                    }
                    OCRToDigitalMedicineResponse obj = new OCRToDigitalMedicineResponse();
                    obj.setArtCode(data.getArtCode());
                    obj.setArtName(data.getArtName());
                    obj.setQty(data.getQty());
                    obj.setArtprice(data.getArtprice());
                    obj.setContainer("Strip");
                    dataList.add(obj);
                }
            } else if (message_string.equalsIgnoreCase("cartdelete")) {
                int count1 = 0;
                for (OCRToDigitalMedicineResponse tempdata1 : new ArrayList<OCRToDigitalMedicineResponse>(dataList)) {
                    String product_sku1 = tempdata1.getArtCode();
                    if (product_sku1.equalsIgnoreCase(delete_item_sku)) {
                        dataList.remove(count1);
                    }
                    count1++;
                }
            }
            SessionManager.INSTANCE.setDataList(dataList);
        }
        if (null != dataList && dataList.size() > 0) {
            cartItemCountLayout.setVisibility(View.VISIBLE);
            total_itemcount_textview.setText(String.valueOf(dataList.size()));
            checkOutImage.setImageResource(R.drawable.checkout_cart);
            curationViewLayout.setVisibility(View.GONE);
            if (SessionManager.INSTANCE.getCurationStatus()) {
                curationProcessLayout.setVisibility(View.VISIBLE);
            } else {
                curationProcessLayout.setVisibility(View.GONE);
            }
        } else {
            cartItemCountLayout.setVisibility(View.GONE);
            if (SessionManager.INSTANCE.getCurationStatus()) {
                curationProcessLayout.setVisibility(View.VISIBLE);
            } else {
                curationProcessLayout.setVisibility(View.GONE);
            }
            checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
        }
        if (dataList.size() > 0) {
            float grandTotalVal = 0;
            for (int i = 0; i < dataList.size(); i++) {
                if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                    Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                    grandTotalVal = grandTotalVal + totalPrice;
                }
            }
            String rupeeSymbol = "\u20B9";
            grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
            grandTotalPriceValue = grandTotalPrice.getText().toString();

            groupingDuplicates();

            cartListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (countdown_timer != null) {
            countdown_timer.cancel();
        }
        SessionManager.INSTANCE.clearMedicineData();
        startActivity(new Intent(MyCartActivity.this, InsertPrescriptionActivity.class));
        finish();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    public List<OCRToDigitalMedicineResponse> removeDuplicate(List<OCRToDigitalMedicineResponse> dataList) {
        Set<OCRToDigitalMedicineResponse> s = new TreeSet<OCRToDigitalMedicineResponse>(new Comparator<OCRToDigitalMedicineResponse>() {

            @Override
            public int compare(OCRToDigitalMedicineResponse o1, OCRToDigitalMedicineResponse o2) {
                if (o1.getArtCode().equalsIgnoreCase(o2.getArtCode())) {
                    return 0;
                }
                return 1;
            }
        });
        s.addAll(dataList);
        return new ArrayList<>(s);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void cartCount(int count) {
        if (null == myCartCount)
            return;
        if (count != 0) {
            myCartCount.setVisibility(View.VISIBLE);
            myCartCount.setText(String.valueOf(count));
            cartItemCountLayout.setVisibility(View.VISIBLE);
            total_itemcount_textview.setText(String.valueOf(dataList.size()));
            checkOutImage.setImageResource(R.drawable.checkout_cart);
        } else {
            myCartCount.setVisibility(View.GONE);
            myCartCount.setText("0");
            cartItemCountLayout.setVisibility(View.GONE);
            checkOutImage.setImageResource(R.drawable.checkout_cart_unselect);
        }
    }

    @Override
    public void showSnackBAr() {

    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onCount(int count, boolean flag) {

    }

    @Override
    public void onPrescriptionClick(int position, boolean flag) {
        if (presSelectedItem.getMedicineList() != null && presSelectedItem.getMedicineList().size() > 0) {
            ScannedMedicine item = presSelectedItem.getMedicineList().get(position);
            if (flag) {
                dialogMedicineList.add(item);
            } else {
                for (int i = 0; i < dialogMedicineList.size(); i++) {
                    if (dialogMedicineList.get(i).getArtCode().equalsIgnoreCase(item.getArtCode())) {
                        dialogMedicineList.remove(item);
                    }
                }
            }
        }
    }

    @Override
    public void onDeletedAddAllClicked() {
        if (SessionManager.INSTANCE.getDeletedDataList().size() > 0) {
            for (OCRToDigitalMedicineResponse data : new ArrayList<OCRToDigitalMedicineResponse>(SessionManager.INSTANCE.getDeletedDataList())) {
                String product_sku = data.getArtCode();
                String product_name = data.getArtName();
                int product_quantyty = data.getQty();
                String product_container = data.getContainer();
                String product_price = data.getArtprice();
                OCRToDigitalMedicineResponse loadobject = new OCRToDigitalMedicineResponse();
                loadobject.setArtCode(product_sku);
                loadobject.setArtName(product_name);
                loadobject.setQty(product_quantyty);
                loadobject.setMedicineType(data.getMedicineType());
                loadobject.setContainer(product_container);
                loadobject.setArtprice(product_price);
                dataList.add(loadobject);
                deletedataList.remove(data);
            }
        }
        SessionManager.INSTANCE.setDataList(dataList);
        SessionManager.INSTANCE.clearDeletedMedicineData();
        addAgainLayout.setVisibility(View.GONE);
        deletedataList.clear();
        if (dataList.size() > 0) {
            float grandTotalVal = 0;
            for (int i = 0; i < dataList.size(); i++) {
                if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                    Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                    grandTotalVal = grandTotalVal + totalPrice;
                }
            }
            String rupeeSymbol = "\u20B9";
            grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));
            grandTotalPriceValue = grandTotalPrice.getText().toString();

            groupingDuplicates();

            cartListAdapter.notifyDataSetChanged();
            cartCount(dataList.size());
        }
    }

    @Override
    public void onSuccessImageService(GetImageRes res) {
        SessionManager.INSTANCE.setScannedImagePath(res.getImageUrl());
        uploadBgImageController.handleUploadImageService(res.getImageUrl());
        handleScannedImageView();
    }

//    List<UpCellCrossCellResponse.Crossselling> crosssellingList = new ArrayList<>();
//    List<UpCellCrossCellResponse.Upselling> upsellingList = new ArrayList<>();

    List<ItemSearchResponse.Item> crosssellingList = new ArrayList<>();
    List<ItemSearchResponse.Item> upsellingList = new ArrayList<>();
    boolean addToCarLayHandel;
    UpCellCrossCellResponse.Crossselling cs;
    private int crosssellcountforadapter = 0;
    private int upssellcountforadapter = 0;

    @Override
    public void onSuccessSearchItemApi(UpCellCrossCellResponse body) {
        crosssellcountforadapter = 0;
        upssellcountforadapter = 0;
        List<UpCellCrossCellResponse.Crossselling> crossselling = null;
        List<UpCellCrossCellResponse.Upselling> upselling = null;
        if (body != null && body.getCrossselling() != null && body.getCrossselling().size() > 0) {
            addToCarLayHandel = false;
            crossselling = new ArrayList<>();
            crossselling.add(body.getCrossselling().get(0));
            crossselling.add(body.getCrossselling().get(1));
            crossselling.add(body.getCrossselling().get(2));
            for (UpCellCrossCellResponse.Crossselling crossSellingList : crossselling) {
                myCartController.searchItemProducts(crossSellingList.getItemid(), 1, 0);
            }
        } else {
            noDataFound.setVisibility(View.VISIBLE);
        }
        if (body != null && body.getUpselling() != null && body.getUpselling().size() > 0) {
            upselling = new ArrayList<>();
            upselling.add(body.getUpselling().get(0));
            upselling.add(body.getUpselling().get(1));
            upselling.add(body.getUpselling().get(2));
            for (UpCellCrossCellResponse.Upselling upSelling : upselling) {
                myCartController.searchItemProducts(upSelling.getItemid(), 2, 0);
            }
        } else {
            noDataFound.setVisibility(View.VISIBLE);
        }
//        if (crossselling != null && crossselling.size() > 0 || upselling != null && upselling.size() > 0)
//            upSellCrosssellApiCall(crossselling, upselling);

    }

    @Override
    public void upSellCrosssellApiCall(List<UpCellCrossCellResponse.Crossselling> crossselling,
                                       List<UpCellCrossCellResponse.Upselling> upselling) {

    }

    @Override
    public void onSearchFailure(String message) {

    }

    List<OCRToDigitalMedicineResponse> dummyDataList = new ArrayList<>();
    private float balanceQty;

    @Override
    public void onSuccessBarcodeItemApi(ItemSearchResponse itemSearchResponse, int serviceType, int qty) {
        if (serviceType == 0) {
            if (itemSearchResponse.getItemList() != null && itemSearchResponse.getItemList().size() > 0) {
                ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(MyCartActivity.this, itemSearchResponse.getItemList().get(0).getArtCode());
                itemBatchSelectionDilaog.setItemBatchListDialogListener(this);
                ProductSearch medicine = new ProductSearch();

                medicine.setName(itemSearchResponse.getItemList().get(0).getGenericName());
                itemBatchSelectionDilaog.setTitle(itemSearchResponse.getItemList().get(0).getDescription());
                medicine.setSku(itemSearchResponse.getItemList().get(0).getArtCode());
                medicine.setQty(1);
                medicine.setDescription(itemSearchResponse.getItemList().get(0).getDescription());
                medicine.setCategory(itemSearchResponse.getItemList().get(0).getCategory());
                medicine.setMedicineType(itemSearchResponse.getItemList().get(0).getCategory());
                medicine.setIsInStock(itemSearchResponse.getItemList().get(0).getStockqty() != 0 ? 1 : 0);
                medicine.setIsPrescriptionRequired(0);
                medicine.setPrice(itemSearchResponse.getItemList().get(0).getMrp());

                if (qty != 0) {
                    itemBatchSelectionDilaog.setQtyCount(String.valueOf(qty));
                }

                itemBatchSelectionDilaog.setUnitIncreaseListener(view3 -> {
                    if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                        if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                            medicine.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount()) + 1);
                        } else {
                            medicine.setQty(medicine.getQty() + 1);
                        }
                        itemBatchSelectionDilaog.setQtyCount("" + medicine.getQty());
                    } else {
                        Toast.makeText(MyCartActivity.this, "Please enter product quantity", Toast.LENGTH_SHORT).show();
                    }
                });
                itemBatchSelectionDilaog.setUnitDecreaseListener(view4 -> {
                    if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                        if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                            medicine.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount()));
                        }
                        if (medicine.getQty() > 1) {
                            medicine.setQty(medicine.getQty() - 1);
                            itemBatchSelectionDilaog.setQtyCount("" + medicine.getQty());
                        }
                    }
                });
                itemBatchSelectionDilaog.setPositiveListener(view2 -> {

                    itemBatchSelectionDilaog.globalBatchListHandlings(medicine.getDescription(), medicine.getSku(),
                            balanceQty, dummyDataList, MyCartActivity.this, medicine.getMedicineType());


////                activityHomeBinding.transColorId.setVisibility(View.GONE);
//                    Intent intent = new Intent("cardReceiver");
//                    intent.putExtra("message", "Addtocart");
//                    intent.putExtra("product_sku", medicine.getSku());
//                    intent.putExtra("product_name", medicine.getDescription());
//                    intent.putExtra("product_quantyty", itemBatchSelectionDilaog.getQtyCount().toString());
//                    intent.putExtra("product_price", String.valueOf(itemBatchSelectionDilaog.getItemProice()));//String.valueOf(medicine.getPrice())
//                    // intent.putExtra("product_container", product_container);
//                    intent.putExtra("medicineType", medicine.getMedicineType());
//                    intent.putExtra("product_mou", String.valueOf(medicine.getMou()));
//                    intent.putExtra("product_position", String.valueOf(0));
//                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
////                OCRToDigitalMedicineResponse ocrToDigitalMedicineResponse = new OCRToDigitalMedicineResponse();
////                ocrToDigitalMedicineResponse.setArtCode(medicine.getSku());
////                ocrToDigitalMedicineResponse.setArtName(medicine.getName());
////                ocrToDigitalMedicineResponse.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount().toString()));
////                ocrToDigitalMedicineResponse.setArtprice(String.valueOf(itemBatchSelectionDilaog.getItemProice()));
////                ocrToDigitalMedicineResponse.setMedicineType(medicine.getMedicineType());
////                ocrToDigitalMedicineResponse.setMou(String.valueOf(medicine.getMou()));
////                if (null != SessionManager.INSTANCE.getDataList()) {
////                    this.dataList = SessionManager.INSTANCE.getDataList();
////                    dataList.add(ocrToDigitalMedicineResponse);
////                    SessionManager.INSTANCE.setDataList(dataList);
////                    SessionManager.INSTANCE.setDataList(dataList);
////                } else {
////                    dataList.add(ocrToDigitalMedicineResponse);
////                    SessionManager.INSTANCE.setDataList(dataList);
////                }
//                    isDialogShow = false;
//                    itemBatchSelectionDilaog.dismiss();
////                Intent intent1 = new Intent(MySearchActivity.this, MyCartActivity.class);
////                intent1.putExtra("activityname", "AddMoreActivity");
////                startActivity(intent1);
////                finish();
////                overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
                });
                itemBatchSelectionDilaog.setNegativeListener(v -> {
//                activityHomeBinding.transColorId.setVisibility(View.GONE);
                    isDialogShow = false;
                    itemBatchSelectionDilaog.dismiss();
                });
                isDialogShow = true;
                itemBatchSelectionDilaog.show();
            } else {
                Utils.showSnackbar(MyCartActivity.this, constraint_Layout, "No Item found");
            }
            Utils.dismissDialog();
        } else if (serviceType == 1) {
            crosssellcountforadapter++;
            if (itemSearchResponse.getItemList() != null && itemSearchResponse.getItemList().size() > 0) {
                crosssellingList.add(itemSearchResponse.getItemList().get(0));
                if (crosssellcountforadapter == 3) {
                    if (crosssellingList != null && crosssellingList.size() > 0) {
                        CrossCellAdapter crossCellAdapter = new CrossCellAdapter(this, crosssellingList, addToCarLayHandel);
                        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                        crossCell_recycle.setLayoutManager(mLayoutManager4);
                        crossCell_recycle.setItemAnimator(new DefaultItemAnimator());
                        crossCell_recycle.setAdapter(crossCellAdapter);
                        noDataFoundCrosssel.setVisibility(View.GONE);
                    } else {
                        noDataFoundCrosssel.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (crosssellcountforadapter == 3 && crosssellingList != null && crosssellingList.size() > 0)
                noDataFoundCrosssel.setVisibility(View.GONE);
            else
                noDataFoundCrosssel.setVisibility(View.VISIBLE);
        } else if (serviceType == 2) {
            upssellcountforadapter++;
            if (itemSearchResponse.getItemList() != null && itemSearchResponse.getItemList().size() > 0) {
                upsellingList.add(itemSearchResponse.getItemList().get(0));
                if (upssellcountforadapter == 3) {
                    if (upsellingList != null && upsellingList.size() > 0) {
                        UpCellAdapter upCellAdapter = new UpCellAdapter(this, upsellingList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                        upcell_recycle.setLayoutManager(mLayoutManager);
                        upcell_recycle.setItemAnimator(new DefaultItemAnimator());
                        upcell_recycle.setAdapter(upCellAdapter);
                        noDataFoundUpsel.setVisibility(View.GONE);
                    } else {
                        noDataFoundUpsel.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (upssellcountforadapter == 3 && upsellingList != null && upsellingList.size() > 0)
                noDataFoundUpsel.setVisibility(View.GONE);
            else
                noDataFoundUpsel.setVisibility(View.VISIBLE);
        }
    }

    private BroadcastReceiver mMessageReceiverNew = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equalsIgnoreCase("OrderNow")) {
                if (null != SessionManager.INSTANCE.getDataList()) {
                    if (SessionManager.INSTANCE.getDataList().size() > 0) {
                        cartCount(SessionManager.INSTANCE.getDataList().size());
                        dataList = SessionManager.INSTANCE.getDataList();
                    }
                }
                float grandTotalVal = 0;
                for (int i = 0; i < dataList.size(); i++) {
                    if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                        Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                        grandTotalVal = grandTotalVal + totalPrice;
                    }
                }
                String rupeeSymbol = "\u20B9";
                grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));

                Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_item_added_cart));
                cartCount(dataList.size());

                groupingDuplicates();

                cartListAdapter = new MyCartListAdapter(MyCartActivity.this, dataComparingList, MyCartActivity.this, expandholdedDataList);
                cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(MyCartActivity.this));
                cartItemRecyclerView.setAdapter(cartListAdapter);
                cartListAdapter.notifyDataSetChanged();
            }
            if (dataList != null && dataList.size() > 0)
                if (cartListAdapter != null) {

                    groupingDuplicates();

                    cartListAdapter.notifyDataSetChanged();
                }
        }
    };

    private BroadcastReceiver mMessageReceivers = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equalsIgnoreCase("Addtocart")) {
                if (null != SessionManager.INSTANCE.getDataList()) {
                    if (SessionManager.INSTANCE.getDataList().size() > 0) {
                        cartCount(SessionManager.INSTANCE.getDataList().size());
                        dataList = SessionManager.INSTANCE.getDataList();
                    }
                }
                boolean product_avilable = false;
                if (null != dataList) {
                    int count = 0;
                    for (OCRToDigitalMedicineResponse data : dataList) {
                        if (data.getArtCode() != null) {
                            if (data.getArtCode().equalsIgnoreCase(intent.getStringExtra("product_sku"))) {
                                product_avilable = true;
                                int qty = data.getQty();
                                qty = qty + 1;
                                dataList.remove(count);
                                OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                                object1.setArtName(intent.getStringExtra("product_name"));
                                object1.setArtCode(intent.getStringExtra("product_sku"));
                                object1.setMedicineType(intent.getStringExtra("medicineType"));
                                object1.setQty(Integer.parseInt(intent.getStringExtra("product_quantyty")));
                                if (null != intent.getStringExtra("product_price")) {
                                    object1.setArtprice(intent.getStringExtra("product_price"));
                                } else {
                                    object1.setArtprice(String.valueOf(intent.getStringExtra("product_price")));
                                }
                                object1.setMou(String.valueOf(intent.getStringExtra("product_mou")));
//                                object1.setQty(qty);
                                object1.setContainer("Strip");
                                dataList.add(object1);
                                SessionManager.INSTANCE.setDataList(dataList);
                                break;
                            } else {
                                product_avilable = false;
                            }
                        }
                        count = count + 1;
                    }
                    if (!product_avilable) {
                        OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                        object1.setArtName(intent.getStringExtra("product_name"));
                        object1.setArtCode(intent.getStringExtra("product_sku"));
                        object1.setMedicineType(intent.getStringExtra("medicineType"));
                        object1.setQty(Integer.parseInt(intent.getStringExtra("product_quantyty")));
                        if (null != intent.getStringExtra("product_price")) {
                            object1.setArtprice(intent.getStringExtra("product_price"));
                        } else {
                            object1.setArtprice(String.valueOf(intent.getStringExtra("product_price")));
                        }
                        object1.setMou(String.valueOf(intent.getStringExtra("product_mou")));
//                        object1.setQty(1);
                        object1.setContainer("Strip");
                        dataList.add(object1);
                        SessionManager.INSTANCE.setDataList(dataList);
                    }
                }
                float grandTotalVal = 0;
                for (int i = 0; i < dataList.size(); i++) {
                    if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                        Float totalPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty();
                        grandTotalVal = grandTotalVal + totalPrice;
                    }
                }
                String rupeeSymbol = "\u20B9";
                grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", grandTotalVal));

                Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_item_added_cart));
                cartCount(dataList.size());

                groupingDuplicates();

                cartListAdapter = new MyCartListAdapter(MyCartActivity.this, dataComparingList, MyCartActivity.this, expandholdedDataList);
                cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(MyCartActivity.this));
                cartItemRecyclerView.setAdapter(cartListAdapter);
                cartListAdapter.notifyDataSetChanged();
            }
            if (dataList != null && dataList.size() > 0)
                if (cartListAdapter != null) {

                    groupingDuplicates();

                    cartListAdapter.notifyDataSetChanged();
                }
//            if (null != SessionManager.INSTANCE.getDataList() && SessionManager.INSTANCE.getDataList().size() > 0)
//                checkOutNewBtn.setVisibility(View.VISIBLE);
//            else
//                checkOutNewBtn.setVisibility(View.GONE);
        }
    };

    @Override
    public void onFailureBarcodeItemApi(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickIncrement(int position) {
        int currentVal = dataList.get(position).getQty() + 1;
        dataList.get(position).setQty(currentVal);
        updatePriceInfo(position);
    }

    @Override
    public void onClickDecrement(int position) {
        if (1 < dataList.get(position).getQty()) {
            dataList.get(position).setQty(dataList.get(position).getQty() - 1);
            updatePriceInfo(position);
        }
    }

    int pos;

    @Override
    public void onClickDelete(int position, OCRToDigitalMedicineResponse item) {
        final Dialog dialog = new Dialog(MyCartActivity.this);
        dialog.setContentView(R.layout.dialog_custom_alert);
        dialog.setCancelable(false);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView dialogTitleText = dialog.findViewById(R.id.dialog_info);
        Button okButton = dialog.findViewById(R.id.dialog_ok);
        Button declineButton = dialog.findViewById(R.id.dialog_cancel);
        dialogTitleText.setText(getResources().getString(R.string.label_delete_cart_item));
        okButton.setText(getResources().getString(R.string.label_ok));
        declineButton.setText(getResources().getString(R.string.label_cancel_text));
        okButton.setOnClickListener(v -> {
            OCRToDigitalMedicineResponse loadobject = new OCRToDigitalMedicineResponse();

            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getArtCode().equalsIgnoreCase(item.getArtCode())) {
                    pos = i;
                    loadobject.setArtCode(dataList.get(i).getArtCode());
                    loadobject.setArtName(dataList.get(i).getArtName());
                    loadobject.setQty(dataList.get(i).getQty());
                    loadobject.setMedicineType(dataList.get(i).getMedicineType());
                    loadobject.setContainer(dataList.get(i).getContainer());
                    loadobject.setArtprice(dataList.get(i).getArtprice());
                    deletedataList.add(loadobject);
                    SessionManager.INSTANCE.setDeletedDataList(deletedataList);
                }
            }

//            loadobject.setArtCode(dataList.get(position).getArtCode());
//            loadobject.setArtName(dataList.get(position).getArtName());
//            loadobject.setQty(dataList.get(position).getQty());
//            loadobject.setMedicineType(dataList.get(position).getMedicineType());
//            loadobject.setContainer(dataList.get(position).getContainer());
//            loadobject.setArtprice(dataList.get(position).getArtprice());
//            deletedataList.add(loadobject);
//            SessionManager.INSTANCE.setDeletedDataList(deletedataList);

            dataList.remove(pos);
            String rupeeSymbol = "\u20B9";
            SessionManager.INSTANCE.setDataList(dataList);
            cartCount(dataList.size());
            if (null != dataList && dataList.size() > 0) {
                float totalMrpPrice = 0;
                for (int i = 0; i < dataList.size(); i++) {
                    if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                        totalMrpPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty() + totalMrpPrice;
                    }
                }
                grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", totalMrpPrice));
            } else {
                grandTotalPrice.setText(rupeeSymbol + "00.00");
            }

            groupingDuplicates();

            cartListAdapter = new MyCartListAdapter(MyCartActivity.this, dataComparingList, this, expandholdedDataList);
            cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            cartItemRecyclerView.setAdapter(cartListAdapter);
            cartListAdapter.notifyDataSetChanged();
            grandTotalPriceValue = grandTotalPrice.getText().toString();
            dialog.dismiss();
            if (SessionManager.INSTANCE.getDeletedDataList().size() > 0) {
                addAgainLayout.setVisibility(View.VISIBLE);
                removedItemsCount.setText(String.valueOf(SessionManager.INSTANCE.getDeletedDataList().size()));
            } else {
                addAgainLayout.setVisibility(View.GONE);
            }
            if (SessionManager.INSTANCE.getCurationStatus()) {
                curationProcessLayout.setVisibility(View.VISIBLE);
            } else {
                curationProcessLayout.setVisibility(View.GONE);
            }
        });
        declineButton.setOnClickListener(v -> dialog.dismiss());
    }

    @Override
    public void onClickEdit(int position) {

    }

    @Override
    public void onItemClick(int position) {

    }

    private void updatePriceInfo(int position) {
        String rupeeSymbol = "\u20B9";
        float totalMrpPrice = 0;
        for (int i = 0; i < dataList.size(); i++) {
            if (!TextUtils.isEmpty(dataList.get(i).getArtprice())) {
                totalMrpPrice = Float.parseFloat(dataList.get(i).getArtprice()) * dataList.get(i).getQty() + totalMrpPrice;
            }
        }
        grandTotalPrice.setText(rupeeSymbol + "" + String.format("%.2f", totalMrpPrice));
        grandTotalPriceValue = grandTotalPrice.getText().toString();

        SessionManager.INSTANCE.setDataList(dataList);
        cartCount(dataList.size());
        cartListAdapter.notifyItemChanged(position);
    }

    private void checkDeliveryOption() {
        boolean homeDeliveryMode = SessionManager.INSTANCE.getKioskSetupResponse().getDeliveryMode().getHomeDelivery();
        boolean storePickupDeliveryMode = SessionManager.INSTANCE.getKioskSetupResponse().getDeliveryMode().getStorePickup();
        boolean atKioskDeliveryMode = SessionManager.INSTANCE.getKioskSetupResponse().getDeliveryMode().getAtKiosk();
        int deliveryMode = 0;
        if (homeDeliveryMode) {
            deliveryMode++;
        }
        if (storePickupDeliveryMode) {
            deliveryMode++;
        }
        if (atKioskDeliveryMode) {
            deliveryMode++;
        }
        if (deliveryMode >= 2) {
            handleNextActivity();
        } else {
            SessionManager.INSTANCE.setDataList(dataList);
            SessionManager.INSTANCE.setCartItems(dataList);
            if (homeDeliveryMode) {
                SessionManager.INSTANCE.setDeliverytype("DeliveryAtHome");
                if (NetworkUtils.isNetworkConnected(this)) {
                    Utils.showDialog(this, getApplicationContext().getResources().getString(R.string.label_please_wait));
                    myCartController.handelFetchAddressService();
                } else {
                    Utils.showSnackbar(this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
                }
            } else if (storePickupDeliveryMode) {
                SessionManager.INSTANCE.setDeliverytype("PickfromStore");
                Intent intent1 = new Intent(MyCartActivity.this, StorePickupActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            } else if (atKioskDeliveryMode) {
                SessionManager.INSTANCE.setDeliverytype("AtKiosk");
                UserAddress userAddress = new UserAddress();
                userAddress.setName(SessionManager.INSTANCE.getKioskSetupResponse().getKIOSK_ID());
                userAddress.setAddress1(SessionManager.INSTANCE.getKioskSetupResponse().getADDRESS());
                userAddress.setCity(SessionManager.INSTANCE.getKioskSetupResponse().getCITY());
                userAddress.setState(SessionManager.INSTANCE.getKioskSetupResponse().getSTATE());
                userAddress.setPincode(SessionManager.INSTANCE.getKioskSetupResponse().getPINCODE());
                SessionManager.INSTANCE.setUseraddress(userAddress);

                Intent intent2 = new Intent(MyCartActivity.this, OrderSummaryActivity.class);
                intent2.putExtra("activityname", "DeliverySelectionActivity");
                startActivity(intent2);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            }
        }
    }

    private void handleNextActivity() {
        SessionManager.INSTANCE.setCartItems(dataList);
        SessionManager.INSTANCE.setDataList(dataList);
        Intent intent = new Intent(MyCartActivity.this, DeliverySelectionActivity.class);
        intent.putExtra("items_count", String.valueOf(dataList.size()));
        intent.putExtra("grand_total_price", grandTotalPriceValue);
        startActivity(intent);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
    }

    @Override
    public void onSuccessGetAddress(ArrayList<UserAddress> response) {
        Utils.dismissDialog();
        if (response != null && response.size() > 0) {
            ArrayList<UserAddress> addressList = new ArrayList<>(response);
            if (addressList.size() == 0 || addressList == null) {
                Intent intent = new Intent(this, AddressAddActivity.class);
                intent.putExtra("addressList", addressList.size());
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            } else {
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra("addressList", addressList);
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            }
        } else {
            Intent intent = new Intent(this, AddressAddActivity.class);
            intent.putExtra("addressList", 0);
            startActivity(intent);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        }
    }

    @Override
    public void onFailure(String error) {
        Utils.showCustomAlertDialog(this, getResources().getString(R.string.label_server_err_message), false, "OK", "");
    }

    private void handleScannedImageView() {
        if (!TextUtils.isEmpty(SessionManager.INSTANCE.getScannedImagePath())) {
            scannedImageHeader.setVisibility(View.VISIBLE);
            scannedImageLayout.setVisibility(View.VISIBLE);
            Glide.with(MyCartActivity.this)
                    .load(SessionManager.INSTANCE.getScannedImagePath())
                    .apply(new RequestOptions().placeholder(R.drawable.thumbnail_image).error(R.drawable.thumbnail_image))
                    .into(scannedImage);
            scannedImage.setOnClickListener(v -> {
                if (SessionManager.INSTANCE.getScannedPrescriptionItems() != null && SessionManager.INSTANCE.getScannedPrescriptionItems().size() > 0) {
                    ScannedPrescriptionViewDialog scannedPrecViewDialog = new ScannedPrescriptionViewDialog(MyCartActivity.this, SessionManager.INSTANCE.getScannedPrescriptionItems());
                    scannedPrecViewDialog.show();
                    Window window = scannedPrecViewDialog.getWindow();
                    int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.95);
                    int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.95);
                    if (window != null) {
                        window.setLayout(width, height);
                    }
                } else {
                    Utils.showSnackbar(MyCartActivity.this, constraint_Layout, getApplicationContext().getResources().getString(R.string.label_curation_progress_wait));
                }
            });
        } else {
            scannedImageHeader.setVisibility(View.INVISIBLE);
            scannedImageLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccessUploadBgImage() {
        Utils.printMessage(TAG, "Successfully Image Uploaded");
    }

    private int scannerEvent = 0;

    @Override
    public void scannerBarcodeEvent(byte[] barcodeData, int barcodeType, int scannerID) {
        if (!isDialogShow) {
            if (scannerEvent == 0) {
                scannerEvent = 1;
                barcodeEventHandle();
                String barcode_code = new String(barcodeData);
                if (barcode_code != null) {
//            Toast.makeText(this, barcode_code, Toast.LENGTH_LONG).show();
                    Utils.showDialog(this, "Plaese wait...");
                    myCartController.searchItemProducts(barcode_code, 0, 0);
                } else {
                    Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Utils.showSnackbar(MyCartActivity.this, constraint_Layout, "Please complete present action first.");
        }
    }

    private void barcodeEventHandle() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scannerEvent = 0;
            }
        }, 5000);
    }

    @Override
    public void scannerFirmwareUpdateEvent(FirmwareUpdateEvent firmwareUpdateEvent) {

    }

    @Override
    public void scannerImageEvent(byte[] imageData) {

    }

    @Override
    public void scannerVideoEvent(byte[] videoData) {

    }

    @Override
    public void onDismissDialog() {
        isDialogShow = false;
    }
}