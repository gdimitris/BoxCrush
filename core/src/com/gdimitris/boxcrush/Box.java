package com.gdimitris.boxcrush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.gdimitris.boxcrush.Constants.PIXELS_PER_METER;

public class Box {

    private Sprite sprite;
    private Body body;
    private int hits;

    public Box(Body body) {
        this.body = body;
        sprite = new Sprite(new Texture("alienBlue_square.png"));
        sprite.flip(false,true);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setSize(float width, float height){
        sprite.setSize(width,height);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x , y);
        body.setTransform(new Vector2( (x + (sprite.getWidth()/2)) / PIXELS_PER_METER, (y + (sprite.getHeight() / 2 ) )/ PIXELS_PER_METER),body.getAngle());
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void hit(){
        body.getWorld().destroyBody(body);
    }

}
