package com.guohaiming.arbitrarilydraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import android.view.View;

import java.util.ArrayList;


/**
 * Created by guoguo on 17-4-24.
 */

public class DrawingView extends View implements DrawingState.OnDrawingStateChangeListener{
    private static final String TAG = "DrawingView";

    Context mContext;
    int mWidth, mHeight;
    Canvas mCanvas;

    DrawingState mDrawingState;
    Shape mDrawingShape;
    Paint mDrawingPaint;
    public DrawingView(Context context){
        this(context, null);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mDrawingState == null) return;
        ArrayList<Sketch> sketches = mDrawingState.getSketchs();
        if(sketches != null && !sketches.isEmpty()){
            for (Sketch sketch: sketches){
                sketch.draw(canvas);
            }
        }
    }

    @Override
    public void onDrawingStateChanged(DrawingState state) {
        mDrawingState  = state;
        mDrawingShape = state.getCurrentShape();
        invalidate();
    }
}
