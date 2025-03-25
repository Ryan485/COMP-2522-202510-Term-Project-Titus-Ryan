package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

/**
 * An interactable part of the map.
 * @author Titus
 * @author Ryan
 * @version 2015
 */
public class Grain extends Game {
    private SpriteBatch batch;
    private Texture grainTexture;
    private final String type;
    private int size;
    private int xCoordinate = 1;
    private int numberOfGrains;
    private float[] terrainX;
    private float[] terrainY;

    /**
     * Construct the ground where the tanks will be on.
     * @param type a String
     * @param size an int
     */
    public Grain(final String type, final int size) {
        this.type = type;
        this.size = size;
    }
    /**
     * Create all objects for a Grain.
     */
    public void create() {
        batch = new SpriteBatch();
//        grainTexture = new Texture(Gdx.files.internal("assets/green.png"));

        if (type.equals("mountain")) {
            grainTexture = new Texture(Gdx.files.internal("assets/bomb.png"));
        }
        if (type.equals("forest")) {
            grainTexture = new Texture(Gdx.files.internal("assets/green.png"));
        }
        Random random = new Random();
        numberOfGrains = Gdx.graphics.getWidth() / size;
        terrainX = new float[numberOfGrains];
        terrainY = new float[numberOfGrains];
        int tempXCoordinate = 0;
        final int minMidLine = 100;
        final int maxMidLine = 30;
        int xAxis = random.nextInt(maxMidLine) + minMidLine;
        int amplitude;
        final int amplitudeMaxRange = 10;
        int frequency;
        final int frequencyMinRange = 30;
        final int frequencyMaxRange = 10;
        final int numberOfInflection = 5;
        int[] inflectionPts = new int[numberOfInflection];

        // Random inflection points
        for (int i = 0; i < numberOfInflection; i++) {
            inflectionPts[i] = random.nextInt(Gdx.graphics.getWidth());
        }
        // Creat the sin wave
        for (int i = 0; i < numberOfGrains; i++) {
            amplitude = random.nextInt(amplitudeMaxRange) + xAxis;
            frequency = random.nextInt(frequencyMaxRange) + frequencyMinRange;
            System.out.println(frequency);
            terrainX[i] = tempXCoordinate;
            terrainY[i] = (float) (30 * Math.sin(terrainX[i] / 30) + xAxis);
            tempXCoordinate += size;
        }
    }
    /**
     * Draw the object to the screen.
     */
    public void render() {


        batch.begin();
        //create x amount of pillars
        for (int i = 0; i < numberOfGrains; i++) {
            batch.draw(grainTexture, terrainX[i], 0, (float)size, terrainY[i]);
        }
        batch.end();
    }
}
