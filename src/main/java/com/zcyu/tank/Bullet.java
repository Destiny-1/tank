package com.zcyu.tank;

import java.awt.*;

public class Bullet extends GameObject{
    public static int width, height;
    private Dir dir;
    private static int SPEED = 10;

    public Rectangle rectangle = new Rectangle();

    public Group group = Group.BAD;

    GameModel gm = GameModel.gm;

    private Boolean alive = true;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = width;
        rectangle.height = height;
        gm.add(this);
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
            gm.remove(this);
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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    public void die() {
        this.alive = false;
    }
}
