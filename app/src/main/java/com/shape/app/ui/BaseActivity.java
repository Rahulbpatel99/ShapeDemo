package com.shape.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setupViewModelAndBinding();

        setUp();

    }

    /***
     * Sets up the ViewModel and Binding.
     */
    public abstract void setupViewModelAndBinding();

    /**
     * Common Initialization
     */
    public abstract void setUp();

    /**
     * Called on Back Press of Activity
     */
    public abstract void closeActivity();

    @Override
    public void onBackPressed() {
        closeActivity();
    }

}
