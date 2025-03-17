package io.github.some_example_name;

import java.util.HashMap;

public class Shop {
    //<name,[Price,Qty]>
    private final HashMap<String, Integer[]> Inventory;
    private String name;
    private Shop() {
        Inventory = new HashMap<>();
        //fill in the items and price
    }
    private boolean isAvailable(final String item) {
        return Inventory.get(item)[1] > 0;
    }
    public int getPrice(final String item) {
        return Inventory.get(item)[0];
    }
}
