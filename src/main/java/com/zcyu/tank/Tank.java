package com.zcyu.tank;

import com.zcyu.strategy.DefaultFireStrategy;
import com.zcyu.strategy.FireStrategy;
import com.zcyu.strategy.FourDirsFireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject {
    public static int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();
    public Rectangle rectangle = new Rectangle();
    public Dir dir;
    private int speed = 5;
    private Boolean moving = true;
    public Group group;
    private Random random = new Random();
    private Boolean alive = true;
    public FireStrategy fireStrategy;

    int oldX, oldY;
    GameModel gm = GameModel.gm;
    public Tank(int x, int y, Dir dir, Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        if (group == Group.BAD) {
            fireStrategy = DefaultFireStrategy.defaultFireStrategy;
        } else {
            fireStrategy = FourDirsFireStrategy.fourDirsFireStrategy;
        }
        gm.add(this);
    }

    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics graphics) {
        if (!alive) gm.remove(this);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        oldX = x;
        oldY = y;
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= speed;
                Bullet.width = ResourceMgr.bulletU.getWidth();
                Bullet.height = ResourceMgr.bulletU.getHeight();
                WIDTH = ResourceMgr.goodTankU.getWidth();
                HEIGHT = ResourceMgr.goodTankU.getHeight();
                break;
            case DOWN:
                y += speed;
                Bullet.width = ResourceMgr.bulletD.getWidth();
                Bullet.height = ResourceMgr.bulletD.getHeight();
                WIDTH = ResourceMgr.goodTankD.getWidth();
                HEIGHT = ResourceMgr.goodTankD.getHeight();
                break;
            case LEFT:
                x -= speed;
                Bullet.width = ResourceMgr.bulletL.getWidth();
                Bullet.height = ResourceMgr.bulletL.getHeight();
                WIDTH = ResourceMgr.goodTankL.getWidth();
                HEIGHT = ResourceMgr.goodTankL.getHeight();
                break;
            case RIGHT:
                x += speed;
                Bullet.width = ResourceMgr.bulletR.getWidth();
                Bullet.height = ResourceMgr.bulletR.getHeight();
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
        if (this.x > TankFrame.WIDTH - WIDTH) x = TankFrame.WIDTH - WIDTH;
        if (this.y > TankFrame.HEIGHT - HEIGHT) y = TankFrame.HEIGHT - HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fireStrategy.fire(this);
    }

    public void die() {
        this.alive = false;
    }

    public void back(){
        this.x = oldX;
        this.y = oldY;
    }
}
