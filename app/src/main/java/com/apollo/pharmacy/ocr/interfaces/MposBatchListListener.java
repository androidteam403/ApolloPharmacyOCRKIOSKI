package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.BatchListResponse;

public interface MposBatchListListener {

    void setSuccessBatchList(BatchListResponse batchListResponse);

    void onFailureBatchList();
}
