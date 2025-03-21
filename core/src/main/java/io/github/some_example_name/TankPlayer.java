package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankPlayer extends Game implements Gravity {
    private SpriteBatch batch;
    private Texture tankTexture;
    private Texture canonTexture;
    private int xCoordinate = 1;
    private int yCoordinate = 1;
    private int width;
    private int height;
    private int speed;
    private int money;
    private int fuel;
    private int hp;

    /**
     * Constructs the tank.
     * @param width an int
     * @param height an int
     * @param speed an int
     * @param money an int
     * @param fuel an int
     * @param hp an int
     */
    public TankPlayer(final int width, final int height, final int speed,
                      final int money, final int fuel, final int hp) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.money = money;
        this.fuel = fuel;
        this.hp = hp;
    }
    private void moveTankLeft() {
        //left boarder check
        if (xCoordinate < 0) {
            xCoordinate = 0;
        } else {
            xCoordinate -= speed;
        }
    }
    private void moveTankRight() {
        // right boarder check
        if (xCoordinate > Gdx.graphics.getWidth() - width) {
            xCoordinate = Gdx.graphics.getWidth() - width;
        } else {
            xCoordinate += speed;
        }
    }

    /**
     * Check for user input and executes appropriate commands.
     */
    public void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveTankLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveTankRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yCoordinate += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yCoordinate -= speed;
        }
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
        tankTexture = new Texture(Gdx.files.internal("assets/tank.png"));
        canonTexture = new Texture(Gdx.files.internal("assets/canon.png"));

//        font = new BitmapFont();
    }

    public void render() {
        batch.begin();

        batch.draw(canonTexture, xCoordinate+20, yCoordinate+18,40, 10);
        batch.draw(tankTexture, xCoordinate, yCoordinate, width, height);

        batch.end();
    }

    @Override
    public void applyGravity() {
        final int gravitation = 9;
        if (yCoordinate > 0) {
            yCoordinate -= gravitation;
        } else if (yCoordinate < 0) {
            yCoordinate = 0;
        }
    }
}
