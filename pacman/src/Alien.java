package src;

import ch.aplu.jgamegrid.Location;

public class Alien extends Monster implements MoveFullDirection{

    private final double LARGE_NUMBER = 10000000;

    public Alien(Game game){
        super(game, "m_alien.gif");
        this.setListLength(4);
    }

    public String getType(){
        return "Alien";
    }

    @Override
    public void walkApproach(){
        Location pacLocation = this.getGame().pacActor.getLocation();
        double minDistance = LARGE_NUMBER;
        Location nextMoveLocation = getLocation();


        for(Location.CompassDirection dir: MoveFullDirection.compassList) {
            Location next = getLocation().getNeighbourLocation(dir);
            Location besideNext = next.getNeighbourLocation(dir);

            if (this.getState().equals("normal")) {
                double distance = next.getDistanceTo(pacLocation);
//                assumption on isVisited: if we keep then in the screenshot will return error;
//                if remove will always be stuck in the walls
                if ( canMove(next) && distance < minDistance) {
                    minDistance = distance;
                    nextMoveLocation = next;
                } else if ( canMove(next) && minDistance == LARGE_NUMBER) {
                    minDistance = distance;
                    nextMoveLocation = next;
                }
            }

            else if (this.getState().equals("multiverse")) {
                double distance = besideNext.getDistanceTo(pacLocation);
//                assumption on isVisited: if we keep then in the screenshot will return error;
//                if remove will always stuck in the walls
                if (canMove(besideNext) && canMove(next) && distance < minDistance) {
                    minDistance = distance;
                    nextMoveLocation = besideNext;
                } else if (canMove(besideNext) && canMove(next)&& minDistance == LARGE_NUMBER) {
                    minDistance = distance;
                    nextMoveLocation = besideNext;
                }
            }
        }

        this.getGame().getGameCallback().monsterLocationChanged(this);
        setLocation(nextMoveLocation);
        addVisitedList(nextMoveLocation);

    }



}
