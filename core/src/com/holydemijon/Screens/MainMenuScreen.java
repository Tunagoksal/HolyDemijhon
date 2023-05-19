package com.holydemijon.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijhon;

public class MainMenuScreen extends ScreenAdapter {

    private Viewport viewport;
    private Stage stage;
    private HolyDemijhon game;
    private OrthographicCamera cam;

    public MainMenuScreen(final HolyDemijhon game){
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH,HolyDemijhon.HEIGHT,cam);
        stage = new Stage(viewport, (game).batch);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        /*
        Texture menuTexture = new Texture(Gdx.files.internal("Buttons/CSmenuB2.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);
        table.setBackground(menuDrawable);*/

        Gdx.input.setInputProcessor(stage);

        Texture exitGameTexture = new Texture(Gdx.files.internal("Buttons/exitGame.png"));
        TextureRegion exitGameRegion = new TextureRegion(exitGameTexture);
        TextureRegionDrawable exitGameDrawable = new TextureRegionDrawable(exitGameRegion);

        Texture newGameTexture = new Texture(Gdx.files.internal("Buttons/newGame.png"));
        TextureRegion newGameRegion = new TextureRegion(newGameTexture);
        TextureRegionDrawable newGameDrawable = new TextureRegionDrawable(newGameRegion);

        Texture leaderBoardTexture = new Texture(Gdx.files.internal("Buttons/LeaderBoard.png"));
        TextureRegion leaderBoardRegion = new TextureRegion(leaderBoardTexture);
        TextureRegionDrawable leaderBoardDrawable = new TextureRegionDrawable(leaderBoardRegion);

        ImageButton leaderBoardButton = new ImageButton(leaderBoardDrawable);
        ImageButton newGameButton = new ImageButton(newGameDrawable);
        ImageButton exitGameButton = new ImageButton(exitGameDrawable);

        exitGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("in exit button");
                Gdx.app.exit();
                System.exit(0);
            }
        });
        newGameButton.addListener(new ChangeListener() {
            int x =0;
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(x++);
                //game.setScreen(new LevelScreen(game));
                game.setScreens(HolyDemijhon.LEVEL_SCREEN);
            }
        });

        leaderBoardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreens(HolyDemijhon.LEADER_BOARD);
            }
        });

        newGameButton.setSize(newGameTexture.getWidth(),newGameTexture.getHeight());

        table.add(newGameButton);
        table.row();
        table.add(exitGameButton);
        table.row();
        table.add(leaderBoardButton);
        stage.addActor(table);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
