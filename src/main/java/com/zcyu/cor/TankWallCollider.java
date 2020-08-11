package com.zcyu.cor;

import com.zcyu.tank.GameObject;
import com.zcyu.tank.Tank;
import com.zcyu.tank.Wall;

public class TankWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Wall) {
			Tank t = (Tank)o1;
			Wall w = (Wall)o2;
			if(t.rectangle.intersects(w.rectangle)) {
				t.back();
			}
		} else if (o1 instanceof Wall && o2 instanceof Tank) {
			return collide(o2, o1);
		} 
		return true;
	}

}
