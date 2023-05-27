package com.holydemijon.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Scenes.HUD;


public class BaseLevel extends ScreenAdapter {

    protected HolyDemijhon game;
    private HUD hud;

    public static boolean isDoorOpened = false;

    public static final float FPS = 1/60f;
    public static final float GRAVITY = -10;

    private John player;

    public BaseLevel(HolyDemijhon game){

        this.game = game;
        hud = new HUD(game.batch);

    }

    @Override
    public void render(float delta) {

        // genel olarak burası da değişebilir ileride sadece geçişler çalışsın tüm screenler birbirine bağlansın diye koydum

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            game.setScreens(HolyDemijhon.MAIN_MENU_SCREEN);// Şimdilik ana menüye döndürüyor daha pause menüyü oluşturmadım
        }

        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

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


    public void levelOver(int screen){
        if(isDoorOpened){
            isDoorOpened = false;
            //game.getPrefs().putInteger("Level",HolyDemijhon.SECOND_LEVEL);
            //game.getPrefs().flush();
            game.setScreens(screen);
        }
    }

    public John getPlayer() {
        return player;
    }

}
