package com.holydemijon.Sprites.Animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.holydemijon.HolyDemijhon;

public class JohnAnimation extends Sprite {
    TextureAtlas atlas;
    TextureAtlas atlas2;
    Body johnBody;
    public enum State { FALLING, JUMPING, IDLE, RUNNING, SIMPLE_ATTACK, HEAVY_ATTACK, DASH, DAMAGE, DIE }

    private Animation<TextureRegion> johnIdle;
    private Animation<TextureRegion> johnRun;
    private Animation<TextureRegion> johnJump;
    private Animation<TextureRegion> simpleAttack;
    private Animation<TextureRegion> heavyAttack;
    private Animation<TextureRegion> dash;
    private Animation<TextureRegion> takeDamage;
    private Animation<TextureRegion> die;

    public State currentState;
    public State previousState;
    private float stateTimer;
    private boolean runningRight;

    public static boolean performSimpleAttack;
    public static boolean performHeavyAttack;
    public static boolean performDash;
    public static boolean performTakingDamage;
    public static boolean performDeath;

    public JohnAnimation(TextureAtlas atlas, TextureAtlas atlas2, Body johnBody) {
        super(atlas.findRegion("idle"));
        this.johnBody = johnBody;
        this.atlas = atlas;
        this.atlas2 = atlas2;

        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        runningRight = true;

        performSimpleAttack = false;
        performHeavyAttack = false;
        performDash = false;
        performTakingDamage = false;
        performDeath = false;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(getTexture(), i * 82 + 2, 0, 80, 64));
        }
        johnJump = new Animation<TextureRegion>(0.1f, frames);
        johnRun = new Animation<TextureRegion>(0.1f, atlas.findRegions("running"), Animation.PlayMode.LOOP);
        johnIdle = new Animation<TextureRegion>(0.2f, atlas.findRegions("idle"), Animation.PlayMode.LOOP);
        simpleAttack = new Animation<TextureRegion>(0.1f, atlas.findRegions("attack1"));
        heavyAttack = new Animation<TextureRegion>(0.1f, atlas.findRegions("attack4"));
        dash = new Animation<TextureRegion>(0.1f, atlas.findRegions("jump"));
        takeDamage = new Animation<TextureRegion>(0.1f, atlas2.findRegions("damage"));
        die = new Animation<TextureRegion>(0.1f, atlas2.findRegions("death"));

        setBounds(0, 0, 80 / HolyDemijhon.PPM, 64 / HolyDemijhon.PPM);
    }

    public void update(float dt) {
        setPosition(johnBody.getPosition().x - (getWidth() / 2), johnBody.getPosition().y - (getHeight() / 2));
        setRegion(getFrame(dt));

        if (simpleAttack.isAnimationFinished(stateTimer)) { performSimpleAttack = false; }
        if (heavyAttack.isAnimationFinished(stateTimer)) { performHeavyAttack = false; }
        if (dash.isAnimationFinished(stateTimer)) { performDash = false; }
        if (takeDamage.isAnimationFinished(stateTimer)) { performTakingDamage = false; }
        if (die.isAnimationFinished(stateTimer)) { performDeath = false; }
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case JUMPING:
                region = johnJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = johnRun.getKeyFrame(stateTimer);
                break;
            case DASH:
                region = dash.getKeyFrame(stateTimer);
                break;
            case SIMPLE_ATTACK:
                region = simpleAttack.getKeyFrame(stateTimer);
                break;
            case HEAVY_ATTACK:
                region = heavyAttack.getKeyFrame(stateTimer);
                break;
            case DAMAGE:
                region = takeDamage.getKeyFrame(stateTimer);
                break;
            case DIE:
                region = die.getKeyFrame(stateTimer);
                break;
            default:
                region = johnIdle.getKeyFrame(stateTimer);
        }

        if ((johnBody.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((johnBody.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        if (currentState == previousState) {
            stateTimer = stateTimer + dt;
        }
        else {
            stateTimer = 0;
        }

        previousState = currentState;

        return region;
    }

    public State getState() {

        if (performSimpleAttack) { return State.SIMPLE_ATTACK; }
        if (performHeavyAttack) { return State.HEAVY_ATTACK; }
        if (performDash) { return State.DASH; }
        if (performTakingDamage) { return State.DAMAGE; }
        if (performDeath) { return State.DIE; }

        if (johnBody.getLinearVelocity().y > 0 ||
                (johnBody.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {return State.JUMPING;}
        else if (johnBody.getLinearVelocity().y < 0) {return State.FALLING;}
        else if (johnBody.getLinearVelocity().x != 0) {return State.RUNNING;}
        else {return State.IDLE;}
    }
}
