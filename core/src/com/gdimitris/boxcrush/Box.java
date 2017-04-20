package com.gdimitris.boxcrush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Box {

    private Sprite sprite;

    public Box(){
        sprite = new Sprite(new Texture("alienBlue_square.png"));
        sprite.setPosition(10,10);
        sprite.flip(false,true);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setSize(float width, float height){
        sprite.setSize(width,height);
    }

    public void setPosition(float x, float y){
        Gdx.app.log("Box",String.format("Updating Box position to %f %f",x,y));
        sprite.setPosition(x, y);
    }
}
