package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Enemies.Fireball;
import com.holydemijon.Sprites.Enemies.Orc;
import com.holydemijon.Sprites.Enemies.Wizard;
import com.holydemijon.Sprites.Enemies.Zombie;
import com.holydemijon.Sprites.John;
import com.holydemijon.Tools.Box2DWorldCreator;

import java.util.ArrayList;

public class FirstLevel extends Level {

    private Fireball fireball;
    private Zombie zombie1;
    private Zombie zombie2;
    private Zombie zombie3;
    private Zombie zombie4;

    private Wizard wizard1;

    private Orc orc1;
    private Orc orc2;
    private Orc orc3;
    private Orc orc4;
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
        b2dbr.setDrawBodies(false);
        b2dwc = new Box2DWorldCreator(this);

        b2dwc.setColliders(2,4,3,5,7,6,9,8);
        b2dwc.colliderCreation();
        wizard1 = new Wizard(this, 1020 / HolyDemijohn.PPM, 400 / HolyDemijohn.PPM, 0);
        for(int i=0;i<8;i++){
            fireballs.add(  new Fireball(this,(1048-100*i) / HolyDemijohn.PPM, 380 / HolyDemijohn.PPM));
        }

        orc1 = new Orc(this, 170 / HolyDemijohn.PPM, 300 / HolyDemijohn.PPM, 0);
        zombie1 = new Zombie(this, 240 / HolyDemijohn.PPM, 300 / HolyDemijohn.PPM, 0);

        orc2 = new Orc(this, 350 / HolyDemijohn.PPM, 540 / HolyDemijohn.PPM, 0);
        zombie2 = new Zombie(this, 220 / HolyDemijohn.PPM, 540 / HolyDemijohn.PPM, 0);

        zombie4 = new Zombie(this, 550 / HolyDemijohn.PPM, 300 / HolyDemijohn.PPM, 0);
        orc3 = new Orc(this, 700 / HolyDemijohn.PPM, 500 / HolyDemijohn.PPM, 0);

        orc4 = new Orc(this, 1020 / HolyDemijohn.PPM, 250 / HolyDemijohn.PPM, 0);
        zombie3= new Zombie(this, 1130 / HolyDemijohn.PPM, 450 / HolyDemijohn.PPM, 0);

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

    public void update(float dt){

        hud.setLevel(1);
        hud.update(dt);
        player.update(dt);
        zombie1.update(dt);
        wizard1.update(dt);
        orc1.update(dt);
        zombie2.update(dt);
        zombie4.update(dt);
        orc2.update(dt);
        zombie3.update(dt);
        orc4.update(dt);
        orc3.update(dt);
        for(int i=0;i<8;i++){
            fireballs.get(i).update(dt);
        }

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

        level = 1;
        update(delta);
        levelOver(HolyDemijohn.SECOND_LEVEL, level);

        mapRenderer.setView(cam);
        mapRenderer.render();

        b2dbr.render(world, cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        player.getJohnAnimation().draw(game.batch);
        zombie1.getZombieAnimation().draw(game.batch);
        wizard1.getWizardAnimation().draw(game.batch);
        orc1.getOrcAnimation().draw(game.batch);
        zombie2.getZombieAnimation().draw(game.batch);
        zombie4.getZombieAnimation().draw(game.batch);
        orc2.getOrcAnimation().draw(game.batch);
        zombie3.getZombieAnimation().draw(game.batch);
        orc4.getOrcAnimation().draw(game.batch);
        orc3.getOrcAnimation().draw(game.batch);
        for(int i=0;i<8;i++){
            fireballs.get(i).getFireBallAnimation().draw(game.batch);
        }

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
        player.getWorld().dispose();
    }
}
