package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Enemies.Enemy;
import com.holydemijon.Sprites.John;

public class Chest extends InteractiveTileObject{
    public Chest(Level level, Rectangle bounds) {
        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void johnCollision() {
        John.openedChest=true;
    }

    @Override
    public void enemyCollision(Enemy enemy) {

    }
}
