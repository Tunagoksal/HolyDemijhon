package com.holydemijon.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Sprites.TileObjects.Chest;
import com.holydemijon.Sprites.TileObjects.Ground;
import com.holydemijon.Sprites.TileObjects.Ladder;
import com.holydemijon.Sprites.TileObjects.Spikes;

public class Box2DWorldCreator {
    public Box2DWorldCreator(World world, TiledMap map) {
        //Creates World


        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Spikes(world, map, rect);
        }
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(world, map, rect);
        }
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Chest(world, map, rect);
        }
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //Collision ayarlanana kadar pasif
            //new Ladder(world, map, rect);
        }
    }
}
