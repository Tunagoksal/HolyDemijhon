package com.holydemijon.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import sun.security.provider.SHA;

public class John extends Sprite {

    public static final int SHAPE_RADIUS = 5;

    public World level;
    public Body b2dbody;

    public John(World level) {
        this.level = level;
        defJohn();
    }

    private void defJohn() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(32 / HolyDemijhon.PPM, 150 / HolyDemijhon.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = level.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(SHAPE_RADIUS / HolyDemijhon.PPM);

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef);
    }
}
