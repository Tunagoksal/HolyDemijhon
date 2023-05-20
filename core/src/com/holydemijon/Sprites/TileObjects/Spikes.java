package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.LevelScreen;

public class Spikes extends InteractiveTileObject {

    public Spikes(World world, TiledMap tiledMap, Rectangle bounds) {
        super(world, tiledMap, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void collision() {
        Gdx.app.log("Spikes", "Collision");
        LevelScreen.health -= 1;
    }
}
