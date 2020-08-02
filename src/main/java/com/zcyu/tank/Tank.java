package com.zcyu.tank;

import java.awt.*;

public class Tank {
    private  int x, y ;
    private  int width = 100;
    private  int height = 100;
    private Dir dir = Dir.UP;
    private int speed = 10;
    private  Boolean moving = false;

    private TankFrame tankFrame = null;

    public Tank(int x, int y, Dir dir, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public Boolean getMoving() {
        return moving;
    }

    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {

        this.speed = speed;
    }

    public void paint(Graphics graphics) {
        switch (dir){
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
        if(!moving) return;
        switch (dir){
            case UP:
                y -= 10;
                break;
            case DOWN:
                y += 10;
                break;
            case LEFT:
                x -= 10;
                break;
            case RIGHT:
                x += 10;
                break;
        }
    }

    public void fire() {
        tankFrame.bulletList.add(new Bullet(this.x + this.height / 2, this.y + this.width / 2, this.dir, tankFrame));
    }
}
