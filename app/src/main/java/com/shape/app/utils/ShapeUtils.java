package com.shape.app.utils;

import android.content.Context;
import android.graphics.Color;

import com.shape.app.shape.ShapeType;
import com.shape.app.shape.ViewProperties;
import com.shape.app.shape.views.CircleView;
import com.shape.app.shape.views.ShapeView;
import com.shape.app.shape.views.SquareView;
import com.shape.app.shape.views.TriangleView;

import java.util.Random;

/**
 * Utility class for Shape.
 */
public class ShapeUtils {

    public static final int WIDTH = 200; // Width of shape view
    public static final int HEIGHT = 200;// Height of shape view

    /**
     * Generates a new @{@link ShapeView}
     *
     * @param context        This is the context of Parent View.
     * @param shape          Type of shape to be generated.
     * @param viewProperties Holds the properties of a Shape.
     * @return
     */
    public ShapeView generateShape(Context context, ShapeType shape,
                                   ViewProperties viewProperties) {


        ShapeView shapeView = null;
        switch (shape) {
            case SQUARE:
                viewProperties.setViewColor(Color.RED);
                viewProperties.setShapeType(ShapeType.SQUARE);
                shapeView = new SquareView(context, viewProperties);
                break;
            case CIRCLE:
                viewProperties.setViewColor(Color.GREEN);
                viewProperties.setShapeType(ShapeType.CIRCLE);
                shapeView = new CircleView(context, viewProperties);
                break;
            case TRIANGLE:
                viewProperties.setViewColor(Color.BLACK);
                viewProperties.setShapeType(ShapeType.TRIANGLE);
                shapeView = new TriangleView(context, viewProperties);
                break;
        }

        return shapeView;
    }


    /**
     * Generates a random point
     *
     * @param isXPosition IF true : It define x position on Screen , false : It define y position on Screen
     * @param min         This argument defines min value of screen
     * @param max         This argument defines max value of screen
     * @return
     */
    public int generateRandomPosition(boolean isXPosition, int min, int max) {

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
    public ShapeType getNextShape(ShapeType oldShapeType) {
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
    public ShapeType getPrevShape(ShapeType oldShapeType) {
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
