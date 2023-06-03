package com.matoe.fractals;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.matoe.fractals.FractalRenderer;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Fractal Rendering");
		config.setWindowedMode(FractalRenderer.WIDTH, FractalRenderer.HEIGHT);
		new Lwjgl3Application(new FractalRenderer(), config);
	}
}
