package com.gdimitris.boxcrush;

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
}
