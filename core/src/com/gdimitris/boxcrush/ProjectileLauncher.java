package com.gdimitris.boxcrush;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class ProjectileLauncher {

    private ProjectileFactory factory;
    private ArrayList<Projectile> projectiles;
    private Vector3 position;
    private static final float PROJECTILE_RADIUS = 10.f;


    public ProjectileLauncher(ProjectileFactory factory){
        this.factory = factory;
        projectiles = new ArrayList<Projectile>();
        this.position = Vector3.Zero;
    }

    public void setPosition(Vector3 position){
        this.position = position;
    }

    public void draw(SpriteBatch batch){
        for(Projectile p : projectiles){
            p.draw(batch);
        }
    }

    public void increaseProjectilesByOne(){
        Projectile projectile = factory.createProjectile(PROJECTILE_RADIUS, position);
        projectiles.add(projectile);
    }

    public void launchProjectiles(){
        for(Projectile p : projectiles){
            p.launchWithVelocity(new Vector3(0.0f,-7.0f,0.0f));
        }
    }
}
