package com.shape.app.ui.shape;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.shape.app.R;
import com.shape.app.databinding.ActivityMainBinding;
import com.shape.app.shape.OnTapEvent;
import com.shape.app.shape.ShapeType;
import com.shape.app.shape.views.ShapeView;
import com.shape.app.ui.BaseActivity;
import com.shape.app.ui.stats.StatsActivity;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements UpdateEmptyView, View.OnClickListener,OnTapEvent {
    public static final String EXTRA_DELETED_SHAPES = "extraDeletedShapes";
    private final int RESULT_UPDATE_SHAPES = 101;
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ShapeContainerView shapeContainer;


    /***
     * Sets up the View Model and Binding.
     */
    @Override
    public void setupViewModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new MainViewModel();
        viewModel.setEmptyImage(ContextCompat.getDrawable(this, R.drawable.ic_tap));
        viewModel.setEmptyLabel(getString(R.string.tap_to_add));
        viewModel.setShowEmptyView(true);
        viewModel.setShapeStatsMap(new HashMap<>());
        binding.setViewModel(viewModel);

    }

    @Override
    public void setUp() {
        shapeContainer = binding.shapeContainer;
        shapeContainer.setUpdateEmptyView(this);


        binding.imageViewSquare.setOnTapEvent(this);
        binding.imageViewCircle.setOnTapEvent(this);
        binding.imageViewTriangle.setOnTapEvent(this);


//        binding.imageViewSquare.setOnClickListener(this);
//        binding.imageViewCircle.setOnClickListener(this);
//        binding.imageViewTriangle.setOnClickListener(this);

    }

    /**
     * Calculates the number of shapes drawn on Screen.
     */
    public void calculateShapeStats() {

        HashMap<ShapeType, Integer> shapeStatsMap = viewModel.getShapeStatsMap();

        shapeStatsMap.clear();

        for (int i = 0; i < shapeContainer.getChildCount(); i++) {
            ShapeType shape = ((ShapeView) shapeContainer.getChildAt(i)).getShapeType();
            if (shapeStatsMap.containsKey(shape)) {
                shapeStatsMap.put(shape, shapeStatsMap.get(shape) + 1);
            } else
                shapeStatsMap.put(shape, 1);
        }

        viewModel.setShapeStatsMap(shapeStatsMap);
    }

    /**
     * Removes the shapes deleted by user in the {@link com.shape.app.ui.stats.StatsActivity}
     */
    public void clearShapes() {
        for (ShapeType deletedShape : viewModel.getShapeStatsMap().keySet()) {
            for (int i = (shapeContainer.getChildCount() - 1); i >= 0; i--) {
                ShapeType shape = ((ShapeView) shapeContainer.getChildAt(i)).getShapeType();
                if (shape == deletedShape) {
                    shapeContainer.removeViewAt(i);
                }
            }
        }

        shapeContainer.resetLastUndoAction();
        shapeContainer.updateEmptyView();

    }

    /**
     * Performs Undo Action
     */
    public void undo() {
        if (shapeContainer != null)
            shapeContainer.performUndo();
    }

    /**
     * Updates the showEmpty
     * If the Children Count > 0 EmptyView is hidden
     * else It will be shown
     *
     * @param showEmptyView
     */
    @Override
    public void onUpdateEmptyView(boolean showEmptyView) {
        viewModel.setShowEmptyView(showEmptyView);
    }

    /**
     * First Calculates the shape stats and starts Stats Screen @{@link StatsActivity}
     */
    private void startStatScreen() {

        calculateShapeStats();

        startActivityForResult(StatsActivity.getStatsIntent(this, viewModel.getShapeStatsMap()), RESULT_UPDATE_SHAPES);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_UPDATE_SHAPES) {
            viewModel.setShapeStatsMap((HashMap<ShapeType, Integer>) data.getSerializableExtra(EXTRA_DELETED_SHAPES));
            clearShapes();
        }
    }


    /**
     * Handles onclick event of shape
     *
     * @param tag
     */
    @Override
    public void onTap(String tag) {

        if(tag.equals(getString(R.string.shape_square))){
            shapeContainer.addNewShape(ShapeType.SQUARE);
        }else if(tag.equals(getString(R.string.shape_circle))){
            shapeContainer.addNewShape(ShapeType.CIRCLE);
        }else if(tag.equals(getString(R.string.shape_triangle))){
            shapeContainer.addNewShape(ShapeType.TRIANGLE);
        }
//        switch (shapeView) {
//            case getString(R.string.shape_square):
//                break;
//            case R.id.imageViewCircle:
//                shapeContainer.addNewShape(ShapeType.CIRCLE);
//                break;
//            case R.id.imageViewTriangle:
//                shapeContainer.addNewShape(ShapeType.TRIANGLE);
//                break;
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.undo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuUndo:
                undo();
                return true;
            case R.id.menuStats:
                startStatScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called when Back Button is pressed.
     */
    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewSquare:
                shapeContainer.addNewShape(ShapeType.SQUARE);
                break;
            case R.id.imageViewCircle:
                shapeContainer.addNewShape(ShapeType.CIRCLE);
                break;
            case R.id.imageViewTriangle:
                shapeContainer.addNewShape(ShapeType.TRIANGLE);
                break;
        }

    }
}
