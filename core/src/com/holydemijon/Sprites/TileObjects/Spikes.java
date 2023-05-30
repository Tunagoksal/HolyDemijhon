package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Enemies.Enemy;
import com.holydemijon.Sprites.John;

public class Spikes extends InteractiveTileObject {

    public static final int SPIKE_DAMAGE = 50;

    public Spikes(Level level, Rectangle bounds) {
        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void johnCollision() {
        John.steppedOnSpike = true;
    }

    @Override
    public void enemyCollision(Enemy enemy) {
        enemy.receiveDamage(SPIKE_DAMAGE);
        enemy.b2dbody.setLinearVelocity(enemy.b2dbody.getLinearVelocity().x, 4);
    }
}
