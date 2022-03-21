package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.AdapterSelectionOffersDialogBinding;
import com.apollo.pharmacy.ocr.interfaces.MyOffersListener;
import com.apollo.pharmacy.ocr.model.GroupOffersModelResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DialogOffersSelectionAdapter extends RecyclerView.Adapter<DialogOffersSelectionAdapter.ViewHolder> {

    private Activity activity;
    private List<GroupOffersModelResponse.Offer.PromoItem> imageList;
    private MyOffersListener myOffersListener;
    private GroupOffersModelResponse.Offer offer;
    int offerCount = 0;
    private ContinueEnablingHandlings continueEnablingHandlings;


    public DialogOffersSelectionAdapter(Activity activity, List<GroupOffersModelResponse.Offer.PromoItem> imageList, MyOffersListener myOffersListener, GroupOffersModelResponse.Offer offer, ContinueEnablingHandlings continueEnablingHandlings) {
        this.activity = activity;
        this.imageList = imageList;
        this.myOffersListener = myOffersListener;
        this.offer = offer;
        this.continueEnablingHandlings = continueEnablingHandlings;
    }

    @NonNull
    @Override
    public DialogOffersSelectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterSelectionOffersDialogBinding adapterSelectionOffersDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_selection_offers_dialog, parent, false);
        return new DialogOffersSelectionAdapter.ViewHolder(adapterSelectionOffersDialogBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DialogOffersSelectionAdapter.ViewHolder holder, int position) {
        GroupOffersModelResponse.Offer.PromoItem image = imageList.get(position);
        imageList.get(position).setOfferCount(0);
        imageList.get(position).setSelected(false);
        continueEnablingHandlings.continueHandlings(image);
        Picasso.with(activity).load(Uri.parse(String.valueOf(image.getArtImage()))).into(holder.adapterSelectionOffersDialogBinding.image);
        holder.adapterSelectionOffersDialogBinding.itemName.setText(image.getProductName());
        if (offer.getPromoItemSelectionType().equalsIgnoreCase("auto")) {
            if (offerCount < offer.getPromoItemSelection()) {
                imageList.get(position).setSelected(true);
                image.setOfferCount(offerCount + 1);
                offerCount = image.getOfferCount();
                holder.adapterSelectionOffersDialogBinding.parent.setBackgroundResource(R.color.home_bottom_background);
                myOffersListener.onSelectedOffersList(offer, image, imageList);
                continueEnablingHandlings.continueHandlings(image);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (offer.getPromoItemSelectionType().equalsIgnoreCase("manual") && offerCount < offer.getPromoItemSelection()) {
                    if (!imageList.get(position).isSelected()) {
                        imageList.get(position).setSelected(true);
                        image.setOfferCount(offerCount + 1);
                        offerCount = image.getOfferCount();
                        holder.adapterSelectionOffersDialogBinding.parent.setBackgroundResource(R.color.home_bottom_background);
                        myOffersListener.onSelectedOffersList(offer, image, imageList);
                        continueEnablingHandlings.continueHandlings(image);
                    } else {
                        imageList.get(position).setSelected(false);
                        image.setOfferCount(offerCount - 1);
                        offerCount = image.getOfferCount();
                        holder.adapterSelectionOffersDialogBinding.parent.setBackgroundResource(R.color.white);
                        continueEnablingHandlings.continueHandlings(image);
                    }
                } else if (offer.getPromoItemSelectionType().equalsIgnoreCase("manual")&&imageList.get(position).isSelected()) {
                    imageList.get(position).setSelected(false);
                    image.setOfferCount(offerCount - 1);
                    offerCount = image.getOfferCount();
                    holder.adapterSelectionOffersDialogBinding.parent.setBackgroundResource(R.color.white);
                    continueEnablingHandlings.continueHandlings(image);
                } else {
                    if (offer.getPromoItemSelectionType().equalsIgnoreCase("manual")) {
                        Toast.makeText(activity, "" + offer.getPromoTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
    });
}

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


public static class ViewHolder extends RecyclerView.ViewHolder {
    AdapterSelectionOffersDialogBinding adapterSelectionOffersDialogBinding;

    public ViewHolder(@NonNull AdapterSelectionOffersDialogBinding adapterSelectionOffersDialogBinding) {
        super(adapterSelectionOffersDialogBinding.getRoot());
        this.adapterSelectionOffersDialogBinding = adapterSelectionOffersDialogBinding;
    }
}

public interface ContinueEnablingHandlings {
    public void continueHandlings(GroupOffersModelResponse.Offer.PromoItem image);
}
}
