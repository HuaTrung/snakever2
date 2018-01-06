package com.gnurt.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.gnurt.manager.*;

//import aurelienribon.tweenengine.TweenManager;
public class MenuState extends State{
	private Texture background;
	private Texture menustate;
//	private TextureRegion title;
	//private Sprite background;
	//private static TweenManager tweenManager;
	private MyActor tittle;

	private MyMenuButton buttonPlay;
	
	private Actor mode;
	private MyMenuButton buttonModeLeft;
	private MyMenuButton buttonModeRight;
	private int curMode;
	private Array<MyActor> modeStatus;

	private MyMenuButton buttonScore;
	
	private MyMenuButton buttonOnline;
	
	private MyMenuButton buttonHelp;
	
	private MyMenuButton buttonExit;
	
	private ImageButton buttonSoundUnactivated;
	private ImageButton buttonSoundActivated;
	
	private Stage stage;
	private Music musicBackground;
	public MenuState(GameStateManager gsm, TextureAtlas ta) {
		super(gsm);
		
		
		background=new Texture("background.png");
		menustate=new Texture("menu.png");
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		tittle=new MyActor(ta.createSprite("tittle"));
		MoveToAction moveToActionup = new MoveToAction();
		MoveToAction moveToActiondown = new MoveToAction();
		RepeatAction repeat = new RepeatAction();
		SequenceAction sequence = new SequenceAction();		
		moveToActiondown.setPosition(gsm.WIDTH/3.5f,  gsm.HEIGHT/10*7-10.0f);
		moveToActiondown.setDuration(0.5f);
		moveToActionup.setPosition(gsm.WIDTH/3.5f,  gsm.HEIGHT/10*7);
		moveToActionup.setDuration(0.5f);
		sequence.addAction(moveToActiondown);
		sequence.addAction(moveToActionup);
		repeat.setAction(sequence);
		repeat.setCount(RepeatAction.FOREVER);
		tittle.setPosition(gsm.WIDTH/3.5f,  gsm.HEIGHT/10*7);
		tittle.addAction(repeat);
		stage.addActor(tittle);	
		
		buttonPlay =new MyMenuButton(new TextureRegionDrawable(new TextureRegion(menustate,165,0,148,60)),new TextureRegionDrawable(new TextureRegion(menustate,165,60,148,58)),MyAsset.Instance().getManger().get(MyAsset.audio_menubutton,Sound.class));
		buttonPlay.setPosition(gsm.WIDTH/2-buttonPlay.getWidth()/2,gsm.HEIGHT/10*5.5f); //** Button location **//
		buttonPlay.getImage().setOrigin(Align.center);
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonPlay.playSound();
				
			}
		});	
		stage.addActor(buttonPlay);
		
		mode=new MyActor(new TextureRegion(menustate,460,10,90,25));
		mode.setPosition(gsm.WIDTH/2-buttonPlay.getWidth()/2,gsm.HEIGHT/10*5f); //** Button location **//
		//stage.addActor(mode);
		
		modeStatus=new Array<MyActor>();
		modeStatus.add(new MyActor(new TextureRegion(menustate,460,70,60,30)));
		modeStatus.add(new MyActor(new TextureRegion(menustate,460,102,100,30)));
		modeStatus.add(new MyActor(new TextureRegion(menustate,460,40,80,30)));
		modeStatus.get(0).setPosition(gsm.WIDTH/2-modeStatus.get(0).getWidth()/2,gsm.HEIGHT/10*4.8f); //** Button location **//
		modeStatus.get(0).setVisible(true);
		modeStatus.get(1).setPosition(gsm.WIDTH/2-modeStatus.get(1).getWidth()/2,gsm.HEIGHT/10*4.8f); //** Button location **//
		modeStatus.get(1).setVisible(false);
		modeStatus.get(2).setPosition(gsm.WIDTH/2-modeStatus.get(2).getWidth()/2,gsm.HEIGHT/10*4.8f); //** Button location **//
		modeStatus.get(2).setVisible(false);
		curMode=0;	
		buttonModeLeft=new MyMenuButton(new TextureRegionDrawable(new TextureRegion(menustate,560,10,40,60)),new TextureRegionDrawable(new TextureRegion(menustate,560,70,40,60)),MyAsset.Instance().getManger().get(MyAsset.audio_switch,Sound.class));
		buttonModeLeft.setPosition(gsm.WIDTH/2-buttonPlay.getWidth()/2-buttonModeLeft.getWidth(), gsm.HEIGHT/10*4.5f);
		buttonModeRight=new MyMenuButton(new TextureRegionDrawable(new TextureRegion(menustate,608,10,42,60)),new TextureRegionDrawable(new TextureRegion(menustate,608,70,42,60)),MyAsset.Instance().getManger().get(MyAsset.audio_switch,Sound.class));
		buttonModeRight.setPosition(gsm.WIDTH/2+buttonPlay.getWidth()/2, gsm.HEIGHT/10*4.5f);
		buttonModeLeft.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonModeLeft.playSound();				
				modeStatus.get(curMode).setVisible(false);
				curMode--;
				if(curMode==-1)
					curMode=2;
				modeStatus.get(curMode).setVisible(true);
			}
		});	
		buttonModeRight.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonModeRight.playSound();
				modeStatus.get(curMode).setVisible(false);
				curMode++;
				if(curMode==3)
					curMode=0;
				modeStatus.get(curMode).setVisible(true);
			}
		});
		stage.addActor(modeStatus.get(0));
		stage.addActor(modeStatus.get(1));
		stage.addActor(modeStatus.get(2));
		stage.addActor(buttonModeLeft);
		stage.addActor(buttonModeRight);
		
		buttonScore =new MyMenuButton(new TextureRegionDrawable(new TextureRegion(menustate,19,5,165-19,58)),new TextureRegionDrawable(new TextureRegion(menustate,19,60,165-19,58)),MyAsset.Instance().getManger().get(MyAsset.audio_menubutton,Sound.class));
		buttonScore.setPosition(gsm.WIDTH/2-buttonScore.getWidth()/2,gsm.HEIGHT/10*3.5f); //** Button location **//
		buttonScore.getImage().setOrigin(Align.center);
		buttonScore.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonScore.playSound();	
			}
		});	
		stage.addActor(buttonScore);
		
		buttonOnline =new MyMenuButton(new TextureRegionDrawable(new TextureRegion(menustate,390,135,540-390,195-135)),new TextureRegionDrawable(new TextureRegion(menustate,390,195,540-390,255-195)),MyAsset.Instance().getManger().get(MyAsset.audio_menubutton,Sound.class));
		buttonOnline.setPosition(gsm.WIDTH/2-buttonOnline.getWidth()/2,gsm.HEIGHT/10*2.5f); //** Button location **//
		buttonOnline.getImage().setOrigin(Align.center);
		buttonOnline.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonOnline.playSound();	
			}
		});	
		stage.addActor(buttonOnline);
		
		buttonHelp =new MyMenuButton(new TextureRegionDrawable(new TextureRegion(menustate,312,0,460-312,60)),new TextureRegionDrawable(new TextureRegion(menustate,312,60,460-312,119-60)),MyAsset.Instance().getManger().get(MyAsset.audio_menubutton,Sound.class));
		buttonHelp.setPosition(gsm.WIDTH/2-buttonHelp.getWidth()/2,gsm.HEIGHT/10*1.5f); //** Button location **//
		buttonHelp.getImage().setOrigin(Align.center);
		buttonHelp.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonHelp.playSound();	
			}
		});	
		stage.addActor(buttonHelp);
		
		buttonExit =new MyMenuButton(new TextureRegionDrawable(new TextureRegion(menustate,540,140,690-540,198-140)),new TextureRegionDrawable(new TextureRegion(menustate,540,198,690-540,255-198)),MyAsset.Instance().getManger().get(MyAsset.audio_menubutton,Sound.class));
		buttonExit.setPosition(gsm.WIDTH/2-buttonExit.getWidth()/2,gsm.HEIGHT/10*0.5f); //** Button location **//
		buttonExit.getImage().setOrigin(Align.center);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonExit.playSound();	
			}
		});	
		stage.addActor(buttonExit);
		
		musicBackground =MyAsset.Instance().getManger().get(MyAsset.audio_background,Music.class);
		musicBackground.setLooping(true);
		musicBackground.setVolume(0.8f);
		musicBackground.play();
		
		buttonSoundUnactivated=new ImageButton(new TextureRegionDrawable(new TextureRegion(menustate,125,170,172-125,60)));
		buttonSoundActivated =new ImageButton(new TextureRegionDrawable(new TextureRegion(menustate,30,160,90,80)));
		buttonSoundUnactivated.setPosition(buttonSoundActivated.getWidth()/4,buttonSoundActivated.getHeight()/4+10); //** Button location **//
		buttonSoundUnactivated.getImage().setOrigin(Align.center);
		buttonSoundUnactivated.setVisible(false);
		buttonSoundActivated.setPosition(buttonSoundActivated.getWidth()/4,buttonSoundActivated.getHeight()/4); //** Button location **//
		buttonSoundActivated.getImage().setOrigin(Align.center);
		
		buttonSoundActivated.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSoundActivated.setVisible(false);
				buttonSoundUnactivated.setVisible(true);
				musicBackground.pause();
			}
		});	
		buttonSoundUnactivated.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSoundUnactivated.setVisible(false);
				buttonSoundActivated.setVisible(true);
				musicBackground.play();
			}
		});	
		stage.addActor(buttonSoundUnactivated);
		stage.addActor(buttonSoundActivated);
		
	
	        
	}
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.getBatch().begin();
		stage.getBatch().draw(background, 0, 0,gsm.WIDTH, gsm.HEIGHT);
		stage.getBatch().end();
		stage.draw();
	
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resize(int width,int height) {
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		menustate.dispose();
		stage.dispose();
	}
}
