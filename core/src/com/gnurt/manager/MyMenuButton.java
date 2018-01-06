package com.gnurt.manager;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MyMenuButton extends ImageButton{
	private Sound sound;
	public MyMenuButton(Drawable imageUp, Drawable imageDown,Sound sound) {
		super(imageUp, imageDown);
		// TODO Auto-generated constructor stub
		this.sound=sound;
	}
	public void playSound() {
		sound.play();
	}
}
