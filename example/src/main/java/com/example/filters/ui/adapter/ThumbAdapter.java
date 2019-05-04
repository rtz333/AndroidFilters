package com.example.filters.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.filters.model.Thumb;
import com.example.filters.ui.adapter.holder.ThumbHolder;
import com.example.filters.ui.adapter.listener.OnItemThumbClickedListener;

import java.util.List;

/**
 * @author Varun on 01/07/15.
 */
public class ThumbAdapter extends RecyclerView.Adapter<ThumbHolder> implements OnItemThumbClickedListener {

    private int currentSelected = 0;

    private List<Thumb> dataSet;
    private OnItemThumbClickedListener listener;

    public ThumbAdapter(List<Thumb> dataSet, OnItemThumbClickedListener listener) {
        this.dataSet = dataSet;
        this.listener = listener;
    }

    public int getCurrentSelected() {
        return currentSelected;
    }

    @NonNull
    @Override
    public ThumbHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return ThumbHolder.provide(viewGroup, this, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            holder.bindSelected();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbHolder holder, final int i) {
        holder.bindData(dataSet.get(i));
    }

    @Override
    public void onViewRecycled(@NonNull ThumbHolder holder) {
        super.onViewRecycled(holder);
        holder.recycled();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onThumbClicked(Thumb thumb) {
        int oldPosition = currentSelected;
        currentSelected = dataSet.indexOf(thumb);

        notifyItemChanged(oldPosition, thumb);
        notifyItemChanged(currentSelected, thumb);

        listener.onThumbClicked(thumb);
    }
}
