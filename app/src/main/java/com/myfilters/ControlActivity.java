package com.myfilters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.myfilters.utility.Helper;
import com.myfilters.utility.TransformImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ControlActivity extends AppCompatActivity {

    final static int PICK_IMAGE = 2;
    final static int MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION = 3;
    private static final String TAG = ControlActivity.class.getSimpleName();

    static {
        System.loadLibrary("NativeImageProcessor");
    }

    Toolbar mControlToolbar;
    ImageView mCenterImageView;
    TransformImage mTransformImage;
    ImageView mFirstFilterPreviewImage;
    ImageView mSecondFilterPreviewImage;
    ImageView mThirdFilterPreviewImage;
    ImageView mFourthFilterPreviewImage;
    SeekBar mSeekbar;
    ImageView mCancelImageView;
    ImageView mTickImageView;
    Uri mSelectedImageUri;
    int mCurrentFilter;
    int mScreenHeight;
    int mScreenWidth;
    Target mApplySingleFilter = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            int currentFilterValue = mSeekbar.getProgress();

            if (mCurrentFilter == TransformImage.FILTER_BRIGHTNESS) {

                mTransformImage.applyBrightnessSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS), mTransformImage.getBitmap(TransformImage.FILTER_BRIGHTNESS));

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0, mScreenHeight / 2).into(mCenterImageView);
            } else if (mCurrentFilter == TransformImage.FILTER_SATURATION) {

                mTransformImage.applyBrightnessSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_SATURATION), mTransformImage.getBitmap(TransformImage.FILTER_SATURATION));

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0, mScreenHeight / 2).into(mCenterImageView);

            } else if (mCurrentFilter == TransformImage.FILTER_VIGNETTE) {

                mTransformImage.applyBrightnessSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE), mTransformImage.getBitmap(TransformImage.FILTER_VIGNETTE));

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0, mScreenHeight / 2).into(mCenterImageView);

            } else if (mCurrentFilter == TransformImage.FILTER_CONTRAST) {

                mTransformImage.applyBrightnessSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_CONTRAST), mTransformImage.getBitmap(TransformImage.FILTER_CONTRAST));

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_CONTRAST))).resize(0, mScreenHeight / 2).into(mCenterImageView);

            }
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    Target mSmallTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            mTransformImage = new TransformImage(ControlActivity.this, bitmap);

            //Brightness
            mTransformImage.applyBrightnessSubFilter(TransformImage.DEFAULT_BRIGHTNESS);
            Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS), bitmap);
            Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS)));

            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).fit().centerInside().into(mFirstFilterPreviewImage);

            //Saturation
            mTransformImage.applySaturationSubFilter(TransformImage.DEFAULT_SATURATION);
            Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_SATURATION), bitmap);
            Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_SATURATION)));
            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_SATURATION))).fit().centerInside().into(mSecondFilterPreviewImage);

            //Vignette
            mTransformImage.applyVignetteSubFilter(TransformImage.DEFAULT_VIGNETTE);
            Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE), bitmap);
            Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE)));
            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).fit().centerInside().into(mThirdFilterPreviewImage);

            //Contrast
            mTransformImage.applyContrastSubFilter(TransformImage.DEFAULT_CONTRAST);
            Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_CONTRAST), bitmap);
            Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_CONTRAST)));
            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_CONTRAST))).fit().centerInside().into(mFourthFilterPreviewImage);
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mControlToolbar = findViewById(R.id.toolbar);
        mSeekbar = findViewById(R.id.seekBar);
        mCenterImageView = findViewById(R.id.CenterImageView);

        mFirstFilterPreviewImage = findViewById(R.id.imageView4);
        mSecondFilterPreviewImage = findViewById(R.id.imageView5);
        mThirdFilterPreviewImage = findViewById(R.id.imageView6);
        mFourthFilterPreviewImage = findViewById(R.id.imageView7);

        mControlToolbar.setTitle(getString(R.string.app_name));
        mControlToolbar.setNavigationIcon(R.drawable.icon);
        mControlToolbar.setTitleTextColor(ContextCompat.getColor(ControlActivity.this, R.color.colorWhite));


        mTickImageView = findViewById(R.id.imageView3);
        mTickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, ImagePreviewActivity.class);
                startActivity(intent);
            }
        });

        mCenterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(ControlActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        //show user message
                    }else {
                        ActivityCompat.requestPermissions(ControlActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION);
                    }
                    return;
                }

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        //Adding Filters
        mFirstFilterPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSeekbar.setMax(TransformImage.MAX_BRIGHTNESS);
                mSeekbar.setProgress(TransformImage.DEFAULT_BRIGHTNESS);
                mCurrentFilter = TransformImage.FILTER_BRIGHTNESS;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0, mScreenHeight / 2).into(mCenterImageView);
            }
        });

        mSecondFilterPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_SATURATION);
                mSeekbar.setProgress(TransformImage.DEFAULT_SATURATION);
                mCurrentFilter = TransformImage.FILTER_SATURATION;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0, mScreenHeight / 2).into(mCenterImageView);
            }
        });

        mThirdFilterPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_VIGNETTE);
                mSeekbar.setProgress(TransformImage.DEFAULT_VIGNETTE);

                mCurrentFilter = TransformImage.FILTER_VIGNETTE;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0, mScreenHeight / 2).into(mCenterImageView);
            }
        });

        mFourthFilterPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_CONTRAST);
                mSeekbar.setProgress(TransformImage.DEFAULT_CONTRAST);

                mCurrentFilter = TransformImage.FILTER_CONTRAST;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.getFilename(TransformImage.FILTER_CONTRAST))).resize(0, mScreenHeight / 2).into(mCenterImageView);
            }
        });

        mTickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(mSelectedImageUri).into(mApplySingleFilter);
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //show message to user
                } else {
                    Log.d(TAG, "Permission Denied");
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            mSelectedImageUri = data.getData();

            Picasso.get().load(mSelectedImageUri).fit().centerInside().into(mCenterImageView);

            Picasso.get().load(R.drawable.center_image).into(mSmallTarget);

            Picasso.get().load(mSelectedImageUri).fit().centerInside().into(mFirstFilterPreviewImage);
            Picasso.get().load(mSelectedImageUri).fit().centerInside().into(mSecondFilterPreviewImage);
            Picasso.get().load(mSelectedImageUri).fit().centerInside().into(mThirdFilterPreviewImage);
            Picasso.get().load(mSelectedImageUri).fit().centerInside().into(mFourthFilterPreviewImage);

        }
    }
}
