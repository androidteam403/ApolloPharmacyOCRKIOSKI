package com.apollo.pharmacy.ocr.dialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReorderAdapter extends RecyclerView.Adapter<ReorderAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<OCRToDigitalMedicineResponse> ordersList;

    public ReorderAdapter(Context context, List<OCRToDigitalMedicineResponse> ordersList) {
        mContext = context;
        this.ordersList = ordersList;
        inflater = LayoutInflater.from(mContext);
    }

    @NotNull
    @Override
    public ReorderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_reorder, parent, false);
        return new ReorderAdapter.MyViewHolder(itemView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView sno_txt, productname_textview, productqty, totalpricetextview, offerpricetextview;
        private LinearLayout parentLayout;
        private RelativeLayout strikeThrough;

        public MyViewHolder(View view) {
            super(view);
            sno_txt = view.findViewById(R.id.sno_txt);
            productname_textview = view.findViewById(R.id.productname_textview);
            offerpricetextview = view.findViewById(R.id.offerpricetextview);
            totalpricetextview = view.findViewById(R.id.totalpricetextview);
            parentLayout = view.findViewById(R.id.parent_layout);
            productqty = view.findViewById(R.id.quantity_txt);
            strikeThrough = view.findViewById(R.id.strike_through);
        }
    }

    @Override
    public void onBindViewHolder(ReorderAdapter.MyViewHolder holder, int position) {
        OCRToDigitalMedicineResponse listItem = ordersList.get(position);
        holder.sno_txt.setText(String.valueOf(position + 1));
        holder.productname_textview.setText(listItem.getArtName());
        holder.productqty.setText(String.valueOf(listItem.getQty()));
        if (listItem.isOutOfStock()) {
            holder.strikeThrough.setVisibility(View.VISIBLE);
        } else {
            holder.strikeThrough.setVisibility(View.GONE);
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.default_image);
        requestOptions.override(100, 100);

        String rupeeSymbol = "\u20B9";
        if (null != listItem.getArtprice()) {
            holder.offerpricetextview.setText(rupeeSymbol + "" + String.format("%.2f", Float.parseFloat(listItem.getArtprice())));
        } else {
            holder.offerpricetextview.setText(rupeeSymbol + "" + String.format("%.2f", Float.parseFloat("45.00")));
        }

        double total_price = 0.0;
        if (null != listItem.getArtprice()) {
            total_price = listItem.getQty() * Float.parseFloat(listItem.getArtprice().toString());
        } else {
            total_price = listItem.getQty() * Float.parseFloat("45.00");
        }

        holder.totalpricetextview.setText(rupeeSymbol + "" + String.format("%.2f", total_price));

//        holder.productname_textview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String product_sku = listItem.getArtCode();
//                Intent intent = new Intent("cardReceiver");
//                intent.putExtra("message", "MedicinenameClicked");
//                intent.putExtra("product_sku", product_sku);
//                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
//            }
//        });

        if (position % 2 == 0) {
            holder.parentLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        } else {
            holder.parentLayout.setBackgroundColor(mContext.getResources().getColor(R.color.dialog_white_dark));
        }
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}