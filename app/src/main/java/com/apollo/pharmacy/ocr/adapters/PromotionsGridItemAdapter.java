package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.UnsupportedEncodingException;

public class PromotionsGridItemAdapter extends RecyclerView.ViewHolder {

    private ImageView item_imageview;
    private LinearLayout itemOriginalPriceLayout, itemOfferPriceLayout, itemAddToCartLayout;
    private TextView product_name, product_cost, product_offercost, save_textview, itemActualPrice;
    private RelativeLayout offerTagCustomLayout;
    private TextView offerTagView;
    private LinearLayout parentLayout;

    public PromotionsGridItemAdapter(View itemView) {
        super(itemView);
        item_imageview = itemView.findViewById(R.id.products_grid_view_item_img);
        product_name = itemView.findViewById(R.id.textview_product_name);
        product_cost = itemView.findViewById(R.id.textview_product_cost);
        product_offercost = itemView.findViewById(R.id.textview_product_offercost);
        save_textview = itemView.findViewById(R.id.save_textview);
        itemOriginalPriceLayout = itemView.findViewById(R.id.itemOriginalPriceLayout);
        itemOfferPriceLayout = itemView.findViewById(R.id.itemOfferPriceLayout);
        offerTagCustomLayout = itemView.findViewById(R.id.offerTagLayout);
        offerTagView = itemView.findViewById(R.id.offerTagViewPriceVal);

        itemActualPrice = itemView.findViewById(R.id.itemActualPrice);
        itemAddToCartLayout = itemView.findViewById(R.id.item_addto_cart_layout);
        parentLayout = itemView.findViewById(R.id.parent_layout);
    }

    @SuppressLint("ResourceAsColor")
    public void setup(Context context, Product product, int position) {
        setClickListeners(product, context, position);
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

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.default_image);
        requestOptions.override(100, 100);

        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(Constants.Get_Image_link + product.getImage())
                .into(item_imageview);

        if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
            product_offercost.setText(rupee_symbol + String.format("%.2f", Float.parseFloat(product.getSpecialPrice())));
            itemOriginalPriceLayout.setVisibility(View.GONE);
            itemOfferPriceLayout.setVisibility(View.VISIBLE);
            save_textview.setVisibility(View.VISIBLE);
            float offerSavePrice = product.getPrice() - Float.parseFloat(product.getSpecialPrice());
            save_textview.setText(context.getResources().getString(R.string.label_save) + " " + rupee_symbol + String.format("%.2f", offerSavePrice));

            float offerTagVal = Float.parseFloat(product.getSpecialPrice()) / product.getPrice();
            float discountTag = offerTagVal * 10;
            int disTagVal = (int) discountTag;
            offerTagCustomLayout.setVisibility(View.VISIBLE);
            offerTagView.setText("" + disTagVal + "%");
        } else {
            save_textview.setVisibility(View.INVISIBLE);
            product_offercost.setVisibility(View.GONE);
            itemOfferPriceLayout.setVisibility(View.GONE);
            itemOriginalPriceLayout.setVisibility(View.VISIBLE);
            offerTagCustomLayout.setVisibility(View.GONE);
        }
        if (String.valueOf(product.getPrice()).length() > 0) {
            product_cost.setText(rupee_symbol + String.format("%.2f", product.getPrice()));
            Utils.strikeThroughText(product_cost);
            itemActualPrice.setText(rupee_symbol + String.format("%.2f", product.getPrice()));
        }
    }

    private void setClickListeners(Product product, Context context, int position) {
        item_imageview.setOnClickListener(v -> {
            if (product.getIsInStock() == 1) {

            } else {
                Utils.showSnackbarLinear(context, parentLayout, "Product Out of Stock");
            }
        });
    }
}
