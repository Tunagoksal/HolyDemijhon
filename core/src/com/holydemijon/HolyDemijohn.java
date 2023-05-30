package com.holydemijon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.TimeUtils;
import com.holydemijon.Screens.*;
import com.holydemijon.Screens.Levels.SecondLevel;
import com.holydemijon.Screens.Levels.ThirdLevel;
import com.holydemijon.Screens.Levels.FirstLevel;


public class HolyDemijohn extends Game {

	public static final int MAIN_MENU_SCREEN = 0;
	public static final int FIRST_LEVEL = 1;
	public static final int SECOND_LEVEL = 2;
	public static final int THIRD_LEVEL = 3;
	public static final int END_GAME_SCREEN = 4;
	public static final int LEADER_BOARD = 5;
	public static final int PAUSE_MENU_SCREEN = 6;
	public static final int GAME_OVER_MENU = 7;

	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
	public static final float PPM = 100;

	public static final short OBJECT_BIT = 1;
	public static final short JOHN_BIT = 2;
	public static final short ENEMY_BIT = 4;

	public SpriteBatch batch;

	private Preferences prefs;

	private MainMenuScreen mainMenu;
	private FirstLevel level1;
	private SecondLevel level2;
	private ThirdLevel level3;
	private PauseMenuScreen pauseMenuScreen;
	private GameOverMenu gameOverMenu;

	private int start;
	private int end;
	private int time;

	@Override
	public void create() {

		prefs = Gdx.app.getPreferences("savePreference");

		batch = new SpriteBatch();

		mainMenu = new MainMenuScreen(this);
		level1 = new FirstLevel(this);
		level2 = new SecondLevel(this);
		level3 = new ThirdLevel(this);
		pauseMenuScreen = new PauseMenuScreen(this);
		gameOverMenu = new GameOverMenu(this);

		setScreen(new MainMenuScreen(this));
	}

	public Preferences getPrefs() {
		return prefs;
	}

	public MainMenuScreen getMainMenu() {
		return mainMenu;
	}

	public PauseMenuScreen getPauseMenuScreen() { return pauseMenuScreen; }

	public FirstLevel getLevelScreen() {
		return level1;
	}

	public SecondLevel getLevel2() { return level2; }

	public void setScreens(int screen){
        switch (screen){
			case MAIN_MENU_SCREEN:
				setScreen(mainMenu);
				break;
			case FIRST_LEVEL:
				setScreen(level1);
				System.out.println("Start of game.");
				System.out.println("At level 1");
				this.start = (int) TimeUtils.millis();
				break;
			case SECOND_LEVEL:
				setScreen(level2);
				System.out.println("At level 2");
				break;
			case THIRD_LEVEL:
				setScreen(level3);
				System.out.println("At level 3");
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
			case PAUSE_MENU_SCREEN:
				setScreen(pauseMenuScreen);
				break;
			case GAME_OVER_MENU:
				setScreen(gameOverMenu);
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


	public GameOverMenu getGameOverMenu() { return gameOverMenu; }

	public EndGameScreen getEndGameScreen() { return new EndGameScreen(this,time);}
	public void resetLevels(){
		level1 = new FirstLevel(this);
		level2 = new SecondLevel(this);
		level3 = new ThirdLevel(this);
	}
}
