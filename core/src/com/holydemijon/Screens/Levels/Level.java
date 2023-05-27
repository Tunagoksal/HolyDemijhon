package com.holydemijon.Screens.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Scenes.HUD;


public abstract class Level extends ScreenAdapter {

    protected HolyDemijhon game;
    private HUD hud;

    public static boolean isDoorOpened = false;

    public static final float FPS = 1/60f;
    public static final float GRAVITY = -10;

    private John player;

    public Level(HolyDemijhon game){

        this.game = game;
        hud = new HUD(game.batch);

    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            game.setScreens(HolyDemijhon.MAIN_MENU_SCREEN);// Şimdilik ana menüye döndürüyor daha pause menüyü oluşturmadım
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

    public John getPlayer() {
        return player;
    }

}
