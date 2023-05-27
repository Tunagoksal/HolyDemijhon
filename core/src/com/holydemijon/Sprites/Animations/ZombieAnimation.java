package com.holydemijon.Sprites.Animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.holydemijon.HolyDemijhon;

public class ZombieAnimation extends Sprite {

    Body body;
    public enum State {ATTACK, IDLE, RUN}

    private Animation<TextureRegion> zombieAttack;
    private Animation<TextureRegion> zombieIdle;
    private Animation<TextureRegion> zombieRun;

    private float stateTimer;
    private boolean runningRight;
    private State currentState;
    private State previousState;
    private boolean isAttacking;

    public ZombieAnimation(TextureAtlas atlas, Body body, int state) {
        super(atlas.findRegion("idle"));
        this.body = body;

        stateTimer = 0;
        runningRight = true;

        if (state == 0) {
            currentState = State.RUN;
            previousState = State.RUN;
        }
        else {
            currentState = State.IDLE;
            previousState = State.IDLE;
        }


        zombieAttack = new Animation<TextureRegion>(0.1f, atlas.findRegions("attack"), Animation.PlayMode.LOOP);
        zombieIdle = new Animation<TextureRegion>(0.1f, atlas.findRegions("idle"), Animation.PlayMode.LOOP);
        zombieRun = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), Animation.PlayMode.LOOP);
        setBounds(getX(), getY(), 40 / HolyDemijhon.PPM, 40 / HolyDemijhon.PPM);
    }

    public void update(float dt) {
        stateTimer += dt;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 4f);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        if (currentState == State.RUN) {
            region = zombieRun.getKeyFrame(stateTimer);
        }
        else if (currentState == State.ATTACK) {
            region = zombieAttack.getKeyFrame(stateTimer);
        }
        else {
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
        if (isAttacking) {return State.ATTACK;}
        else if (body.getLinearVelocity().x != 0) {return State.RUN;}
        else {return State.IDLE;}
    }
}
