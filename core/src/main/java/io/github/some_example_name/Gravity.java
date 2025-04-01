package io.github.some_example_name;

/**
 * Provide downward gravity to objects.
 * @author Titus
 * @version 2015
 */
public interface Gravity {
    float GRAVITY_CONSTANT = -9.8f * 10;
    /**
     * to activate gravity.
     */

    default float[] applyGravity(float delta, float yCoordinate, float velocityY) {
        velocityY += GRAVITY_CONSTANT * delta;
        yCoordinate += velocityY * delta;
        if (yCoordinate < 0) {
            yCoordinate = 0;
            velocityY = 0;
        }
        return new float[] {yCoordinate, velocityY};
    }
}
