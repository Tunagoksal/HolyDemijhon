package com.holydemijon.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.TileObjects.Chest;
import com.holydemijon.Sprites.Items.Door;
import com.holydemijon.Sprites.TileObjects.Ground;
import com.holydemijon.Sprites.TileObjects.Spikes;

public class Box2DWorldCreator {

    private int ground;
    private int spike;
    private int door;
    private int chest;

    private Level level;
    private World world;
    private TiledMap map;

    public Box2DWorldCreator(Level level) {
        this.level = level;
        this.world = level.getWorld();
        this.map = level.getMap();
    }

    public void colliderCreation(){
        //Creates World
        for (MapObject object : map.getLayers().get(spike).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Spikes(level, rect);
        }
        for (MapObject object : map.getLayers().get(door).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Door(level, rect);
        }
        for (MapObject object : map.getLayers().get(ground).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(level, rect);
        }
        for (MapObject object : map.getLayers().get(chest).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Chest(level, rect);
        }
    }

    public void setColliders(int ground, int chest, int door, int spike){
        this.ground = ground;
        this.chest = chest;
        this.door = door;
        this.spike = spike;
    }
}
