package com.holydemijon.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;

public class John extends Sprite {

    private static final float JUMP_HEIGHT = 4f;
    private static final float MAX_LINEAR_VELOCITY = 1;
    private static final float PLAYER_ACCELERATION = 0.1f;

    public static final float JOHN_WIDTH = 4;
    public static final float JOHN_HEIGHT = 7;

    private PolygonShape shape;

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
        shape = new PolygonShape();
        shape.setAsBox(JOHN_WIDTH / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM);

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData("player");
    }
    public void jump(){
            b2dbody.applyLinearImpulse(new Vector2(0, JUMP_HEIGHT), b2dbody.getWorldCenter(), true);
    }


}
