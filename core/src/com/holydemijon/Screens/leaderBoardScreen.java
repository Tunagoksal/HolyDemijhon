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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijohn;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class leaderBoardScreen extends ScreenAdapter{

    private Viewport viewport;
    private Stage stage;
    private HolyDemijohn game;
    private OrthographicCamera cam;
    private Table table;

    private TextField text1;
    private TextField text2;
    private TextField.TextFieldStyle textstyle;

    private static ArrayList<String> topScores;

    public leaderBoardScreen(HolyDemijohn game) {

        this.game= game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH, HolyDemijohn.HEIGHT,cam);
        stage = new Stage(viewport, (game).batch);

        textstyle = new TextField.TextFieldStyle();
        textstyle.font = new BitmapFont();
        textstyle.fontColor = new Color(40,30,150,1);

        topScores = new ArrayList<>();

        getTopScores();
        text1 = new TextField("Name",textstyle);
        text2 = new TextField("Name",textstyle);
        text1.setPosition(200,100);

        table = new Table();
        table.center();
        table.setFillParent(true);

        Texture menuTexture = new Texture(Gdx.files.internal("menu/HolyLeaderBoard.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);
        table.setBackground(menuDrawable);

        for (String score : topScores) {
            TextField textField = new TextField(score, textstyle);
            textField.setDisabled(true);
            table.add(textField).padLeft(100).row();
        }

        table.add(text1).padLeft(100);
        table.add(text2);
        stage.addActor(table);
    }

    public static void getTopScores(){
        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection<Document> collection = database.getCollection("ScoreCollection");

        Bson sort = Sorts.ascending("score");

        FindIterable<Document> iterDoc = collection.find().sort(sort).limit(5);
        MongoCursor<Document> it = iterDoc.iterator();

        while(it.hasNext()){
            Document doc = it.next();
            String name = doc.getString("name");
            int score = doc.getInteger("score");
            String scoreText = "Name: " + name + ", Score: " + score;
            topScores.add(scoreText);

            System.out.println("Name: " + doc.getString("name") + ", Score: " + doc.getInteger("score"));
        }

        System.out.println("getTopScores working fine pls :)");
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.input.setInputProcessor(game.getMainMenu().getStage());
            game.setScreens(HolyDemijohn.MAIN_MENU_SCREEN);
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

    }
    @Override
    public void resize(int x, int y){ viewport.update(x,y); }
}
