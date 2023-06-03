package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Animations.OrcAnimation;

public class Orc extends Enemy {

    public static final float ORC_WIDTH = 9;
    public static final float ORC_HEIGHT = 13;
    public static final int ORC_DAMAGE = 50;
    public static final int ORC_HEALTH = 300;


    TextureAtlas atlas;
    private OrcAnimation orcAnimation;
    public Orc(Level level, float x, float y, int orcState) {
        super(level, x, y);

        atlas = new TextureAtlas("animations/orc_animations.atlas");
        orcAnimation = new OrcAnimation(this, atlas, b2dbody);
        health = ORC_HEALTH;
    }
    @Override
    public void update(float dt) {
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2dbody);
            destroyed = true;
        }
        else if(!destroyed&&enemyTouchingGround){
            b2dbody.setLinearVelocity(velocity);
        }
        orcAnimation.update(dt);
    }

    public OrcAnimation getOrcAnimation() {
        return orcAnimation;
    }

    @Override
    protected void defEnemy() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(this.getX(), this.getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ORC_WIDTH / HolyDemijohn.PPM, ORC_HEIGHT / HolyDemijohn.PPM);

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
        orcAnimation.performAction(ENEMY_PERFORM_TAKING_DAMAGE);

        if (health <= 0) {
            orcAnimation.performAction(ENEMY_PERFORM_DEATH);
            setToDestroy = true;
        }
        else
            HolyDemijohn.audioManager.playSound(12);

    }
}
