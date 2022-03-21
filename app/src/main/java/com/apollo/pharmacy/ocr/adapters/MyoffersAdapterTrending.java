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
import com.apollo.pharmacy.ocr.databinding.ViewYourOffersBinding;
import com.apollo.pharmacy.ocr.dialog.ItemBatchSelectionDilaog;
import com.apollo.pharmacy.ocr.interfaces.CartCountListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.UpCellCrossCellResponse;
import com.apollo.pharmacy.ocr.utility.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class MyoffersAdapterTrending extends RecyclerView.Adapter<MyoffersAdapterTrending.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<UpCellCrossCellResponse.Upselling> upsellingList;
    CartCountListener cartCountListener;
    private List<OCRToDigitalMedicineResponse> datalist;
    List<OCRToDigitalMedicineResponse> dummyDataList = new ArrayList<>();
    private float balanceQty;

    public MyoffersAdapterTrending(Context context, Activity activity, List<UpCellCrossCellResponse.Upselling> upsellingList, CartCountListener cartCountListener) {
        this.context = context;
        this.activity = activity;
        this.upsellingList = upsellingList;
        this.cartCountListener = cartCountListener;
        datalist = new ArrayList<>();
        if (null != SessionManager.INSTANCE.getDataList()) {
            datalist = SessionManager.INSTANCE.getDataList();
        }
    }

    @NonNull
    @Override
    public MyoffersAdapterTrending.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewYourOffersBinding viewYourOffersBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.view_your_offers, parent, false);
        return new MyoffersAdapterTrending.ViewHolder(viewYourOffersBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyoffersAdapterTrending.ViewHolder holder, int position) {
        UpCellCrossCellResponse.Upselling upselling = upsellingList.get(position);
        holder.viewYourOffersBinding.textviewProductName.setText(upselling.getItemname());
        holder.viewYourOffersBinding.itemAddtoCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(context, upselling.getItemid());
                itemBatchSelectionDilaog.setTitle(upselling.getItemname());
                upselling.setQty(1);

                itemBatchSelectionDilaog.setUnitIncreaseListener(view1 -> {
                    if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                        if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                            upselling.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount()) + 1);
                        }else {
                            upselling.setQty(upselling.getQty() + 1);
                        }
                        itemBatchSelectionDilaog.setQtyCount("" + upselling.getQty());
                    } else {
                        Toast.makeText(activity, "Please enter product quantity", Toast.LENGTH_SHORT).show();
                    }
                });

                itemBatchSelectionDilaog.setUnitDecreaseListener(view2 -> {
                    if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                        if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                            upselling.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount()));
                        }
                        if (upselling.getQty() > 1) {
                            upselling.setQty(upselling.getQty() - 1);
                            itemBatchSelectionDilaog.setQtyCount("" + upselling.getQty());
                        }
                    }
                });

                itemBatchSelectionDilaog.setPositiveListener(view3 -> {
                    itemBatchSelectionDilaog.getBatchAvilableData();

                    itemBatchSelectionDilaog.globalBatchListHandlings(upselling.getItemname(), upselling.getItemid(),
                            balanceQty, dummyDataList, context,"FMCG");

//                    if (itemBatchSelectionDilaog.getBatchAvilableData() != null && itemBatchSelectionDilaog.getBatchAvilableData().getBatchList() != null && itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().size() > 0) {
//                        if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {                             if (itemBatchSelectionDilaog.getItemBatchSelectionDataQty() != null && Float.parseFloat(itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getQOH()) >= Float.parseFloat(itemBatchSelectionDilaog.getQtyCount())) {
//                                Intent intent = new Intent("cardReceiver");
//                                intent.putExtra("message", "Addtocart");
//                                intent.putExtra("product_sku", upselling.getItemid());
//                                intent.putExtra("product_name", upselling.getItemname());
//                                intent.putExtra("product_quantyty", itemBatchSelectionDilaog.getQtyCount().toString());
//                                intent.putExtra("product_price", String.valueOf(itemBatchSelectionDilaog.getItemProice()));//String.valueOf(medicine.getPrice())
//                                // intent.putExtra("product_container", product_container);
//                                intent.putExtra("medicineType", "FMCG");
//                                intent.putExtra("product_mou", "");
////                    intent.putExtra("product_position", String.valueOf(position));
//                                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
////                if (addToCartCallBackData!=null) {
////                    addToCartCallBackData.addToCartCallBack();
////                }
//                                itemBatchSelectionDilaog.dismiss();
//                            } else {
//                                Toast.makeText(activity, "Selected quantity is not available in batch", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(activity, "Please enter product quantity", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(getContext(), "Product Out of Stock", Toast.LENGTH_SHORT).show();
//                        itemBatchSelectionDilaog.dismiss();
//                    }
                });
                itemBatchSelectionDilaog.setNegativeListener(v1 -> {
//                activityHomeBinding.transColorId.setVisibility(View.GONE);
                    itemBatchSelectionDilaog.dismiss();
                });
                itemBatchSelectionDilaog.show();
            }
        });

//        Picasso.with(activity).load(Uri.parse(String.valueOf(upsell.getImage()))).error(R.drawable.placeholder_image).into(holder.adapterAccesariesItemsBinding.image);
    }


    @Override
    public int getItemCount() {
        return upsellingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewYourOffersBinding viewYourOffersBinding;

        public ViewHolder(@NonNull ViewYourOffersBinding viewYourOffersBinding) {
            super(viewYourOffersBinding.getRoot());
            this.viewYourOffersBinding = viewYourOffersBinding;
        }
    }

}