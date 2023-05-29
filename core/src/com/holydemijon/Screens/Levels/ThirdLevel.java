package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Tools.Box2DWorldCreator;

public class ThirdLevel extends Level {
    public static boolean isOver;

    public ThirdLevel(HolyDemijohn game){

        super(game);

        isOver  = false;

        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH / HolyDemijohn.PPM, HolyDemijohn.HEIGHT / HolyDemijohn.PPM, cam);
        viewport.setWorldSize(640 / HolyDemijohn.PPM,360 / HolyDemijohn.PPM);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level3.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / HolyDemijohn.PPM);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        world = new World(new Vector2(0, GRAVITY), true);
        b2dbr = new Box2DDebugRenderer();
        b2dwc = new Box2DWorldCreator(this);

        b2dwc.setColliders(1,5,6,3,2,4,7);
        b2dwc.colliderCreation();
        player = new John(world);

        world.setContactListener(listener);
    }

    public void update(float dt){

        player.update(dt);

        world.step(FPS, 6, 2);
        if (!player.johnIsDead) {
            cam.position.x = player.b2dbody.getPosition().x;
            cam.position.y = player.b2dbody.getPosition().y;
        }
        cam.update();
        mapRenderer.setView(cam);
    }

    @Override
    public void render(float delta) {

        super.render(delta);

        update(delta);
        super.levelOver(HolyDemijohn.END_GAME_SCREEN);

        mapRenderer.setView(cam);
        mapRenderer.render();

        b2dbr.render(world, cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        player.getJohnAnimation().draw(game.batch);
        //zombie.getZombieAnimation().draw(game.batch);
        game.batch.end();

    }

    @Override
    public World getWorld() { return world; }

    @Override
    public TiledMap getMap() { return map; }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        b2dbr.dispose();
        player.getWorld().dispose();
    }
}