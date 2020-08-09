package com.zcyu.tank;

import com.zcyu.Strategy.DefaultFireStrategy;
import com.zcyu.Strategy.FireStrategy;
import com.zcyu.abstrackFactory.TankFactory;

import java.awt.*;
import java.util.Random;

public class BadTank extends BaseTank {

    FireStrategy fireStrategy = DefaultFireStrategy.getDefaultFireStrategy();

    public BadTank(int x, int y, Dir dir, TankFrame tankFrame) {
        super(x, y, dir, Group.BAD, tankFrame);
    }
    public void paint(Graphics graphics) {
        if (!alive) tankFrame.tanks.remove(this);
        switch (dir) {
            case UP:
                graphics.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
        }
        move();
    }

    public void fire() {
        fireStrategy.fire(this);
    }

    public void die() {
        this.alive = false;
    }
}
