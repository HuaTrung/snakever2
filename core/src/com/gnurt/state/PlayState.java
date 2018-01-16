package com.gnurt.state;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gnurt.MySnake.SnakeController;
import com.gnurt.SnakeAI.*;
import com.gnurt.manager.MyRender;
import com.gnurt.manager.World;

import MyBait.BaitController;

public class PlayState extends State {
	
	private static final float WIDTH = Gdx.graphics.getWidth();
	private static final float HEIGHT = Gdx.graphics.getHeight();
	private enum GameState {
		GAME_READY, GAME_RUNNING, GAME_PAUSED, GAME_OVER;
	}
	
	private GameState STATE;
	//private Renderer renderer;
	private SnakeController snakeController;
	private SnakeControllerAI snakeControllerAI;
	private BaitController baitController;
	private World world;
	private boolean resetGame;
	private float stateTime=0f;
	private Sound soundBait;
	
	public PlayState(GameStateManager gsm,TextureAtlas ta) {
		super(gsm);
		this.world = new World(WIDTH, HEIGHT);
		this.snakeController = new SnakeController(world, WIDTH, HEIGHT);
		this.snakeControllerAI=new SnakeControllerAI(world,WIDTH,HEIGHT);
		this.baitController = new BaitController(world);
		MyRender.initInstance(world,WIDTH,HEIGHT,ta);
		//Gdx.input.setInputProcessor(this);
		STATE = GameState.GAME_RUNNING;
		this.soundBait=Gdx.audio.newSound(Gdx.files.internal("data/eat.wav"));
		resetGame=true;
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				if(keycode == Keys.W || keycode == Keys.UP) 
					snakeController.upPressed();
				if(keycode == Keys.S || keycode == Keys.DOWN) 
					snakeController.downPressed();
				if(keycode == Keys.A || keycode == Keys.LEFT)
					snakeController.leftPressed();
				if(keycode == Keys.D || keycode == Keys.RIGHT)
					snakeController.rightPressed();
				return true;
			}
			@Override
			public boolean keyUp(int keycode) {
				if(keycode == Keys.W || keycode == Keys.UP)
					snakeController.upReleased();
				if(keycode == Keys.S || keycode == Keys.DOWN)
					snakeController.downReleased();
				if(keycode == Keys.A || keycode == Keys.LEFT)
					snakeController.leftReleased();
				if(keycode == Keys.D || keycode == Keys.RIGHT)
					snakeController.rightReleased();
				return true;
			}
		});
	}
	
	@Override
	public void handleInput() {
	}
	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(float dt) { 
		switch (STATE) {
		case GAME_READY:
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			MyRender.getInstance().renderReady();			
			break;
		case GAME_RUNNING:
			stateTime+=dt;
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
			baitController.update();
			stateTime+=dt;
			if(stateTime>0.1f) {
				stateTime=0;
				if(snakeController.update()) {
					STATE=GameState.GAME_OVER;
				}
				else {
					if(snakeController.collidedWithBait())
						soundBait.play();
				}
				
				if(snakeControllerAI.update(FindMinimumPath.getDir(snakeControllerAI.getSnake(), baitController.getBait(),resetGame))) {
					soundBait.play();
				}
			}		
			if(STATE!=GameState.GAME_OVER)
				MyRender.getInstance().render();
			if(snakeControllerAI.getSnake().getSnakeBody().size()==13||snakeController.getSnake().getSnakeBody().size()==8)
				STATE=GameState.GAME_OVER;
			resetGame=false;
			break;
		case GAME_PAUSED:
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			MyRender.getInstance().render();
			break;
		case GAME_OVER:
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			MyRender.getInstance().renderFail();
			reset();
			break;
		}
		handleInput();
	}
	private void reset() {
		this.world = new World(WIDTH, HEIGHT);
		this.snakeController = new SnakeController(world, WIDTH, HEIGHT);
		this.baitController = new BaitController(world);
		this.snakeControllerAI=new SnakeControllerAI(world, WIDTH, HEIGHT);
		this.stateTime=0f;
		//this.renderer = new Renderer(world, CAM_WIDTH, CAM_HEIGHT);
		STATE = GameState.GAME_READY;
		FindMinimumPath.resultReturn.clear();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
