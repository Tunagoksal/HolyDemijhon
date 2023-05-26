package com.holydemijon.Entities.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.holydemijon.Levels.BaseLevel;
import com.holydemijon.Screens.LevelScreen;


public class Door extends InteractiveTileObject {

    Timer timer;
    // bu kapının bir obje gibi fiizksel bir colliderının olmasından ziyade sensör  niteliği görmesi
    // ve alanını içine girildikten bir süre sonra veya bir tuşa basılması ile yeni bölüme geçmesini asıl planlıyorum
    // ama nasıl oalcağı konusnda emin değilim halilin ksıımlarını da bozmka istemediğim için sonraya erteledim
    // şimdilik kapıya değdikten 1 saniye sonra yeni bölümü yüklüyor


    public Door(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);
        fixture.setUserData(this);
        fixtureDef.isSensor = true;
        timer = new Timer();
    }

    @Override
    public void collision() {
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                BaseLevel.isDoorOpened = true;
            }
        },1);

    }
}
