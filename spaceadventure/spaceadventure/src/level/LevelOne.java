package level;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelOne {
    private int x = 0, y = 0;
    private BufferedImage starsky;
    private GamePanel gp;

    public LevelOne(GamePanel gp) {
        this.gp = gp;
        try {
            starsky = ImageIO.read(new File("spaceadventure/src/res/level/starsky.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderLevelOne(Graphics g) {
            if (getY() < 600) {
                g.drawImage(starsky, x, y - 600, null);
                g.drawImage(starsky, x, y, 800, 600, null);
            } else {
                setY(0);
            }
        }
    public void moveBackground() {
        if (gp.getGameStatus()) {
            setY(getY() + gp.getPlayer().getSpeed());
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
