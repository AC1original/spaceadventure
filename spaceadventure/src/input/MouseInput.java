package input;

import main.GamePanel;
import utility.Utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    private GamePanel gp;
    public MouseInput(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            updateGameState(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void updateGameState(int mouseX, int mouseY) {
        if (gp.getGameStateClass().getGameState() == Utils.MAIN_MENU) {
            gp.getPlayer().setHealth(Utils.MAX_PLAYER_HEALTH);
            gp.getPlayer().setAmmo(Utils.MAX_PLAYER_AMMO);
            gp.getGameStateClass().setGameState(Utils.GAME);
        } else if (gp.getGameStateClass().getGameState() == Utils.GAME_OVER) {
            gp.getGameStateClass().setGameState(Utils.MAIN_MENU);
        }
    }
}
