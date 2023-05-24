package com.holydemijon.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Screens.LevelScreen;
import com.holydemijon.Sprites.TileObjects.Chest;
import com.holydemijon.Sprites.TileObjects.Ground;
import com.holydemijon.Sprites.TileObjects.Ladder;
import com.holydemijon.Sprites.TileObjects.Spikes;

public class Box2DWorldCreator {

    private LevelScreen screen;
    private TiledMap map;
    private World world;
    public Box2DWorldCreator(LevelScreen screen) {
        this.screen = screen;
        this.map = screen.getMap();
        this.world = screen.getWorld();

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Spikes(screen, rect);
        }
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(screen, rect);
        }
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Chest(screen, rect);
        }
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            //Intersector.overlapConvexPolygons()

            new Ladder(screen, rect);
        }
    }
}
