package com.holydemijon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.holydemijon.Levels.FirstLevel;
import com.holydemijon.Levels.SecondLevel;
import com.holydemijon.Screens.EndGameScreen;
import com.holydemijon.Screens.LevelScreen;
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


	public SpriteBatch batch;

	//private FileHandle file;

	private Preferences prefs;

	private MainMenuScreen mainMenu;
	private LevelScreen levelScreen;
	private FirstLevel level1;
	private EndGameScreen endGameScreen;

	@Override
	public void create() {

		//file = Gdx.files.local("saveFile.txt");
		//file.writeString("5",false);

		prefs = Gdx.app.getPreferences("savePreference");

		batch = new SpriteBatch();

		mainMenu = new MainMenuScreen(this);
		levelScreen = new LevelScreen(this);
		endGameScreen = new EndGameScreen(this);
		level1 = new FirstLevel(this);

		setScreen(new MainMenuScreen(this));
	}

	public Preferences getPrefs() {
		return prefs;
	}

	public MainMenuScreen getMainMenu() {
		return mainMenu;
	}

	public LevelScreen getLevelScreen() {
		return levelScreen;
	}

	public void setScreens(int screen){
        switch (screen){
			case MAIN_MENU_SCREEN:
				setScreen(mainMenu);
				break;
			case LEVEL_SCREEN:
				setScreen(levelScreen);
				break;
			case END_GAME_SCREEN:
				setScreen(endGameScreen);
				break;
			case LEADER_BOARD:
				setScreen(new leaderBoardScreen(this));
				break;
			case FIRST_LEVEL:
				setScreen(level1);
				break;
			case SECOND_LEVEL:
				setScreen(new SecondLevel(this));
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
