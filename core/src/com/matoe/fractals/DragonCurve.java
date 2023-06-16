package com.matoe.fractals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.LinkedList;

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
    LinkedList<Vector2> ll;

    void rotateFirst(Vector2 temp){
        temp.rotateAroundDeg(start, -45);
        temp.set((float) (start.x - (start.x - temp.x) / Math.sqrt(2)), (float) (start.y - (start.y - temp.y) / Math.sqrt(2)));
    }
    void rotateSecond(Vector2 temp){
        temp.rotateAroundDeg(pivot, -90);
    }

    void showFrac(){
        ScreenUtils.clear(.1f,0.06f,0.06f,1);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        backButton.batchDraw();
        game.font.draw(game.batch, "Depth: "+depth + " Size: " + (int)Math.pow(2,depth), (float)(Dim.WIDTH-200)/2, 700, 200f, 1, true);
        game.batch.end();

        sr.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i<Math.pow(2, depth); i++){
            Dim.drawLine(sr, ll.get(i), ll.get(i+1), 10-depth/2, color);
        }
        sr.end();
    }

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
        ll = new LinkedList<>();
        ll.add(new Vector2(start));
        ll.add(new Vector2(end));
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        showFrac();


        if(backButton.isPressed())
        {
            game.setScreen(new MainMenu(game));
            dispose();
        } else
        {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

                Vector2 temp;
                for (int i = 1; i < Math.pow(2, depth) + 1; i++) {
                    rotateFirst(ll.get(i));
                }
                for (int i = (int) Math.pow(2, depth)-1; i > -1; i--) {
                    temp = ll.get(i).cpy();
                    rotateSecond(temp);
                    ll.add(temp.cpy());
                }
                depth++;
            }
            if (Gdx.input.isButtonJustPressed((Input.Buttons.RIGHT))) {
                if (depth > 0) {

                    for(int i = 1; i<Math.pow(2, depth-1)+1;i++) {
                        ll.get(i).rotateAroundDeg(start, 45);
                        ll.get(i).set((float) (start.x - (start.x - ll.get(i).x) * Math.sqrt(2)), (float) (start.y - (start.y - ll.get(i).y) * Math.sqrt(2)));
                    }
                    while(ll.size()>Math.pow(2,depth-1)+1) {
                        ll.removeLast();
                    }
                    depth--;
                }

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
        sr.dispose();
        backButton.img.dispose();
    }
}
