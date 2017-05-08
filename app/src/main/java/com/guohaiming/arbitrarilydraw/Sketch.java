package com.guohaiming.arbitrarilydraw;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by guoguo on 17-5-5.
 */

public class Sketch {

    protected int mSimilartDistance = 50;
    ArrayList<Shape> mShapes = new ArrayList<>();
    ArrayList<Point> mVertexes = new ArrayList<>();

    public void addShape(Shape shape){
        if(shape != null){
            shape.setBelongedToSketch(this);
            mShapes.add(shape);
        }
    }

    public void resetSketch(){
        if(!mShapes.isEmpty()){
            mShapes.clear();
        }
        if(!mVertexes.isEmpty()){
            mVertexes.clear();
        }
    }
    protected boolean isNearedPoint(Point p1, Point p2){
        boolean isSmilar = false;
        if( p1 != null && p2 != null){
            int dx = Math.abs(p1.x - p2.x);
            int dy = Math.abs(p1.y - p2.y);
            Log.d("Shape", "isNearedPoint dx: "+ dx + ", dy : "+dy);
            if(dx < mSimilartDistance && dy < mSimilartDistance){
                isSmilar = true;
            }
        }
        return isSmilar;
    }
    public void popLatestShape(){
        if(!mShapes.isEmpty()){
            mShapes.remove(mShapes.size() -1);
        }
    }

    public void draw(Canvas canvas){
        if(mShapes.isEmpty() || canvas == null)return;
        for (Shape shape: mShapes){
            shape.draw(canvas);
        }
    }

    public Point getNearExistedPoint(Point point){
        Point nearPoint = null;
        Log.d("Shape", "getNearExistedPoint");
        Log.d("Shape", "mVertexes size: "+mVertexes.size());
        for (Point p : mVertexes){
            Log.d("Shape", "p: "+p);
            if(isNearedPoint(point, p)){
                nearPoint = p;
                break;
            }
        }
        return nearPoint;
    }

    public void startDrawShape(){

    }

    public void finishedDrawShpe(){

    }
}
