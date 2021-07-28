package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.UnsupportedEncodingException;

public class TrendingNowGridItemAdapter extends RecyclerView.ViewHolder {
    private ImageView item_imageview;
    private TextView product_name, product_cost;
    private Context mContext;
    private LinearLayout parentLayout;

    public TrendingNowGridItemAdapter(View itemView) {
        super(itemView);
        item_imageview = itemView.findViewById(R.id.products_grid_view_item_img);
        product_name = itemView.findViewById(R.id.textview_product_name);
        product_cost = itemView.findViewById(R.id.textview_product_cost);
        parentLayout = itemView.findViewById(R.id.parent_layout);
    }

    @SuppressLint("ResourceAsColor")
    public void setup(Context context, Product product) {
        setClickListeners(product, context);

        product_name.setText(product.getName());
        String rupee_symbol;
        rupee_symbol = "\u20B9";
        byte[] utf8 = null;

        try {
            utf8 = rupee_symbol.getBytes("UTF-8");
            rupee_symbol = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        product_cost.setText("" + rupee_symbol + product.getPrice());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.default_image);
        requestOptions.override(100, 100);

        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(Constants.Get_Image_link + product.getImage())
                .into(item_imageview);
    }

    private void setClickListeners(Product product, Context context) {
        item_imageview.setOnClickListener(v -> {
            if (product.getIsInStock() == 1) {

            } else {
                Utils.showSnackbarLinear(mContext, parentLayout, "Product Out of Stock");
            }
        });
    }
}
