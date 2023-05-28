package com.holydemijon.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.Sprites.Animations.JohnAnimation;
import com.holydemijon.Sprites.Animations.ZombieAnimation;
import com.holydemijon.Sprites.Enemies.Enemy;
import com.holydemijon.Sprites.Enemies.Zombie;
import com.holydemijon.Sprites.Items.Door;
import com.holydemijon.Sprites.John;
import com.holydemijon.Sprites.TileObjects.*;

import static com.badlogic.gdx.Gdx.input;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() instanceof John || fixtureB.getUserData() instanceof John) {
            Fixture player = null;
            Fixture object = null;

            if (fixtureA.getUserData() instanceof John) {
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

            if (object.getUserData() != null && object.getUserData() instanceof Door) {
                ((Door) object.getUserData()).collision();
            }

            if (object.getUserData() != null && object.getUserData() instanceof Enemy) {

                if (John.johnPositionX < ((Enemy) object.getUserData()).getPositionX()) {
                    ((John) player.getUserData()).bounce(-2, 2);
                }
                else {
                    ((John) player.getUserData()).bounce(2, 2);
                }

                if (object.getUserData() instanceof Zombie) {
                    ((John) player.getUserData()).takeDamage(Zombie.ZOMBIE_DAMAGE);
                }
            }
        }

        if ((fixtureA.getUserData().equals("attack range") || fixtureB.getUserData().equals("attack range")) &&
                fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy) {

            Fixture enemy = null;

            if (fixtureA.getUserData().equals("attack range")) {enemy = fixtureB;}
            else {enemy = fixtureA;}

            if (enemy.getUserData() != null && enemy.getUserData() instanceof Enemy) {
                John.attackableEnemy = (Enemy) enemy.getUserData();
                Gdx.app.log("Begin Contact", "Enemy is in range");
            }
        }

        if (fixtureA.getUserData().equals("feet") || fixtureB.getUserData().equals("feet")) {
            Fixture object = null;

            if (fixtureA.getUserData().equals("feet")) {
                object = fixtureB;
            }
            else {
                object = fixtureA;
            }

            if (object.getUserData() instanceof InteractiveTileObject) {
                ((InteractiveTileObject) object.getUserData()).collision();

                if (object.getUserData() instanceof Ground || object.getUserData() instanceof Chest) {
                    John.isTouchingGround = true;

                    if (John.doubleJumpIsActive) {
                        John.remainingJumps = 2;
                    } else {
                        John.remainingJumps = 1;
                    }
                }
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData().equals("feet") || fixtureB.getUserData().equals("feet")) {
            Fixture object = null;

            if (fixtureA.getUserData().equals("feet")) {
                object = fixtureB;
            } else {
                object = fixtureA;
            }

            if (object.getUserData() instanceof Ground || object.getUserData() instanceof Chest) {
                John.isTouchingGround = false;
            }
        }

        if ((fixtureA.getUserData().equals("attack range") || fixtureB.getUserData().equals("attack range")) &&
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
