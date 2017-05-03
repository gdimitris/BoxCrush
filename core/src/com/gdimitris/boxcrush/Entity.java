package com.gdimitris.boxcrush;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

import static com.gdimitris.boxcrush.Constants.PIXELS_PER_METER;

public abstract class Entity {

    private Body body;
    private Sprite sprite;

    public Entity(Body body, String textureName){
        this.body = body;
        sprite = new Sprite(new Texture(textureName));
        sprite.flip(false,true);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setPosition(Vector3 position) {
        sprite.setPosition(position.x,position.y);
        body.setTransform(new Vector2( (position.x + (sprite.getWidth()/2)) / PIXELS_PER_METER, (position.y + (sprite.getHeight() / 2 ) )/ PIXELS_PER_METER),body.getAngle());
    }

    public void update(float delta) {
        Vector2 bodyPos = body.getPosition();
        sprite.setPosition((bodyPos.x * PIXELS_PER_METER) - sprite.getWidth()/2, (bodyPos.y * PIXELS_PER_METER) - sprite.getHeight()/2);
    }

    protected Body getBody(){
        return body;
    }

    protected Sprite getSprite(){
        return sprite;
    }

    public void setSize(float width, float height){
        sprite.setSize(width,height);
    }
}
