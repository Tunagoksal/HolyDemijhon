package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Chest extends InteractiveTileObject{
    public Chest(World world, TiledMap tiledMap, Rectangle bounds) {
        super(world, tiledMap, bounds);
        BodyDef bodyDef = new BodyDef();
    }
}
