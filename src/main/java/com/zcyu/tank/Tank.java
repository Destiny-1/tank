package com.zcyu.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    public int x, y;
    public int WIDTH = ResourceMgr.tankD.getWidth();
    public int HEIGHT = ResourceMgr.tankD.getHeight();
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
    }

    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics graphics) {
        if (!alive) tankFrame.enemies.remove(this);
        switch (dir) {
            case UP:
                graphics.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.tankR, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= speed;
                if(y <= 0){
                    dir = Dir.DOWN;
                }
                Bullet.WIDTH = ResourceMgr.bulletU.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletU.getHeight();
                WIDTH = ResourceMgr.tankU.getWidth();
                HEIGHT = ResourceMgr.tankU.getHeight();
                break;
            case DOWN:
                y += speed;
                if(y >= tankFrame.HEIGHT){
                    dir = Dir.UP;
                }
                Bullet.WIDTH = ResourceMgr.bulletD.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletD.getHeight();
                WIDTH = ResourceMgr.tankD.getWidth();
                HEIGHT = ResourceMgr.tankD.getHeight();
                break;
            case LEFT:
                x -= speed;
                if(x <= 0){
                    dir = Dir.RIGHT;
                }
                Bullet.WIDTH = ResourceMgr.bulletL.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletL.getHeight();
                WIDTH = ResourceMgr.tankL.getWidth();
                HEIGHT = ResourceMgr.tankL.getHeight();
                break;
            case RIGHT:
                x += speed;
                if(x > tankFrame.WIDTH){
                    dir = Dir.LEFT;
                }
                Bullet.WIDTH = ResourceMgr.bulletR.getWidth();
                Bullet.HEIGHT = ResourceMgr.bulletR.getHeight();
                WIDTH = ResourceMgr.tankR.getWidth();
                HEIGHT = ResourceMgr.tankR.getHeight();
                break;
        }
        if(random.nextInt(10) > 8 ) this.fire();
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
