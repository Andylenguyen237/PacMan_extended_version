package src;

import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pills extends Items {

    private static final int PILL_SCORE = 1;


    public Pills() {
        super(null);
    }

    @Override
    public void effectOnPacActor(PacActor actor) {
        int currentNbpills = actor.getNbPills();
        actor.setNbPills(currentNbpills + PILL_SCORE);
        int score = actor.getScore();
        actor.setScore(score + PILL_SCORE);
    }
}
