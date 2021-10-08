package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.CrossCellAdapterBinding;
import com.apollo.pharmacy.ocr.databinding.UpcellAdapterBinding;
import com.apollo.pharmacy.ocr.dialog.ItemBatchSelectionDilaog;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;

import java.util.List;

public class UpCellAdapter extends RecyclerView.Adapter<UpCellAdapter.ViewHolder> {

    private Activity activity;
    private List<ItemSearchResponse.Item> upsellList;

    public UpCellAdapter(Activity activity, List<ItemSearchResponse.Item> upsellList) {
        this.activity = activity;
        this.upsellList = upsellList;
    }

    @NonNull
    @Override
    public UpCellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CrossCellAdapterBinding upcellAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cross_cell_adapter, parent, false);
        return new UpCellAdapter.ViewHolder(upcellAdapterBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull UpCellAdapter.ViewHolder holder, int position) {
        ItemSearchResponse.Item upselling = upsellList.get(position);
        holder.upcellAdapterBinding.itemName.setText(upselling.getDescription());
        holder.upcellAdapterBinding.itemAddtoCartLayout.setOnClickListener(v -> {
            ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(activity, upselling.getArtCode());
            itemBatchSelectionDilaog.setTitle(upselling.getGenericName());
            itemBatchSelectionDilaog.setUnitIncreaseListener(view1 -> {
                upselling.setQty(upselling.getQty() + 1);
                itemBatchSelectionDilaog.setQtyCount("" + upselling.getQty());
            });

            itemBatchSelectionDilaog.setUnitDecreaseListener(view2 -> {
                if (upselling.getQty() > 1) {
                    upselling.setQty(upselling.getQty() - 1);
                    itemBatchSelectionDilaog.setQtyCount("" + upselling.getQty());
                }
            });

            itemBatchSelectionDilaog.setPositiveListener(view3 -> {

                Intent intent = new Intent("cardReceiver");
                intent.putExtra("message", "Addtocart");
                intent.putExtra("product_sku", upselling.getArtCode());
                intent.putExtra("product_name", upselling.getDescription());
                intent.putExtra("product_quantyty", upselling.getQty().toString());//txtQty.getText().toString()
                intent.putExtra("product_price", String.valueOf(itemBatchSelectionDilaog.getItemProice()));//String.valueOf(medicine.getPrice())
                // intent.putExtra("product_container", product_container);
                intent.putExtra("medicineType", upselling.getMedicineType());
//                intent.putExtra("product_mou", String.valueOf(crossselling.getMou()));
                intent.putExtra("product_position", String.valueOf(position));
                LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
//                if (addToCartCallBackData!=null) {
//                    addToCartCallBackData.addToCartCallBack();
//                }
                itemBatchSelectionDilaog.dismiss();
            });
            itemBatchSelectionDilaog.setNegativeListener(v1 -> {
                itemBatchSelectionDilaog.dismiss();
            });
            itemBatchSelectionDilaog.show();
        });
//        Picasso.with(activity).load(Uri.parse(String.valueOf(upsell.getImage()))).error(R.drawable.placeholder_image).into(holder.adapterAccesariesItemsBinding.image);
    }


    @Override
    public int getItemCount() {
        return upsellList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CrossCellAdapterBinding upcellAdapterBinding;

        public ViewHolder(@NonNull CrossCellAdapterBinding upcellAdapterBinding) {
            super(upcellAdapterBinding.getRoot());
            this.upcellAdapterBinding = upcellAdapterBinding;
        }
    }

}
