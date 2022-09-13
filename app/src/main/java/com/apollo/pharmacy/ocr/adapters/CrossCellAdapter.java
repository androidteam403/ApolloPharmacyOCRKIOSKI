package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.CrossCellAdapterBinding;
import com.apollo.pharmacy.ocr.dialog.ItemBatchSelectionDilaog;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;

import java.util.ArrayList;
import java.util.List;

public class CrossCellAdapter extends RecyclerView.Adapter<CrossCellAdapter.ViewHolder> {

    private Context activity;
    private List<ItemSearchResponse.Item> upsellList;
    boolean addToCarLayHandel;
    List<OCRToDigitalMedicineResponse> dummyDataList = new ArrayList<>();
    private float balanceQty;

    public CrossCellAdapter(Activity activity, List<ItemSearchResponse.Item> upsellList, boolean addToCarLayHandel) {
        this.activity = activity;
        this.upsellList = upsellList;
        this.addToCarLayHandel = addToCarLayHandel;
    }

    @NonNull
    @Override
    public CrossCellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CrossCellAdapterBinding crossCellAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cross_cell_adapter, parent, false);
        return new CrossCellAdapter.ViewHolder(crossCellAdapterBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CrossCellAdapter.ViewHolder holder, int position) {
        ItemSearchResponse.Item crossselling = upsellList.get(position);
        holder.adapterAccesariesItemsBinding.itemName.setText(crossselling.getDescription());
        if (addToCarLayHandel) {
            holder.adapterAccesariesItemsBinding.itemAddtoCartLayout.setVisibility(View.VISIBLE);
        }
        holder.adapterAccesariesItemsBinding.itemAddtoCartLayout.setOnClickListener(v -> {
            ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(activity, crossselling.getArtCode());
            itemBatchSelectionDilaog.setTitle(crossselling.getDescription());

            crossselling.setQty(1);
            itemBatchSelectionDilaog.setUnitIncreaseListener(view1 -> {
                if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                    if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                        crossselling.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount()) + 1);
                    }else {
                        crossselling.setQty(crossselling.getQty() + 1);
                    }
                    itemBatchSelectionDilaog.setQtyCount("" + crossselling.getQty());
                } else {
                    Toast.makeText(activity, "Please enter product quantity", Toast.LENGTH_SHORT).show();
                }

            });

            itemBatchSelectionDilaog.setUnitDecreaseListener(view2 -> {
                if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                    if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                        crossselling.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount()));
                    }
                    if (crossselling.getQty() > 1) {
                        crossselling.setQty(crossselling.getQty() - 1);
                        itemBatchSelectionDilaog.setQtyCount("" + crossselling.getQty());
                    }
                }
            });

            itemBatchSelectionDilaog.setPositiveListener(view3 -> {

                itemBatchSelectionDilaog.globalBatchListHandlings(crossselling.getDescription(), crossselling.getArtCode(),
                        balanceQty, dummyDataList, activity, crossselling.getMedicineType());


//                Intent intent = new Intent("cardReceiver");
//                intent.putExtra("message", "Addtocart");
//                intent.putExtra("product_sku", crossselling.getArtCode());
//                intent.putExtra("product_name", crossselling.getDescription());
//                intent.putExtra("product_quantyty", crossselling.getQty().toString());//txtQty.getText().toString()
//                intent.putExtra("product_price", String.valueOf(itemBatchSelectionDilaog.getItemProice()));//String.valueOf(medicine.getPrice())
//                // intent.putExtra("product_container", product_container);
//                intent.putExtra("medicineType", crossselling.getMedicineType());
////                intent.putExtra("product_mou", String.valueOf(crossselling.getMou()));
//                intent.putExtra("product_position", String.valueOf(position));
//                LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
////                if (addToCartCallBackData!=null) {
////                    addToCartCallBackData.addToCartCallBack();
////                }
//                itemBatchSelectionDilaog.dismiss();
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
        CrossCellAdapterBinding adapterAccesariesItemsBinding;

        public ViewHolder(@NonNull CrossCellAdapterBinding adapterAccesariesItemsBinding) {
            super(adapterAccesariesItemsBinding.getRoot());
            this.adapterAccesariesItemsBinding = adapterAccesariesItemsBinding;
        }
    }
}
