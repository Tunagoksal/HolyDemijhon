package com.holydemijon.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;

public abstract class InteractiveTileObject {
    protected LevelScreen screen;
    protected World world;
    protected TiledMap tiledMap;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;
    protected FixtureDef fixtureDef;

    public InteractiveTileObject(LevelScreen screen, Rectangle bounds) {
        this.world = screen.getWorld();
        this.tiledMap = screen.getMap();
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / HolyDemijhon.PPM, (bounds.getY() + bounds.getHeight() / 2) / HolyDemijhon.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / HolyDemijhon.PPM, bounds.getHeight() / 2 / HolyDemijhon.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void collision();
    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}
