package com.matoe.fractals;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FractalRenderer extends Game {
	//use matrix transformations to iterate fractals recursively
	//ie 90-degree rotation matrices for dragon curve, then reset the coordinates and do the same

	SpriteBatch batch;
	BitmapFont font;
	static final int WIDTH = 1000;
	static final int HEIGHT = 720;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		setScreen(new MainMenu(this));
	}
}