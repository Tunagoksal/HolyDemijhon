package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Animations.WizardAnimation;


public class Wizard extends Enemy {

    public static final float WIZARD_WIDTH = 5;
    public static final float WIZARD_HEIGHT = 8;
    public static final int WIZARD_HEALTH = 200;
    public static final int WIZARD_DAMAGE = 25;


    TextureAtlas atlas;
    private WizardAnimation wizardAnimation;
    public Wizard(Level level, float x, float y, int wizardState) {
        super(level, x, y);

        atlas = new TextureAtlas("animations/wizard_animations.atlas");
        wizardAnimation = new WizardAnimation(this, atlas, b2dbody);
        health = WIZARD_HEALTH;
    }

    @Override
    public void update(float dt) {
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2dbody);
            destroyed = true;
        }
        wizardAnimation.update(dt);
    }

    public WizardAnimation getWizardAnimation() { return wizardAnimation; }

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
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
        HolyDemijohn.audioManager.playSound(12);
        wizardAnimation.performAction(ENEMY_PERFORM_TAKING_DAMAGE);

        if (health <= 0) {
            wizardAnimation.performAction(ENEMY_PERFORM_DEATH);
            setToDestroy = true;
        }
        else
            HolyDemijohn.audioManager.playSound(12);
    }
}
