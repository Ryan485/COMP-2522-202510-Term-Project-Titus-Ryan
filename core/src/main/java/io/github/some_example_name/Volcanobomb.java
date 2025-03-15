package io.github.some_example_name;

public class Volcanobomb extends Weapon {
    int damageRadius;
    int radius;

    public Volcanobomb(int damageRadius, int radius, int speed, int damage) {
        super(speed, damage);
        this.damageRadius = damageRadius;
        this.radius = radius;
    }
}
