package com.holydemijon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.TimeUtils;
import com.holydemijon.Screens.EndGameScreen;
import com.holydemijon.Screens.LevelScreen;
import com.holydemijon.Screens.MainMenuScreen;
import com.holydemijon.Screens.leaderBoardScreen;

import javax.xml.transform.sax.SAXTransformerFactory;


public class HolyDemijhon extends Game {

	// bu constantları da ayrı bir class'a taşıyabiliriz belki
	public static final int MAIN_MENU_SCREEN = 0;
	public static final int LEVEL_SCREEN = 1;
	public static final int END_GAME_SCREEN = 2;
	public static final int LEADER_BOARD = 3;

	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
	public static final float PPM = 100;

	public static final short GROUND_BIT = 1;
	public static final short JOHN_BIT = 2;
	public static final short LADDER_BIT = 4;
	public static final short CHEST_BIT = 8;
	public static final short BEAR_TRAP_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short DESTROYED_BIT = 128;

	public SpriteBatch batch;

	private long start;
	private long end;
	private long time;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}

	public void setScreens(int screen){
        switch (screen){
			case MAIN_MENU_SCREEN:
				setScreen(new MainMenuScreen(this));
				break;
			case LEVEL_SCREEN:
				System.out.println("Start of game.");
				this.start = TimeUtils.millis();
				setScreen(new LevelScreen(this));
				break;
			case END_GAME_SCREEN:
				this.end = TimeUtils.millis();
				this.time = (this.end - this.start) / 1000;
				System.out.println("time: " + time);
				setScreen(new EndGameScreen(this));
				break;
			case LEADER_BOARD:
				setScreen(new leaderBoardScreen(this));
		}

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}


}
