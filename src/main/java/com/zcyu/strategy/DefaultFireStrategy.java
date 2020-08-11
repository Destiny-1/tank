package com.zcyu.strategy;

import com.zcyu.tank.Bullet;
import com.zcyu.tank.GameModel;
import com.zcyu.tank.Tank;

public class DefaultFireStrategy implements FireStrategy{
    public static final DefaultFireStrategy defaultFireStrategy = new DefaultFireStrategy();
    private DefaultFireStrategy(){}

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + tank.getWidth() / 2 - Bullet.width / 2;
        int bY = tank.y + tank.getHeight() / 2 - Bullet.height / 2;
        new Bullet(bX, bY, tank.dir, tank.group);
    }
}
