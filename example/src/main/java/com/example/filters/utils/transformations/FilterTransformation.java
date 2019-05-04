package com.example.filters.utils.transformations;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.zomato.photofilters.imageprocessors.Filter;

import java.security.MessageDigest;

public class FilterTransformation extends BitmapTransformation {

    private static final String TAG = "FilterTransformation";

    private String ID = "com.example.filters.utils.transformations.FilterTransformation";
    private byte[] ID_BYTES;

    private Filter filter;

    public FilterTransformation(Filter filter) {
        this.filter = filter;

        ID = ID + filter.getName();
        ID_BYTES = ID.getBytes(CHARSET);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap bitmap = TransformationUtils.fitCenter(pool, toTransform, outWidth, outHeight);
        return filter.processFilter(bitmap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterTransformation that = (FilterTransformation) o;
        return equals(ID, that.ID) && equals(filter, that.filter);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    private static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
