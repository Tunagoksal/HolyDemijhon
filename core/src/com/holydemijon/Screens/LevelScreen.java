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
<<<<<<<< HEAD:core/src/com/holydemijon/Screens/LevelScene.java
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Sprites.Player;
import com.holydemijon.Scenes.HUD;

public class LevelScene implements Screen {
========
import com.holydemijon.HUD;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Player;

public class LevelScreen implements Screen {
>>>>>>>> BranchTuna:core/src/com/holydemijon/Screens/LevelScreen.java

    private Player player;
    private HolyDemijhon game;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private OrthographicCamera cam;
    private Viewport viewport;

    int health = 1;

    public LevelScreen(HolyDemijhon game){

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH,HolyDemijhon.HEIGHT,cam);
        //viewport.setScreenSize(360,360);
        viewport.setWorldSize(360,360);

        hud = new HUD(game.batch);
        player = new Player();

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Tileset.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);
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
            System.out.println("sdasd");
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

        if(health == 0){
            game.setScreens(HolyDemijhon.END_GAME_SCREEN);
        }

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
