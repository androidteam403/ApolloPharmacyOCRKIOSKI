package com.apollo.pharmacy.ocr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.adapters.CartDeletedListAdapter;
import com.apollo.pharmacy.ocr.interfaces.MyCartListener;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;

import java.util.List;

public class CartDeletedItemsDialog extends Dialog {
    private final Context context;
    private final List<OCRToDigitalMedicineResponse> deletedList;
    private final MyCartListener addDeletedItemsInterface;

    public CartDeletedItemsDialog(Context context, List<OCRToDigitalMedicineResponse> deletedList, MyCartListener addDeletedItemsInterface) {
        super(context);
        this.context = context;
        this.deletedList = deletedList;
        this.addDeletedItemsInterface = addDeletedItemsInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete_cart_items);
        setCanceledOnTouchOutside(false);

        RelativeLayout addAgainLayout = findViewById(R.id.add_again_layout);
        TextView removedItemsCount = findViewById(R.id.removed_items_count);
        RecyclerView cartDeleteRecyclerView = findViewById(R.id.cart_delete_recyclerView);
        LinearLayout addAllLayout = findViewById(R.id.add_all_layout);
        ImageView closeDialog = findViewById(R.id.close_dialog);

        closeDialog.setOnClickListener(v -> {
            dismiss();
        });

        cartDeleteRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        CartDeletedListAdapter deletedListAdaptor = new CartDeletedListAdapter(context, deletedList, addDeletedItemsInterface);
        cartDeleteRecyclerView.setAdapter(deletedListAdaptor);
        deletedListAdaptor.notifyDataSetChanged();

        addAllLayout.setOnClickListener(v -> {
            dismiss();
            if (addDeletedItemsInterface != null) {
                addDeletedItemsInterface.onDeletedAddAllClicked();
            }
        });
    }
}
