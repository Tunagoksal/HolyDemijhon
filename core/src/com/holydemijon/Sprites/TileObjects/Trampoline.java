package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.Levels.Level;

public class Trampoline extends InteractiveTileObject {

    public static final float JUMPING_HEIGHT = 8;

    public Trampoline(Level level, Rectangle bounds) {

        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void collision() {
    }
}
