package com.holydemijon.Levels;

import com.holydemijon.HolyDemijhon;

public class FirstLevel extends BaseLevel{

    HolyDemijhon game;
    public FirstLevel(HolyDemijhon game){
        super(game,"level1.tmx");
        super.getB2dwc().setColliers(2,4,6,3);
        super.getB2dwc().colliderCreation();
        System.out.println("in first level");
    }
}
