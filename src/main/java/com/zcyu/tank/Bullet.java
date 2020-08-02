package com.zcyu.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    public static int WIDTH, HEIGHT;
    private Dir dir;
    private static int SPEED = 10;

    public Group group = Group.BAD;

    private TankFrame tankFrame;

    private Boolean alive = true;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.WIDTH || y > TankFrame.HEIGHT) {
            alive = false;
        }


    }

    public void paint(Graphics graphics) {
        move();
        if (!alive) {
            tankFrame.bulletList.remove(this);
        } else {
            switch (dir) {
                case UP:
                    graphics.drawImage(ResourceMgr.bulletU, x, y, null);
                    break;
                case DOWN:
                    graphics.drawImage(ResourceMgr.bulletD, x, y, null);
                    break;
                case LEFT:
                    graphics.drawImage(ResourceMgr.bulletL, x, y, null);
                    break;
                case RIGHT:
                    graphics.drawImage(ResourceMgr.bulletR, x, y, null);
                    break;
            }
        }
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.group) return;
        Rectangle b = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle t = new Rectangle(tank.x, tank.y, tank.WIDTH, tank.HEIGHT);
        if (b.intersects(t)) {
            this.die();
            tank.die();
            Explode.setExplodeList(this.x, this.y, tankFrame);
        }
    }

    private void die() {
        this.alive = false;
    }
}
