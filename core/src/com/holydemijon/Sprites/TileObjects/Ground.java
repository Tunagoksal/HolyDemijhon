package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Enemies.Enemy;

public class Ground extends InteractiveTileObject {

    public Ground(Level level, Rectangle bounds) {
        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void johnCollision() {

    }

    @Override
    public void enemyCollision(Enemy enemy) {
        enemy.enemyTouchingGround=true;
    }
}
