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
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;


public class EndGameScreen extends ScreenAdapter{

    public static String name;
    private Viewport viewport;
    private Stage stage;
    private HolyDemijohn game;
    private OrthographicCamera cam;
    private Table table;
    private int time;

    private TextField text;
    private TextField.TextFieldStyle textstyle;

    public EndGameScreen(HolyDemijohn game, int time){

        this.time = time;
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


        text.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {

            }
        });
        table.add(endGameButton);
        table.row();
        table.add(text).padLeft(62);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        System.out.println("in end game menu");
    }
    public Stage getStage(){ return stage; }

    @Override
    public void render(float delta) {
        if (text.getText().length() < 8)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE))
            {
                text.setDisabled(true);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
        {
            if (!Gdx.input.isKeyPressed(Input.Keys.BACKSPACE))
            {
                text.setDisabled(false);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            if (text.getText().length() < 15) {
                name = text.getText().substring(7);
            }
            else {
                name = text.getText().substring(7,14);
            }
            System.out.println(name);
            addToDatabase(name, this.time);
            System.out.println(name + " added.");
            System.out.println(this.time + " time passed.");
            game.setScreens(HolyDemijohn.LEADER_BOARD);
            HolyDemijohn.audioManager.playSound(0);
        }
        Gdx.gl.glClearColor(155/255f,173/255f,183/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
    }
    @Override
    public void resize(int x, int y){ viewport.update(x,y); }

    public static void addToDatabase(String name, int time){
        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection collection = database.getCollection("ScoreCollection");

        Document doc = new Document("name", name);
        doc.append("score", time);
        collection.insertOne(doc);

        System.out.println("addToDatabase working fine pls :)");

    }



}
