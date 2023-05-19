package com.holydemijon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
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

	public SpriteBatch batch;

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
				setScreen(new LevelScreen(this));
				break;
			case END_GAME_SCREEN:
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
