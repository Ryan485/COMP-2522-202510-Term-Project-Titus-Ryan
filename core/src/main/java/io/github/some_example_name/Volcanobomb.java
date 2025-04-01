package io.github.some_example_name;

public class Volcanobomb extends Bomb {
    private int damageRadius;
    private int radius;

    public Volcanobomb(final int damageRadius, final int radius, final int speed, final int damage, final int xCoordinate, final int yCoordinate) {
        super(speed, damage, xCoordinate, yCoordinate);
        this.damageRadius = damageRadius;
        this.radius = radius;
    }

    public void setDamageRadius(final int damageRadius) {
        this.damageRadius = damageRadius;
    }
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    public void setDamage(final int damage) {
        this.damage = damage;
    }
    public void setRadius(final int radius) {
        this.radius = radius;
    }

}
