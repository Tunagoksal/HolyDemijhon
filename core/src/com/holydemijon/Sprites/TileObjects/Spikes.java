package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.LevelScreen;
import com.holydemijon.Sprites.John;

public class Spikes extends InteractiveTileObject {

    public Spikes(LevelScreen screen, Rectangle bounds) {

        super(screen, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void collision() {

        Gdx.app.log("Spikes", "Collision");
        LevelScreen.getPlayer().setHealth(-25);
    }
}
