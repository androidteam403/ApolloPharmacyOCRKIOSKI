package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.Categorylist_Response;
import com.apollo.pharmacy.ocr.model.PortFolioModel;

public interface HomeListener {

    void onSuccessRedeemPoints(PortFolioModel model);

    void categoryListSuccessRes(Categorylist_Response response);

    void onFailureService(String message);
}
