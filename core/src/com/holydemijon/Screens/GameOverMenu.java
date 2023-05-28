package com.holydemijon.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.John;

public class GameOverMenu extends ScreenAdapter {
    private Viewport viewport;
    private Stage stage;
    private HolyDemijohn game;
    private OrthographicCamera cam;

    public GameOverMenu(final HolyDemijohn game){
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH,HolyDemijohn.HEIGHT,cam);
        stage = new Stage(viewport);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Gdx.input.setInputProcessor(stage);

        Image youDied = new Image(new Texture(Gdx.files.internal("Buttons/youDiedButton.jpg")));

        Texture restartButtonTexture = new Texture(Gdx.files.internal("Buttons/restartButton.jpg"));
        TextureRegion restartButtonRegion = new TextureRegion(restartButtonTexture);
        TextureRegionDrawable restartButtonDrawable = new TextureRegionDrawable(restartButtonRegion);

        Texture quitButtonTwoTexture = new Texture(Gdx.files.internal("Buttons/quitButtonTwo.jpg"));
        TextureRegion quitButtonTwoRegion = new TextureRegion(quitButtonTwoTexture);
        TextureRegionDrawable quitButtonTwoDrawable = new TextureRegionDrawable(quitButtonTwoRegion);

        ImageButton restartButton = new ImageButton(restartButtonDrawable);
        ImageButton quitButtonTwo = new ImageButton(quitButtonTwoDrawable);

        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                JohnAnimation.performDeath = false;
                Gdx.input.setInputProcessor(null);
                game.setScreens(HolyDemijohn.FIRST_LEVEL);
            }
        });

        quitButtonTwo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("in exit button");
                Gdx.app.exit();
                System.exit(0);
            }
        });
        table.add(youDied);
        table.row();
        table.add(restartButton);
        table.row();
        table.add(quitButtonTwo);
        table.row();

        stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
}
