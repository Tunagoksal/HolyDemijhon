package com.holydemijon.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.Items.Border;
import com.holydemijon.Sprites.TileObjects.*;
import com.holydemijon.Sprites.Items.Door;

public class Box2DWorldCreator {

    private int ground;
    private int spike;
    private int door;
    private int chest;
    private int tramboline;
    private int trap;
    private int border;
    private int bouncer;

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
        for (MapObject object : map.getLayers().get(door).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Door(level, rect);
        }
        for (MapObject object : map.getLayers().get(tramboline).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Trampoline(level, rect);
        }
        for (MapObject object : map.getLayers().get(trap).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new BearTrap(level, rect);
        }
        for (MapObject object : map.getLayers().get(border).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Border(level, rect);
        }

        for (MapObject object : map.getLayers().get(bouncer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Bouncer(level, rect);
        }
    }

    public void setColliders(int ground, int chest, int door, int spike, int tramboline,int trap,int border,int bouncer){
        this.ground = ground;
        this.chest = chest;
        this.door = door;
        this.spike = spike;
        this.tramboline = tramboline;
        this.trap = trap;
        this.border = border;
        this.bouncer = bouncer;
    }
}
