package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

/**
 * A class that constitutes a nuclear bomb.
 *
 * @author Ryan Song
 * @version 2025
 */
public class NuclearBomb extends Bomb{
    private int damageRadius;
    private int radius;
    private final Texture nuclearBombtexture;
    private float x,y;   // float coordinates for smooth motion
    private float velocity_x, velocity_y;    // velocity components
    private boolean active;
    private static final float GRAVITY_FORMULA = -9.8f * 10;



    public NuclearBomb(final int damageRadius, final int radius, final int speed,
                       final int damage, float x, float y, int angle) {
        super(speed, damage);
        this.damageRadius = damageRadius;
        this.radius = radius;
        this.nuclearBombtexture = new Texture(Gdx.files.internal("assets/bomb.png"));
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.velocity_x = (float) Math.cos(Math.toRadians(angle)) * speed;
        this.velocity_y = (float) Math.sin(Math.toRadians(angle)) * speed;
        this.active = true;
    }

    /**
     * Update the bomb's position.
     *
     * @param delta
     */
    public void update(float delta) {
        if (!active) return;

        // update position with velocity
        x += velocity_x * delta;
        y += velocity_y * delta;

        velocity_y += GRAVITY_FORMULA * delta;

        if (y <= 0) {
            active = false;
            y = 0;
        }
    }

    public void setDamageRadius(final int damageRadius) {
        this.damageRadius = damageRadius;
    }

    @Override
    public void setSpeed(final int speed) {
        this.speed = speed;
        this.velocity_x = (float) Math.cos(Math.toRadians(angle)) * speed;
        this.velocity_y = (float) Math.sin(Math.toRadians(angle)) * speed;
    }

    @Override
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    public void setRadius(final int radius) {
        this.radius = radius;
    }

    public int getDamageRadius() {
        return damageRadius;
    }

    public void dispose() {
        nuclearBombtexture.dispose();
    }

    public boolean isActive() {
        return active;
    }

    public void render(SpriteBatch batch) {
        if (active) {
            batch.draw(nuclearBombtexture, x, y, radius * 2, radius * 2);
        }
    }

    public void applyGravity() {
    }
}
