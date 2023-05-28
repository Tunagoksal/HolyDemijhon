package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Tools.Box2DWorldCreator;
import com.holydemijon.Tools.WorldContactListener;

public class SecondLevel extends Level {

    private John player;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private FirstLevel firstLevel;

    public SecondLevel(HolyDemijhon game){

        super(game);

        firstLevel = game.getLevelScreen();
        setHud(firstLevel.getHud());

        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH / HolyDemijhon.PPM, HolyDemijhon.HEIGHT / HolyDemijhon.PPM, cam);
        viewport.setWorldSize(640 / HolyDemijhon.PPM,360 / HolyDemijhon.PPM);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / HolyDemijhon.PPM);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        world = new World(new Vector2(0, GRAVITY), true);
        b2dbr = new Box2DDebugRenderer();
        b2dwc = new Box2DWorldCreator(world, map);

        b2dwc.setColliders(1,5,4,2);
        b2dwc.colliderCreation();
        player = new John(world);

        world.setContactListener(listener);
    }

    public void update(float dt){

        hud.update(dt);
        hud.setLevel(2);

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
        super.levelOver(HolyDemijhon.SECOND_LEVEL);

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
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        b2dbr.dispose();
        player.getLevel().dispose();
    }
}
