package com.test.android.beachhacks.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SavedItems {

    private final static String[] DUMMY_OBJECTNAMES = {"Cat", "Dog", "Person", "Animal"};

    /**
     * An array of sample (dummy) items.
     */
    public static final List<SavedItem> ITEMS = new ArrayList<SavedItem>();


    private static final int COUNT = 50;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(new SavedItem(null, DUMMY_OBJECTNAMES[i % DUMMY_OBJECTNAMES.length]));
        }
    }

    private static void addItem(SavedItem item) {
        ITEMS.add(item);
    }


}
