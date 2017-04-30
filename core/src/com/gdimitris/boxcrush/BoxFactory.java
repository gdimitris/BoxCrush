package com.gdimitris.boxcrush;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.gdimitris.boxcrush.Constants.PIXELS_PER_METER;

public class BoxFactory {

    private World world;

    public BoxFactory(World world){
        this.world = world;
    }

    public Box createBox(int x, int y, int width, int height){
        Box box = new Box();
        BodyDef bodyDef = createBodyDefinition(x, y);

        Body pBody = world.createBody(bodyDef);
        box.setBody(pBody);

        PolygonShape shape = createBoxShape(width, height);
        pBody.createFixture(shape,1.0f);
        pBody.setUserData(box);

        shape.dispose();
        return box;
    }

    private PolygonShape createBoxShape(int width, int height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PIXELS_PER_METER,height/2/PIXELS_PER_METER);
        return shape;
    }

    private BodyDef createBodyDefinition(int x, int y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/PIXELS_PER_METER,y/PIXELS_PER_METER);
        bodyDef.fixedRotation = true;
        return bodyDef;
    }
}
