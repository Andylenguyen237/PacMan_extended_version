package src;

import ch.aplu.jgamegrid.Location;

public class Tx5 extends Monster{
    public Tx5(Game game){
        super(game, "m_tx5.gif");
    }

    public String getType(){
        return "Tx5";
    }

    @Override
    public void walkApproach(){
    Location pacLocation = this.getGame().pacActor.getLocation();
    double oldDirection = getDirection();
    // TX5: Determine direction to pacActor and try to move in that direction. Otherwise, random walk.
    Location.CompassDirection compassDir =
      getLocation().get4CompassDirectionTo(pacLocation);
    Location next = getLocation().getNeighbourLocation(compassDir);
    Location besideNext = next.getNeighbourLocation(compassDir);
    setDirection(compassDir);

    if (this.getState().equals("normal")) {
        if (!isVisited(next) && canMove(next)) {
            setLocation(next);
        } else {
            next = randomWalk(oldDirection);
        }
    }
    else if (this.getState().equals("multiverse")){
        if (!isVisited(next) && canMove(next) && canMove(besideNext) &&(!isVisited(besideNext))) {
            setLocation(besideNext);
        } else {
            next = randomWalkFurious(oldDirection);
        }
    }
    this.getGame().getGameCallback().monsterLocationChanged(this);
    addVisitedList(next);
    }
}
