package com.sasuke.overlappingimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

    private static final float DEFAULT_WIDTH = 50;
    private static final float DEFAULT_HEIGHT = 50;
    private static final float DEFAULT_BORDER_WIDTH = 4;
    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;
    private static final int DEFAULT_MAX_ITEM_COUNT = 1000;
    private static final float DEFAULT_OFFSET = 0.5f;

    private LinearLayout linearLayout;

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
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout);

    }

    public void setThumbnailUrl(ArrayList<String> imageUrlList) {
        if (imageUrlList != null && getContext() != null) {
            for (int i = 0; i < imageUrlList.size(); i++) {
                final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                final CircleImageView circleImageView = new CircleImageView(getContext());
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                final String url = imageUrlList.get(i);
                if (i <= this.mMaxItemCount) {
                    Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).into(circleImageView);
                    linearLayout.addView(circleImageView);

                    circleImageView.setOnTouchListener(new OnSwipeTouchListener(getContext()));
                }
            }
        }
    }

    public void setThumbnailDrawableRes(ArrayList<Integer> imageDrawableResList) {
        if (imageDrawableResList != null && getContext() != null) {
            for (int i = 0; i < imageDrawableResList.size(); i++) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                CircleImageView circleImageView = new CircleImageView(getContext());
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                if (i <= this.mMaxItemCount) {
                    Picasso.get().load(imageDrawableResList.get(i)).placeholder(R.drawable.ic_launcher_background).into(circleImageView);
                    linearLayout.addView(circleImageView);
                }
            }
        }
    }

    public void setThumbnailFile(ArrayList<File> imageFileList) {
        if (imageFileList != null && getContext() != null) {
            for (int i = 0; i < imageFileList.size(); i++) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                CircleImageView circleImageView = new CircleImageView(getContext());
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                if (i <= this.mMaxItemCount) {
                    if (imageFileList.get(i) != null) {
                        Picasso.get().load(imageFileList.get(i)).placeholder(R.drawable.ic_launcher_background).into(circleImageView);
                        linearLayout.addView(circleImageView);
                    }
                }
            }
        }
    }

    public void setThumbnailUri(ArrayList<Uri> imageFileList) {
        if (imageFileList != null && getContext() != null) {
            for (int i = 0; i < imageFileList.size(); i++) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
                if (i > 0) {
                    layoutParams.setMargins(Math.round(mItemWidth * mOffset) * -1, 0, 0, 0);
                }
                CircleImageView circleImageView = new CircleImageView(getContext());
                circleImageView.setBorderWidth(mItemBorderWidth);
                circleImageView.setBorderColor(mBorderColor);
                circleImageView.setLayoutParams(layoutParams);
                if (i <= this.mMaxItemCount) {
                    if (imageFileList.get(i) != null) {
                        Picasso.get().load(imageFileList.get(i)).placeholder(R.drawable.ic_launcher_background).into(circleImageView);
                        linearLayout.addView(circleImageView);
                    }
                }
            }
        }
    }

    private class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;
        private View view;

        public OnSwipeTouchListener(Context ctx) {
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            this.view = v;
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            view.animate()
                                    .y(e2.getRawY() + mItemWidth)
                                    .alpha(0.0f)
                                    .setDuration(300)
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            (linearLayout).removeView(view);
                                            linearLayout.invalidate();

                                        }
                                    })
                                    .start();
                        } else {
                            view.animate()
                                    .y(mItemWidth - e2.getRawY())
                                    .alpha(0.0f)
                                    .setDuration(300)
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            (linearLayout).removeView(view);
                                            linearLayout.invalidate();

                                        }
                                    })
                                    .start();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

    }

}