package io.github.some_example_name;

/**
 * Bomb is an abstract class intended to be used
 * as a superclass to all types of bombs.
 *
 * @author Titus
 * @author Ryan
 * @version 2015
 */
public abstract class Bomb implements Gravity{
    /**
     * Stores how fast the bomb should go.
     */
    protected int speed;
    /**
     * Stores how much damage this bomb deals.
     */
    protected int damage;

    protected int angle;

    protected int yCoordinate;

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

    /**
     * Make sure the bomb obeys gravity.
     */
    public void applyGravity() {
        final int gravitation = 9;
        if (yCoordinate > 0) {
            yCoordinate -= gravitation;
        } else if (yCoordinate < 0) {
            yCoordinate = 0;
        }
    }
}
