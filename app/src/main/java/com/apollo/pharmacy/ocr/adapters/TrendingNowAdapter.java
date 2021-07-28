package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.enums.ViewMode;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TrendingNowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<Product> products;
    private int viewMode;
    public final int TYPE_LOAD = 2;
    private MyOffersAdapter.OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public TrendingNowAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ViewMode viewMode = ViewMode.values()[viewType];
        switch (viewMode) {
            case GRID:
                return new TrendingNowGridItemAdapter(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.view_trending_now, parent, false)
                );
            case LOAD:
                return new MyOffersAdapter.LoadHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false)
                );
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        if (getItemViewType(position) != TYPE_LOAD) {
            ViewMode viewMode = ViewMode.values()[getItemViewType(position)];
            Product product = products.get(position);
            if (viewMode == ViewMode.GRID) {
                int margin = Utils.convertDpToPixel(context, 5);
                GridLayoutManager.LayoutParams params = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(margin, margin, margin, 0);
                if (position % 2 == 0) {
                    // Left Item
                    params.rightMargin = Utils.convertDpToPixel(context, 2.5f);
                } else {
                    // Right Item
                    params.leftMargin = Utils.convertDpToPixel(context, 2.5f);
                }
                ((TrendingNowGridItemAdapter) holder).setup(context, product);
            }
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (null == products.get(position).type || products.get(position).type.equals("")) {
            type = this.viewMode;
        } else {
            type = TYPE_LOAD;
        }
        return type;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode.ordinal();
    }
}
