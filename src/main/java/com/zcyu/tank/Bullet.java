package com.zcyu.tank;

import java.awt.*;

public class Bullet {
    private  int x , y;
    private int width = 20;
    private int height = 20;
    private Dir dir;
    private static int speed = 2;

    private TankFrame tankFrame;

    private  Boolean alive = true;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
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

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Bullet.speed = speed;
    }

    private void move() {
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
        if(x < 0 || y < 0 || x > TankFrame.width || y > TankFrame.height){
            alive = false;
        }
    }

    public void paint(Graphics graphics) {
        move();
        if(!alive){
            tankFrame.bulletList.remove(this);
        }else{
            Color c = graphics.getColor();
            graphics.setColor(Color.cyan);
            graphics.fillOval(x, y, width , height);
            graphics.setColor(c);
        }
    }
}
