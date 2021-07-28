package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.CheckPrescriptionListener;
import com.apollo.pharmacy.ocr.model.ScannedMedicine;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PrescriptionViewListAdapter extends RecyclerView.Adapter<PrescriptionViewListAdapter.MyViewHolder> {
    private final List<ScannedMedicine> pastPrescriptionResponse;
    private final Context activity;
    private final CheckPrescriptionListener listener;

    public PrescriptionViewListAdapter(Context activity, List<ScannedMedicine> pastPrescriptionResponse, CheckPrescriptionListener listener) {
        this.activity = activity;
        this.pastPrescriptionResponse = pastPrescriptionResponse;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productname_textview, product_textview, totalpricetextview, offerpricetextview;
        LinearLayout parentLayout;

        public MyViewHolder(View view) {
            super(view);
            parentLayout = view.findViewById(R.id.parent_layout);
            productname_textview = view.findViewById(R.id.productname_textview);
            offerpricetextview = view.findViewById(R.id.offerpricetextview);
            totalpricetextview = view.findViewById(R.id.totalpricetextview);
            product_textview = view.findViewById(R.id.quantity_txt);
        }
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_prescription_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ScannedMedicine scannedMedicine = pastPrescriptionResponse.get(position);
        holder.productname_textview.setText(scannedMedicine.getArtName());
        String rupeeSymbol = "\u20B9";
        if (!TextUtils.isEmpty(scannedMedicine.getArtprice())) {
            holder.offerpricetextview.setText(rupeeSymbol + "" + String.format("%.2f", Float.parseFloat(scannedMedicine.getArtprice())));
        } else {
            holder.offerpricetextview.setText(rupeeSymbol + "" + String.format("%.2f", Float.parseFloat("00")));
        }

        double total_price = 0.0;
        if (!TextUtils.isEmpty(scannedMedicine.getArtprice())) {
            total_price = scannedMedicine.getQty() * Float.parseFloat(scannedMedicine.getArtprice());
        } else {
            total_price = scannedMedicine.getQty() * Float.parseFloat("00");
        }
        holder.totalpricetextview.setText(rupeeSymbol + "" + String.format("%.2f", total_price));
        holder.product_textview.setText(String.valueOf(scannedMedicine.getQty()));

        if (position % 2 == 0) {
            holder.parentLayout.setBackgroundColor(activity.getResources().getColor(R.color.dialog_dark_blue));
        } else {
            holder.parentLayout.setBackgroundColor(activity.getResources().getColor(R.color.dialog_adapter_dark_blue));
        }

        holder.itemView.setOnClickListener(v -> {
            listener.onPrescriptionClick(position, !pastPrescriptionResponse.get(position).getSelectionFlag());
        });
    }

    @Override
    public int getItemCount() {
        return pastPrescriptionResponse.size();
    }
}
