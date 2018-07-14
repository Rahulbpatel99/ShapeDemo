package com.shape.app.stats.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.shape.app.R;
import com.shape.app.databinding.ActivityStatsBinding;
import com.shape.app.shape.ShapeType;
import com.shape.app.stats.listener.OnStatsTap;
import com.shape.app.stats.adapter.StatsAdapter;
import com.shape.app.stats.model.StatsItemViewModel;
import com.shape.app.stats.model.StatsViewModel;
import com.shape.app.basecomponents.BaseActivity;
import com.shape.app.shape.view.MainActivity;

import java.util.HashMap;

public class StatsActivity extends BaseActivity implements OnStatsTap {
    public static final String EXTRA_SHAPE_DATA = "extraShapeData";
    private ActivityStatsBinding binding;
    private StatsViewModel viewModel;
    private StatsAdapter mAdapter;

    /**
     * @param context   Context of Caller Activity
     * @param statsData Stats Data
     * @return Intent of @{@link StatsActivity}
     */
    public static Intent getStatsIntent(Context context, HashMap<ShapeType, Integer> statsData) {
        Intent intent = new Intent(context, StatsActivity.class);
        intent.putExtra(EXTRA_SHAPE_DATA, statsData);
        return intent;
    }

    /***
     * Sets up the ViewModel and Binding.
     */
    @Override
    public void setupViewModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stats);

        viewModel = new StatsViewModel();
        viewModel.setEmptyImage(ContextCompat.getDrawable(this, R.drawable.ic_no_stats));
        viewModel.setEmptyLabel(getString(R.string.no_stats));

        binding.setStatsModel(viewModel);
    }

    @Override
    public void setUp() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupList();

        setupRecyclerView();
    }

    /**
     * Sets up the RecyclerView
     */
    private void setupRecyclerView() {
        mAdapter = new StatsAdapter(this);

        binding.statsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.statsRecyclerView.setAdapter(mAdapter);
        mAdapter.setStatsItemList(viewModel.getStatsList());
    }

    /**
     * Sets up the list
     * Gets HashMap from Intent and adds it to the Recycler View Data set.
     */
    private void setupList() {
        HashMap<ShapeType, Integer> shapeMap = (HashMap<ShapeType, Integer>) getIntent().getSerializableExtra(EXTRA_SHAPE_DATA);

        for (ShapeType shape : shapeMap.keySet()) {
            viewModel.getStatsList().add(new StatsItemViewModel(this, shape, shapeMap.get(shape)));
        }
    }

    /**
     * Adds Removed shape to the HashMap which will be sent back to the @{@link MainActivity} using
     * Intent.
     *
     * @param position
     */
    private void updateRemovedShapes(int position) {
        viewModel.getRemovedShapes()
                .put(viewModel.getStatsList().get(position).getShapeTypeValue(), 0);
    }

    /**
     * Handles the delete tap of Shape.
     *
     * @param position
     */
    @Override
    public void onTap(int position) {
        updateRemovedShapes(position);
        viewModel.getStatsList().remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                closeActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Closes Activity.
     * If user has deleted any shape then activity will be closed with a result.
     */
    @Override
    public void closeActivity() {
        if (viewModel.getRemovedShapes().size() > 0) {
            Intent closingIntent = new Intent();
            closingIntent.putExtra(MainActivity.EXTRA_DELETED_SHAPES,
                    viewModel.getRemovedShapes());
            setResult(RESULT_OK, closingIntent);
        }
        finish();
    }

}
