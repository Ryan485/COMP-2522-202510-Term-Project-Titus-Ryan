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
    private TankPlayer tank;
    private Grain grain;

    @Override
    public void create() {
        final int WIDTH = 50;
        final int HEIGHT = WIDTH / 2;
        final int SPEED = 50;
        int money = 0;
        int fuel = 20;
        int hp = 100;
        // Grain
        final String type = "forest";
        final int grainSize = 1;

        batch = new SpriteBatch();
        map = new BackgroundMap();
        map.create();
        grain = new Grain(type, grainSize);
        grain.create();
        tank = new TankPlayer(WIDTH, HEIGHT, SPEED, money, fuel, hp);
        tank.create();
    }

    public void update() {
        tank.input();
        tank.applyGravity();
    }

    @Override
    public void render() {
        update();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        map.render();
        grain.render();
        tank.render();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

}
