package com.diabin.festec.uimake.sample;

import android.graphics.drawable.Drawable;

/**
 * A dummy item representing a piece of content.
 */
public class ExampleItem {
    public String itemNumber;
    public String itemNumber2;
    public Drawable image;

    public ExampleItem(String itemNumber, String itemNumber2, Drawable image) {
        this.itemNumber = itemNumber;
        this.itemNumber2 = itemNumber2;
        this.image = image;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemNumber2() {
        return itemNumber2;
    }

    public void setItemNumber2(String itemNumber2) {
        this.itemNumber2 = itemNumber2;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}