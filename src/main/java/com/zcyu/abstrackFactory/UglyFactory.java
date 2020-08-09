package com.zcyu.abstrackFactory;

import com.zcyu.tank.*;

public class UglyFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame) {
        return new BadTank(x, y, dir, tankFrame);
    }

    @Override
    public ExplodeFactory createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x, y, tankFrame);
    }

    @Override
    public BulletFactory createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new CircleBullet(x, y, dir, group, tankFrame);
    }
}
