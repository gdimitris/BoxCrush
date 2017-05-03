package com.gdimitris.boxcrush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.gdimitris.boxcrush.Constants.PIXELS_PER_METER;

public class Box extends Entity{

    private int hits;

    public Box(Body body) {
        super(body, "alienBlue_square.png");
    }

    public void hit(){
        Body body = getBody();
        body.getWorld().destroyBody(body);
    }

}
