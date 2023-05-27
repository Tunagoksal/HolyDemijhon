package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.LevelScreen;

public abstract class Enemy extends Sprite {

    protected LevelScreen screen;
    protected World world;
    protected Body b2dbody;

    public boolean setToDestroy;
    public boolean destroyed;

    public static int health;

    public Enemy(LevelScreen screen, float x, float y) {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defEnemy();

        setToDestroy = false;
        destroyed = false;
    }

    protected abstract void defEnemy();
    public int getHealth() {
        return health;
    }
    public void receiveSimpleAttack() {
        health -= 100;
        this.b2dbody.applyLinearImpulse(new Vector2(0, 3), this.b2dbody.getWorldCenter(), true);
    }
    public void receiveHeavyAttack() {}
    public void receiveFireballAttack() {}
    public abstract void kill();
}
