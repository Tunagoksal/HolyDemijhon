package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Scenes.HUD;
import com.holydemijon.Tools.Box2DWorldCreator;
import com.holydemijon.Tools.WorldContactListener;


public abstract class Level extends ScreenAdapter {

    protected John player;
    protected HolyDemijohn game;
    protected HUD hud;

    protected WorldContactListener listener;
    protected OrthographicCamera cam;
    protected Viewport viewport;

    protected World world;
    protected Box2DDebugRenderer b2dbr;
    protected Box2DWorldCreator b2dwc;

    protected TmxMapLoader mapLoader;
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer mapRenderer;

    public static boolean isDoorOpened = false;
    public static Screen currentScreen;

    public static final float FPS = 1/60f;
    public static final float GRAVITY = -10;

    public Level(HolyDemijohn game){
        this.game = game;
        hud = new HUD(game.batch);

        listener = new WorldContactListener();
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.input.setInputProcessor(game.getPauseMenuScreen().getStage());
            currentScreen = game.getScreen();
            game.setScreens(HolyDemijohn.PAUSE_MENU_SCREEN);// Şimdilik ana menüye döndürüyor daha pause menüyü oluşturmadım
        }
        if (JohnAnimation.performDeath){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Gdx.input.setInputProcessor(game.getGameOverMenu().getStage());
            game.setScreens(HolyDemijohn.GAME_OVER_MENU);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K))
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Gdx.input.setInputProcessor(game.getEndGameScreen().getStage());
            game.setScreens(HolyDemijohn.END_GAME_SCREEN);
        }
        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }


    public void levelOver(int screen){
        if(isDoorOpened){
            isDoorOpened = false;
            game.getPrefs().putInteger("Level",screen);
            game.getPrefs().flush();
            game.setScreens(screen);
        }
    }

    public abstract World getWorld();
    public abstract TiledMap getMap();

}
