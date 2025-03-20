package io.github.some_example_name;

public abstract class Item {
    protected int price;
    protected String name;

    public Item(int price, String name) {
        this.price = price;
        this.name = name;
    }
}
