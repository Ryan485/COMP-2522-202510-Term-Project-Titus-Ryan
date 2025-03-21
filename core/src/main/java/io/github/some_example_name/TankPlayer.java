package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankPlayer extends Game implements Gravity {
    private SpriteBatch batch;
    private Sprite sprite;
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
    private int rotation = -1;
    private final int rotationSpeed = 1;

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
    private void moveCanonLeft() {
        if (rotation < 185) {
            rotation += rotationSpeed;
        }
    }
    private void moveCanonRight() {
        if (rotation > -5) {
            rotation -= rotationSpeed;
        }
    }
    private void switchBombLeft(){
        //...Code...
    }
    private void switchBombRight() {
        //...Code...
    }
    private void fire(){
        //...Code...
    }

    /**
     * Check for user input and executes appropriate commands.
     */
    public void input() {
        // Moving the tank
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveTankLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveTankRight();
        }
        // Moves the canon
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveCanonLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveCanonRight();
        }
        // Switch Weapon
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            switchBombLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            switchBombRight();
        }
        // Fire!
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fire();
        }
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
        tankTexture = new Texture(Gdx.files.internal("assets/tank.png"));
        canonTexture = new Texture(Gdx.files.internal("assets/canon.png"));
        sprite = new Sprite(canonTexture);
//        font = new BitmapFont();
    }

    public void render() {
//        sprite.setRotation(90);
//        sprite.setPosition(xCoordinate, yCoordinate);
        batch.begin();

        //Canon
        batch.draw(canonTexture, xCoordinate+25, yCoordinate+18, 0,  5, 40, 10,
        1, 1, rotation, 0, 0, 500, 101, false, false);
//        batch.draw(canonTexture, xCoordinate+20, yCoordinate+18,40, 10);
//        batch.draw(sprite, xCoordinate+20, yCoordinate+18, 40, 10);
//        sprite.draw(batch);

        //Tank
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
