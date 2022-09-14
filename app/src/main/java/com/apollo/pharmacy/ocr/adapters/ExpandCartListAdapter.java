package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class ExpandCartListAdapter extends RecyclerView.Adapter<ExpandCartListAdapter.ViewHolder> {
    private List<OCRToDigitalMedicineResponse> cartMedicineList;
    private final OnItemClickListener listener;
    List<OCRToDigitalMedicineResponse> expandList;
    private Context context;


    public ExpandCartListAdapter(Context context, List<OCRToDigitalMedicineResponse> cartMedicineList, OnItemClickListener listener, List<OCRToDigitalMedicineResponse> expandList) {
        this.context = context;
        this.cartMedicineList = cartMedicineList;
        this.listener = listener;
        this.expandList = expandList;
    }

    @NonNull
    @Override
    public ExpandCartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_expand_cart_view, parent, false);
        return new ExpandCartListAdapter.ViewHolder(itemView);
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
    public void onBindViewHolder(@NonNull ExpandCartListAdapter.ViewHolder holder, int position) {
        if (expandList.size() > 0) {
            OCRToDigitalMedicineResponse item = expandList.get(position);
            holder.snoTxt.setText(String.valueOf(position + 1));
            if (expandList.get(position).getArtName().length() > 0) {
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






            if (item.getNetAmountInclTax() != null && !item.getNetAmountInclTax().isEmpty()) {

                DecimalFormat formatter = new DecimalFormat("#,###.00");
                String pharmaformatted = formatter.format(Double.valueOf(item.getNetAmountInclTax()));


                holder.totalPriceTxt.setText(rupeeSymbol + pharmaformatted);

            } else {

                if (!TextUtils.isEmpty(item.getArtprice())) {
                    Float total_price = Float.parseFloat(item.getArtprice()) * item.getQty();

                    DecimalFormat formatter = new DecimalFormat("#,###.00");
                    String pharmaformatted = formatter.format(total_price);


                    holder.totalPriceTxt.setText(rupeeSymbol + pharmaformatted);
                } else {
                    Float total_price = Float.parseFloat("00") * item.getQty();
                    holder.totalPriceTxt.setText(rupeeSymbol + String.format(Locale.ENGLISH, "%.2f", total_price));
                }

            }










            holder.deleteButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClickDelete(position, item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return expandList.size();
    }
}