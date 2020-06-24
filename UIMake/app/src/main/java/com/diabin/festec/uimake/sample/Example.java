package com.diabin.festec.uimake.sample;

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
public class Example {

    /**
     * An array of sample (dummy) items.
     */
    private List<ExampleItem> items = new ArrayList<ExampleItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    private Map<String, ExampleItem> item_map = new HashMap<String, ExampleItem>();

    private int count = 0;

    public void addItem(ExampleItem item) {
        items.add(item);
        item_map.put(String.valueOf(count), item);
        count++;
    }
}