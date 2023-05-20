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

public class LevelScreen implements Screen {


    private Player player;
    private HolyDemijhon game;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private OrthographicCamera cam;
    private Viewport viewport;

    int health = 1; // daha karakter olmadığı için canı alabileceğim bir yer yoktu geçici olarak koydum
    // manuel olarak 0 yapıp çalıştırsan direk endgame ekranına geçiyor

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
        int x = 1;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            System.out.println("sdasd");
            cam.translate(x,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println("sdasd");
            cam.translate(-x,0);
        }
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

        update(delta);

        // endgame geçiş bunun da yeri düzenlenebilir ileride
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
