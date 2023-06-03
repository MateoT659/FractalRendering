package com.matoe.fractals;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {
    final int BLEN = 400;
    final int DELTA = (FractalRenderer.WIDTH - BLEN*3)/4+20;
    FractalRenderer game;
    OrthographicCamera camera;
    Texture triangleImage;
    Texture fernImage;
    Texture dragonImage;

    public MainMenu(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH,game.HEIGHT);
        triangleImage = new Texture("TriangleButton.png");
        fernImage = new Texture("FernButton.png");
        dragonImage = new Texture("DragonButton.png");

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,.05f,1);
        game.batch.begin();
        game.batch.draw(dragonImage, DELTA,30, BLEN,BLEN);
        game.batch.draw(triangleImage, (FractalRenderer.WIDTH-BLEN)/2,30, BLEN,BLEN);
        game.batch.draw(fernImage,FractalRenderer.WIDTH-BLEN-DELTA,30, BLEN,BLEN);
        game.batch.end();

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
