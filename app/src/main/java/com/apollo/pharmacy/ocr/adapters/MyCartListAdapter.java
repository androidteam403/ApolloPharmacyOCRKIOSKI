package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.OnItemClickListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyCartListAdapter extends RecyclerView.Adapter<MyCartListAdapter.ViewHolder> {
    private List<OCRToDigitalMedicineResponse> cartMedicineList = new ArrayList<>();
    private final OnItemClickListener listener;

    public MyCartListAdapter(List<OCRToDigitalMedicineResponse> cartMedicineList, OnItemClickListener listener) {
        this.cartMedicineList = cartMedicineList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyCartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_my_cart_list, parent, false);
        return new MyCartListAdapter.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView snoTxt, productNameTxt, offerPriceTxt, productQty, totalPriceTxt, mrppricetextview;
        ImageView decButton, incButton;
        ImageView deleteButton;

        public ViewHolder(View view) {
            super(view);
            snoTxt = view.findViewById(R.id.sno_txt);
            productNameTxt = view.findViewById(R.id.product_name_txt);
            offerPriceTxt = view.findViewById(R.id.offer_price_txt);
            decButton = view.findViewById(R.id.dec_button);
            incButton = view.findViewById(R.id.inc_button);
            productQty = view.findViewById(R.id.product_qty);
            totalPriceTxt = view.findViewById(R.id.total_price_txt);
            mrppricetextview = view.findViewById(R.id.mrppricetextview);
            deleteButton = view.findViewById(R.id.delete_item_button);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyCartListAdapter.ViewHolder holder, int position) {
        if (cartMedicineList.size() > 0) {
            OCRToDigitalMedicineResponse item = cartMedicineList.get(position);
            holder.snoTxt.setText(String.valueOf(position + 1));
            if (cartMedicineList.get(position).getArtName().length() > 0) {
                holder.productNameTxt.setText(item.getArtName());
            }
            holder.productQty.setText(String.valueOf(item.getQty()));
            String rupeeSymbol = "\u20B9";
            if (!TextUtils.isEmpty(item.getArtprice())) {
                holder.offerPriceTxt.setText(rupeeSymbol + String.format(Locale.ENGLISH, "%.2f", Float.parseFloat(item.getArtprice())));
            } else {
                holder.offerPriceTxt.setText(rupeeSymbol + String.format(Locale.ENGLISH, "%.2f", Float.parseFloat("00")));
            }
            holder.decButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClickDecrement(position);
                }
            });
            holder.incButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClickIncrement(position);
                }
            });
            if (!TextUtils.isEmpty(item.getArtprice())) {
                Float total_price = Float.parseFloat(item.getArtprice()) * item.getQty();
                holder.totalPriceTxt.setText(rupeeSymbol + String.format(Locale.ENGLISH, "%.2f", total_price));
            } else {
                Float total_price = Float.parseFloat("00") * item.getQty();
                holder.totalPriceTxt.setText(rupeeSymbol + String.format(Locale.ENGLISH, "%.2f", total_price));
            }
            holder.deleteButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClickDelete(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cartMedicineList.size();
    }
}
