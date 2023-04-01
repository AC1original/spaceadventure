package values;

import main.GamePanel;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreManager {
    private Color scoreColor = new Color(121, 0, 220, 255);
    private Font scoreFont = new Font("scoreFont", Font.BOLD, 20);
    private File file = new File("spaceadventure/src/res/main/Values.txt");
    private GamePanel gp;
    private int currentScore = 0;

    public ScoreManager(GamePanel gp) {
        this.gp = gp;
        currentScore = getScoreFromFile();
    }

    public void saveScore(int score) {
        currentScore += score;
    }

    public void saveFinalScore() {
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write(Integer.toString(currentScore));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getScoreFromFile() {
        int score = 0;
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                score = scanner.nextInt();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

    public void drawScore(Graphics g) {
        g.setColor(scoreColor);
        g.setFont(scoreFont);
        saveScore(gp.getPlayer().getScore());
        g.drawString("SCORE: " + currentScore, 550, 50);
    }
}
