package com.zcyu.abstrackFactory;

import com.zcyu.tank.*;

public class DefaultFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame) {
        return new GoodTank(x, y, dir, tankFrame);
    }

    @Override
    public ExplodeFactory createExplode(int x, int y, TankFrame tankFrame) {
        return new BaseExplode(x, y, tankFrame);
    }

    @Override
    public BulletFactory createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new DefaultBullet(x, y, dir, group, tankFrame);
    }
}
