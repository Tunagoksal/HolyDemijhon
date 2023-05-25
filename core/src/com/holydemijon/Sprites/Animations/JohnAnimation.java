package com.holydemijon.Sprites.Animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;
import com.holydemijon.Sprites.John;

public class JohnAnimation extends Sprite {
    TextureAtlas atlas;
    Body johnBody;
    public enum State { FALLING, JUMPING, STANDING, RUNNING, SIMPLEATTACK, HEAVYATTACK }

    private Animation<TextureRegion> johnStand;
    private Animation<TextureRegion> johnRun;
    private Animation<TextureRegion> johnJump;

    public State currentState;
    public State previousState;
    private float stateTimer;
    private boolean runningRight;

    public JohnAnimation(TextureAtlas atlas, Body johnBody) {
        super(atlas.findRegion("idle"));
        this.johnBody = johnBody;
        this.atlas = atlas;

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(getTexture(), i * 82 + 2, 0, 80, 64));
        }
        johnJump = new Animation<TextureRegion>(0.1f, frames);
        johnRun = new Animation<TextureRegion>(0.1f, atlas.findRegions("running"), Animation.PlayMode.LOOP);
        johnStand = new Animation<TextureRegion>(0.2f, atlas.findRegions("idle"), Animation.PlayMode.LOOP);
        setBounds(0, 0, 80 / HolyDemijhon.PPM, 64 / HolyDemijhon.PPM);
    }

    public void update(float dt) {
        setPosition(johnBody.getPosition().x - getWidth() / 2, johnBody.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        if (currentState == State.JUMPING) {
            region = johnJump.getKeyFrame(stateTimer);
        }
        else if (currentState == State.RUNNING) {
            region = johnRun.getKeyFrame(stateTimer);
        }
        else {
            region = johnStand.getKeyFrame(stateTimer);
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
        if (johnBody.getLinearVelocity().y > 0 ||
                (johnBody.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {return State.JUMPING;}
        else if (johnBody.getLinearVelocity().y < 0) {return State.FALLING;}
        else if (johnBody.getLinearVelocity().x != 0) {return State.RUNNING;}
        else {return State.STANDING;}
    }
}
