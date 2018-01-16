package com.gnurt.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
* <h1>State</h1>
* This is a abstract base class. It represents generally a state of game <br>
* For example introduction state, playing state,... 
* @author  gnurt
*/
public abstract class State {
	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected GameStateManager gsm;
	protected State(GameStateManager gsm) {
		this.gsm=gsm;
		cam=new OrthographicCamera();
		mouse=new Vector3();
	}
	
	public abstract void handleInput() ;
	public abstract void update(float dt) ;
	public abstract void render(SpriteBatch sb) ;
	public abstract void resize(int width,int height);
	public abstract void dispose();

}
