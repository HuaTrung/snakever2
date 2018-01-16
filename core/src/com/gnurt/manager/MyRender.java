package com.gnurt.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gnurt.MySnake.Snake;

import MyBait.Bait;

public class MyRender {
	private static MyRender instance = null;
	TextureAtlas ta;
	public static void initInstance(World world, float width, float height,TextureAtlas _ta) {
		if (instance == null)
			instance = new MyRender(world, width, height,_ta);
		return;
	}

	public static MyRender getInstance() {
		if (instance != null)
			return instance;
		return null;
	}

	private static World world;
	private OrthographicCamera cam;
	private Bait bait;
	private float worldWidth, worldHeight;
	private SpriteBatch spriteBatch;
	private BitmapFont font;

	private Snake snake;
	private Snake snakeAI;
	// texture snake
	Texture snakeTexture1;
	Texture snakeTexture2;

	Texture snakeTexture1AI;
	Texture snakeTexture2AI;
	Texture fruit;

	TextureRegion[] headSnakeFrames;
	TextureRegion[] tailSnakeFrames;
	TextureRegion bodySnakeFrame;
	TextureRegion[] curveSnake;

	TextureRegion[] headSnakeFramesAI;
	TextureRegion[] tailSnakeFramesAI;
	TextureRegion bodySnakeFrameAI;
	TextureRegion[] curveSnakeAI;

	TextureRegion[] baitFruit;

