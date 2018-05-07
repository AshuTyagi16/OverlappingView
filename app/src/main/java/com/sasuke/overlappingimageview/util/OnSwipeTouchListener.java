package com.sasuke.overlappingimageview.util;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by abc on 5/7/2018.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    private View mView;
    private int mItemWidth;

    public OnSwipeTouchListener(Context ctx, int itemWidth) {
        this.mItemWidth = itemWidth;
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        this.mView = view;
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        private static final int AMINATION_DURATION = 300;
        private static final float ALPHA = 0.0f;

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
                        mView.animate()
                                .y(e2.getRawY() + mItemWidth)
                                .alpha(ALPHA)
                                .setDuration(AMINATION_DURATION)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout linearLayout = (LinearLayout) mView.getParent();
                                        (linearLayout).removeView(mView);
                                        (linearLayout).invalidate();

                                    }
                                })
                                .start();
                    } else {
                        mView.animate()
                                .y(mItemWidth - e2.getRawY())
                                .alpha(ALPHA)
                                .setDuration(AMINATION_DURATION)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout linearLayout = (LinearLayout) mView.getParent();
                                        (linearLayout).removeView(mView);
                                        (linearLayout).invalidate();

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