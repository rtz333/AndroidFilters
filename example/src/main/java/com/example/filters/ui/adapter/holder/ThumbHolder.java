package com.example.filters.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.filters.App;
import com.example.filters.R;
import com.example.filters.model.Thumb;
import com.example.filters.ui.adapter.ThumbAdapter;
import com.example.filters.ui.adapter.listener.OnItemThumbClickedListener;
import com.example.filters.utils.transformations.FilterTransformation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThumbHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_thumb)
    ImageView ivThumb;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.v_status)
    View vStatus;

    private Thumb thumb;
    private ThumbAdapter thumbAdapter;
    private OnItemThumbClickedListener listener;

    public static ThumbHolder provide(ViewGroup viewGroup, ThumbAdapter thumbAdapter, OnItemThumbClickedListener listener) {
        View itemView = LayoutInflater.from(App.self()).inflate(R.layout.holder_thumb, viewGroup, false);
        return new ThumbHolder(itemView, thumbAdapter, listener);
    }

    private ThumbHolder(@NonNull View itemView, ThumbAdapter thumbAdapter, OnItemThumbClickedListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.thumbAdapter = thumbAdapter;
        this.listener = listener;
    }

    public void bindData(Thumb thumb) {
        this.thumb = thumb;

        Glide.with(ivThumb)
                .asBitmap()
                .override(150)
                .transform(new CenterCrop(), new RoundedCorners(20), new FilterTransformation(thumb.getFilter()))
                .load(thumb.getImage())
                .into(ivThumb);

        tvName.setText(thumb.getFilter().getName());

        bindSelected();
    }

    public void bindSelected(){
        if (thumbAdapter.getCurrentSelected() == getAdapterPosition()) {
            vStatus.setVisibility(View.VISIBLE);
        } else {
            vStatus.setVisibility(View.INVISIBLE);
        }
    }

    public void recycled() {
        Glide.with(ivThumb).clear(ivThumb);
    }

    @OnClick(R.id.root_holder_thumb)
    public void onViewClicked() {
        listener.onThumbClicked(thumb);
    }
}
