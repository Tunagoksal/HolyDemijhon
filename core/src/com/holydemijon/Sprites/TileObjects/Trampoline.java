package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.LevelScreen;

public class Trampoline extends InteractiveTileObject {

    public Trampoline(LevelScreen screen, Rectangle bounds) {

        super(screen, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void collision() {

    }
}
