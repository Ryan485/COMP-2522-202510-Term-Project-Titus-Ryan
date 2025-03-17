package io.github.some_example_name;

public class Shield{
    private int durability;
    private Shield(final int durability) {
        this.durability = durability;
    }
    private int getDurability() {
        return durability;
    }
    private void damageReceive(final int damage) {
        durability -= damage;
    }
}
