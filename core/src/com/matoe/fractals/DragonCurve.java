package com.matoe.fractals;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class DragonCurve implements Screen {
    FractalRenderer game;
    OrthographicCamera camera;
    Button backButton;
    public DragonCurve(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, FractalRenderer.WIDTH,FractalRenderer.HEIGHT);
        backButton = new Button("DragonBackButton.png", .2f, game, camera);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(.5f,0,0,1);
        camera.update();
        game.batch.begin();
        backButton.batchDraw();
        game.batch.end();

        if(backButton.isPressed()){
            game.setScreen(new MainMenu(game));
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
