package com.zcyu.Strategy;

import com.zcyu.tank.BaseTank;
import com.zcyu.tank.DefaultBullet;
import com.zcyu.tank.Dir;

public class FourDirsFireStrategy  implements FireStrategy {

    private FourDirsFireStrategy(){}

    private static class fourDirsFireStrategyHandler {
        private static final FourDirsFireStrategy fourDirsFireStrategy = new FourDirsFireStrategy();
    }

    public static FourDirsFireStrategy getFourDirsFireStrategy(){
        return fourDirsFireStrategyHandler.fourDirsFireStrategy;
    }
    public void fire(BaseTank tank) {
        int bX = tank.x + tank.WIDTH / 2 - DefaultBullet.WIDTH / 2;
        int bY = tank.y + tank.HEIGHT / 2 - DefaultBullet.HEIGHT / 2;
        Dir dirs[] = Dir.values();
        for (Dir dir : dirs) {
            new DefaultBullet(bX, bY, dir, tank.group, tank.tankFrame);
        }
    }

}
