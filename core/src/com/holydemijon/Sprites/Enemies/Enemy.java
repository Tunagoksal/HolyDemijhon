package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.LevelScreen;

public abstract class Enemy extends Sprite {

    protected LevelScreen screen;
    protected World world;
    protected Body b2dbody;

    public Enemy(LevelScreen screen, float x, float y) {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defEnemy();
    }

    protected abstract void defEnemy();
}
