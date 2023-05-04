package src;

import ch.aplu.jgamegrid.Location;

public class Counter {
    private Game game;

    public Counter(Game game){
        this.game = game;
    }

    public int countPillsAndItems() {
        int pillsAndItemsCount = 0;
        for (int y = 0; y < this.game.getNumVertCells(); y++)
        {
            for (int x = 0; x < this.game.getNumHorzCells(); x++)
            {
                Location location = new Location(x, y);
                int a = this.game.grid.getCell(location);
                if (a == 1 && this.game.getPropertyPillLocations().size() == 0) {
                    // Pill
                    pillsAndItemsCount++;
                } else if (a == 3 && this.game.getPropertyGoldLocations().size() == 0) {
                    // Gold
                    pillsAndItemsCount++;
                }
            }
        }
        if (this.game.getPropertyPillLocations().size() != 0) {
            pillsAndItemsCount += this.game.getPropertyPillLocations().size();
        }

        if (this.game.getPropertyGoldLocations().size() != 0) {
            pillsAndItemsCount += this.game.getPropertyGoldLocations().size();
        }

        return pillsAndItemsCount;
    }

}
