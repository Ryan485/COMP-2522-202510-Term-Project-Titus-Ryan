package io.github.some_example_name;

/**
 * Bomb is an abstract class intended to be used
 * as a superclass to all types of bombs.
 *
 * @author Titus
 * @author Ryan
 * @version 2015
 */
public abstract class Bomb {
    /**
     * Stores how fast the bomb should go.
     */
    protected int speed;
    /**
     * Stores how much damage this bomb deals.
     */
    protected int damage;

    //need int angle.

    /**
     * Constructs an object of type Bomb.
     *
     * @param speed an int
     * @param damage an int
     */
    public Bomb(final int speed, final int damage) {
        this.speed = speed;
        this.damage = damage;
    }

    /**
     * Set the damage amount this bomb deals.
     * @param damage an int
     */
    public abstract void setDamage(int damage);

    /**
     * Set the speed of the bomb.
     * @param speed an int
     */
    public abstract void setSpeed(int speed);

    //Need getDamage();
}
