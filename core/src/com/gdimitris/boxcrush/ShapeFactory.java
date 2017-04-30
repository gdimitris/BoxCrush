package com.gdimitris.boxcrush;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.gdimitris.boxcrush.Constants.PIXELS_PER_METER;

public class ShapeFactory {


    public static PolygonShape createBoxShape(int width, int height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PIXELS_PER_METER,height/2/PIXELS_PER_METER);
        return shape;
    }

    public static CircleShape createCircleShape(float radius) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius/PIXELS_PER_METER);
        return circleShape;
    }

    public static EdgeShape createEdgeShape(int width, int height){
        EdgeShape shape = new EdgeShape();

        return shape;
    }
}
