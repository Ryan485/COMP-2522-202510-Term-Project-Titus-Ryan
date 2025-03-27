package io.github.some_example_name;
import java.util.Random;

public class SmoothRandomArray {
    public static float[] generateSmoothTerrain(final int n, final float minValue, final int controlPointCount) {
        Random rand = new Random();
        float[] terrain = new float[n];

        // Generate control points (inflection points)
        float[] controlPoints = new float[controlPointCount];
        for (int i = 0; i < controlPointCount; i++) {
            controlPoints[i] = minValue + rand.nextFloat() * (1.0f - minValue);
        }
        for (int i = 0; i < n; i++) {
            float fluctuation = (float) i / (n - 1);
            // adjust the fluctuation so that it doesn't go crazy
            float xLocation = fluctuation * (controlPointCount - 1);
            int index = (int) xLocation;
            float frac = xLocation - index; // Get fractional part (ask James)

            // Ensure index is within bounds
            int nextIndex = Math.min(index + 1, controlPointCount - 1);

            // heart of the smooth transition (also ask James)
            float mu2 = (1 - (float) Math.cos(frac * Math.PI)) / 2;
            terrain[i] = (controlPoints[index] * (1 - mu2) + controlPoints[nextIndex] * mu2);
        }
        return terrain;
    }
}

