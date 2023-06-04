package com.matoe.fractals;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {

    FractalRenderer game;
    OrthographicCamera camera;
    Button triangleButton;
    Button fernButton;
    Button dragonButton;

    public MainMenu(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, FractalRenderer.WIDTH,FractalRenderer.HEIGHT);

        dragonButton = new Button("DragonButton.png", .293f, game, camera);
        dragonButton.place(FractalRenderer.WIDTH/5 - dragonButton.getWidth()/2 -15,30);

        triangleButton = new Button("TriangleButton.png",.293f, game, camera);
        triangleButton.place(FractalRenderer.WIDTH/2 - triangleButton.getWidth()/2,30);

        fernButton = new Button("FernButton.png", .293f, game, camera);
        fernButton.place(4*FractalRenderer.WIDTH/5 - fernButton.getWidth()/2 +15,30);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,.05f,1);
        game.batch.begin();

        dragonButton.batchDraw();
        triangleButton.batchDraw();
        fernButton.batchDraw();

        game.batch.end();

        if(dragonButton.isPressed()){
            game.setScreen(new DragonCurve(game));
            dispose();
        }
        if(triangleButton.isPressed()){
            game.setScreen(new SierpinskiTriangle(game));
            dispose();
        }
        if(fernButton.isPressed()){
            game.setScreen(new BarnsleyFern(game));
            dispose();
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
        dragonButton.img.dispose();
        fernButton.img.dispose();
        triangleButton.img.dispose();
    }
}
