package com.gnurt.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MyAsset {
	private static MyAsset instance=null;
	private AssetManager manager;
	public static final String data="data.png";
	public static final String audio_background="audio/audio_background.mp3";
	public static final String audio_menubutton="audio/audio_menubutton.mp3";
	public static final String audio_switch="audio/audio_switch.mp3";
	public static final String audio_eat="audio/audio_eat.wav";
	private MyAsset() {
		manager=new AssetManager();
		manager.load(data,Texture.class);
		manager.load(audio_background, Music.class);
		manager.load(audio_menubutton, Sound.class);
		manager.load(audio_switch, Sound.class);
		manager.load(audio_eat, Sound.class);
		manager.finishLoading();
	}
	public static MyAsset Instance() {
		if(instance==null)
			instance=new MyAsset();
		return instance;
		
	}
	public AssetManager getManger() {
		return manager;
	}
	public void dispose() {
		if(instance!=null)
			instance.manager.dispose();
	}
}
