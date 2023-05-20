package com.holydemijon.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import sun.security.provider.SHA;

public class John extends Sprite {

    public static final float JOHN_WIDTH = 4;
    public static final float JOHN_HEIGHT = 7;

    public World level;
    public Body b2dbody;

    private int Health = 100;

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
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(JOHN_WIDTH / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM);

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData("player");
    }

    public void setHealth(int health) {
        Health += health;
    }

    public int getHealth() {
        return Health;
    }
}
