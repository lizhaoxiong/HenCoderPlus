package com.hencoder.a12_scalable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

public class ScalableImageView extends View {
    private static final float IMAGE_WIDTH = Utils.dpToPixel(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float offsetX;
    float offsetY;
    float originalOffsetX;
    float originalOffsetY;
    float smallScale;
    float bigScale;
    boolean big;
    float currentScale;
    ObjectAnimator scaleAnimator;
    GestureDetectorCompat detector;
    HenGestureListener gestureListener = new HenGestureListener();
    HenFlingRunner henFlingRunner = new HenFlingRunner();
    ScaleGestureDetector scaleDetector;
    HenScaleListener henScaleListener = new HenScaleListener();
    OverScroller scroller;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        detector = new GestureDetectorCompat(context, gestureListener);
        scroller = new OverScroller(context);
        scaleDetector = new ScaleGestureDetector(context, henScaleListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originalOffsetX = ((float) getWidth() - bitmap.getWidth()) / 2;
        originalOffsetY = ((float) getHeight() - bitmap.getHeight()) / 2;

        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleDetector.onTouchEvent(event);
        if (!scaleDetector.isInProgress()) {
            result = detector.onTouchEvent(event);
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint);
    }

    private float getCurrentScale() {
        return currentScale;
    }

    private void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
        }
        scaleAnimator.setFloatValues(smallScale, bigScale);
        return scaleAnimator;
    }

    class HenGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
            if (big) {
                offsetX -= distanceX;
                offsetY -= distanceY;
                fixOffsets();
                invalidate();
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {
            if (big) {
                scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                        - (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        - (int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int) (bitmap.getHeight() * bigScale - getHeight()) / 2);

                postOnAnimation(henFlingRunner);
            }
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {
                offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2) * bigScale / smallScale;
                offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2) * bigScale / smallScale;
                fixOffsets();
                getScaleAnimator().start();
            } else {
                getScaleAnimator().reverse();
            }
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }

    class HenFlingRunner implements Runnable {

        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.getCurrX();
                offsetY = scroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }
        }
    }

    private void fixOffsets() {
        offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetX = Math.max(offsetX, - (bitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
        offsetY = Math.max(offsetY, - (bitmap.getHeight() * bigScale - getHeight()) / 2);
    }

    class HenScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
        float initialScale;
        float axisX;
        float axisY;

        private void fixScale(float x, float y, float scale) {
            offsetX = (x - getWidth() / 2f) - (x - getWidth() / 2) * scale / initialScale;
            offsetY = (x - getHeight() / 2f) - (x - getHeight() / 2) * scale / initialScale;
//        offsetX = Math.min(offsetX, (bitmap.getWidth() * scale - getWidth()) / 2);
//        offsetX = Math.max(offsetX, - (bitmap.getWidth() * scale - getWidth()) / 2);
//        offsetY = Math.min(offsetY, (bitmap.getHeight() * scale - getHeight()) / 2);
//        offsetY = Math.max(offsetY, - (bitmap.getHeight() * scale - getHeight()) / 2);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            currentScale = initialScale * detector.getScaleFactor();
            fixScale(axisX,  axisY, bigScale);
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialScale = currentScale;
            axisX = detector.getFocusX();
            axisY = detector.getFocusY();
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
