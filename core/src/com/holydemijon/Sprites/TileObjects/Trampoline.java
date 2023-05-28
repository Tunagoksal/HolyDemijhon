package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Filter;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Enemies.Enemy;
import com.holydemijon.Sprites.John;

public class Trampoline extends InteractiveTileObject {

    public static final float JUMPING_HEIGHT = 6;

    public Trampoline(Level level, Rectangle bounds) {

        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void johnCollision() {
        John.steppedOnTrampoline = true;
    }

    @Override
    public void enemyCollision(Enemy enemy) {
        enemy.b2dbody.setLinearVelocity(enemy.b2dbody.getLinearVelocity().x, 8);
    }
}
