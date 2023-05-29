package com.holydemijon.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.holydemijon.HolyDemijohn;
import com.holydemijon.Screens.Levels.Level;

import java.awt.*;

public class HUD implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;

    Label countDownLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;

    private static int level = 1;

    public HUD(SpriteBatch batch){

        worldTimer = 0;
        timeCount = 0;

        viewport = new FitViewport(HolyDemijohn.WIDTH,HolyDemijohn.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table= new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(),Color.WHITE);

        countDownLabel = new Label(String.format("%03d", worldTimer),style);
        timeLabel = new Label("TIME", style);
        levelLabel = new Label("" + this.level, style);
        worldLabel = new Label("LEVEL", style);


        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(worldLabel).expandX().padTop(1);
        table.add(timeLabel).expandX().padTop(10);
        //add a second row to our table
        table.row();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            worldTimer++;
            levelLabel.setText(String.valueOf(this.level));
            countDownLabel.setText(String.format("%03d",worldTimer));
            timeCount = 0;

        }
    }

    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
