package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.AdapterGroupOffersBinding;
import com.apollo.pharmacy.ocr.interfaces.MyOffersListener;
import com.apollo.pharmacy.ocr.model.AllOffersResponse;
import com.apollo.pharmacy.ocr.model.GroupOffersModelResponse;
import com.bumptech.glide.Glide;

import java.util.List;

public class GroupOffersAdapters extends RecyclerView.Adapter<GroupOffersAdapters.ViewHolder> {

    private Activity activity;
    private List<GroupOffersModelResponse.Offer> imageList;
    private List<AllOffersResponse.Datum> offersDateList;

    private MyOffersListener myOffersListener;


    public GroupOffersAdapters(Activity activity, List<AllOffersResponse.Datum> offersDateList, MyOffersListener myOffersListener) {
        this.activity = activity;
        this.offersDateList = offersDateList;
        this.myOffersListener = myOffersListener;
    }

    @NonNull
    @Override
    public GroupOffersAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterGroupOffersBinding adapterGroupOffersBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_group_offers, parent, false);
        return new GroupOffersAdapters.ViewHolder(adapterGroupOffersBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GroupOffersAdapters.ViewHolder holder, int position) {
        AllOffersResponse.Datum image = offersDateList.get(position);
//        holder.adapterGroupOffersBinding.offersBanners.setImageResource(R.drawable.banner);
        Glide.with(activity).load(Uri.parse(String.valueOf("http://" + image.getPromoBanner()))).into(holder.adapterGroupOffersBinding.offersBanners);

        holder.adapterGroupOffersBinding.offersBanners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image.getPromoItemSelection() == 1) {
                    myOffersListener.onfiftyPerOffOffer(image, image.getPromoItems());
                } else if (image.getPromoItemSelection() == 2) {
                    myOffersListener.onBuyOneGetOneOffer(image, image.getPromoItems());
                } else if (image.getPromoItemSelection() == 3) {
                    myOffersListener.onBuyMultipleOnGroupOfOffers(image, image.getPromoItems());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return offersDateList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterGroupOffersBinding adapterGroupOffersBinding;

        public ViewHolder(@NonNull AdapterGroupOffersBinding adapterGroupOffersBinding) {
            super(adapterGroupOffersBinding.getRoot());
            this.adapterGroupOffersBinding = adapterGroupOffersBinding;
        }
    }
}
