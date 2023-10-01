package entity;

import jdk.jshell.Snippet;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {
    private GamePanel gp;
    private int score = 0;
    private Rectangle hitbox;
    private boolean playerCollision = false;
    private BufferedImage player, bullet;
    private int speed = 3, projectileSpeed = 12, ammo = 7, health = 3;
    private int x = 350, y = 440;
    private boolean STAND = false, UP = false, DOWN = false, LEFT = false, RIGHT = false, SHOOT = false;
    private boolean invulnerable = false, infiniteAmmo = false, infiniteHealth = false, freeMovement = false;
    public static CopyOnWriteArrayList<Point> projectiles;
    Timer timer = new Timer();
    TimerTask task;

    public Player(GamePanel gp) {
        hitbox = new Rectangle(getX(), getY(), 80, 100);
        this.gp = gp;
        projectiles = new CopyOnWriteArrayList<Point>();
        try {
            player = ImageIO.read(new File("spaceadventure/src/res/player/rocket1.png"));
            bullet = ImageIO.read(new File("spaceadventure/src/res/projectile/projectile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        task = new TimerTask() {
            @Override
            public void run() {
                if (ammo < 7) {
                    if (gp.getGameStatus())
                        ammo++;
                }
            }
        };
        timer.schedule(task, 0, 2000);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public void renderPlayer(Graphics g) {
        g.drawImage(player, x, y, 80, 100, null);
        spawnProjectile(g);

    }

    public void spawnProjectile(Graphics g) {
        for (Point p : projectiles) {
            g.drawImage(bullet, p.x, p.y -= projectileSpeed, 40, 50, null);
        }
    }

    public void playerMovement() {
        if (LEFT) {
            if (getX() >= -35) {
                setX(getX() - 5);
            }
        } else if (RIGHT) {
            if (getX() <= 730) {
                setX(getX() + 5);
            }
        } else if (UP) {
            if (getY() > 0) {
                setY(getY() - 5);
            }
        } else if (DOWN) {
            if (getY() < 600) {
                setY(getY() + 7);
            }
        }
    }

    public void setLEFT(boolean LEFT) {
        this.LEFT = LEFT;
    }

    public void setRIGHT(boolean RIGHT) {
        this.RIGHT = RIGHT;
    }

    public void setUP(boolean UP) {
        this.UP = UP;
    }

    public void setDOWN(boolean DOWN) {
        this.DOWN = DOWN;
    }

    public void setSHOOT(boolean SHOOT) {
        this.SHOOT = SHOOT;
    }

    public boolean getShoot() {
        return SHOOT;
    }

    public void playerTick() {
        projectiles.removeIf(p -> p.y < -200);
    }

    public CopyOnWriteArrayList<Point> getBullets() {
        return projectiles;
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setInfiniteAmmo(boolean infiniteAmmo) {
        this.infiniteAmmo = infiniteAmmo;
    }

    public boolean ammoIsInfinite() {
        return infiniteAmmo;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPlayerCollision(boolean playerCollision) {
        this.playerCollision = playerCollision;
    }

    public boolean isPlayerCollided() {
        return playerCollision;
    }

    public boolean isHealthInfinite() {
        return infiniteHealth;
    }

    public void setInfiniteHealth(boolean infiniteHealth) {
        this.infiniteHealth = infiniteHealth;
    }

    public boolean freeMovementIsEnabled() {
        return freeMovement;
    }

    public void allowFreeMovement(boolean freeMovement) {
        this.freeMovement = freeMovement;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
}