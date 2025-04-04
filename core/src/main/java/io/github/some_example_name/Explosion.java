package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion {
    private final Texture explosionTexture;
    private float x, y;
    private float elapsedTime;
    private final float duration = 1.0f; // lasts 1 second

    public Explosion(float x, float y) {
        this.x = x;
        this.y = y;
        explosionTexture = new Texture(Gdx.files.internal("assets/explosion.png")); // ensure this asset exists
        elapsedTime = 0;
    }

    public void update(float delta) {
        elapsedTime += delta;
    }

    public void render(SpriteBatch batch) {
        if (elapsedTime < duration) {
            // Scale the explosion as it progresses for a nice visual effect
            float scale = 1.0f + (elapsedTime / duration) * 2;
            float width = explosionTexture.getWidth() * scale;
            float height = explosionTexture.getHeight() * scale;
            batch.draw(explosionTexture, x - width/2, y - height/2, width, height);
        }
    }


    public boolean isFinished() {
        return elapsedTime >= duration;
    }

    public void dispose() {
        explosionTexture.dispose();
    }
}
