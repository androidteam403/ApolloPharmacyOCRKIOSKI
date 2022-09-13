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

public class MyOfersAdapterNew extends RecyclerView.Adapter<MyOfersAdapterNew.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<UpCellCrossCellResponse.Crossselling> crosssellingList;
    CartCountListener cartCountListener;
    private List<OCRToDigitalMedicineResponse> datalist;
    List<OCRToDigitalMedicineResponse> dummyDataList = new ArrayList<>();
    private float balanceQty;


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
                ItemBatchSelectionDilaog itemBatchSelectionDilaog = new ItemBatchSelectionDilaog(context.getApplicationContext(), crossselling.getItemid());

                itemBatchSelectionDilaog.show();

                itemBatchSelectionDilaog.setTitle(crossselling.getItemname());
                crossselling.setQty(1);

                itemBatchSelectionDilaog.setUnitIncreaseListener(view1 -> {
                    if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                        if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
                            crossselling.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount()) + 1);
                        } else {
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
                    itemBatchSelectionDilaog.getBatchAvilableData();

                    itemBatchSelectionDilaog.globalBatchListHandlings(crossselling.getItemname(), crossselling.getItemid(),
                            balanceQty, dummyDataList, activity, "FMCG");


//                    if (itemBatchSelectionDilaog.getBatchAvilableData() != null && itemBatchSelectionDilaog.getBatchAvilableData().getBatchList() != null && itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().size() > 0) {
//                        if (itemBatchSelectionDilaog.getQtyCount() != null && !itemBatchSelectionDilaog.getQtyCount().isEmpty()) {
//                            if (itemBatchSelectionDilaog.getTotalBatchQty() >= Float.parseFloat(itemBatchSelectionDilaog.getQtyCount())) {
//                                if (Float.parseFloat(itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getQOH()) >= Float.parseFloat(itemBatchSelectionDilaog.getQtyCount())) {
//                                    OCRToDigitalMedicineResponse data = new OCRToDigitalMedicineResponse();
//                                    data.setArtName(TextUtils.isEmpty(crossselling.getItemname()) ? "-" : crossselling.getItemname());
//                                    data.setArtCode(crossselling.getItemid() + "," + itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getBatchNo());
//                                    data.setBatchId(itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getBatchNo());
//                                    data.setArtprice(String.valueOf(itemBatchSelectionDilaog.getItemProice()));
//                                    data.setContainer("");
//                                    data.setQty(Integer.parseInt(itemBatchSelectionDilaog.getQtyCount().toString()));
//
//                                    for (OCRToDigitalMedicineResponse proQtyIncorg : SessionManager.INSTANCE.getDataList()) {
//                                        if (proQtyIncorg.getArtCode().equals(data.getArtCode())) {
//                                            data.setQty(proQtyIncorg.getQty() + Integer.parseInt(itemBatchSelectionDilaog.getQtyCount().toString()));
//                                        }
//                                    }
//                                    data.setMedicineType("FMCG");
//                                    dummyDataList.add(data);
//
//                                    if (null != SessionManager.INSTANCE.getDataList()) {
//                                        if (SessionManager.INSTANCE.getDataList().size() > 0) {
//                                            List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
//                                            tempCartItemList = SessionManager.INSTANCE.getDataList();
//                                            for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
//                                                boolean isItemEqual = false;
//                                                for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
//                                                    if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
//                                                        isItemEqual = true;
//                                                    }
//                                                }
//                                                if (!isItemEqual)
//                                                    dummyDataList.add(listItem);
//                                            }
//                                        }
//                                    }
//                                    SessionManager.INSTANCE.setDataList(dummyDataList);
//                                    Intent intent = new Intent("OrderhistoryCardReciver");
//                                    intent.putExtra("message", "OrderNow");
//                                    intent.putExtra("MedininesNames", new Gson().toJson(dummyDataList));
//                                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
//                                    itemBatchSelectionDilaog.dismiss();
//
//                                } else {
//                                    OCRToDigitalMedicineResponse data1 = new OCRToDigitalMedicineResponse();
//
//                                    data1.setArtName(TextUtils.isEmpty(crossselling.getItemname()) ? "-" : crossselling.getItemname());
//                                    data1.setArtCode(crossselling.getItemid() + "," + itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getBatchNo());
//                                    data1.setBatchId(itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getBatchNo());
//                                    data1.setArtprice(String.valueOf(itemBatchSelectionDilaog.getItemProice()));
//                                    data1.setContainer("");
//                                    data1.setQty((int) Float.parseFloat(itemBatchSelectionDilaog.getItemBatchSelectionDataQty().getQOH().toString()));
//                                    balanceQty = Float.parseFloat(itemBatchSelectionDilaog.getQtyCount()) - Float.parseFloat(String.valueOf(data1.getQty()));
//                                    data1.setMedicineType("FMCG");
//                                    itemBatchSelectionDilaog.getItemBatchSelectionDataQty().setBatchQtySelected(true);
//                                    dummyDataList.add(data1);
//
//
//                                    if (null != SessionManager.INSTANCE.getDataList()) {
//                                        if (SessionManager.INSTANCE.getDataList().size() > 0) {
//                                            List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
//                                            tempCartItemList = SessionManager.INSTANCE.getDataList();
//                                            for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
//                                                boolean isItemEqual = false;
//                                                for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
//                                                    if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
//                                                        isItemEqual = true;
//                                                    }
//                                                }
//                                                if (!isItemEqual)
//                                                    dummyDataList.add(listItem);
//                                            }
//                                        }
//                                    }
//
//                                    for (int i = 0; i < itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().size(); i++) {
//                                        if (balanceQty != 0) {
//                                            if (Float.parseFloat(itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getQOH()) >= balanceQty && !itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).isBatchQtySelected()) {
//                                                OCRToDigitalMedicineResponse data2 = new OCRToDigitalMedicineResponse();
//
//                                                data2.setArtName(TextUtils.isEmpty(crossselling.getItemname()) ? "-" : crossselling.getItemname());
//                                                data2.setArtCode(crossselling.getItemid() + "," + itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getBatchNo());
//                                                data2.setBatchId(itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getBatchNo());
//                                                data2.setArtprice(String.valueOf(itemBatchSelectionDilaog.getItemProice()));
//                                                data2.setContainer("");
//
//                                                if (balanceQty >= Float.parseFloat(itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getQOH())) {
//                                                    data2.setQty((int) Float.parseFloat(itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getQOH().toString()));
//                                                } else {
//                                                    data2.setQty((int) balanceQty);
//                                                    balanceQty = 0;
//                                                }
//                                                balanceQty = balanceQty - Float.parseFloat(String.valueOf(data2.getQty()));
//                                                data2.setMedicineType("FMCG");
//                                                dummyDataList.add(data2);
//
//
//                                                if (null != SessionManager.INSTANCE.getDataList()) {
//                                                    if (SessionManager.INSTANCE.getDataList().size() > 0) {
//                                                        List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
//                                                        tempCartItemList = SessionManager.INSTANCE.getDataList();
//                                                        for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
//                                                            boolean isItemEqual = false;
//                                                            for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
//                                                                if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
//                                                                    isItemEqual = true;
//                                                                }
//                                                            }
//                                                            if (!isItemEqual)
//                                                                dummyDataList.add(listItem);
//                                                        }
//                                                    }
//                                                }
//                                                break;
//                                            } else {
//                                                if (!itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).isBatchQtySelected() && balanceQty != 0) {
//                                                    OCRToDigitalMedicineResponse data3 = new OCRToDigitalMedicineResponse();
//                                                    data3.setArtName(TextUtils.isEmpty(crossselling.getItemname()) ? "-" : crossselling.getItemname());
//                                                    data3.setArtCode(crossselling.getItemid() + "," + itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getBatchNo());
//                                                    data3.setBatchId(itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getBatchNo());
//                                                    data3.setArtprice(String.valueOf(itemBatchSelectionDilaog.getItemProice()));
//                                                    data3.setContainer("");
//                                                    if (balanceQty >= Float.parseFloat(itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getQOH())) {
//                                                        data3.setQty((int) Float.parseFloat(itemBatchSelectionDilaog.getBatchAvilableData().getBatchList().get(i).getQOH().toString()));
//                                                    } else {
//                                                        data3.setQty((int) balanceQty);
//                                                        balanceQty = 0;
//                                                    }
//                                                    balanceQty = balanceQty - Float.parseFloat(String.valueOf(data3.getQty()));
//                                                    data3.setMedicineType("FMCG");
//                                                    dummyDataList.add(data3);
//
//
//                                                    if (null != SessionManager.INSTANCE.getDataList()) {
//                                                        if (SessionManager.INSTANCE.getDataList().size() > 0) {
//                                                            List<OCRToDigitalMedicineResponse> tempCartItemList = new ArrayList<>();
//                                                            tempCartItemList = SessionManager.INSTANCE.getDataList();
//                                                            for (OCRToDigitalMedicineResponse listItem : tempCartItemList) {
//                                                                boolean isItemEqual = false;
//                                                                for (OCRToDigitalMedicineResponse duplecateItem : dummyDataList) {
//                                                                    if (duplecateItem.getArtCode().equals(listItem.getArtCode())) {
//                                                                        isItemEqual = true;
//                                                                    }
//                                                                }
//                                                                if (!isItemEqual)
//                                                                    dummyDataList.add(listItem);
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//
//                                    SessionManager.INSTANCE.setDataList(dummyDataList);
//                                    Intent intent = new Intent("OrderhistoryCardReciver");
//                                    intent.putExtra("message", "OrderNow");
//                                    intent.putExtra("MedininesNames", new Gson().toJson(dummyDataList));
//                                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
//                                    itemBatchSelectionDilaog.dismiss();
//                                }
//
////                                Intent intent = new Intent("cardReceiver");
////                                intent.putExtra("message", "Addtocart");
////                                intent.putExtra("product_sku", crossselling.getItemid());
////                                intent.putExtra("product_name", crossselling.getItemname());
////                                intent.putExtra("product_quantyty", itemBatchSelectionDilaog.getQtyCount().toString());
////                                intent.putExtra("product_price", String.valueOf(itemBatchSelectionDilaog.getItemProice()));//String.valueOf(medicine.getPrice())
////                                intent.putExtra("medicineType", "FMCG");
////                                intent.putExtra("product_mou", "");
////                                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
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
                    itemBatchSelectionDilaog.dismiss();
                });
            }
        });
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