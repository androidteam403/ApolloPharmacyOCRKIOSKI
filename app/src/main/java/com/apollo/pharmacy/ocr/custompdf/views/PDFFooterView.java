package com.apollo.pharmacy.ocr.custompdf.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.apollo.pharmacy.ocr.custompdf.views.basic.PDFCustomView;
import com.apollo.pharmacy.ocr.custompdf.views.basic.PDFVerticalView;
import com.apollo.pharmacy.ocr.custompdf.views.basic.PDFView;


import java.io.Serializable;

public class PDFFooterView extends PDFVerticalView implements Serializable {

    public PDFFooterView(@NonNull Context context) {
        super(context);

        PDFCustomView emptySpaceView = new PDFCustomView(context, new View(context), 0, 0, 1);
        this.addView(emptySpaceView);
    }

    @Override
    public PDFFooterView addView(@NonNull PDFView viewToAdd) {
        super.addView(viewToAdd);
        return this;
    }

    @Override
    public LinearLayout getView() {
        return (LinearLayout) super.getView();
    }
}