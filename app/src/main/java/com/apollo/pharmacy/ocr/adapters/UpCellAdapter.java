package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.UpcellAdapterBinding;
import com.apollo.pharmacy.ocr.model.UpCellCrossCellResponse;

import java.util.List;

public class UpCellAdapter extends RecyclerView.Adapter<UpCellAdapter.ViewHolder> {

    private Activity activity;
    private List<UpCellCrossCellResponse.Upselling> upsellList;

    public UpCellAdapter(Activity activity, List<UpCellCrossCellResponse.Upselling> upsellList) {
        this.activity = activity;
        this.upsellList = upsellList;
    }

    @NonNull
    @Override
    public UpCellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UpcellAdapterBinding upcellAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.upcell_adapter, parent, false);
        return new UpCellAdapter.ViewHolder(upcellAdapterBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull UpCellAdapter.ViewHolder holder, int position) {
        UpCellCrossCellResponse.Upselling upselling = upsellList.get(position);
        holder.upcellAdapterBinding.itemName.setText(upselling.getItemname());
//        Picasso.with(activity).load(Uri.parse(String.valueOf(upsell.getImage()))).error(R.drawable.placeholder_image).into(holder.adapterAccesariesItemsBinding.image);
    }


    @Override
    public int getItemCount() {
        return upsellList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        UpcellAdapterBinding upcellAdapterBinding;

        public ViewHolder(@NonNull UpcellAdapterBinding upcellAdapterBinding) {
            super(upcellAdapterBinding.getRoot());
            this.upcellAdapterBinding = upcellAdapterBinding;
        }
    }

}
