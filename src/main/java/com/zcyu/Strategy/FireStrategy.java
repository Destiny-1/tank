package com.zcyu.Strategy;

import com.zcyu.tank.BaseTank;
import com.zcyu.tank.DefaultBullet;

public interface FireStrategy {
    default void fire(BaseTank tank) {
        int bX = tank.x + tank.WIDTH / 2 - DefaultBullet.WIDTH / 2;
        int bY = tank.y + tank.HEIGHT / 2 - DefaultBullet.HEIGHT / 2;
        tank.tankFrame.gameFactory.createBullet(bX, bY, tank.dir, tank.group, tank.tankFrame);
    }
}
