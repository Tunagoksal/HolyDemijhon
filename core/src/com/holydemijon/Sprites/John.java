package com.holydemijon.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.Enemies.Enemy;
import com.holydemijon.Tools.KeyboardInputs;

public class John extends Sprite {

    public static final float JUMP_HEIGHT = 4f;
    public static final float MAX_LINEAR_VELOCITY = 1;
    public static final float PLAYER_ACCELERATION = 0.1f;

    public static final float JOHN_WIDTH = 4;
    public static final float JOHN_HEIGHT = 7;

    public static final int JOHN_HEALTH = 500;
    private static final float DASH_POWER = 2;

    private KeyboardInputs inputs;

    private World world;
    public Body b2dbody;
    private BodyDef bodydef;

    private TextureAtlas atlas;
    private TextureAtlas atlas2;
    private JohnAnimation johnAnimation;
    public static boolean lookingRight;

    public static Enemy attackableEnemy;
    public static float johnPositionX;

    private int johnHealth;
    public boolean johnIsDead;

    public John(World world) {
        this.world = world;
        defJohn();

        atlas = new TextureAtlas("animations/characterAnimations.atlas");
        atlas2 = new TextureAtlas("animations/player_death.atlas");
        johnAnimation = new JohnAnimation(this, atlas, atlas2);
        attackableEnemy = null;
        lookingRight = true;

        inputs = new KeyboardInputs(this);
        Gdx.input.setInputProcessor(inputs);

        johnPositionX = b2dbody.getPosition().x;
        johnHealth = JOHN_HEALTH;
        johnIsDead = false;
    }

    public void update(float dt) {
        if (!johnIsDead) { inputs.update(dt); }
        johnAnimation.update(dt);
        johnPositionX = b2dbody.getPosition().x;
    }

    private void defJohn() {
        bodydef = new BodyDef();
        bodydef.position.set(100 / HolyDemijohn.PPM, 150 / HolyDemijohn.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(JOHN_WIDTH / HolyDemijohn.PPM, JOHN_HEIGHT / HolyDemijohn.PPM);

        fixDef.filter.categoryBits = HolyDemijohn.JOHN_BIT;
        fixDef.filter.maskBits = HolyDemijohn.OBJECT_BIT |
                HolyDemijohn.ENEMY_BIT;

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData(this);

        fixDef.isSensor = true;
        PolygonShape attackRange = new PolygonShape();
        attackRange.setAsBox(JOHN_WIDTH * 6.5f / HolyDemijohn.PPM, JOHN_HEIGHT / HolyDemijohn.PPM, new Vector2(0, 0), 0);
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

    public void johnDash(int dashDirection) {
        JohnAnimation.performDash = true;

        if (dashDirection > 0) {
            b2dbody.applyLinearImpulse(new Vector2(DASH_POWER, 0), b2dbody.getWorldCenter(), true);
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    if (b2dbody.getLinearVelocity().x > 0)
                        b2dbody.applyLinearImpulse(new Vector2(-DASH_POWER / 2, 0), b2dbody.getWorldCenter(), true);
                }
            },0.4f);
        }
        else {
            b2dbody.applyLinearImpulse(new Vector2(-DASH_POWER, 0), b2dbody.getWorldCenter(), true);
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    if (b2dbody.getLinearVelocity().x < 0)
                        b2dbody.applyLinearImpulse(new Vector2(DASH_POWER / 2, 0), b2dbody.getWorldCenter(), true);
                }
            },0.4f);
        }
    }

    public void takeDamage(int damage) {
        JohnAnimation.performTakingDamage = true;
        johnHealth -= damage;

        if (johnHealth <= 0) {
            johnDied();
        }
    }

    public void johnDied() {
        JohnAnimation.performDeath = true;
        johnIsDead = true;
    }
    //Applies linear impulse in the direction of positive y-axis
    public void jump(float jumpingPower){
            b2dbody.applyLinearImpulse(new Vector2(0, jumpingPower), b2dbody.getWorldCenter(), true);
    }

    //Resets the linear velocity and gives the player a bounce with bounceForce
    public void bounce(float bounceForceX, float bounceForceY) {

        b2dbody.setLinearVelocity(bounceForceX, bounceForceY);
    }
    public void move(int direction){
        b2dbody.applyLinearImpulse(new Vector2(PLAYER_ACCELERATION * direction, 0), b2dbody.getWorldCenter(), true);
    }

    public JohnAnimation getJohnAnimation() {
        return johnAnimation;
    }

    public World getWorld() {
        return world;
    }
}
