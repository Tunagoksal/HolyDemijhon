package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;

public class Zombie extends Enemy {

    private static final float ZOMBIE_WIDTH = 6;
    private static final float ZOMBIE_HEIGHT = 9;
    public Zombie(LevelScreen screen, float x, float y) {
        super(screen, x, y);
        health = 200;
    }

    @Override
    protected void defEnemy() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(this.getX(), this.getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ZOMBIE_WIDTH / HolyDemijhon.PPM, ZOMBIE_HEIGHT / HolyDemijhon.PPM);

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
        setToDestroy = true;
    }
}