	private TextureRegion leftBackground;
	private TextureRegion rightBackground;
	private TextureRegion botBackground;
	private TextureRegion topleftBackground;
	private TextureRegion toprightBackground;
	private TextureRegion botleftBackground;
	private TextureRegion botrightBackground;
	private TextureRegion mainBackground;
	private TextureRegion topBackground;
	public MyRender(World world, float width, float height,TextureAtlas _ta) {
		MyRender.world = world;
		worldWidth = width;
		worldHeight = height;
		ta=_ta;
		this.cam = new OrthographicCamera(width, height);
		this.cam.position.set((width/2), (height/2), 0);
		this.cam.update();
		snake = world.getSnake();
		snakeAI=world.getSnakeAI();
		bait = world.getBait();
		drawbait=0;
		font = new BitmapFont();
		spriteBatch = new SpriteBatch();
		
		// take texture snake
		snakeTexture1 = new Texture(Gdx.files.internal("data/SpriteSnake.png"));
		snakeTexture2 = new Texture(Gdx.files.internal("data/SpriteSnake2.png"));
		
		snakeTexture1AI= new Texture(Gdx.files.internal("data/SpriteSnakeAI.png"));
		snakeTexture2AI= new Texture(Gdx.files.internal("data/SpriteSnake2AI.png"));
		fruit=new Texture(Gdx.files.internal("data/fruit.png"));
		headSnakeFrames=new TextureRegion[4];
		tailSnakeFrames=new TextureRegion[4];
		bodySnakeFrame=new TextureRegion();
		curveSnake=new TextureRegion[4];
		
		headSnakeFramesAI=new TextureRegion[4];
		tailSnakeFramesAI=new TextureRegion[4];
		bodySnakeFrameAI=new TextureRegion();
		curveSnakeAI=new TextureRegion[4];
		
		baitFruit=new TextureRegion[12];
		
		headSnakeFramesAI[0]=new TextureRegion(snakeTexture1,520,0,200,270);
		headSnakeFramesAI[1]=new TextureRegion(snakeTexture1,300,20,200,270);
		headSnakeFramesAI[2]=new TextureRegion(snakeTexture1,0,20,270,200);
		headSnakeFramesAI[3]=new TextureRegion(snakeTexture1,730,20,270,200);
		
		tailSnakeFramesAI[0]=new TextureRegion(snakeTexture1,270,300,200,200);
		tailSnakeFramesAI[1]=new TextureRegion(snakeTexture1,770,300,200,200);
		tailSnakeFramesAI[2]=new TextureRegion(snakeTexture1,520,300,200,200);
		tailSnakeFramesAI[3]=new TextureRegion(snakeTexture1,20,300,200,200);
		
		bodySnakeFrameAI=new TextureRegion(snakeTexture1,20,540,200,200);
		
		curveSnakeAI[0]=new TextureRegion(snakeTexture2,20,20,200,200);
		curveSnakeAI[1]=new TextureRegion(snakeTexture2,270,20,200,200);
		curveSnakeAI[2]=new TextureRegion(snakeTexture2,520,20,200,200);
		curveSnakeAI[3]=new TextureRegion(snakeTexture2,770,20,200,200);
		
		headSnakeFrames[0]=new TextureRegion(snakeTexture1AI,520,0,200,270);
		headSnakeFrames[1]=new TextureRegion(snakeTexture1AI,300,20,200,270);
		headSnakeFrames[2]=new TextureRegion(snakeTexture1AI,0,20,270,200);
		headSnakeFrames[3]=new TextureRegion(snakeTexture1AI,730,20,270,200);
		
	
		tailSnakeFrames[0]=new TextureRegion(snakeTexture1AI,270,300,200,200);
		tailSnakeFrames[1]=new TextureRegion(snakeTexture1AI,770,300,200,200);
		tailSnakeFrames[2]=new TextureRegion(snakeTexture1AI,520,300,200,200);
		tailSnakeFrames[3]=new TextureRegion(snakeTexture1AI,20,300,200,200);
		
		bodySnakeFrame=new TextureRegion(snakeTexture1AI,20,540,200,200);
		
		curveSnake[0]=new TextureRegion(snakeTexture2AI,20,20,200,200);
		curveSnake[1]=new TextureRegion(snakeTexture2AI,270,20,200,200);
		curveSnake[2]=new TextureRegion(snakeTexture2AI,520,20,200,200);
		curveSnake[3]=new TextureRegion(snakeTexture2AI,770,20,200,200);
		
	
		baitFruit[0]=new TextureRegion(fruit,20,20,200,200);
		baitFruit[1]=new TextureRegion(fruit,270,20,200,200);
		baitFruit[2]=new TextureRegion(fruit,520,20,200,200);
		baitFruit[3]=new TextureRegion(fruit,770,20,200,200);
		baitFruit[4]=new TextureRegion(fruit,20,270,200,200);
		baitFruit[5]=new TextureRegion(fruit,270,270,200,200);
		baitFruit[6]=new TextureRegion(fruit,520,270,200,200);
		baitFruit[7]=new TextureRegion(fruit,770,270,200,200);
		baitFruit[8]=new TextureRegion(fruit,20,520,200,200);
		baitFruit[9]=new TextureRegion(fruit,270,520,200,200);
		baitFruit[10]=new TextureRegion(fruit,520,520,200,200);
		baitFruit[11]=new TextureRegion(fruit,770,520,200,200);
		
		leftBackground=ta.findRegion("left");
		rightBackground=ta.findRegion("right");
		botBackground=ta.findRegion("bot");
		topBackground=ta.findRegion("top");
		topleftBackground=ta.findRegion("topleft");
		toprightBackground=ta.findRegion("topright");
		botleftBackground=ta.findRegion("botleft");
		botrightBackground=ta.findRegion("botright");
		mainBackground=ta.createSprite("main");
		
	}

	public void renderFail() {
		if ((world.getSnakeAI().getSnakeBody().size() - 3) >= 10 || (world.getSnake().getSnakeBody().size() - 3) >= 5) {
			if ((world.getSnakeAI().getSnakeBody().size() - 3) >= 10) {
				spriteBatch.begin();
				font.setColor(Color.BLACK);
				font.draw(spriteBatch, " GNURT WIN", worldWidth / 2 - 40, worldHeight / 2 + 20);
				spriteBatch.end();
			} else {
				spriteBatch.begin();
				font.setColor(Color.BLACK);
				font.draw(spriteBatch, " YOU WIN", worldWidth / 2 - 40, worldHeight / 2 + 20);
				spriteBatch.end();
			}
		} else {
			spriteBatch.begin();
			font.setColor(Color.BLACK);
			font.draw(spriteBatch, " YOU LOSE", worldWidth / 2 - 40, worldHeight / 2 + 20);
			spriteBatch.end();
		}
	}

	public void renderReady() {
		spriteBatch.begin();
		font.draw(spriteBatch, "Touch to begin!", worldWidth / 2 - 50, worldHeight / 2 + 20);
		spriteBatch.end();
	}

