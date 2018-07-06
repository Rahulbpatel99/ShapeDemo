package com.shape.app.ui.stats;

import android.content.Context;
import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.shape.app.R;
import com.shape.app.shape.ShapeType;

/**
 * ViewModel of @{@link StatsAdapter.StatsItemHolder}
 */

public class StatsItemViewModel extends BaseObservable {
    private Context context;
    private ShapeType shapeType;
    private int shapeCount;


    public StatsItemViewModel(Context context, ShapeType shapeType, int shapeCount) {
        this.context = context;
        this.shapeType = shapeType;
        this.shapeCount = shapeCount;
    }

    public String getShapeCount() {
        return String.valueOf(this.shapeCount);
    }

    public ShapeType getShapeTypeValue() {
        return this.shapeType;
    }

    /**
     * Used to show drawables according to shape.
     *
     * @return Drawable according to the shape.
     */
    public Drawable getShapeDrawable() {
        switch (this.shapeType) {
            case SQUARE:
                return ContextCompat.getDrawable(context, R.drawable.square);
            case CIRCLE:
                return ContextCompat.getDrawable(context, R.drawable.circle);
            case TRIANGLE:
                return ContextCompat.getDrawable(context, R.drawable.triangle);
            default:
                return ContextCompat.getDrawable(context, R.drawable.square);
        }
    }
}
