package com.guohaiming.arbitrarilydraw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.guohaiming.arbitrarilydraw.shapes.CircleShape;
import com.guohaiming.arbitrarilydraw.shapes.LineShape;
import com.guohaiming.arbitrarilydraw.shapes.MiddleLineShape;
import com.guohaiming.arbitrarilydraw.shapes.PointShape;

import java.util.ArrayList;

/**
 * Created by guoguo on 17-4-24.
 */

abstract public class Shape {
    private static final String TAG = "Shape";
    protected int mSimilartDistance = 5;
    public enum Format {
        POINT,
        LINE,
        CICLE,
        MIDDLE_LINE
    }

    protected Format mFormat;
    protected ArrayList<Point> mPoints = new ArrayList<>();
    protected Paint mPaint;
    protected Sketch mBelongedToSketch;
    protected ArrayList<Point> mVertexes = new ArrayList<>();
    public Shape(Format format, Paint paint){
        mFormat = format;
        mPaint = paint;
    }

    public void setBelongedToSketch(Sketch sketch){
        if(sketch != null){
            mBelongedToSketch = sketch;
        }
    }

    public static Shape createShape(Format format, Paint paint){
        if(format == null){
            return null;
        }
        switch (format){
            case LINE:
                return new LineShape(format, paint);
            case CICLE:
                return new CircleShape(format, paint);
            case MIDDLE_LINE:
                return new MiddleLineShape(format, paint);
            case POINT:
            default:
                return new PointShape(format, paint);
        }
    }

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint p){
        if(p != null)
            mPaint = p;
    }
    public Format getFormat(){
        return  mFormat;
    }

    public void setFormat(Format format){
        mFormat = format;
    }

    public ArrayList<Point> getPoints(){
        return mPoints;
    }

    public void addPoint(Point p){
        if(p != null && mPoints != null){
            int smilarPointIndex = filterSmilarPoint(p);
            if(smilarPointIndex >= 0)return;
            Point nearedPoint = getExistedNearPoint(p);
            Log.d(TAG, "targetPoint = "+p+", nearPoint = "+nearedPoint);
            if(nearedPoint != null){
                mPoints.add(nearedPoint);
            } else {
                mPoints.add(p);
            }
        }
    }

    public ArrayList<Point> getVertexes(){
        return mVertexes;
    }

    public void collectionVertexes(){
        mVertexes.add(mPoints.get(0));
        mVertexes.add(mPoints.get(mPoints.size() - 1));
    }
    public void startToDrawShape(){

    }

    public void finishedToDrawShape(){
        collectionVertexes();
        mBelongedToSketch.mVertexes.addAll(mVertexes);
    }

    private  Point getExistedNearPoint(Point point){
        if(mBelongedToSketch == null)return null;
        Point nearPoint = mBelongedToSketch.getNearExistedPoint(point);
        return nearPoint;
    }

    public void reset(){
        if(mPoints !=null){
            mPoints.clear();
        }
    }

    abstract public void draw(Canvas canvas);

    public int filterSmilarPoint(Point newPoint){
        int index = -1;
        for (int i = 0; i < mPoints.size(); i++){
            Point p = mPoints.get(i);
            if(isSmilarPoint(p, newPoint)){
                index = i;
                break;
            }
        }
        return index;
    }

    public void setSimilartDistance(int distance){
        mSimilartDistance = distance;
    }

    protected boolean isSmilarPoint(Point p1, Point p2){
        boolean isSmilar = false;
        if( p1 != null && p2 != null){
            int dx = Math.abs(p1.x - p2.x);
            int dy = Math.abs(p1.y - p2.y);
            if(dx < mSimilartDistance && dy < mSimilartDistance){
                isSmilar = true;
            }
        }
        return isSmilar;
    }

}
