package com.kubik.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

public class DrawOnTouchView extends View {

    private Path path;
    private Paint paint = new Paint();
    private Paint restored = new Paint();
    private GestureDetector gestureDetector;
    private ArrayList<SavedPath> list = new ArrayList<>();


    public DrawOnTouchView(Context context, AttributeSet attrs) {
        super(context);

        gestureDetector = new GestureDetector(context, new GestureListener());

        path = new Path();

        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        restored.setAntiAlias(true);
        restored.setStrokeWidth(6f);
        restored.setStyle(Paint.Style.STROKE);
        restored.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setPaintColor(int color) {
        list.add(new SavedPath());
        paint.setColor(color);
        path = new Path();
    }

    public int getPaintCurrentColor() {
        return paint.getColor();
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            path.reset();
            return true;
        }
    }

    private class SavedPath {
        Path p;
        int color;

        SavedPath() {
            this.p = path;
            this.color = paint.getColor();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (SavedPath s : list) {
            restored.setColor(s.color);
            canvas.drawPath(s.p, restored);
        }

        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}