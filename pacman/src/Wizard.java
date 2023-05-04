package src;

import ch.aplu.jgamegrid.Location;
import java.util.ArrayList;
import java.util.Collections;

import java.awt.*;

public class Wizard extends Monster implements MoveFullDirection{

    public Wizard(Game game){
        super(game, "m_wizard.gif");
    }

    public String getType(){
        return "Wizard";
    }

    public ArrayList<Location.CompassDirection> createCopyList(ArrayList<Location.CompassDirection> list){
        ArrayList<Location.CompassDirection> copied = new ArrayList<>();
        for (Location.CompassDirection dir: list){
            copied.add(dir);
        }
        Collections.shuffle(copied);
        return copied;
    }
    @Override
    public void walkApproach(){
        Location nextMoveLocation = getLocation();

        ArrayList<Location.CompassDirection> shuffledCopiedCompassList = createCopyList(MoveFullDirection.compassList);

        for(Location.CompassDirection dir: shuffledCopiedCompassList){
            Location next = getLocation().getNeighbourLocation(dir);
            Location besideNext = next.getNeighbourLocation(dir);


            if(this.getState().equals("normal")){
                if (!isVisited(next) && canMove(next)){
                    nextMoveLocation = next;
                }
                else{
                    if (!isVisited(besideNext) && canMove(besideNext)){
                        nextMoveLocation = besideNext;
                    }
                }
            }

            else if (this.getState().equals("multiverse")){
                if (!isVisited(besideNext) && canMove(besideNext)){
                    nextMoveLocation = besideNext;
                }

                else{
                    Location besBesideNext = besideNext.getNeighbourLocation(dir);
                    if(!isVisited(besBesideNext) && canMove(besBesideNext)){
                        nextMoveLocation = besBesideNext;
                    }
                }
            }

        }

        this.getGame().getGameCallback().monsterLocationChanged(this);
        setLocation(nextMoveLocation);
        addVisitedList(nextMoveLocation);
    }

}