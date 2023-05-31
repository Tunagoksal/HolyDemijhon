package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Filter;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Enemies.Enemy;

public class Bouncer extends InteractiveTileObject {

    public Bouncer(Level level, Rectangle bounds) {
        super(level, bounds);

        Filter filter = new Filter();
        filter.maskBits = HolyDemijohn.ENEMY_BIT;

        fixture.setUserData(this);
        fixture.setFilterData(filter);
    }

    @Override
    public void johnCollision() {

    }

    @Override
    public void enemyCollision(Enemy enemy) {
        enemy.reverseVelocity(true,false);
    }
}
