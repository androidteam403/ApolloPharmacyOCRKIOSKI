package com.apollo.pharmacy.ocr.interfaces;

import com.apollo.pharmacy.ocr.model.AllOffersResponse;
import com.apollo.pharmacy.ocr.model.GetProductListResponse;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.Suggestion_Product;
import com.apollo.pharmacy.ocr.model.UpCellCrossCellResponse;

import java.util.HashMap;
import java.util.List;

public interface MyOffersListener {

    void onSearchFailure(String error);

    void onSuccessProductList(HashMap<String, GetProductListResponse> productList);

    void onSuccessProductListMainCategory(HashMap<String, GetProductListResponse> productList);

    void onFailure(String error);

    void onSearchSuggestionList(List<Suggestion_Product> m);

    void onNewSearchResult(List<Suggestion_Product> m);

    void onSuccessLoadMoreTrendingNow(GetProductListResponse body);

    void onFailureLoadMoreTrendingNow(String message);

    void onSuccessLoadMorePromotions(GetProductListResponse body);

    void onFailureLoadMorePromotions(String message);

    void onSuccessSearchItemApi(ItemSearchResponse itemSearchResponse);

    void onSuccessSearchUpcellCroscellApi(UpCellCrossCellResponse body);

    void onSearchFailureUpcellCroscell(String message);

    void onSuccessAllOffers(AllOffersResponse allOffersResponse);

    void onFailureAllOffers(String message);


}
