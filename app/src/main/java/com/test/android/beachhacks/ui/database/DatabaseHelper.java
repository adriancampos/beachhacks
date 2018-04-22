package com.test.android.beachhacks.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.android.beachhacks.ui.SavedItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "saveditems.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SAVEDITEM_NAME = "name";
    public static final String COLUMN_SAVEDITEM_IMAGE = "image";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SAVEDITEM_NAME + " TEXT NOT NULL, " +
                COLUMN_SAVEDITEM_IMAGE + " BLOB NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // NOT a migration. But eh, we'll deal with that when we have to.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void addItemToDB(SavedItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SAVEDITEM_NAME, item.name);
        values.put(COLUMN_SAVEDITEM_IMAGE, item.getImageBytes());

        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addItemToDBAsync(final SavedItem item) {
        new Thread() {
            @Override
            public void run() {
                addItemToDB(item);
            }
        }.start();
    }

    public List<SavedItem> loadFromDB() {
        List<SavedItem> items = new ArrayList<>();

        String query = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                SavedItem item = new SavedItem();

                item.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_SAVEDITEM_NAME)));

                item.setImageFromBytes(cursor.getBlob(cursor.getColumnIndex(COLUMN_SAVEDITEM_IMAGE)));
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return items;
    }

    public void loadFromDBAsync(final ItemsLoadedCallback itemsLoadedCallback) {
        new Thread() {
            @Override
            public void run() {
                itemsLoadedCallback.onItemsLoaded(loadFromDB());
            }
        }.start();
    }

    public interface ItemsLoadedCallback {
        void onItemsLoaded(List<SavedItem> items);
    }


    public void deleteItem(int id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "'");
    }

    // TODO Implement deleteItemAsync


    public void clearDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
        db.close();
    }

    public void clearDBAsync() {
        new Thread() {
            @Override
            public void run() {
                clearDB();
            }
        }.start();
    }

}
