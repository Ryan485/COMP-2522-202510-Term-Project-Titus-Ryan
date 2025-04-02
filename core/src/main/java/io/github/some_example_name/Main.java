package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    private BackgroundMap map;
    private TankPlayer tankOne;
    private TankPlayer tankTwo;
    private Grain grain;

    @Override
    public void create() {
        final int width = 50;
        final int height = width / 2;
        final int speed = 1;
        int money = 0;
        int fuel = 20;
        int hp = 100;
        // Grain
        final String type = "forest";
        final int grainSize = 1;
        //All objects
        batch = new SpriteBatch();
        map = new BackgroundMap();
        map.create();
        grain = new Grain(type, grainSize);
        grain.create();
        tankOne = new TankPlayer(map.randomX("left"), "left", width, height, speed, money, fuel, hp, grain);
        tankOne.create();
        tankTwo = new TankPlayer(map.randomX("right"), "right", width, height, speed, money, fuel, hp, grain);
        tankTwo.create();
    }

    /**
     * Constant feedback and update of the object the objects.
     */
    public void update() {
        tankOne.input();
        tankOne.applyGravity();
        tankTwo.input();
        tankTwo.applyGravity();
    }

    /**
     * Renders the objects onto the screen.
     */
    @Override
    public void render() {
        update();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        map.render();
        grain.render();
        tankOne.render();
        tankTwo.render();
        batch.end();
    }

    /**
     * Safely ends the program.
     */
    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

}
