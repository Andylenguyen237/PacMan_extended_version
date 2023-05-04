package src;

import ch.aplu.jgamegrid.Location;

import java.util.ArrayList;
import java.util.Arrays;

// this interface is used for monsters who are capable of moving in all 8 directions
// Monsters which can use this are those who need to iteratively go through the 8 directions in their walk approach
// Monsters are: Alien, Wizard, Orion (optionally - because our approach uses another approach but it can use this),
// and other potential monsters in the future (extendable)
public interface MoveFullDirection {
     public ArrayList<Location.CompassDirection> compassList =
            new ArrayList<>(Arrays.asList(Location.EAST,
                    Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST, Location.WEST, Location.NORTHWEST, Location.NORTH,
                    Location.NORTHEAST));
}
