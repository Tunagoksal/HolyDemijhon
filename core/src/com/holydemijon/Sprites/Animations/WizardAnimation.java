package com.holydemijon.Sprites.Animations;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Enemies.Wizard;


public class WizardAnimation extends Sprite {
    Body body;
    Wizard wizard;
    public enum State {ATTACK, IDLE, RUN, DAMAGE, DEATH}

    private Animation<TextureRegion> wizardAttack;
    private Animation<TextureRegion> wizardIdle;
    private Animation<TextureRegion> wizardRun;
    private Animation<TextureRegion> wizardDamage;
    private Animation<TextureRegion> wizardDeath;

    private float stateTimer;
    private boolean runningRight;
    private State currentState;
    private State previousState;

    private boolean performAttack;
    private boolean performTakingDamage;
    private boolean performDeath;

    public WizardAnimation(Wizard wizard, TextureAtlas atlas, Body body) {
        super(atlas.findRegion("idle"));
        this.body = body;
        this.wizard = wizard;

        stateTimer = 0;
        runningRight = false;

        performAttack = false;
        performTakingDamage = false;
        performDeath = false;

        wizardAttack = new Animation<TextureRegion>(0.1f, atlas.findRegions("attack"), Animation.PlayMode.LOOP);
        wizardIdle = new Animation<TextureRegion>(0.2f, atlas.findRegions("idle"), Animation.PlayMode.LOOP);
        wizardRun = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), Animation.PlayMode.LOOP);
        wizardDamage = new Animation<TextureRegion>(0.1f, atlas.findRegions("damage"));
        wizardDeath = new Animation<TextureRegion>(0.1f, atlas.findRegions("death"));

        setBounds(getX(), getY(), 72 / HolyDemijohn.PPM, 64 / HolyDemijohn.PPM);
    }

    public void update(float dt) {
        stateTimer += dt;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 6f);
        setRegion(getFrame(dt));

        if (wizardDamage.isAnimationFinished(stateTimer)) { performTakingDamage = false; }
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region = null;
        switch (currentState) {
            case DEATH:
                region = wizardDeath.getKeyFrame(stateTimer);
                break;
            case DAMAGE:
                region = wizardDamage.getKeyFrame(stateTimer);
                break;
            case ATTACK:
                region = wizardAttack.getKeyFrame(stateTimer);
                break;
            case RUN:
                region = wizardRun.getKeyFrame(stateTimer);
                break;
            default:
                region = wizardIdle.getKeyFrame(stateTimer);
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

    public WizardAnimation.State getState() {
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
        if (!wizard.destroyed || stateTimer < 1) {
            super.draw(batch);
        }
    }
}
