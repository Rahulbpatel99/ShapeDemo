package com.shape.app.ui.shape;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.shape.app.shape.OnTapEvents;
import com.shape.app.shape.ShapeType;
import com.shape.app.shape.ViewProperties;
import com.shape.app.shape.views.ShapeView;
import com.shape.app.undo.Undo;
import com.shape.app.undo.UndoActions;
import com.shape.app.utils.ShapeUtils;

/**
 * This Class is a Container for holding all the shape views.
 */
public class ShapeContainerView extends FrameLayout implements OnTapEvents {
    private Undo undo;
    private ShapeUtils shapeUtils;
    private UpdateEmptyView updateEmptyView;

    public ShapeContainerView(@NonNull Context context) {
        super(context);
        this.init();
    }

    public ShapeContainerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public ShapeContainerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    /**
     * Common Initialization
     */
    private void init() {
        undo = new Undo();
        shapeUtils = new ShapeUtils();
    }

    public void setUpdateEmptyView(UpdateEmptyView updateEmptyView) {
        this.updateEmptyView = updateEmptyView;
    }

    /**
     * Draws Shape on screen.
     *
     * @param shapeView To be drawn on screen
     */
    public void drawShape(ShapeView shapeView) {
        addView(shapeView);
        shapeView.setOnTapEvents(this);
    }

    /**
     * Removes shape from the view
     *
     * @param shapeView This is the view to be removed from screen
     */
    public void removeShape(ShapeView shapeView) {
        removeView(shapeView);
    }

    /**
     * Adds new Shape
     *
     * @param shape
     */
    public void addNewShape(ShapeType shape) {
        ViewProperties viewProperties = new ViewProperties();
        viewProperties.setViewCoordinates(
                new Point(shapeUtils.generateRandomPosition(true, getLeft(), getRight()),
                        shapeUtils.generateRandomPosition(false, getTop(), getBottom())));

        viewProperties.setViewWidth(ShapeUtils.WIDTH);
        viewProperties.setViewHeight(ShapeUtils.HEIGHT);

        ShapeView shapeView = shapeUtils.generateShape(getContext(), shape, viewProperties);
        undo.updateLastAction(shapeView, UndoActions.CREATED);
        drawShape(shapeView);
        updateEmptyView();
    }

    /**
     * Transforms the shapes to a new shape.
     * Removes the old shape view and adds a new transformed shape.
     * Shapes will be transformed from Square to Circle, Circle to Triangle and Triangle to Square.
     *
     * @param shapeView This view will be removed.
     */
    private void transformShape(ShapeView shapeView) {
        //Shape Screen Coordinates will be obtained from ViewProperties of last shape.
        ShapeView transformedShape = shapeUtils.generateShape(getContext(), shapeUtils.getNextShape(shapeView.getShapeType()),
                shapeView.getViewProperties());

        //Removes the last shape
        removeView(shapeView);

        //Updates Undo Action
        undo.updateLastAction(transformedShape, UndoActions.TRANSFORMED);

        //Draws Shape
        drawShape(transformedShape);

        updateEmptyView();
    }

    /**
     * Shape Tap event Listener
     *
     * @param shapeView
     */
    @Override
    public void onTap(ShapeView shapeView) {
        transformShape(shapeView);
    }

    /**
     * Shape Long Tap event Listener
     *
     * @param shapeView
     */
    @Override
    public void onLongTap(ShapeView shapeView) {
        undo.updateLastAction(shapeView, UndoActions.DELETED);
        removeShape(shapeView);
        updateEmptyView();
    }

    /**
     * Performs Undo operation
     */
    public void performUndo() {
        if (!undo.canUndo())
            return;

        if (undo.getLastActionPerformed() == UndoActions.CREATED) {
            //Removes the last known shape
            removeShape(undo.getShapeView());
        } else if (undo.getLastActionPerformed() == UndoActions.TRANSFORMED) {
            //Removes the last known shape
            removeShape(undo.getShapeView());
            //Draws a new transformed shape
            drawShape(shapeUtils.generateShape(getContext(), shapeUtils.getPrevShape
                    (undo.getViewProperties().getShapeType()), undo.getViewProperties()));
        } else if (undo.getLastActionPerformed() == UndoActions.DELETED) {
            //Draws a shape using last known shape
            drawShape(shapeUtils.generateShape(getContext(), undo.getViewProperties().getShapeType(),
                    undo.getViewProperties()));
        }
        updateEmptyView();

        resetLastUndoAction();
    }

    /**
     * Sends Callback to @{@link MainViewModel} whether to show Empty View or not.
     * If child count is zero empty view will be visible or it will be visible.
     */
    public void updateEmptyView() {
        if (updateEmptyView != null)
            updateEmptyView.onUpdateEmptyView(getChildCount() == 0);
    }

    /**
     * Resets the Last Shape Saved.
     */
    protected void resetLastUndoAction() {
        undo.setShapeView(null);
        undo.setViewProperties(null);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllViews();
        undo = null;
        updateEmptyView = null;
    }
}
