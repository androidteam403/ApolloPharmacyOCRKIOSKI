package com.apollo.pharmacy.ocr.activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.CategoryGridItemAdapter;
import com.apollo.pharmacy.ocr.adapters.MedicineSearchAdapter;
import com.apollo.pharmacy.ocr.adapters.MyOfersAdapterNew;
import com.apollo.pharmacy.ocr.adapters.MyOffersAdapter;
import com.apollo.pharmacy.ocr.adapters.MyoffersAdapterTrending;
import com.apollo.pharmacy.ocr.controller.MyOffersController;
import com.apollo.pharmacy.ocr.databinding.ActivityMyOffersBinding;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.interfaces.MyOffersListener;
import com.apollo.pharmacy.ocr.model.AllOffersResponse;
import com.apollo.pharmacy.ocr.model.GetProductListResponse;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.model.ProductSearch;
import com.apollo.pharmacy.ocr.model.ScannedData;
import com.apollo.pharmacy.ocr.model.ScannedMedicine;
import com.apollo.pharmacy.ocr.model.Suggestion_Product;
import com.apollo.pharmacy.ocr.model.UpCellCrossCellResponse;
import com.apollo.pharmacy.ocr.receiver.ConnectivityReceiver;
import com.apollo.pharmacy.ocr.utility.ApplicationConstant;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.NetworkUtils;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.net.ssl.HttpsURLConnection;

import static com.apollo.pharmacy.ocr.utility.Constants.Promotions;
import static com.apollo.pharmacy.ocr.utility.Constants.TrendingNow;

public class MyOffersActivity extends AppCompatActivity implements MyOffersListener, CartCountListener,
        ConnectivityReceiver.ConnectivityReceiverListener, CategoryGridItemAdapter.CheckOutData {

    public MyOffersController myOffersController;
    public ArrayList<ProductSearch> searchResult1;
    public MedicineSearchAdapter myAdapter;
    private ArrayList<ProductSearch> item = new ArrayList<>();
    private RecyclerView promotionsRecyclerView, trendingNowRecyclerView;
    private MyOffersAdapter promotionList, trendingNowList;
    public TextView textView1;
    private ArrayList<Product> outofstock_array, stockin_array, outofstock_array1, stockin_array1;
    private ArrayList<Product> product_list_array, product_list_array1;
    private int totalPage = 1, currentPage = 1;
    private int totalPage1 = 1, currentPage1 = 1;
    private boolean isEmpty = false;
    private boolean isEmpty1 = false;
    private List<OCRToDigitalMedicineResponse> dataList = new ArrayList<>();
    private final List<OCRToDigitalMedicineResponse> dataListCount = new ArrayList<>();
    private TextView myCartCount;
    private TextView total_itemcount_textview;
    private String myOffersText = "SpecialOffers";
    private TextView plusIcon, itemsCount;
    private ImageView checkOutImage;
    private TextView fcItemCountTxt, pharmaItemCountTxt;
    private LinearLayout fcItemCountLayout, pharmaItemCountLayout;
    private ConstraintLayout constraintLayout;
    private LinearLayout offerLayout, trendingLayout;
    private TextView offerTxt, trendingTxt;
    private ActivityMyOffersBinding activityMyOffersBinding;
    private ImageView mPdfView;
    String googleDocs = "https://docs.google.com/viewer?url=";

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
        MyOffersActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyOffersBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_offers);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        new MyOffersController(this).getAllOffersApiCall();


        mPdfView = (ImageView) findViewById(R.id.offers_pdf_image);
//        WebSettings webSettings = mPdfView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
//        initWebView();
//        String pdf_url = "https://d1xsr68o6znzvt.cloudfront.net/Product_360/product_360/kisok_offers_1.pdf";
//        String pdf = "https://docs.google.com/viewer?url=https://d1xsr68o6znzvt.cloudfront.net/Product_360/product_360/kisok_offers_1.pdf";
//
//        mPdfView.loadUrl(pdf);


