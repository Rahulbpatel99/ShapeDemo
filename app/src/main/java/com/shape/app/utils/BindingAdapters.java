package com.shape.app.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Binding Adapter
 */
public class BindingAdapters {

    /**
     * Loads Drawable into an ImageView
     *
     * @param imageView  This is the ImageView on which Drawable is shown
     * @param imageLocal This is the drawable to be shown
     */
    @BindingAdapter(value = {"imageLocal"}, requireAll = false)
    public static void loadDrawable(ImageView imageView, Drawable imageLocal) {
        imageView.setImageDrawable(imageLocal);
    }

    /**
     * @param view
     * @param makeViewVisible If true makes the visibility of view as Visible else Gone.
     */
    @BindingAdapter(value = {"makeViewVisible"}, requireAll = false)
    public static void toggleVisibility(View view, boolean makeViewVisible) {
        view.setVisibility(makeViewVisible ? View.VISIBLE : View.GONE);

    }

}
