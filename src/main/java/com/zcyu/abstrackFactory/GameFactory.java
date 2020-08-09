package com.zcyu.abstrackFactory;

import com.zcyu.tank.BaseTank;
import com.zcyu.tank.Dir;
import com.zcyu.tank.Group;
import com.zcyu.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame);

    public abstract ExplodeFactory createExplode(int x, int y, TankFrame tankFrame);

    public abstract BulletFactory createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame);
}
