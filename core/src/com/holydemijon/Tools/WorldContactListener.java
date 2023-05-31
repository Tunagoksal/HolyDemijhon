package com.holydemijon.Tools;

import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.Sprites.Enemies.*;
import com.holydemijon.Sprites.Items.Border;
import com.holydemijon.Sprites.Items.Door;
import com.holydemijon.Sprites.John;
import com.holydemijon.Sprites.TileObjects.*;

import javax.imageio.metadata.IIOMetadataNode;

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
                ((InteractiveTileObject) object.getUserData()).johnCollision();
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
                else if (object.getUserData() instanceof Orc) {
                    ((John) player.getUserData()).takeDamage(Orc.ORC_DAMAGE);
                }
                else if (object.getUserData() instanceof Wizard) {
                    ((John) player.getUserData()).takeDamage(Wizard.WIZARD_DAMAGE);
                }
                else if (object.getUserData() instanceof Fireball) {
                    ((John) player.getUserData()).takeDamage(Fireball.FIREBALL_DAMAGE);
                }
            }
        }

        if (John.attackableEnemy1 == null) {
            if ((fixtureA.getUserData().equals("attack range") || fixtureB.getUserData().equals("attack range")) &&
                    fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy) {

                Fixture enemy = null;

                if (fixtureA.getUserData().equals("attack range")) {
                    enemy = fixtureB;
                } else {
                    enemy = fixtureA;
                }

                if (enemy.getUserData() != null && enemy.getUserData() instanceof Enemy) {
                    John.attackableEnemy1 = (Enemy) enemy.getUserData();
                }
            }
        }
        else {
            if ((fixtureA.getUserData().equals("attack range") || fixtureB.getUserData().equals("attack range")) &&
                    fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy) {

                Fixture enemy = null;

                if (fixtureA.getUserData().equals("attack range")) {
                    enemy = fixtureB;
                } else {
                    enemy = fixtureA;
                }

                if (enemy.getUserData() != null && enemy.getUserData() instanceof Enemy) {
                    John.attackableEnemy2 = (Enemy) enemy.getUserData();
                }
            }
        }

        if (fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy) {
            Fixture enemy = null;
            Fixture object = null;

            if (fixtureA.getUserData() instanceof Enemy) {
                enemy = fixtureA;
                object = fixtureB;
            }
            else {
                enemy = fixtureB;
                object = fixtureA;
            }

            if (object.getUserData() != null && object.getUserData() instanceof InteractiveTileObject) {
                ((InteractiveTileObject) object.getUserData()).enemyCollision((Enemy) enemy.getUserData());
            }
        }

        if (fixtureA.getUserData() instanceof Enemy && fixtureB.getUserData() instanceof Enemy) {
            Fixture enemy1 = fixtureA;
            Fixture enemy2 = fixtureB;

            ((Enemy)fixtureA.getUserData()).reverseVelocity(true,false);
            ((Enemy)fixtureB.getUserData()).reverseVelocity(true,false);
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
                ((InteractiveTileObject) object.getUserData()).johnCollision();

                if (object.getUserData() instanceof Ground || object.getUserData() instanceof Chest) {
                    John.isTouchingGround = true;

                    if (John.doubleJumpIsActive) {
                        John.remainingJumps = 2;
                    } else {
                        John.remainingJumps = 1;
                    }
                }
            }

            if (object.getUserData() instanceof Enemy) {
                John.enemyStepped = (Enemy) object.getUserData();
                John.steppedOnEnemy = true;
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

            if (object.getUserData() instanceof Enemy) {

            }
        }

        if ((fixtureA.getUserData().equals("attack range") || fixtureB.getUserData().equals("attack range")) &&
                (fixtureA.getUserData() instanceof Enemy || fixtureB.getUserData() instanceof Enemy)) {

            if (John.attackableEnemy2 != null)
                John.attackableEnemy2 = null;
            else
                John.attackableEnemy1 = null;
        }

        if ((fixtureA.getUserData() instanceof Border || fixtureB.getUserData() instanceof Border) &&
                (fixtureA.getUserData() instanceof John || fixtureB.getUserData() instanceof John)) {

            Fixture player = null;

            if (fixtureA.getUserData() instanceof John) {
                player = fixtureA;
            }
            else {
                player = fixtureB;
            }
            ((John) player.getUserData()).takeDamage(10000);
            ((John) player.getUserData()).bounce(0, 4);
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
