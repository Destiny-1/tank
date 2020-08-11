package com.zcyu.cor;

import com.zcyu.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
