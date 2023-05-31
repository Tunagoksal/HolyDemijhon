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
import com.holydemijon.Sprites.John;

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
    Label powerLabel;
    Label dashLabel;
    Label doubleJumpLabel;
    Label heavyAttackLabel;

    private static int level = 1;

    public HUD(SpriteBatch batch){

        worldTimer = 0;
        timeCount = 0;

        viewport = new FitViewport(HolyDemijohn.WIDTH,HolyDemijohn.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table= new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(),Color.DARK_GRAY);

        countDownLabel = new Label(String.format("%03d", worldTimer),style);
        timeLabel = new Label("TIME", style);
        levelLabel = new Label("" + this.level, style);
        worldLabel = new Label("LEVEL", style);
        powerLabel = new Label("POWERS",style);
        dashLabel= new Label("Dash: -",style);
        doubleJumpLabel= new Label("Double Jump: -",style);
        heavyAttackLabel= new Label("Heavy Attack: -",style);


        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(worldLabel).expandX().padTop(0);
        table.add(powerLabel).expandX().padTop(0);
        table.add(timeLabel).expandX().padTop(0);
        //add a second row to our table
        table.row();
        table.add(levelLabel).expandX();
        table.add(dashLabel).expandX();
        table.add(countDownLabel).expandX();
        table.row();
        table.add().expandX();
        table.add(doubleJumpLabel).center();
        table.add().expandX();
        table.row();
        table.add().expandX();
        table.add(heavyAttackLabel).expandX();
        table.add().expandX();

        stage.addActor(table);
    }

    public void update(float dt){
        if(John.dashIsActive){
            dashLabel.setText("Dash: +");
        }
        if(John.doubleJumpIsActive){
            doubleJumpLabel.setText("Double Jump: +");
        }

        if(John.heavyAttackIsActive){
            heavyAttackLabel.setText("Heavy Attack: +");
        }

        timeCount += dt;
        if(timeCount >= 1){
            worldTimer++;
            levelLabel.setText(String.valueOf(this.level));
            countDownLabel.setText(String.format("%03d",worldTimer));
            timeCount = 0;
        }
    }

    public int getWorldTimer(){
        return worldTimer;
    }
    public void setWorldTimer(int worldTimer) { this.worldTimer = worldTimer;}

    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
