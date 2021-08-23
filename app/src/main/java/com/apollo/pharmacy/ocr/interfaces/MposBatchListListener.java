package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.BatchList;

public interface MposBatchListListener {

    void setSuccessBatchList(BatchList batchList);

    void onFailureBatchList();
}
