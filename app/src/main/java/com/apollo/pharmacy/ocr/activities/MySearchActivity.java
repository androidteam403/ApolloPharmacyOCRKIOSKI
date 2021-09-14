package com.apollo.pharmacy.ocr.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.CategoryGridItemAdapter;
import com.apollo.pharmacy.ocr.adapters.MedicineSearchAdapter;
import com.apollo.pharmacy.ocr.adapters.ProductsCustomAdapter;
import com.apollo.pharmacy.ocr.adapters.SubCategoryListAdapter;
import com.apollo.pharmacy.ocr.controller.MyOffersController;
import com.apollo.pharmacy.ocr.controller.MySearchController;
import com.apollo.pharmacy.ocr.dialog.ItemBatchSelectionDilaog;
import com.apollo.pharmacy.ocr.dialog.ProductScanDialog;
import com.apollo.pharmacy.ocr.enums.ViewMode;
import com.apollo.pharmacy.ocr.fragments.KeyboardFragment;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.interfaces.MyOffersListener;
import com.apollo.pharmacy.ocr.interfaces.SubCategoryListener;
import com.apollo.pharmacy.ocr.model.Category_request;
import com.apollo.pharmacy.ocr.model.GetProductListResponse;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.NewSearchapirequest;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.model.ProductSearch;
import com.apollo.pharmacy.ocr.model.ProductSrearchResponse;
import com.apollo.pharmacy.ocr.model.ScannedData;
import com.apollo.pharmacy.ocr.model.ScannedMedicine;
import com.apollo.pharmacy.ocr.model.Searchsuggestionrequest;
import com.apollo.pharmacy.ocr.model.SubCategoryItemModel;
import com.apollo.pharmacy.ocr.model.Suggestion_Product;
import com.apollo.pharmacy.ocr.network.ApiClient;
import com.apollo.pharmacy.ocr.network.ApiInterface;
import com.apollo.pharmacy.ocr.receiver.ConnectivityReceiver;
import com.apollo.pharmacy.ocr.utility.ApplicationConstant;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.NetworkUtils;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

