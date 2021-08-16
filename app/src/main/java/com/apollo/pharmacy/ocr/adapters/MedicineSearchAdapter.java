package com.apollo.pharmacy.ocr.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.dialog.ItemBatchSelectionDilaog;
import com.apollo.pharmacy.ocr.model.Product;
import com.apollo.pharmacy.ocr.model.ProductSearch;
import com.apollo.pharmacy.ocr.utility.Constants;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MedicineSearchAdapter extends ArrayAdapter<ProductSearch> {
    private ArrayList<ProductSearch> tempCustomer, suggestions;
    private Context context;
    private String string;
    private Activity activity;
    private AddToCartCallBackData addToCartCallBackData;

    public MedicineSearchAdapter(Context context, ArrayList<ProductSearch> objects, Activity activity, AddToCartCallBackData addToCartCallBackData) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
        this.tempCustomer = new ArrayList<ProductSearch>(objects);
        this.suggestions = new ArrayList<ProductSearch>(objects);
        this.activity = activity;
        this.addToCartCallBackData = addToCartCallBackData;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        ProductSearch medicine = getItem(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.view_custom_medicine_search, parent, false);
        }

        TextView txtMedName = (TextView) convertView.findViewById(R.id.medName);
        TextView txtQtyIncrease = (TextView) convertView.findViewById(R.id.unitIncrease);
        TextView txtQtyDecrease = (TextView) convertView.findViewById(R.id.unitDecrease);
        TextView txtQty = (TextView) convertView.findViewById(R.id.unitCount);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtOfferPrice = (TextView) convertView.findViewById(R.id.offerPrice);
        TextView total_price_textview = (TextView) convertView.findViewById(R.id.total_price_textview);
        Button btnAddCart = (Button) convertView.findViewById(R.id.search_Addtocart);

        ImageView medImage = (ImageView) convertView.findViewById(R.id.medImage);
        Glide.with(context)
                .load(Constants.Get_Image_link + medicine.getImage())
                .error(R.drawable.hospital)
                .into(medImage);

        string = "\u20B9";
        byte[] utf8 = null;
        try {
            utf8 = string.getBytes("UTF-8");
            string = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        txtOfferPrice.setText(string + medicine.getPrice());
        total_price_textview.setText(string + medicine.getPrice());
        txtMedName.setText(medicine.getName());
        txtQty.setText("" + medicine.getQty());

        txtQtyIncrease.setOnClickListener(view -> {
//            medicine.setQty(medicine.getQty() + 1);
//            txtQty.setText("" + medicine.getQty());
//            Object priceobject = medicine.getPrice();
//            if (priceobject instanceof Integer) {
//                int medicine_price = (int) medicine.getPrice();
//                int total_price = Integer.parseInt(txtQty.getText().toString()) * medicine_price;
//                total_price_textview.setText(string + total_price);
//            } else {
//                double medicine_price = (Double) medicine.getPrice();
//                double total_price = Double.parseDouble(txtQty.getText().toString()) * medicine_price;
//                total_price_textview.setText(string + total_price);
//            }
        });

        txtQtyDecrease.setOnClickListener(view -> {
//            if (medicine.getQty() > 1) {
//                medicine.setQty(medicine.getQty() - 1);
//                txtQty.setText("" + medicine.getQty());
//                Object priceobject = medicine.getPrice();
//                if (priceobject instanceof Integer) {
//                    int medicine_price = (int) medicine.getPrice();
//                    int total_price = Integer.parseInt(txtQty.getText().toString()) * medicine_price;
//                    total_price_textview.setText(string + total_price);
//                } else {
//                    double medicine_price = (Double) medicine.getPrice();
//                    double total_price = Double.parseDouble(txtQty.getText().toString()) * medicine_price;
//                    total_price_textview.setText(string + total_price);
//                }
//            }
        });


        btnAddCart.setOnClickListener(view -> {

            ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(context);
            itemBatchSelectionDilaog.setTitle(medicine.getName());

            itemBatchSelectionDilaog.setUnitIncreaseListener(view1 -> {
                medicine.setQty(medicine.getQty() + 1);
                itemBatchSelectionDilaog.setQtyCount("" + medicine.getQty());
                txtQty.setText("" + medicine.getQty());
                Object priceobject = medicine.getPrice();
                if (priceobject instanceof Integer) {
                    int medicine_price = (int) medicine.getPrice();
                    int total_price = Integer.parseInt(txtQty.getText().toString()) * medicine_price;
                    total_price_textview.setText(string + total_price);
                } else {
                    double medicine_price = (Double.parseDouble(String.valueOf(medicine.getPrice())));
                    double total_price = Double.parseDouble(txtQty.getText().toString()) * medicine_price;
                    total_price_textview.setText(string + total_price);
                }
            });

            itemBatchSelectionDilaog.setUnitDecreaseListener(view2 -> {
                if (medicine.getQty() > 1) {
                    medicine.setQty(medicine.getQty() - 1);
                    itemBatchSelectionDilaog.setQtyCount("" + medicine.getQty());
                    txtQty.setText("" + medicine.getQty());
                    Object priceobject = medicine.getPrice();
                    if (priceobject instanceof Integer) {
                        int medicine_price = (int) medicine.getPrice();
                        int total_price = Integer.parseInt(txtQty.getText().toString()) * medicine_price;
                        total_price_textview.setText(string + total_price);
                    } else {
                        double medicine_price = (Double.parseDouble(String.valueOf(medicine.getPrice())));
                        double total_price = Double.parseDouble(txtQty.getText().toString()) * medicine_price;
                        total_price_textview.setText(string + total_price);
                    }
                }
            });

            itemBatchSelectionDilaog.setPositiveListener(view3 -> {

                Intent intent = new Intent("cardReceiver");
                intent.putExtra("message", "Addtocart");
                intent.putExtra("product_sku", medicine.getSku());
                intent.putExtra("product_name", medicine.getName());
                intent.putExtra("product_quantyty", txtQty.getText().toString());
                intent.putExtra("product_price", String.valueOf(medicine.getPrice()));
                // intent.putExtra("product_container", product_container);
                intent.putExtra("product_mou", String.valueOf(medicine.getMou()));
                intent.putExtra("product_position", String.valueOf(position));
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
//                if (addToCartCallBackData!=null) {
//                    addToCartCallBackData.addToCartCallBack();
//                }
                itemBatchSelectionDilaog.dismiss();
            });
            itemBatchSelectionDilaog.setNegativeListener(v1 -> {
//                activityHomeBinding.transColorId.setVisibility(View.GONE);
                itemBatchSelectionDilaog.dismiss();
            });
            itemBatchSelectionDilaog.show();

        });
        return convertView;
    }

    public interface AddToCartCallBackData {
        void addToCartCallBack();
    }

    @NotNull
    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            ProductSearch customer = (ProductSearch) resultValue;
            return customer.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (ProductSearch people : tempCustomer) {
                    if (people.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<ProductSearch> c = (ArrayList<ProductSearch>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (ProductSearch cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
