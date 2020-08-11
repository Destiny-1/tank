package com.zcyu.strategy;

import com.zcyu.tank.Bullet;
import com.zcyu.tank.Dir;
import com.zcyu.tank.Tank;

public class FourDirsFireStrategy implements FireStrategy {

    public static final FourDirsFireStrategy fourDirsFireStrategy = new FourDirsFireStrategy();

    private FourDirsFireStrategy() {
    }

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + tank.getWidth() / 2 - Bullet.width / 2;
        int bY = tank.y + tank.getHeight() / 2 - Bullet.height / 2;
        Dir dirs[] = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bX, bY, dir, tank.group);
        }
    }
}
