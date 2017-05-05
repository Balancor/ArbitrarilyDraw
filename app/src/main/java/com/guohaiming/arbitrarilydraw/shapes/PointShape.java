package com.guohaiming.arbitrarilydraw.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.guohaiming.arbitrarilydraw.Shape;

/**
 * Created by guoguo on 17-5-5.
 */

public class PointShape extends Shape {
    private static final float RCICLE_ADIUS = 5.0f;
    public PointShape(Format format, Paint paint) {
        super(format, paint);
    }
    @Override
    public void draw(Canvas canvas) {
        if(mPoints.isEmpty()) return;
        Point point = mPoints.get(0);
        canvas.drawCircle(point.x, point.y, RCICLE_ADIUS, mPaint);
    }
}
