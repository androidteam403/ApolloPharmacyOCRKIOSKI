package com.apollo.pharmacy.ocr.dialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.AdapterBatchSelectionListBinding;
import com.apollo.pharmacy.ocr.model.BatchList;

import java.util.List;

public class AdapterItemBatchSelection extends RecyclerView.Adapter<AdapterItemBatchSelection.ViewHolder> {
    private Context context;
    private List<BatchList.Batch> itemBatchSelectionDataList;
    private OnItemBatchClick onItemBatchClick;

    public AdapterItemBatchSelection(Context context, List<BatchList.Batch> itemBatchSelectionDataList, OnItemBatchClick onItemBatchClick) {
        this.context = context;
        this.itemBatchSelectionDataList = itemBatchSelectionDataList;
        this.onItemBatchClick = onItemBatchClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterBatchSelectionListBinding adapterBatchSelectionListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_batch_selection_list, parent, false);
        return new AdapterItemBatchSelection.ViewHolder(adapterBatchSelectionListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BatchList.Batch itemBatchSelectionData = itemBatchSelectionDataList.get(position);
        holder.adapterBatchSelectionListBinding.date.setText(itemBatchSelectionData.getExpDate());
        holder.adapterBatchSelectionListBinding.price.setText(String.valueOf(itemBatchSelectionData.getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemBatchClick.onItemBatchClickData(position, itemBatchSelectionData);
            }
        });
    }


    public interface OnItemBatchClick {
        void onItemBatchClickData(int position, BatchList.Batch itemBatchSelectionData);
    }

    @Override
    public int getItemCount() {
        return itemBatchSelectionDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterBatchSelectionListBinding adapterBatchSelectionListBinding;

        public ViewHolder(@NonNull AdapterBatchSelectionListBinding adapterBatchSelectionListBinding) {
            super(adapterBatchSelectionListBinding.getRoot());
            this.adapterBatchSelectionListBinding = adapterBatchSelectionListBinding;
        }
    }
}

