package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.GetImageRes;
import com.apollo.pharmacy.ocr.model.GetProductListResponse;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.OCRToDigitalMedicineResponse;
import com.apollo.pharmacy.ocr.model.UpCellCrossCellResponse;
import com.apollo.pharmacy.ocr.model.UserAddress;

import java.util.ArrayList;
import java.util.HashMap;

public interface MyCartListener {

    void onSuccessGetAddress(ArrayList<UserAddress> response);

    void onFailure(String message);

    void onSuccessProductList(HashMap<String, GetProductListResponse> productList);

    void onDeleteItemClicked(OCRToDigitalMedicineResponse ocrToDigitalMedicineResponse, int position);

    void onDeletedAddAllClicked();

    void onSuccessImageService(GetImageRes response);

    void onSuccessSearchItemApi(UpCellCrossCellResponse body);

    void onSearchFailure(String message);

    void onSuccessBarcodeItemApi(ItemSearchResponse itemSearchResponse);

    void onFailureBarcodeItemApi(String message);
}
