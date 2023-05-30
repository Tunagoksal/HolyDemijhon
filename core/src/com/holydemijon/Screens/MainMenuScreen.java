package com.holydemijon.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijohn;

public class MainMenuScreen extends ScreenAdapter{

    private Viewport viewport;
    private Stage stage;
    private HolyDemijohn game;
    private OrthographicCamera cam;

    public MainMenuScreen(final HolyDemijohn game){

        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH, HolyDemijohn.HEIGHT,cam);
        stage = new Stage(viewport);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Table sliderTable = new Table();
        sliderTable.center();
        sliderTable.setFillParent(true);

        /*
        Texture menuTexture = new Texture(Gdx.files.internal("Buttons/CSmenuB2.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);
        table.setBackground(menuDrawable);
        */

        Gdx.input.setInputProcessor(stage);

        Image titleImage = new Image(new Texture(Gdx.files.internal("Buttons/title.png")));

        Image soundIcon= new Image(new Texture(Gdx.files.internal("Buttons/soundIcon.png")));

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

        Texture ControlsTexture = new Texture(Gdx.files.internal("Buttons/ControlsButton.png"));
        TextureRegion ControlsRegion = new TextureRegion(ControlsTexture);
        TextureRegionDrawable ControlsDrawable = new TextureRegionDrawable(ControlsRegion);

        Texture leaderBoardTexture = new Texture(Gdx.files.internal("Buttons/LeaderBoard.png"));
        TextureRegion leaderBoardRegion = new TextureRegion(leaderBoardTexture);
        TextureRegionDrawable leaderBoardDrawable = new TextureRegionDrawable(leaderBoardRegion);

        ImageButton leaderBoardButton = new ImageButton(leaderBoardDrawable);
        ImageButton continueButton = new ImageButton(continueDrawable);
        ImageButton newGameButton = new ImageButton(newGameDrawable);
        ImageButton controlsButton = new ImageButton(ControlsDrawable);
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
                game.resetLevels();
                game.setScreens(HolyDemijohn.FIRST_LEVEL);
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
                game.setScreens(HolyDemijohn.LEADER_BOARD);
            }
        });

        newGameButton.setSize(newGameTexture.getWidth(),newGameTexture.getHeight());

        table.add(titleImage);
        table.row();
        table.add(newGameButton);
        table.row();
        table.add(continueButton);
        table.row();
        table.add(controlsButton);
        table.row();
        table.add(leaderBoardButton);
        table.row();
        table.add(exitGameButton);
        table.row();

        sliderTable.setPosition(0,-155);
        sliderTable.add(soundIcon).width(30);
        sliderTable.add(soundSlider);

        stage.addActor(table);
        stage.addActor(sliderTable);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.X)){
            game.setScreens(HolyDemijohn.END_GAME_SCREEN);
        }

        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    @Override
    public void resize(int x, int y){ viewport.update(x,y); }

}
