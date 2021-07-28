package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.model.Image;
import com.apollo.pharmacy.ocr.utility.PhotoPopupWindow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Image> mValues;
    Context mContext;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context context, ArrayList<Image> values, ItemListener itemListener) {
        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView deleteImage;
        public ImageView imageView;
        Image item;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            deleteImage = v.findViewById(R.id.image_delete);

            deleteImage.setOnClickListener(v1 -> {
                if (mListener != null) {
                    mListener.onItemClick(item);
                }
            });
        }

        public void setData(Image item) {
            this.item = item;
            Glide.with(mContext)
                    .load(item.getPath())
                    .apply(new RequestOptions().placeholder(R.drawable.thumbnail_image).error(R.drawable.thumbnail_image))
                    .into(imageView);
            imageView.setOnClickListener(view -> {
                new PhotoPopupWindow(mContext, R.layout.layout_image_fullview, view, item.getPath(), null);
            });
        }

        @Override
        public void onClick(View view) {

        }
    }

    @NotNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(Image item);
    }
}