package com.gnurt.manager;

public class MyModeGame {
	public enum GameMode {
	KID,ADULT, HELL;
	}
	private static MyModeGame instance=null;
	private GameMode gameMode ;
	private MyModeGame() {
		gameMode=GameMode.KID;
	}
	public static MyModeGame Instance() {
		if(instance==null)
			instance=new MyModeGame();
		return instance;	
	}
	public static GameMode getMode() {
		if(instance==null)
			instance=new MyModeGame();
		return instance.gameMode;
	}
	public static void setMode(int curMode) {
		switch (curMode) {
		case 0:
			if(instance==null)
				instance=new MyModeGame();
			 instance.gameMode=GameMode.KID;
			break;
		case 1:
			if(instance==null)
				instance=new MyModeGame();
			 instance.gameMode=GameMode.ADULT;
			break;
		case 2:
			if(instance==null)
				instance=new MyModeGame();
			 instance.gameMode=GameMode.HELL;
			break;
		}
	}
}
