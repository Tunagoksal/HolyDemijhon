package com.holydemijon.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.Sprites.TileObjects.Chest;
import com.holydemijon.Sprites.Items.Door;
import com.holydemijon.Sprites.TileObjects.Ground;
import com.holydemijon.Sprites.TileObjects.Spikes;

public class Box2DWorldCreator {

    private int ground;
    private int spike;
    private int door;
    private int chest;


    World world;
    TiledMap map;

    public Box2DWorldCreator(World world, TiledMap map) {

        this.world = world;
        this.map = map;

    }

    public void colliderCreation(){
        //Creates World
        for (MapObject object : map.getLayers().get(spike).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Spikes(world, map, rect);
        }
        for (MapObject object : map.getLayers().get(door).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Door(world, map, rect);
        }
        for (MapObject object : map.getLayers().get(ground).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(world, map, rect);
        }
        for (MapObject object : map.getLayers().get(chest).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Chest(world, map, rect);
        }
    }

    public void setColliders(int ground, int chest, int door, int spike){
        this.ground = ground;
        this.chest = chest;
        this.door = door;
        this.spike = spike;
    }
}
