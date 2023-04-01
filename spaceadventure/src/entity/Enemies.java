package entity;

import main.BetterPoint;
import main.GamePanel;
import utility.Utils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Enemies {
    private int speed = 5;
    private int spawnDelay = 300;
    private boolean movement = true;
    private BufferedImage meteor;
    public static CopyOnWriteArrayList<BetterPoint> enemies;
    private Random rnd = new Random();
    private GamePanel gp;

    public Enemies(GamePanel gp) {
        this.gp = gp;
            enemies = new CopyOnWriteArrayList<BetterPoint>();

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (movement && gp.getGameStateClass().getGameState() == Utils.GAME) {
                        enemies.add(new BetterPoint(rnd.nextInt(0, 800), -100, rnd.nextInt(20, 50), rnd.nextInt(30, 50)));
                            } else {
                        for (BetterPoint p : enemies) {
                            enemies.remove(p);
                        }
                    }
                    enemies.removeIf(p -> p.y > 700);
                }
            };
            timer.schedule(task, 0, spawnDelay);
            try {
                meteor = ImageIO.read(new File("spaceadventure/src/res/level/meteor.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public void enemyTick() {
    }

    public void renderEnemies(Graphics g) {
        for (BetterPoint p : enemies) {
                if (movement) {
                    g.drawImage(meteor, p.x += 1, p.y += speed, 44, 44, null);
                } else {
                    g.drawImage(meteor, p.x, p.y, 44, 44, null);
            }
        }
    }
    public boolean isAllowedToMove() {
        return movement;
    }
    public void allowMovement(boolean movement) {
        this.movement = movement;
    }
}
