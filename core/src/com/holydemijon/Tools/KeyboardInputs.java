package com.holydemijon.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijhon;

public class KeyboardInputs implements InputProcessor {

    public HolyDemijhon game = new HolyDemijhon();
    private John player;

    public KeyboardInputs(John player) {
        this.player = player;
    }

    public void update(float dt){
        processInput();
    }

    public void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump(4f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2dbody.getLinearVelocity().x >= -John.MAX_LINEAR_VELOCITY) {
            player.move(-1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2dbody.getLinearVelocity().x <= John.MAX_LINEAR_VELOCITY) {
            player.move(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            player.simpleAttack();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            player.heavyAttack();
        }
    }
    @Override
    public boolean keyDown(int keycode) {return false;}
    @Override
    public boolean keyUp(int keycode) {return false;}
    @Override
    public boolean keyTyped(char character) {return false;}
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
    @Override
    public boolean mouseMoved(int screenX, int screenY) {return false;}
    @Override
    public boolean scrolled(float amountX, float amountY) {return false;}
}
