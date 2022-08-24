package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.interfaces.OnItemClickListener;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {
    private final Context mContext;
    private List<UserAddress> userAddressList = null;
    private final OnItemClickListener listener;

    public AddressListAdapter(Context context, List<UserAddress> userAddressList, OnItemClickListener listener) {
        mContext = context;
        this.userAddressList = userAddressList;
        this.listener = listener;
    }

    @NotNull
    @Override
    public AddressListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_address, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AddressListAdapter.MyViewHolder holder, int position) {
        UserAddress userAdd = userAddressList.get(position);
        holder.User_name.setText(userAdd.getName());
        holder.userMobile.setText("P " + userAdd.getMappingMobile());
        if (userAdd.getAddress1().length() > 0) {
            holder.User_address.setText(userAdd.getAddress1() + ", " + userAdd.getCity() + ", " + userAdd.getState());
        } else {
            holder.User_address.setText(userAdd.getCity() + ", " + userAdd.getState());
        }
        if (position == 0) {
            if (SessionManager.INSTANCE.getUseraddress().getAddress1() == null) {
                SessionManager.INSTANCE.setUseraddress(userAdd);
            }
            holder.homeTxt.setText(mContext.getResources().getString(R.string.label_home));
            holder.typeTxt.setText("(" + mContext.getResources().getString(R.string.label_default) + ")");
        } else {
            holder.homeTxt.setText("");
            holder.typeTxt.setText("");
        }
        if (userAdd.isItemSelected()) {
            holder.parentListItem.setBackgroundResource(R.drawable.review_address_home_bg);
            holder.selectAddressText.setBackgroundResource(R.drawable.review_order_button_bg);
            holder.selectAddressText.setText(mContext.getResources().getString(R.string.label_selected));
            holder.selectAddressText.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        } else {
            holder.parentListItem.setBackgroundResource(R.drawable.address_unselected_bg);
            holder.selectAddressText.setText(mContext.getResources().getString(R.string.label_select));
            holder.selectAddressText.setBackgroundResource(R.drawable.review_address_home_bg);
            holder.selectAddressText.setTextColor(mContext.getResources().getColor(R.color.review_color_blue));
        }

        holder.parentListItem.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });

        holder.editAddressImg.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClickEdit(position);
            }
        });
        holder.deleteAddressImg.setOnClickListener(v -> {
            if (listener != null) {
//                listener.onClickDelete(position, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userAddressList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout parentListItem;
        private final ImageView editAddressImg, deleteAddressImg;
        private final TextView User_name, User_address, userMobile, selectAddressText, homeTxt, typeTxt;

        public MyViewHolder(View view) {
            super(view);
            parentListItem = view.findViewById(R.id.parent_list_item);
            editAddressImg = view.findViewById(R.id.edit_address_image);
            deleteAddressImg = view.findViewById(R.id.delete_address_image);
            User_name = view.findViewById(R.id.User_name);
            homeTxt = view.findViewById(R.id.home_txt);
            typeTxt = view.findViewById(R.id.type_txt);
            User_address = view.findViewById(R.id.User_address);
            userMobile = view.findViewById(R.id.user_mobile);
            selectAddressText = view.findViewById(R.id.select_address_text);
        }
    }
}


