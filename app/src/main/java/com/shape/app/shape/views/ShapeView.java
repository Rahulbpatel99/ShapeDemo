package com.shape.app.shape.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.shape.app.R;
import com.shape.app.shape.OnTapEvents;
import com.shape.app.shape.ShapeType;
import com.shape.app.shape.ViewProperties;

/**
 * This class draws Shape Objects with respect to User Inputs.
 */

public class ShapeView extends View implements View.OnClickListener, View.OnLongClickListener {
    private OnTapEvents onTapEvents;
    private Paint paint;
    private ViewProperties viewProperties;

    public ShapeView(Context context, ViewProperties viewProperties) {
        super(context);
        this.viewProperties = viewProperties;
        this.init();
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    /**
     * Common Initialization for Xml attributes
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {

        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.shapeColor);

        int color = typedArray.getColor(R.styleable.shapeColor_color, -1);

        if (color == -1)
            color = Color.BLACK;

        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(color);

        setClickListeners();
    }

    /**
     * Common Initialization
     */
    private void init() {
        setLayoutParameters();
        setClickListeners();
    }

    /**
     * Sets the Canvas view LayoutParams
     * Sets the X and Y position of canvas on screen.
     */
    private void setLayoutParameters() {
        if (this.viewProperties != null) {

            this.paint = new Paint();
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(this.viewProperties.getViewColor());

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.viewProperties.getViewWidth(),
                    this.viewProperties.getViewHeight());
            setLayoutParams(layoutParams);
            setX(this.viewProperties.getViewCoordinates().x);
            setY(this.viewProperties.getViewCoordinates().y);
        }

        setWillNotDraw(false);

    }

    /**
     * This method is getter for @{@link ShapeType}.
     *
     * @return
     */
    public ShapeType getShapeType() {
        return this.viewProperties.getShapeType();
    }

    /**
     * This method is getter for paint.
     *
     * @return
     */
    public Paint getPaint() {
        return this.paint;
    }

    /**
     * This method is getter for @{@link ViewProperties}.
     *
     * @return
     */
    public ViewProperties getViewProperties() {
        return this.viewProperties;
    }

    /**
     * This method is setter for @{@link OnTapEvents}.
     *
     * @param onTapEvents
     */
    public void setOnTapEvents(OnTapEvents onTapEvents) {
        this.onTapEvents = onTapEvents;
    }

    /**
     * Registers OnClick and LongPress events
     */
    private void setClickListeners() {
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    /**
     * Handles the onClick Event of View
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (this.onTapEvents != null)
            this.onTapEvents.onTap(this);
    }

    /**
     * Handles the LongClick Event of View
     *
     * @param v
     * @return
     */
    @Override
    public boolean onLongClick(View v) {
        if (this.onTapEvents != null)
            this.onTapEvents.onLongTap(this);
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setOnClickListener(null);
        setOnLongClickListener(null);
        this.onTapEvents = null;
        this.paint = null;
        this.viewProperties = null;
    }

}
