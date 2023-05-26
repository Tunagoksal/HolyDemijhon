package com.holydemijon.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Tools.KeyboardInputs;

public class John extends Sprite {

    public static final float JUMP_HEIGHT = 4f;
    public static final float MAX_LINEAR_VELOCITY = 1;
    public static final float PLAYER_ACCELERATION = 0.1f;

    public static final float JOHN_WIDTH = 4;
    public static final float JOHN_HEIGHT = 7;

    private PolygonShape shape;
    KeyboardInputs inputs;

    public World level;
    public Body b2dbody;

    private BodyDef bodydef;

    public John(World level) {
        this.level = level;
        defJohn();
        inputs = new KeyboardInputs(this);
        Gdx.input.setInputProcessor(inputs);

    }


    private void defJohn() {
        bodydef = new BodyDef();
        bodydef.position.set(100 / HolyDemijhon.PPM, 150 / HolyDemijhon.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = level.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(JOHN_WIDTH / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM);

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData("player");
    }
    public void jump(float jumpforce){
            b2dbody.applyLinearImpulse(new Vector2(0, jumpforce), b2dbody.getWorldCenter(), true);
    }

    public void move(int direction){
        b2dbody.applyLinearImpulse(new Vector2(PLAYER_ACCELERATION*direction, 0), b2dbody.getWorldCenter(), true);
    }

    public KeyboardInputs getInputs() {
        return inputs;
    }

    public World getLevel() {
        return level;
    }

    public BodyDef getBodydef() {
        return bodydef;
    }
}
