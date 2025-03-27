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
    private final int size;
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
        if (type.equals("mountain")) {
            grainTexture = new Texture(Gdx.files.internal("assets/bomb.png"));
        }
        if (type.equals("forest")) {
            grainTexture = new Texture(Gdx.files.internal("assets/green.png"));
        }
        numberOfGrains = Gdx.graphics.getWidth() / size; // Find the number of rectangles needed
        terrainX = new float[numberOfGrains];
        terrainY = new float[numberOfGrains];
        Random random = new Random();
        int tempXCoordinate = 0;
        final int minMidLine = 200;
        final int maxMidLine = 30;
        final int inflectionPoint = random.nextInt(5) + 10;
        int xAxis = random.nextInt(maxMidLine) + minMidLine;
        float[] copyTerrainY = SmoothRandomArray.generateSmoothTerrain(numberOfGrains,
            xAxis, inflectionPoint);
        for (int i = 0; i < numberOfGrains; i++) {
            terrainY[i] = copyTerrainY[i];
            tempXCoordinate += size;
            terrainX[i] = tempXCoordinate;
        }
    }
    /**
     * Draw the object to the screen.
     */
    public void render() {
        batch.begin();
        //create x amount of pillars
        for (int i = 0; i < numberOfGrains; i++) {
            batch.draw(grainTexture, terrainX[i], 0, (float) size, terrainY[i]);
        }
        batch.end();
    }
}
