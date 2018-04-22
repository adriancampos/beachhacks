package com.test.android.beachhacks.ui.database;

import com.test.android.beachhacks.ui.DummySavedItemHelper;
import com.test.android.beachhacks.ui.SavedItem;

import java.util.ArrayList;

public class DatabaseHelper {

    public void addItemToDB(SavedItem item) {
        // TODO
    }

    public void addItemToDBAsync(final SavedItem item) {
        new Thread() {
            @Override
            public void run() {
                addItemToDB(item);
            }
        }.start();
    }

    public ArrayList<SavedItem> loadFromDB() {
        // TODO

        ArrayList<SavedItem> items = new ArrayList<>();

        // Dummy data for now
        items.add(DummySavedItemHelper.getRandomSavedItem());
        items.add(DummySavedItemHelper.getRandomSavedItem());
        items.add(DummySavedItemHelper.getRandomSavedItem());
        items.add(DummySavedItemHelper.getRandomSavedItem());
        items.add(DummySavedItemHelper.getRandomSavedItem());

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
        void onItemsLoaded(ArrayList<SavedItem> items);
    }


    public void clearDB() {
        // TODO
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
