package input;

import entity.Player;
import main.GamePanel;
import utility.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private GamePanel gp;
    public KeyboardInput(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
                gp.getPlayer().setLEFT(true);
        }else if (e.getKeyChar() == 'd') {
                gp.getPlayer().setRIGHT(true);
        } else if (e.getKeyChar() == ' ') {
                if (!gp.getPlayer().getShoot()) {
                    gp.getPlayer().setSHOOT(true);
                    if (gp.getPlayer().getAmmo() > 0) {
                        Player.projectiles.add(new Point(gp.getPlayer().getX() + 37, gp.getPlayer().getY() + 15));
                        if (!gp.getPlayer().ammoIsInfinite()) {
                            gp.getPlayer().setAmmo(gp.getPlayer().getAmmo() - 1);
                    }
                }
            }
        } else if (e.getKeyChar() == 'w') {
            if (gp.getPlayer().freeMovementIsEnabled()) {
                    gp.getPlayer().setUP(true);
            }
        } else if (e.getKeyChar() == 's') {
            if (gp.getPlayer().freeMovementIsEnabled()) {
                    gp.getPlayer().setDOWN(true);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_1) {
            gp.getPlayer().setInfiniteAmmo(!gp.getPlayer().ammoIsInfinite());
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_2) {
            gp.getPlayer().setHealth(Utils.MAX_PLAYER_HEALTH);
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_3) {
            gp.getPlayer().setAmmo(Utils.MAX_PLAYER_AMMO);
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_4) {
            gp.getPlayer().setInfiniteHealth(!gp.getPlayer().isHealthInfinite());
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_5) {
            gp.getPlayer().setInvulnerable(!gp.getPlayer().isInvulnerable());
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_6) {
            gp.getEnemies().allowMovement(!gp.getEnemies().isAllowedToMove());
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_7) {
            gp.getPlayer().allowFreeMovement(!gp.getPlayer().freeMovementIsEnabled());
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_8) {
            gp.getGameStateClass().setGameState(Utils.MAIN_MENU);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (gp.getGameStateClass().getGameState() != Utils.PAUSE_MENU) {
                gp.getGameStateClass().setGameState(Utils.PAUSE_MENU);
            }else {
                gp.getGameStateClass().setGameState(Utils.GAME);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gp.getPlayer().setLEFT(false);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            gp.getPlayer().setRIGHT(false);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gp.getPlayer().setSHOOT(false);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            gp.getPlayer().setUP(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            gp.getPlayer().setDOWN(false);
        }
    }
}
