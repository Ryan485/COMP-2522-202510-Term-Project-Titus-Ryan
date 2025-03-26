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
    private int amplitude = 50;
    private int frequency = 50;

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
        numberOfGrains = Gdx.graphics.getWidth() / size; // Find the number of rectangles needed
        terrainX = new float[numberOfGrains];
        terrainY = new float[numberOfGrains];
        Random random = new Random();
        int tempXCoordinate = 0;
        final int minMidLine = 200;
        final int maxMidLine = 30;
        final int numberOfInflection = 5;
        int xAxis = random.nextInt(maxMidLine) + minMidLine;
//        private int amplitude = 1;
//        private int frequency = 1;
        int[] inflectionPts = new int[numberOfInflection];

        // Random inflection points
        for (int i = 0; i < numberOfInflection; i++) {
            inflectionPts[i] = random.nextInt(Gdx.graphics.getWidth());
        }
        // Creat the sin wave
        for (int i = 0; i < numberOfGrains; i++) {
            terrainX[i] = tempXCoordinate;
            System.out.println(frequency);

            terrainY[i] = (float) (amplitude * Math.sin(terrainX[i] / frequency) + xAxis);
//            for (int n = 0; n < numberOfInflection; n++) {
//                if (inflectionPts[n] == i) {
//                    amplitude = random.nextInt(50)+10;
//                    frequency = random.nextInt(90)+10;
//                }
//            }
            int maxima = amplitude + xAxis;
            int minima = xAxis - amplitude;
//            if (Math.sin(terrainX[i]) == 1 || Math.sin(terrainX[i]) == -1) {}
//            if (terrainY[i] == maxima || terrainY[i] == minima && 3 == random.nextInt(10)) {
//            if (1 == random.nextInt(100)) {
            if (maxima-1 <= terrainY[i]|| minima+1 >= terrainY[i]){
                Random rand = new Random();
                amplitude = rand.nextInt(50)+10;
                frequency = rand.nextInt(90)+10;
                System.out.println(frequency);
                System.out.println(amplitude);
            }
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
