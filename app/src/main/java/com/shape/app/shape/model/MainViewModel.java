package com.shape.app.shape.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import com.shape.app.BR;
import com.shape.app.shape.ShapeType;
import com.shape.app.shape.view.MainActivity;

import java.util.HashMap;

/**
 * ViewModel of @{@link MainActivity}
 */
public class MainViewModel extends BaseObservable {
    private Drawable emptyImage;
    private String emptyLabel;
    private boolean showEmptyView = true;
    private HashMap<ShapeType, Integer> shapeStatsMap;

    public MainViewModel() {
        init();
    }

    private void init() {
        shapeStatsMap = new HashMap<>();
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

    @Bindable
    public boolean isShowEmptyView() {
        return showEmptyView;
    }

    public void setShowEmptyView(boolean showEmptyView) {
        this.showEmptyView = showEmptyView;
        notifyPropertyChanged(BR.showEmptyView);
    }

    public HashMap<ShapeType, Integer> getShapeStatsMap() {
        return shapeStatsMap;
    }

    public void setShapeStatsMap(HashMap<ShapeType, Integer> shapeStatsMap) {
        this.shapeStatsMap = shapeStatsMap;
    }
}
