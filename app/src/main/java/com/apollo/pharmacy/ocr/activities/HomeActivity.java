package com.apollo.pharmacy.ocr.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.controller.HomeActivityController;
import com.apollo.pharmacy.ocr.interfaces.HomeListener;
import com.apollo.pharmacy.ocr.model.CategoryList;
import com.apollo.pharmacy.ocr.model.Categorylist_Response;
import com.apollo.pharmacy.ocr.model.PortFolioModel;
import com.apollo.pharmacy.ocr.receiver.ConnectivityReceiver;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.ApplicationConstant;
import com.apollo.pharmacy.ocr.utility.NetworkUtils;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener, HomeListener {

    private TextView myCartCount, welcomeTxt;
    private boolean isUploadPrescription = false;
    private final int MY_READ_PERMISSION_REQUEST_CODE = 103;
    private HomeActivityController homeActivityController;
    private ConstraintLayout constraintLayout;

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        HomeActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView customerCareImg = findViewById(R.id.customer_care_icon);
        LinearLayout customerHelpLayout = findViewById(R.id.customer_help_layout);
        customerHelpLayout.setVisibility(View.VISIBLE);
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

        LinearLayout faqLayout = findViewById(R.id.help_layout);
        TextView helpText = findViewById(R.id.help_text);
        helpText.setText(getResources().getString(R.string.faq));
        faqLayout.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, FAQActivity.class)));

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
        welcomeTxt = findViewById(R.id.welcome_txt);
        ImageView userLogout = findViewById(R.id.userLogout);
        ImageView dashboardApolloIcon = findViewById(R.id.apollo_logo);
        myCartCount = findViewById(R.id.lblCartCnt);
        LinearLayout scanPrescriptionBtn = findViewById(R.id.scan_prescription);
        LinearLayout uploadPrescriptionBtn = findViewById(R.id.upload_prescription);
        constraintLayout = findViewById(R.id.constraint_layout);
        homeActivityController = new HomeActivityController(this);

        userLogout.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(HomeActivity.this);
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

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
                finishAffinity();
            });
            declineButton.setOnClickListener(v12 -> dialog.dismiss());
        });

        dashboardApolloIcon.setClickable(false);

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

            Intent intent = new Intent(HomeActivity.this, MySearchActivity.class);
            startActivity(intent);
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

            SessionManager.INSTANCE.setCurrentPage(ApplicationConstant.ORDER_SUCCESS);
            Intent intent1 = new Intent(HomeActivity.this, MyCartActivity.class);
            intent1.putExtra("activityname", "AddMoreActivity");
            startActivity(intent1);
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
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

            Intent intent1 = new Intent(this, MyOrdersActivity.class);
            startActivity(intent1);
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

            Intent intent = new Intent(HomeActivity.this, MyOffersActivity.class);
            intent.putExtra("categoryname", "Promotions");
            startActivity(intent);
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
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });
        if (null != SessionManager.INSTANCE.getDataList()) {
            if (SessionManager.INSTANCE.getDataList().size() > 0) {
                cartCountData(SessionManager.INSTANCE.getDataList().size());
            }
        }
        scanPrescriptionBtn.setOnClickListener(arg0 -> {
            Utils.dismissDialog();
            finish();
            Intent intent = new Intent(this, InsertPrescriptionActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });

        uploadPrescriptionBtn.setOnClickListener(v -> {
            isUploadPrescription = true;
            checkGalleryPermission();
        });

        if (SessionManager.INSTANCE.getLoggedUserName().isEmpty()) {
            handleRedeemPointsService(); //For Showing User Name on Dashboard
        } else {
            welcomeTxt.setText(getApplicationContext().getResources().getString(R.string.label_welcome) + " " + SessionManager.INSTANCE.getLoggedUserName());
        }
    }

    private void checkGalleryPermission() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            //Permission Granted
            if (isUploadPrescription) {
                handleNextActivity();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_READ_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == MY_READ_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Permission Granted
            if (isUploadPrescription) {
                handleNextActivity();
            }
        }
    }

    private void handleNextActivity() {
        isUploadPrescription = false;
        Utils.dismissDialog();
        finish();
        Intent intent = new Intent(this, UploadPrescriptionActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(HomeActivity.this, UserLoginActivity.class));
        finish();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    public void cartCountData(int count) {
        if (count != 0) {
            myCartCount.setVisibility(View.VISIBLE);
            myCartCount.setText(String.valueOf(count));
        } else {
            myCartCount.setVisibility(View.GONE);
            myCartCount.setText("0");
        }
    }

    private void handleRedeemPointsService() {
        if (!Constants.Get_Portfolio_of_the_User.isEmpty()) {
            if (NetworkUtils.isNetworkConnected(HomeActivity.this)) {
                Utils.showDialog(HomeActivity.this, getApplicationContext().getResources().getString(R.string.label_please_wait));
                homeActivityController.handleRedeemPoints(this);
            } else {
                Utils.showSnackbar(HomeActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
            }
        } else {
            Utils.showSnackbar(HomeActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_something_went_wrong));
        }
    }

    @Override
    public void categoryListSuccessRes(Categorylist_Response response) {
        if (response != null) {
            if (response.getStatus()) {
                List<CategoryList> list = response.getCategoryList();
                for (CategoryList list1 : list) {
                    if (list1.getKEY().equalsIgnoreCase("Baby_Care")) {
                        Constants.Baby_Care = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Health_Monitoring_Devices")) {
                        Constants.Health_Monitoring_Devices = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("First_Aid")) {
                        Constants.First_Aid = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Health_Foods_Drinks")) {
                        Constants.Health_Foods_Drinks = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Beauty_Hygiene")) {
                        Constants.Beauty_Hygiene = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("OTC")) {
                        Constants.OTC = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("General_Wellness")) {
                        Constants.General_Wellness = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Mobility_Aids_Rehabilitation")) {
                        Constants.Mobility_Aids_Rehabilitation = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Nutrition_Supplement")) {
                        Constants.Nutrition_Supplement = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Anti_allergic_drugs")) {
                        Constants.Anti_allergic_drugs = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Infections_Infestation")) {
                        Constants.Infections_Infestation = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Diabetics")) {
                        Constants.Diabetics = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Cardiology")) {
                        Constants.Cardiology = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Skin_disorders")) {
                        Constants.Skin_disorders = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Gastro_entrology")) {
                        Constants.Gastro_entrology = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Endocrine_disorders")) {
                        Constants.Endocrine_disorders = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Promotions")) {
                        Constants.Promotions = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("TrendingNow")) {
                        Constants.TrendingNow = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("CNS_Drugs")) {
                        Constants.CNS_Drugs = list1.getVALUE();
                    } else if (list1.getKEY().equalsIgnoreCase("Generic")) {
                        Constants.Generic = list1.getVALUE();
                    }
                }
            }
        }
    }

    @Override
    public void onFailureService(String message) {
        Utils.dismissDialog();
        Utils.showSnackbar(HomeActivity.this, constraintLayout, message);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccessRedeemPoints(PortFolioModel model) {
        if (model.getCustomerData() != null) {
            if (model.getCustomerData().getName() != null && !model.getCustomerData().getName().isEmpty()) {
                SessionManager.INSTANCE.setLoggedUserName(model.getCustomerData().getName());
                welcomeTxt.setText(getApplicationContext().getResources().getString(R.string.label_welcome) + " " + SessionManager.INSTANCE.getLoggedUserName());
            } else {
                welcomeTxt.setText(getApplicationContext().getResources().getString(R.string.label_welcome) + " " + getApplicationContext().getResources().getString(R.string.label_guest));
            }
        } else {
            welcomeTxt.setText(getApplicationContext().getResources().getString(R.string.label_welcome) + " " + getApplicationContext().getResources().getString(R.string.label_guest));
        }
    }
}