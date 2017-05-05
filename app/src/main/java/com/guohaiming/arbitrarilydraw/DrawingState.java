package com.guohaiming.arbitrarilydraw;

import android.graphics.Paint;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by guoguo on 17-4-24.
 */

public class DrawingState {
    private Shape mCurrentShape;


    private ArrayList<OnDrawingStateChangeListener> mListeners;

    ArrayList<Sketch> mSketchs = new ArrayList<>();
    interface OnDrawingStateChangeListener {
        void onDrawingStateChanged(DrawingState state);
    }

    public DrawingState(Shape shape){
        mCurrentShape = shape;
        mSketchs.add(new Sketch());
        mListeners = new ArrayList<>();

        updateState(mCurrentShape);
    }

    public void addSketch(Sketch sketch){
        if(sketch != null){
            mSketchs.add(sketch);
        }
    }

    public void updateState(Shape shape){
        mCurrentShape = shape;
        for (OnDrawingStateChangeListener listener:mListeners) {
            listener.onDrawingStateChanged(this);
        }
    }

    public Shape getCurrentShape() {
        return mCurrentShape;
    }



    public ArrayList<Sketch> getSketchs(){
        return mSketchs;
    }

    public Sketch getTopSketch(){
        Sketch sketch = null;
        if(mSketchs != null && !mSketchs.isEmpty()){
            sketch = mSketchs.get(mSketchs.size() - 1);
        }
        return sketch;
    }

    public void setCurrentShape(Shape mCurrentShape) {
        this.mCurrentShape = mCurrentShape;
        updateState(this.mCurrentShape);
    }

    public void setPaint(Paint mPaint) {
        updateState(mCurrentShape);
    }

    public void setOnDrawingStateChangedListener(OnDrawingStateChangeListener l){
        if(l != null && mListeners != null){
            mListeners.add(l);
        }
    }
}
