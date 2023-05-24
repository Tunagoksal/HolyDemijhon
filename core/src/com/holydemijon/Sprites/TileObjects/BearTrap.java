package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;

import java.util.logging.Level;

public class BearTrap extends InteractiveTileObject {

    public BearTrap(LevelScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(HolyDemijhon.BEAR_TRAP_BIT);
    }

    @Override
    public void collision() {

    }
}
