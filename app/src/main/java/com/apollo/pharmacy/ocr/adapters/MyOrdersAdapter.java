package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.model.Item;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.OrderHistoryResponse;
import com.apollo.pharmacy.ocr.model.StatusHistory;
import com.apollo.pharmacy.ocr.utility.SessionManager;
import com.apollo.pharmacy.ocr.utility.Utils;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder> {
    private Context mContext;
    private List<OrderHistoryResponse> orderHistoryList;
    private MyOrderTrackInfoAdapter trackInfoAdaptor;
    private List<OCRToDigitalMedicineResponse> dataList;

    public MyOrdersAdapter(Context context, List<OrderHistoryResponse> orderHistoryList) {
        mContext = context;
        this.orderHistoryList = orderHistoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_my_orders, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderHistoryResponse item = orderHistoryList.get(position);
        holder.orderid_textview.setText(item.getOrdno());
        holder.deliverytype_textview.setText(item.getPayMode());
        holder.totalitem_count_textview.setText("( " + item.getItems().size() + " items )");
        List<StatusHistory> statusdata = item.getStatusHistory();
        String orderdate;
        if (statusdata != null && statusdata.size() > 0) {
            for (StatusHistory data : statusdata) {
                if (data.getStatus().equalsIgnoreCase("Order Placed")) {
                    orderdate = data.getDateTime();
                    holder.orderdate_textview.setText(orderdate);
                }
            }
        }
        if (!TextUtils.isEmpty(item.getPayMode())) {
            String cardType = item.getPayMode();
            holder.deliverytype_textview.setText(item.getPayMode());
            if (cardType.equalsIgnoreCase("ccavenue")) {
                holder.deliverytype_img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.payment_online));
            } else if (cardType.equalsIgnoreCase("online")) {
                holder.deliverytype_img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.payment_cash_on_delivery));
            } else if (cardType.equalsIgnoreCase("paytm")) {
                holder.deliverytype_img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.payment_paytm));
            } else if (cardType.equalsIgnoreCase("card")) {
                holder.deliverytype_img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.payment_card_swipe));
            }
        }
        String address = item.getPatAddr();
        address = address.replace("null", "");
        holder.address_textview.setText(address);


        if (!item.getStatusHistory().get(0).getDateTime().equalsIgnoreCase("-"))
        {
            holder.Status_textview.setText(item.getStatusHistory().get(0).getStatus());
        }
        if (!item.getStatusHistory().get(1).getDateTime().equalsIgnoreCase("-"))
        {
            holder.Status_textview.setText(item.getStatusHistory().get(1).getStatus());
        }
        if (!item.getStatusHistory().get(2).getDateTime().equalsIgnoreCase("-"))
        {
            holder.Status_textview.setText(item.getStatusHistory().get(2).getStatus());
        }
        if (!item.getStatusHistory().get(3).getDateTime().equalsIgnoreCase("-"))
        {
            holder.Status_textview.setText(item.getStatusHistory().get(3).getStatus());
        }
        if (!item.getStatusHistory().get(4).getDateTime().equalsIgnoreCase("-"))
        {
            holder.Status_textview.setText(item.getStatusHistory().get(4).getStatus());
        }


             /*  if ((item.getStatus().equalsIgnoreCase("Order Placed") ||
                item.getStatus().equalsIgnoreCase("Order Confirmed") ||
                item.getStatus().equalsIgnoreCase("Order Billed") ||
                item.getStatus().equalsIgnoreCase("Order Delivered") ||
                item.getStatus().equalsIgnoreCase("Order Cancelled"))) {
            holder.Status_textview.setText(item.getStatus());
             } else {
            holder.Status_textview.setText("-");
             }*/

        if (item.getStatus().equalsIgnoreCase("Order Delivered Successfully.")) {
            holder.order_delivered_layout.setVisibility(View.VISIBLE);
            holder.order_track_layout.setVisibility(View.GONE);
            holder.view_details_layout.setVisibility(View.VISIBLE);
        } else {
            holder.order_delivered_layout.setVisibility(View.GONE);
            holder.order_track_layout.setVisibility(View.VISIBLE);
            holder.view_details_layout.setVisibility(View.GONE);
        }

        if (!item.getStatus().equalsIgnoreCase("Order Delivered Successfully.")) {
            if (!item.getStatusHistory().get(0).getDateTime().equalsIgnoreCase("-")) {
                holder.order_confirmed_layout.setBackgroundResource(R.drawable.order_completed_status_bg);
                String orderPlaceDate = item.getStatusHistory().get(0).getDateTime();
                holder.order_confirmed_date.setText(Utils.getDateFormat(orderPlaceDate.substring(0, orderPlaceDate.indexOf("."))));
                holder.order_confirmed_time.setText(Utils.getTimeFormat(orderPlaceDate.substring(0, orderPlaceDate.indexOf("."))));
            } else {
                holder.order_confirmed_layout.setBackgroundResource(R.drawable.order_pending_status_bg);
                holder.order_confirmed_date.setText("-");
                holder.order_confirmed_time.setText("-");
            }
            if (!item.getStatusHistory().get(1).getDateTime().equalsIgnoreCase("-")) {
                holder.order_processing_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_delivered_color));
                holder.order_processing_layout.setBackgroundResource(R.drawable.order_completed_status_bg);
                holder.order_processing_date.setText(Utils.getDateFormat(item.getStatusHistory().get(1).getDateTime()));
                holder.order_processing_time.setText(Utils.getTimeFormat(item.getStatusHistory().get(1).getDateTime()));
            } else {
                holder.order_processing_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_pending_color));
                holder.order_processing_layout.setBackgroundResource(R.drawable.order_pending_status_bg);
                holder.order_processing_date.setText("-");
                holder.order_processing_time.setText("-");
            }
            if (!item.getStatusHistory().get(2).getDateTime().equalsIgnoreCase("-")) {
                holder.order_checking_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_delivered_color));
                holder.order_checking_layout.setBackgroundResource(R.drawable.order_completed_status_bg);
                holder.order_checking_date.setText(Utils.getDateFormat(item.getStatusHistory().get(2).getDateTime()));
                holder.order_checking_time.setText(Utils.getTimeFormat(item.getStatusHistory().get(2).getDateTime()));
            } else {
                holder.order_checking_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_pending_color));
                holder.order_checking_layout.setBackgroundResource(R.drawable.order_pending_status_bg);
                holder.order_checking_date.setText("-");
                holder.order_checking_time.setText("-");
            }
            if (!item.getStatusHistory().get(3).getDateTime().equalsIgnoreCase("-")) {
                holder.order_dispatched_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_delivered_color));
                holder.order_dispatched_layout.setBackgroundResource(R.drawable.order_completed_status_bg);
                holder.order_dispatched_date.setText(Utils.getDateFormat(item.getStatusHistory().get(3).getDateTime()));
                holder.order_dispatched_time.setText(Utils.getTimeFormat(item.getStatusHistory().get(3).getDateTime()));
            } else {
                holder.order_dispatched_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_pending_color));
                holder.order_dispatched_layout.setBackgroundResource(R.drawable.order_pending_status_bg);
                holder.order_dispatched_date.setText("-");
                holder.order_dispatched_time.setText("-");
            }
            if (!item.getStatusHistory().get(4).getDateTime().equalsIgnoreCase("-")) {
                holder.order_delivered_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_delivered_color));
                holder.order_delivered_layout_view.setBackgroundResource(R.drawable.order_completed_status_bg);
                holder.order_delivered_date.setText(Utils.getDateFormat(item.getStatusHistory().get(4).getDateTime()));
                holder.order_delivered_time.setText(Utils.getTimeFormat(item.getStatusHistory().get(4).getDateTime()));
            } else {
                holder.order_delivered_view.setBackgroundColor(mContext.getResources().getColor(R.color.order_status_pending_color));
                holder.order_delivered_layout_view.setBackgroundResource(R.drawable.order_pending_status_bg);
                holder.order_delivered_date.setText("-");
                holder.order_delivered_time.setText("-");
            }
        }

        holder.track_order_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.order_track_view_layout.getVisibility() == View.GONE) {
                    holder.order_track_view_layout.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.close_track_img.setOnClickListener(v -> {
            holder.order_track_view_layout.setVisibility(View.GONE);
        });

        String string = "\u20B9";
        byte[] utf8 = null;
        try {
            utf8 = string.getBytes("UTF-8");
            string = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        List<Item> medicine_list = item.getItems();
        if (null != medicine_list && medicine_list.size() > 0) {
            float Total_amount = 0;
            for (Item listItem : medicine_list) {
                Total_amount = Total_amount + (listItem.getMrp() * listItem.getQty());
            }
            holder.totalamount_textview.setText(string + String.format("%.2f", Total_amount));
        } else {
           // holder.totalamount_textview.setText(string + "0.00");
               holder.totalamount_textview.setText("-");
        }
        holder.view_details_layout.setOnClickListener(v -> {
            item.setSelected(true);
            refreshView(position);
        });
        holder.less_details_layout.setOnClickListener(v -> {
            refreshView(-1);
            item.setSelected(false);
        });

        holder.re_order_layout.setOnClickListener(v -> {
            List<Item> itemList = item.getItems();
            dataList = new ArrayList<>();
            for (Item medicine : itemList) {
                OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
                data.setArtName(medicine.getArtName());
                data.setArtCode(medicine.getArtCode());
                data.setArtprice(String.valueOf(medicine.getMrp()));
                data.setContainer("");
                data.setQty(medicine.getQty());
                dataList.add(data);
            }
            if (null != SessionManager.INSTANCE.getDataList()) {
                if (SessionManager.INSTANCE.getDataList().size() > 0) {
                    List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
                    tempCartItemList = SessionManager.INSTANCE.getDataList();
                    for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
                        dataList.add(listItem);
                    }
                }
            }
            SessionManager.INSTANCE.setDataList(dataList);
            Intent intent = new Intent("OrderhistoryCardReciver");
            intent.putExtra("message", "OrderNow");
            intent.putExtra("MedininesNames", new Gson().toJson(dataList));
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        });

        if (item.isSelected()) {
            holder.medicines_list.setVisibility(View.VISIBLE);
            holder.medicine_layout.setVisibility(View.VISIBLE);
            holder.view_details_layout.setVisibility(View.GONE);
            holder.less_details_layout.setVisibility(View.VISIBLE);
            List<Item> itemList = item.getItems();
            dataList = new ArrayList<>();
            for (Item medicine : itemList) {
                OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
                data.setArtName(TextUtils.isEmpty(medicine.getArtName()) ? "-" : medicine.getArtName());
                data.setArtCode("");
                data.setArtprice(String.valueOf(medicine.getMrp()));
                data.setContainer("");
                data.setQty(medicine.getQty());
                dataList.add(data);
            }

            holder.medicines_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            trackInfoAdaptor = new MyOrderTrackInfoAdapter(mContext, dataList);
            holder.medicines_list.setAdapter(trackInfoAdaptor);
        } else {
            holder.medicines_list.setVisibility(View.GONE);
            holder.medicine_layout.setVisibility(View.GONE);
            holder.view_details_layout.setVisibility(View.VISIBLE);
            holder.less_details_layout.setVisibility(View.GONE);
            if (item.getItems().size() == 0) {
                holder.order_delivered_layout.setVisibility(View.GONE);
                holder.view_details_layout.setVisibility(View.GONE);
            }
        }
    }

    public void refreshView(int pos) {
        int i = -1;
        for (OrderHistoryResponse obj : orderHistoryList) {
            i++;
            obj.setSelected(pos == i);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderid_textview, deliverytype_textview, totalitem_count_textview, scanneddate_textview, orderdate_textview,
                Status_textview, expecteddeliverydate_textview, totalamount_textview, address_textview, track_order_text,
                order_confirmed_date, order_confirmed_time, order_processing_date, order_processing_time, order_checking_date,
                order_checking_time, order_dispatched_date, order_dispatched_time, order_delivered_date, order_delivered_time;
        ImageView prescription_image, deliverytype_img, close_track_img;
        LinearLayout re_order_layout, delete_order_layout, order_delivered_layout, order_track_layout, order_track_view_layout,
                view_details_layout, less_details_layout, order_confirmed_layout, order_processing_layout, order_checking_layout,
                order_dispatched_layout, order_delivered_layout_view;
        RecyclerView medicines_list;
        View order_processing_view, order_checking_view, order_dispatched_view, order_delivered_view;
        LinearLayout medicine_layout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            orderid_textview = view.findViewById(R.id.orderid_textview);
            deliverytype_textview = view.findViewById(R.id.deliverytype_textview);
            totalitem_count_textview = view.findViewById(R.id.totalitem_count_textview);
            scanneddate_textview = view.findViewById(R.id.scanneddate_textview);
            orderdate_textview = view.findViewById(R.id.orderdate_textview);
            Status_textview = view.findViewById(R.id.Status_textview);
            expecteddeliverydate_textview = view.findViewById(R.id.expecteddeliverydate_textview);
            totalamount_textview = view.findViewById(R.id.totalamount_textview);
            address_textview = view.findViewById(R.id.address_textview);
            prescription_image = view.findViewById(R.id.prescription_image);
            deliverytype_img = view.findViewById(R.id.deliverytype_img);
            re_order_layout = view.findViewById(R.id.re_order_layout);
            delete_order_layout = view.findViewById(R.id.delete_order_layout);
            medicines_list = view.findViewById(R.id.medicines_recyclerView);
            medicine_layout = view.findViewById(R.id.recycle_layout);
            view_details_layout = view.findViewById(R.id.view_details_layout);
            less_details_layout = view.findViewById(R.id.less_details_layout);
            order_delivered_layout = view.findViewById(R.id.order_delivered_layout);
            order_track_layout = view.findViewById(R.id.order_track_layout);
            track_order_text = view.findViewById(R.id.track_order_text);
            order_track_view_layout = view.findViewById(R.id.order_track_view_layout);
            close_track_img = view.findViewById(R.id.close_track_img);

            order_confirmed_layout = view.findViewById(R.id.order_confirmed_layout);
            order_processing_layout = view.findViewById(R.id.order_processing_layout);
            order_checking_layout = view.findViewById(R.id.order_checking_layout);
            order_dispatched_layout = view.findViewById(R.id.order_dispatched_layout);
            order_delivered_layout_view = view.findViewById(R.id.order_delivered_layout_view);
            order_processing_view = view.findViewById(R.id.order_processing_view);
            order_checking_view = view.findViewById(R.id.order_checking_view);
            order_dispatched_view = view.findViewById(R.id.order_dispatched_view);
            order_delivered_view = view.findViewById(R.id.order_delivered_view);
            order_confirmed_date = view.findViewById(R.id.order_confirmed_date);
            order_confirmed_time = view.findViewById(R.id.order_confirmed_time);
            order_processing_date = view.findViewById(R.id.order_processing_date);
            order_processing_time = view.findViewById(R.id.order_processing_time);
            order_checking_date = view.findViewById(R.id.order_checking_date);
            order_checking_time = view.findViewById(R.id.order_checking_time);
            order_dispatched_date = view.findViewById(R.id.order_dispatched_date);
            order_dispatched_time = view.findViewById(R.id.order_dispatched_time);
            order_delivered_date = view.findViewById(R.id.order_delivered_date);
            order_delivered_time = view.findViewById(R.id.order_delivered_time);
        }
    }
}
