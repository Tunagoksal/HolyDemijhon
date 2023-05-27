package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.Levels.FirstLevel;
import com.holydemijon.Sprites.Animations.ZombieAnimation;

public class Zombie extends Enemy {

    public static final float ZOMBIE_WIDTH = 8;
    public static final float ZOMBIE_HEIGHT = 11;
    public static final int ZOMBIE_DAMAGE = 50;


    TextureAtlas atlas;
    private ZombieAnimation zombieAnimation;
    public Zombie(FirstLevel screen, float x, float y, int zombieState) {
        super(screen, x, y);

        atlas = new TextureAtlas("animations/zombieAnimations.atlas");
        zombieAnimation = new ZombieAnimation(atlas, b2dbody, zombieState);
        health = 200;
    }

    public void update(float dt) {
        zombieAnimation.update(dt);
    }

    public ZombieAnimation getZombieAnimation() {
        return zombieAnimation;
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
        fixDef.filter.maskBits = HolyDemijhon.OBJECT_BIT |
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
