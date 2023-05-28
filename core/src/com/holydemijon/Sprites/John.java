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
import com.holydemijon.Sprites.Enemies.Zombie;
import com.holydemijon.Sprites.TileObjects.Spikes;
import com.holydemijon.Sprites.TileObjects.Trampoline;
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

    public static Enemy attackableEnemy1;
    public static Enemy attackableEnemy2;
    public static float johnPositionX;

    private int johnHealth;
    public boolean johnIsDead;

    public static boolean isTouchingGround;
    public static int remainingJumps;

    //PowerUps
    public static boolean dashIsActive;
    public static boolean doubleJumpIsActive;
    public static boolean heavyAttackIsActive;

    //Interactive Tile Object and Enemy effects
    public static boolean steppedOnSpike;
    public static boolean steppedOnTrampoline;
    public static boolean steppedOnEnemy;
    public static Enemy enemyStepped;

    public John(World world) {
        this.world = world;
        defJohn();

        atlas = new TextureAtlas("animations/characterAnimations.atlas");
        atlas2 = new TextureAtlas("animations/player_death.atlas");
        johnAnimation = new JohnAnimation(this, atlas, atlas2);
        attackableEnemy1 = null;
        attackableEnemy2 = null;
        lookingRight = true;

        inputs = new KeyboardInputs(this);
        Gdx.input.setInputProcessor(inputs);

        johnPositionX = b2dbody.getPosition().x;
        johnHealth = JOHN_HEALTH;
        johnIsDead = false;

        isTouchingGround = false;
        remainingJumps = 0;

        dashIsActive = true;
        doubleJumpIsActive = true;
        heavyAttackIsActive = true;

        steppedOnSpike = false;
        steppedOnTrampoline = false;
        steppedOnEnemy = false;
        enemyStepped = null;
    }

    public void update(float dt) {
        if (!johnIsDead) { inputs.update(dt); }
        johnAnimation.update(dt);
        johnPositionX = b2dbody.getPosition().x;
        didStepOnObject();
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

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-4 / HolyDemijohn.PPM, -8 / HolyDemijohn.PPM), new Vector2(4 / HolyDemijohn.PPM, -8 / HolyDemijohn.PPM));
        fixDef.shape = feet;
        b2dbody.createFixture(fixDef).setUserData("feet");

        fixDef.isSensor = true;
        PolygonShape attackRange = new PolygonShape();
        attackRange.setAsBox(JOHN_WIDTH * 6.5f / HolyDemijohn.PPM, JOHN_HEIGHT / HolyDemijohn.PPM, new Vector2(0, 0), 0);
        fixDef.shape = attackRange;
        b2dbody.createFixture(fixDef).setUserData("attack range");
    }

    public void simpleAttack() {

        if (attackableEnemy1 != null) {
            attackableEnemy1.receiveDamage(50);
        }

        if (attackableEnemy2 != null) {
            attackableEnemy2.receiveDamage(50);
        }
    }

    public void heavyAttack() {

        if (attackableEnemy1 != null) {
            attackableEnemy1.receiveDamage(150);
        }

        if (attackableEnemy2 != null) {
            attackableEnemy2.receiveDamage(150);
        }
    }

    public void johnDash(int dashDirection) {
        JohnAnimation.performDash = true;

        Filter ghostFilter = new Filter();
        ghostFilter.maskBits = HolyDemijohn.OBJECT_BIT;
        for (Fixture fixture : b2dbody.getFixtureList())
            fixture.setFilterData(ghostFilter);

        if (dashDirection > 0) {
            b2dbody.applyLinearImpulse(new Vector2(DASH_POWER, 0), b2dbody.getWorldCenter(), true);

            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    if (b2dbody.getLinearVelocity().x > 0) {
                        b2dbody.applyLinearImpulse(new Vector2(-DASH_POWER / 2, 0), b2dbody.getWorldCenter(), true);

                        Filter normalFilter = new Filter();
                        normalFilter.maskBits = HolyDemijohn.OBJECT_BIT | HolyDemijohn.ENEMY_BIT;
                        for (Fixture fixture : b2dbody.getFixtureList())
                            fixture.setFilterData(normalFilter);
                    }
                }
            },0.4f);
        }
        else {
            b2dbody.applyLinearImpulse(new Vector2(-DASH_POWER, 0), b2dbody.getWorldCenter(), true);
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    if (b2dbody.getLinearVelocity().x < 0) {
                        b2dbody.applyLinearImpulse(new Vector2(DASH_POWER / 2, 0), b2dbody.getWorldCenter(), true);

                        Filter normalFilter = new Filter();
                        normalFilter.maskBits = HolyDemijohn.OBJECT_BIT | HolyDemijohn.ENEMY_BIT;
                        for (Fixture fixture : b2dbody.getFixtureList())
                            fixture.setFilterData(normalFilter);
                    }
                }
            },0.4f);
        }
    }

    public void takeDamage(int damage) {
        JohnAnimation.performTakingDamage = true;
        johnHealth -= damage;

        if (johnHealth <= 0) {
            JohnAnimation.performDeath = true;
            johnIsDead = true;
        }
    }

    public void didStepOnObject() {
        if (steppedOnSpike) {
            takeDamage(Spikes.SPIKE_DAMAGE);
            bounce(0, 4);
            steppedOnSpike = false;
        }

        if (steppedOnTrampoline) {
            bounce(0, Trampoline.JUMPING_HEIGHT);
            steppedOnTrampoline = false;
        }

        if (steppedOnEnemy) {
            if (johnPositionX < enemyStepped.getPositionX()) {
                bounce(-2, 2);
            }
            else {
                bounce(2, 2);
            }

            if (enemyStepped instanceof Zombie) {
                takeDamage(Zombie.ZOMBIE_DAMAGE);
            }
            /*
            else if (enemyStepped instanceof Orc) {
                takeDamage(Orc.ORC_DAMAGE);
            }*/

            steppedOnEnemy = false;
            enemyStepped = null;
        }
    }

    //Applies linear impulse in the direction of positive y-axis
    public void jump(float jumpingPower){
        if (b2dbody.getLinearVelocity().y >= -1)
            b2dbody.applyLinearImpulse(new Vector2(0, jumpingPower), b2dbody.getWorldCenter(), true);
        else
            bounce(0, JUMP_HEIGHT / 1.5f);
    }

    //Resets the linear velocity and gives the player a bounce with bounceForce
    public void bounce(float bounceForceX, float bounceForceY) {
        if (bounceForceX == 0)
            b2dbody.setLinearVelocity(b2dbody.getLinearVelocity().x, bounceForceY);
        else
            b2dbody.setLinearVelocity(bounceForceX, bounceForceY);
    }
    public void move(int direction){
        b2dbody.applyLinearImpulse(new Vector2(PLAYER_ACCELERATION * direction, 0), b2dbody.getWorldCenter(), true);
    }

    public static void dashUsed() {
        John.dashIsActive = false;
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                John.dashIsActive = true;
            }
        },0.5f);
    }

    public JohnAnimation getJohnAnimation() {
        return johnAnimation;
    }

    public World getWorld() {
        return world;
    }
}
