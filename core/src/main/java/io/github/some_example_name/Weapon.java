package io.github.some_example_name;

public abstract class Weapon {
    int speed;
    int damage;

    public Weapon(int speed, int damage) {
        this.speed = speed;
        this.damage = damage;
    }
    abstract public int setDamage();
    abstract public void setSpeed();
}
