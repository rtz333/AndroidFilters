package com.example.filters.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filters.R;
import com.example.filters.model.Thumb;
import com.example.filters.ui.adapter.ThumbAdapter;
import com.example.filters.ui.adapter.listener.OnItemThumbClickedListener;
import com.example.filters.ui.widget.SquareImageView;
import com.example.filters.utils.FilterUtils;
import com.example.filters.utils.transformations.FilterTransformation;
import com.zomato.photofilters.imageprocessors.Filter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemThumbClickedListener {

    private static final int DRAWABLE = R.drawable.img_19;

    static {
        System.loadLibrary("NativeImageProcessor");
    }

    @BindView(R.id.iv_thumb)
    SquareImageView ivThumb;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecyclerView();
        getFilters();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void getFilters() {
        ArrayList<Thumb> thumbs = new ArrayList<>();
        List<Filter> filters = FilterUtils.getFilterPack(getApplicationContext());
        for (Filter filter : filters) {
            thumbs.add(new Thumb(DRAWABLE, filter));
        }

        recyclerView.setAdapter(new ThumbAdapter(thumbs, this));
        onThumbClicked(thumbs.get(0));
    }

    @Override
    public void onThumbClicked(final Thumb thumb) {
        Glide.with(MainActivity.this)
                .asBitmap()
                .override(600, 600)
                .load(DRAWABLE)
                .transform(new FilterTransformation(thumb.getFilter()))
                .into(ivThumb);
    }
}
