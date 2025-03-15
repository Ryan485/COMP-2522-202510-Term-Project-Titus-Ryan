package io.github.some_example_name;

public abstract class Weapon {
    protected int speed, damage;

    public Weapon(int speed, int damage) {
        this.speed = speed;
        this.damage = damage;
    }
    abstract public void setDamage(int damage);
    abstract public void setSpeed(int speed);
}
