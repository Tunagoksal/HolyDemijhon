package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Enemies.Enemy;
import com.holydemijon.Sprites.John;

public class BearTrap extends InteractiveTileObject {

    public static final int BEAR_TRAP_DAMAGE = 25;
    public BearTrap(Level level, Rectangle bounds) {

        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void johnCollision() {
        John.steppedOnTrap = true;
        Filter filter = new Filter();
        filter.maskBits = HolyDemijohn.OBJECT_BIT;
        fixture.setFilterData(filter);
    }

    @Override
    public void enemyCollision(Enemy enemy) {
        enemy.receiveDamage(50);
    }
}
