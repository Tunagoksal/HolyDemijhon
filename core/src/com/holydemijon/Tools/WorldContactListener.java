package com.holydemijon.Tools;

import com.badlogic.gdx.physics.box2d.*;
import com.holydemijon.Entities.TileObjects.InteractiveTileObject;

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
            } else {
                player = fixtureB;
                object = fixtureA;
            }

            if (object.getUserData() != null && object.getUserData() instanceof InteractiveTileObject) {
                ((InteractiveTileObject) object.getUserData()).collision();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
