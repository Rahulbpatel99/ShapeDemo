package com.shape.app.shape;

import com.shape.app.shape.views.ShapeView;

/**
 * Handles the OnClick Events of the @{@link ShapeView}
 */
public interface OnTapEvents {

    void onTap(ShapeView shapeView);

    void onLongTap(ShapeView shapeView);

}
