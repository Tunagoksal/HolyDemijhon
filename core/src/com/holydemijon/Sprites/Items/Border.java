package com.holydemijon.Sprites.Items;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;

public class Border {

    private World world;
    private Rectangle bounds;
    private Body body;
    private FixtureDef fixtureDef;

    public Border(Level level, Rectangle bounds) {
        this.world = level.getWorld();
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / HolyDemijohn.PPM, (bounds.getY() + bounds.getHeight() / 2) / HolyDemijohn.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / HolyDemijohn.PPM, bounds.getHeight() / 2 / HolyDemijohn.PPM);

        fixtureDef.isSensor = true;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData(this);

    }

}
