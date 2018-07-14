package com.shape.app.utils;

import android.graphics.Color;
import android.graphics.Point;

import com.shape.app.shape.ShapeType;

/**
 * Generates the ViewProperties Depending on the {@link com.shape.app.shape.ShapeType} and Params.
 * <p>
 * Created by Rahul.
 */
public class ViewPropertiesFactory {


    /**
     * Generates a new ViewProperties
     *
     * @param shape           This is the Shape Type.
     * @param height          Height of the Shape.
     * @param width           Width of the Shape.
     * @param viewCoordinates Position of view on Screen.
     * @return New ViewProperties Object.
     */
    public ViewProperties generateViewProperties(final ShapeType shape, final int height,
                                                 final int width, final Point viewCoordinates) {

        ViewProperties.ViewPropertiesBuilder builder =
                new ViewProperties.ViewPropertiesBuilder();

        switch (shape) {
            case SQUARE:
                builder.setViewColor(Color.RED);
                break;
            case CIRCLE:
                builder.setViewColor(Color.GREEN);
                break;
            case TRIANGLE:
                builder.setViewColor(Color.BLACK);
                break;
        }

        builder.setShapeType(shape);
        builder.setViewHeight(height);
        builder.setViewWidth(width);
        builder.setViewCoordinates(viewCoordinates);
        builder.setViewTag(ShapeUtils.generateId());
        return builder.create();
    }
}
