package com.gdimitris.boxcrush;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;


public class Projectile extends Entity {

    public Projectile(Body body){
        super(body,"ball.png");
    }

    public void launchWithVelocity(Vector3 velocity){
        Body body = getBody();
        body.applyLinearImpulse(velocity.x,velocity.y,body.getMassData().center.x,body.getMassData().center.y,true);
    }

    public void setRadius(float radius) {
        Sprite sprite = getSprite();
        sprite.setSize(2 * radius,2 * radius);
    }
}
