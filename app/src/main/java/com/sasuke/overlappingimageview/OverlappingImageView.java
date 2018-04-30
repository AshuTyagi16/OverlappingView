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

    private int _yDelta;

    private float dX, dY;

    private boolean isBlockedScrollView;

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
//        disabelScrollView();
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout);

    }


    private void enableScrollView() {
        setOnTouchListener(null);
    }

    private void disabelScrollView() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private final class ChoiceTouchListener implements OnTouchListener {
        public boolean onTouch(final View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    dY = view.getY() - event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    
                    Log.d("onTouch", "view.getY: " + view.getY());
                    Log.d("onTouch", "event.getY: " + event.getY());
                    Log.d("onTouch", "event.getRawY: " + event.getRawY());

                    view.animate()
                            .y(event.getRawY() + dY)
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

//                    if (view.getY() >= mItemHeight / 2) {
//
//                    } else if (view.getY() <= -mItemHeight / 2) {
//                        (linearLayout).removeView(view);
//                        linearLayout.invalidate();
//                    } else {
//                        view.animate()
//                                .alpha(1.0f)
//                                .setDuration(0)
//                                .start();
//                    }
                    break;

                default:
                    break;
            }
            return true;
        }
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

                    circleImageView.setOnTouchListener(new ChoiceTouchListener());
//                    circleImageView.setOnDragListener(new OnDragListener() {
//                        @Override
//                        public boolean onDrag(View view, DragEvent dragEvent) {
//                            switch (dragEvent.getAction()) {
//                                case DragEvent.ACTION_DRAG_LOCATION:
//                                    break;
//                            }
//                            (linearLayout).removeView(view);
//                            linearLayout.invalidate();
//                            return false;
//                        }
//                    });
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

}