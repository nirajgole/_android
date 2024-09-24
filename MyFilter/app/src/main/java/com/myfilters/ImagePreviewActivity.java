package com.myfilters;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


public class ImagePreviewActivity extends AppCompatActivity {
    Toolbar mControlToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        mControlToolbar = findViewById(R.id.toolbar);
        mControlToolbar.setTitle(getString(R.string.app_name));
        mControlToolbar.setNavigationIcon(R.drawable.icon);
        mControlToolbar.setTitleTextColor(ContextCompat.getColor(ImagePreviewActivity.this, R.color.colorWhite));
    }
}
