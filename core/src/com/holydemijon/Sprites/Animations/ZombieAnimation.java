package com.holydemijon.Sprites.Animations;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Enemies.Zombie;

public class ZombieAnimation extends Sprite {

    Body body;
    Zombie zombie;
    public enum State {ATTACK, IDLE, RUN, DAMAGE, DEATH}

    private Animation<TextureRegion> zombieAttack;
    private Animation<TextureRegion> zombieIdle;
    private Animation<TextureRegion> zombieRun;
    private Animation<TextureRegion> zombieDamage;
    private Animation<TextureRegion> zombieDeath;

    private float stateTimer;
    private boolean runningRight;
    private State currentState;
    private State previousState;

    public boolean performAttack;
    public boolean performTakingDamage;
    public boolean performDeath;

    public ZombieAnimation(Zombie zombie, TextureAtlas atlas, Body body) {
        super(atlas.findRegion("idle"));
        this.body = body;
        this.zombie = zombie;

        stateTimer = 0;
        runningRight = true;

        performAttack = false;
        performTakingDamage = false;
        performDeath = false;

        zombieAttack = new Animation<TextureRegion>(0.1f, atlas.findRegions("attack"), Animation.PlayMode.LOOP);
        zombieIdle = new Animation<TextureRegion>(0.2f, atlas.findRegions("idle"), Animation.PlayMode.LOOP);
        zombieRun = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), Animation.PlayMode.LOOP);
        zombieDamage = new Animation<TextureRegion>(0.1f, atlas.findRegions("damage"));
        zombieDeath = new Animation<TextureRegion>(0.1f, atlas.findRegions("death"));

        setBounds(getX(), getY(), 40 / HolyDemijohn.PPM, 40 / HolyDemijohn.PPM);
    }

    public void update(float dt) {
        stateTimer += dt;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 3f);
        setRegion(getFrame(dt));

        if (zombieDamage.isAnimationFinished(stateTimer)) { performTakingDamage = false; }
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region = null;
        switch (currentState) {
            case DEATH:
                region = zombieDeath.getKeyFrame(stateTimer);
                break;
            case DAMAGE:
                region = zombieDamage.getKeyFrame(stateTimer);
                break;
            case ATTACK:
                region = zombieAttack.getKeyFrame(stateTimer);
                break;
            case RUN:
                region = zombieRun.getKeyFrame(stateTimer);
                break;
            default:
                region = zombieIdle.getKeyFrame(stateTimer);
        }

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
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
        if (performDeath) { return State.DEATH; }
        else if (performTakingDamage) { return State.DAMAGE; }
        else if (performAttack) { return State.ATTACK; }
        else if (body.getLinearVelocity().x != 0) { return State.RUN; }
        else { return State.IDLE; }
    }

    public void performAction(int action) {
        if (action == 0) {
            performAttack = true;
        }
        else if (action == 1) {
            performTakingDamage = true;
        }
        else if (action == 2) {
            performDeath = true;
        }
    }

    public void draw(Batch batch) {
        if (!zombie.destroyed || stateTimer < 1) {
            super.draw(batch);
        }
    }
}
