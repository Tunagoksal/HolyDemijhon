package com.holydemijon.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.Entities.John;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Scenes.HUD;
import com.holydemijon.Tools.Box2DWorldCreator;
import com.holydemijon.Tools.WorldContactListener;

public class BaseLevel extends ScreenAdapter {

    private HolyDemijhon game;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private OrthographicCamera cam;
    private Viewport viewport;

    private World world;
    private Box2DDebugRenderer b2dbr;
    private Box2DWorldCreator b2dwc;

    public static final float GRAVITY = -10;

    private John player;

    public BaseLevel(HolyDemijhon game,String mapfile){

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH,HolyDemijhon.HEIGHT,cam);
        //viewport.setScreenSize(360,360);
        viewport.setWorldSize(360,360);

        hud = new HUD(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapfile);
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        world = new World(new Vector2(0, GRAVITY), true);
        b2dbr = new Box2DDebugRenderer();
        b2dwc = new Box2DWorldCreator(world, map);
        player = new John(world);

        world.setContactListener(new WorldContactListener());


    }

    @Override
    public void render(float delta) {

        // genel olarak burası da değişebilir ileride sadece geçişler çalışsın tüm screenler birbirine bağlansın diye koydum

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            game.setScreens(HolyDemijhon.MAIN_MENU_SCREEN);// Şimdilik ana menüye döndürüyor daha pause menüyü oluşturmadım
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        mapRenderer.setView(cam);
        mapRenderer.render();


        // endgame geçiş bunun da yeri düzenlenebilir ileride
        //if(health == 0){
            //game.setScreens(HolyDemijhon.END_GAME_SCREEN);
        //}

        /*
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        //player.render();
        game.batch.end();
         */

    }

    public void isLevelOver(){



    }

    public Box2DWorldCreator getB2dwc() {
        return b2dwc;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

}
