package com.holydemijon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijhon;

import java.awt.*;


public class EndGameScreen extends ScreenAdapter {

    private Viewport viewport;
    private Stage stage;
    private HolyDemijhon game;
    private OrthographicCamera cam;
    private Table table;

    public EndGameScreen(HolyDemijhon game){

        // Enes screenleri paylaşalım dediğimiz için biraz sana ithafen yazıyorum backgroundı eklerken scalelemede gariplik oluyor
        // çözmeye uğraşmadım sen çözersen onu halledebilirsin

        this.game= game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH,HolyDemijhon.HEIGHT,cam);
        stage = new Stage(viewport, (game).batch);

        table = new Table();
        table.center();
        table.setFillParent(true);

        Texture menuTexture = new Texture(Gdx.files.internal("menu/endgamemenu.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);
        table.setBackground(menuDrawable);

        stage.addActor(table);
        System.out.println("in end game menu");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

}