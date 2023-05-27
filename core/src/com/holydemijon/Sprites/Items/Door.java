package com.holydemijon.Sprites.Items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.TileObjects.InteractiveTileObject;


public class Door {

    private World world;
    private TiledMap tiledMap;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;
    private Fixture fixture;
    private FixtureDef fixtureDef;

    Timer timer;

    public Door(World world, TiledMap tiledMap, Rectangle bounds) {
        this.world = world;
        this.tiledMap = tiledMap;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / HolyDemijhon.PPM, (bounds.getY() + bounds.getHeight() / 2) / HolyDemijhon.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / HolyDemijhon.PPM, bounds.getHeight() / 2 / HolyDemijhon.PPM);

        fixtureDef.isSensor = true;
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

        fixture.setUserData(this);
        timer = new Timer();
    }

    public void collision() {
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                Level.isDoorOpened = true;
            }
        },0.5f);

    }
}
