package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.MyCartListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class CartDeletedListAdapter extends RecyclerView.Adapter<CartDeletedListAdapter.MyViewHolder> {
    private Context mContext;
    private List<OCRToDigitalMedicineResponse> cartDeletedListArr;
    private MyCartListener addDeletedItemsInterface;

    public CartDeletedListAdapter(Context context, List<OCRToDigitalMedicineResponse> cartDeletedListArr, MyCartListener addDeletedItemsInterface) {
        mContext = context;
        this.cartDeletedListArr = cartDeletedListArr;
        this.addDeletedItemsInterface = addDeletedItemsInterface;
    }

    @NotNull
    @Override
    public CartDeletedListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cart_item_deleted, parent, false);
        return new CartDeletedListAdapter.MyViewHolder(itemView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameText, totalPriceText, offerPriceText, mrpPriceText, quantityText;
        private Button addAgainButton;

        public MyViewHolder(View view) {
            super(view);
            productNameText = view.findViewById(R.id.productname_textview);
            offerPriceText = view.findViewById(R.id.offerpricetextview);
            totalPriceText = view.findViewById(R.id.totalpricetextview);
            mrpPriceText = view.findViewById(R.id.mrppricetextview);
            quantityText = view.findViewById(R.id.quantity_textview);
            addAgainButton = view.findViewById(R.id.addagain_button);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OCRToDigitalMedicineResponse item = cartDeletedListArr.get(position);
        holder.productNameText.setText(item.getArtName());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.default_image);
        requestOptions.override(100, 100);
        String string = "\u20B9";
        byte[] utf8 = null;
        try {
            utf8 = string.getBytes("UTF-8");
            string = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        holder.offerPriceText.setText(string + String.format("%.2f", Float.parseFloat(item.getArtprice())));
        holder.quantityText.setText(String.valueOf(item.getQty()));
        holder.mrpPriceText.setText(string + "60");
        holder.mrpPriceText.setPaintFlags(holder.mrpPriceText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Float total_price = Float.parseFloat(item.getArtprice()) * item.getQty();
        holder.totalPriceText.setText(string + String.format("%.2f", total_price));

        holder.addAgainButton.setOnClickListener(view1 -> {
            if (addDeletedItemsInterface != null) {
                addDeletedItemsInterface.onDeleteItemClicked(item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartDeletedListArr.size();
    }
}
