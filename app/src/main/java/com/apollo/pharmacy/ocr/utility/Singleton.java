package com.apollo.pharmacy.ocr.utility;

import com.apollo.pharmacy.ocr.model.Image;

import java.util.ArrayList;

public class Singleton {
    private static final Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

    public ArrayList<Image> imageArrayList = new ArrayList<com.apollo.pharmacy.ocr.model.Image>();
}
