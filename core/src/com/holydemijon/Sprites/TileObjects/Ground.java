package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;

public class Ground extends InteractiveTileObject {

    public Ground(LevelScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(HolyDemijhon.GROUND_BIT);
    }

    @Override
    public void collision() {

    }
}
