package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.Levels.Level;

public class Spikes extends InteractiveTileObject {

    public static final int SPIKE_DAMAGE = 25;

    public Spikes(Level level, Rectangle bounds) {
        super(level, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void collision() {
    }
}
