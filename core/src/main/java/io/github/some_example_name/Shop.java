package io.github.some_example_name;

import java.util.HashMap;

public class Shop {
    HashMap<String, Integer> Inventory;
    String name;
    public Shop() {
        Inventory = new HashMap<>();
        //fill in the items and price
    }
    public int getPrice(final String item) {
        return Inventory.get(item);
    }
}
