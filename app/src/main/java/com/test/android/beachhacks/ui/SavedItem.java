package com.test.android.beachhacks.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;

/**
 * Represents an item. Contains picture, name of item, translations?
 */
public class SavedItem {

    public String name;

    public Bitmap image;

    public int id;

    public SavedItem() {

    }

    public SavedItem(Bitmap image, String name) {
        this.image = image;
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }


    public byte[] getImageBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;
    }

    public void setImageFromBytes(byte[] bytes) {
        image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
