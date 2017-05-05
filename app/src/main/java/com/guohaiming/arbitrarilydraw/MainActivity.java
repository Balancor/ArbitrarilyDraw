package com.guohaiming.arbitrarilydraw;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.MotionEvent;
import android.widget.RadioGroup;



public class MainActivity extends Activity {
    RadioGroup mShapeRadioGroup;
    DrawingState mDrawingState;
    DrawingView mDrawingView;
    Shape mCurrentShape;
    Shape.Format mCurrentShapeFormat = Shape.Format.POINT;
    Paint mCurrentPaint = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawingView = (DrawingView)findViewById(R.id.drawing_view);
        mShapeRadioGroup = (RadioGroup)findViewById(R.id.shape_group);
        mShapeRadioGroup.setOnCheckedChangeListener(mOnCheckedChangedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentPaint = new Paint();
        mCurrentPaint.setStyle(Paint.Style.STROKE);
        mCurrentPaint.setColor(Color.BLUE);
        mCurrentPaint.setAntiAlias(true);

        mCurrentShape = Shape.createShape(mCurrentShapeFormat, mCurrentPaint);
        mCurrentShapeFormat = Shape.Format.POINT;

        mCurrentShape.setPaint(mCurrentPaint);
        mDrawingState = new DrawingState(mCurrentShape);
        mDrawingState.setOnDrawingStateChangedListener(mDrawingView);
        mDrawingState.updateState(mCurrentShape);
    }

    RadioGroup.OnCheckedChangeListener mOnCheckedChangedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            Shape.Format format = Shape.Format.POINT;
            switch (checkedId){
                case R.id.shape_point:
                    format = Shape.Format.POINT;
                    break;

                case R.id.shape_line:
                    format = Shape.Format.LINE;
                    break;

                case R.id.shape_circle:
                    format = Shape.Format.CICLE;
                    break;

                case R.id.shape_middle_line:
                    format = Shape.Format.MIDDLE_LINE;
                    break;
            }
            mCurrentShapeFormat = format;
            mCurrentShape.setFormat(mCurrentShapeFormat);
            mDrawingState.updateState(mCurrentShape);
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:

                mCurrentShape = Shape.createShape(mCurrentShapeFormat,mCurrentPaint);
                mCurrentShape.addPoint(new Point((int)event.getX(), (int)event.getY()));

                Sketch topSketch = mDrawingState.getTopSketch();
                topSketch.addShape(mCurrentShape);
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentShape.addPoint(new Point((int)event.getX(), (int)event.getY()));
                mDrawingView.invalidate();
                break;

            case MotionEvent.ACTION_UP:
                mCurrentShape.addPoint(new Point((int)event.getX(), (int)event.getY()));
                break;
        }

        mDrawingState.updateState(mCurrentShape);
        return true;
    }
}
