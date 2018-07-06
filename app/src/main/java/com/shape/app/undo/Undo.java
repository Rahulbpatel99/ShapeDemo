package com.shape.app.undo;

import com.shape.app.shape.ViewProperties;
import com.shape.app.shape.views.ShapeView;

/**
 * Holds information of last action for undoing the Last Action performed
 */
public class Undo {
    private ShapeView shapeView;
    private UndoActions lastActionPerformed;
    private ViewProperties viewProperties;

    public Undo() {
    }

    public ShapeView getShapeView() {
        return shapeView;
    }

    public void setShapeView(ShapeView shapeView) {
        this.shapeView = shapeView;
    }

    public ViewProperties getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(ViewProperties viewProperties) {
        this.viewProperties = viewProperties;
    }

    public UndoActions getLastActionPerformed() {
        return lastActionPerformed;
    }

    public void setLastActionPerformed(UndoActions lastActionPerformed) {
        this.lastActionPerformed = lastActionPerformed;
    }

    /**
     * Updates the Last Shape Object to perform Undo Actions
     *
     * @param shapeView           Last ShapeView.
     * @param lastActionPerformed Last action performed by the user.
     */
    public void updateLastAction(ShapeView shapeView, UndoActions lastActionPerformed) {
        setLastActionPerformed(lastActionPerformed);
        setShapeView(shapeView);
        setViewProperties(shapeView.getViewProperties());
    }

    /**
     * This method checks if Undo is available
     *
     * @return If ShapeView is available, this is returned as true. Otherwise, this is returned as false.
     */
    public boolean canUndo() {
        return this.getShapeView() != null;
    }
}
