package com.guohaiming.arbitrarilydraw.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.guohaiming.arbitrarilydraw.Shape;

import java.util.ArrayList;

/**
 * Created by guoguo on 17-5-5.
 */

public class LineShape extends Shape {
    private static final float RCICLE_ADIUS = 5.0f;

    public LineShape(Format format, Paint paint) {
        super(format, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        int pointSize = mPoints.size();
        if(pointSize > 1) {
            Point startPoint = mPoints.get(0);
            Point endPoint = mPoints.get(pointSize - 1);
            canvas.drawCircle(startPoint.x, startPoint.y, RCICLE_ADIUS, mPaint);
            canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y,
                    mPaint);
            canvas.drawCircle(endPoint.x, endPoint.y, RCICLE_ADIUS, mPaint);
        }
    }
}
