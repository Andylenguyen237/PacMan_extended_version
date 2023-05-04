package src;

import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.Location;

import java.awt.*;

public class ItemSetter{

    private Game game;
    public ItemSetter(Game game){
        this.game = game;
    }

    //  set up pill locations

    public void setupPillAndItemsLocations() {
        for (int y = 0; y < this.game.getNbVertCells(); y++)
        {
            for (int x = 0; x < this.game.getNbHorzCells(); x++)
            {
                Location location = new Location(x, y);
                int a = this.game.grid.getCell(location);
                if (a == 1 && this.game.getPropertyPillLocations().size() == 0) {
                    this.game.getPillAndItemLocations().add(location);
                }
                if (a == 3 && this.game.getPropertyGoldLocations().size() == 0) {
                    this.game.getPillAndItemLocations().add(location);
                }
                if (a == 4) {
                    this.game.getPillAndItemLocations().add(location);
                }
            }
        }

        if (this.game.getPropertyPillLocations().size() > 0){
            for (Location location : this.game.getPropertyPillLocations()) {
                this.game.getPillAndItemLocations().add(location);
            }
        }
        if (this.game.getPropertyGoldLocations().size() > 0) {
            for (Location location : this.game.getPropertyGoldLocations()) {
                this.game.getPillAndItemLocations().add(location);
            }
        }
    }


}
