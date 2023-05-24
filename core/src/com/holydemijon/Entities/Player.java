package com.holydemijon.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player implements InputProcessor {
    TextureAtlas atlas;
    public Animation<TextureRegion> runningAnimation;
    public Animation<TextureRegion> idleAnimation;

    public Animation<TextureRegion> currentAnimation;

    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation
    private float elapsedTime = 0f;

    public void create() {
        atlas = new TextureAtlas(Gdx.files.internal("animations/characterAnimations.atlas"));

        runningAnimation = new Animation<TextureRegion>(0.20f, atlas.findRegions("running"));
        idleAnimation = new Animation<TextureRegion>(0.20f, atlas.findRegions("idle"));

        spriteBatch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);

        currentAnimation = idleAnimation;

    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen

        elapsedTime += Gdx.graphics.getDeltaTime();

        spriteBatch.begin();

        spriteBatch.draw(currentAnimation.getKeyFrame(elapsedTime,true),200,200,128,128);

        spriteBatch.end();
    }

    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.LEFT){

            currentAnimation = runningAnimation;
            render();

            //idleAnimation = new Animation<TextureRegion>(0.20f, atlas.findRegions("running"));

            System.out.println("asdasd");
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if( keycode ==Input.Keys.RIGHT  || keycode ==Input.Keys.LEFT ){

            currentAnimation = idleAnimation;
            render();

            //idleAnimation = new Animation<TextureRegion>(0.20f, atlas.findRegions("idle"));

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
