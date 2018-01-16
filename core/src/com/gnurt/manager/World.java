package com.gnurt.manager;
import com.badlogic.gdx.math.Rectangle;
import com.gnurt.MySnake.Snake;

import MyBait.Bait;

public class World {

	private Snake snake;
	private Snake snakeAI;
	private Bait bait;	
	private static final float snakeBodyLength = 3;
	
	private Rectangle[][] boardgame;
	public World(float CAM_WIDTH, float CAM_HEIGHT) {
		boardgame=new Rectangle[32][32];
		float length=(CAM_HEIGHT-2);
		float x=(CAM_WIDTH-length)/2;
		float y=CAM_HEIGHT-1-(CAM_HEIGHT-2)/32;
		for(int i=0;i<32;i++) {
			for(int j=0;j<32;j++) {
				boardgame[i][j]=new Rectangle();
				boardgame[i][j].x=x;
				boardgame[i][j].y=y;
				boardgame[i][j].width=length/32;
				boardgame[i][j].height=length/32;
				x+=(length/32);
			}
			x=(CAM_WIDTH-length)/2;
			y-=(length/32);
		}
		int[] posSnake= {1,2,3};
		int[] posSnakeAI= {30,29,28};
		this.snake = new Snake(snakeBodyLength,posSnake,length/32);
		this.snakeAI = new Snake(snakeBodyLength,posSnakeAI,length/32);
		this.bait = new Bait();
	}
	
	public Snake getSnake() {
		return snake;
	}
	public Snake getSnakeAI() {
		return snakeAI;
	}
	public Rectangle[][] getBoardGame(){
		return boardgame;
	}
	public Bait getBait() {
		return bait;
	}
}