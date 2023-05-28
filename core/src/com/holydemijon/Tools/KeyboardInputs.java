package com.holydemijon.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.John;
import com.holydemijon.HolyDemijohn;

public class KeyboardInputs implements InputProcessor {

    public HolyDemijohn game = new HolyDemijohn();
    private John player;

    public KeyboardInputs(John player) {
        this.player = player;
    }

    public void update(float dt){ processInput(); }

    public void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && John.remainingJumps > 0) {
            if (!John.isTouchingGround) {
                JohnAnimation.performDash = true;
                player.jump(John.JUMP_HEIGHT / 1.5f);
                John.remainingJumps = 0;
            }
            else {
                player.jump(John.JUMP_HEIGHT);
                John.remainingJumps -= 1;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2dbody.getLinearVelocity().x >= -John.MAX_LINEAR_VELOCITY) {
            player.move(-1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2dbody.getLinearVelocity().x <= John.MAX_LINEAR_VELOCITY) {
            player.move(1);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            JohnAnimation.performSimpleAttack = true;
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    player.simpleAttack();
                }
            },0.25f);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && John.heavyAttackIsActive) {
            JohnAnimation.performHeavyAttack = true;
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    player.heavyAttack();
                }
            },0.25f);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && John.dashIsActive) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player.johnDash(1);
                John.dashUsed();
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player.johnDash(-1);
                John.dashUsed();
            }
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
