package com.gnurt.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gnurt.snake.SnakeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1024;
		config.height=700;
		config.x = 100;
		config.y = 0;
		config.resizable=false;
		config.title="SNAKE";
		new LwjglApplication(new SnakeGame(config.width,config.height), config);
	}
}
