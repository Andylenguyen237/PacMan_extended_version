package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GoldPieces extends Items{

    private static final String FILEPATH = "sprites/gold.png";
    private static final int GOLD_SCORE = 5;

    public GoldPieces(){
        super(FILEPATH);
    }

    @Override
    public void effectOnMonsters(ArrayList<Monster> monsterList, String version) {
        if(version.equals("multiverse")) {
            for (Monster monster : monsterList) {
                monster.becomeFurious(version, 3);
            }
        }
    }

    public void effectOnPacActor(PacActor actor) {
        int currentNbpills = actor.getNbPills();
        actor.setNbPills(currentNbpills + 1);
        int score = actor.getScore();
        actor.setScore(score + GOLD_SCORE);
    }
}
