package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;

public class Wizard extends Enemy {

    private static final float WIZARD_WIDTH = 4;
    private static final float WIZARD_HEIGHT = 7;
    public Wizard(LevelScreen screen, float x, float y) {
        super(screen, x, y);
        health = 100;
    }

    @Override
    protected void defEnemy() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(this.getX(), this.getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIZARD_WIDTH / HolyDemijhon.PPM, WIZARD_HEIGHT / HolyDemijhon.PPM);

        fixDef.filter.categoryBits = HolyDemijhon.ENEMY_BIT;
        fixDef.filter.maskBits = HolyDemijhon.GROUND_BIT |
                HolyDemijhon.CHEST_BIT |
                HolyDemijhon.OBJECT_BIT |
                HolyDemijhon.ENEMY_BIT |
                HolyDemijhon.JOHN_BIT;

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData(this);
    }

    @Override
    public void kill() {

    }
}
