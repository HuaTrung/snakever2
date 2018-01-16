package com.gnurt.MySnake;
import java.util.LinkedList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Snake {
	private float snakeBodyLength;
	private boolean movingX=false;
	private boolean grows=false;
	//private int score=0;
	public enum Dir  { UP, DOWN, LEFT, RIGHT };
	
	private Rectangle elementBody;
	//private LinkedList<Rectangle> snakeBody; // include head and tail
	private LinkedList<Vector2> snakeBody;
	private LinkedList<Dir> snakeDir;
	
	public Snake(float length,int[] pos,float size) {
		this.snakeBodyLength=length;
		this.movingX=true;
		this.grows=false;
		elementBody=new Rectangle(0,0,size,size) ;
		this.snakeBody=new LinkedList<Vector2>();
		this.snakeDir=new LinkedList<Dir>();
		if(pos[0]==1) {
			for(int i=0;i<length;i++) {
				snakeBody.offer(new Vector2(1,pos[i]));
				this.snakeDir.offer(Dir.RIGHT);
			}
		}
		else {
			for(int i=0;i<length;i++) {
				snakeBody.offer(new Vector2(1,pos[i]));
				this.snakeDir.offer(Dir.LEFT);
			}
		}
		
	}

	public LinkedList<Vector2> getSnakeBody() {
		return snakeBody;
	}
	
	public LinkedList<Dir> getSnakeDir() {
		return snakeDir;
	}
	
	public float getBodyLength() {
		return snakeBodyLength;
	}
	
	
	public boolean isMovingX() {
		return movingX;
	}
	
	public void setMovingX(boolean set) {
		movingX = set;
	}
	
	public boolean isGrowing() {
		return grows;
	}
	
	public void setGrowing(boolean set) {
		grows = set;
	}
	
	 public Rectangle getElementBody() {
		 return elementBody;
	 }
	 
}