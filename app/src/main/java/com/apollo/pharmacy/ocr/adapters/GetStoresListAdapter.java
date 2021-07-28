package com.apollo.pharmacy.ocr.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.StoreSetupListener;
import com.apollo.pharmacy.ocr.model.GetStoreInfoResponse;

import java.util.ArrayList;

public class GetStoresListAdapter extends RecyclerView.Adapter<GetStoresListAdapter.ViewHolder> implements Filterable {
    private ArrayList<GetStoreInfoResponse.DeviceDetailsEntity> storeArrayList;
    private ArrayList<GetStoreInfoResponse.DeviceDetailsEntity> storeFilteredArrayList;
    private StoreSetupListener listener;

    public GetStoresListAdapter(ArrayList<GetStoreInfoResponse.DeviceDetailsEntity> storeArrayList, StoreSetupListener listener) {
        this.storeArrayList = storeArrayList;
        this.storeFilteredArrayList = storeArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GetStoresListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_store_search_item, parent, false);
        return new GetStoresListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GetStoresListAdapter.ViewHolder holder, int position) {
        GetStoreInfoResponse.DeviceDetailsEntity item = storeFilteredArrayList.get(position);
        holder.storeName.setText(item.getKIOSK_NAME());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClickListener(item);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName;

        public ViewHolder(View view) {
            super(view);
            storeName = view.findViewById(R.id.store_name);
        }
    }

    @Override
    public int getItemCount() {
        return storeFilteredArrayList.size();
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
                    ArrayList<GetStoreInfoResponse.DeviceDetailsEntity> filteredList = new ArrayList<>();
                    for (GetStoreInfoResponse.DeviceDetailsEntity row : storeArrayList) {
                        if (row.getKIOSK_ID().contains(charString.toUpperCase()) || row.getKIOSK_NAME().contains(charString.toUpperCase())) {
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
                storeFilteredArrayList = (ArrayList<GetStoreInfoResponse.DeviceDetailsEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
