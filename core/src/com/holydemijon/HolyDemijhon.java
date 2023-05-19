package com.holydemijon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.holydemijon.Screens.LevelScene;

public class HolyDemijhon extends Game {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;

	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new LevelScene(this));
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
