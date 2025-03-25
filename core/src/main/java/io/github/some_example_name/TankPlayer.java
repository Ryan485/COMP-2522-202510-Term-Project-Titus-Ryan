package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

public class TankPlayer extends Game implements Gravity {
    private SpriteBatch batch;
//    private Sprite sprite;
    private Texture tankTexture;
    private Texture canonTexture;
    private Texture bombTexture;
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
    private ArrayList<NuclearBomb> bombs;
    private boolean useNuclearBomb = true;

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
        this.bombs = new ArrayList<>();
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
        final int lowestLeftRotation = 185;
        if (rotation < lowestLeftRotation) {
            rotation += rotationSpeed;
        }
    }

    private void moveCanonRight() {
        final int lowestRightRotation = -5;
        if (rotation > lowestRightRotation) {
            rotation -= rotationSpeed;
        }
    }
    private void switchBombLeft(){
        //...Code...
    }
    private void switchBombRight() {
        //...Code...
    }
    private void fire() {
        float cannonLength = 40f;
        float cannonPivotX = 0f;
        float cannonPivotY = 5f;
        float cannonX = xCoordinate + 25 + cannonPivotX + (float) Math.cos(Math.toRadians(rotation)) * cannonLength;
        float cannonY = yCoordinate + 18 + cannonPivotY + (float) Math.sin(Math.toRadians(rotation)) * cannonLength;

        if (useNuclearBomb) {
            NuclearBomb bomb = new NuclearBomb(50, 20, 300, 100, cannonX, cannonY, rotation);
            bomb.applyGravity();
            bombs.add(bomb);
        }
    }

    /**
     * Check for user input and executes appropriate commands.
     * Tank has:
     * Move tank left and right: A, D
     * Move canon up and down: W, S
     * Fire: SpaceBar
     * Weapon switch: Q, E
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            fire();
        }
    }

    /**
     * Create all objects for a tank.
     */
    public void create() {
        batch = new SpriteBatch();
        tankTexture = new Texture(Gdx.files.internal("assets/tank.png"));
        canonTexture = new Texture(Gdx.files.internal("assets/canon.png"));
        bombTexture = new Texture(Gdx.files.internal("assets/bomb.png"));
//        sprite = new Sprite(canonTexture);
//        font = new BitmapFont();
    }

    /**
     * Draw the objects to the screen.
     */
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        Iterator<NuclearBomb> iterator = bombs.iterator();
        while (iterator.hasNext()) {
            NuclearBomb bomb = iterator.next();
            bomb.update(delta);
            if (!bomb.isActive()) {
                iterator.remove();
            }
        }

//        sprite.setRotation(90);
//        sprite.setPosition(xCoordinate, yCoordinate);
        batch.begin();

        //Canon
        batch.draw(canonTexture, xCoordinate + 25, yCoordinate+18, 0,  5, 40, 10,
        1, 1, rotation, 0, 0, 500, 101, false, false);

        //Tank
        batch.draw(tankTexture, xCoordinate, yCoordinate, width, height);

        //Bomb
        batch.draw(canonTexture, xCoordinate + 25, yCoordinate + 18, 40, 10);
        for (NuclearBomb bomb : bombs) {
            bomb.applyGravity();
            bomb.render(batch);
        }

        batch.end();
    }

    /**
     * Make sure the tank obeys gravity.
     */
    public void applyGravity() {
        final int gravitation = 9;
        if (yCoordinate > 0) {
            yCoordinate -= gravitation;
        } else if (yCoordinate < 0) {
            yCoordinate = 0;
        }
    }

    public boolean isUseNuclearBomb() {
        return useNuclearBomb;
    }

    public void setUseNuclearBomb(boolean useNuclearBomb) {
        this.useNuclearBomb = useNuclearBomb;
    }

}
