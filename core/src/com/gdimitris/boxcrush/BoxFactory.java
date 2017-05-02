package com.gdimitris.boxcrush;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BoxFactory {

    private World world;

    public BoxFactory(World world){
        this.world = world;
    }

    public Box createBox(int x, int y, int width, int height){
        PolygonShape shape = ShapeFactory.createBoxShape(width, height);

        Body body = world.createBody(BodyFactory.createStaticBody(x, y));
        Fixture fixture= body.createFixture(shape,1.0f);
        Filter filter = fixture.getFilterData();
        filter.categoryBits = EntityCategoryMask.BOX.getValue();
        filter.maskBits = EntityCategoryMask.PROJECTILE.getValue();
        fixture.setFilterData(filter);

        Box box = new Box(body);
        box.setSize(width,height);
        body.setUserData(box);

        shape.dispose();
        return box;
    }

}
