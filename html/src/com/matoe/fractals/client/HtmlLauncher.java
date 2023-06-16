package com.matoe.fractals.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.matoe.fractals.FractalRenderer;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                GwtApplicationConfiguration config =  new GwtApplicationConfiguration(true);
                config.padHorizontal = 0;
                config.padVertical = 0;
                return config;
                // Fixed size application:
                //return new GwtApplicationConfiguration(1500, 1080);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new FractalRenderer();
        }
}