package com.shape.app.undo;

import com.shape.app.shape.ViewProperties;

/**
 * Holds information of last action for undoing the Last Action performed
 */
public class Undo {
    private String viewTag;
    private UndoActions lastActionPerformed;
    private ViewProperties viewProperties;

    public Undo() {
    }

    public String getViewTag() {
        return viewTag;
    }

    public void setViewTag(String viewTag) {
        this.viewTag = viewTag;
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

    private void setLastActionPerformed(UndoActions lastActionPerformed) {
        this.lastActionPerformed = lastActionPerformed;
    }

    /**
     * Updates the Last Shape Object to perform Undo Actions
     *
     * @param viewProperties      Last ShapeView's View Properties.
     * @param lastActionPerformed Last action performed by the user.
     */
    public void updateLastAction(final ViewProperties viewProperties, final UndoActions lastActionPerformed) {
        setLastActionPerformed(lastActionPerformed);
        setViewTag(viewProperties.getViewTag());
        setViewProperties(viewProperties);
    }

    /**
     * This method checks if Undo is available
     *
     * @return If ShapeView Tag is available, this is returned as true. Otherwise, this is returned as false.
     */
    public boolean canUndo() {
        return this.getViewTag() != null;
    }
}
