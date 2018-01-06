package com.gnurt.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gnurt.state.*;
import com.gnurt.manager.*;

public class SnakeGame extends ApplicationAdapter {
	private TextureAtlas textureatlas;
	private SpriteBatch batch;
	public static int WIDTH;
	public static int HEIGHT;
	public static final String TITLE="SNAKE GAME";
	private GameStateManager gsm;

	public SnakeGame(int _WIDTH, int _HEIGHT) {
		SnakeGame.WIDTH=_WIDTH;
		SnakeGame.HEIGHT=_HEIGHT;
	}
	
	@Override
	public void create () {
		textureatlas=new TextureAtlas((Gdx.files.internal("data.atlas")));
		MyAsset.Instance();
		MyCamera.Instance();
		batch=new SpriteBatch();
		gsm=new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm,textureatlas));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	@Override
	 public void resize(int width, int height) {
		MyCamera.Instance().getViewport().update(width, height);
		gsm.resize(width, height);
		
	 }
	@Override
	public void dispose () {
		batch.dispose();
		MyAsset.Instance().dispose();
	}
}
