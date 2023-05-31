package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.math.Rectangle;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Enemies.Enemy;

public class Bouncer extends InteractiveTileObject {

    public Bouncer(Level level, Rectangle bounds) {
        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void johnCollision() {

    }

    @Override
    public void enemyCollision(Enemy enemy) {
        enemy.reverseVelocity(true,false);
    }
}
