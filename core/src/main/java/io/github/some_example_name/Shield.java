package io.github.some_example_name;

public class Shield{
    private int durability;
    public Shield(final int durability) {
        this.durability = durability;
    }
    public int getDurability() {
        return durability;
    }
    public void damageReceive(final int damage) {
        durability -= damage;
    }
}
