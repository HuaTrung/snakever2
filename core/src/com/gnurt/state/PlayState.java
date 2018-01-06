package com.gnurt.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gnurt.game.*;
import com.gnurt.SnakeAI.*;
public class PlayState extends State {
	
	private static final float CAM_WIDTH = Gdx.graphics.getWidth();
	private static final float CAM_HEIGHT = Gdx.graphics.getHeight();
	private enum GameState {
		GAME_READY, GAME_RUNNING, GAME_PAUSED, GAME_OVER;
	}
	
	private GameState STATE;
	private Renderer renderer;
	private SnakeController snakeController;
	private SnakeControllerAI snakeControllerAI;
	private BaitController baitController;
	private World world;
	private boolean resetGame;
	private float stateTime=0f;
	private Sound soundBait;
	
	protected PlayState(GameStateManager gsm) {
		super(gsm);
		this.world = new World(CAM_WIDTH, CAM_HEIGHT);
		this.renderer = new Renderer(world, CAM_WIDTH, CAM_HEIGHT);
		this.snakeController = new SnakeController(world, CAM_WIDTH, CAM_HEIGHT);
		this.snakeControllerAI=new SnakeControllerAI(world,CAM_WIDTH,CAM_HEIGHT);
		this.baitController = new BaitController(world);
		Gdx.input.setInputProcessor(this);
		STATE = GameState.GAME_READY;
		this.soundBait=Gdx.audio.newSound(Gdx.files.internal("data/eat.wav"));
		resetGame=true;
	}
	
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(SpriteBatch sb) {
		switch (STATE) {
		case GAME_READY:
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			renderer.renderReady();			
			break;
		case GAME_RUNNING:
			stateTime+=delta;
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
			baitController.update();
			stateTime+=delta;
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
				renderer.render();
			if(snakeControllerAI.getSnake().getSnakeBody().size()==13||snakeController.getSnake().getSnakeBody().size()==8)
				STATE=GameState.GAME_OVER;
			resetGame=false;
			break;
		case GAME_PAUSED:
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			renderer.render();
			break;
		case GAME_OVER:
			Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			renderer.renderFail();
			break;
		}
	}
	private void reset() {
		this.world = new World(CAM_WIDTH, CAM_HEIGHT);
		this.snakeController = new SnakeController(world, CAM_WIDTH, CAM_HEIGHT);
		this.baitController = new BaitController(world);
		this.snakeControllerAI=new SnakeControllerAI(world, CAM_WIDTH, CAM_HEIGHT);
		this.stateTime=0f;
		this.renderer = new Renderer(world, CAM_WIDTH, CAM_HEIGHT);
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
