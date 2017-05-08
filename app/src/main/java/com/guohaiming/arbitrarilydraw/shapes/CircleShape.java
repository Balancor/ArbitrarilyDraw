package com.guohaiming.arbitrarilydraw.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.guohaiming.arbitrarilydraw.Shape;

import java.util.ArrayList;

/**
 * Created by guoguo on 17-5-5.
 */

public class CircleShape extends Shape{
    private static final float RCICLE_ADIUS = 5.0f;
    public CircleShape(Format format, Paint paint) {
        super(format, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        int pointSize = mPoints.size();
        if (pointSize > 1){
            Point centerPoint = mPoints.get(0);
            Point onCirclePoint = mPoints.get(pointSize - 1);
            canvas.drawCircle(centerPoint.x, centerPoint.y, RCICLE_ADIUS, mPaint);
            float radius = (float)Math.hypot(centerPoint.x - onCirclePoint.x,
                    centerPoint.y - onCirclePoint.y);
            canvas.drawCircle(centerPoint.x, centerPoint.y, radius,mPaint);
            canvas.drawCircle(onCirclePoint.x, onCirclePoint.y, RCICLE_ADIUS, mPaint);
        }
    }
}
