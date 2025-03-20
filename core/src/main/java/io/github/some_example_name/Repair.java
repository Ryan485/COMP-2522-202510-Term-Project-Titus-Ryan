package io.github.some_example_name;

public class Repair extends Item {
    private int repairAmount;
    private final int price;
    private final String name;


    public Repair(final int repairAmount, final int price, final String name) {
        super(price, name);
        this.repairAmount = repairAmount;
        this.price = price;
        this.name = name;
    }
}
