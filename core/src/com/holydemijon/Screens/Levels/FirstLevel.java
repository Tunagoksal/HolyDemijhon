package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Sprites.Enemies.Zombie;
import com.holydemijon.Sprites.John;
import com.holydemijon.Tools.Box2DWorldCreator;
import com.holydemijon.Tools.WorldContactListener;

public class FirstLevel extends Level {

    private Zombie zombie;
    private John player;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public FirstLevel(HolyDemijhon game){

        super(game);

        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH / HolyDemijhon.PPM, HolyDemijhon.HEIGHT / HolyDemijhon.PPM, cam);
        viewport.setWorldSize(640 / HolyDemijhon.PPM,360 / HolyDemijhon.PPM);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / HolyDemijhon.PPM);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        world = new World(new Vector2(0, GRAVITY), true);
        b2dbr = new Box2DDebugRenderer();
        b2dwc = new Box2DWorldCreator(world, map);

        b2dwc.setColliders(2,4,6,3);
        b2dwc.colliderCreation();

        zombie = new Zombie(this, 200f / HolyDemijhon.PPM, 150f / HolyDemijhon.PPM, 0);
        player = new John(world);

        world.setContactListener(listener);
    }
    @Override
    public void show() {}

    public World getWorld() {
        return world;
    }

    public John getPlayer() {
        return player;
    }

    public void update(float dt){

        hud.update(dt);
        hud.setLevel(1);

        zombie.update(dt);
        player.update(dt);

        world.step(FPS, 6, 2);
        cam.position.x = player.b2dbody.getPosition().x;
        cam.position.y = player.b2dbody.getPosition().y;
        cam.update();
        mapRenderer.setView(cam);
    }

    @Override
    public void render(float delta) {

        super.render(delta);

        update(delta);
        levelOver(HolyDemijhon.FIRST_LEVEL);

        mapRenderer.setView(cam);
        mapRenderer.render();

        b2dbr.render(world, cam.combined);

        /*
        if(health == 0){
            super.getGame().setScreens(HolyDemijhon.(GAME OVER MENU GELECEK));
        }
        */

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        player.getJohnAnimation().draw(game.batch);
        zombie.getZombieAnimation().draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void dispose() {
        super.dispose();
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        player.getLevel().dispose();
        //b2dbr.dispose();
    }
}
