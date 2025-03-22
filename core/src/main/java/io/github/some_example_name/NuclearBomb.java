package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class NuclearBomb extends Bomb{
    private int damageRadius;
    private int radius;
    private Texture nuclearBombtexture;
    public NuclearBomb(final int damageRadius, final int radius, final int speed, final int damage) {
        super(speed, damage);
        this.damageRadius = damageRadius;
        this.radius = radius;
    }
    public void setDamageRadius(final int damageRadius) {
        this.damageRadius = damageRadius;
    }
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    public void setDamage(final int damage) {
        this.damage = damage;
    }
    public void setRadius(final int radius) {
        this.radius = radius;
    }
}
