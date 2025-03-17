package io.github.some_example_name;

public abstract class Item {
    protected int price;
    protected String name;
    protected String itemID;

    public Item(int price, String name, String itemID) {
        this.price = price;
        this.name = name;
        this.itemID = itemID;
    }
}
