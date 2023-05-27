package com.holydemijon.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.Enemies.Enemy;
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
    private TextureAtlas atlas;
    private TextureAtlas atlas2;
    private JohnAnimation johnAnimation;

    public static boolean lookingRight;

    public static Enemy attackableEnemy;

    private BodyDef bodydef;

    public static float johnPositionX;

    private int health = 100;

    public John(World level) {
        this.level = level;
        defJohn();

        atlas = new TextureAtlas("animations/characterAnimations.atlas");
        atlas2 = new TextureAtlas("animations/player_death.atlas");
        johnAnimation = new JohnAnimation(atlas, atlas2, b2dbody);
        attackableEnemy = null;
        lookingRight = true;

        inputs = new KeyboardInputs(this);
        Gdx.input.setInputProcessor(inputs);

        johnPositionX = b2dbody.getPosition().x;
    }

    public void update(float dt) {
        inputs.update(dt);
        johnAnimation.update(dt);
        johnPositionX = b2dbody.getPosition().x;
    }

    private void defJohn() {
        bodydef = new BodyDef();
        bodydef.position.set(100 / HolyDemijhon.PPM, 150 / HolyDemijhon.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = level.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(JOHN_WIDTH / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM);

        fixDef.filter.categoryBits = HolyDemijhon.JOHN_BIT;
        fixDef.filter.maskBits = HolyDemijhon.OBJECT_BIT |
                HolyDemijhon.ENEMY_BIT;

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData(this);

        fixDef.isSensor = true;
        PolygonShape attackRange = new PolygonShape();
        attackRange.setAsBox(JOHN_WIDTH * 6.5f / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM, new Vector2(0, 0), 0);
        fixDef.shape = attackRange;
        b2dbody.createFixture(fixDef).setUserData("attack range");
    }

    public void simpleAttack() {

        if (attackableEnemy != null) {
            attackableEnemy.receiveDamage(50);
            Gdx.app.log("Attack", "Enemy health:" + attackableEnemy.getHealth());
        }
    }

    public void heavyAttack() {

        if (attackableEnemy != null) {
            attackableEnemy.receiveDamage(150);
            Gdx.app.log("Attack", "Enemy health:" + attackableEnemy.getHealth());
        }
    }

    public void takeDamage(int damage) {
        JohnAnimation.performTakingDamage = true;
        health -= damage;

        if (health <= 0) {
            killJohn();
        }
        else {
            jump(5f);
        }
    }
    public void killJohn() {
        JohnAnimation.performDeath = true;
        jump(5f);
    }
    public void jump(float jumpingPower){
            b2dbody.applyLinearImpulse(new Vector2(0, jumpingPower), b2dbody.getWorldCenter(), true);
    }

    public void move(int direction){
        b2dbody.applyLinearImpulse(new Vector2(PLAYER_ACCELERATION * direction, 0), b2dbody.getWorldCenter(), true);
    }

    public int getHealth() { return health; }

    public JohnAnimation getJohnAnimation() {
        return johnAnimation;
    }

    public World getLevel() {
        return level;
    }
}
