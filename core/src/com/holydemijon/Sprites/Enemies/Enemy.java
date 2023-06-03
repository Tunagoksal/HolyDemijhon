package com.holydemijon.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.holydemijon.Screens.Levels.FirstLevel;
import com.holydemijon.Screens.Levels.Level;
import com.holydemijon.Sprites.John;

public abstract class Enemy extends Sprite {

    public static final int ENEMY_PERFORM_TAKING_DAMAGE = 1;
    public static final int ENEMY_PERFORM_DEATH = 2;

    protected Level level;
    protected World world;
    public Body b2dbody;
    public Vector2 velocity;

    public boolean setToDestroy;
    public boolean destroyed;

    public boolean enemyTouchingGround;

    public int health;

    public Enemy(Level level, float x, float y) {
        this.world = level.getWorld();
        this.level = level;
        setPosition(x, y);
        defEnemy();
        enemyTouchingGround=true;
        setToDestroy = false;
        destroyed = false;
        velocity= new Vector2(-0.5f,0);
    }

    public abstract void update(float dt);

    protected abstract void defEnemy();
    public int getHealth() {
        return health;
    }
    public void reverseVelocity(boolean x, boolean y){
        if(x){
            velocity.x=-velocity.x;
        }
        if(y){
            velocity.y=-velocity.y;

        }
    }

    public float getPositionX() { return b2dbody.getPosition().x; }
    public void receiveDamage(int damage) {
        health -= damage;

        reverseVelocity(true,false);
        if (John.johnPositionX < b2dbody.getPosition().x) {
            b2dbody.applyLinearImpulse(new Vector2(1f, 2), this.b2dbody.getWorldCenter(), true);
        }
        else {
            b2dbody.applyLinearImpulse(new Vector2(-1f, 2), this.b2dbody.getWorldCenter(), true);
        }
    }
}
