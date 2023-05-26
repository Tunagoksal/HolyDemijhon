package com.holydemijon.Entities.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.holydemijon.Screens.LevelScreen;


public class Door extends InteractiveTileObject {

    Timer timer;

    public Door(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);
        fixture.setUserData(this);
        fixtureDef.isSensor = true;
        timer = new Timer();
    }

    @Override
    public void collision() {
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                LevelScreen.isDoorOpened = true;
            }
        },1);

    }
}
