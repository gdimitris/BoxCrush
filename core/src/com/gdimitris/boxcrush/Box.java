package com.gdimitris.boxcrush;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class Box extends Entity{

    private int hitsLeft;

    public Box(Body body) {
        super(body, "alienBlue_square.png");
    }

    public Box(Body body, Sprite sprite){
        super(body,sprite);
    }

    public void hit(){
        Body body = getBody();
        body.getWorld().destroyBody(body);
    }

    public void setHitsLeft(int hitsLeft){
        this.hitsLeft = hitsLeft;
    }


}
