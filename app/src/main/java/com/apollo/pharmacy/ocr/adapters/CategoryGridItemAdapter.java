package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class CategoryGridItemAdapter extends RecyclerView.ViewHolder {
    private static final String TAG = CategoryGridItemAdapter.class.getSimpleName();
    private TextView itemName, itemOfferPrice, itemOriginalPrice, itemSavePrice, offerTagViewPrice, inc_dec_text, itemActualPrice, offerTagPriceVal;
    private ImageView itemImage;
    private RelativeLayout offerTagLayout, offerTagCustomLayout;
    private CardView inc_dec_card;
    private Button increement_button, decreement_button;
    private LinearLayout addToCartLayout, itemOriginalPriceLayout, itemOfferPriceLayout, itemAddToCartLayout, parentLayout;
    Context mContext;

    public CategoryGridItemAdapter(View view) {
        super(view);
        itemImage = view.findViewById(R.id.itemImage);
        itemName = view.findViewById(R.id.itemName);
        itemOfferPrice = view.findViewById(R.id.itemOfferPrice);
        itemOriginalPrice = view.findViewById(R.id.itemOriginalPrice);
        itemSavePrice = view.findViewById(R.id.itemSavePrice);
        offerTagViewPrice = view.findViewById(R.id.offerTagViewPrice);
        offerTagPriceVal = view.findViewById(R.id.offerTagViewPriceVal);

        offerTagLayout = view.findViewById(R.id.offerTagView);
        offerTagCustomLayout = view.findViewById(R.id.offerTagLayout);
        inc_dec_card = view.findViewById(R.id.card_view_inc_dec);
        itemAddToCartLayout = view.findViewById(R.id.item_addto_cart_layout);
        increement_button = view.findViewById(R.id.increement_button);
        decreement_button = view.findViewById(R.id.decreement_button);
        addToCartLayout = view.findViewById(R.id.item_addto_cart_layout);
        inc_dec_text = view.findViewById(R.id.inc_dec_text);

        itemActualPrice = view.findViewById(R.id.itemActualPrice);
        itemOriginalPriceLayout = view.findViewById(R.id.itemOriginalPriceLayout);
        itemOfferPriceLayout = view.findViewById(R.id.itemOfferPriceLayout);
        parentLayout = view.findViewById(R.id.parent_layout);
    }

    @SuppressLint("ResourceAsColor")
    public void setup(Context context, Product product, int position, List<OCRToDigitalMedicineResponse> datalist, CartCountListener cartCountListener) {
        setClickListeners(product, context, position, datalist, cartCountListener);
        String string = "\u20B9";
        byte[] utf8 = null;
        try {
            utf8 = string.getBytes("UTF-8");
            string = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.sample_medicine_image);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(Constants.Get_Image_link + product.getImage()).into(itemImage);
        itemName.setText(product.getName());

        if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
            if (product.getPrice() > Float.parseFloat(product.getSpecialPrice())) {
                Utils.printMessage(TAG, "Special :: " + product.getSpecialPrice() + ", Price :: " + product.getPrice());
                itemOfferPrice.setText(string + String.format("%.2f", Float.parseFloat(product.getSpecialPrice())));
                itemOriginalPrice.setText(string + String.format("%.2f", product.getPrice()));
                Utils.strikeThroughText(itemOriginalPrice);
                itemOriginalPriceLayout.setVisibility(View.GONE);
                itemOfferPriceLayout.setVisibility(View.VISIBLE);
                itemSavePrice.setVisibility(View.VISIBLE);
                float offerSavePrice = product.getPrice() - Float.parseFloat(product.getSpecialPrice());
                itemSavePrice.setText(context.getResources().getString(R.string.label_save) + " " + string + String.format("%.2f", offerSavePrice));
                float offerTagVal = Float.parseFloat(product.getSpecialPrice()) / product.getPrice();
                float discountTag = offerTagVal * 10;
                int disTagVal = (int) discountTag;
                offerTagCustomLayout.setVisibility(View.VISIBLE);
                offerTagPriceVal.setText("" + disTagVal + "%");
            } else {
                itemSavePrice.setVisibility(View.INVISIBLE);
                itemOfferPriceLayout.setVisibility(View.GONE);
                itemOriginalPriceLayout.setVisibility(View.VISIBLE);
                offerTagCustomLayout.setVisibility(View.GONE);
                itemActualPrice.setText(string + String.format("%.2f", product.getPrice()));
            }
        } else {
            itemSavePrice.setVisibility(View.INVISIBLE);
            itemOfferPriceLayout.setVisibility(View.GONE);
            itemOriginalPriceLayout.setVisibility(View.VISIBLE);
            offerTagCustomLayout.setVisibility(View.GONE);
            itemActualPrice.setText(string + String.format("%.2f", product.getPrice()));
        }
        offerTagLayout.setVisibility(View.GONE);
        itemAddToCartLayout.setVisibility(View.VISIBLE);
        inc_dec_card.setVisibility(View.GONE);

        for (OCRToDigitalMedicineResponse data : datalist) {
            if (data.getArtCode() != null) {
                if (data.getArtCode().equalsIgnoreCase(product.getSku())) {
                    itemAddToCartLayout.setVisibility(View.GONE);
                    inc_dec_card.setVisibility(View.VISIBLE);
                    inc_dec_text.setText(String.valueOf(data.getQty()));
                    cartCountListener.cartCount(datalist.size());
                }
            }
        }
    }

    private void setClickListeners(Product product, Context context, int position, List<OCRToDigitalMedicineResponse> datalist, CartCountListener cartCountListener) {

        addToCartLayout.setOnClickListener(v -> {
            if (cartCountListener == null) {
                Utils.showSnackbarLinear(mContext, parentLayout, "Add Cart null reference ");
                return;
            }
            itemAddToCartLayout.setVisibility(View.GONE);
            inc_dec_card.setVisibility(View.VISIBLE);
            inc_dec_text.setText("1");

            boolean product_avilable = false;
            if (null != datalist) {
                int count = 0;
                for (OCRToDigitalMedicineResponse data : datalist) {
                    if (data.getArtCode() != null) {
                        if (data.getArtCode().equalsIgnoreCase(product.getSku())) {
                            product_avilable = true;
                            int qty = data.getQty();
                            qty = qty + 1;
                            datalist.remove(count);
                            OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                            object1.setArtName(product.getName());
                            object1.setArtCode(product.getSku());
                            if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                                object1.setArtprice(product.getSpecialPrice());
                            } else {
                                object1.setArtprice(String.valueOf(product.getPrice()));
                            }
                            object1.setQty(qty);
                            object1.setMou(product.getMou());
                            object1.setContainer("Strip");
                            datalist.add(object1);
                            inc_dec_text.setText(String.valueOf(qty));
                            SessionManager.INSTANCE.setDataList(datalist);
                            break;
                        } else {
                            product_avilable = false;
                        }
                    }
                    count = count + 1;
                }
                if (!product_avilable) {
                    OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                    object1.setArtName(product.getName());
                    object1.setArtCode(product.getSku());
                    if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                        object1.setArtprice(product.getSpecialPrice());
                    } else {
                        object1.setArtprice(String.valueOf(product.getPrice()));
                    }
                    object1.setQty(1);
                    object1.setMou(product.getMou());
                    object1.setContainer("Strip");
                    datalist.add(object1);
                    SessionManager.INSTANCE.setDataList(datalist);
                    cartCountListener.cartCount(datalist.size());
                    inc_dec_text.setText(String.valueOf("1"));
                }
            }

            Intent intent = new Intent("UpdateCartItemCount");
            intent.putExtra("message", "CartCount");
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

            itemAddToCartLayout.setVisibility(View.GONE);
            inc_dec_card.setVisibility(View.VISIBLE);
        });

        increement_button.setOnClickListener(view -> {
            int qty = Integer.parseInt(inc_dec_text.getText().toString());
            qty = qty + 1;
            String product_sku = product.getSku();
            int count = 0;
            for (OCRToDigitalMedicineResponse data : datalist) {
                if (product_sku.equalsIgnoreCase(data.getArtCode())) {
                    datalist.remove(count);
                    break;
                }
                count = count + 1;
            }
            OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
            object1.setArtName(product.getName());
            object1.setArtCode(product.getSku());
            if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                object1.setArtprice(product.getSpecialPrice());
            } else {
                object1.setArtprice(String.valueOf(product.getPrice()));
            }
            object1.setQty(qty);
            object1.setMou(product.getMou());
            object1.setContainer("Strip");
            datalist.add(object1);
            inc_dec_text.setText(String.valueOf(qty));
            cartCountListener.cartCount(datalist.size());
            SessionManager.INSTANCE.setDataList(datalist);

            Intent intent = new Intent("UpdateCartItemCount");
            intent.putExtra("message", "CartCount");
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        });
        decreement_button.setOnClickListener(view -> {
            int qty = Integer.parseInt(inc_dec_text.getText().toString());
            if (qty > 1) {
                qty = qty - 1;
                int count = 0;
                String product_code = product.getSku();
                for (OCRToDigitalMedicineResponse data : datalist) {
                    if (product_code.equalsIgnoreCase(data.getArtCode())) {
                        datalist.remove(count);
                        break;
                    }
                    count = count + 1;
                }
                OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                object1.setArtName(product.getName());
                object1.setArtCode(product.getSku());
                if (null != product.getSpecialPrice() && product.getSpecialPrice().length() > 0) {
                    object1.setArtprice(product.getSpecialPrice());
                } else {
                    object1.setArtprice(String.valueOf(product.getPrice()));
                }
                object1.setQty(qty);
                object1.setMou(product.getMou());
                object1.setContainer("Strip");
                datalist.add(object1);

                SessionManager.INSTANCE.setDataList(datalist);
                Intent intent = new Intent("UpdateCartItemCount");
                intent.putExtra("message", "CartCount");
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                inc_dec_text.setText(String.valueOf(qty));
            } else {
                int count = 0;
                String product_code = product.getSku();
                for (OCRToDigitalMedicineResponse data : datalist) {
                    if (product_code.equalsIgnoreCase(data.getArtCode())) {
                        datalist.remove(count);
                        break;
                    }
                    count = count + 1;
                }
                SessionManager.INSTANCE.setDataList(datalist);
                itemAddToCartLayout.setVisibility(View.VISIBLE);
                inc_dec_card.setVisibility(View.GONE);
                if (itemAddToCartLayout.getVisibility() == View.VISIBLE) {
                    cartCountListener.cartCount(datalist.size());
                }
                Intent intent = new Intent("UpdateCartItemCount");
                intent.putExtra("message", "CartCount");
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
            }
        });
    }
}
