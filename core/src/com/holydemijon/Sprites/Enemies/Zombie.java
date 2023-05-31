package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Animations.ZombieAnimation;

public class Zombie extends Enemy {

    public static final float ZOMBIE_WIDTH = 8;
    public static final float ZOMBIE_HEIGHT = 11;
    public static final int ZOMBIE_DAMAGE = 50;
    public static final int ZOMBIE_HEALTH = 500;


    TextureAtlas atlas;
    private ZombieAnimation zombieAnimation;
    public Zombie(Level level, float x, float y, int zombieState) {
        super(level, x, y);

        atlas = new TextureAtlas("animations/zombie_animations.atlas");
        zombieAnimation = new ZombieAnimation(this, atlas, b2dbody);
        health = ZOMBIE_HEALTH;
    }
    @Override
    public void update(float dt) {
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2dbody);
            destroyed = true;
        }
        else if(!destroyed){
            b2dbody.setLinearVelocity(velocity);
        }
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
        shape.setAsBox(ZOMBIE_WIDTH / HolyDemijohn.PPM, ZOMBIE_HEIGHT / HolyDemijohn.PPM);

        fixDef.filter.categoryBits = HolyDemijohn.ENEMY_BIT;
        fixDef.filter.maskBits = HolyDemijohn.OBJECT_BIT |
                HolyDemijohn.ENEMY_BIT |
                HolyDemijohn.JOHN_BIT;

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData(this);
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
        zombieAnimation.performAction(ENEMY_PERFORM_TAKING_DAMAGE);

        if (health <= 0) {
            zombieAnimation.performAction(ENEMY_PERFORM_DEATH);
            setToDestroy = true;
        }
    }

}
