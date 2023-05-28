package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.John;

public abstract class InteractiveTileObject {

    protected Level level;
    protected World world;
    protected TiledMap tiledMap;
    protected John player;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;
    protected FixtureDef fixtureDef;

    public InteractiveTileObject(Level level, Rectangle bounds) {
        this.level = level;
        this.world = level.getWorld();
        this.tiledMap = level.getMap();
        this.player = level.getPlayer();
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / HolyDemijohn.PPM, (bounds.getY() + bounds.getHeight() / 2) / HolyDemijohn.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / HolyDemijohn.PPM, bounds.getHeight() / 2 / HolyDemijohn.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void collision();
}
