package com.zcyu.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    public int x, y;
    public int WIDTH = ResourceMgr.goodTankD.getWidth();
    public int HEIGHT = ResourceMgr.goodTankD.getHeight();
    public Rectangle rectangle = new Rectangle();
    private Dir dir = Dir.UP;
    private int speed = 5;
    private Boolean moving = true;
    public Group group = Group.BAD;
    private Random random = new Random();
    private Boolean alive = true;

    private TankFrame tankFrame = null;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
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

    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics graphics) {
        if (!alive) tankFrame.tanks.remove(this);
        switch (dir) {
            case UP:
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= speed;
                Bullet.WIDTH = ResourceMgr.bulletU.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletU.getHeight();
                WIDTH = ResourceMgr.goodTankU.getWidth();
                HEIGHT = ResourceMgr.goodTankU.getHeight();
                break;
            case DOWN:
                y += speed;
                Bullet.WIDTH = ResourceMgr.bulletD.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletD.getHeight();
                WIDTH = ResourceMgr.goodTankD.getWidth();
                HEIGHT = ResourceMgr.goodTankD.getHeight();
                break;
            case LEFT:
                x -= speed;
                Bullet.WIDTH = ResourceMgr.bulletL.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletL.getHeight();
                WIDTH = ResourceMgr.goodTankL.getWidth();
                HEIGHT = ResourceMgr.goodTankL.getHeight();
                break;
            case RIGHT:
                x += speed;
                Bullet.WIDTH = ResourceMgr.bulletR.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletR.getHeight();
                WIDTH = ResourceMgr.goodTankR.getWidth();
                HEIGHT = ResourceMgr.goodTankR.getHeight();
                break;
        }

        if (random.nextInt(100) > 85 && this.group == Group.BAD) {
            this.fire();
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) randomDir();

        boundsCheck();

        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) x = 6;
        if (this.y < 30) y = 30;
        if (this.x > tankFrame.WIDTH - this.WIDTH) x = tankFrame.WIDTH - this.WIDTH;
        if (this.y > tankFrame.HEIGHT - this.HEIGHT) y = tankFrame.HEIGHT - this.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + this.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + this.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Bullet bullet = new Bullet(bX, bY, this.dir, this.group, this.tankFrame);
        tankFrame.bulletList.add(bullet);
    }

    public void die() {
        this.alive = false;
    }
}
