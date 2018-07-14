package com.shape.app.shape.view.customshape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.shape.app.utils.ViewProperties;

/**
 * This class draws a Triangle.
 */

public class TriangleView extends ShapeView {

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TriangleView(Context context, ViewProperties viewProperties) {
        super(context, viewProperties);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Draws Triangle
        Point point1 = new Point(0, canvas.getWidth());

        Point point2 = new Point(canvas.getWidth(), canvas.getHeight());

        Point point3 = new Point(canvas.getWidth() / 2, 0);

        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);

        canvas.drawPath(path, getPaint());
    }
}