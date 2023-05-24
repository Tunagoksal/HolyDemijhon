package com.holydemijon.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijhon;

import javax.swing.text.Style;

public class MainMenuScreen extends ScreenAdapter{

    private Viewport viewport;
    private Stage stage;
    private HolyDemijhon game;
    private OrthographicCamera cam;

    public MainMenuScreen(final HolyDemijhon game){

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH,HolyDemijhon.HEIGHT,cam);
        stage = new Stage(viewport);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        /*
        Texture menuTexture = new Texture(Gdx.files.internal("Buttons/CSmenuB2.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);
        table.setBackground(menuDrawable);*/

        Gdx.input.setInputProcessor(stage);


        Texture sliderTexture = new Texture(Gdx.files.internal("Buttons/soundSlider.png"));
        TextureRegion sliderRegion = new TextureRegion(sliderTexture);
        TextureRegionDrawable sliderDrawable = new TextureRegionDrawable(sliderRegion);
        Texture knob = new Texture(Gdx.files.internal("Buttons/knob.png"));
        TextureRegion knobr = new TextureRegion(knob);
        TextureRegionDrawable knobd = new TextureRegionDrawable(knobr);
        Slider.SliderStyle style = new Slider.SliderStyle(sliderDrawable,knobd);
        Slider soundSlider = new Slider(0,100,10,false,style);

        Texture exitGameTexture = new Texture(Gdx.files.internal("Buttons/exitGame.png"));
        TextureRegion exitGameRegion = new TextureRegion(exitGameTexture);
        TextureRegionDrawable exitGameDrawable = new TextureRegionDrawable(exitGameRegion);

        Texture continueTexture = new Texture(Gdx.files.internal("Buttons/continue.png"));
        TextureRegion continueRegion = new TextureRegion(continueTexture);
        TextureRegionDrawable continueDrawable = new TextureRegionDrawable(continueRegion);

        Texture newGameTexture = new Texture(Gdx.files.internal("Buttons/newGame.png"));
        TextureRegion newGameRegion = new TextureRegion(newGameTexture);
        TextureRegionDrawable newGameDrawable = new TextureRegionDrawable(newGameRegion);

        Texture leaderBoardTexture = new Texture(Gdx.files.internal("Buttons/LeaderBoard.png"));
        TextureRegion leaderBoardRegion = new TextureRegion(leaderBoardTexture);
        TextureRegionDrawable leaderBoardDrawable = new TextureRegionDrawable(leaderBoardRegion);

        ImageButton leaderBoardButton = new ImageButton(leaderBoardDrawable);
        ImageButton continueButton = new ImageButton(continueDrawable);
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
                Gdx.input.setInputProcessor(null);
                game.setScreens(HolyDemijhon.LEVEL_SCREEN);
            }
        });

        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //String x = game.getFile().readString();
                //game.setScreens(Integer.valueOf(x));

                game.setScreens(game.getPrefs().getInteger("Level"));
            }
        });

        leaderBoardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                game.setScreens(HolyDemijhon.LEADER_BOARD);
            }
        });

        newGameButton.setSize(newGameTexture.getWidth(),newGameTexture.getHeight());

        table.add(newGameButton);
        table.row();
        table.add(continueButton);
        table.row();
        table.add(exitGameButton);
        table.row();
        table.add(leaderBoardButton);
        table.row();
        table.add(soundSlider);
        stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

}
