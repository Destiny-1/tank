package com.zcyu.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    public static int WIDTH, HEIGHT;
    private Dir dir;
    private static int SPEED = 10;

    Rectangle rectangle = new Rectangle();

    public Group group = Group.BAD;

    private TankFrame tankFrame;

    private Boolean alive = true;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
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
        rectangle.x = this.x;
        rectangle.y = this.y;

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
        if (rectangle.intersects(tank.rectangle)) {
            this.die();
            tank.die();
            int bX = tank.x + tank.WIDTH / 2 - Explode.WIDTH / 2;
            int bY = tank.y + tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tankFrame.explodes.add(new Explode(bX, bY, tankFrame));
        }
    }

    private void die() {
        this.alive = false;
    }
}
