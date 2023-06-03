package com.matoe.fractals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class Button {
    int x, y, width, height;
    Texture img;
    FractalRenderer game;
    OrthographicCamera camera;
    public Texture getImg() {
        return img;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void place(int x, int y){
        this.x = x;
        this.y = y;
        rectangle.setLocation(x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    Rectangle rectangle;
    public Rectangle getRectangle() {
        return rectangle;
    }
    public Button(String imgfilepath, float scale, FractalRenderer game, OrthographicCamera camera){
        this.img = new Texture(imgfilepath);
        this.x = 0;
        this.y = 0;
        this.width = Math.round(img.getWidth()*scale);
        this.height = Math.round(img.getHeight()*scale);
        rectangle = new Rectangle(0, 0, this.width, this.height);
        this.game = game;
        this.camera = camera;
    }
    public void batchDraw(){
        game.batch.draw(img, x, y, width, height);
    }

    public boolean isPressed(){
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            return getRectangle().contains(touchPos.x, touchPos.y);
        }
        return false;
    }
}
