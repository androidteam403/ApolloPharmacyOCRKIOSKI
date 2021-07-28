package com.apollo.pharmacy.ocr.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.activities.StorePickupActivity;
import com.apollo.pharmacy.ocr.interfaces.OnRecyclerItemClickListener;
import com.apollo.pharmacy.ocr.model.PharmacyLocatorCustomList;
import com.apollo.pharmacy.ocr.model.UserAddress;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PickFromStoreListAdapter extends RecyclerView.Adapter<PickFromStoreListAdapter.MyViewHolder> {
    private Context mContext;
    private List<PharmacyLocatorCustomList> storesArrayList;
    private ArrayList<PharmacyLocatorCustomList> tempStoresArrayList;
    private OnRecyclerItemClickListener listener;

    public PickFromStoreListAdapter(Context context, List<PharmacyLocatorCustomList> storesArrayList, OnRecyclerItemClickListener listener) {
        mContext = context;
        this.storesArrayList = storesArrayList;
        this.tempStoresArrayList = new ArrayList<PharmacyLocatorCustomList>();
        this.tempStoresArrayList.addAll(storesArrayList);
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView storeaddress, contactnumber_textview, selectAddressText;
        private Button direction_button;

        public MyViewHolder(View view) {
            super(view);
            storeaddress = view.findViewById(R.id.locate_pharmacy_textview);
            selectAddressText = view.findViewById(R.id.select_address_text);
            contactnumber_textview = view.findViewById(R.id.contactnumber_textview);
            direction_button = view.findViewById(R.id.direction_button);
        }
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pick_from_store_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        PharmacyLocatorCustomList data = storesArrayList.get(position);
        holder.storeaddress.setText(data.getStoreaddress() + "," + data.getStoredistance() + " away");
        if (!TextUtils.isEmpty(data.getStorenumber())) {
            holder.contactnumber_textview.setVisibility(View.VISIBLE);
            holder.contactnumber_textview.setText("Contact Number :" + data.getStorenumber());
        } else {
            holder.contactnumber_textview.setVisibility(View.GONE);
            holder.contactnumber_textview.setText("");
        }

        if (data.isSelected()) {
            holder.selectAddressText.setBackgroundResource(R.drawable.lang_selection_btn_bg);
            holder.selectAddressText.setText(mContext.getResources().getString(R.string.label_selected));
            holder.selectAddressText.setTextColor(mContext.getResources().getColor(R.color.colorBlack));

            UserAddress userAddress = new UserAddress();
            userAddress.setName("Apollo Pharmacy");
            userAddress.setAddress1(data.getStoreaddress());
            userAddress.setCity(data.getStorecity());
            userAddress.setState(data.getStorestate());
            userAddress.setMobile(data.getStorenumber());
            userAddress.setPincode(data.getStorepincode());
            SessionManager.INSTANCE.setUseraddress(userAddress);
            SessionManager.INSTANCE.setSiteid(data.getStoreid());
        } else {
            holder.selectAddressText.setText(mContext.getResources().getString(R.string.label_select));
            holder.selectAddressText.setBackgroundResource(R.drawable.lang_selection_btn_bg_gray);
            holder.selectAddressText.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }

        holder.selectAddressText.setOnClickListener(view -> {
            if (listener != null) {
                listener.onViewClick(position);
            }
        });

        holder.direction_button.setOnClickListener(view -> {
            if (mContext instanceof StorePickupActivity) {
                ((StorePickupActivity) mContext).updatedmaps(Double.parseDouble(storesArrayList.get(position).getStorelatitude()), Double.valueOf(storesArrayList.get(position).getStorelongtude()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return storesArrayList.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        storesArrayList.clear();
        if (charText.length() == 0) {
            storesArrayList.addAll(tempStoresArrayList);
        } else {
            for (PharmacyLocatorCustomList wp : tempStoresArrayList) {
                if (charText.length() >= 4 && charText.contains("[a-zA-Z]+") == true) {
                    if (wp.getStorepincode().toLowerCase(Locale.getDefault()).contains(charText)) {
                        storesArrayList.add(wp);
                    }
                } else {
                    if (wp.getStoreaddress().toLowerCase(Locale.getDefault()).contains(charText)) {
                        storesArrayList.add(wp);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
