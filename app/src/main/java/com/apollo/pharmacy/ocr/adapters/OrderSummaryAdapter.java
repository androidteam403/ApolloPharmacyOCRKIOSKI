package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.bumptech.glide.request.RequestOptions;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.MyViewHolder> {
    private Context mContext;
    private List<OCRToDigitalMedicineResponse> orderSummaryItemArrayList;

    public OrderSummaryAdapter(Context context, List<OCRToDigitalMedicineResponse> orderSummaryItemArrayList) {
        mContext = context;
        this.orderSummaryItemArrayList = orderSummaryItemArrayList;
    }

    @NonNull
    @Override
    public OrderSummaryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_order_summary_item, viewGroup, false);
        return new OrderSummaryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderSummaryAdapter.MyViewHolder holder, int position) {
        OCRToDigitalMedicineResponse item = orderSummaryItemArrayList.get(position);

        holder.productname_textview.setText(item.getArtName());
        holder.productqty.setText(String.valueOf(item.getQty()));

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
        if (null != item.getArtprice()) {
            holder.offerpricetextview.setText(string + String.format("%.2f", Float.parseFloat(item.getArtprice())));
        } else {
            holder.offerpricetextview.setText(string + String.format("%.2f", Float.parseFloat("45.00")));
        }
        double total_price = 0.0;
        if (null != item.getArtprice()) {
            total_price = item.getQty() * Float.parseFloat(item.getArtprice().toString());
        } else {
            total_price = item.getQty() * Float.parseFloat("45.00");
        }
        holder.totalpricetextview.setText(string + String.format("%.2f", total_price));
        holder.productname_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_sku = item.getArtCode();
                Intent intent = new Intent("cardReceiver");
                intent.putExtra("message", "MedicinenameClicked");
                intent.putExtra("product_sku", product_sku);
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
            }
        });
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        } else {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.cart_alternate_color));
        }
    }

    @Override
    public int getItemCount() {
        return orderSummaryItemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView productname_textview, productqty, totalpricetextview, offerpricetextview;

        public MyViewHolder(View view) {
            super(view);
            productname_textview = view.findViewById(R.id.productname_textview);
            offerpricetextview = view.findViewById(R.id.offerpricetextview);
            totalpricetextview = view.findViewById(R.id.totalpricetextview);
            productqty = view.findViewById(R.id.quantity_txt);
        }
    }
}
