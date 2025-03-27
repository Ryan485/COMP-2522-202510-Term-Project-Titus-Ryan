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
        int xAxis = random.nextInt(maxMidLine) + minMidLine;
        float copyterrainY[] = SmoothRandomArray.generateSmoothTerrain(numberOfGrains, xAxis, 10);
        for (int i = 0; i < numberOfGrains; i++) {
            terrainY[i] = copyterrainY[i];
            tempXCoordinate += size;
            terrainX[i] = tempXCoordinate;
        }
        //        for (int i = 0; i < numberOfGrains; i++) {
//            System.out.println(terrainY[i]);
//            terrainX[i] = xCoordinate;
//            tempXCoordinate += size;
//        }
        // Creat the sin wave
//        for (int i = 0; i < numberOfGrains; i++) {
//            terrainX[i] = tempXCoordinate;
//
//            terrainY[i] = (float) (30 * Math.sin(terrainX[i] / frequency)) + xAxis;
//            int maxima = amplitude + xAxis;
//            int minima = xAxis - amplitude;
////            if (Math.sin(terrainX[i]) == 1 || Math.sin(terrainX[i]) == -1) {}
////            if (terrainY[i] == maxima || terrainY[i] == minima && 3 == random.nextInt(10)) {
////            if (1 == random.nextInt(100)) {
//            if (maxima-1 <= terrainY[i] || minima+1 >= terrainY[i]){
//                Random rand = new Random();
//                amplitude = rand.nextInt(50)+10;
//                frequency = rand.nextInt(90)+10;
//                System.out.println("frequency = " + frequency);
//                System.out.println("amplitude = " + amplitude);
//            }
//            tempXCoordinate += size;
//        }
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
