package com.gdimitris.boxcrush;


import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class ProjectileFactory {

    private World world;

    public ProjectileFactory(World world){
        this.world = world;
    }

    public Projectile createProjectile(float radius, Vector3 position){
        CircleShape circleShape = ShapeFactory.createCircleShape(radius);

        Body body = world.createBody(BodyFactory.createDynamicBody(position.x,position.y));
        body.createFixture(circleShape,0.6f);
        body.getFixtureList().first().setRestitution(1.0f);

        Projectile projectile = new Projectile(body);
        projectile.setRadius(radius);
        projectile.setPosition(position);
        body.setUserData(projectile);

        circleShape.dispose();
        return projectile;
    }

}
