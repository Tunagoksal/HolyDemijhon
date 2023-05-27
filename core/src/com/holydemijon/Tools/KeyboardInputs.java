package com.holydemijon.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Levels.BaseLevel;
import com.holydemijon.Screens.LevelScreen;

public class KeyboardInputs implements InputProcessor {

    public HolyDemijhon game = new HolyDemijhon();
    private John player;

    public KeyboardInputs(John player) {
        this.player = player;
        System.out.println("in keyboardInputs");
    }

    @Override
    public boolean keyDown(int keycode) {

        /*
        if(keycode == Input.Keys.UP){
            level.getPlayer().jump(1);
        }*/
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

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

    public void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump(4f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2dbody.getLinearVelocity().x >= -John.MAX_LINEAR_VELOCITY) {
            player.move(-1);
            System.out.println("left");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2dbody.getLinearVelocity().x <= John.MAX_LINEAR_VELOCITY) {
            player.move(1);
            System.out.println("right");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            System.out.println("simple attack");
            player.simpleAttack();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            System.out.println("heavy attack");
            player.heavyAttack();
        }

    }
    public void update(float dt){
        processInput();
    }
}
