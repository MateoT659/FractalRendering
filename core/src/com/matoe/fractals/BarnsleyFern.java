package com.matoe.fractals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class BarnsleyFern implements Screen {
    public class Line{
        Vector2 p1, p2;
        Line(Vector2 p1, Vector2 p2){
            this.p1 = p1;
            this.p2 = p2;
        }
        public Line applyMatrixVector(Matrix3 A, Vector2 b){
          Line ret = new Line(new Vector2(p1), new Vector2(p2));
          ret.p1.sub(bottom).mul(A).add(bottom).add(b);
          ret.p2.sub(bottom).mul(A).add(bottom).add(b);
          return ret;
        }
    }
    FractalRenderer game;
    Color color;
    OrthographicCamera camera;
    Button backButton;
    int depth;
    ShapeRenderer sr;
    Vector2 bottom, top, stemtop;
    Line start, stem;
    Matrix3 A2, A3, A4;
    Vector2 b23, b4;
    ArrayList<Line> toRender;
    ArrayList<Line> duplicate;
    public BarnsleyFern(FractalRenderer game){
        this.game = game;
        bottom = new Vector2(500,100);
        top = new Vector2(500, 600);
        stemtop = new Vector2(500, 178);
        A2 = new Matrix3(new float[]{0.85f, 0.04f, 0, 0.04f, 0.85f, 0, 0,0,1});
        A3 = new Matrix3(new float[]{0.2f, -0.26f, 0, 0.23f, 0.22f, 0, 0,0,1});
        A4 = new Matrix3(new float[]{-0.15f, 0.28f, 0, -0.26f, 0.24f, 0, 0,0,1});
        b23 = new Vector2(0,78f);
        b4 = new Vector2(0,35f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Dim.WIDTH,Dim.HEIGHT);
        backButton = new Button("FernBackButton.png", 0.2f, game, camera);
        depth = 0;
        start = new Line(bottom, top);
        stem = new Line(bottom, stemtop);
        toRender = new ArrayList();
        toRender.add(stem);
        toRender.add(start.applyMatrixVector(A2, b23));
        toRender.add(start.applyMatrixVector(A3,b23));
        toRender.add(start.applyMatrixVector(A4,b4));
        sr = new ShapeRenderer();
        sr.setProjectionMatrix(camera.combined);
        color = new Color(255,255,255,255);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,.05f,0.03f,1);
        camera.update();
        game.batch.begin();
        backButton.batchDraw();
        game.font.draw(game.batch, "Depth: "+depth, (float)(Dim.WIDTH-200)/2, 700, 200f, 1, true);
        game.batch.end();

        sr.begin(ShapeRenderer.ShapeType.Filled);
        for(Line l:toRender){
            Dim.drawLine(sr, l.p1, l.p2,5, color);
        }
        sr.end();

        if(backButton.isPressed()){
            game.setScreen(new MainMenu(game));
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            depth++;
            int size = toRender.size();

            for(int i = 0; i<size; i++){
                toRender.add(toRender.get(0).applyMatrixVector(A2, b23));
                toRender.add(toRender.get(0).applyMatrixVector(A3, b23));
                toRender.add(toRender.get(0).applyMatrixVector(A4, b4));
                toRender.remove(0);
            }
            toRender.add(stem);

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