public class MySearchActivity extends AppCompatActivity implements SubCategoryListener, MyOffersListener, CartCountListener,
        ConnectivityReceiver.ConnectivityReceiverListener, KeyboardFragment.OnClickKeyboard, AdapterView.OnItemSelectedListener, CategoryGridItemAdapter.CheckOutData, MedicineSearchAdapter.AddToCartCallBackData {

    Context context;
    List<OCRToDigitalMedicineResponse> dataList = new ArrayList<>();
    private ArrayList<ProductSearch> item = new ArrayList<>();
    private EditText searchAutoComplete;
    private ListView search_listview, search_suggestion_listview;
    public MedicineSearchAdapter myAdapter;
    public MyOffersController addMoreController;
    public TextView subCategoryCount;
    private RecyclerView subCategoryRecyclerView;
    private ArrayList<SubCategoryItemModel> subCategoryItem = new ArrayList<>();
    private SubCategoryListAdapter subCategoryListAdapter;
    private RecyclerView categoriesRecyclerView;
    private ProductsCustomAdapter cardAdapter;
    ArrayList<Product> subDataList;
    Map<String, GetProductListResponse> productListResponseHashMap = new HashMap<>();
    ArrayList<Product> outOfStack = new ArrayList<>();
    int totalPage = 1, currentPage = 1;
    boolean isEmpty = false;
    private String selectedProductName = "Baby Care";
    private int selectedProductID = Constants.Baby_Care;
    private RelativeLayout mySearchLayout, myCartLayout, myOrdersLayout, myOffersLayout, myProfileLayout, parentLayout;
    private ImageView dashboardSearchIcon, dashboardMyCartIcon, dashboardMyOrdersIcon, dashboardMyOffersIcon, dashboardMyProfileIcon;
    private TextView dashboardMySearch, dashboardMySearchText, dashboardMyCart, dashboardMyCartText, dashboardMyOrders, dashboardMyOrdersText,
            dashboardMyOffers, dashboardMyOffersText, dashboardMyProfile, dashboardMyProfileText, myCartCount;
    private LinearLayout searchViewLayout, searchProductsLayout, searchCancelLayout, doneProductsLayout, fcPharmaLayout, search_product_layout, subParentLayout;
    private ImageView cancel_image, clearSearchText;
    private ConstraintLayout constraintLayout;
    private KeyboardFragment keyboardFrag;
    private Handler handler = new Handler();
    private LinearLayout itemCountLayout, fcItemCountLayout, pharmaItemCountLayout, fmcgLayout, pharmaLayout, subSubParentLayout, checkOutNewBtn;
    private FrameLayout medic_keyboard;
    private TextView plusIcon, itemsCount;
    private ImageView checkOutImage;
    private TextView fmcgTxt, pharmaTxt, fcItemCountTxt, pharmaItemCountTxt;
    private ImageView searchImg;
    private LinearLayout imageLayout;
    private boolean tabFlag;
    private boolean search_auto_complete_text;
    private MySearchController mySearchController;
    private MyOffersController myOffersController;
    private EditText searchProducts;
    private ProgressBar pDialog;
    TextView transColorId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_search);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        searchProducts = findViewById(R.id.search_product_text);
        pDialog = findViewById(R.id.pdialog);
        item = new ArrayList<>();
        dataList = new ArrayList<>();
        searchAutoComplete = findViewById(R.id.search_autocomplete);
        myAdapter = new MedicineSearchAdapter(MySearchActivity.this, item, this, this);
        addMoreController = new MyOffersController(this);
        itemCountLayout = findViewById(R.id.item_count_layout);
        subCategoryCount = (TextView) findViewById(R.id.subcategory_count);
        search_listview = (ListView) findViewById(R.id.search_listview);
        search_suggestion_listview = (ListView) findViewById(R.id.search_suggestion_listview);
        searchViewLayout = findViewById(R.id.search_view_layout);
        fcPharmaLayout = findViewById(R.id.fc_phar_layout);
        searchProductsLayout = findViewById(R.id.search_products_layout);
        searchCancelLayout = findViewById(R.id.search_cancel_layout);
        doneProductsLayout = findViewById(R.id.done_btn_layout);
        cancel_image = findViewById(R.id.cancel_image);
        constraintLayout = findViewById(R.id.constraint_layout);
        medic_keyboard = findViewById(R.id.medic_keyboard);
        plusIcon = findViewById(R.id.plus_icon);
        itemsCount = findViewById(R.id.items_count);
        checkOutImage = findViewById(R.id.checkout_image);
        checkOutNewBtn = findViewById(R.id.check_out_btn);
        search_product_layout = findViewById(R.id.search_product_layout);
        subParentLayout = findViewById(R.id.sub_parent_layout);
        clearSearchText = findViewById(R.id.clear_search_text);

        doneProductsLayout.setVisibility(View.GONE);
        mySearchController = new MySearchController(this);
        myOffersController = new MyOffersController(this);

        Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        categorySpinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("FMCG");
        categories.add("Pharma");
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, R.layout.view_spinner_row, R.id.category_name, categories);
        categorySpinner.setAdapter(categoryAdapter);

        LinearLayout faqLayout = findViewById(R.id.help_layout);
        faqLayout.setOnClickListener(view -> startActivity(new Intent(MySearchActivity.this, FAQActivity.class)));

        subCategoryRecyclerView = findViewById(R.id.sub_categories_recycler_view);
        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));

        mySearchLayout = findViewById(R.id.mySearchLayout);
        myCartLayout = findViewById(R.id.myCartLayout);
        myOrdersLayout = findViewById(R.id.myOrdersLayout);
        myOffersLayout = findViewById(R.id.myOffersLayout);
        myProfileLayout = findViewById(R.id.myProfileLayout);
        parentLayout = findViewById(R.id.parent_layout);

        dashboardSearchIcon = findViewById(R.id.dashboardSearchIcon);
        dashboardMyCartIcon = findViewById(R.id.dashboardMyCartIcon);
        myCartCount = findViewById(R.id.lblCartCnt);
        dashboardMyOrdersIcon = findViewById(R.id.dashboardMyOrdersIcon);
        dashboardMyOffersIcon = findViewById(R.id.dashboardMyOffersIcon);
        dashboardMyProfileIcon = findViewById(R.id.dashboardMyProfileIcon);

        dashboardMySearch = findViewById(R.id.dashboardMySearch);
        dashboardMySearchText = findViewById(R.id.dashboardMySearchText);
        dashboardMyCart = findViewById(R.id.dashboardMyCart);
        dashboardMyCartText = findViewById(R.id.dashboardMyCartText);
        dashboardMyOrders = findViewById(R.id.dashboardMyOrders);
        dashboardMyOrdersText = findViewById(R.id.dashboardMyOrdersText);
        dashboardMyOffers = findViewById(R.id.dashboardMyOffers);
        dashboardMyOffersText = findViewById(R.id.dashboardMyOffersText);
        dashboardMyProfile = findViewById(R.id.dashboardMyProfile);
        dashboardMyProfileText = findViewById(R.id.dashboardMyProfileText);

        fmcgTxt = findViewById(R.id.FMCG_txt);
        pharmaTxt = findViewById(R.id.pharma_txt);
        fcItemCountLayout = findViewById(R.id.fc_item_count_layout);
        fmcgLayout = findViewById(R.id.fmcg_layout);
        pharmaLayout = findViewById(R.id.pharma_layout);
        fcItemCountTxt = findViewById(R.id.fc_item_count_txt);
        pharmaItemCountLayout = findViewById(R.id.pharma_item_count_layout);
        pharmaItemCountTxt = findViewById(R.id.pharma_item_count_txt);
        searchImg = findViewById(R.id.search_icon);
        imageLayout = findViewById(R.id.image_layout);
        subSubParentLayout = findViewById(R.id.sub_sub_parent_layout);

        initUi();

        searchProducts.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 2) {

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 3) {
//                    searchProductsApiCall(s.toString());
                    search_listview.setVisibility(View.GONE);
                    search_suggestion_listview.setVisibility(View.VISIBLE);
                    itemCountLayout.setVisibility(View.GONE);
                    subCategoryCount.setText("");
                    if (search_auto_complete_text) {
                        Searchsuggestionrequest request = new Searchsuggestionrequest();
                        request.setParams(searchProducts.getText().toString());
                        search_auto_complete_text = false;
                        if (NetworkUtils.isNetworkConnected(MySearchActivity.this)) {
                            pDialog.setVisibility(View.VISIBLE);
                            addMoreController.searchItemProducts(s.toString());
//                            addMoreController.searchSuggestion(request, MySearchActivity.this);
                        } else {
                            Utils.showSnackbar(MySearchActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
                        }
                    }
                } else {
                    search_auto_complete_text = true;
                    search_listview.setVisibility(View.GONE);
                    search_suggestion_listview.setVisibility(View.GONE);
                }
            }
        });
        clearSearchText.setOnClickListener(view -> {
            searchProducts.setText(null);
            if (item != null && item.size() > 0) {
                item.clear();
            }
        });

        fmcgLayout.setOnClickListener(v -> {
            tabFlag = true;
            fmcgLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            fmcgTxt.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            fmcgTxt.setTextColor(getResources().getColor(R.color.text_dark_blue_color));
            pharmaLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            pharmaTxt.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            pharmaTxt.setTextColor(getResources().getColor(R.color.colorWhite));
            imageLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            searchImg.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            searchProductsLayout.setVisibility(View.GONE);
            searchAutoComplete.setText("");
            fcPharmaLayout.setVisibility(View.VISIBLE);
            itemCountLayout.setVisibility(View.GONE);
            fcItemCountLayout.setVisibility(View.VISIBLE);
            fcItemCountTxt.setText("0");
            pharmaItemCountLayout.setVisibility(View.GONE);
            pharmaItemCountTxt.setText("0");
            setFMCGListData();
        });

        pharmaLayout.setOnClickListener(v -> {
            tabFlag = false;
            fmcgLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            fmcgTxt.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            fmcgTxt.setTextColor(getResources().getColor(R.color.colorWhite));
            pharmaLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            pharmaTxt.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            pharmaTxt.setTextColor(getResources().getColor(R.color.text_dark_blue_color));
            imageLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            searchImg.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            searchProductsLayout.setVisibility(View.GONE);
            searchAutoComplete.setText("");
            fcPharmaLayout.setVisibility(View.VISIBLE);
            itemCountLayout.setVisibility(View.GONE);
            fcItemCountLayout.setVisibility(View.GONE);
            fcItemCountTxt.setText("0");
            pharmaItemCountLayout.setVisibility(View.VISIBLE);
            pharmaItemCountTxt.setText("0");
            setPharmacyListData();
        });

        imageLayout.setOnClickListener(v -> {
            fmcgLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            fmcgTxt.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            fmcgTxt.setTextColor(getResources().getColor(R.color.colorWhite));
            pharmaLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            pharmaTxt.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
            pharmaTxt.setTextColor(getResources().getColor(R.color.colorWhite));
            imageLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            searchImg.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            searchProductsLayout.setVisibility(View.VISIBLE);
            searchAutoComplete.setText("");
            itemCountLayout.setVisibility(View.GONE);
            fcPharmaLayout.setVisibility(View.GONE);
            fcItemCountLayout.setVisibility(View.GONE);
            fcItemCountTxt.setText("0");
            pharmaItemCountLayout.setVisibility(View.GONE);
            pharmaItemCountTxt.setText("0");
        });

        search_listview.setVisibility(View.GONE);

        searchViewLayout.setOnClickListener(v -> {
            searchProductsLayout.setVisibility(View.VISIBLE);
            searchAutoComplete.setText("");
            searchViewLayout.setVisibility(View.GONE);
        });

        searchCancelLayout.setOnClickListener(v -> {
            search_listview.setVisibility(View.GONE);
            search_suggestion_listview.setVisibility(View.GONE);
            searchProductsLayout.setVisibility(View.GONE);
            fcPharmaLayout.setVisibility(View.VISIBLE);
            itemCountLayout.setVisibility(View.GONE);
            medic_keyboard.setVisibility(View.GONE);
            if (tabFlag) {
                setFMCGFirst();
            } else {
                setPharmaFirst();
            }
            handleCategoryListService(this);
        });
        cancel_image.setOnClickListener(v -> {
            searchAutoComplete.setText("");
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(MySearchActivity.this, LinearLayoutManager.VERTICAL, false);
        subCategoryRecyclerView.setLayoutManager(layoutManager);
        subCategoryListAdapter = new SubCategoryListAdapter(MySearchActivity.this, subCategoryItem, MySearchActivity.this);
        subCategoryRecyclerView.setAdapter(subCategoryListAdapter);

// uncomment line if you need to change to fmcg, pharma and search
//        handleCategoryListService(this);

        keyboardFrag = KeyboardFragment.newInstance(MySearchActivity.this, this);
        searchAutoComplete.setText("");
        searchAutoComplete.setInputType(InputType.TYPE_CLASS_TEXT);
        searchAutoComplete.setRawInputType(InputType.TYPE_CLASS_TEXT);

        searchProducts.setText("");
        searchProducts.setInputType(InputType.TYPE_CLASS_TEXT);
        searchProducts.setRawInputType(InputType.TYPE_CLASS_TEXT);

        hideSystemKeyBoard();


        checkOutImage.setOnClickListener(v -> {
            Intent intent1 = new Intent(MySearchActivity.this, MyCartActivity.class);
            intent1.putExtra("activityname", "AddMoreActivity");
            startActivity(intent1);
            finish();
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
        });

        checkOutNewBtn.setOnClickListener(v -> {
            Intent intent1 = new Intent(MySearchActivity.this, MyCartActivity.class);
            intent1.putExtra("activityname", "AddMoreActivity");
            startActivity(intent1);
            finish();
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
        });

        doneProductsLayout.setOnClickListener(v -> {
            finish();
            Intent intent1 = new Intent(MySearchActivity.this, MyCartActivity.class);
            intent1.putExtra("activityname", "AddMoreActivity");
            startActivity(intent1);
            overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
        });

        if (null != SessionManager.INSTANCE.getDataList()) {
            doneProductsLayout.setVisibility(View.VISIBLE);
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

        search_product_layout.setOnClickListener(v -> {
            search_listview.setVisibility(View.VISIBLE);
            search_suggestion_listview.setVisibility(View.GONE);

            NewSearchapirequest request = new NewSearchapirequest();
            request.setParams(searchAutoComplete.getText().toString());
            search_auto_complete_text = true;
            if (NetworkUtils.isNetworkConnected(this)) {
                addMoreController.newSearchProduct(request, MySearchActivity.this);
            } else {
                Utils.showSnackbar(this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
            }
        });

        search_auto_complete_text = true;

        searchAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if (searchAutoComplete.getText().toString().length() >= 3) {
                    search_listview.setVisibility(View.GONE);
                    search_suggestion_listview.setVisibility(View.VISIBLE);
                    itemCountLayout.setVisibility(View.GONE);
                    subCategoryCount.setText("");
                    if (search_auto_complete_text) {
                        Searchsuggestionrequest request = new Searchsuggestionrequest();
                        request.setParams(searchAutoComplete.getText().toString());
                        search_auto_complete_text = false;
                        if (NetworkUtils.isNetworkConnected(MySearchActivity.this)) {
                            addMoreController.searchSuggestion(request, MySearchActivity.this);
                        } else {
                            Utils.showSnackbar(MySearchActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_internet_error_text));
                        }
                    }
                } else {
                    search_auto_complete_text = true;
                    search_listview.setVisibility(View.GONE);
                    search_suggestion_listview.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        //uncomment line if you need to change to fmcg, pharma and search
//        setFMCGFirst();

        if (SessionManager.INSTANCE.getDataList() != null) {
            if (SessionManager.INSTANCE.getDataList().size() > 0) {
                cartCount(SessionManager.INSTANCE.getDataList().size());
            }
        }
        search_listview.setAdapter(myAdapter);
        search_suggestion_listview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("cardReceiver"));
        Constants.getInstance().setConnectivityListener(this);

        SessionManager.INSTANCE.setCurrentPage(ApplicationConstant.ACTIVITY_ADDMORE);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverAddmore,
                new IntentFilter("MedicineReciverAddMore"));
        RelativeLayout scanProducts = findViewById(R.id.scan_products);
        transColorId = findViewById(R.id.trans_color_id);
        scanProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                transColorId.setVisibility(View.VISIBLE);
                ProductScanDialog productScanDialog = new ProductScanDialog(MySearchActivity.this);
                productScanDialog.setPositiveListener(view -> {
                    productScanDialog.dismiss();
                    ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(MySearchActivity.this,null);
                    ProductSearch medicine = new ProductSearch();
                    medicine.setSku("APC0005");
                    medicine.setQty(1);
                    medicine.setName("Static");
                    medicine.setPrice("6");
                    medicine.setMou("");

                    itemBatchSelectionDilaog.setUnitIncreaseListener(view3 -> {
                        medicine.setQty(medicine.getQty() + 1);
                        itemBatchSelectionDilaog.setQtyCount("" + medicine.getQty());
                    });
                    itemBatchSelectionDilaog.setUnitDecreaseListener(view4 -> {
                        if (medicine.getQty() > 1) {
                            medicine.setQty(medicine.getQty() - 1);
                            itemBatchSelectionDilaog.setQtyCount("" + medicine.getQty());
                        }
                    });
                    itemBatchSelectionDilaog.setPositiveListener(view2 -> {
                        transColorId.setVisibility(View.GONE);

                        Intent intent = new Intent("cardReceiver");
                        intent.putExtra("message", "Addtocart");
                        intent.putExtra("product_sku", medicine.getSku());
                        intent.putExtra("product_name", medicine.getName());
                        intent.putExtra("product_quantyty", itemBatchSelectionDilaog.getQtyCount().toString());
                        intent.putExtra("product_price", String.valueOf(medicine.getPrice()));
                        // intent.putExtra("product_container", product_container);
                        intent.putExtra("product_mou", String.valueOf(medicine.getMou()));
                        intent.putExtra("product_position", String.valueOf(0));
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        addToCartCallBack();
                        itemBatchSelectionDilaog.dismiss();

                    });
                    itemBatchSelectionDilaog.setNegativeListener(v -> {
                        transColorId.setVisibility(View.GONE);
                        itemBatchSelectionDilaog.dismiss();
                    });
                    itemBatchSelectionDilaog.show();
                });
                productScanDialog.setNegativeListener(v -> {
                    transColorId.setVisibility(View.GONE);
                    productScanDialog.dismiss();
                });
                productScanDialog.show();
            }
        });
        hideKeyboardEmptyPlaceClick();
    }

    private void setPharmaFirst() {
        tabFlag = false;
        fmcgLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        fmcgTxt.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        fmcgTxt.setTextColor(getResources().getColor(R.color.colorWhite));
        pharmaLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        pharmaTxt.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        pharmaTxt.setTextColor(getResources().getColor(R.color.text_dark_blue_color));
        imageLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        searchImg.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        searchProductsLayout.setVisibility(View.GONE);
        searchAutoComplete.setText("");
        fcPharmaLayout.setVisibility(View.VISIBLE);
        itemCountLayout.setVisibility(View.GONE);
        fcItemCountLayout.setVisibility(View.GONE);
        fcItemCountTxt.setText("0");
        pharmaItemCountLayout.setVisibility(View.VISIBLE);
        pharmaItemCountTxt.setText("0");
        setPharmacyListData();
    }

    private void setFMCGFirst() {
        tabFlag = true;
        fmcgLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        fmcgTxt.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        fmcgTxt.setTextColor(getResources().getColor(R.color.text_dark_blue_color));
        pharmaLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        pharmaTxt.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        pharmaTxt.setTextColor(getResources().getColor(R.color.colorWhite));
        imageLayout.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        searchImg.setBackgroundColor(getResources().getColor(R.color.text_dark_blue_color));
        itemCountLayout.setVisibility(View.GONE);
        fcItemCountLayout.setVisibility(View.VISIBLE);
        fcItemCountTxt.setText("0");
        pharmaItemCountLayout.setVisibility(View.GONE);
        pharmaItemCountTxt.setText("0");
        setFMCGListData();
    }

    private void hideSystemKeyBoard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        searchAutoComplete.setOnTouchListener(touchListenerEdt);
        searchProducts.setOnTouchListener(touchListenerEdt);
    }

    View.OnTouchListener touchListenerEdt = (v, event) -> {
        medic_keyboard.setVisibility(View.VISIBLE);
        initKeyBoard();
        showKeyBoard((EditText) v);
        v.onTouchEvent(event);
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return true;
    };

    private void initKeyBoard() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.medic_keyboard, keyboardFrag, "KEYBOARD");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showKeyBoard(final EditText et) {
        if (et.getInputType() == InputType.TYPE_CLASS_NUMBER) {
            keyboardFrag.inputFilter = keyboardFrag.filterArray[1];
        } else {
            keyboardFrag.inputFilter = keyboardFrag.filterArray[2];
        }
        keyboardFrag.editTextFocus = et;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                keyboardFrag.cursorPos = et.getSelectionStart();
                keyboardFrag.str = "";
            }
        }, 100);
    }

    @Override
    public void getKeyboardText(EditText editText, String str, int curPostion) {
        editText.setText("" + str);
        if (str.length() > 0 && str.length() >= curPostion)
            editText.setSelection(curPostion);
    }

    @Override
    public void onEnter(EditText editText, String str) {
        editText.setText(str);
        hideKeyBoard();
    }

    private void hideKeyBoard() {
        if (keyboardFrag != null) {
            medic_keyboard.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("KEYBOARD")).commit();
        }
    }

    @Override
    public void onHideKeyboard() {
        hideKeyBoard();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MySearchActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        LocalBroadcastManager.getInstance(this).registerReceiver(mCartMessageReceiver,
                new IntentFilter("UpdateCartItemCount"));

        Constants.getInstance().setConnectivityListener(this);
        if (!ConnectivityReceiver.isConnected()) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCartMessageReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiverAddmore);
        super.onPause();
        Constants.getInstance().setConnectivityListener(null);
    }

    @Override
    public void onViewClick(int position) {
        SubCategoryItemModel dataItem = subCategoryItem.get(position);
        selectedProductName = dataItem.getSubCategoryName();
        selectedProductID = dataItem.getSubCategoryId();
        for (int i = 0; i < subCategoryItem.size(); i++) {
            subCategoryItem.get(i).setSelected(false);
        }
        for (int i = 0; i < subCategoryItem.size(); i++) {
            if (dataItem.getSubCategoryName().equalsIgnoreCase(subCategoryItem.get(i).getSubCategoryName())) {
                subCategoryItem.get(i).setSelected(true);
            }
        }
        subCategoryListAdapter.notifyDataSetChanged();
        handleCategoryListService(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            findViewById(R.id.networkErrorLayout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.networkErrorLayout).setVisibility(View.VISIBLE);
        }
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
                Utils.showSnackbar(MySearchActivity.this, constraintLayout, getApplicationContext().getResources().getString(R.string.label_item_added_cart));
                cartCount(dataList.size());
            }
        }
    };

    private BroadcastReceiver mCartMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.equalsIgnoreCase("CartCount")) {
                doneProductsLayout.setVisibility(View.VISIBLE);
            }
        }
    };

    public void medConvertDialog(ScannedData fcmMedicine) {
        final Dialog dialog = new Dialog(MySearchActivity.this);
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
                Utils.showSnackbar(MySearchActivity.this, constraintLayout, getResources().getString(R.string.label_medicine_added_success));
                dialog.dismiss();
            }
        });
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
    public void onSearchSuggestionList(List<Suggestion_Product> m) {
        pDialog.setVisibility(View.GONE);
        if (item.size() > 0) {
            item.clear();
        }
        if (m != null) {
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
                // product.setSpecialPrice(r.getSpecialPrice());
                product.setStatus(r.getStatus());
                product.setThumbnail(r.getThumbnail());
                product.setTypeId(r.getTypeId());
                product.setMou(r.getMou());
                item.add(product);
            }
        }
        search_auto_complete_text = true;
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
            // product.setSpecialPrice(r.getSpecialPrice());
            product.setStatus(r.getStatus());
            product.setThumbnail(r.getThumbnail());
            product.setTypeId(r.getTypeId());
            product.setMou(r.getMou());
            item.add(product);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessLoadMoreTrendingNow(GetProductListResponse response) {
        subDataList.remove(subDataList.size() - 1);
        if (null != response.getProducts() && response.getProducts().size() > 0) {
            ArrayList<Product> temp = (ArrayList<Product>) response.getProducts();
            for (Product p : temp) {
                if (p.getIsInStock() == 1) {
                    subDataList.add(p);
                } else {
                    outOfStack.add(p);
                }
            }
            if (totalPage == currentPage) {
                if (isEmpty) {
                    subDataList.remove(0);
                    cardAdapter.notifyItemRemoved(0);
                    isEmpty = false;
                }
                subDataList.addAll(outOfStack);
                cardAdapter.setMoreDataAvailable(false);
                for (Map.Entry<String, GetProductListResponse> obj : productListResponseHashMap.entrySet()) {
                    SessionManager.INSTANCE.setProductListSaved(obj.getKey());
                    SessionManager.INSTANCE.setCategoryProductList(subDataList, obj.getKey());
                }
            }
        }
        subCategoryCount.setText(String.valueOf(subDataList.size()));
        fcItemCountTxt.setText(String.valueOf(subDataList.size()));
        pharmaItemCountTxt.setText(String.valueOf(subDataList.size()));
        cardAdapter.notifyDataChanged();
    }

    @Override
    public void onFailureLoadMoreTrendingNow(String message) {
        search_auto_complete_text = true;
//        Utils.showCustomAlertDialog(MySearchActivity.this, message, false, getResources().getString(R.string.label_ok), "");
    }

    @Override
    public void onSuccessLoadMorePromotions(GetProductListResponse body) {

    }

    @Override
    public void onFailureLoadMorePromotions(String message) {

    }

    @Override
    public void onSuccessSearchItemApi(ItemSearchResponse m) {
        pDialog.setVisibility(View.GONE);
        if (item.size() > 0) {
            item.clear();
        }
        if (m != null) {
            for (ItemSearchResponse.Item r : m.getItemList()) {
                ProductSearch product = new ProductSearch();
                product.setName(r.getGenericName());
                product.setSku(r.getArtCode());
                product.setQty(1);
                product.setDescription(r.getDescription());
                product.setMedicineType(r.getCategory());
//                product.setId(r.getId());
//                product.setImage(r.getImage());
                product.setIsInStock(r.getStockqty() != 0 ? 1 : 0);
                product.setIsPrescriptionRequired(0);
                product.setPrice(r.getMrp());
//                product.setSmallImage(r.getSmallImage());
                // product.setSpecialPrice(r.getSpecialPrice());
                /* product.setStatus(r.getStatus());
                 * product.setThumbnail(r.getThumbnail());
                 *product.setTypeId(r.getTypeId());
                 *product.setMou(r.getMou());      */
                item.add(product);
            }
        }
//        search_auto_complete_text = true;
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchFailure(String error) {
        pDialog.setVisibility(View.GONE);
        search_auto_complete_text = true;
        Utils.showCustomAlertDialog(MySearchActivity.this, error, false, "OK", "");
    }

    @Override
    public void onSuccessProductList(HashMap<String, GetProductListResponse> productList) {

    }

    @Override
    public void onSuccessProductListMainCategory(HashMap<String, GetProductListResponse> productList) {
        productListResponseHashMap = productList;
        subDataList = (ArrayList<Product>) productListResponseHashMap.get(selectedProductName).getProducts();
        setupView();
    }

    @Override
    public void onFailure(String error) {
        Utils.showCustomAlertDialog(MySearchActivity.this, error, false, getResources().getString(R.string.label_ok), "");
    }

    private void handleCategoryListService(MyOffersListener myOffersListener) {
        List<Observable<GetProductListResponse>> observableList = new ArrayList<>();
        Utils.showDialog(MySearchActivity.this, getApplicationContext().getResources().getString(R.string.label_please_wait));
        ApiInterface restConnection = ApiClient.createService(ApiInterface.class, Constants.Get_Special_Offers_Products);
        observableList.addAll(setupProductNetworkMiancategory(restConnection, Constants.MainCategoryHealthWellness, selectedProductID));
        Observable<HashMap<String, GetProductListResponse>> combined = Observable.zip(observableList, new FuncN<HashMap<String, GetProductListResponse>>() {
            @Override
            public HashMap<String, GetProductListResponse> call(Object... args) {
                HashMap<String, GetProductListResponse> data = new HashMap<>();
                data.putAll(prepareToListViewMainlist(selectedProductName, args));
                return data;
            }
        });

        combined.subscribe(new Subscriber<HashMap<String, GetProductListResponse>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Utils.dismissDialog();
                myOffersListener.onFailure(e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(HashMap<String, GetProductListResponse> o) {
                Utils.dismissDialog();
                myOffersListener.onSuccessProductListMainCategory(o);
            }
        });
    }

    public List<Observable<GetProductListResponse>> setupProductNetworkMiancategory(ApiInterface apiInterface, String mainCategory, int productId) {
        List<Observable<GetProductListResponse>> list = new ArrayList<>();

        Category_request request = new Category_request();
        request.setCategoryId(productId);
        request.setPageId(1);
        list.add(apiInterface.getProductListByCategId(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()));
        return list;
    }

    public HashMap<String, GetProductListResponse> prepareToListViewMainlist(String categName, Object[] args) {
        HashMap<String, GetProductListResponse> data = new HashMap<>();
        if ((null != args && args.length > 0) && (!categName.isEmpty())) {
            data.put(categName, (GetProductListResponse) args[0]);
        }
        return data;
    }

    private void setupView() {
        totalPage = productListResponseHashMap.get(selectedProductName).getProductCount() / 20;
        if (totalPage % 2 != 0) {
            totalPage++;
        }
        if (!SessionManager.INSTANCE.getProductListSaved(selectedProductName)) {
            ArrayList<Product> stack = new ArrayList<>();
            for (Product p : subDataList) {
                if (p.getIsInStock() == 1) {
                    stack.add(p);
                } else {
                    outOfStack.add(p);
                }
            }
            subDataList.clear();
            subDataList.addAll(stack);
        }
        subCategoryCount.setText(String.valueOf(subDataList.size()));
        fcItemCountTxt.setText(String.valueOf(subDataList.size()));
        pharmaItemCountTxt.setText(String.valueOf(subDataList.size()));
        if (totalPage > currentPage) {
            subDataList.remove(0);
        }
        cardAdapter = new ProductsCustomAdapter(MySearchActivity.this, subDataList, this, this);
        cardAdapter.setViewMode(ViewMode.GRID);
        categoriesRecyclerView.setAdapter(cardAdapter);
        cardAdapter.notifyDataSetChanged();

        cardAdapter.setLoadMoreListener(() -> categoriesRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                currentPage++;
                if (totalPage >= currentPage) {
                    loadMore();
                }
            }
        }));
    }

    private void loadMore() {
        if (currentPage > 3) {
            subDataList.add(new Product("load"));
            cardAdapter.notifyItemInserted(subDataList.size() - 1);
        }
        myOffersController.handleLoadMoreTrendingNowService(selectedProductID, currentPage);
    }

    private void initUi() {
        mySearchLayout.setBackgroundResource(R.color.selected_menu_color);
        dashboardSearchIcon.setImageResource(R.drawable.dashboard_search_hover);
        dashboardMySearch.setTextColor(getResources().getColor(R.color.selected_text_color));
        dashboardMySearchText.setTextColor(getResources().getColor(R.color.selected_text_color));
        mySearchLayout.setClickable(false);

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

        ImageView userLogout = findViewById(R.id.userLogout);
        userLogout.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(MySearchActivity.this);
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

                Intent intent = new Intent(MySearchActivity.this, MainActivity.class);
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

            Intent intent1 = new Intent(MySearchActivity.this, MyCartActivity.class);
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

            Intent intent = new Intent(MySearchActivity.this, MyOffersActivity.class);
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

            Intent intent1 = new Intent(MySearchActivity.this, MyProfileActivity.class);
            startActivity(intent1);
            finish();
            overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setFMCGListData() {
        mySearchController.setFMCGListData(subCategoryItem);
        subCategoryListAdapter.notifyDataSetChanged();
        selectedProductName = "Baby Care";
        selectedProductID = Constants.Baby_Care;
        handleCategoryListService(this);
    }

    public void setPharmacyListData() {
        mySearchController.setPharmacyListData(subCategoryItem);
        subCategoryListAdapter.notifyDataSetChanged();
        selectedProductName = "Anti allergic drugs";
        selectedProductID = Constants.Anti_allergic_drugs;
        handleCategoryListService(this);
    }

    @Override
    public void cartCount(int count) {
        if (count != 0) {
            myCartCount.setVisibility(View.VISIBLE);
            myCartCount.setText(String.valueOf(count));
//            itemsCount.setVisibility(View.VISIBLE);
//            plusIcon.setVisibility(View.VISIBLE);
//            checkOutImage.setVisibility(View.VISIBLE);
            checkOutImage.setImageResource(R.drawable.checkout_cart);
            itemsCount.setText(count + " " + getResources().getString(R.string.label_items) + " " + getResources().getString(R.string.label_added));
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
    public void checkoutData() {
        Intent intent1 = new Intent(MySearchActivity.this, MyCartActivity.class);
        intent1.putExtra("activityname", "AddMoreActivity");
        startActivity(intent1);
        finish();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    private void searchProductsApiCall(String searchKey) {
        if (isNetworkConnected()) {
            ApiInterface apiInterface = ApiClient.getApiService("");
            Map<String, Object> productRequest = new HashMap<>();
            productRequest.put("params", searchKey);
            Call<List<ProductSrearchResponse>> call = apiInterface.productSearch("Bearer p8g0s5tcwz1qnqibpszco93rp36ec7mk", productRequest);
            call.enqueue(new retrofit2.Callback<List<ProductSrearchResponse>>() {

                @Override
                public void onResponse(Call<List<ProductSrearchResponse>> call, Response<List<ProductSrearchResponse>> response) {
                    if (response.body() != null && response.isSuccessful() && response.body().get(0).getStatus().equalsIgnoreCase("1")) {
                        onSuccessProductApi(response.body());
                        //                        onSuccessApi(response.body());
//                        InputMethodManager inputManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
//                        inputManager.hideSoftInputFromWindow(getDialog().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } else {
//                        onFailureSearchApi();
//                        InputMethodManager inputManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
//                        inputManager.hideSoftInputFromWindow(getDialog().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    Log.e("TAG", response.code() + "");
                }

                @Override
                public void onFailure(Call<List<ProductSrearchResponse>> call, Throwable t) {
                    Log.e("Service", "Failed res :: " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void onSuccessProductApi(List<ProductSrearchResponse> productSrearchResponse) {

    }

    private void onSuccessProductApi() {

    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void addToCartCallBack() {
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

        Intent intent1 = new Intent(MySearchActivity.this, MyCartActivity.class);
        intent1.putExtra("activityname", "AddMoreActivity");
        startActivity(intent1);
        finish();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void hideKeyboardEmptyPlaceClick() {
        constraintLayout.setOnTouchListener((view, motionEvent) -> {
            hideKeyBoard();
            return false;
        });
        parentLayout.setOnTouchListener((view, motionEvent) -> {
            hideKeyBoard();
            return false;
        });
        subParentLayout.setOnTouchListener((view, motionEvent) -> {
            hideKeyBoard();
            return false;
        });
        subSubParentLayout.setOnTouchListener((view, motionEvent) -> {
            hideKeyBoard();
            return false;
        });
    }
}