package com.apollo.pharmacy.ocr.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.StoreLocatorAdapter;
import com.apollo.pharmacy.ocr.model.PharmacyLocatorCustomList;

import java.util.ArrayList;
import java.util.List;

public class StoreLocatorDialog extends DialogFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<PharmacyLocatorCustomList> location = new ArrayList<>();
    RecyclerView recyclerView;
    Button cancel;
    StoreLocatorAdapter adapter;

    public StoreLocatorDialog() {
    }

    public static StoreLocatorDialog newInstance(String param1, String param2) {
        StoreLocatorDialog fragment = new StoreLocatorDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogFragment newInstance(ArrayList<PharmacyLocatorCustomList> contacts) {
        StoreLocatorDialog fragment = new StoreLocatorDialog();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, contacts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            location = (List<PharmacyLocatorCustomList>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_store_locator, container);
        cancel = rootView.findViewById(R.id.cancelDialog);
        cancel.setOnClickListener(v -> dismiss());
        recyclerView = rootView.findViewById(R.id.storeLocatorRecyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new StoreLocatorAdapter(this.getActivity(), location);
        recyclerView.setAdapter(adapter);
        this.getDialog().setTitle("Select Store....");
        return rootView;
    }
}
