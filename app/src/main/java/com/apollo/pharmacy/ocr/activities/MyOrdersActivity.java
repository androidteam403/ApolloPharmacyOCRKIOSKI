package com.apollo.pharmacy.ocr.activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.MyOrdersAdapter;
import com.apollo.pharmacy.ocr.controller.MyOrdersController;
import com.apollo.pharmacy.ocr.dialog.ReOrderDilaog;
import com.apollo.pharmacy.ocr.interfaces.MyOrdersListener;
import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.OrderHistoryResponse;
import com.apollo.pharmacy.ocr.model.PricePrescriptionResponse;
import com.apollo.pharmacy.ocr.model.ScannedData;
import com.apollo.pharmacy.ocr.model.ScannedMedicine;
import com.apollo.pharmacy.ocr.receiver.ConnectivityReceiver;
import com.apollo.pharmacy.ocr.utility.ApplicationConstant;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.NetworkUtils;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class MyOrdersActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener,
        MyOrdersListener {

    private RecyclerView orderListRecyclerView;
    private MyOrdersAdapter orderdetails_adaptor;
    private List<OrderHistoryResponse> orderdetials_list = new ArrayList<>();
    private MyOrdersController myOrdersController;
    private TextView myCartCount;
    private List<OCRToDigitalMedicineResponse> dataList = new ArrayList<>();
    private ConstraintLayout constraintLayout;
    private Button refresh_button;

    @Override
    protected void onResume() {
        super.onResume();
        MyOrdersActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Constants.getInstance().setConnectivityListener(this);
        if (!ConnectivityReceiver.isConnected()) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }
    }

    private void initLeftMenu() {
        LinearLayout faqLayout = findViewById(R.id.help_layout);
        TextView helpText = findViewById(R.id.help_text);
        helpText.setText(getResources().getString(R.string.faq));
        faqLayout.setOnClickListener(view -> startActivity(new Intent(MyOrdersActivity.this, FAQActivity.class)));

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
        refresh_button = findViewById(R.id.refresh_button);

        myCartCount = findViewById(R.id.lblCartCnt);

        if (null != SessionManager.INSTANCE.getDataList()) {
            if (SessionManager.INSTANCE.getDataList().size() > 0) {
                List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                tempCartItemList = SessionManager.INSTANCE.getDataList();
                for (OCRToDigitalMedicineResponse item : tempCartItemList) {
                    dataList.add(item);
                }
                SessionManager.INSTANCE.setDataList(dataList);
                cartCount(dataList.size());
            }
        }
        ImageView userLogout = findViewById(R.id.userLogout);
        userLogout.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(MyOrdersActivity.this);
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

                Intent intent = new Intent(MyOrdersActivity.this, MainActivity.class);
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


        refresh_button.setOnClickListener(v -> {
            finish();
            startActivity(getIntent());
        });

        myOrdersLayout.setBackgroundResource(R.color.selected_menu_color);
        dashboardMyOrdersIcon.setImageResource(R.drawable.dashboard_orders_hover);
        dashboardMyOrders.setTextColor(getResources().getColor(R.color.selected_text_color));
        dashboardMyOrdersText.setTextColor(getResources().getColor(R.color.selected_text_color));
        myOrdersLayout.setClickable(false);

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

            Intent intent = new Intent(this, MySearchActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });

        myCartLayout.setOnClickListener(v -> {
            mySearchLayout.setBackgroundResource(R.color.unselected_menu_color);
            dashboardSearchIcon.setImageResource(R.drawable.dashboard_search);
            dashboardMySearch.setTextColor(getResources().getColor(R.color.colorWhite));
            dashboardMySearchText.setTextColor(getResources().getColor(R.color.colorWhite));

            myCartLayout.setBackgroundResource(R.color.selected_menu_color);
            dashboardMyCartIcon.setImageResource(R.drawable.dashboard_cart_hover);
            dashboardMyCart.setTextColor(getResources().getColor(R.color.selected_text_color));
            dashboardMyCartText.setTextColor(getResources().getColor(R.color.selected_text_color));

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

            Intent intent1 = new Intent(MyOrdersActivity.this, MyCartActivity.class);
            intent1.putExtra("activityname", "AddMoreActivity");
            startActivity(intent1);
            finish();
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
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

            Intent intent = new Intent(this, MyOffersActivity.class);
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

            Intent intent1 = new Intent(this, MyProfileActivity.class);
            startActivity(intent1);
            finish();
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        myOrdersController = new MyOrdersController(this);

        initLeftMenu();
        orderListRecyclerView = findViewById(R.id.myOrdersRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        orderListRecyclerView.setLayoutManager(mLayoutManager);
        orderListRecyclerView.setHasFixedSize(true);
        orderListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        constraintLayout = findViewById(R.id.constraint_layout);

        ImageView imageView = findViewById(R.id.customer_care_icon);
        LinearLayout linearText = findViewById(R.id.customer_help_layout);
        imageView.setOnClickListener(v -> {
            if (linearText.getVisibility() == View.VISIBLE) {
                imageView.setBackgroundResource(R.drawable.icon_help_circle);
                linearText.setVisibility(View.GONE);
            } else {
                imageView.setBackgroundResource(R.drawable.icon_customer_care);
                linearText.setVisibility(View.VISIBLE);
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("OrderhistoryCardReciver"));
        Constants.getInstance().setConnectivityListener(this);

        if (NetworkUtils.isNetworkConnected(MyOrdersActivity.this)) {
            myOrdersController.getOrderHistory(this);
        } else {
            Utils.showSnackbar(MyOrdersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
        }
        SessionManager.INSTANCE.setCurrentPage(ApplicationConstant.ACTIVITY_ADDMORE);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverAddmore, new IntentFilter("MedicineReciverAddMore"));
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equalsIgnoreCase("OrderNow")) {
                Intent intent1 = new Intent(context, MyCartActivity.class);
                intent.putExtra("activityname", "MyOrdersActivity");
                startActivity(intent1);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    @Override
    public void onPricePrescription(PricePrescriptionResponse response) {

    }

    @Override
    public void onOrderHistorySuccess(List<OrderHistoryResponse> response) {
        orderdetials_list = new ArrayList<OrderHistoryResponse>();
        if (response.size() > 0) {
            orderdetials_list.addAll(response);
            Collections.reverse(orderdetials_list);
            orderdetails_adaptor = new MyOrdersAdapter(this, orderdetials_list, this);
            orderListRecyclerView.setAdapter(orderdetails_adaptor);
            orderdetails_adaptor.notifyDataSetChanged();
        } else {
            Utils.showCustomAlertDialog(MyOrdersActivity.this, getApplicationContext().getResources().getString(R.string.try_again_later), false, getResources().getString(R.string.label_ok), "");
        }
    }

    @Override
    public void onOrderHistoryFailure(String error) {
//        Utils.showCustomAlertDialog(MyOrdersActivity.this, getResources().getString(R.string.label_server_err_message), false, getResources().getString(R.string.label_ok), "");
    }

    @Override
    public void onDeletePrescriptionSuccess(Meta m) {

    }

    @Override
    public void onReorderClick(List<OCRToDigitalMedicineResponse> dataList) {
        ReOrderDilaog reOrderDilaog = new ReOrderDilaog(MyOrdersActivity.this, dataList);
        reOrderDilaog.setPositiveListener(view -> {
            reOrderDilaog.dataListLatest();

            if (null != SessionManager.INSTANCE.getDataList()) {
                if (SessionManager.INSTANCE.getDataList().size() > 0) {
                    List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                    tempCartItemList = SessionManager.INSTANCE.getDataList();
                    for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
                        boolean isItemEqual = false;
                        for (OCRToDigitalMedicineResponse duplecateItem : reOrderDilaog.dataListLatest()) {
                            if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
                                isItemEqual = true;
                            }
                        }
                        if (!isItemEqual)
                            reOrderDilaog.dataListLatest().add(listItem);
                    }
                }
            }
            SessionManager.INSTANCE.setDataList(reOrderDilaog.dataListLatest());
            Intent intent = new Intent("OrderhistoryCardReciver");
            intent.putExtra("message", "OrderNow");
            intent.putExtra("MedininesNames", new Gson().toJson(reOrderDilaog.dataListLatest()));
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        });
        reOrderDilaog.setNegativeListener(view -> {
            reOrderDilaog.dismiss();
        });
        reOrderDilaog.show();
    }

    public void cartCount(int count) {
        if (count != 0) {
            myCartCount.setVisibility(View.VISIBLE);
            myCartCount.setText(String.valueOf(count));
        } else {
            myCartCount.setVisibility(View.GONE);
            myCartCount.setText("0");
        }
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiverAddmore);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onPause();
        Constants.getInstance().setConnectivityListener(null);
    }

    private BroadcastReceiver mMessageReceiverAddmore = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equals("MedicinedataAddMore")) {
                ScannedData fcmMedicine = new Gson().fromJson(intent.getStringExtra("MedininesNames"), ScannedData.class);
                medConvertDialog(fcmMedicine);
            }
        }
    };

    public void medConvertDialog(ScannedData fcmMedicine) {
        final Dialog dialog = new Dialog(MyOrdersActivity.this);
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
        okButton.setText(getResources().getString(R.string.label_yes));
        declineButton.setText(getResources().getString(R.string.label_no));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                SessionManager.INSTANCE.setCurationStatus(false);
                if (null != SessionManager.INSTANCE.getDataList()) {
                    if (SessionManager.INSTANCE.getDataList().size() > 0) {
                        List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                        tempCartItemList = SessionManager.INSTANCE.getDataList();
                        for (OCRToDigitalMedicineResponse item : tempCartItemList) {
                            dataList.add(item);
                        }
                        SessionManager.INSTANCE.setDataList(dataList);
                        cartCount(SessionManager.INSTANCE.getDataList().size());
                    }
                }
                Utils.showSnackbar(MyOrdersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_medicine_added_success));
                dialog.dismiss();
            }
        });
        declineButton.setOnClickListener(v -> dialog.dismiss());
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
}