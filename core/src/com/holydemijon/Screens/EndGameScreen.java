package com.holydemijon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijohn;


public class EndGameScreen extends ScreenAdapter{

    public static String name;
    private Viewport viewport;
    private Stage stage;
    private HolyDemijohn game;
    private OrthographicCamera cam;
    private Table table;

    private TextField text;
    private TextField.TextFieldStyle textstyle;

    public EndGameScreen(HolyDemijohn game){

        name = "";
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH,HolyDemijohn.HEIGHT,cam);
        stage = new Stage(viewport);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Image endGameButton = new Image(new Texture(Gdx.files.internal("Buttons/endGameButton.png")));

        textstyle = new TextField.TextFieldStyle();
        textstyle.font = new BitmapFont();
        textstyle.fontColor = new Color(40,30,150,1);

        text = new TextField("Name : ",textstyle);
        text.setSize(200,200);
        text.setX(320);
        text.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {

            }
        });
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            name = text.getText();
        }
        table.add(endGameButton);
        table.row();
        table.add(text);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }
    public Stage getStage(){ return stage; }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            name = text.getText();
            System.out.println(name);
        }
        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
    }
    @Override
    public void resize(int x, int y){ viewport.update(x,y); }
}
