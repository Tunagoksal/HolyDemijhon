package com.holydemijon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijhon;

public class leaderBoardScreen extends ScreenAdapter{

    private Viewport viewport;
    private Stage stage;
    private HolyDemijhon game;
    private OrthographicCamera cam;
    private Table table;

    private TextField text;
    private TextField.TextFieldStyle textstyle;

    public leaderBoardScreen(HolyDemijhon game) {

        this.game= game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH,HolyDemijhon.HEIGHT,cam);
        stage = new Stage(viewport, (game).batch);

        textstyle = new TextField.TextFieldStyle();
        textstyle.font = new BitmapFont();
        textstyle.fontColor = new Color(40,30,150,1);

        text = new TextField("Name",textstyle);
        //textField.setSize(50,100);
        text.setBounds(100,100,200,50);

        table = new Table();
        table.center();
        table.setFillParent(true);

        Texture menuTexture = new Texture(Gdx.files.internal("menu/HolyLeaderBoard.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);
        table.setBackground(menuDrawable);

        table.add(text);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {



        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.input.setInputProcessor(game.getMainMenu().getStage());
            game.setScreens(HolyDemijhon.MAIN_MENU_SCREEN);
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

}
