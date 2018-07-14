package com.shape.app.ui.shape;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.shape.app.shape.OnLongTapEvent;
import com.shape.app.shape.OnTapEvent;
import com.shape.app.shape.ShapeType;
import com.shape.app.shape.ViewProperties;
import com.shape.app.shape.views.ShapeFactory;
import com.shape.app.shape.views.ShapeView;
import com.shape.app.shape.views.ViewPropertiesFactory;
import com.shape.app.undo.Undo;
import com.shape.app.undo.UndoActions;
import com.shape.app.utils.ShapeUtils;

/**
 * This Class is a Container for holding all the shape views.
 */
public class ShapeContainerView extends FrameLayout implements OnTapEvent, OnLongTapEvent {
    private Undo undo;
    private UpdateEmptyView updateEmptyView;
    private ViewPropertiesFactory viewPropertiesFactory;
    private ShapeFactory shapeFactory;

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
        viewPropertiesFactory = new ViewPropertiesFactory();
//        shapeUtils = new ShapeUtils();
        shapeFactory = new ShapeFactory();
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
        shapeView.setOnTapEvent(this);
        shapeView.setOnLongTapEvent(this);
    }

    /**
     * Removes shape from the view
     *
     * @param tagId This is the Tag of the view to be removed from screen
     */
    public void removeShape(String tagId) {
        removeView(findViewByTag(tagId));
    }


    /**
     * Finds a view using tagId
     *
     * @param viewTag Tag of the View
     * @return
     */
    private ShapeView findViewByTag(String viewTag) {
        return findViewWithTag(viewTag);
    }


    /**
     * Adds new Shape
     *
     * @param shape
     */
    public void addNewShape(ShapeType shape) {

        ViewProperties viewProperties = viewPropertiesFactory.generateViewProperties(shape
                , ShapeUtils.HEIGHT
                , ShapeUtils.WIDTH
                , new Point(ShapeUtils.generateRandomPosition(true, getLeft(), getRight()),
                        ShapeUtils.generateRandomPosition(false, getTop(), getBottom())));


        ShapeView shapeView = shapeFactory.getShape(getContext(), viewProperties);


        undo.updateLastAction(viewProperties, UndoActions.CREATED);

        drawShape(shapeView);

        updateEmptyView();
    }

    /**
     * Transforms the shapes to a new shape.
     * Removes the old shape view and adds a new transformed shape.
     * Shapes will be transformed from Square to Circle, Circle to Triangle and Triangle to Square.
     *
     * @param viewTag This view Tag that needs to be Transformed to new shape.
     */
    private void transformShape(String viewTag) {

        ViewProperties viewProperties = findViewByTag(viewTag).getViewProperties();

        //Removes the last shape
        removeShape(viewTag);

        //Updates the Tag to a new Random Tag
        viewProperties.setViewTag(ShapeUtils.generateId());

        //Updated the Next Shape
        viewProperties.setShapeType(ShapeUtils.getNextShape(viewProperties.getShapeType()));

        //Shape Screen Coordinates will be obtained from ViewProperties of last shape.
        ShapeView transformedShape = shapeFactory.getShape(getContext(), viewProperties);

        //Updates Undo Action
        undo.updateLastAction(viewProperties, UndoActions.TRANSFORMED);

        //Draws Shape
        drawShape(transformedShape);

        updateEmptyView();
    }

    /**
     * Shape Tap event Listener
     *
     * @param tag
     */
    @Override
    public void onTap(String tag) {
        transformShape(tag);
    }

    /**
     * Shape Long Tap event Listener
     *
     * @param tag
     */
    @Override
    public void onLongTap(String tag) {
        View shapeView = findViewWithTag(tag);
        if (shapeView instanceof ShapeView) {
            ViewProperties viewProperties = ((ShapeView) shapeView).getViewProperties();
            undo.updateLastAction(viewProperties, UndoActions.DELETED);
            removeShape(viewProperties.getViewTag());
        }

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
            removeShape(undo.getViewTag());
        } else if (undo.getLastActionPerformed() == UndoActions.TRANSFORMED) {
            //Removes the last known shape
            removeShape(undo.getViewTag());
            //Draws a new transformed shape
            ViewProperties viewProperties = undo.getViewProperties();

            //Updating shape type to the previous shape type
            viewProperties.setShapeType(ShapeUtils.getPrevShape(undo.getViewProperties().getShapeType()));

            drawShape(shapeFactory.getShape(getContext()
                    , undo.getViewProperties()));

        } else if (undo.getLastActionPerformed() == UndoActions.DELETED) {
            //Draws a shape using last known shape
            drawShape(shapeFactory.getShape(getContext()
                    , undo.getViewProperties()));
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
        undo.setViewTag(null);
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
