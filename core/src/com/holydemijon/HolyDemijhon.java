package com.holydemijon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.TimeUtils;
import com.holydemijon.Screens.Levels.SecondLevel;
import com.holydemijon.Screens.Levels.ThirdLevel;
import com.holydemijon.Screens.EndGameScreen;
import com.holydemijon.Screens.Levels.FirstLevel;
import com.holydemijon.Screens.MainMenuScreen;
import com.holydemijon.Screens.leaderBoardScreen;


public class HolyDemijhon extends Game {

	// bu constantları da ayrı bir class'a taşıyabiliriz belki
	public static final int MAIN_MENU_SCREEN = 0;
	public static final int LEVEL_SCREEN = 1;
	public static final int END_GAME_SCREEN = 2;
	public static final int LEADER_BOARD = 3;
	public static final int FIRST_LEVEL = 4;
	public static final int SECOND_LEVEL = 5;


	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
	public static final float PPM = 100;

	public static final short OBJECT_BIT = 1;
	public static final short JOHN_BIT = 2;
	public static final short ENEMY_BIT = 4;

	public SpriteBatch batch;

	//private FileHandle file;

	private Preferences prefs;

	private MainMenuScreen mainMenu;
	private FirstLevel level1;
	private SecondLevel level2;
	private ThirdLevel level3;

	private int start;
	private int end;
	private int time;


	@Override
	public void create() {

		//file = Gdx.files.local("saveFile.txt");
		//file.writeString("5",false);

		prefs = Gdx.app.getPreferences("savePreference");

		batch = new SpriteBatch();

		mainMenu = new MainMenuScreen(this);
		level1 = new FirstLevel(this);
		level2 = new SecondLevel(this);
		level3 = new ThirdLevel(this);

		setScreen(new MainMenuScreen(this));
	}

	public Preferences getPrefs() {
		return prefs;
	}

	public MainMenuScreen getMainMenu() {
		return mainMenu;
	}

	public FirstLevel getLevelScreen() {
		return level1;
	}

	public SecondLevel getLevel2() {
		return level2;
	}

	public void setScreens(int screen){
        switch (screen){
			case MAIN_MENU_SCREEN:
				setScreen(mainMenu);
				break;
			case LEVEL_SCREEN:
				System.out.println("Start of game.");
				this.start = (int) TimeUtils.millis();
				setScreen(level1);
				break;
			case END_GAME_SCREEN:
				this.end = (int) TimeUtils.millis();
				this.time = (this.end - this.start) / 1000;
				System.out.println("time: " + time);
				setScreen(new EndGameScreen(this, this.time));
				break;
			case LEADER_BOARD:
				setScreen(new leaderBoardScreen(this));
				break;
			case FIRST_LEVEL:
				setScreen(level2);
				break;
			case SECOND_LEVEL:
				setScreen(level3);
				break;
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
