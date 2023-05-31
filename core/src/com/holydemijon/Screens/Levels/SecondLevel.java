package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.holydemijon.Sprites.Enemies.Orc;
import com.holydemijon.Sprites.Enemies.Wizard;
import com.holydemijon.Sprites.Enemies.Zombie;
import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Tools.Box2DWorldCreator;

public class SecondLevel extends Level {

    private FirstLevel firstLevel;

    private Zombie zombie1;
    private Zombie zombie2;

    private Wizard wizard1;
    private Wizard wizard2;
    private Wizard wizard3;

    private Orc orc1;
    private Orc orc2;

    public SecondLevel(HolyDemijohn game){

        super(game);

        firstLevel = game.getLevelScreen();
        setHud(firstLevel.getHud());

        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH / HolyDemijohn.PPM, HolyDemijohn.HEIGHT / HolyDemijohn.PPM, cam);
        viewport.setWorldSize(640 / HolyDemijohn.PPM,360 / HolyDemijohn.PPM);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / HolyDemijohn.PPM);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        world = new World(new Vector2(0, GRAVITY), true);
        b2dbr = new Box2DDebugRenderer();
        b2dwc = new Box2DWorldCreator(this);

        orc1 = new Orc(this, 390 / HolyDemijohn.PPM, 500 / HolyDemijohn.PPM, 0);
        wizard1 = new Wizard(this, 490 / HolyDemijohn.PPM, 450 / HolyDemijohn.PPM, 0);
        zombie1 = new Zombie(this, 800 / HolyDemijohn.PPM, 400 / HolyDemijohn.PPM, 0);

        orc2 = new Orc(this, 700 / HolyDemijohn.PPM, 400 / HolyDemijohn.PPM, 0);
        wizard2 = new Wizard(this, 750 / HolyDemijohn.PPM, 550 / HolyDemijohn.PPM, 0);
        zombie2 = new Zombie(this, 530 / HolyDemijohn.PPM, 200 / HolyDemijohn.PPM, 0);

        wizard3 = new Wizard(this, 230 / HolyDemijohn.PPM, 500 / HolyDemijohn.PPM, 0);


        b2dwc.setColliders(3,5,4,5,7,6,8,9);
        b2dwc.colliderCreation();
        player = new John(world);

        world.setContactListener(listener);
    }

    public void update(float dt){

        hud.setLevel(2);
        hud.update(dt);

        player.update(dt);
        zombie1.update(dt);
        wizard1.update(dt);
        orc1.update(dt);
        zombie2.update(dt);
        wizard2.update(dt);
        orc2.update(dt);
        wizard3.update(dt);

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
        super.levelOver(HolyDemijohn.THIRD_LEVEL);

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
        wizard2.getWizardAnimation().draw(game.batch);
        orc2.getOrcAnimation().draw(game.batch);
        wizard3.getWizardAnimation().draw(game.batch);
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
        player.getWorld().dispose();
    }
}
