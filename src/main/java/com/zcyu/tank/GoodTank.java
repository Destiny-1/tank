package com.zcyu.tank;

import com.zcyu.Strategy.FireStrategy;
import com.zcyu.Strategy.FourDirsFireStrategy;

import java.awt.*;

public class GoodTank extends BaseTank {
    FireStrategy fireStrategy = FourDirsFireStrategy.getFourDirsFireStrategy();

    public GoodTank(int x, int y, Dir dir, TankFrame tankFrame) {
        super(x, y, dir, Group.GOOD, tankFrame);
    }


    public void paint(Graphics graphics) {
        if (!alive) tankFrame.tanks.remove(this);
        switch (dir) {
            case UP:
                graphics.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
        }
        move();
    }

    public void fire() {
        fireStrategy.fire(this);
    }
}
