package com.sasuke.overlappingimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.sasuke.overlappingimageview.util.ImageLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by abc on 4/27/2018.
 */

public class OverlappingImageView extends HorizontalScrollView {

    private int mItemWidth;
    private int mItemHeight;
    private int mItemBorderWidth;
    private int mMaxItemCount;
    private float mOffset;
    private int mBorderColor;
    private LinearLayout mLinearLayout;

    private static final float DEFAULT_WIDTH = 50;
    private static final float DEFAULT_HEIGHT = 50;
    private static final float DEFAULT_BORDER_WIDTH = 4;
    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;
    private static final int DEFAULT_MAX_ITEM_COUNT = 1000;
    private static final float DEFAULT_OFFSET = 0.5f;

    private ImageLoader mImageLoader;

    public OverlappingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OverlappingImageView, 0, 0);
            mItemWidth = Math.round(typedArray.getDimension(R.styleable.OverlappingImageView_itemWidth, DEFAULT_WIDTH));
            mItemHeight = Math.round(typedArray.getDimension(R.styleable.OverlappingImageView_itemHeight, DEFAULT_HEIGHT));
            mItemBorderWidth = Math.round(typedArray.getDimension(R.styleable.OverlappingImageView_itemBorderWidth, DEFAULT_BORDER_WIDTH));
            mBorderColor = typedArray.getColor(R.styleable.OverlappingImageView_itemBorderColor, DEFAULT_BORDER_COLOR);
            mMaxItemCount = typedArray.getInt(R.styleable.OverlappingImageView_maxItemCount, DEFAULT_MAX_ITEM_COUNT);
            mOffset = typedArray.getFloat(R.styleable.OverlappingImageView_offset, DEFAULT_OFFSET);

            typedArray.recycle();
        }
        setHorizontalScrollBarEnabled(false);
        mLinearLayout = new LinearLayout(context);
        mLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(mLinearLayout);
        mImageLoader = new ImageLoader(mItemWidth, mItemHeight, mItemBorderWidth, mMaxItemCount, mOffset, mBorderColor, getContext(), mLinearLayout);

    }

    public void setThumbnailUrl(ArrayList<String> imageUrlList, boolean removeItemOnSwipe) {
        mImageLoader.setThumbnailUrl(imageUrlList, removeItemOnSwipe);
    }

    public void setThumbnailDrawableRes(ArrayList<Integer> imageDrawableResList, boolean removeItemOnSwipe) {
        mImageLoader.setThumbnailDrawableRes(imageDrawableResList, removeItemOnSwipe);
    }

    public void setThumbnailFile(ArrayList<File> imageFileList, boolean removeItemOnSwipe) {
        mImageLoader.setThumbnailFile(imageFileList, removeItemOnSwipe);
    }

    public void setThumbnailUri(ArrayList<Uri> imageUriList, boolean removeItemOnSwipe) {
        mImageLoader.setThumbnailUri(imageUriList, removeItemOnSwipe);
    }
}