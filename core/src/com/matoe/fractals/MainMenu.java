package com.matoe.fractals;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {

    FractalRenderer game;
    OrthographicCamera camera;
    Button triangleButton;
    Button fernButton;
    Button dragonButton;
    ShapeRenderer sr;

    public MainMenu(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Dim.WIDTH,Dim.HEIGHT);

        dragonButton = new Button("DragonButton.png", .293f, game, camera);
        dragonButton.place(Dim.WIDTH/5 - dragonButton.getWidth()/2 -15,30);

        triangleButton = new Button("TriangleButton.png",.293f, game, camera);
        triangleButton.place(Dim.WIDTH/2 - triangleButton.getWidth()/2,30);

        fernButton = new Button("FernButton.png", .293f, game, camera);
        fernButton.place(4*Dim.WIDTH/5 - fernButton.getWidth()/2 +15,30);

        sr = new ShapeRenderer();
        sr.setColor(1,1,1,1);
        sr.setProjectionMatrix(camera.combined);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,.05f,1);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.font.draw(game.batch, "Iterated Fractal Rendering", (float)(Dim.WIDTH-200)/2,650, 200f, 1, true);
        game.font.draw(game.batch, "For each fractal, left click to increase iteration depth, and right click to decrease iteration depth", (float)(Dim.WIDTH-310)/2,620, 310f, 1, true);
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
