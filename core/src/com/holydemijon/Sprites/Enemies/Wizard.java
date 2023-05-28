package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.FirstLevel;

public class Wizard extends Enemy {

    private static final float WIZARD_WIDTH = 4;
    private static final float WIZARD_HEIGHT = 7;
    public Wizard(FirstLevel screen, float x, float y) {
        super(screen, x, y);
        health = 100;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    protected void defEnemy() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(this.getX(), this.getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIZARD_WIDTH / HolyDemijohn.PPM, WIZARD_HEIGHT / HolyDemijohn.PPM);

        fixDef.filter.categoryBits = HolyDemijohn.ENEMY_BIT;
        fixDef.filter.maskBits = HolyDemijohn.OBJECT_BIT |
                HolyDemijohn.ENEMY_BIT |
                HolyDemijohn.JOHN_BIT;

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData(this);
    }

    @Override
    public void die() {

    }
}
