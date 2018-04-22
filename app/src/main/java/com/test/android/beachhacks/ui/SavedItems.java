package com.test.android.beachhacks.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            addItem(new SavedItem(getRandomBitmap(), DUMMY_OBJECTNAMES[i % DUMMY_OBJECTNAMES.length]));
        }
    }

    private static void addItem(SavedItem item) {
        ITEMS.add(item);
    }


    // TODO Temporary method
    private static final int WIDTH = 1000;

    private static Bitmap getRandomBitmap() {
        Random r = new Random();

        Bitmap bitmap = Bitmap.createBitmap(WIDTH, WIDTH, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(getRandomColor());
        canvas.drawRect(0F, 0F, WIDTH, WIDTH, paint);


        // Draw some random circles
        for (int i = 0; i < r.nextInt(10) + 2; i++) {
            int radius = r.nextInt(WIDTH / 4) + (WIDTH / 16);
            int cx = r.nextInt(WIDTH);
            int cy = r.nextInt(WIDTH);

            paint.setColor(getRandomColor());

            canvas.drawCircle(cx, cy, radius, paint);
        }


        return bitmap;
    }

    private static int getRandomColor() {
        Random r = new Random();
        return Color.argb(255, r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

}
