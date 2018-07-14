package com.shape.app.shape.view.customshape;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.shape.app.utils.ViewProperties;

/**
 * This class draws a Circle.
 */

public class CircleView extends ShapeView {

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleView(Context context, ViewProperties viewProperties) {
        super(context, viewProperties);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Draws Circle
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2,
                canvas.getWidth() / 2, getPaint());
    }
}
