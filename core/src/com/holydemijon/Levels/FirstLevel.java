package com.holydemijon.Levels;

import com.holydemijon.HolyDemijhon;

public class FirstLevel extends BaseLevel{

    HolyDemijhon game;
    public FirstLevel(HolyDemijhon game){
        super(game,"Tileset.tmx");
        System.out.println("in first level");
    }
}
