package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Animations.FireballAnimation;



public class Fireball extends Enemy {

    TextureAtlas atlas;

    public static final float FIREBALL_WIDTH = 4;
    public static final float FIREBALL_HEIGHT = 4;
    public static final int FIREBALL_DAMAGE = 50;

    private FireballAnimation fireballAnimation ;
    public Fireball(Level level, float x, float y) {
        super(level, x, y);
        atlas = new TextureAtlas("animations/wizard_animations.atlas");
        fireballAnimation = new FireballAnimation(this, atlas, b2dbody);

    }

    @Override
    public void update(float dt) {
        b2dbody.setLinearVelocity(-0.2F,0);
        fireballAnimation.update(dt);
    }

    @Override
    protected void defEnemy() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(this.getX(), this.getY());
        bodydef.type = BodyDef.BodyType.KinematicBody;
        b2dbody = world.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(FIREBALL_WIDTH / HolyDemijohn.PPM, FIREBALL_HEIGHT / HolyDemijohn.PPM);

        fixDef.filter.categoryBits = HolyDemijohn.ENEMY_BIT;
        fixDef.filter.maskBits = HolyDemijohn.OBJECT_BIT |
                HolyDemijohn.ENEMY_BIT |
                HolyDemijohn.JOHN_BIT;

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData(this);
    }

    public FireballAnimation getFireBallAnimation() {
        return fireballAnimation;
    }
}
