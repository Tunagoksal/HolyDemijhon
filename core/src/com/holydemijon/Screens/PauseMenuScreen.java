package com.holydemijon.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Tools.AudioManager;

public class PauseMenuScreen extends ScreenAdapter{
    private HolyDemijohn game;
    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;
    private Slider soundSlider;
    public PauseMenuScreen(final HolyDemijohn game){
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH,HolyDemijohn.HEIGHT,cam);
        stage = new Stage(viewport);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Table sliderTable = new Table();
        sliderTable.center();
        sliderTable.setFillParent(true);

        Gdx.input.setInputProcessor(stage);

        Image pauseMenuButton = new Image(new Texture(Gdx.files.internal("Buttons/pauseMenuButton.jpg")));

        Image soundIcon= new Image(new Texture(Gdx.files.internal("Buttons/soundIcon.png")));

        Texture sliderTexture = new Texture(Gdx.files.internal("Buttons/soundSlider.png"));
        TextureRegion sliderRegion = new TextureRegion(sliderTexture);
        TextureRegionDrawable sliderDrawable = new TextureRegionDrawable(sliderRegion);
        Texture knob = new Texture(Gdx.files.internal("Buttons/knob.png"));
        TextureRegion knobr = new TextureRegion(knob);
        TextureRegionDrawable knobd = new TextureRegionDrawable(knobr);
        Slider.SliderStyle style = new Slider.SliderStyle(sliderDrawable,knobd);
        soundSlider = new Slider(0,1,0.01f,false,style);
        soundSlider.setValue(AudioManager.volume);

        Texture resumeButtonTexture = new Texture(Gdx.files.internal("Buttons/resumeButton.jpg"));
        TextureRegion resumeButtonRegion = new TextureRegion(resumeButtonTexture);
        TextureRegionDrawable resumeButtonDrawable = new TextureRegionDrawable(resumeButtonRegion);

        Texture quitButtonTexture = new Texture(Gdx.files.internal("Buttons/quitButton.jpg"));
        TextureRegion quitButtonRegion = new TextureRegion(quitButtonTexture);
        TextureRegionDrawable quitButtonDrawable = new TextureRegionDrawable(quitButtonRegion);

        ImageButton resumeButton = new ImageButton(resumeButtonDrawable);
        ImageButton quitButton = new ImageButton(quitButtonDrawable);

        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                game.setScreen(Level.currentScreen);
                HolyDemijohn.audioManager.playSound(0);
            }
        });

        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                game.getPrefs().putInteger("Level", Level.level);
                Level.setHealth = Level.health;
                Level.setPowerUps = Level.powerUps;
                Level.setWorldTimer = Level.worldTimer;
                game.setScreens(HolyDemijohn.MAIN_MENU_SCREEN);
                Gdx.input.setInputProcessor(game.getMainMenu().getStage());
                HolyDemijohn.audioManager.playMusic(0);
                HolyDemijohn.audioManager.playSound(0);
            }
        });

        table.add(pauseMenuButton);
        table.row();
        table.add(resumeButton).padTop(10);
        table.row();
        table.add(quitButton).padTop(10);
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
        if (soundSlider.isDragging()) {
            HolyDemijohn.audioManager.setGameVolume(soundSlider.getValue());
            game.getMainMenu().getSoundSlider().setValue(AudioManager.volume);
        }

        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    @Override
    public void resize(int x, int y){ viewport.update(x,y); }

    public Slider getSoundSlider() {
        return this.soundSlider;
    }
}
