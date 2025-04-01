package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    protected float xCoordinate;
    protected float yCoordinate;

    protected int damage;
    protected int angle;

    protected float velocityX;
    protected float velocityY;

    protected static final float GRAVITY = -9.8f * 15;
    /**
     * Constructs an object of type Bomb.
     *
     * @param speed an int
     * @param damage an int
     */
    public Bomb(final int speed, final int damage, float xCoordinate, float yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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
    public void applyGravity(float delta) {
        // Apply gravity to velocity
        velocityY += GRAVITY * delta;
        // Update position using velocity
        yCoordinate += velocityY * delta;

        if (yCoordinate < 0) {
            yCoordinate = 0;
            velocityY = 0;
        }
    }

    public float getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(float yCoordinate) {
        this.yCoordinate = (int) yCoordinate;
    }

    public float getVelocityY() {
        return velocityY;
    }

    // Setter for velocityY (to allow subclasses to set initial velocity)
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }
}
