package com.zcyu.cor;

import com.zcyu.tank.Bullet;
import com.zcyu.tank.Explode;
import com.zcyu.tank.GameObject;
import com.zcyu.tank.Tank;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Bullet){
            Tank tank = (Tank)o1;
            Bullet bullet = (Bullet)o2;
            if(bullet.group == tank.group) return true;
            if(bullet.rectangle.intersects(tank.rectangle)){
                tank.die();
                bullet.die();
                int bX = tank.x + tank.getWidth() / 2 - Explode.width / 2;
                int bY = tank.y + tank.getHeight() / 2 - Explode.height / 2;
                new Explode(bX, bY);
                return false;
            }
        }else if(o1 instanceof Bullet && o2 instanceof Tank){
            return collide(o2, o1);
        }
        return true;
    }
}
