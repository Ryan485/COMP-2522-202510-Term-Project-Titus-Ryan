package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;


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
    private boolean active;



    public NuclearBomb(final int damageRadius, final int radius, final int speed,
                       final int damage, float xCoordinate, float yCoordinate, int angle) {
        super(speed, damage, xCoordinate, yCoordinate);
        this.damageRadius = damageRadius;
        this.radius = radius;
        this.nuclearBombtexture = new Texture(Gdx.files.internal("assets/nuclear_bomb.png"));
        this.nuclearBombRegion = new TextureRegion(nuclearBombtexture);
        this.angle = angle;
        this.velocityX = (float) Math.cos(Math.toRadians(angle)) * speed;
        this.velocityY = (float) Math.sin(Math.toRadians(angle)) * speed;
        this.active = true;
    }

    /**
     * Update the bomb's position.
     *
     * @param delta a float
     */
    public void update(final float delta, Grain grain) {
        if (!active) return;

        // Update bomb position
        xCoordinate += velocityX * delta;
        applyGravity(delta);
        angle = (int)(Math.atan2(velocityY, velocityX) * MathUtils.radiansToDegrees) - 1;

        // Check terrain height from the Grain instance
        float terrainHeight = grain.getTerrainHeightAt(xCoordinate);

        if (yCoordinate <= terrainHeight) {
            active = false;
            yCoordinate = terrainHeight; // Align bomb with the terrain

            // Reduce the terrain height by 100 at the impact point
            grain.createCrater(xCoordinate, yCoordinate, 100, 100);

            // Optionally, you can also trigger explosion effects or damage here
        }
    }


    public void setDamageRadius(final int damageRadius) {
        this.damageRadius = damageRadius;
    }

    @Override
    public void setSpeed(final int speed) {
        this.speed = speed;
        this.velocityX = (float) Math.cos(Math.toRadians(angle)) * speed;
        this.velocityY = (float) Math.sin(Math.toRadians(angle)) * speed;
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
                xCoordinate, yCoordinate,
                radius * 7.5f, radius * 7.5f,     // origin of rotation (center)
                radius * 5, radius * 8,         // width and height
                1f, 1f,                           // scaleX, scaleY
                angle - 90                      // rotation angle (matches movement)
            );

        }
    }

}
