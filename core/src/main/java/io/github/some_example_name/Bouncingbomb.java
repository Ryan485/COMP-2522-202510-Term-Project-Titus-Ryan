package io.github.some_example_name;

public class Bouncingbomb extends Weapon {
    private int damageRadius;
    private int radius;

    public Bouncingbomb(int damageRadius, int radius, int speed, int damage) {
        super(speed, damage);
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
