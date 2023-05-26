package com.holydemijon.Levels;

import com.holydemijon.HolyDemijhon;

public class SecondLevel extends BaseLevel{

    HolyDemijhon game;
    public SecondLevel(HolyDemijhon game){

        super(game,"level2.tmx");
        super.getB2dwc().setColliers(2,7,5,3);
        super.getB2dwc().colliderCreation();
        System.out.println("in second level");



    }
}
