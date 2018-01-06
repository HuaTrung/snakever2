package com.gnurt.manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.gnurt.viewport.*;
public class MyCamera {
	private static MyCamera instance=null;
	private Viewport viewport;
	private Camera camera;
	
	private MyCamera() {
		camera = new PerspectiveCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}
	public static MyCamera Instance() {
		if(instance==null)
			instance=new MyCamera();
		return instance;	
	}
	public Camera getCamera() {
		return instance.camera;
	}
	public Viewport getViewport() {
		return instance.viewport;
	}
}
