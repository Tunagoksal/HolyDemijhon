package com.holydemijon.Sprites.Animations;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Enemies.Fireball;



public class FireballAnimation extends Sprite {
    Body body;
    Fireball fireball;

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

        setBounds(getX(), getY(), 20 / HolyDemijohn.PPM, 15 / HolyDemijohn.PPM);

    }

    public void update(float dt) {
        stateTimer += dt;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(fireballRun.getKeyFrame(dt));
    }

    public void draw(Batch batch) {
        if (!fireball.destroyed) {
            super.draw(batch);
        }
    }
}
