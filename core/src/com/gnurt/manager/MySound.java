package com.gnurt.manager;

import com.badlogic.gdx.audio.Music;

public class MySound {

	private static MySound instance=null;
	private Music musicBackground;

	private MySound( ) {
		musicBackground =MyAsset.Instance().getManger().get(MyAsset.audio_background,Music.class);
		musicBackground.setLooping(true);
		musicBackground.setVolume(0.8f);
	}
	public static MySound Instance() {
		if(instance==null)
			instance=new MySound();
		return instance;	
	}
	public static void playMusic() {
		if(instance==null)
			instance=new MySound();
		instance.musicBackground.play();
	}
	public static void pauseMusic() {
		if(instance==null)
			instance=new MySound();
		instance.musicBackground.pause();
	}
}
