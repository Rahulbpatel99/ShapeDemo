package com.shape.app.shape.view.customshape;

import android.content.Context;
import android.graphics.Color;

import com.shape.app.utils.ViewProperties;

/**
 * Generates the ShapeView Depending on the {@link com.shape.app.shape.ShapeType}
 * <p>
 * Created by Rahul.
 */

public class ShapeFactory {

    /**
     * Generates a new @{@link ShapeView}
     *
     * @param context        This is the context of Parent View.
     * @param viewProperties Holds the properties of a Shape.
     * @return Returns a new ShapeView
     */
    public ShapeView getShape(Context context, ViewProperties viewProperties) {
        ShapeView shapeView;
        switch (viewProperties.getShapeType()) {
            case SQUARE:
                //Updates the Color because if a Shape is transformed it will have Old Shape's Color.
                viewProperties.setViewColor(Color.RED);
                shapeView = new SquareView(context, viewProperties);
                break;
            case CIRCLE:
                //Updates the Color because if a Shape is transformed it will have Old Shape's Color.
                viewProperties.setViewColor(Color.GREEN);
                shapeView = new CircleView(context, viewProperties);
                break;
            case TRIANGLE:
                //Updates the Color because if a Shape is transformed it will have Old Shape's Color.
                viewProperties.setViewColor(Color.BLACK);
                shapeView = new TriangleView(context, viewProperties);
                break;
            default:
                viewProperties.setViewColor(Color.RED);
                shapeView = new SquareView(context, viewProperties);
                break;
        }

        //Tag is set to the view so that Transformation,delete and Undo action can happen.
        shapeView.setTag(viewProperties.getViewTag());
        return shapeView;
    }
}
