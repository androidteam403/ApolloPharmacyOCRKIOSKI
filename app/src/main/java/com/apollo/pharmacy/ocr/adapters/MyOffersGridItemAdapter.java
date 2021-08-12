package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.MyCartActivity;
import com.apollo.pharmacy.ocr.dialog.ItemBatchSelectionDilaog;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MyOffersGridItemAdapter extends RecyclerView.ViewHolder {

    private ImageView item_imageview;
    private CardView inc_dec_card;
    private LinearLayout itemOriginalPriceLayout, itemOfferPriceLayout, itemAddToCartLayout, parentLayout;
    private TextView product_name, product_cost, product_offercost, product_qty, save_textview, itemActualPrice;
    private Button increement_button, decreement_button;
    private int int_qty = 0;
    private RelativeLayout offerTagCustomLayout;
    private TextView offerTagView;

    public MyOffersGridItemAdapter(View itemView) {
        super(itemView);
        parentLayout = itemView.findViewById(R.id.products_grid_view_item_container);
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
        product_qty = itemView.findViewById(R.id.inc_dec_text);
        inc_dec_card = itemView.findViewById(R.id.card_view_inc_dec);
        increement_button = itemView.findViewById(R.id.increement_button);
        decreement_button = itemView.findViewById(R.id.decreement_button);
    }


    @SuppressLint("ResourceAsColor")
    public void setup(Context context, Product product, int position, List<OCRToDigitalMedicineResponse> datalist, ArrayList<Product> offersArrayList, CartCountListener cartCountListener) {
        setClickListeners(product, context, position, datalist, offersArrayList, cartCountListener);
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
            itemOfferPriceLayout.setVisibility(View.GONE);
            itemOriginalPriceLayout.setVisibility(View.VISIBLE);
            offerTagCustomLayout.setVisibility(View.GONE);
        }
        if (String.valueOf(product.getPrice()).length() > 0) {
            product_cost.setText(rupee_symbol + String.format("%.2f", product.getPrice()));
            Utils.strikeThroughText(product_cost);
            itemActualPrice.setText(rupee_symbol + String.format("%.2f", product.getPrice()));
        }
        itemAddToCartLayout.setVisibility(View.VISIBLE);
        inc_dec_card.setVisibility(View.GONE);
        for (OCRToDigitalMedicineResponse data : datalist) {
            if (data.getArtCode() != null) {
                if (data.getArtCode().equalsIgnoreCase(product.getSku())) {
                    itemAddToCartLayout.setVisibility(View.GONE);
                    inc_dec_card.setVisibility(View.VISIBLE);
                    product_qty.setText(String.valueOf(data.getQty()));
                    cartCountListener.cartCount(datalist.size());
                }
            }
        }
    }

    private void setClickListeners(Product product, Context context, int position, List<OCRToDigitalMedicineResponse> datalist, ArrayList<Product> offersArrayList, CartCountListener cartCountListener) {
        itemAddToCartLayout.setOnClickListener(v -> {

//            ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(product, context, position, datalist,offersArrayList, cartCountListener);
//            itemBatchSelectionDilaog.setTitle(product.getName());
//
//            itemBatchSelectionDilaog.setPositiveListener(view2 -> {
//                if (checkOutData != null) {
//                    checkOutData.checkoutData();
//                }
//                itemBatchSelectionDilaog.dismiss();
//            });
//            itemBatchSelectionDilaog.setNegativeListener(v1 -> {
////                activityHomeBinding.transColorId.setVisibility(View.GONE);
//                itemBatchSelectionDilaog.dismiss();
//            });
//            itemBatchSelectionDilaog.show();

            boolean product_avilable = false;
            if (null != datalist) {
                int count = 0;
                for (OCRToDigitalMedicineResponse data : datalist) {
                    String product_sku = product.getSku();
                    if (product_sku.equalsIgnoreCase(data.getArtCode())) {
                        product_avilable = true;
                        int qty = data.getQty();
                        qty = qty + 1;
                        datalist.remove(count);

                        OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                        object1.setArtName(offersArrayList.get(position).getName());
                        object1.setArtCode(offersArrayList.get(position).getSku());
                        if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                            object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
                        } else {
                            object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
                        }
                        object1.setMou(offersArrayList.get(position).getMou());
                        object1.setQty(qty);
                        object1.setContainer("Strip");
                        datalist.add(object1);
                        SessionManager.INSTANCE.setDataList(datalist);
                        break;
                    } else {
                        product_avilable = false;
                    }
                    count = count + 1;
                }

                if (!product_avilable) {
                    OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                    object1.setArtName(offersArrayList.get(position).getName());
                    object1.setArtCode(offersArrayList.get(position).getSku());
                    if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                        object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
                    } else {
                        object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
                    }
                    object1.setMou(offersArrayList.get(position).getMou());
                    object1.setQty(1);
                    object1.setContainer("Strip");
                    datalist.add(object1);
                    SessionManager.INSTANCE.setDataList(datalist);
                    cartCountListener.cartCount(datalist.size());
                    product_qty.setText(String.valueOf("1"));
                }
            }
            if (context instanceof MyCartActivity) {
                ((MyCartActivity) context).message_string = "addtocart";
                ((MyCartActivity) context).updatecartlist();
            }
            itemAddToCartLayout.setVisibility(View.GONE);
            inc_dec_card.setVisibility(View.VISIBLE);
        });

        increement_button.setOnClickListener(v -> {
            int qty = Integer.parseInt(product_qty.getText().toString());
            qty = qty + 1;
            String product_sku = offersArrayList.get(position).getSku();
            int count = 0;
            for (OCRToDigitalMedicineResponse data : datalist) {
                if (product_sku.equalsIgnoreCase(data.getArtCode())) {
                    datalist.remove(count);
                    break;
                }
                count = count + 1;
            }

            OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
            object1.setArtName(offersArrayList.get(position).getName());
            object1.setArtCode(offersArrayList.get(position).getSku());
            if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
            } else {
                object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
            }
            object1.setMou(offersArrayList.get(position).getMou());
            object1.setQty(qty);
            object1.setContainer("Strip");
            datalist.add(object1);
            product_qty.setText(String.valueOf(qty));
            cartCountListener.cartCount(datalist.size());
            SessionManager.INSTANCE.setDataList(datalist);
            if (context instanceof MyCartActivity) {
                ((MyCartActivity) context).message_string = "cartupdate";
            }
            if (context instanceof MyCartActivity) {
                ((MyCartActivity) context).updatecartlist();
            }
        });

        decreement_button.setOnClickListener(v -> {
            int qty = Integer.parseInt(product_qty.getText().toString());
            if (qty > 1) {
                qty = qty - 1;
                int count = 0;
                String product_code = offersArrayList.get(position).getSku();
                for (OCRToDigitalMedicineResponse data : datalist) {
                    if (product_code.equalsIgnoreCase(data.getArtCode())) {
                        datalist.remove(count);
                        break;
                    }
                    count = count + 1;
                }
                OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                object1.setArtName(offersArrayList.get(position).getName());
                object1.setArtCode(offersArrayList.get(position).getSku());
                if (null != offersArrayList.get(position).getSpecialPrice() && offersArrayList.get(position).getSpecialPrice().length() > 0) {
                    object1.setArtprice(offersArrayList.get(position).getSpecialPrice());
                } else {
                    object1.setArtprice(String.valueOf(offersArrayList.get(position).getPrice()));
                }
                object1.setMou(offersArrayList.get(position).getMou());
                object1.setQty(qty);
                object1.setContainer("Strip");
                datalist.add(object1);

                SessionManager.INSTANCE.setDataList(datalist);
                product_qty.setText(String.valueOf(qty));
                if (context instanceof MyCartActivity) {
                    ((MyCartActivity) context).message_string = "cartupdate";
                }
            } else {
                int qty1 = Integer.parseInt(product_qty.getText().toString());
                qty1 = qty1 - 1;
                int count = 0;
                String product_code = offersArrayList.get(position).getSku();
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
                if (context instanceof MyCartActivity) {
                    ((MyCartActivity) context).message_string = "cartdelete";
                    ((MyCartActivity) context).delete_item_sku = product_code;
                }
            }
            if (context instanceof MyCartActivity) {
                ((MyCartActivity) context).updatecartlist();
            }
        });

        item_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getIsInStock() == 1) {
                } else {
                    Utils.showSnackbarLinear(context, parentLayout, "Product Out of Stock");
                }
            }
        });
    }
}
