package com.apollo.pharmacy.ocr.controller;

import com.apollo.pharmacy.ocr.interfaces.MyOffersListener;
import com.apollo.pharmacy.ocr.model.SubCategoryItemModel;
import com.apollo.pharmacy.ocr.utility.Constants;

import java.util.ArrayList;

public class MySearchController {
    MyOffersListener myOffersListener;

    public MySearchController(MyOffersListener listInterface) {
        myOffersListener = listInterface;
    }

    public void setFMCGListData(ArrayList<SubCategoryItemModel> subCategoryItemArr) {
        subCategoryItemArr.clear();
        SubCategoryItemModel subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Baby Care");
        subCategoryItemModel.setSubCategoryId(Constants.Baby_Care);
        subCategoryItemModel.setSelected(true);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Health Monitoring Devices");
        subCategoryItemModel.setSubCategoryId(Constants.Health_Monitoring_Devices);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("First Aid");
        subCategoryItemModel.setSubCategoryId(Constants.First_Aid);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Health Foods & Drinks");
        subCategoryItemModel.setSubCategoryId(Constants.Health_Foods_Drinks);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Beauty & Hygiene");
        subCategoryItemModel.setSubCategoryId(Constants.Beauty_Hygiene);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("OTC");
        subCategoryItemModel.setSubCategoryId(Constants.OTC);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("General Wellnes");
        subCategoryItemModel.setSubCategoryId(Constants.General_Wellness);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Mobility Aids & Rehabilitation");
        subCategoryItemModel.setSubCategoryId(Constants.Mobility_Aids_Rehabilitation);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Nutrition Supplement");
        subCategoryItemModel.setSubCategoryId(Constants.Nutrition_Supplement);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);
    }

    public void setPharmacyListData(ArrayList<SubCategoryItemModel> subCategoryItemArr) {
        subCategoryItemArr.clear();
        SubCategoryItemModel subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Anti allergic drugs");
        subCategoryItemModel.setSubCategoryId(Constants.Anti_allergic_drugs);
        subCategoryItemModel.setSelected(true);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Infections & Infestation");
        subCategoryItemModel.setSubCategoryId(Constants.Infections_Infestation);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("C.N.S Drugs");
        subCategoryItemModel.setSubCategoryId(Constants.CNS_Drugs);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Generic");
        subCategoryItemModel.setSubCategoryId(Constants.Generic);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Diabetics");
        subCategoryItemModel.setSubCategoryId(Constants.Diabetics);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Cardiology");
        subCategoryItemModel.setSubCategoryId(Constants.Cardiology);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Skin disorders");
        subCategoryItemModel.setSubCategoryId(Constants.Skin_disorders);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Gastro entrology");
        subCategoryItemModel.setSubCategoryId(Constants.Gastro_entrology);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);

        subCategoryItemModel = new SubCategoryItemModel();
        subCategoryItemModel.setSubCategoryName("Endocrine disorders");
        subCategoryItemModel.setSubCategoryId(Constants.Endocrine_disorders);
        subCategoryItemModel.setSelected(false);
        subCategoryItemArr.add(subCategoryItemModel);
    }
}
