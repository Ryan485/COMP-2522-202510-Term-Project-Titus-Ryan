package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


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
    private final TextureRegion nuclearBombRegion;
    private float x,y;   // float coordinates for smooth motion
    private float velocity_x, velocity_y;    // velocity components
    private boolean active;
    private static final float GRAVITY_FORMULA = -9.8f * 10;



    public NuclearBomb(final int damageRadius, final int radius, final int speed,
                       final int damage, float x, float y, int angle) {
        super(speed, damage);
        this.damageRadius = damageRadius;
        this.radius = radius;
        this.nuclearBombtexture = new Texture(Gdx.files.internal("assets/nuclear_bomb.png"));
        this.nuclearBombRegion = new TextureRegion(nuclearBombtexture);
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
     * @param delta a float
     */
    public void update(final float delta) {
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
            batch.draw(
                nuclearBombRegion,
                x, y,
                radius * 7.5f, radius * 7.5f,     // origin of rotation (center)
                radius * 5, radius * 10,         // width and height
                1f, 1f,                           // scaleX, scaleY
                angle - 90                      // rotation angle (matches movement)
            );

        }
    }
}
