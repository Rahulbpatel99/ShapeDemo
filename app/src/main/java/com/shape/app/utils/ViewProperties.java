package com.shape.app.utils;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.shape.app.shape.ShapeType;

/**
 * This class holds the Properties of a view.
 */
public class ViewProperties {
    private ShapeType shapeType;
    private int viewWidth;
    private int viewHeight;
    private Point viewCoordinates;
    private int viewColor;
    private String viewTag;
    
    private ViewProperties(final ViewPropertiesBuilder builder) {
        this.shapeType = builder.shapeType;
        this.viewWidth = builder.viewWidth;
        this.viewHeight = builder.viewHeight;
        this.viewCoordinates = builder.viewCoordinates;
        this.viewColor = builder.viewColor;
        this.viewTag = builder.viewTag;
    }

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

    public String getViewTag() {
        return viewTag;
    }

    public void setViewTag(String viewTag) {
        this.viewTag = viewTag;
    }

    public static class ViewPropertiesBuilder {
        private ShapeType shapeType;
        private int viewWidth;
        private int viewHeight;
        private Point viewCoordinates;
        private int viewColor;
        public String viewTag;


        public ViewPropertiesBuilder setShapeType(ShapeType shapeType) {
            this.shapeType = shapeType;
            return this;
        }


        public ViewPropertiesBuilder setViewWidth(int viewWidth) {
            this.viewWidth = viewWidth;
            return this;
        }


        public ViewPropertiesBuilder setViewHeight(int viewHeight) {
            this.viewHeight = viewHeight;
            return this;
        }


        public ViewPropertiesBuilder setViewCoordinates(Point viewCoordinates) {
            this.viewCoordinates = viewCoordinates;
            return this;
        }


        public ViewPropertiesBuilder setViewColor(int viewColor) {
            this.viewColor = viewColor;
            return this;
        }


        public ViewPropertiesBuilder setViewTag(String viewTag) {
            this.viewTag = viewTag;
            return this;
        }


        public ViewProperties create() {
            validate();
            return new ViewProperties(this);
        }

        private void validate() {
            if (this.shapeType == null) {
                throw new IllegalStateException(
                        "Please add shape type");
            } else if (this.viewWidth < 0) {
                throw new IllegalStateException(
                        "Please add shape width");
            } else if (this.viewHeight < 0) {
                throw new IllegalStateException(
                        "Please add shape height");
            } else if (this.viewCoordinates == null) {
                throw new IllegalStateException(
                        "Please add shape View Coordinates");
            } else if (this.viewColor == -1) {
                throw new IllegalStateException(
                        "Please add shape Color");
            }

        }

    }

}
