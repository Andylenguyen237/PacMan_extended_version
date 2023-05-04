package src;

import ch.aplu.jgamegrid.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Orion extends Monster{

        private ArrayList<GoldPieces> visitedGoldLst;
        public Orion(Game game){
            super(game, "m_orion.gif");
            visitedGoldLst = new ArrayList<>();
        }
        public String getType(){
            return "Orion";
        }
        @Override
        public void walkApproach(){
            GoldPieces bestGold = bestGoldPiece();
            double oldDirection = getDirection();

            if (bestGold == null){
                this.restartCycle();
                bestGold = bestGoldPiece();
            }

            if (this.getLocation().equals(bestGold.getLocation())){
                visitedGoldLst.add(bestGold);
            }
//            assumption stuck in the loop
            Location.CompassDirection compassDir =
                    getLocation().getCompassDirectionTo(bestGold.getLocation());
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

        public GoldPieces bestGoldPiece(){
            ArrayList<GoldPieces> list = this.getGame().getGoldPieces();
            Collections.sort(list, new Comparator<GoldPieces>() {
                @Override
                public int compare(GoldPieces o1, GoldPieces o2) {
                    // Sort in descending order based on visibility attribute
                    return Integer.compare(o2.getIdVisible(), o1.getIdVisible());
                }
            });

            for (GoldPieces piece: list){
                if (!visitedGoldLst.contains(piece)){
                    return piece;
                }
            }
            return null;
        }

        public void restartCycle(){
            this.visitedGoldLst.clear();
        }
}

