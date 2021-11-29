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
import androidx.recyclerview.widget.LinearLayoutManager;
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
    List<OCRToDigitalMedicineResponse> expandList;
    ExpandCartListAdapter expandCartListAdapter;
    private Context context;
    private boolean expandHandlingBool;

    public MyCartListAdapter(Context context, List<OCRToDigitalMedicineResponse> cartMedicineList, OnItemClickListener listener, List<OCRToDigitalMedicineResponse> expandList) {
        this.context = context;
        this.cartMedicineList = cartMedicineList;
        this.listener = listener;
        this.expandList = expandList;
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
        ImageView deleteButton, expandView;
        RecyclerView expandResyclerView;

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
            expandView = view.findViewById(R.id.expand_view);
            expandResyclerView = view.findViewById(R.id.expand_recycle);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyCartListAdapter.ViewHolder holder, int position) {
        if (cartMedicineList.size() > 0) {
            OCRToDigitalMedicineResponse item = cartMedicineList.get(position);
            expandHandlingBool = false;

            if (expandList.size() > 0) {
                holder.expandView.setVisibility(View.VISIBLE);
            } else {
                holder.expandView.setVisibility(View.INVISIBLE);
            }
            for (int i = 0; i < expandList.size(); i++) {
                if (expandList != null && expandList.size() > 0 && cartMedicineList.get(position).getArtName().equalsIgnoreCase(expandList.get(i).getArtName())) {
                    expandHandlingBool = true;
                }
            }

            if (expandHandlingBool) {
                holder.expandView.setVisibility(View.VISIBLE);
            } else {
                holder.expandView.setVisibility(View.INVISIBLE);
            }


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
                    listener.onClickDelete(position, item);
                }
            });

            holder.expandView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!cartMedicineList.get(position).isExpandStatus()) {
                        cartMedicineList.get(position).setExpandStatus(true);

                        List<OCRToDigitalMedicineResponse> expandListDummy = new ArrayList<>();
                        for (int i = 0; i < expandList.size(); i++) {
                            if (cartMedicineList.get(position).getArtName().equalsIgnoreCase(expandList.get(i).getArtName())) {
                                expandListDummy.add(expandList.get(i));
                            }
                        }
                        holder.expandResyclerView.setVisibility(View.VISIBLE);
                        expandCartListAdapter = new ExpandCartListAdapter(context, cartMedicineList, listener, expandListDummy);
                        holder.expandResyclerView.setLayoutManager(new LinearLayoutManager(context));
                        holder.expandResyclerView.setAdapter(expandCartListAdapter);
                        expandCartListAdapter.notifyDataSetChanged();
                    } else {
                        cartMedicineList.get(position).setExpandStatus(false);
                        holder.expandResyclerView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cartMedicineList.size();
    }
}
