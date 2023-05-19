package com.holydemijon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Sprites.Player;
import com.holydemijon.Scenes.HUD;

public class LevelScene implements Screen {

    private Player player;
    private HolyDemijhon game;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private OrthographicCamera cam;
    private Viewport viewport;

    public LevelScene(HolyDemijhon game){
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH,HolyDemijhon.HEIGHT,cam);
        viewport.setScreenSize(640,360);
        viewport.setWorldSize(1280,720);

        hud = new HUD(game.batch);
        player = new Player();

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Tileset.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        cam.position.set(viewport.getScreenWidth()/4,viewport.getScreenHeight()/4,0);
    }
    @Override
    public void show() {

    }

    public void update(float dt){
        handleInput(dt);

        cam.update();
        mapRenderer.setView(cam);
    }

    private void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            int x = 10;
            cam.translate(viewport.getScreenWidth()/2 + x,viewport.getScreenHeight()/2+x);
        }
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        mapRenderer.setView(cam);
        mapRenderer.render();

        /*
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        //player.render();
        game.batch.end();
         */

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