//        mPdfView.getSettings().setJavaScriptEnabled(true);
//        mPdfView.loadUrl("https://d1xsr68o6znzvt.cloudfront.net/Product_360/product_360/kisok_offers_1.pdf");
//        new RetrivePDFfromUrl().execute("https://d1xsr68o6znzvt.cloudfront.net/Product_360/product_360/kisok_offers_1.pdf");


        initLeftMenu();

        product_list_array = new ArrayList<Product>();
        product_list_array1 = new ArrayList<Product>();
        item = new ArrayList<>();
        searchResult1 = new ArrayList<>();
        outofstock_array = new ArrayList<Product>();
        stockin_array = new ArrayList<Product>();
        outofstock_array1 = new ArrayList<Product>();
        stockin_array1 = new ArrayList<Product>();

        total_itemcount_textview = findViewById(R.id.total_itemcount_textview);
        plusIcon = findViewById(R.id.plus_icon);
        itemsCount = findViewById(R.id.items_count);
        checkOutImage = findViewById(R.id.checkout_image);
        fcItemCountLayout = findViewById(R.id.fc_item_count_layout);
        fcItemCountTxt = findViewById(R.id.fc_item_count_txt);
        pharmaItemCountLayout = findViewById(R.id.pharma_item_count_layout);
        pharmaItemCountTxt = findViewById(R.id.pharma_item_count_txt);
        textView1 = (TextView) findViewById(R.id.textView1);
        myCartCount = findViewById(R.id.lblCartCnt);
        TextView checkOutText = findViewById(R.id.checkout_text);
        constraintLayout = findViewById(R.id.constraint_layout);

        checkOutText.setOnClickListener(v -> {
            finish();
            Intent intent1 = new Intent(MyOffersActivity.this, MyCartActivity.class);
            startActivity(intent1);
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
        });

        if (null != SessionManager.INSTANCE.getDataList()) {
            if (SessionManager.INSTANCE.getDataList().size() > 0) {
                List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                tempCartItemList = SessionManager.INSTANCE.getDataList();
                for (OCRToDigitalMedicineResponse item : tempCartItemList) {
                    dataListCount.add(item);
                }
            }
        }
        SessionManager.INSTANCE.setDataList(dataListCount);
        cartCount(dataListCount.size());
        checkOutImage.setOnClickListener(v -> {
            finish();
            Intent intent1 = new Intent(MyOffersActivity.this, MyCartActivity.class);
            intent1.putExtra("activityname", "AddMoreActivity");
            startActivity(intent1);
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
        });

        myOffersController = new MyOffersController(this);
        myOffersController.upcellCrosscellList("7353910637", MyOffersActivity.this);
        offerLayout = findViewById(R.id.offer_layout);
        trendingLayout = findViewById(R.id.trending_layout);
        offerTxt = findViewById(R.id.offer_name);
        trendingTxt = findViewById(R.id.trending_name);

        promotionsRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewOffers);
        promotionsRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        trendingNowRecyclerView = findViewById(R.id.trendingnowproductrecycleerview);
        trendingNowRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));

        if (null != getIntent().getExtras()) {
            String activityName = this.getIntent().getExtras().getString("categoryname");
            if (activityName.equalsIgnoreCase("Promotions")) {
                product_list_array.clear();
                trendingNowRecyclerView.setVisibility(View.GONE);
                promotionsRecyclerView.setVisibility(View.VISIBLE);
                myOffersText = "SpecialOffers";
                if (NetworkUtils.isNetworkConnected(MyOffersActivity.this)) {
                    myOffersController.getProductList(myOffersText, this, this, Promotions);
                } else {
                    Utils.showSnackbar(MyOffersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
                }
                offerLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                offerTxt.setTextColor(getResources().getColor(R.color.offers_list_bg));
                trendingLayout.setBackgroundColor(getResources().getColor(R.color.offers_list_bg));
                trendingTxt.setTextColor(getResources().getColor(R.color.colorWhite));
                fcItemCountLayout.setVisibility(View.VISIBLE);
                fcItemCountTxt.setText("0");
                pharmaItemCountLayout.setVisibility(View.GONE);
                pharmaItemCountTxt.setText("0");
            } else {
                product_list_array1.clear();
                promotionsRecyclerView.setVisibility(View.GONE);
                trendingNowRecyclerView.setVisibility(View.VISIBLE);
                myOffersText = "TrendingNow";
                if (NetworkUtils.isNetworkConnected(MyOffersActivity.this)) {
                    myOffersController.getProductList("TrendingNow", this, this, TrendingNow);
                } else {
                    Utils.showSnackbar(MyOffersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
                }
                offerLayout.setBackgroundColor(getResources().getColor(R.color.offers_list_bg));
                offerTxt.setTextColor(getResources().getColor(R.color.colorWhite));
                trendingLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                trendingTxt.setTextColor(getResources().getColor(R.color.offers_list_bg));
                fcItemCountLayout.setVisibility(View.GONE);
                fcItemCountTxt.setText("0");
                pharmaItemCountLayout.setVisibility(View.VISIBLE);
                pharmaItemCountTxt.setText("0");
            }
        }


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

        SessionManager.INSTANCE.setCurrentPage(ApplicationConstant.ACTIVITY_ADDMORE);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverAddmore, new IntentFilter("MedicineReciverAddMore"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("cardReceiver"));
        Constants.getInstance().setConnectivityListener(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverNew, new IntentFilter("OrderhistoryCardReciver"));
        Constants.getInstance().setConnectivityListener(this);

        activityMyOffersBinding.crossSellingOfferLay.setBackgroundColor(getResources().getColor(R.color.offers_list_bg));
        activityMyOffersBinding.crossSellingOfferName.setTextColor(getResources().getColor(R.color.white));
        activityMyOffersBinding.upSellingTrendingLayout.setBackgroundColor(getResources().getColor(R.color.white));
        activityMyOffersBinding.upSellingTrendingName.setTextColor(getResources().getColor(R.color.offers_list_bg));
        if (crosssellingList != null && crosssellingList.size() > 0) {
            activityMyOffersBinding.crossSellingCountLayout.setVisibility(View.VISIBLE);
            activityMyOffersBinding.crossSellingCountTxt.setText(String.valueOf(crosssellingList.size()));
        }
        if (upsellingList != null && upsellingList.size() > 0) {
            activityMyOffersBinding.upSellingItemCountLayout.setVisibility(View.VISIBLE);
            activityMyOffersBinding.upSellingItemCountTxt.setText(String.valueOf(upsellingList.size()));
        } else {
            activityMyOffersBinding.upSellingItemCountLayout.setVisibility(View.GONE);
            activityMyOffersBinding.upSellingItemCountTxt.setText("0");
        }
        activityMyOffersBinding.nodataFound.setVisibility(View.GONE);
        listeners();
    }

    @Override
    public void onSearchSuggestionList(List<Suggestion_Product> m) {
        item.clear();
        for (Suggestion_Product r : m) {
            ProductSearch product = new ProductSearch();
            product.setName(r.getName());
            product.setSku(r.getSku());
            product.setQty(1);
            product.setDescription(r.getDescription());
            product.setId(r.getId());
            product.setImage(r.getImage());
            product.setIsInStock(r.getIsInStock());
            product.setIsPrescriptionRequired(r.getIsPrescriptionRequired());
            product.setPrice(r.getPrice());
            product.setSmallImage(r.getSmallImage());
            product.setStatus(r.getStatus());
            product.setThumbnail(r.getThumbnail());
            product.setTypeId(r.getTypeId());
            item.add(product);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNewSearchResult(List<Suggestion_Product> m) {
        item.clear();
        for (Suggestion_Product r : m) {
            ProductSearch product = new ProductSearch();
            product.setName(r.getName());
            product.setSku(r.getSku());
            product.setQty(1);
            product.setDescription(r.getDescription());
            product.setId(r.getId());
            product.setImage(r.getImage());
            product.setIsInStock(r.getIsInStock());
            product.setIsPrescriptionRequired(r.getIsPrescriptionRequired());
            product.setPrice(r.getPrice());
            product.setSmallImage(r.getSmallImage());
            product.setStatus(r.getStatus());
            product.setThumbnail(r.getThumbnail());
            product.setTypeId(r.getTypeId());
            item.add(product);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessLoadMoreTrendingNow(GetProductListResponse response) {
        if (product_list_array1.size() > 0) {
            product_list_array1.remove(product_list_array1.size() - 1);
            if (response.getProducts() != null && response.getProducts().size() > 0) {
                ArrayList<Product> temp = (ArrayList<Product>) response.getProducts();
                for (Product p : temp) {
                    if (p.getIsInStock() == 1) {
                        product_list_array1.add(p);
                    } else {
                        outofstock_array1.add(p);
                    }
                }
                if (totalPage1 == currentPage1) {
                    if (isEmpty1) {
                        product_list_array1.remove(0);
                        trendingNowList.notifyItemRemoved(0);
                        isEmpty1 = false;
                    }
                    product_list_array1.addAll(outofstock_array1);
                    trendingNowList.setMoreDataAvailable(false);
                }
            }
            if (null != product_list_array1 && product_list_array1.size() > 0) {
                total_itemcount_textview.setVisibility(View.GONE);
                total_itemcount_textview.setText(product_list_array1.size() + " " + getApplicationContext().getResources().getString(R.string.label_items));
                pharmaItemCountTxt.setText(String.valueOf(product_list_array1.size()));
            } else {
                total_itemcount_textview.setVisibility(View.GONE);
                total_itemcount_textview.setText("0" + " " + getApplicationContext().getResources().getString(R.string.label_items));
                pharmaItemCountTxt.setText("0");
            }
            trendingNowList.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailureLoadMoreTrendingNow(String message) {
        Utils.showCustomAlertDialog(MyOffersActivity.this, message, false, getApplicationContext().getResources().getString(R.string.label_ok), "");
    }

    @Override
    public void onSuccessLoadMorePromotions(GetProductListResponse response) {
        if (product_list_array.size() > 0) {
            product_list_array.remove(product_list_array.size() - 1);
            if (null != response.getProducts() && response.getProducts().size() > 0) {
                ArrayList<Product> temp = (ArrayList<Product>) response.getProducts();
                for (Product p : temp) {
                    if (p.getIsInStock() == 1) {
                        product_list_array.add(p);
                    } else {
                        outofstock_array.add(p);
                    }
                }
                if (totalPage == currentPage) {
                    if (isEmpty) {
                        product_list_array.remove(0);
                        promotionList.notifyItemRemoved(0);
                        isEmpty = false;
                    }
                    product_list_array.addAll(outofstock_array);
                    promotionList.setMoreDataAvailable(false);
                }
            }
            if (null != product_list_array && product_list_array.size() > 0) {
                total_itemcount_textview.setVisibility(View.GONE);
                total_itemcount_textview.setText(product_list_array.size() + " " + getApplicationContext().getResources().getString(R.string.label_items));
                fcItemCountTxt.setText(String.valueOf(product_list_array.size()));
            } else {
                total_itemcount_textview.setVisibility(View.GONE);
                total_itemcount_textview.setText("0" + " " + getApplicationContext().getResources().getString(R.string.label_items));
                fcItemCountTxt.setText("0");
            }
            promotionList.notifyDataChanged();
        }
    }

    @Override
    public void onFailureLoadMorePromotions(String message) {
        Utils.showCustomAlertDialog(MyOffersActivity.this, message, false, getApplicationContext().getResources().getString(R.string.label_ok), "");
    }

    @Override
    public void onSuccessSearchItemApi(ItemSearchResponse itemSearchResponse) {

    }

    public void listeners() {
        activityMyOffersBinding.crossSellingOfferLay.setOnClickListener(v -> {
            product_list_array.clear();
            product_list_array1.clear();
            activityMyOffersBinding.upSellingTrendingrecycleerviewNew.setVisibility(View.GONE);
            activityMyOffersBinding.crossSellingRecycleNew.setVisibility(View.VISIBLE);
            myOffersText = "SpecialOffers";

            MyOfersAdapterNew crossCellAdapter = new MyOfersAdapterNew(MyOffersActivity.this, this, crosssellingList, this);
            activityMyOffersBinding.crossSellingRecycleNew.setLayoutManager(new GridLayoutManager(this, 6));
            activityMyOffersBinding.crossSellingRecycleNew.setAdapter(crossCellAdapter);

//            if (NetworkUtils.isNetworkConnected(MyOffersActivity.this)) {
////                myOffersController.getProductList("SpecialOffers", this, this, Promotions);
//                myOffersController.upcellCrosscellList("7353910637", getApplicationContext());
//            } else {
//                Utils.showSnackbar(MyOffersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
//            }
            activityMyOffersBinding.crossSellingOfferLay.setBackgroundColor(getResources().getColor(R.color.offers_list_bg));
            activityMyOffersBinding.crossSellingOfferName.setTextColor(getResources().getColor(R.color.colorWhite));
            activityMyOffersBinding.upSellingTrendingLayout.setBackgroundColor(getResources().getColor(R.color.white));
            activityMyOffersBinding.upSellingTrendingName.setTextColor(getResources().getColor(R.color.offers_list_bg));
            if (crosssellingList != null && crosssellingList.size() > 0) {

                activityMyOffersBinding.crossSellingCountLayout.setVisibility(View.VISIBLE);
                activityMyOffersBinding.crossSellingCountTxt.setText(String.valueOf(crosssellingList.size()));
            }
            if (upsellingList != null && upsellingList.size() > 0) {
                activityMyOffersBinding.upSellingItemCountLayout.setVisibility(View.VISIBLE);
                activityMyOffersBinding.upSellingItemCountTxt.setText(String.valueOf(upsellingList.size()));
            } else {
                activityMyOffersBinding.upSellingItemCountLayout.setVisibility(View.GONE);
                activityMyOffersBinding.upSellingItemCountTxt.setText("0");
            }
            activityMyOffersBinding.nodataFound.setVisibility(View.GONE);
            if (crosssellingList.size() < 1) {
                activityMyOffersBinding.nodataFound.setVisibility(View.VISIBLE);
            }
        });

        activityMyOffersBinding.upSellingTrendingLayout.setOnClickListener(v -> {
            product_list_array.clear();
            product_list_array1.clear();
            activityMyOffersBinding.crossSellingRecycleNew.setVisibility(View.GONE);
            activityMyOffersBinding.upSellingTrendingrecycleerviewNew.setVisibility(View.VISIBLE);
            myOffersText = "TrendingNow";

            MyoffersAdapterTrending trending = new MyoffersAdapterTrending(MyOffersActivity.this, this, upsellingList, this);
            activityMyOffersBinding.upSellingTrendingrecycleerviewNew.setLayoutManager(new GridLayoutManager(this, 6));
            activityMyOffersBinding.upSellingTrendingrecycleerviewNew.setAdapter(trending);

//            if (NetworkUtils.isNetworkConnected(MyOffersActivity.this)) {
////                myOffersController.getProductList("TrendingNow", this, this, TrendingNow);
//                myOffersController.upcellCrosscellList("7353910637", getApplicationContext());
//
//            } else {
//                Utils.showSnackbar(MyOffersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
//            }
            activityMyOffersBinding.crossSellingOfferLay.setBackgroundColor(getResources().getColor(R.color.white));
            activityMyOffersBinding.crossSellingOfferName.setTextColor(getResources().getColor(R.color.offers_list_bg));
            activityMyOffersBinding.upSellingTrendingLayout.setBackgroundColor(getResources().getColor(R.color.offers_list_bg));
            activityMyOffersBinding.upSellingTrendingName.setTextColor(getResources().getColor(R.color.colorWhite));
            if (crosssellingList != null && crosssellingList.size() > 0) {
                activityMyOffersBinding.crossSellingCountLayout.setVisibility(View.VISIBLE);
                activityMyOffersBinding.crossSellingCountTxt.setText(String.valueOf(crosssellingList.size()));
            } else {
                activityMyOffersBinding.crossSellingCountLayout.setVisibility(View.GONE);
                activityMyOffersBinding.crossSellingCountTxt.setText("0");
            }
            if (upsellingList != null && upsellingList.size() > 0) {
                activityMyOffersBinding.upSellingItemCountLayout.setVisibility(View.VISIBLE);
                activityMyOffersBinding.upSellingItemCountTxt.setText(String.valueOf(upsellingList.size()));
            }
            activityMyOffersBinding.nodataFound.setVisibility(View.GONE);
            if (upsellingList.size() < 1) {
                activityMyOffersBinding.nodataFound.setVisibility(View.VISIBLE);
            }
        });
    }

    List<UpCellCrossCellResponse.Crossselling> crosssellingList = new ArrayList<>();

    List<UpCellCrossCellResponse.Upselling> upsellingList = new ArrayList<>();

    @Override
    public void onSuccessSearchUpcellCroscellApi(UpCellCrossCellResponse productList) {
        if (productList != null && productList.getCrossselling() != null && productList.getCrossselling().size() > 0) {
            crosssellingList = productList.getCrossselling();

            activityMyOffersBinding.upSellingTrendingrecycleerviewNew.setVisibility(View.GONE);
            activityMyOffersBinding.crossSellingRecycleNew.setVisibility(View.VISIBLE);
            myOffersText = "SpecialOffers";

            MyOfersAdapterNew crossCellAdapter = new MyOfersAdapterNew(MyOffersActivity.this, this, crosssellingList, this);
            activityMyOffersBinding.crossSellingRecycleNew.setLayoutManager(new GridLayoutManager(this, 6));
            activityMyOffersBinding.crossSellingRecycleNew.setAdapter(crossCellAdapter);

            activityMyOffersBinding.crossSellingOfferLay.setBackgroundColor(getResources().getColor(R.color.offers_list_bg));
            activityMyOffersBinding.crossSellingOfferName.setTextColor(getResources().getColor(R.color.colorWhite));
            activityMyOffersBinding.upSellingTrendingLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            activityMyOffersBinding.upSellingTrendingName.setTextColor(getResources().getColor(R.color.offers_list_bg));
            activityMyOffersBinding.crossSellingCountLayout.setVisibility(View.VISIBLE);
            activityMyOffersBinding.crossSellingCountTxt.setText(String.valueOf(crosssellingList.size()));
            activityMyOffersBinding.nodataFound.setVisibility(View.GONE);
        } else {
            activityMyOffersBinding.crossSellingRecycleNew.setVisibility(View.GONE);
            activityMyOffersBinding.nodataFound.setVisibility(View.VISIBLE);
        }
        if (productList != null && productList.getUpselling() != null && productList.getUpselling().size() > 0) {
            upsellingList = productList.getUpselling();
            activityMyOffersBinding.nodataFound.setVisibility(View.GONE);
        } else {
            activityMyOffersBinding.crossSellingRecycleNew.setVisibility(View.GONE);
            activityMyOffersBinding.nodataFound.setVisibility(View.VISIBLE);
        }

        if (upsellingList != null && upsellingList.size() > 0) {
            activityMyOffersBinding.upSellingItemCountLayout.setVisibility(View.VISIBLE);
            activityMyOffersBinding.upSellingItemCountTxt.setText(String.valueOf(upsellingList.size()));
        } else {
            activityMyOffersBinding.upSellingItemCountLayout.setVisibility(View.GONE);
            activityMyOffersBinding.upSellingItemCountTxt.setText("0");
        }

        Utils.dismissDialog();
    }

    private int screeSize() {
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        String toastMsg;

        if (screenSize == 4) {
            return 4;
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            toastMsg = "Large screen";
            return 4;
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            toastMsg = "Normal screen";
            return 3;
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            toastMsg = "Small screen";
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    public void onSearchFailureUpcellCroscell(String message) {

    }

    @Override
    public void onSuccessAllOffers(AllOffersResponse allOffersResponse) {

    }

    @Override
    public void onFailureAllOffers(String message) {

    }

    @Override
    public void onSearchFailure(String error) {
        Utils.showCustomAlertDialog(MyOffersActivity.this, error, false, getApplicationContext().getResources().getString(R.string.label_ok), "");
    }

    public void imageclicked_method(ProductSearch productsku) {

    }

    @Override
    public void onSuccessProductList(HashMap<String, GetProductListResponse> productList) {
//        product_list_array.clear();
//        product_list_array1.clear();
//        if (null != myOffersController.getSpecialOffersCategoryList() && myOffersController.getSpecialOffersCategoryList().size() > 0) {
//            for (String s : myOffersController.getSpecialOffersCategoryList()) {
//                if (myOffersText.equalsIgnoreCase("SpecialOffers")) {
//                    if (productList.get(s) != null) {
//                        totalPage = (productList.get(s).getProductCount() / 20);
//                        if (productList.get(s).getProductCount() % 3 != 0) {
//                            totalPage++;
//                        }
//                        ArrayList<Product> products = (ArrayList<Product>) productList.get(s).getProducts();
//                        for (Product d : products) {
//                            Product load_obj = new Product();
//                            load_obj.setName(d.getName());
//                            load_obj.setSku(d.getSku());
//                            load_obj.setPrice(d.getPrice());
//                            load_obj.setImage(d.getImage());
//                            load_obj.setSpecialPrice(d.getSpecialPrice());
//                            load_obj.setProduct_pricetype("offer");
//                            load_obj.setIsInStock(d.getIsInStock());
//                            load_obj.setMou(d.getMou());
//                            load_obj.setId(d.getId());
//                            if (d.getIsInStock() == 1) {
//                                stockin_array.add(load_obj);
//                            } else {
//                                outofstock_array.add(load_obj);
//                            }
//                        }
//                        product_list_array.addAll(stockin_array);
//                        if (null != product_list_array && product_list_array.size() > 0) {
//                            total_itemcount_textview.setVisibility(View.GONE);
//                            total_itemcount_textview.setText(product_list_array.size() + " " + getApplicationContext().getResources().getString(R.string.label_items));
//                            fcItemCountTxt.setText(String.valueOf(product_list_array.size()));
//                        } else {
//                            total_itemcount_textview.setVisibility(View.GONE);
//                            total_itemcount_textview.setText("0" + " " + getApplicationContext().getResources().getString(R.string.label_items));
//                            fcItemCountTxt.setText("0");
//                        }
//                        if (totalPage > currentPage) {
//                            product_list_array.remove(0);
//                        }
//                        promotionList = new MyOffersAdapter(this, product_list_array, product_list_array, this);
//                        promotionList.setViewMode(ViewMode.GRID);
//                        trendingNowRecyclerView.setVisibility(View.GONE);
////                        promotionsRecyclerView.setAdapter(promotionList);
//                        promotionsRecyclerView.setVisibility(View.VISIBLE);
//                        promotionList.notifyDataSetChanged();
//                        promotionList.setLoadMoreListener(() -> promotionsRecyclerView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                currentPage++;
//                                if (totalPage >= currentPage) {
//                                    loadMoreOffers();
//                                }
//                            }
//                        }));
//                    }
//                } else {
//                    if (productList.get(s) != null) {
//                        if (productList.get(s).getProductCount() > 0) {
//                            totalPage1 = (productList.get(s).getProductCount() / 20);
//                            if (productList.get(s).getProductCount() % 3 != 0) {
//                                totalPage1++;
//                            }
//                            ArrayList<Product> products = (ArrayList<Product>) productList.get(s).getProducts();
//                            for (Product d : products) {
//                                Product load_obj = new Product();
//                                load_obj.setName(d.getName());
//                                load_obj.setSku(d.getSku());
//                                load_obj.setPrice(d.getPrice());
//                                load_obj.setImage(d.getImage());
//                                load_obj.setSpecialPrice(d.getSpecialPrice());
//                                load_obj.setProduct_pricetype("trendingnow");
//                                load_obj.setIsInStock(d.getIsInStock());
//                                load_obj.setMou(d.getMou());
//                                load_obj.setId(d.getId());
//                                if (d.getIsInStock() == 1) {
//                                    stockin_array1.add(load_obj);
//                                } else {
//                                    outofstock_array1.add(load_obj);
//                                }
//                            }
//                            product_list_array1.addAll(stockin_array1);
//                            if (null != product_list_array1 && product_list_array1.size() > 0) {
//                                total_itemcount_textview.setVisibility(View.GONE);
//                                total_itemcount_textview.setText(product_list_array1.size() + " " + getApplicationContext().getResources().getString(R.string.label_items));
//                                pharmaItemCountTxt.setText(String.valueOf(product_list_array1.size()));
//                            } else {
//                                total_itemcount_textview.setVisibility(View.GONE);
//                                total_itemcount_textview.setText("0" + " " + getApplicationContext().getResources().getString(R.string.label_items));
//                                pharmaItemCountTxt.setText("0");
//                            }
//                            if (totalPage1 > currentPage1) {
//                                product_list_array1.remove(0);
//                            }
//                            trendingNowList = new MyOffersAdapter(this, product_list_array1, product_list_array1, this);
//                            trendingNowList.setViewMode(ViewMode.GRID);
//                            promotionsRecyclerView.setVisibility(View.GONE);
////                            trendingNowRecyclerView.setAdapter(trendingNowList);
//                            trendingNowRecyclerView.setVisibility(View.VISIBLE);
//                            trendingNowList.notifyDataSetChanged();
//                            trendingNowList.setLoadMoreListener(() -> trendingNowRecyclerView.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    currentPage1++;
//                                    if (totalPage1 >= currentPage1) {
//                                        loadMore_tendingnow();
//                                    }
//                                }
//                            }));
//                        }
//                    }
//                }
//            }
//        }
    }

    @Override
    public void onSuccessProductListMainCategory
            (HashMap<String, GetProductListResponse> productList) {
    }

    @Override
    public void onFailure(String error) {
//        Utils.showCustomAlertDialog(MyOffersActivity.this, error, false, "OK", "");
    }

    private void loadMore_tendingnow() {
        product_list_array1.add(new Product("load"));
        trendingNowList.notifyItemInserted(product_list_array1.size() - 1);
        int cId = myOffersController.getHealthWellnessCategoryId("TrendingNow");
        myOffersController.handleLoadMoreTrendingNowService(cId, currentPage1);
    }

    private void loadMoreOffers() {
        product_list_array.add(new Product("load"));
        promotionList.notifyItemInserted(product_list_array.size() - 1);
        int cId = myOffersController.getHealthWellnessCategoryId("Promotions");
        myOffersController.handleLoadMorePromotionsService(cId, currentPage);
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
            final Dialog dialog = new Dialog(MyOffersActivity.this);
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

                Intent intent = new Intent(MyOffersActivity.this, MainActivity.class);
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

        myOffersLayout.setBackgroundResource(R.color.selected_menu_color);
        dashboardMyOffersIcon.setImageResource(R.drawable.dashboard_offers_hover);
        dashboardMyOffers.setTextColor(getResources().getColor(R.color.selected_text_color));
        dashboardMyOffersText.setTextColor(getResources().getColor(R.color.selected_text_color));
        myOffersLayout.setClickable(false);

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

            Intent intent1 = new Intent(MyOffersActivity.this, MyCartActivity.class);
            intent1.putExtra("activityname", "AddMoreActivity");
            startActivity(intent1);
            finish();
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
    public void cartCount(int count) {
        if (count != 0) {

            List<OCRToDigitalMedicineResponse> countUniques = new ArrayList<>();
            countUniques.addAll(SessionManager.INSTANCE.getDataList());

            for (int i = 0; i < countUniques.size(); i++) {
                for (int j = 0; j < countUniques.size(); j++) {
                    if (i != j && countUniques.get(i).getArtName().equals(countUniques.get(j).getArtName())) {
                        countUniques.remove(j);
                        j--;
                    }
                }
            }

            myCartCount.setVisibility(View.VISIBLE);
//            myCartCount.setText(String.valueOf(count));
            myCartCount.setText(String.valueOf(countUniques.size()));
//            itemsCount.setVisibility(View.VISIBLE);
//            plusIcon.setVisibility(View.VISIBLE);
//            checkOutImage.setVisibility(View.VISIBLE);
            checkOutImage.setImageResource(R.drawable.checkout_cart);
            itemsCount.setText(count + " " + getApplicationContext().getResources().getString(R.string.label_items) + " " + getApplicationContext().getResources().getString(R.string.label_added));
        } else {
            plusIcon.setVisibility(View.GONE);
            itemsCount.setVisibility(View.GONE);
            checkOutImage.setVisibility(View.GONE);
            myCartCount.setVisibility(View.GONE);
            myCartCount.setText("");
            itemsCount.setText("");
        }
    }

    @Override
    public void showSnackBAr() {

    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiverAddmore);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiverNew);

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
                Utils.showSnackbar(MyOffersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_item_added_cart));
                cartCount(dataList.size());
            }
        }
    };

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
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
                Utils.showSnackbar(MyOffersActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_item_added_cart));
                cartCount(dataList.size());
            }
//            if (null != SessionManager.INSTANCE.getDataList())
//                checkOutNewBtn.setVisibility(View.VISIBLE);
//            else
//                checkOutNewBtn.setVisibility(View.GONE);
        }
    };

    public void medConvertDialog(ScannedData fcmMedicine) {
        final Dialog dialog = new Dialog(MyOffersActivity.this);
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
        okButton.setOnClickListener(v -> {
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
                }
            }
            SessionManager.INSTANCE.setDataList(dataList);
            cartCount(SessionManager.INSTANCE.getDataList().size());
            Utils.showSnackbar(MyOffersActivity.this, constraintLayout, getResources().getString(R.string.label_medicine_added_success));
            dialog.dismiss();
        });
        declineButton.setOnClickListener(v -> dialog.dismiss());
    }

    public List<OCRToDigitalMedicineResponse> removeDuplicate
            (List<OCRToDigitalMedicineResponse> dataList) {
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
    public void checkoutData() {
        finish();
        Intent intent1 = new Intent(MyOffersActivity.this, MyCartActivity.class);
        intent1.putExtra("activityname", "AddMoreActivity");
        startActivity(intent1);
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
//            mPdfView.fromStream(inputStream).load();
        }

    }

//    private void initWebView() {
//        mPdfView.setWebChromeClient(new MyWebChromeClient(getBaseContext()));
//        mPdfView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                Utils.showDialog(MyOffersActivity.this, "Please Wait");
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                if (url.endsWith(".pdf")) {
////                    String pdfUrl = googleDocs + url;
////                    mPdfView.loadUrl(pdfUrl);
////                } else {
////                mPdfView.loadUrl(url);
////                }
////                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Utils.dismissDialog();
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                Utils.dismissDialog();
//            }
//        });
////        mPdfView.clearCache(true);
////        mPdfView.clearHistory();
////        mPdfView.getSettings().setJavaScriptEnabled(true);
////        mPdfView.getSettings().setLoadWithOverviewMode(true);
////        mPdfView.getSettings().setUseWideViewPort(true);
//
//    }

//    private class MyWebChromeClient extends WebChromeClient {
//        Context context;
//
//        public MyWebChromeClient(Context context) {
//            super();
//            this.context = context;
//        }
//    }
}