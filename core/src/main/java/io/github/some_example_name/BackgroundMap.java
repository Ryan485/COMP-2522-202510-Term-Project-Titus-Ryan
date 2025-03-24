package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Background is a class tasked with displaying a background image
 * and setting the environment interactions.
 *
 * @author Titus
 * @author Ryan
 * @version 2015
 */
public class BackgroundMap extends Game {
    private String biome;
    private int wind;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    /**
     * Set the wind of the environment.
     * @param wind an int
     */
    public void setWind(final int wind) {
        this.wind = wind;
    }

    /**
     * gets the wind.
     * @return wind an int
     */
    public int getWind() {
        return wind;
    }

    /**
     * get the biome.
     * @return biome a string
     */
    public String getBiome() {
        return biome;
    }

    /**
     * create the texture for the background.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("assets/mountainBright.png"));
        // Create the display of: Power/HP, bomb type, name turn, wind, and biome
        font = new BitmapFont();
    }

    /**
     * draw the texture onto the screen.
     */
    public void render() {
        batch.begin();
        final int originPtr = 0;
        batch.draw(texture, originPtr, originPtr,
            Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }
}
