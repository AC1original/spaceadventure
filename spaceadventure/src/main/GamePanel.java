package main;
import entity.Enemies;
import entity.Player;
import input.KeyboardInput;
import input.MouseInput;
import level.Collision;
import level.LevelOne;
import utility.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private int TARGET_FPS = 60;
    private GameState gameState = new GameState(this);
    private JFrame jFrame = new JFrame();
    private Player player = new Player(this);
    private Enemies enemies = new Enemies(this);
    private LevelOne levelOne = new LevelOne(this);
    private Collision collision = new Collision(this);
    private MouseInput mouseInput = new MouseInput(this);
    //private Loot loot = new Loot(this);
    private KeyboardInput keyboardInput = new KeyboardInput(this);
    private BufferedImage Ammo0, Ammo1, Ammo2, heart, emptyheartcontainer;

    public GamePanel() {
        new Utils();
        try {
            Ammo2 = ImageIO.read(new File("spaceadventure/src/res/player/Ammo2.png"));
            Ammo1 = ImageIO.read(new File("spaceadventure/src/res/player/Ammo1.png"));
            Ammo0 = ImageIO.read(new File("spaceadventure/src/res/player/Ammo0.png"));
            heart = ImageIO.read(new File("spaceadventure/src/res/player/heart.png"));
            emptyheartcontainer = ImageIO.read(new File("spaceadventure/src/res/player/empty_heartcontainer.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Space Adventure");
        jFrame.add(this);
        jFrame.addKeyListener(keyboardInput);
        jFrame.addMouseListener(mouseInput);
        jFrame.setVisible(true);
        jFrame.requestFocus();


            while (true) {
                long startTime = System.nanoTime();
                updateGameState();
                getPlayer().playerTick();
                long endTime = System.nanoTime();
                long frameTime = endTime - startTime;
                long targetFrameTime = 1000000000 / TARGET_FPS;
                long pauseTime = targetFrameTime - frameTime;
                if (pauseTime > 0) {
                    try {
                        Thread.sleep(pauseTime / 1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void updateGameState() {
            repaint();
            if (gameState.getGameState() == Utils.GAME) {
                collision.collisionTick();
                levelOne.moveBackground();
                player.playerMovement();
                enemies.enemyTick();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            if (gameState.getGameState() != Utils.GAME) {
                gameState.drawGameState(g);
            } else if (gameState.getGameState() == Utils.GAME) {
                levelOne.renderLevelOne(g);
                player.renderPlayer(g);
                enemies.renderEnemies(g);
                g.setColor(Color.RED);
                g.setFont(new Font("Font", Font.BOLD, 20));
                g.drawImage(Ammo0, 10, 60, 40, 28, null);
                drawAmmo(g);
                drawHealth(g);
            }
        }

    public Player getPlayer() {
            return player;
        }
        public LevelOne getLevel() {
            return levelOne;
        }
        public Enemies getEnemies() {
            return enemies;
        }

    public void drawHealth(Graphics g) {
        switch (getPlayer().getHealth()) {
            case 2 -> {
                g.drawImage(heart, 10, 20, 30, 28, null);
                g.drawImage(heart, 30, 20, 30, 28, null);
                g.drawImage(emptyheartcontainer, 50, 20, 30, 28, null);
            }
            case 1 -> {
                g.drawImage(heart, 10, 20, 30, 28, null);
                g.drawImage(emptyheartcontainer, 30, 20, 30, 28, null);
                g.drawImage(emptyheartcontainer, 50, 20, 30, 28, null);
            }
            case 0 -> {
                g.drawImage(emptyheartcontainer, 10, 20, 30, 28, null);
                g.drawImage(emptyheartcontainer, 30, 20, 30, 28, null);
                g.drawImage(emptyheartcontainer, 50, 20, 30, 28, null);
            }
            default -> {
                g.drawImage(heart, 10, 20, 30, 28, null);
                g.drawImage(heart, 30, 20, 30, 28, null);
                g.drawImage(heart, 50, 20, 30, 28, null);
            }
        }
    }
    public void drawAmmo(Graphics g) {
        switch (getPlayer().getAmmo()) {
            case 6 -> {
                g.drawImage(Ammo2, 60, 60, 12, 32, null);
                g.drawImage(Ammo2, 70, 60, 12, 32, null);
                g.drawImage(Ammo2, 80, 60, 12, 32, null);
                g.drawImage(Ammo2, 90, 60, 12, 32, null);
                g.drawImage(Ammo2, 100, 60, 12, 32, null);
                g.drawImage(Ammo2, 110, 60, 12, 32, null);
                g.drawImage(Ammo1, 120, 60, 12, 32, null);
            }
            case 5 -> {
                g.drawImage(Ammo2, 60, 60, 12, 32, null);
                g.drawImage(Ammo2, 70, 60, 12, 32, null);
                g.drawImage(Ammo2, 80, 60, 12, 32, null);
                g.drawImage(Ammo2, 90, 60, 12, 32, null);
                g.drawImage(Ammo2, 100, 60, 12, 32, null);
                g.drawImage(Ammo1, 110, 60, 12, 32, null);
                g.drawImage(Ammo1, 120, 60, 12, 32, null);
            }
            case 4 -> {
                g.drawImage(Ammo2, 60, 60, 12, 32, null);
                g.drawImage(Ammo2, 70, 60, 12, 32, null);
                g.drawImage(Ammo2, 80, 60, 12, 32, null);
                g.drawImage(Ammo2, 90, 60, 12, 32, null);
                g.drawImage(Ammo1, 100, 60, 12, 32, null);
                g.drawImage(Ammo1, 110, 60, 12, 32, null);
                g.drawImage(Ammo1, 120, 60, 12, 32, null);
            }
            case 3 -> {
                g.drawImage(Ammo2, 60, 60, 12, 32, null);
                g.drawImage(Ammo2, 70, 60, 12, 32, null);
                g.drawImage(Ammo2, 80, 60, 12, 32, null);
                g.drawImage(Ammo1, 90, 60, 12, 32, null);
                g.drawImage(Ammo1, 100, 60, 12, 32, null);
                g.drawImage(Ammo1, 110, 60, 12, 32, null);
                g.drawImage(Ammo1, 120, 60, 12, 32, null);
            }
            case 2 -> {
                g.drawImage(Ammo2, 60, 60, 12, 32, null);
                g.drawImage(Ammo2, 70, 60, 12, 32, null);
                g.drawImage(Ammo1, 80, 60, 12, 32, null);
                g.drawImage(Ammo1, 90, 60, 12, 32, null);
                g.drawImage(Ammo1, 100, 60, 12, 32, null);
                g.drawImage(Ammo1, 110, 60, 12, 32, null);
                g.drawImage(Ammo1, 120, 60, 12, 32, null);
            }
            case 1 -> {
                g.drawImage(Ammo2, 60, 60, 12, 32, null);
                g.drawImage(Ammo1, 70, 60, 12, 32, null);
                g.drawImage(Ammo1, 80, 60, 12, 32, null);
                g.drawImage(Ammo1, 90, 60, 12, 32, null);
                g.drawImage(Ammo1, 100, 60, 12, 32, null);
                g.drawImage(Ammo1, 110, 60, 12, 32, null);
                g.drawImage(Ammo1, 120, 60, 12, 32, null);
            }
            case 0 -> {
                g.drawImage(Ammo1, 60, 60, 12, 32, null);
                g.drawImage(Ammo1, 70, 60, 12, 32, null);
                g.drawImage(Ammo1, 80, 60, 12, 32, null);
                g.drawImage(Ammo1, 90, 60, 12, 32, null);
                g.drawImage(Ammo1, 100, 60, 12, 32, null);
                g.drawImage(Ammo1, 110, 60, 12, 32, null);
                g.drawImage(Ammo1, 120, 60, 12, 32, null);
            }
            default -> {
                g.drawImage(Ammo2, 60, 60, 12, 32, null);
                g.drawImage(Ammo2, 70, 60, 12, 32, null);
                g.drawImage(Ammo2, 80, 60, 12, 32, null);
                g.drawImage(Ammo2, 90, 60, 12, 32, null);
                g.drawImage(Ammo2, 100, 60, 12, 32, null);
                g.drawImage(Ammo2, 110, 60, 12, 32, null);
                g.drawImage(Ammo2, 120, 60, 12, 32, null);
            }
        }
    }
    public GameState getGameStateClass() {
        return gameState;
    }
}
