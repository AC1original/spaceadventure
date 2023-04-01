package main;

import utility.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameState {
    private GamePanel gp;
    private int gameState;
    private BufferedImage main_menu, pause_menu, game_over;

    public GameState(GamePanel gp) {
        this.gp = gp;
        try {
            main_menu = ImageIO.read(new File("spaceadventure/src/res/main/main_menu.png"));
            pause_menu = ImageIO.read(new File("spaceadventure/src/res/main/pause_menu.png"));
            game_over = ImageIO.read(new File("spaceadventure/src/res/main/game_over.png"));
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("LOOOL");
        }
    }
    public void setGameState(int gameState) {
        switch (this.gameState) {
            case 0, 1, 2, 3 -> this.gameState = gameState;
        }
    }
    public int getGameState() {
        return gameState;
    }
    public void drawGameState(Graphics g) {
        if (getGameState() == Utils.MAIN_MENU) {
            gp.getPlayer().setX(350);
            g.drawImage(main_menu, 0, 0, 800, 600, null);
        } else if (getGameState() == Utils.PAUSE_MENU) {
            g.drawImage(pause_menu, 0, 0, 800, 600, null);
        } else if (getGameState() == Utils.GAME_OVER) {
            g.drawImage(game_over, 0, 0, 800, 600, null);
        }
    }
}
