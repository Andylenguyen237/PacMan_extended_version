package src;

import ch.aplu.jgamegrid.Actor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IceCubes extends Items{

    private static final String FILEPATH = "sprites/ice.png";


    public IceCubes(){
        super(FILEPATH);
    }

    @Override
    public void effectOnMonsters(ArrayList<Monster> monsterList, String version){
        if (version.equals("multiverse")) {
            for (Monster monster : monsterList) {
                monster.stopMoving(5);
            }
        }
    }


}
