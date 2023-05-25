package com.holydemijon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holydemijon.HolyDemijhon;
import com.holydemijon.Sprites.Enemies.Zombie;
import com.holydemijon.Sprites.John;
import com.holydemijon.Scenes.HUD;
import com.holydemijon.Tools.Box2DWorldCreator;
import com.holydemijon.Tools.WorldContactListener;

public class LevelScreen implements Screen {

    public static final float FPS = 1/60f;
    public static final float GRAVITY = -10;
    private static final float JUMP_HEIGHT = 4;
    private static final float MAX_LINEAR_VELOCITY = 1;
    private static final float PLAYER_ACCELERATION = 0.1f;

    //private Player player;
    public static John player;
    private Zombie zombie;
    private HolyDemijhon game;
    private HUD hud;

    private TextureAtlas atlas;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private OrthographicCamera cam;
    private Viewport viewport;

    private World world;
    private Box2DDebugRenderer b2dbr;

    public static int health = 3;

    public LevelScreen(HolyDemijhon game){

        this.game = game;
        atlas = new TextureAtlas("animations/characterAnimations.atlas");

        cam = new OrthographicCamera();
        viewport = new FitViewport(HolyDemijhon.WIDTH / HolyDemijhon.PPM, HolyDemijhon.HEIGHT / HolyDemijhon.PPM, cam);
        //viewport.setScreenSize(360,360);
        viewport.setWorldSize(360 / HolyDemijhon.PPM,360 / HolyDemijhon.PPM);

        hud = new HUD(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Tileset.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / HolyDemijhon.PPM);
        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        world = new World(new Vector2(0, GRAVITY), true);
        b2dbr = new Box2DDebugRenderer();
        new Box2DWorldCreator(this);
        player = new John(world, this);

        zombie = new Zombie(this, 40f / HolyDemijhon.PPM, 150f / HolyDemijhon.PPM);

        world.setContactListener(new WorldContactListener());
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public World getWorld() {
        return this.world;
    }
    public TiledMap getMap() {
        return this.map;
    }
    @Override
    public void show() {

    }

    public static John getPlayer() {
        return player;
    }

    public void update(float dt){
        handleInput(dt);

        world.step(FPS, 6, 2);

        player.update(dt);

        cam.position.x = player.b2dbody.getPosition().x;
        cam.update();
        mapRenderer.setView(cam);
    }

    private void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2dbody.getLinearVelocity().x >= -MAX_LINEAR_VELOCITY){
            player.b2dbody.applyLinearImpulse(new Vector2(-PLAYER_ACCELERATION, 0), player.b2dbody.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2dbody.getLinearVelocity().x <= MAX_LINEAR_VELOCITY){
            player.b2dbody.applyLinearImpulse(new Vector2(PLAYER_ACCELERATION, 0), player.b2dbody.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.b2dbody.applyLinearImpulse(new Vector2(0, JUMP_HEIGHT), player.b2dbody.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            player.simpleAttack();
        }
    }



    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        mapRenderer.setView(cam);
        mapRenderer.render();

        b2dbr.render(world, cam.combined);

        // endgame geçiş bunun da yeri düzenlenebilir ileride
        if(player.getHealth() == 0){
            game.setScreens(HolyDemijhon.END_GAME_SCREEN);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            game.setScreens(HolyDemijhon.MAIN_MENU_SCREEN);
        }


        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        player.getJohnAnimation().draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        b2dbr.dispose();
        hud.dispose();
    }
}
