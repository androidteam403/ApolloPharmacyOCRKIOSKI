package com.apollo.pharmacy.ocr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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

import static com.apollo.pharmacy.ocr.utility.Constants.getContext;

public class MyOfersAdapterNew extends RecyclerView.Adapter<MyOfersAdapterNew.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<UpCellCrossCellResponse.Crossselling> crosssellingList;
    CartCountListener cartCountListener;
    private List<OCRToDigitalMedicineResponse> datalist;

    public MyOfersAdapterNew(Context context, Activity activity, List<UpCellCrossCellResponse.Crossselling> crosssellingList, CartCountListener cartCountListener) {
        this.context = context;
        this.activity = activity;
        this.crosssellingList = crosssellingList;
        this.cartCountListener = cartCountListener;
        datalist = new ArrayList<>();
        if (null != SessionManager.INSTANCE.getDataList()) {
            datalist = SessionManager.INSTANCE.getDataList();
        }
    }

    @NonNull
    @Override
    public MyOfersAdapterNew.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewYourOffersBinding viewYourOffersBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.view_your_offers, parent, false);
        return new MyOfersAdapterNew.ViewHolder(viewYourOffersBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyOfersAdapterNew.ViewHolder holder, int position) {
        UpCellCrossCellResponse.Crossselling crossselling = crosssellingList.get(position);
        holder.viewYourOffersBinding.textviewProductName.setText(crossselling.getItemname());

        holder.viewYourOffersBinding.itemAddtoCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(context, crossselling.getItemid());
                itemBatchSelectionDilaog.setTitle(crossselling.getItemname());

                itemBatchSelectionDilaog.setUnitIncreaseListener(view1 -> {

                    if (itemBatchSelectionDilaog.getItemBatchSelectionDataQty() != null && Float.parseFloat(itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getQOH()) >= Float.parseFloat(itemBatchSelectionDilaog.getQtyCount().toString()) + 1) {
                        int qty = Integer.parseInt(itemBatchSelectionDilaog.getQtyCount().toString());
                        qty = qty + 1;
                        String product_sku = crosssellingList.get(position).getItemid();
                        int count = 0;
                        for (OCRToDigitalMedicineResponse data : datalist) {
                            if (product_sku.equalsIgnoreCase(data.getArtCode())) {
                                datalist.remove(count);
                                break;
                            }
                            count = count + 1;
                        }

                        OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                        object1.setArtName(crosssellingList.get(position).getItemname());
                        object1.setArtCode(crosssellingList.get(position).getItemid());
                        crosssellingList.get(position).setOfferPrice("10");
                        if (null != crosssellingList.get(position).getOfferPrice() && crosssellingList.get(position).getOfferPrice().length() > 0) {
                            object1.setArtprice(crosssellingList.get(position).getOfferPrice());
                        } else {
                            object1.setArtprice(String.valueOf(crosssellingList.get(position).getOfferPrice()));
                        }
                        object1.setMou("");
                        object1.setQty(qty);
                        object1.setContainer("Strip");
                        datalist.add(object1);
                        itemBatchSelectionDilaog.setQtyCount(String.valueOf(qty));
                    } else {
                        Toast.makeText(activity, "Selected quantity is not available in batch", Toast.LENGTH_SHORT).show();
                    }


                });

                itemBatchSelectionDilaog.setUnitDecreaseListener(view2 -> {
                    int qty = Integer.parseInt(itemBatchSelectionDilaog.getQtyCount().toString());
                    if (qty > 1) {
                        qty = qty - 1;
                        int count = 0;
                        String product_code = crosssellingList.get(position).getItemid();
                        for (OCRToDigitalMedicineResponse data : datalist) {
                            if (product_code.equalsIgnoreCase(data.getArtCode())) {
                                datalist.remove(count);
                                break;
                            }
                            count = count + 1;
                        }
                        OCRToDigitalMedicineResponse object1 = new OCRToDigitalMedicineResponse();
                        object1.setArtName(crosssellingList.get(position).getItemname());
                        object1.setArtCode(crosssellingList.get(position).getItemid());
                        crosssellingList.get(position).setOfferPrice("10");
                        if (null != crosssellingList.get(position).getOfferPrice() && crosssellingList.get(position).getOfferPrice().length() > 0) {
                            object1.setArtprice(crosssellingList.get(position).getOfferPrice());
                        } else {
                            object1.setArtprice(String.valueOf(crosssellingList.get(position).getOfferPrice()));
                        }
                        object1.setMou("");
                        object1.setQty(qty);
                        object1.setContainer("Strip");
                        datalist.add(object1);

//                        SessionManager.INSTANCE.setDataList(datalist);
                        itemBatchSelectionDilaog.setQtyCount(String.valueOf(qty));
//                        if (context instanceof MyCartActivity) {
//                            ((MyCartActivity) context).message_string = "cartupdate";
//                        }
                    } else {
                        int qty1 = Integer.parseInt(itemBatchSelectionDilaog.getQtyCount().toString());
                        qty1 = qty1 - 1;
                        int count = 0;
                        String product_code = crosssellingList.get(position).getItemid();
                        for (OCRToDigitalMedicineResponse data : datalist) {
                            if (product_code.equalsIgnoreCase(data.getArtCode())) {
                                datalist.remove(count);
                                break;
                            }
                            count = count + 1;
                        }
                        SessionManager.INSTANCE.setDataList(datalist);
//                        itemAddToCartLayout.setVisibility(View.VISIBLE);
//                        inc_dec_card.setVisibility(View.GONE);
//                        if (itemAddToCartLayout.getVisibility() == View.VISIBLE) {
//                            cartCountListener.cartCount(datalist.size());
//                        }
//                        if (context instanceof MyCartActivity) {
//                            ((MyCartActivity) context).message_string = "cartdelete";
//                            ((MyCartActivity) context).delete_item_sku = product_code;
//                        }
                    }
//                    if (context instanceof MyCartActivity) {
//                        ((MyCartActivity) context).updatecartlist();
//                    }

                });

                itemBatchSelectionDilaog.setPositiveListener(view3 -> {
                    itemBatchSelectionDilaog.getBatchAvilableData();
                    if (itemBatchSelectionDilaog.getBatchAvilableData() != null && itemBatchSelectionDilaog.getBatchAvilableData().getBatchList() != null && itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().size() > 0) {
                        Intent intent = new Intent("cardReceiver");
                        intent.putExtra("message", "Addtocart");
                        intent.putExtra("product_sku", crossselling.getItemid());
                        intent.putExtra("product_name", crossselling.getItemname());
                        intent.putExtra("product_quantyty", itemBatchSelectionDilaog.getQtyCount().toString());
                        intent.putExtra("product_price", String.valueOf(itemBatchSelectionDilaog.getItemProice()));//String.valueOf(medicine.getPrice())
                        // intent.putExtra("product_container", product_container);
                        intent.putExtra("medicineType", "FMCG");
                        intent.putExtra("product_mou", "");
//                    intent.putExtra("product_position", String.valueOf(position));
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//                if (addToCartCallBackData!=null) {
//                    addToCartCallBackData.addToCartCallBack();
//                }
                        itemBatchSelectionDilaog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Product Out of Stock", Toast.LENGTH_SHORT).show();
                        itemBatchSelectionDilaog.dismiss();
                    }
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
        return crosssellingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewYourOffersBinding viewYourOffersBinding;

        public ViewHolder(@NonNull ViewYourOffersBinding viewYourOffersBinding) {
            super(viewYourOffersBinding.getRoot());
            this.viewYourOffersBinding = viewYourOffersBinding;
        }
    }

}