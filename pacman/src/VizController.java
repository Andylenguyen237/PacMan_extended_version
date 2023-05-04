package src;
import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.Location;

import java.awt.*;

public class VizController {

    private Game game;

    public VizController(Game game){
        this.game = game;
    }

    public void drawGrid(GGBackground bg)
    {
        bg.clear(Color.gray);
        bg.setPaintColor(Color.white);
        for (int y = 0; y < this.game.getNbVertCells(); y++)
        {
            for (int x = 0; x < this.game.getNbHorzCells(); x++)
            {
                bg.setPaintColor(Color.white);
                Location location = new Location(x, y);
                int a = this.game.grid.getCell(location);
                if (a > 0)
                    bg.fillCell(location, Color.lightGray);
                if (a == 1 && this.game.getPropertyPillLocations().size() == 0) { // Pill
                    putPill(bg, location); // draw only
                } else if (a == 3 && this.game.getPropertyGoldLocations().size() == 0) { // Gold
                    putGold(bg, location); // draw + setup adding actor --> separate + gold.addLocation
                } else if (a == 4) {
                    putIce(bg, location); // draw + setup adding actor
                }
            }
        }

        for (Location location : this.game.getPropertyPillLocations()) {
            putPill(bg, location);
        }

        for (Location location : this.game.getPropertyGoldLocations()) {
            putGold(bg, location);
        }
    }

    //  Visualization purpose only
    public void putPill(GGBackground bg, Location location){
        bg.fillCircle(this.game.toPoint(location), 5);
    }

    //  Visualization purpose only
    public void putGold(GGBackground bg, Location location){
        bg.setPaintColor(Color.yellow);
        bg.fillCircle(this.game.toPoint(location), 5);
        GoldPieces gold = new GoldPieces();
        this.game.addActor(gold, location);
        this.game.getGoldPieces().add(gold);
    }

    //  setup ice locations
    public void putIce(GGBackground bg, Location location){
        bg.setPaintColor(Color.blue);
        bg.fillCircle(this.game.toPoint(location), 5);
        IceCubes ice = new IceCubes();
        this.game.addActor(ice, location);
        this.game.getIceCubes().add(ice);
    }

}
