package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;

public interface OnItemClickListener {

    void onClickIncrement(int position);

    void onClickDecrement(int position);

    void onClickDelete(int position, OCRToDigitalMedicineResponse item);

    void onClickEdit(int position);

    void onItemClick(int position);

}
