package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.enums.ViewMode;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductsCustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Product> products;
    private int viewMode;
    public final int TYPE_LOAD = 2;
    private OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;
    private List<OCRToDigitalMedicineResponse> datalist;
    private CartCountListener cartCountListener;
    private View itemView;

    public ProductsCustomAdapter(Context context, ArrayList<Product> products, CartCountListener cartCountListener) {
        this.context = context;
        this.products = products;
        this.cartCountListener = cartCountListener;
        datalist = new ArrayList<>();
        if (null != SessionManager.INSTANCE.getDataList()) {
            datalist = SessionManager.INSTANCE.getDataList();
        }
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewMode viewMode = ViewMode.values()[viewType];
        switch (viewMode) {
            case GRID:
                return new CategoryGridItemAdapter(
                        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_promotion_viewall, parent, false));
            case LOAD:
                return new LoadHolder(
                        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false)
                );
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        if (getItemViewType(position) != TYPE_LOAD) {
            ViewMode viewMode = ViewMode.values()[getItemViewType(position)];
            Product product = products.get(position);
            if (viewMode == ViewMode.GRID) {
                ((CategoryGridItemAdapter) holder).setup(context, product, position, datalist, cartCountListener);
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

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode.ordinal();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}
