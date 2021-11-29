package com.apollo.pharmacy.ocr.activities.mposstoresetup.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.mposstoresetup.StoreSetupMvpView;
import com.apollo.pharmacy.ocr.activities.mposstoresetup.model.StoreListResponseModel;
import com.apollo.pharmacy.ocr.databinding.MposDialogStoreListBinding;

import java.util.ArrayList;

public class MposGetStoresDialog implements GetStoresDialogMvpView {
    private Dialog dialog;
    private Context context;
    private ArrayList<StoreListResponseModel.StoreListObj> storesArrList = new ArrayList<>();
    private StoreSetupMvpView storeSetupMvpView;
    private MposDialogStoreListBinding mposDialogStoreListBinding;


    public MposGetStoresDialog(Context context) {
        dialog = new Dialog(context);
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        mposDialogStoreListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.mpos_dialog_store_list, null, false);
        dialog.setContentView(mposDialogStoreListBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

    }

    protected void setUp() {
        mposDialogStoreListBinding.setCallback(this);

        MposGetStoresListAdapter storesListAdapter = new MposGetStoresListAdapter(context, storesArrList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        mposDialogStoreListBinding.storesRecyclerView.setLayoutManager(mLayoutManager);
        storesListAdapter.onClickListener(this);
        mposDialogStoreListBinding.storesRecyclerView.setAdapter(storesListAdapter);
        mposDialogStoreListBinding.doctorNameSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                storesListAdapter.getFilter().filter(s);
            }
        });
    }

    public void setStoreListArray(ArrayList<StoreListResponseModel.StoreListObj> storesArrList) {
        this.storesArrList = storesArrList;
        setUp();
    }

    public void setStoreDetailsMvpView(StoreSetupMvpView detailsMvpView) {
        this.storeSetupMvpView = detailsMvpView;
    }


    @Override
    public void dismissDialog() {
        dialog.dismiss();
        storeSetupMvpView.dialogCloseListiner();
    }

    @Override
    public void onClickListener(StoreListResponseModel.StoreListObj item) {
        storeSetupMvpView.onSelectStore(item);
        dialog.dismiss();
        storeSetupMvpView.dialogCloseListiner();
    }

    public void show() {

        dialog.show();
    }

}
