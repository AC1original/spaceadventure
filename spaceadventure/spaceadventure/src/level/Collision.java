package level;
import entity.Enemies;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Collision {
    private GamePanel gp;
    private BufferedImage gameover;
    private int meteorX, meteorY;

    public Collision(GamePanel gp) {
        this.gp = gp;
        try {
            gameover = ImageIO.read(new File("spaceadventure/src/res/level/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void collisionTick() {
        for (Point enemies : Enemies.enemies) {
            if (enemies.x < gp.getPlayer().getX() + 45 && enemies.x + 15 > gp.getPlayer().getX() && enemies.y < gp.getPlayer().getY() +70 && enemies.y + 15 > gp.getPlayer().getY()) {
                if (!gp.getPlayer().isInvulnerable()) {
                    if (gp.getPlayer().getHealth() > 0) {
                        gp.getPlayer().setPlayerCollision(true);
                        Enemies.enemies.remove(enemies);
                        gp.getPlayer().setScore(gp.getPlayer().getScore() + 5);
                    }
                }
            }
            for (Point bullets : gp.getPlayer().getBullets()) {
                if (bullets.x < enemies.x + 30 && bullets.x > enemies.x - 20 && bullets.y < enemies.y + 30 && bullets.y > enemies.y - 30) {
                    Enemies.enemies.remove(enemies);
                    gp.getPlayer().getBullets().remove(bullets);
                    gp.getPlayer().setScore(gp.getPlayer().getScore() + 5);
                }
            }
            if (gp.getPlayer().isPlayerCollided()) {
                if (gp.getGameStatus()) {
                    gp.getPlayer().setHealth(gp.getPlayer().getHealth() - 1);
                    if (gp.getPlayer().isHealthInfinite()) {
                        gp.getPlayer().setHealth(gp.getPlayer().getHealth() + 1);
                    }
                    gp.getPlayer().setPlayerCollision(false);
                    if (gp.getPlayer().getHealth() < 1) {
                        gp.stopGame();
                    }
                }
            }
        }
    }
}
