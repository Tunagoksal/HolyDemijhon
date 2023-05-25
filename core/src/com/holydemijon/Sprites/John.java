package com.holydemijon.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.Enemies.Enemy;
import sun.security.provider.SHA;

public class John {

    public static final float JOHN_WIDTH = 4;
    public static final float JOHN_HEIGHT = 7;

    public World world;
    public Body b2dbody;
    public LevelScreen screen;

    private TextureAtlas atlas;
    private JohnAnimation johnAnimation;

    public static boolean lookingRight;

    public static Enemy attackableEnemy;

    private int Health = 100;

    public John(World world, LevelScreen screen) {
        this.world = world;
        this.screen = screen;
        defJohn();

        atlas = new TextureAtlas("animations/characterAnimations.atlas");
        johnAnimation = new JohnAnimation(atlas, b2dbody);
        attackableEnemy = null;
        lookingRight = true;
    }

    public void update(float dt) {
        johnAnimation.update(dt);
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
        attackRangeRight.setAsBox(JOHN_WIDTH * 1.4f / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM, new Vector2(JOHN_WIDTH * 2 / HolyDemijhon.PPM, 0), 0);
        fixDef.shape = attackRangeRight;
        b2dbody.createFixture(fixDef).setUserData("attack range right");

        PolygonShape attackRangeLeft = new PolygonShape();
        attackRangeLeft.setAsBox(JOHN_WIDTH * 1.4f / HolyDemijhon.PPM, JOHN_HEIGHT / HolyDemijhon.PPM, new Vector2(-JOHN_WIDTH * 2 / HolyDemijhon.PPM, 0), 0);
        fixDef.shape = attackRangeLeft;
        b2dbody.createFixture(fixDef).setUserData("attack range left");
    }

    public JohnAnimation getJohnAnimation() {
        return johnAnimation;
    }

    public void simpleAttack() {
        JohnAnimation.performSimpleAttack = true;
        if (attackableEnemy != null) {
            attackableEnemy.receiveSimpleAttack();
            Gdx.app.log("Attack", "Enemy health:" + attackableEnemy.getHealth());
        }
    }

    public void heavyAttack() {
        JohnAnimation.performHeavyAttack = true;
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
