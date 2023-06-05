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


}
