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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private Label labelName1;
    private Label labelScore1;
    private Label labelName2;
    private Label labelScore2;
    private Label labelName3;
    private Label labelScore3;
    private Label labelName4;
    private Label labelScore4;
    private Label labelName5;
    private Label labelScore5;
    private Label labelName6;
    private Label labelScore6;


    static ArrayList<String> names;
    static ArrayList<Integer> scores;

    private static ArrayList<String> topScores;

    public leaderBoardScreen(HolyDemijohn game) {

        this.game= game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijohn.WIDTH, HolyDemijohn.HEIGHT,cam);
        stage = new Stage(viewport, (game).batch);

        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(),Color.DARK_GRAY);

        names = new ArrayList<>();
        scores = new ArrayList<>();

        topScores = new ArrayList<>();

        getTopScores();
        
        labelName1 = new Label(names.get(0), style);
        labelScore1 = new Label("" + scores.get(0), style);
        labelName2 = new Label(names.get(1), style);
        labelScore2 = new Label("" + scores.get(1), style);
        labelName3 = new Label(names.get(2), style);
        labelScore3 = new Label("" + scores.get(2), style);
        labelName4 = new Label(names.get(3), style);
        labelScore4 = new Label("" + scores.get(3), style);
        labelName5 = new Label(names.get(4), style);
        labelScore5 = new Label("" + scores.get(4), style);
        labelName6 = new Label(names.get(5), style);
        labelScore6 = new Label("" + scores.get(5), style);

        table = new Table();
        table.center();
        table.setFillParent(true);

        Texture menuTexture = new Texture(Gdx.files.internal("menu/HolyLeaderboard.png"));
        TextureRegion menuRegion = new TextureRegion(menuTexture);
        TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuRegion);
        table.setBackground(menuDrawable);

        table.add(labelName1).expandX().padLeft(170).padTop(140);
        table.add(labelScore1).expandX().padRight(220).padTop(140);
        table.row();
        table.add(labelName2).expandX().padLeft(170).padTop(15);
        table.add(labelScore2).expandX().padRight(220).padTop(15);
        table.row();
        table.add(labelName3).expandX().padLeft(170).padTop(10);
        table.add(labelScore3).expandX().padRight(220).padTop(10);
        table.row();
        table.add(labelName4).expandX().padLeft(170).padTop(10);
        table.add(labelScore4).expandX().padRight(220).padTop(10);
        table.row();
        table.add(labelName5).expandX().padLeft(170).padTop(15);
        table.add(labelScore5).expandX().padRight(220).padTop(15);
        table.row();
        table.add(labelName6).expandX().padLeft(170).padTop(15);
        table.add(labelScore6).expandX().padRight(220).padTop(15);
        table.row();
        
        stage.addActor(table);
    }

    public static void getTopScores(){
        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection<Document> collection = database.getCollection("ScoreCollection");

        Bson sort = Sorts.ascending("score");

        FindIterable<Document> iterDoc = collection.find().sort(sort).limit(6);
        MongoCursor<Document> it = iterDoc.iterator();

        while(it.hasNext()){
            Document doc = it.next();
            names.add(doc.getString("name"));
            scores.add(doc.getInteger("score"));
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
