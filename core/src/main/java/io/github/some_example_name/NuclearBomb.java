package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class NuclearBomb extends Bomb{
    private int damageRadius;
    private int radius;
    private Texture nuclearBombtexture;
    private float x,y;   // float coordinates for smooth motion
    private float velocity_x, velocity_y;    // velocity components



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
}
