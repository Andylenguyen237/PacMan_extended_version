// PacMan.java
// Simple PacMan implementation
package src;

import ch.aplu.jgamegrid.*;
import src.utility.GameCallback;

import java.awt.*;
import java.util.ArrayList;

public class Game extends GameGrid
{
  private final static int nbHorzCells = 20;
  private final static int nbVertCells = 11;
  protected PacManGameGrid grid = new PacManGameGrid(nbHorzCells, nbVertCells);
  protected PacActor pacActor = new PacActor(this);
  private Monster troll = new Troll(this);
  private Monster tx5 = new Tx5(this);
  private Monster alien = new Alien(this);

  private Monster orion = new Orion(this);

  private Monster wizard = new Wizard(this);

  private ArrayList<Monster> monsterList = new ArrayList<>();

  private ArrayList<Location> pillAndItemLocations = new ArrayList<>();
  private ArrayList<IceCubes> iceCubes = new ArrayList<>();
  private ArrayList<GoldPieces> goldPieces = new ArrayList<>();

  private ArrayList<Pills> pills = new ArrayList<>();
  private GameCallback gameCallback;
  private ArrayList<Location> propertyPillLocations = new ArrayList<>();
  private ArrayList<Location> propertyGoldLocations = new ArrayList<>();
  private String version;
  private Counter counter;
  private ItemSetter itemSetter;
  private VizController vizController;

  public Game(GameCallback gameCallback) {
    //Setup game
    super(nbHorzCells, nbVertCells, 20, false);
    this.gameCallback = gameCallback;
    counter = new Counter(this);
    itemSetter = new ItemSetter(this);
    vizController = new VizController(this);
  }


  //main function to run
  public void playGame(){
    setSimulationPeriod(100);
    setTitle("[PacMan in the Multiverse]");
    GGBackground bg = getBg();
    vizController.drawGrid(bg);

    //Run the game
    doRun();
    show();

    // Loop to look for collision in the application thread
    // This makes it improbable that we miss a hit
    boolean[] endGameConditions = new boolean[2];
    CheckEndGame(endGameConditions);

    //End game reached, either win or loss
    endGameRemovePrint(endGameConditions, bg);
    doPause();
  }



//  Handle ending, either win or game over; clear characters
  private void endGameRemovePrint(boolean[] endGameConditions, GGBackground bg){
    Location loc = pacActor.getLocation();

    for (Monster monster: monsterList){
      monster.setStopMoving(true);
    }
    pacActor.removeSelf();
    boolean hasPacmanBeenHit = endGameConditions[0];
    boolean hasPacmanEatAllPills = endGameConditions[1];

    String title = "";
    if (hasPacmanBeenHit) {
      bg.setPaintColor(Color.red);
      title = "GAME OVER";
      addActor(new Actor("sprites/explosion3.gif"), loc);
    } else if (hasPacmanEatAllPills) {
      bg.setPaintColor(Color.yellow);
      title = "YOU WIN";
    }
    setTitle(title);
    gameCallback.endOfGame(title);
  }


//  check end game conditions, win or lose
  private void CheckEndGame(boolean[] endGameConditions){
    boolean hasPacmanBeenHit;
    boolean hasPacmanEatAllPills;

    itemSetter.setupPillAndItemsLocations();
    int maxPillsAndItems = counter.countPillsAndItems();

    do {
      hasPacmanBeenHit = pacActor.hitEnemies();
      hasPacmanEatAllPills = pacActor.getNbPills() >= maxPillsAndItems;
      endGameConditions[0] = hasPacmanBeenHit;
      endGameConditions[1] = hasPacmanEatAllPills;
      delay(10);
    } while(!hasPacmanBeenHit && !hasPacmanEatAllPills);
    delay(120);

}

//  removeItem on the Game
  public void removeItem(String type,Location location){
    if(type.equals("gold")){
      for (GoldPieces item : this.goldPieces){
        if (location.getX() == item.getLocation().getX() && location.getY() == item.getLocation().getY()) {
          item.hide();
        }
      }
    }else if(type.equals("ice")){
      for (IceCubes item : this.iceCubes){
        if (location.getX() == item.getLocation().getX() && location.getY() == item.getLocation().getY()) {
          item.hide();
        }
      }
    }
  }

//  Getter and setter

  public int getNumHorzCells(){
    return nbHorzCells;
  }
  public int getNumVertCells(){
    return nbVertCells;
  }

  public ArrayList<GoldPieces> getGoldPieces() {
    return goldPieces;
  }

  public ArrayList<IceCubes> getIceCubes() {
    return iceCubes;
  }

  public ArrayList<Monster> getMonsterList() {
    return monsterList;
  }

  public String getGameVersion() {
    return version;
  }

  public Monster getTroll() {
    return troll;
  }

  public Monster getTx5() {
    return tx5;
  }

  public Monster getAlien() {
    return alien;
  }

  public Monster getOrion() {
    return orion;
  }

  public Monster getWizard() {
    return wizard;
  }

  public ArrayList<Location> getPropertyPillLocations() {
    return propertyPillLocations;
  }

  public ArrayList<Location> getPropertyGoldLocations() {
    return propertyGoldLocations;
  }

  public GameCallback getGameCallback() {
    return gameCallback;
  }
  public void setVersion(String version) {this.version = version;}

  public ArrayList<Location> getPillAndItemLocations() {
    return pillAndItemLocations;
  }

}
