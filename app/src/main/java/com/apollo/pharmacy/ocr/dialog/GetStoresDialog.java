package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.GetStoresListAdapter;
import com.apollo.pharmacy.ocr.interfaces.StoreSetupListener;
import com.apollo.pharmacy.ocr.model.GetStoreInfoResponse;

import java.util.ArrayList;

public class GetStoresDialog extends Dialog {
    private final ArrayList<GetStoreInfoResponse.DeviceDetailsEntity> storesArrList;
    private final Context context;
    private final StoreSetupListener listener;

    public GetStoresDialog(@NonNull Context context, ArrayList<GetStoreInfoResponse.DeviceDetailsEntity> storesArrList, StoreSetupListener listener) {
        super(context);
        this.context = context;
        this.storesArrList = storesArrList;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_store_list);
        setCanceledOnTouchOutside(false);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        EditText storeSearchEditText = findViewById(R.id.store_name_search);
        ImageView closeDialog = findViewById(R.id.close_dialog);
        RecyclerView storesRecyclerView = findViewById(R.id.stores_recyclerView);
        GetStoresListAdapter storesListAdapter = new GetStoresListAdapter(storesArrList, listener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        storesRecyclerView.setLayoutManager(mLayoutManager);
        storesRecyclerView.setAdapter(storesListAdapter);
        storeSearchEditText.addTextChangedListener(new TextWatcher() {
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
        closeDialog.setOnClickListener(v -> {
            dismiss();
        });
    }
}