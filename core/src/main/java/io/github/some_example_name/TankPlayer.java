package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankPlayer extends Game {
    private SpriteBatch batch;
    private Texture texture;
    private int x_coordinate;
    private int y_coordinate;
    private String colour;
    private int speed;
    private int money;
    private int fuel;
    private int HP;


    public TankPlayer(String colour, int speed, int money, int fuel, int HP) {
        this.colour = colour;
        this.speed = speed;
        this.money = money;
        this.fuel = fuel;
        this.HP = HP;
    }

    public void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x_coordinate -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x_coordinate += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y_coordinate += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y_coordinate -= speed;
        }
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("assets/tank.png"));
//        font = new BitmapFont();
    }

    public void render() {
        batch.begin();
        batch.draw(texture,
            x_coordinate,
            y_coordinate,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight());
        batch.end();
    }
}
