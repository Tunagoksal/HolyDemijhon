package com.holydemijon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijohn;


public class EndGameScreen extends ScreenAdapter implements Input.TextInputListener {

    private Viewport viewport;
    private Stage stage;
    private HolyDemijohn game;
    private OrthographicCamera cam;
    private Table table;

    private TextField text;
    private TextField.TextFieldStyle textstyle;

    public EndGameScreen(HolyDemijohn game){

        // Enes screenleri paylaşalım dediğimiz için biraz sana ithafen yazıyorum backgroundı eklerken scalelemede gariplik oluyor
        // çözmeye uğraşmadım sen çözersen onu halledebilirsin

        this.game= game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH, HolyDemijohn.HEIGHT,cam);
        stage = new Stage(viewport, (game).batch);

        textstyle = new TextField.TextFieldStyle();
        textstyle.font = new BitmapFont();
        textstyle.fontColor = new Color(40,30,150,1);

        text = new TextField("",textstyle);
        //textField.setSize(50,100);
        text.setBounds(100,100,200,50);

        table = new Table();
        table.center();
        table.setFillParent(true);

        table.add(text);

        Texture menuTexture = new Texture(Gdx.files.internal("menu/endgamemenu.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);

        Image menu = new Image(menuTexture);
        //table.setBackground(menuDrawable);
        table.add(menu);

        stage.addActor(table);
        System.out.println("in end game menu");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void input(String text) {
        this.text.setText(text);
    }

    @Override
    public void canceled() {

    }
}
