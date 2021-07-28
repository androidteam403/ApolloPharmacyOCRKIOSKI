package com.apollo.pharmacy.ocr.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.PrescriptionViewListAdapter;
import com.apollo.pharmacy.ocr.interfaces.CheckPrescriptionListener;
import com.apollo.pharmacy.ocr.model.ScannedMedicine;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ScannedPrescriptionViewDialog extends Dialog implements CheckPrescriptionListener {
    private final Activity activity;
    private PrescriptionViewListAdapter prescriptionListAdapter;
    private final List<ScannedMedicine> selectedMedicineList = new ArrayList<>();
    private final List<ScannedMedicine> dataList;

    public ScannedPrescriptionViewDialog(Activity activity, List<ScannedMedicine> dataList) {
        super(activity);
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_prescription_view);
        setCanceledOnTouchOutside(false);

        TextView curation_process_text = findViewById(R.id.curation_process_text);
        ImageView prescriptionImage = findViewById(R.id.prescription_image);
        ImageView closeImage = findViewById(R.id.close_image);
        RecyclerView prescRecyclerView = findViewById(R.id.recycle_view_prescription);

        Glide.with(activity)
                .load(SessionManager.INSTANCE.getScannedImagePath())
                .apply(new RequestOptions().placeholder(R.drawable.thumbnail_image).error(R.drawable.thumbnail_image))
                .into(prescriptionImage);

        closeImage.setOnClickListener(view -> {
            dismiss();
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        prescRecyclerView.setLayoutManager(layoutManager);

        if (SessionManager.INSTANCE.getCurationStatus()) {
            curation_process_text.setVisibility(View.VISIBLE);
            prescRecyclerView.setVisibility(View.GONE);
        } else {
            curation_process_text.setVisibility(View.GONE);
            prescRecyclerView.setVisibility(View.VISIBLE);

            prescriptionListAdapter = new PrescriptionViewListAdapter(activity, dataList, this);
            prescRecyclerView.setAdapter(prescriptionListAdapter);
            prescriptionListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onCount(int count, boolean flag) {

    }

    @Override
    public void onPrescriptionClick(int position, boolean flag) {
        if (dataList != null && dataList.size() > 0) {
            ScannedMedicine item = dataList.get(position);
            if (flag) {
                selectedMedicineList.add(item);
                item.setSelectionFlag(flag);
            } else {
                item.setSelectionFlag(flag);
                for (int i = 0; i < selectedMedicineList.size(); i++) {
                    if (selectedMedicineList.get(i).getArtCode().equalsIgnoreCase(item.getArtCode())) {
                        selectedMedicineList.remove(item);
                    }
                }
            }
        }
        prescriptionListAdapter.notifyDataSetChanged();
    }
}
