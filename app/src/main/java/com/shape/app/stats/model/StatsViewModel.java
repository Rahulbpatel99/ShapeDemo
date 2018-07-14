package com.shape.app.stats.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;

import com.shape.app.shape.ShapeType;
import com.shape.app.stats.view.StatsActivity;

import java.util.HashMap;

/**
 * ViewModel of @{@link StatsActivity}
 */

public class StatsViewModel extends BaseObservable {
    private ObservableList<StatsItemViewModel> statsList;
    private HashMap<ShapeType, Integer> removedShapes;
    private Drawable emptyImage;
    private String emptyLabel;

    public StatsViewModel() {
        init();
    }

    /**
     * Common Initialization
     */
    private void init() {
        statsList = new ObservableArrayList<>();
        removedShapes = new HashMap<>();
    }

    public ObservableList<StatsItemViewModel> getStatsList() {
        return statsList;
    }

    public HashMap<ShapeType, Integer> getRemovedShapes() {
        if (removedShapes == null)
            removedShapes = new HashMap<>();
        return removedShapes;
    }

    public Drawable getEmptyImage() {
        return emptyImage;
    }

    public void setEmptyImage(Drawable emptyImage) {
        this.emptyImage = emptyImage;
    }

    public String getEmptyLabel() {
        return emptyLabel;
    }

    public void setEmptyLabel(String emptyLabel) {
        this.emptyLabel = emptyLabel;
    }

}