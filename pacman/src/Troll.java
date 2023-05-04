package src;

import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.util.*;

public class Troll extends Monster{
    public Troll(Game game){
        super(game, "m_troll.gif");
    }

    public String getType(){
        return "Troll";
    }

    @Override
    public void walkApproach(){

        Location pacLocation = this.getGame().pacActor.getLocation();
        double oldDirection = getDirection();

        Location.CompassDirection compassDir =
          getLocation().get4CompassDirectionTo(pacLocation);
        Location next = getLocation().getNeighbourLocation(compassDir);
        setDirection(compassDir);

        if (this.getState().equals("normal")) {
            next = randomWalk(oldDirection);
        }
        else if(this.getState().equals("multiverse")){
            next = randomWalkFurious(oldDirection);
        }
        this.getGame().getGameCallback().monsterLocationChanged(this);
        addVisitedList(next);

    }
}
