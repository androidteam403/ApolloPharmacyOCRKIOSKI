package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.PrescriptionPreviewListener;
import com.apollo.pharmacy.ocr.model.PrescriptionItem;
import com.apollo.pharmacy.ocr.utility.PhotoPopupWindow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionListAdapter.MyViewHolder> {
    private List<PrescriptionItem> prescriptionList;
    private Context activity;
    private PrescriptionPreviewListener listener;

    public PrescriptionListAdapter(Context activity, List<PrescriptionItem> prescriptionList, PrescriptionPreviewListener listener) {
        this.activity = activity;
        this.prescriptionList = prescriptionList;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView prescriptionClose;
        ImageView prescriptionImage;
        ImageView prescriptionView;

        public MyViewHolder(View view) {
            super(view);
            prescriptionClose = view.findViewById(R.id.close_prescription);
            prescriptionImage = view.findViewById(R.id.prescription_image);
            prescriptionView = view.findViewById(R.id.prescription_view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_scanned_prescription, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        PrescriptionItem prescriptionItem = prescriptionList.get(position);

        Glide.with(activity)
                .load(prescriptionItem.getImagePath())
                .apply(new RequestOptions().placeholder(R.drawable.thumbnail_image).error(R.drawable.thumbnail_image))
                .into(holder.prescriptionImage);
        holder.prescriptionClose.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClosePrescriptionClick(position);
            }
        });
        holder.prescriptionView.setOnClickListener(v -> {
            if (listener != null) {
                new PhotoPopupWindow(activity, R.layout.layout_image_fullview, v, prescriptionItem.getImagePath(), null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }
}
