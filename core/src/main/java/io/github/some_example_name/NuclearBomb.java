package io.github.some_example_name;

public class NuclearBomb extends Weapon{
    private int damageRadius;
    private int radius;
    public NuclearBomb(int damageRadius, int radius, int speed, int damage) {
        super(speed, damage);
        this.damageRadius = damageRadius;
        this.radius = radius;
    }
    public void setDamageRadius(int damageRadius) {
        this.damageRadius = damageRadius;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
