package com.example.filters;

import android.app.Application;

public class App extends Application {
    private static App self;

    public static App self() {
        return self;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
    }
}
