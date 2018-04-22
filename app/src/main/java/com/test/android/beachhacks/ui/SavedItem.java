package com.test.android.beachhacks.ui;

import android.graphics.Bitmap;

/**
 * Represents an item. Contains picture, name of item, translations?
 */
public class SavedItem {
    public final Bitmap image;
    public final String name;


    public SavedItem(Bitmap image, String name) {
        this.image = image;
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }


}
