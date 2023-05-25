package com.holydemijon.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.Enemies.Enemy;
import com.holydemijon.Sprites.John;
import com.holydemijon.Sprites.TileObjects.InteractiveTileObject;

import static com.badlogic.gdx.Gdx.input;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            Fixture player = null;
            Fixture object = null;

            if (fixtureA.getUserData().equals("player")) {
                player = fixtureA;
                object = fixtureB;
            }
            else {
                player = fixtureB;
                object = fixtureA;
            }

            if (object.getUserData() != null && object.getUserData() instanceof InteractiveTileObject) {
                ((InteractiveTileObject) object.getUserData()).collision();
            }
        }

        if (John.lookingRight) {
            if ((fixtureA.getUserData().equals("attack range right") || fixtureB.getUserData().equals("attack range right")) &&
                    fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy) {

                Fixture enemy = null;

                if (fixtureA.getUserData().equals("attack range right")) {
                    enemy = fixtureB;
                }
                else {
                    enemy = fixtureA;
                }

                if (enemy.getUserData() != null && enemy.getUserData() instanceof Enemy) {
                    John.attackableEnemy = (Enemy) enemy.getUserData();
                    Gdx.app.log("Begin Contact", "Enemy is in range");
                }
            }
        }
        else {
            if ((fixtureA.getUserData().equals("attack range left") || fixtureB.getUserData().equals("attack range left")) &&
                    fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy) {

                Fixture enemy = null;

                if (fixtureA.getUserData().equals("attack range left")) {
                    enemy = fixtureB;
                } else {
                    enemy = fixtureA;
                }

                if (enemy.getUserData() != null && enemy.getUserData() instanceof Enemy) {
                    John.attackableEnemy = (Enemy) enemy.getUserData();
                    Gdx.app.log("Begin Contact", "Enemy is in range");
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (((fixtureA.getUserData().equals("attack range left") || fixtureB.getUserData().equals("attack range left")) ||
                (fixtureA.getUserData().equals("attack range right") || fixtureB.getUserData().equals("attack range right"))) &&
                (fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy)) {

            John.attackableEnemy = null;
            Gdx.app.log("End Contact", "Enemy is not in range anymore");
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
