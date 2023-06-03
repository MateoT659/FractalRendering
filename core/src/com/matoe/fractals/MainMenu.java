package com.matoe.fractals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class MainMenu implements Screen {

    FractalRenderer game;
    OrthographicCamera camera;
    Texture triangleImage;
    Texture fernImage;
    Texture dragonImage;
    float SCALE;

    public MainMenu(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, FractalRenderer.WIDTH,FractalRenderer.HEIGHT);
        triangleImage = new Texture("TriangleButton.png");
        fernImage = new Texture("FernButton.png");
        dragonImage = new Texture("DragonButton.png");
        SCALE = (float) 400 / dragonImage.getHeight();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,.05f,1);
        game.batch.begin();
        game.batch.draw(dragonImage, FractalRenderer.WIDTH/5 - dragonImage.getWidth()*SCALE/2 -15,30, dragonImage.getWidth()*SCALE, dragonImage.getHeight()*SCALE);
        game.batch.draw(triangleImage, FractalRenderer.WIDTH/2 - triangleImage.getWidth()*SCALE/2,30, triangleImage.getWidth()*SCALE, triangleImage.getHeight()*SCALE);
        game.batch.draw(fernImage,4*FractalRenderer.WIDTH/5 - fernImage.getWidth()*SCALE/2 +15,30, fernImage.getWidth()*SCALE, fernImage.getHeight() * SCALE);
        game.batch.end();
        Rectangle r = new Rectangle();
        if(Gdx.input.isTouched()){

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
