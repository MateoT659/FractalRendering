package com.matoe.fractals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class SierpinskiTriangle implements Screen {
    public class Triangle{
        Vector2 p1, p2, p3;
        Triangle(Vector2 p1, Vector2 p2, Vector2 p3){
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
        public ArrayList<Triangle> expandTriangle(){
            Triangle t1, t2, t3;
            Vector2 a12, a23, a13;
            a12 = Dim.average(p1,p2);
            a23 = Dim.average(p2,p3);
            a13 = Dim.average(p1,p3);
            renderingList.addAll(Arrays.asList(a12,a23,a13));
            t1 = new Triangle(p1, a12, a13);
            t2 = new Triangle(p2, a12, a23);
            t3 = new Triangle(p3, a13, a23);
            return new ArrayList<Triangle>(Arrays.asList(t1, t2, t3));
        }
    }
    FractalRenderer game;
    OrthographicCamera camera;
    Button backButton;
    int depth;
    ShapeRenderer sr;
    Color color;
    ArrayList<Vector2> renderingList;
    ArrayList<Triangle> triangleList;

    public SierpinskiTriangle(FractalRenderer game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Dim.WIDTH,Dim.HEIGHT);
        backButton = new Button("TriangleBackButton.png", .2f, game, camera);
        depth = 0;
        sr = new ShapeRenderer();
        sr.setProjectionMatrix(camera.combined);
        renderingList = new ArrayList<>();
        renderingList.add(new Vector2(500, 650));
        renderingList.add(new Vector2(168,75));
        renderingList.add(new Vector2(832, 75));
        triangleList = new ArrayList<>();
        triangleList.add(new Triangle(renderingList.get(0), renderingList.get(1), renderingList.get(2)));
        color = new Color(202,101,51, 255);
        sr.setColor(color);
    }
    @Override
    public void show() {

    }
    public void renderTriangles(){
        sr.begin(ShapeRenderer.ShapeType.Line);
        for(int i = 0; i<renderingList.size(); i+=3){
            Dim.drawTriangle(sr, renderingList.get(i), renderingList.get(i+1), renderingList.get(i+2));
        }
        sr.end();
    }

    public void increaseTriangles(){
        int currentSize = triangleList.size();
        ArrayList<Triangle> newTriangles;
        for(int i = 0; i<currentSize; i++){
            newTriangles = triangleList.get(0).expandTriangle();
            triangleList.addAll(newTriangles);
            triangleList.remove(0);
        }
    }

    public void decreaseTriangles(){
        int currentSize = triangleList.size();
        Triangle newTriangle;
        for(int i = 0; i<currentSize; i+=3){
            newTriangle = new Triangle(triangleList.get(0).p1, triangleList.get(1).p1, triangleList.get(2).p1);
            triangleList.remove(2);
            triangleList.remove(1);
            triangleList.remove(0);
            triangleList.add(newTriangle);
        }
        for(int i = 0; i<Math.pow(3,depth+1); i++){
            renderingList.remove(renderingList.size()-1);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(.05f,0.035f,0.02f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        backButton.batchDraw();
        game.font.draw(game.batch, "Depth: "+depth, (float)(Dim.WIDTH-200)/2, 700, 200f, 1, true);
        game.batch.end();

        renderTriangles();

        if(backButton.isPressed()){
            game.setScreen(new MainMenu(game));
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            increaseTriangles();
            depth++;
        }
        if(Gdx.input.isButtonJustPressed((Input.Buttons.RIGHT))){
            if(depth>0){
                depth--;
                decreaseTriangles();
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
