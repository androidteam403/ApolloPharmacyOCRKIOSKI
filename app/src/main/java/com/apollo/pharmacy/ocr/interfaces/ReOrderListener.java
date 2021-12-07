package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.BatchListResponse;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;

public interface ReOrderListener {

    void setSuccessBatchList(BatchListResponse body,int position,ItemSearchResponse.Item itemSearchData);

    void onSuccessSearchItemApi(ItemSearchResponse itemSearchResponse, int position);

    void onSearchFailure(String message);
}
