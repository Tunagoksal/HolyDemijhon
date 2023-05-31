package com.holydemijon.Sprites.Animations;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Enemies.Fireball;



public class FireballAnimation extends Sprite {
    Body body;
    Fireball fireball;

    public enum State {RUN}
    private FireballAnimation.State currentState;
    private FireballAnimation.State previousState;

    private Animation<TextureRegion> fireballRun;
    private float stateTimer;
    private boolean runningRight;

    public FireballAnimation(Fireball fireball, TextureAtlas atlas, Body body) {
        super(atlas.findRegion("fireball"));
        this.body = body;
        this.fireball = fireball;
        stateTimer = 0;
        runningRight = true;
        fireballRun = new Animation<TextureRegion>(0.1f, atlas.findRegions("fireball"));

        setBounds(getX(), getY(), 50 / HolyDemijohn.PPM, 50 / HolyDemijohn.PPM);

    }

    public void update(float dt) {
        stateTimer += dt;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

    }

    public FireballAnimation.State getState() {

        if (body.getLinearVelocity().x != 0) { return FireballAnimation.State.RUN; }
        return FireballAnimation.State.RUN;
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region = null;
        switch (currentState) {
            case RUN:
                region = fireballRun.getKeyFrame(stateTimer);
                break;
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

    public void draw(Batch batch) {
        if (!fireball.destroyed || stateTimer < 1) {
            super.draw(batch);
        }
    }
}
