package com.guohaiming.arbitrarilydraw;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by guoguo on 17-5-5.
 */

public class Sketch {
    ArrayList<Shape> mShapes = new ArrayList<>();

    public Sketch(){

    }

    public void addShape(Shape shape){
        if(shape != null){
            mShapes.add(shape);
        }
    }

    public void resetSketch(){
        if(!mShapes.isEmpty()){
            mShapes.clear();
        }
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
}
