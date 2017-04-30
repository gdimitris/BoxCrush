package com.gdimitris.boxcrush;


import com.badlogic.gdx.physics.box2d.BodyDef;

import static com.gdimitris.boxcrush.Constants.PIXELS_PER_METER;

public class BodyFactory {


    public static BodyDef createDynamicBody(float x, float y) {
        BodyDef bodyDef = createGenericBody(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    public static BodyDef createStaticBody(float x, float y){
        BodyDef bodyDef = createGenericBody(x,y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    private static BodyDef createGenericBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();

        bodyDef.position.set(x/PIXELS_PER_METER,y/PIXELS_PER_METER);
        bodyDef.fixedRotation = true;

        return bodyDef;
    }
}
