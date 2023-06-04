package com.matoe.fractals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class SierpinskiTriangle implements Screen {
    FractalRenderer game;
    OrthographicCamera camera;
    Button backButton;
    int depth;
    public SierpinskiTriangle(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, FractalRenderer.WIDTH,FractalRenderer.HEIGHT);
        backButton = new Button("TriangleBackButton.png", .2f, game, camera);
        depth = 0;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(.05f,0.035f,0.02f,1);
        camera.update();
        game.batch.begin();
        backButton.batchDraw();
        game.font.draw(game.batch, "Depth: "+depth, (float)(FractalRenderer.WIDTH-200)/2, 700, 200f, 1, true);
        game.batch.end();

        if(backButton.isPressed()){
            game.setScreen(new MainMenu(game));
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            depth++;
        }
        if(Gdx.input.isButtonJustPressed((Input.Buttons.RIGHT))){
            if(depth>0){
                depth--;
            }
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
