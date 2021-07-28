package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.SerializedName;

public class GetImageRes {
    @SerializedName("imageUrl")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}
