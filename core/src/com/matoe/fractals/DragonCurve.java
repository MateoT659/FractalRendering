package com.matoe.fractals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.xml.crypto.dsig.Transform;

public class DragonCurve implements Screen {
    FractalRenderer game;
    OrthographicCamera camera;
    Button backButton;
    ShapeRenderer sr;
    Color color;
    int depth;
    Vector2 start;
    Vector2 pivot;
    Vector2 end;
    public DragonCurve(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Dim.WIDTH,Dim.HEIGHT);
        backButton = new Button("DragonBackButton.png", .2f, game, camera);
        depth = 0;
        sr = new ShapeRenderer();
        sr.setProjectionMatrix(camera.combined);
        start = new Vector2(250, 440);
        pivot = new Vector2(550,140);
        end = new Vector2(850, 440);
        color = new Color(.5f,0,0,1);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(.1f,0.06f,0.06f,1);
        camera.update();
        game.batch.begin();
        backButton.batchDraw();
        game.font.draw(game.batch, "Depth: "+depth, (float)(Dim.WIDTH-200)/2, 700, 200f, 1, true);



        game.batch.end();



        sr.begin(ShapeRenderer.ShapeType.Filled);
        Dim.drawLine(sr, start, end, 10, color);
        sr.end();

        if(backButton.isPressed()){
            game.setScreen(new MainMenu(game));
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            depth++;
            end.rotateAroundDeg(start,-45);
            end.set((float)(start.x - (start.x-end.x)/Math.sqrt(2)), (float)(start.y - (start.y - end.y)/Math.sqrt(2)));
            start.rotateAroundDeg(pivot,-90);
        }
        if(Gdx.input.isButtonJustPressed((Input.Buttons.RIGHT))){
            if(depth>0){
                depth--;
                end.rotateAroundDeg(start,45);
                end.set((float)(start.x - (start.x-end.x)*Math.sqrt(2)), (float)(start.y - (start.y - end.y)*Math.sqrt(2)));
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
