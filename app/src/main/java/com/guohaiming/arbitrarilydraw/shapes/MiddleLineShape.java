package com.guohaiming.arbitrarilydraw.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.guohaiming.arbitrarilydraw.Shape;

/**
 * Created by guoguo on 17-5-5.
 */

public class MiddleLineShape extends Shape{
    private static final float RCICLE_ADIUS = 5.0f;
    private static final int MIDDLE_LINE_DX = 100;
    private static final int MIDDLE_LINE_DY = 100;

    public MiddleLineShape(Format format, Paint paint) {
        super(format, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        if(mPoints.size() > 1){
            int maxX = canvas.getWidth();
            int maxY = canvas.getHeight();
            Point startPoint = mPoints.get(0);
            Point endPoint = mPoints.get(mPoints.size() -1);
            Point middlePoint = new Point(
                    (int)((startPoint.x + endPoint.x) / 2),
                    (int)((startPoint.y + endPoint.y) / 2));
            int dx = (endPoint.x - startPoint.x);
            int dy = (endPoint.y - startPoint.y);

            canvas.drawCircle(startPoint.x, startPoint.y, RCICLE_ADIUS, mPaint);
            canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y,
                    mPaint);
            canvas.drawCircle(endPoint.x, endPoint.y, RCICLE_ADIUS, mPaint);
            canvas.drawCircle(middlePoint.x, middlePoint.y, RCICLE_ADIUS, mPaint);
            if( Math.abs(dx) < 10){
                int startX = middlePoint.x - MIDDLE_LINE_DX;
                int endX = middlePoint.x + MIDDLE_LINE_DX;
                if(startX < 0) startX = 0;
                if(startX > maxX) startX = maxX;
                if(endX < 0) endX = 0;
                if(endX > maxX) endX = maxX;

                canvas.drawLine(startX, middlePoint.y, endX, middlePoint.y, mPaint);
            }else if(Math.abs(dy) < 10) {
                int startY = middlePoint.y - MIDDLE_LINE_DY;
                int endY = middlePoint.y + MIDDLE_LINE_DY;
                if(startY < 0) startY = 0;
                if(startY > maxY) startY = maxY;
                if(endY < 0) endY = 0;
                if(endY > maxY) endY = maxY;


                canvas.drawLine(middlePoint.x, startY, middlePoint.x, endY, mPaint);
            } else {
                float targetK = -1 * (dx / dy);
                float c = middlePoint.y - targetK * middlePoint.x;

                Point middleLineStartPoint = new Point();
                Point middleLineEndPoint = new Point();
                if(Math.abs(dx) > Math.abs(dy)){
                    middleLineStartPoint.x = middlePoint.x + MIDDLE_LINE_DX;
                    middleLineStartPoint.y = (int)(targetK * middleLineStartPoint.x + c + 0.5);
                    if(middleLineStartPoint.x > maxX) middleLineStartPoint.x = maxX;
                    if(middleLineStartPoint.y < 0) middleLineStartPoint.y = 0;
                    if(middleLineStartPoint.y > maxY) middleLineStartPoint.y = maxY;

                    middleLineEndPoint.x = middlePoint.x - MIDDLE_LINE_DX;
                    middleLineEndPoint.y = (int)(targetK * middleLineEndPoint.x + c + 0.5);
                    if(middleLineEndPoint.x < 0) middleLineEndPoint.x = 0;
                    if(middleLineEndPoint.y < 0) middleLineEndPoint.y = 0;
                    if(middleLineEndPoint.y > maxY) middleLineEndPoint.y = maxY;

                } else {
                    middleLineStartPoint.y = middlePoint.y + MIDDLE_LINE_DY;
                    middleLineStartPoint.x = (int)((middleLineStartPoint.y - c) / targetK);
                    if(middleLineStartPoint.y > maxY) middleLineStartPoint.y = maxY;
                    if(middleLineStartPoint.x < 0) middleLineStartPoint.x = 0;
                    if(middleLineStartPoint.x > maxX) middleLineStartPoint.x = maxX;

                    middleLineEndPoint.y = middlePoint.y - MIDDLE_LINE_DY;
                    middleLineEndPoint.x = (int)((middleLineEndPoint.y - c) / targetK);

                    if(middleLineEndPoint.y < 0) middleLineEndPoint.y = 0;
                    if(middleLineEndPoint.x < 0) middleLineEndPoint.x = 0;
                    if(middleLineEndPoint.x > maxX) middleLineEndPoint.x = maxX;
                }


                canvas.drawLine(middleLineStartPoint.x,middleLineStartPoint.y,
                        middlePoint.x, middlePoint.y, mPaint);

                canvas.drawLine(middlePoint.x,middlePoint.y,
                        middleLineEndPoint.x, middleLineEndPoint.y, mPaint);
            }


        }
    }
}
