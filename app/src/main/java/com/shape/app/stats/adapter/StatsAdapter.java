package com.shape.app.stats.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shape.app.R;
import com.shape.app.databinding.RowStatsBinding;
import com.shape.app.stats.model.StatsItemViewModel;
import com.shape.app.stats.listener.OnStatsTap;

import java.util.ArrayList;
import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsItemHolder> {

    private List<StatsItemViewModel> statsItemList;
    private OnStatsTap mOnStatsTap;

    public StatsAdapter(OnStatsTap onStatsTap) {
        this.mOnStatsTap = onStatsTap;
        statsItemList = new ArrayList<>();
    }

    public void setStatsItemList(@NonNull List<StatsItemViewModel> statsItem) {
        this.statsItemList = statsItem;
        notifyDataSetChanged();
    }

    @Override
    public StatsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stats, parent, false);
        return new StatsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final StatsItemHolder holder, int position) {
        holder.binding.setModel(statsItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return statsItemList == null ? 0 : statsItemList.size();
    }

    public class StatsItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RowStatsBinding binding;

        private StatsItemHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.imageViewDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnStatsTap != null)
                mOnStatsTap.onTap(getAdapterPosition());
        }
    }

}
