package com.example.filters.model;

import com.zomato.photofilters.imageprocessors.Filter;

/**
 * @author Varun on 01/07/15.
 */
public class Thumb {

    private int image;
    private Filter filter;


    public Thumb(int image, Filter filter) {
        this.image = image;
        this.filter = filter;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
