package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.SubCategoryListener;
import com.apollo.pharmacy.ocr.model.SubCategoryItemModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SubCategoryListAdapter extends RecyclerView.Adapter<SubCategoryListAdapter.MyViewHolder> {
    private List<SubCategoryItemModel> subCategoryList;
    private Context activity;
    private SubCategoryListener listener;

    public SubCategoryListAdapter(Context activity, List<SubCategoryItemModel> subCategoryList, SubCategoryListener listener) {
        this.activity = activity;
        this.subCategoryList = subCategoryList;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subCategoryName;
        LinearLayout subCategoryParentLayout;
        ImageView categorySelectedImg;

        public MyViewHolder(View view) {
            super(view);
            subCategoryName = view.findViewById(R.id.sub_category_name);
            subCategoryParentLayout = view.findViewById(R.id.sub_category_parent_layout);
            categorySelectedImg = view.findViewById(R.id.category_selected);
        }
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sub_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, final int position) {
        SubCategoryItemModel subCategoryItem = subCategoryList.get(position);

        if (subCategoryItem.isSelected()) {
            holder.categorySelectedImg.setVisibility(View.VISIBLE);
            holder.subCategoryParentLayout.setBackgroundResource(R.drawable.sub_category_selected_bg);
            holder.subCategoryName.setTextColor(activity.getResources().getColor(R.color.colorBlack));
        } else {
            holder.categorySelectedImg.setVisibility(View.GONE);
            holder.subCategoryParentLayout.setBackgroundResource(R.drawable.sub_category_unselected_bg);
            holder.subCategoryName.setTextColor(activity.getResources().getColor(R.color.colorWhite));
        }

        holder.subCategoryName.setText(subCategoryItem.getSubCategoryName());
        holder.subCategoryParentLayout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onViewClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }
}
