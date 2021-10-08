package com.apollo.pharmacy.ocr.activities.mposstoresetup.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.mposstoresetup.model.StoreListResponseModel;
import com.apollo.pharmacy.ocr.databinding.MposViewStoreSearchItemBinding;

import java.util.ArrayList;

public class MposGetStoresListAdapter extends RecyclerView.Adapter<MposGetStoresListAdapter.ViewHolder> implements Filterable {
    private Context activity;
    private ArrayList<StoreListResponseModel.StoreListObj> storeArrayList;
    private ArrayList<StoreListResponseModel.StoreListObj> storeFilteredArrayList;
    private GetStoresDialogMvpView storesDialogMvpView;

    public MposGetStoresListAdapter(Context activity, ArrayList<StoreListResponseModel.StoreListObj> storeArrayList, GetStoresDialogMvpView storesDialogMvpView) {
        this.activity = activity;
        this.storeArrayList = storeArrayList;
        this.storeFilteredArrayList = storeArrayList;
        this.storesDialogMvpView = storesDialogMvpView;
    }

    @NonNull
    @Override
    public MposGetStoresListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MposViewStoreSearchItemBinding doctorSearchItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.mpos_view_store_search_item, parent, false);
        return new MposGetStoresListAdapter.ViewHolder(doctorSearchItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MposGetStoresListAdapter.ViewHolder holder, int position) {
        StoreListResponseModel.StoreListObj item = storeFilteredArrayList.get(position);
        holder.viewStoreSearchItemBinding.setModel(item);
        holder.itemView.setOnClickListener(v -> {
            if (storesDialogMvpView != null) {
                storesDialogMvpView.onClickListener(item);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public MposViewStoreSearchItemBinding viewStoreSearchItemBinding;

        public ViewHolder(@NonNull MposViewStoreSearchItemBinding viewStoreSearchItemBinding) {
            super(viewStoreSearchItemBinding.getRoot());
            this.viewStoreSearchItemBinding = viewStoreSearchItemBinding;
        }
    }

    @Override
    public int getItemCount() {
        return storeFilteredArrayList.size();
    }

    public void onClickListener(GetStoresDialogMvpView mvpView) {
        this.storesDialogMvpView = mvpView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    storeFilteredArrayList = storeArrayList;
                } else {
                    ArrayList<StoreListResponseModel.StoreListObj> filteredList = new ArrayList<>();
                    for (StoreListResponseModel.StoreListObj row : storeArrayList) {
                        if (row.getStoreId().contains(charString.toUpperCase()) || row.getStoreName().contains(charString.toUpperCase())) {
                            filteredList.add(row);
                        }
                    }
                    storeFilteredArrayList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = storeFilteredArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                storeFilteredArrayList = (ArrayList<StoreListResponseModel.StoreListObj>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}