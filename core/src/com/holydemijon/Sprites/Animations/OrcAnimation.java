package com.holydemijon.Sprites.Animations;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Enemies.Orc;

public class OrcAnimation extends Sprite {
    Body body;
    Orc orc;
    public enum State {ATTACK, IDLE, RUN, DAMAGE, DEATH}

    private Animation<TextureRegion> orcAttack;
    private Animation<TextureRegion> orcIdle;
    private Animation<TextureRegion> orcRun;
    private Animation<TextureRegion> orcDamage;
    private Animation<TextureRegion> orcDeath;

    private float stateTimer;
    private boolean runningRight;
    private State currentState;
    private State previousState;

    private boolean performAttack;
    private boolean performTakingDamage;
    private boolean performDeath;

    public OrcAnimation(Orc orc, TextureAtlas atlas, Body body) {
        super(atlas.findRegion("idle"));
        this.body = body;
        this.orc = orc;

        stateTimer = 0;
        runningRight = true;

        performAttack = false;
        performTakingDamage = false;
        performDeath = false;

        orcAttack = new Animation<TextureRegion>(0.1f, atlas.findRegions("attack"), Animation.PlayMode.LOOP);
        orcIdle = new Animation<TextureRegion>(0.2f, atlas.findRegions("idle"), Animation.PlayMode.LOOP);
        orcRun = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), Animation.PlayMode.LOOP);
        orcDamage = new Animation<TextureRegion>(0.1f, atlas.findRegions("damage"));
        orcDeath = new Animation<TextureRegion>(0.1f, atlas.findRegions("death"));

        setBounds(getX(), getY(), 78 / HolyDemijohn.PPM, 64 / HolyDemijohn.PPM);
    }

    public void update(float dt) {
        stateTimer += dt;
        if (runningRight)
            setPosition(body.getPosition().x - getWidth() / 3, body.getPosition().y - getHeight() / 4);
        else
            setPosition(body.getPosition().x - getWidth() / 1.5f, body.getPosition().y - getHeight() / 4);
        setRegion(getFrame(dt));

        if (orcDamage.isAnimationFinished(stateTimer)) { performTakingDamage = false; }
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region = null;
        switch (currentState) {
            case DEATH:
                region = orcDeath.getKeyFrame(stateTimer);
                break;
            case DAMAGE:
                region = orcDamage.getKeyFrame(stateTimer);
                break;
            case ATTACK:
                region = orcAttack.getKeyFrame(stateTimer);
                break;
            case RUN:
                region = orcRun.getKeyFrame(stateTimer);
                break;
            default:
                region = orcIdle.getKeyFrame(stateTimer);
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
        if (!orc.destroyed || stateTimer < 1) {
            super.draw(batch);
        }
    }
}
