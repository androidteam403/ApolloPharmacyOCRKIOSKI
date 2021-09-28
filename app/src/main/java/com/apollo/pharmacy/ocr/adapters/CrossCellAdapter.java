package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.CrossCellAdapterBinding;
import com.apollo.pharmacy.ocr.model.UpCellCrossCellResponse;

import java.util.List;

public class CrossCellAdapter extends RecyclerView.Adapter<CrossCellAdapter.ViewHolder> {

    private Activity activity;
    private List<UpCellCrossCellResponse.Crossselling> upsellList;
    boolean addToCarLayHandel;

    public CrossCellAdapter(Activity activity, List<UpCellCrossCellResponse.Crossselling> upsellList, boolean addToCarLayHandel) {
        this.activity = activity;
        this.upsellList = upsellList;
        this.addToCarLayHandel = addToCarLayHandel;
    }

    @NonNull
    @Override
    public CrossCellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CrossCellAdapterBinding crossCellAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cross_cell_adapter, parent, false);
        return new CrossCellAdapter.ViewHolder(crossCellAdapterBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CrossCellAdapter.ViewHolder holder, int position) {
        UpCellCrossCellResponse.Crossselling crossselling = upsellList.get(position);
        holder.adapterAccesariesItemsBinding.itemName.setText(crossselling.getItemname());
        if (addToCarLayHandel){
            holder.adapterAccesariesItemsBinding.itemAddtoCartLayout.setVisibility(View.VISIBLE);
        }
//        Picasso.with(activity).load(Uri.parse(String.valueOf(upsell.getImage()))).error(R.drawable.placeholder_image).into(holder.adapterAccesariesItemsBinding.image);
    }


    @Override
    public int getItemCount() {
        return upsellList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CrossCellAdapterBinding adapterAccesariesItemsBinding;

        public ViewHolder(@NonNull CrossCellAdapterBinding adapterAccesariesItemsBinding) {
            super(adapterAccesariesItemsBinding.getRoot());
            this.adapterAccesariesItemsBinding = adapterAccesariesItemsBinding;
        }
    }
}
