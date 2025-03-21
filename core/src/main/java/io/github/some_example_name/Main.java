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

    @Override
    public void create() {
        final String COLOUR = "green";
        final int SPEED = 10;
        int money = 0;
        int fuel = 20;
        int HP = 100;

        batch = new SpriteBatch();
        map = new BackgroundMap();
        map.create();

        tank = new TankPlayer(COLOUR, SPEED, money, fuel, HP);
        tank.create();
    }

    public void update() {
        tank.input();
    }

    @Override
    public void render() {
        update();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        map.render();
        tank.render();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

}
