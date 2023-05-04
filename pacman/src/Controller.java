package src;

import ch.aplu.jgamegrid.Location;

import java.util.Properties;

public class Controller{

    private Game game;
    private Properties properties;

    private int seed = 30006;
    public Controller(Game game, Properties properties) {
        this.game = game;
        this.properties = properties;
    }

    private void testSetUp(){
        //Setup for auto test
        game.pacActor.setPropertyMoves(properties.getProperty("PacMan.move"));
        game.pacActor.setAuto(Boolean.parseBoolean(properties.getProperty("PacMan.isAuto")));
        loadPillAndItemsLocations();
    }
    private void seedSetUp(){
        //Setup Random seeds
        seed = Integer.parseInt(properties.getProperty("seed"));
        setupActorLocations();
        game.pacActor.setSeed(seed);

        for (Monster monster: game.getMonsterList()){
            monster.setSeed(seed);
        }

        game.addKeyRepeatListener(game.pacActor);
        game.setKeyRepeatPeriod(150);
        game.pacActor.setSlowDown(3);
        for (Monster monster: game.getMonsterList()){
            monster.setSlowDown(3);
        }
        game.getMonsterList().get(1).stopMoving(5);
    }
    private void setupActorLocations() {
        String version = this.properties.getProperty("version");
        game.setVersion(version);
        String[] trollLocations = this.properties.getProperty("Troll.location").split(",");
        String[] tx5Locations = this.properties.getProperty("TX5.location").split(",");
        String[] pacManLocations = this.properties.getProperty("PacMan.location").split(",");
        String[] orionLocations = this.properties.getProperty("Orion.location").split(",");
        String[] alienLocations = this.properties.getProperty("Alien.location").split(",");
        String[] wizLocations = this.properties.getProperty("Wizard.location").split(",");

        int trollX = Integer.parseInt(trollLocations[0]);
        int trollY = Integer.parseInt(trollLocations[1]);

        int tx5X = Integer.parseInt(tx5Locations[0]);
        int tx5Y = Integer.parseInt(tx5Locations[1]);

        int pacManX = Integer.parseInt(pacManLocations[0]);
        int pacManY = Integer.parseInt(pacManLocations[1]);

        int alienX = Integer.parseInt(alienLocations[0]);
        int alienY = Integer.parseInt(alienLocations[1]);

        int orionX = Integer.parseInt(orionLocations[0]);
        int orionY = Integer.parseInt(orionLocations[1]);

        int wizX = Integer.parseInt(wizLocations[0]);
        int wizY = Integer.parseInt(wizLocations[1]);

//    simple version
        game.addActor(game.getTroll(), new Location(trollX, trollY), Location.NORTH);
        game.addActor(game.pacActor, new Location(pacManX, pacManY));
        game.addActor(game.getTx5(), new Location(tx5X, tx5Y), Location.NORTH);
        game.getMonsterList().add(game.getTroll());
        game.getMonsterList().add(game.getTx5());
        // multiverse mode
        if (version.equals("multiverse")) {
            game.addActor(game.getAlien(), new Location(alienX, alienY), Location.NORTH);
            game.addActor(game.getOrion(), new Location(orionX, orionY), Location.NORTH);
            game.addActor(game.getWizard(), new Location(wizX, wizY), Location.NORTH);
            game.getMonsterList().add(game.getWizard());
            game.getMonsterList().add(game.getAlien());
            game.getMonsterList().add(game.getOrion());
        }
    }
    private void loadPillAndItemsLocations() {
        String pillsLocationString = properties.getProperty("Pills.location");
        if (pillsLocationString != null) {
            String[] singlePillLocationStrings = pillsLocationString.split(";");
            for (String singlePillLocationString: singlePillLocationStrings) {
                String[] locationStrings = singlePillLocationString.split(",");
                game.getPropertyPillLocations().add(new Location(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1])));
            }
        }

        String goldLocationString = properties.getProperty("Gold.location");
        if (goldLocationString != null) {
            String[] singleGoldLocationStrings = goldLocationString.split(";");
            for (String singleGoldLocationString: singleGoldLocationStrings) {
                String[] locationStrings = singleGoldLocationString.split(",");
                game.getPropertyGoldLocations().add(new Location(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1])));
            }
        }
    }

    public void setup() {
        testSetUp();
        seedSetUp();
    }
    public void startGame() {
        game.playGame();
    }
}
