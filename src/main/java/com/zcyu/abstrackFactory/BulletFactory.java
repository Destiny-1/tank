package com.zcyu.abstrackFactory;

import com.zcyu.tank.BaseTank;
import com.zcyu.tank.GoodTank;

import java.awt.*;

public abstract class BulletFactory {
    public abstract void paint(Graphics graphics);

    public abstract void collideWith(BaseTank baseTank);
}
