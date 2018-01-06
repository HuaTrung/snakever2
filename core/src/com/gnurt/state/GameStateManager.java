package com.gnurt.state;

import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gnurt.snake.SnakeGame;

public class GameStateManager {
	private Stack<State> states;
	protected final int WIDTH;
	protected final int HEIGHT;
	public GameStateManager() {
		this.WIDTH=SnakeGame.WIDTH;
		this.HEIGHT=SnakeGame.HEIGHT;
		states=new Stack<State>();
	}
	public void push(State state) {
		states.push(state);
	}
	public void pop(State state) {
		states.pop();
	}
	public void set(State state) {
		states.pop();
		states.push(state);
	}
	public void update(float dt) {
		states.peek().update(dt);
	}
	public void resize(int width,int height) {
		states.peek().resize(width,height);
	}
	public void render(SpriteBatch sb) {
		states.peek().render(sb);
	}
}
