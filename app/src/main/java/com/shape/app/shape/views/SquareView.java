package com.shape.app.shape.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.shape.app.shape.ViewProperties;

/**
 * This class draws a Square.
 */

public class SquareView extends ShapeView {

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SquareView(Context context, ViewProperties viewProperties) {
        super(context, viewProperties);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Draws Square
        canvas.drawRect(0, 0,
                canvas.getWidth(),
                canvas.getWidth(), getPaint());
    }
}
