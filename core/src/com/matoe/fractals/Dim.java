package com.matoe.fractals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Dim {
    static final int WIDTH = 1000;
    static final int HEIGHT = 720;
    static void drawLine(ShapeRenderer sr, Vector2 start, Vector2 end,int width, Color c){
        sr.setColor(c);
        sr.circle(start.x, start.y, width/2);
        sr.circle(end.x, end.y, width/2);
        sr.rectLine(start, end, width);
    }
    static Vector2 average(Vector2 v1, Vector2 v2){
        return new Vector2((v1.x+v2.x)/2, (v1.y+v2.y)/2);
    }
    static void drawTriangle(ShapeRenderer sr, Vector2 p1, Vector2 p2, Vector2 p3, Color c){
        //equilateral triangle
        sr.setColor(c);
        sr.triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
    }

}
