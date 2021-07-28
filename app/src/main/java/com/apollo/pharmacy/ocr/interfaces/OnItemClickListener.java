package com.apollo.pharmacy.ocr.interfaces;

public interface OnItemClickListener {

    void onClickIncrement(int position);

    void onClickDecrement(int position);

    void onClickDelete(int position);

    void onClickEdit(int position);

    void onItemClick(int position);
}
