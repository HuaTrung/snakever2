package com.gnurt.manager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor {
    Sprite sprite=null;
    public MyActor(Sprite _sprite) {
    	sprite=_sprite;
    	setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
    }
    public MyActor(TextureRegion _texture) {
    	sprite = new Sprite(_texture);
    	setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
           batch.draw(sprite, this.getX(), this.getY() ,getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
    }
}