	public void render() {
		drawBoard();
		drawSnakeAI();
		drawSnake();
		drawScore();
		drawScoreAI();
		if (bait.isSpawned())
			drawBait();
	}

	private void drawScore() {
		spriteBatch.begin();
		font.draw(spriteBatch, "Player Score: " + (world.getSnake().getSnakeBody().size() - 3), 50, 690);
		spriteBatch.end();
	}

	private void drawScoreAI() {
		spriteBatch.begin();
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, "GNURT Score: " + (world.getSnakeAI().getSnakeBody().size() - 3), 550, 690);
		spriteBatch.end();
	}

	private void drawBoard() {
		//shapeRenderer.begin(ShapeType.Line);
		spriteBatch.begin();
		//shapeRenderer.setColor(0, 0, 0, 1);
		//shapeRenderer.rect((worldWidth - worldHeight + 2) / 2, 1, worldHeight - 2, worldHeight - 2);
		spriteBatch.draw(topleftBackground,world.getBoardGame()[0][0].x,world.getBoardGame()[0][0].y,
				world.getBoardGame()[0][0].width,	world.getBoardGame()[0][0].height);
		spriteBatch.draw(toprightBackground,world.getBoardGame()[0][31].x,world.getBoardGame()[0][31].y,
				world.getBoardGame()[0][31].width,	world.getBoardGame()[0][31].height);
		spriteBatch.draw(botleftBackground,world.getBoardGame()[31][0].x,world.getBoardGame()[31][0].y,
				world.getBoardGame()[31][0].width,	world.getBoardGame()[31][0].height);
		spriteBatch.draw(botrightBackground,world.getBoardGame()[31][31].x,world.getBoardGame()[31][31].y,
				world.getBoardGame()[31][31].width,	world.getBoardGame()[31][31].height);
		
		for(int i=1;i<31;i++)
			spriteBatch.draw(topBackground,world.getBoardGame()[0][i].x,world.getBoardGame()[0][i].y,
					world.getBoardGame()[0][0].height,world.getBoardGame()[0][0].height);
		
		for(int i=1;i<31;i++)
		spriteBatch.draw(botBackground,world.getBoardGame()[31][i].x,world.getBoardGame()[31][i].y,
				world.getBoardGame()[0][0].height,world.getBoardGame()[0][0].height);
		
		for(int i=1;i<31;i++)
			spriteBatch.draw(leftBackground,world.getBoardGame()[i][0].x,world.getBoardGame()[i][0].y,
					world.getBoardGame()[0][0].height,world.getBoardGame()[0][0].height);
		
		for(int i=1;i<31;i++)
			spriteBatch.draw(rightBackground,world.getBoardGame()[i][31].x,world.getBoardGame()[i][31].y,
					world.getBoardGame()[0][0].height,world.getBoardGame()[0][0].height);
		
		for (int i = 1; i < 31; i++) {
			for (int j = 1; j < 31; j++) {
				spriteBatch.draw(mainBackground,world.getBoardGame()[i][j].x,world.getBoardGame()[i][j].y,
						world.getBoardGame()[0][0].height,world.getBoardGame()[0][0].height);
			}
		}
		spriteBatch.end();
	//	shapeRenderer.end();
	}

	private void drawSnake() {
		spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.begin();
		for (int i = 0; i < snake.getSnakeBody().size(); i++) {
			if (i == 0) {
				if (snake.getSnakeDir().get(0) != snake.getSnakeDir().get(1))
					snake.getSnakeDir().set(0, snake.getSnakeDir().get(1));
				spriteBatch.draw(tailSnakeFrames[snake.getSnakeDir().get(i).ordinal()],
						world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
								.get(i).y].x,
						world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
								.get(i).y].y,
						world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
			} else {
				if (i == (snake.getSnakeBody().size() - 1)) {
					spriteBatch.draw(headSnakeFrames[snake.getSnakeDir().get(i).ordinal()],
							world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
									.get(i).y].x,
							world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
									.get(i).y].y,
							world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
				} else {
					if (snake.getSnakeDir().get(i).ordinal() == 0 && snake.getSnakeDir().get(i + 1).ordinal() == 2)
						spriteBatch.draw(curveSnake[0],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == 0 && snake.getSnakeDir().get(i + 1).ordinal() == 3)
						spriteBatch.draw(curveSnake[1],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == 1 && snake.getSnakeDir().get(i + 1).ordinal() == 2)
						spriteBatch.draw(curveSnake[2],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == 1 && snake.getSnakeDir().get(i + 1).ordinal() == 3)
						spriteBatch.draw(curveSnake[3],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == 2 && snake.getSnakeDir().get(i + 1).ordinal() == 0)
						spriteBatch.draw(curveSnake[3],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == 2 && snake.getSnakeDir().get(i + 1).ordinal() == 1)
						spriteBatch.draw(curveSnake[1],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == 3 && snake.getSnakeDir().get(i + 1).ordinal() == 0)
						spriteBatch.draw(curveSnake[2],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == 3 && snake.getSnakeDir().get(i + 1).ordinal() == 1)
						spriteBatch.draw(curveSnake[0],
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snake.getSnakeDir().get(i).ordinal() == snake.getSnakeDir().get(i + 1).ordinal())
						spriteBatch.draw(bodySnakeFrame,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snake.getSnakeBody().get(i).x][(int) snake.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
				}
			}
		}
		spriteBatch.end();
	}

	private void drawSnakeAI() {
		spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.begin();
		for (int i = 0; i < snakeAI.getSnakeBody().size(); i++) {
			if (i == 0) {
				if (snakeAI.getSnakeDir().get(0) != snakeAI.getSnakeDir().get(1))
					snakeAI.getSnakeDir().set(0, snakeAI.getSnakeDir().get(1));
				spriteBatch.draw(tailSnakeFramesAI[snakeAI.getSnakeDir().get(i).ordinal()],
						world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
								.get(i).y].x,
						world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
								.get(i).y].y,
						world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
			} else {
				if (i == (snakeAI.getSnakeBody().size() - 1)) {
					spriteBatch.draw(headSnakeFramesAI[snakeAI.getSnakeDir().get(i).ordinal()],
							world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
									.get(i).y].x,
							world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
									.get(i).y].y,
							world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
				} else {
					if (snakeAI.getSnakeDir().get(i).ordinal() == 0 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 2)
						spriteBatch.draw(curveSnakeAI[0],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == 0 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 3)
						spriteBatch.draw(curveSnakeAI[1],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == 1 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 2)
						spriteBatch.draw(curveSnakeAI[2],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == 1 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 3)
						spriteBatch.draw(curveSnakeAI[3],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == 2 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 0)
						spriteBatch.draw(curveSnakeAI[3],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == 2 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 1)
						spriteBatch.draw(curveSnakeAI[1],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == 3 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 0)
						spriteBatch.draw(curveSnakeAI[2],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == 3 && snakeAI.getSnakeDir().get(i + 1).ordinal() == 1)
						spriteBatch.draw(curveSnakeAI[0],
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
					if (snakeAI.getSnakeDir().get(i).ordinal() == snakeAI.getSnakeDir().get(i + 1).ordinal())
						spriteBatch.draw(bodySnakeFrameAI,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].x,
								world.getBoardGame()[(int) snakeAI.getSnakeBody().get(i).x][(int) snakeAI.getSnakeBody()
										.get(i).y].y,
								world.getBoardGame()[0][0].width, world.getBoardGame()[0][0].height);
				}
			}
		}
		spriteBatch.end();
	}

	private int drawbait;

	private void drawBait() {
		spriteBatch.begin();
		if (drawbait > 10) {
			spriteBatch.draw(baitFruit[bait.pos],
					world.getBoardGame()[bait.x][bait.y].x - world.getBoardGame()[bait.x][bait.y].height / 4,
					world.getBoardGame()[bait.x][bait.y].y - world.getBoardGame()[bait.x][bait.y].height / 4,
					world.getBoardGame()[bait.x][bait.y].width * 1.5f,
					world.getBoardGame()[bait.x][bait.y].height * 1.5f);
			if (drawbait == 20)
				drawbait = 0;
		} else
			spriteBatch.draw(baitFruit[bait.pos], world.getBoardGame()[bait.x][bait.y].x,
					world.getBoardGame()[bait.x][bait.y].y, world.getBoardGame()[bait.x][bait.y].width,
					world.getBoardGame()[bait.x][bait.y].height);
		drawbait++;
		spriteBatch.end();
	}
}