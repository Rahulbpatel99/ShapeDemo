package com.shape.app.shape;

import android.graphics.Point;
import android.support.annotation.NonNull;

/**
 * This class holds the Properties of a view.
 */
public class ViewProperties {
    private ShapeType shapeType;
    private int viewWidth;
    private int viewHeight;
    private Point viewCoordinates;
    private int viewColor;

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType viewType) {
        this.shapeType = viewType;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public Point getViewCoordinates() {
        return viewCoordinates;
    }

    public void setViewCoordinates(@NonNull Point viewCoordinates) {
        this.viewCoordinates = viewCoordinates;
    }

    public int getViewColor() {
        return viewColor;
    }

    public void setViewColor(int viewColor) {
        this.viewColor = viewColor;
    }
}
