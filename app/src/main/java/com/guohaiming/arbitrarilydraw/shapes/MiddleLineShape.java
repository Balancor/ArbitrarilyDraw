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

public class MiddleLineShape extends Shape {
    private static final float RCICLE_ADIUS = 5.0f;
    private static final int MIDDLE_LINE_DX = 100;
    private static final int MIDDLE_LINE_DY = 100;

    public MiddleLineShape(Format format, Paint paint) {
        super(format, paint);
        setSimilartDistance(10);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mPoints.size() > 1) {
            int maxX = canvas.getWidth();
            int maxY = canvas.getHeight();
            Point startPoint = mPoints.get(0);
            Point endPoint = mPoints.get(mPoints.size() - 1);
            Point middlePoint = new Point(
                    (int) ((startPoint.x + endPoint.x) / 2),
                    (int) ((startPoint.y + endPoint.y) / 2));
            int dx = endPoint.x - startPoint.x;
            int dy = endPoint.y - startPoint.y;

            canvas.drawCircle(startPoint.x, startPoint.y, RCICLE_ADIUS, mPaint);
            canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y,
                    mPaint);
            canvas.drawCircle(endPoint.x, endPoint.y, RCICLE_ADIUS, mPaint);
            canvas.drawCircle(middlePoint.x, middlePoint.y, RCICLE_ADIUS, mPaint);
            if (Math.abs(dx) < 10) {
                int startX = middlePoint.x - MIDDLE_LINE_DX;
                int endX = middlePoint.x + MIDDLE_LINE_DX;
                if (startX < 0) startX = 0;
                if (startX > maxX) startX = maxX;
                if (endX < 0) endX = 0;
                if (endX > maxX) endX = maxX;

                canvas.drawLine(startX, middlePoint.y, endX, middlePoint.y, mPaint);
            } else if (Math.abs(dy) < 10) {
                int startY = middlePoint.y - MIDDLE_LINE_DY;
                int endY = middlePoint.y + MIDDLE_LINE_DY;
                if (startY < 0) startY = 0;
                if (startY > maxY) startY = maxY;
                if (endY < 0) endY = 0;
                if (endY > maxY) endY = maxY;


                canvas.drawLine(middlePoint.x, startY, middlePoint.x, endY, mPaint);
            } else {
                Point middleLineStartPoint = new Point();
                Point middleLineEndPoint = new Point();
                float targetK = -1 * ((float) dx / dy);
                float targetKTimes = targetK * targetK;

                float tmp = (float)(Math.sqrt((MIDDLE_LINE_DX * MIDDLE_LINE_DX) / (targetKTimes + 1)));
                float tmpX = tmp + middlePoint.x;
                float tmpY = targetK * tmp + middlePoint.y;

                middleLineStartPoint.x = (int)(tmpX + 0.5);
                middleLineStartPoint.y = (int)(tmpY + 0.5);

                middleLineEndPoint.x = (2 * middlePoint.x - middleLineStartPoint.x);
                middleLineEndPoint.y = (2 * middlePoint.y - middleLineStartPoint.y);

                canvas.drawLine(middleLineStartPoint.x, middleLineStartPoint.y,
                        middleLineEndPoint.x, middleLineEndPoint.y, mPaint);
            }

        }
    }

    @Override
    public void collectionVertexes() {
        Point startPoint = mPoints.get(0);
        Point endPoint = mPoints.get(mPoints.size() - 1);
        Point middlePoint = new Point(
                (int) ((startPoint.x + endPoint.x) / 2),
                (int) ((startPoint.y + endPoint.y) / 2));
        mVertexes.add(startPoint);
        mVertexes.add(middlePoint);
        mVertexes.add(endPoint);

    }
}
