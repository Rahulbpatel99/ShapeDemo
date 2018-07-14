package com.shape.app.utils;

import com.shape.app.shape.ShapeType;

import java.util.Random;
import java.util.UUID;

/**
 * Utility class for Shape.
 */
public class ShapeUtils {

    public static final int WIDTH = 200; // Width of shape view
    public static final int HEIGHT = 200;// Height of shape view


    public static String generateId() {
        return UUID.randomUUID().toString();
    }


    /**
     * Generates a random point
     *
     * @param isXPosition IF true : It define x position on Screen , false : It define y position on Screen
     * @param min         This argument defines min value of screen
     * @param max         This argument defines max value of screen
     * @return Random Position on Screen
     */
    public static int generateRandomPosition(boolean isXPosition, int min, int max) {

        max = max - (isXPosition ? WIDTH : HEIGHT);

        if (min == max) {
            return min;
        }
        Random rand = new Random();
        float delta = max - min;
        float offset = rand.nextFloat() * delta;
        return (int) (min + offset);
    }

    /**
     * Returns next transformed shape.
     *
     * @param oldShapeType Old shape type
     * @return New transformed ShapeType
     */
    public static ShapeType getNextShape(ShapeType oldShapeType) {
        ShapeType transformedShapeType;
        switch (oldShapeType) {
            case SQUARE:
                transformedShapeType = ShapeType.CIRCLE;
                break;
            case CIRCLE:
                transformedShapeType = ShapeType.TRIANGLE;
                break;
            case TRIANGLE:
                transformedShapeType = ShapeType.SQUARE;
                break;
            default:
                transformedShapeType = ShapeType.SQUARE;
                break;
        }

        return transformedShapeType;
    }


    /**
     * Returns previous transformed shape.
     *
     * @param oldShapeType Old shape type
     * @return New transformed ShapeType
     */
    public static ShapeType getPrevShape(ShapeType oldShapeType) {
        ShapeType transformedShapeType;
        switch (oldShapeType) {
            case SQUARE:
                transformedShapeType = ShapeType.TRIANGLE;
                break;
            case CIRCLE:
                transformedShapeType = ShapeType.SQUARE;
                break;
            case TRIANGLE:
                transformedShapeType = ShapeType.CIRCLE;
                break;
            default:
                transformedShapeType = ShapeType.SQUARE;
                break;
        }
        return transformedShapeType;
    }


}
