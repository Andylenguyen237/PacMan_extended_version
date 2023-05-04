package src;

import ch.aplu.jgamegrid.Actor;
import java.util.ArrayList;

public class Items extends Actor {

    public Items(String imagePath){
        super(imagePath);
    }

    public void effectOnMonsters(ArrayList<Monster> monsterList, String version){
    }

    public void effectOnPacActor(PacActor actor){}

}
