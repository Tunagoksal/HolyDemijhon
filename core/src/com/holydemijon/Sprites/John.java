package com.holydemijon.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;
import com.holydemijon.Sprites.Enemies.Enemy;
import sun.security.provider.SHA;

public class John extends Sprite {

    public enum State { FALLING, JUMPING, STANDING, RUNNING, SIMPLEATTACK, HEAVYATTACK }
    public State currentState;
    public State previousState;

    public static final float JOHN_WIDTH = 4;
    public static final float JOHN_HEIGHT = 7;

    public World world;
    public Body b2dbody;

    private TextureRegion johnStand;
    private Animation<TextureRegion> johnRun;
    private Animation<TextureRegion> johnJump;
    private float stateTimer;
    private boolean runningRight;


    public static Enemy attackableEnemy;

    private int Health = 100;

    public John(World world, LevelScreen screen) {
        super(screen.getAtlas().findRegion("idle"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("running"), i * 80, 0, 80, 64));
        }
        johnRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(getTexture(), i * 80, 0, 80, 64));
        }
        johnJump = new Animation<TextureRegion>(0.1f, frames);
        defJohn();
        johnStand = new TextureRegion(screen.getAtlas().findRegion("idle"));
        setBounds(0, 0, 80 / HolyDemijhon.PPM, 64 / HolyDemijhon.PPM);
        setRegion(johnStand);

        attackableEnemy = null;
    }

    public void update(float dt) {
        setPosition(b2dbody.getPosition().x - getWidth() / 2, b2dbody.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        if (currentState == State.JUMPING) {
            region = johnJump.getKeyFrame(stateTimer);
        }
        else if (currentState)
    }

    public State getState() {
        if (b2dbody.getLinearVelocity().y > 0 ||
                (b2dbody.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {return State.JUMPING;}
        else if (b2dbody.getLinearVelocity().y < 0) {return State.FALLING;}
        else if (b2dbody.getLinearVelocity().x != 0) {return State.RUNNING;}
        else {return State.STANDING;}
    }

    private void defJohn() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(32 / HolyDemijhon.PPM, 150 / HolyDemijhon.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bodydef);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(JOHN_WIDTH / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM);

        fixDef.filter.categoryBits = HolyDemijhon.JOHN_BIT;
        fixDef.filter.maskBits = HolyDemijhon.GROUND_BIT |
                HolyDemijhon.CHEST_BIT |
                HolyDemijhon.OBJECT_BIT |
                HolyDemijhon.ENEMY_BIT;

        fixDef.shape = shape;
        b2dbody.createFixture(fixDef).setUserData("player");


        fixDef.isSensor = true;
        PolygonShape attackRangeRight = new PolygonShape();
        attackRangeRight.setAsBox(JOHN_WIDTH * 1.2f / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM, new Vector2(JOHN_WIDTH * 2 / HolyDemijhon.PPM, 0), 0);
        fixDef.shape = attackRangeRight;
        b2dbody.createFixture(fixDef).setUserData("attack range right");

        PolygonShape attackRangeLeft = new PolygonShape();
        attackRangeLeft.setAsBox(JOHN_WIDTH * 1.2f / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM, new Vector2(-JOHN_WIDTH * 2 / HolyDemijhon.PPM, 0), 0);
        fixDef.shape = attackRangeLeft;
        b2dbody.createFixture(fixDef).setUserData("attack range left");
    }

    public void simpleAttack() {
        if (attackableEnemy != null) {
            attackableEnemy.receiveSimpleAttack();
            Gdx.app.log("Attack", "Enemy health:" + attackableEnemy.getHealth());
        }
    }

    public void setHealth(int health) {
        Health += health;
    }

    public int getHealth() {
        return Health;
    }
}
