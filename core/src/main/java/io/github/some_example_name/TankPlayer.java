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
    private Texture tankTexture;
    private Texture canonTexture;
    private float xCoordinate;
    private float yCoordinate;
    private final String control;
    private int width;
    private int height;
    private int speed;
    private int money;
    private int fuel;
    private static int hp;
    private int rotation = -1;
    private final int rotationSpeed = 1;
    private final ArrayList<NuclearBomb> bombs;
    private boolean useNuclearBomb = true;
    private final int offset = 25;
    private final float[] grainHeights;
    private final int acceptableIncline = 55;
    private final int futureSight = 5 + offset;
    private float inclineAngle = 0;
    private Grain grain; // store a reference to the terrain
    private final ArrayList<Explosion> explosions;


    /**
     * Constructs the tank.
     * @param xCoordinate the x coordinate of the tank
     * @param control the control identifier ("left" or "right")
     * @param width the width of the tank
     * @param height the height of the tank
     * @param speed the movement speed of the tank
     * @param money the starting money
     * @param fuel the starting fuel
     * @param hp the starting health points
     * @param grain the Grain instance representing the terrain
     */
    public TankPlayer(final int xCoordinate, final String control, final int width, final int height, final int speed,
                      final int money, final int fuel, final int hp, final Grain grain) {
        this.xCoordinate = xCoordinate;
        this.control = control;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.money = money;
        this.fuel = fuel;
        TankPlayer.hp = hp;
        this.bombs = new ArrayList<>();
        this.grain = grain; // set the terrain instance
        grainHeights = grain.getTerrainY();
        this.explosions = new ArrayList<>();

    }

    public static int getHp() {
        return hp;
    }

    private void moveTankLeft() {
        float futureY = grainHeights[(int)xCoordinate + offset - futureSight];
        float futureX = xCoordinate - futureSight;
        float deltaY = futureY - yCoordinate;
        float deltaX = futureX - xCoordinate;
        inclineAngle = (float) Math.toDegrees(Math.atan2(deltaY, deltaX));
        float deviation = futureY - yCoordinate;
        if (deviation < acceptableIncline && xCoordinate > 10) {
            xCoordinate -= speed;
        }
        if (inclineAngle > 90 || inclineAngle < -90) {
            inclineAngle += 180;
        }
    }

    private void moveTankRight() {
        float futureY = grainHeights[(int)xCoordinate + offset + futureSight];
        float futureX = xCoordinate + futureSight;
        float deltaY = futureY - yCoordinate;
        float deltaX = futureX - xCoordinate;
        inclineAngle = (float) Math.toDegrees(Math.atan2(deltaY, deltaX));
        if (futureY - yCoordinate < acceptableIncline && xCoordinate < Gdx.graphics.getWidth() - width) {
            xCoordinate += speed;
        }
        if (inclineAngle > 90 || inclineAngle < -90) {
            inclineAngle += 180;
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

    private void switchBombLeft() {
        //...Code...
    }

    private void switchBombRight() {
        //...Code...
    }

    private void fire() {
        float cannonLength = 40f;
        float cannonBaseX = xCoordinate + 25;
        float cannonBaseY = yCoordinate + 18;
        float bombAlignmentOffset = 30f;
        float cannonTipX = cannonBaseX + (float) Math.cos(Math.toRadians(rotation)) * cannonLength;
        float cannonTipY = cannonBaseY + (float) Math.sin(Math.toRadians(rotation)) * cannonLength - bombAlignmentOffset;
        if (useNuclearBomb) {
            final int damageRadius = 50;
            final int bombSize = 5;
            final int bombSpeed = 300;
            final int bombDamage = 100;
            NuclearBomb bomb = new NuclearBomb(damageRadius, bombSize, bombSpeed, bombDamage, cannonTipX, cannonTipY, rotation);
            bomb.applyGravity(0.016f);
            bombs.add(bomb);
        }
    }

    /**
     * Processes input for moving and firing the tank.
     */
    public void input() {
        if (control.equals("right")) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveTankLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moveTankRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                moveCanonLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                moveCanonRight();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                fire();
            }
        } else if (control.equals("left")) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                moveTankLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                moveTankRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                moveCanonLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                moveCanonRight();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                fire();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            switchBombLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            switchBombRight();
        }
    }

    /**
     * Creates textures and objects for the tank.
     */
    public void create() {
        batch = new SpriteBatch();
        if (control.equals("left")) {
            tankTexture = new Texture(Gdx.files.internal("assets/greenTank.png"));
        } else if (control.equals("right")) {
            tankTexture = new Texture(Gdx.files.internal("assets/redTank.png"));
        }
        canonTexture = new Texture(Gdx.files.internal("assets/canon.png"));
    }

    /**
     * Renders the tank, its cannon, and active bombs.
     * The bomb update method now receives the Grain instance so that when a bomb hits the terrain,
     * it can reduce the height accordingly.
     */
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        // Update bombs by passing the grain instance
        Iterator<NuclearBomb> iterator = bombs.iterator();
        Iterator<Explosion> explosionIterator = explosions.iterator();
        while (iterator.hasNext()) {
            NuclearBomb bomb = iterator.next();
            bomb.update(delta, grain);  // Pass the Grain reference here
            if (!bomb.isActive()) {
               iterator.remove();
            }
        }


        batch.begin();
        // Render canon
        batch.draw(canonTexture,
            xCoordinate + offset, yCoordinate + 18,
            0, 5,
            40, 10,
            1, 1,
            rotation,
            0, 0,
            canonTexture.getWidth(), canonTexture.getHeight(),
            false, false);
        // Render tank
        batch.draw(tankTexture,
            xCoordinate, yCoordinate,
            offset, 0,
            width, height,
            1, 1,
            inclineAngle,
            0, 0,
            tankTexture.getWidth(), tankTexture.getHeight(),
            false, false);
        // Render bombs
        for (NuclearBomb bomb : bombs) {
            bomb.render(batch);
        }
        batch.end();
    }

    /**
     * Ensures the tank is positioned on the terrain.
     */
    public void applyGravity() {
        if (yCoordinate != grainHeights[(int)xCoordinate + offset]) {
            yCoordinate = grainHeights[(int)xCoordinate + offset];
        }
    }

    public boolean isUseNuclearBomb() {
        return useNuclearBomb;
    }

    public void setUseNuclearBomb(boolean useNuclearBomb) {
        this.useNuclearBomb = useNuclearBomb;
    }

    // Additional getters for damage calculations
    public float getX() {
        return xCoordinate;
    }


    public float getY() {
        return yCoordinate;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        // Optionally, add logic for when the tank is destroyed
    }
}
