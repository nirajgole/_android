package com.myfilters.utility;

import android.content.Context;
import android.graphics.Bitmap;

import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubFilter;

public class TransformImage {
    public static final int MAX_BRIGHTNESS = 100;
    public static final int MAX_SATURATION = 100;
    public static final int MAX_VIGNETTE = 255;
    public static final int MAX_CONTRAST = 5;

    public static final int DEFAULT_BRIGHTNESS = 70;
    public static final int DEFAULT_VIGNETTE = 60;
    public static final int DEFAULT_SATURATION = 100;
    public static final int DEFAULT_CONTRAST = 5;
    public static int FILTER_BRIGHTNESS = 0;
    public static int FILTER_VIGNETTE = 1;
    public static int FILTER_SATURATION = 2;
    public static int FILTER_CONTRAST = 3;
    private String mFilename;
    private Bitmap mBitmap;
    private Context mContext;
    private Bitmap brightnessFilteredBitmap;
    private Bitmap vignetteFilteredBitmap;
    private Bitmap saturationFilteredBitmap;
    private Bitmap contrastFilteredBitmap;

    public TransformImage(Context context, Bitmap bitmap) {
        mContext = context;
        mBitmap = bitmap;
        mFilename = System.currentTimeMillis() + "";
    }

    public String getFilename(int filter) {
        if (filter == FILTER_BRIGHTNESS) {
            return mFilename + "_brightness";
        } else if (filter == FILTER_CONTRAST) {
            return mFilename + "_contrast";
        } else if (filter == FILTER_SATURATION) {
            return mFilename + "_saturation";
        } else if (filter == FILTER_VIGNETTE) {
            return mFilename + "_vignette";
        }
        return mFilename;
    }

    public Bitmap getBitmap(int filter) {
        if (filter == FILTER_BRIGHTNESS) {
            return brightnessFilteredBitmap;
        } else if (filter == FILTER_CONTRAST) {
            return contrastFilteredBitmap;
        } else if (filter == FILTER_SATURATION) {
            return saturationFilteredBitmap;
        } else if (filter == FILTER_VIGNETTE) {
            return vignetteFilteredBitmap;
        }
        return mBitmap;
    }

    //brightness
    public Bitmap applyBrightnessSubFilter(int brightness) {
        Filter myFilter = new Filter();
        Bitmap WorkingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = WorkingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        myFilter.addSubFilter(new BrightnessSubFilter(brightness));
        Bitmap outputImage = myFilter.processFilter(mutableBitmap);

        brightnessFilteredBitmap = outputImage;
        return outputImage;
    }

    // vignette
    public Bitmap applyVignetteSubFilter(int vignette) {
        Filter myFilter = new Filter();
        Bitmap WorkingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = WorkingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        myFilter.addSubFilter(new VignetteSubFilter(mContext, vignette));
        Bitmap outputImage = myFilter.processFilter(mutableBitmap);

        vignetteFilteredBitmap = outputImage;
        return outputImage;
    }

    //saturation
    public Bitmap applySaturationSubFilter(int saturation) {
        Filter myfilter = new Filter();
        Bitmap WorkingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = WorkingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        myfilter.addSubFilter(new SaturationSubFilter(saturation));
        Bitmap outputImage = myfilter.processFilter(mutableBitmap);

        saturationFilteredBitmap = outputImage;
        return outputImage;
    }

    //contrast
    public Bitmap applyContrastSubFilter(int contrast) {
        Filter myFilter = new Filter();
        Bitmap WorkingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = WorkingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        myFilter.addSubFilter(new ContrastSubFilter(contrast));
        Bitmap outputImage = myFilter.processFilter(mutableBitmap);

        contrastFilteredBitmap = outputImage;
        return outputImage;
    }
}


