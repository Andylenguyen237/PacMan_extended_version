// Monster.java
// Used for PacMan
package src;

import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.util.*;
import ch.aplu.jgamegrid.*;


public class Monster extends Actor {
  private Game game;
  private ArrayList<Location> visitedList = new ArrayList<Location>();
  private  int listLength = 10;
  private boolean stopMoving = false;
  private int seed = 0;
  private Random randomiser = new Random(seed);
  private String state;

  public Monster(Game game, String string) {
    super("sprites/" + string);
    this.game = game;
    this.state = "normal";
  }

  public String getType() {return null;}

  public void stopMoving(int seconds) {
    this.stopMoving = true;
    Timer timer = new Timer(); // Instantiate Timer Object
    int SECOND_TO_MILLISECONDS = 1000;
    final Monster monster = this;
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        monster.stopMoving = false;
      }
    }, seconds * SECOND_TO_MILLISECONDS);
  }

  public void becomeFurious(String state, int seconds){
    this.setState(state);
    Timer timer = new Timer();
    int SECOND_TO_MILLISECONDS = 1000;
    final Monster monster = this;
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        monster.state = "normal";
      }
    }, seconds * SECOND_TO_MILLISECONDS);
  }

  public void setSeed(int seed) {
    this.seed = seed;
    randomiser.setSeed(seed);
  }

  public void setStopMoving(boolean stopMoving) {
    this.stopMoving = stopMoving;
  }

  public void act()
  {
    if (stopMoving) {
      return;
    }
    walkApproach();
    if (getDirection() > 150 && getDirection() < 210)
      setHorzMirror(false);
    else
      setHorzMirror(true);
  }

  public void walkApproach() {
  }

  public Location obtainBesideNextMoveLocation(){
    Location answer = null;
    if (this.state.equals("normal")){
      answer = getNextMoveLocation();
    }
    else if (this.state.equals("multiverse")){
      Location next = getNextMoveLocation();
      if (this.getNbHorzCells() * this.getNbVertCells() <= 2500){
        answer = next.getNeighbourLocation(this.getDirection());
      }
      else{
        answer = next.getAdjacentLocation(this.getDirection());
      }
    }
    return answer;
  }

  public Location randomWalk(double oldDirection){
    // Random walk
    int sign = randomiser.nextDouble() < 0.5 ? 1 : -1;
    setDirection(oldDirection);
    turn(sign * 90);  // Try to turn left/right
    Location next = getNextMoveLocation();

    if (canMove(next))
    {
      setLocation(next);
    }
    else
    {
      setDirection(oldDirection);
      next = getNextMoveLocation();
      if (canMove(next)) // Try to move forward
      {
        setLocation(next);
      }
      else
      {
        setDirection(oldDirection);
        turn(-sign * 90);  // Try to turn right/left
        next = getNextMoveLocation();
        if (canMove(next))
        {
          setLocation(next);
        }
        else
        {
          setDirection(oldDirection);
          turn(180);  // Turn backward
          next = getNextMoveLocation();
          setLocation(next);
        }
      }
    }

    return next;
  }

  public void setListLength(int listLength) {
    this.listLength = listLength;
  }

  public Location randomWalkFurious(double oldDirection){
    // Random walk
    int sign = randomiser.nextDouble() < 0.5 ? 1 : -1;
    setDirection(oldDirection);
    turn(sign * 90);  // Try to turn left/right
    Location next = getNextMoveLocation();
    Location besideNext = obtainBesideNextMoveLocation();

    if (canMove(next) && canMove(besideNext))
    {
      setLocation(besideNext);
    }
    else
    {
      setDirection(oldDirection);
      next = getNextMoveLocation();
      besideNext = obtainBesideNextMoveLocation();
      if (canMove(next) && canMove(besideNext)) // Try to move forward
      {
        setLocation(besideNext);
      }
      else
      {
        setDirection(oldDirection);
        turn(-sign * 90);  // Try to turn right/left
        next = getNextMoveLocation();
        besideNext = obtainBesideNextMoveLocation();
        if (canMove(next) && canMove(besideNext))
        {
          setLocation(besideNext);
        }
        else
        {

          setDirection(oldDirection);
          turn(180);  // Turn backward
          next = getNextMoveLocation();
          besideNext = obtainBesideNextMoveLocation();
          if (canMove(next) && canMove(besideNext)){
            setLocation(besideNext);

          }
        }
      }
    }

    return besideNext;
  }

  public Game getGame() {
    return game;
  }

  protected void addVisitedList(Location location)
  {
    visitedList.add(location);
    if (visitedList.size() == listLength)
      visitedList.remove(0);
  }

  protected boolean isVisited(Location location)
  {
    for (Location loc : visitedList)
      if (loc.equals(location))
        return true;
    return false;
  }

  protected boolean canMove(Location location)
  {
    Color c = getBackground().getColor(location);
    if (c.equals(Color.gray) || location.getX() >= game.getNumHorzCells()
          || location.getX() < 0 || location.getY() >= game.getNumVertCells() || location.getY() < 0)
      return false;
    else
      return true;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public boolean getStopMoving(){
    return this.stopMoving;
  }
}


