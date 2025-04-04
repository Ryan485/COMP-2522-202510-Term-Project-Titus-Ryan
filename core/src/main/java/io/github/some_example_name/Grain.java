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
        // To determine the color of the terrain
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
        final int minMidLine = 500;
        final int maxMidLine = 100;
        final int minFloorHeight = 100;
        final int inflectionPoint = random.nextInt(5) + 10;
        int xAxis = random.nextInt(maxMidLine) + minMidLine;
        float[] copyTerrainY = SmoothRandomArray.generateSmoothTerrain(numberOfGrains,
            xAxis, inflectionPoint);
        for (int i = 0; i < numberOfGrains; i++) {
            terrainY[i] = copyTerrainY[i] + minFloorHeight;
            tempXCoordinate += size;
            terrainX[i] = tempXCoordinate;
        }
    }

    public float getTerrainHeightAt(float x) {
        float pos = x / size;
        int index = (int) pos;
        int nextIndex = Math.min(index + 1, terrainY.length - 1);
        float fraction = pos - index;
        return terrainY[index] * (1 - fraction) + terrainY[nextIndex] * fraction;
    }

    public void createCrater(float bombX, float bombY, int craterRadius, float depth) {
        for (int i = 0; i < terrainX.length; i++) {
            // Calculate horizontal distance from the bomb impact
            float dx = terrainX[i] - bombX;
            // Use the current terrain height (pillar top) at this x position
            float dy = terrainY[i] - bombY;
            // Compute the radial distance using both x and y differences
            float distance = (float)Math.sqrt(dx * dx + dy * dy);
            if (distance < craterRadius) {
                // Use quadratic falloff for a smooth, rounded crater
                float effect = depth * (1 - (distance / craterRadius) * (distance / craterRadius));
                terrainY[i] = Math.max(terrainY[i] - effect, 0);
            }
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

    /**
     * A list of all x coordinates.
     * @return float array
     */
    public float[] getTerrainX() {
        return terrainX;
    }

    /**
     * a list of all y coordinates relative to x.
     * @return float array
     */
    public float[] getTerrainY() {
        return terrainY;
    }
}
