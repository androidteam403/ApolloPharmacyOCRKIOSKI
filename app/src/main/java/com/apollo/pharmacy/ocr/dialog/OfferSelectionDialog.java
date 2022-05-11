package com.apollo.pharmacy.ocr.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.DialogOffersSelectionAdapter;
import com.apollo.pharmacy.ocr.databinding.DialogOffersSelcetionBinding;
import com.apollo.pharmacy.ocr.interfaces.MyOffersListener;
import com.apollo.pharmacy.ocr.model.AllOffersResponse;

import java.util.List;

public class OfferSelectionDialog implements DialogOffersSelectionAdapter.ContinueEnablingHandlings {
    private Dialog dialog;
    private DialogOffersSelcetionBinding offersSelcetionBinding;
    private DialogOffersSelectionAdapter adapter;
    Context context;
    List<AllOffersResponse.PromoItem> promoItemList;
    private AllOffersResponse.Datum offer;

    public OfferSelectionDialog(Context context, AllOffersResponse.Datum offer, List<AllOffersResponse.PromoItem> promoItemList, Activity activity, MyOffersListener myOffersListener) {
        this.context = context;
        this.promoItemList = promoItemList;
        dialog = new Dialog(context);
        this.offer = offer;
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        offersSelcetionBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_offers_selcetion, null, false);
        dialog.setContentView(offersSelcetionBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawableResource(R.color.transperent_black_color);
        dialog.setCancelable(false);

        offersSelcetionBinding.offerTittle.setText(offer.getPromoTitle());

        if (offer.getPromoItemSelectionType().equalsIgnoreCase("auto")) {
            offersSelcetionBinding.chooseAnySomeProducts.setVisibility(View.GONE);
            offersSelcetionBinding.selectClear.setVisibility(View.GONE);
            offersSelcetionBinding.clear.setVisibility(View.GONE);
        } else {
            offersSelcetionBinding.chooseAnySomeProducts.setVisibility(View.VISIBLE);
            offersSelcetionBinding.chooseAnySomeProducts.setText("Choose any " + offer.getPromoItemSelection() + " products");
            offersSelcetionBinding.selectClear.setVisibility(View.VISIBLE);
            offersSelcetionBinding.clear.setVisibility(View.VISIBLE);
        }
        adapter = new DialogOffersSelectionAdapter(activity, promoItemList, myOffersListener, offer, this);
        offersSelcetionBinding.selectionoffersRecycle.setLayoutManager(new GridLayoutManager(context, 4));

//        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        offersSelcetionBinding.selectionoffersRecycle.setLayoutManager(mLayoutManager2);
        offersSelcetionBinding.selectionoffersRecycle.setItemAnimator(new DefaultItemAnimator());
        offersSelcetionBinding.selectionoffersRecycle.setAdapter(adapter);
        offersSelcetionBinding.selectionoffersRecycle.setNestedScrollingEnabled(false);
        onClickClear();
    }

    private void onClickClear() {
        offersSelcetionBinding.clear.setOnClickListener(v -> {
            if (promoItemList != null && promoItemList.size() > 0) {
                for (int i = 0; i < promoItemList.size(); i++) {
                    promoItemList.get(i).setOfferCount(0);
                    promoItemList.get(i).setSelected(false);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private int screeSize() {
        int screenSize = context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        String toastMsg;

        if (screenSize == 4) {
            return 4;
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            toastMsg = "Large screen";
            return 4;
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            toastMsg = "Normal screen";
            return 3;
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            toastMsg = "Small screen";
            return 3;
        } else {
            return 4;
        }
    }


    public void setPositiveListener(View.OnClickListener okListener) {
        offersSelcetionBinding.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        offersSelcetionBinding.dialogButtonNO.setOnClickListener(okListener);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
//        alertDialogBoxBinding.title.setText(title);
    }

    @SuppressLint({"ResourceAsColor", "Range"})
    @Override
    public void continueHandlings(AllOffersResponse.PromoItem image, int offerCount) {
        if (offer.getPromoItemSelectionType().equals("auto")) {
            offersSelcetionBinding.dialogButtonOK.setAlpha(255);
            offersSelcetionBinding.dialogButtonOK.setClickable(true);
        } else {
            offersSelcetionBinding.selectedOffersCount.setText("Seleceted " + image.getOfferCount() + "/" + offer.getPromoItemSelection());
            if (image.getOfferCount() == offer.getPromoItemSelection()) {
//            offersSelcetionBinding.dialogButtonOK.setBackgroundColor(R.color.selected_text_color);
                offersSelcetionBinding.dialogButtonOK.setAlpha(255);
                offersSelcetionBinding.dialogButtonOK.setClickable(true);
            } else {
//            offersSelcetionBinding.dialogButtonOK.setBackgroundColor(R.color.selected_text_color);
                offersSelcetionBinding.dialogButtonOK.setAlpha((float) 0.4);
                offersSelcetionBinding.dialogButtonOK.setClickable(false);
            }
        }
    }
}
