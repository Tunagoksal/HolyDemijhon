package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Enemies.Orc;
import com.holydemijon.Sprites.Enemies.Wizard;
import com.holydemijon.Sprites.Enemies.Zombie;
import com.holydemijon.Sprites.John;
import com.holydemijon.Tools.Box2DWorldCreator;

public class FirstLevel extends Level {

    private Zombie zombie;
    private Wizard wizard;
    private Orc orc;
    public FirstLevel(HolyDemijohn game){

        super(game);

        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH / HolyDemijohn.PPM, HolyDemijohn.HEIGHT / HolyDemijohn.PPM, cam);
        viewport.setWorldSize(640 / HolyDemijohn.PPM,360 / HolyDemijohn.PPM);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / HolyDemijohn.PPM);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        world = new World(new Vector2(0, GRAVITY), true);
        b2dbr = new Box2DDebugRenderer();
        b2dwc = new Box2DWorldCreator(this);

        b2dwc.setColliders(2,4,6,3,7,5,1);
        b2dwc.colliderCreation();

        orc = new Orc(this, 300 / HolyDemijohn.PPM, 150 / HolyDemijohn.PPM, 0);
        wizard = new Wizard(this, 250 / HolyDemijohn.PPM, 150 / HolyDemijohn.PPM, 0);
        zombie = new Zombie(this, 200 / HolyDemijohn.PPM, 150 / HolyDemijohn.PPM, 0);
        player = new John(world);

        world.setContactListener(listener);
    }
    @Override
    public void show() {}

    public World getWorld() {
        return world;
    }

    @Override
    public TiledMap getMap() { return map; }

    public John getPlayer() {
        return player;
    }

    public void update(float dt){

        zombie.update(dt);
        player.update(dt);
        wizard.update(dt);
        orc.update(dt);

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
        levelOver(HolyDemijohn.FIRST_LEVEL);

        mapRenderer.setView(cam);
        mapRenderer.render();

        b2dbr.render(world, cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        player.getJohnAnimation().draw(game.batch);
        zombie.getZombieAnimation().draw(game.batch);
        wizard.getWizardAnimation().draw(game.batch);
        orc.getOrcAnimation().draw(game.batch);
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
        player.getWorld().dispose();
        //b2dbr.dispose();
    }
}
