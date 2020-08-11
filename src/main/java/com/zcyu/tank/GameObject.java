package com.zcyu.tank;

import java.awt.*;

public abstract class GameObject {
    public int x, y;

    public abstract void paint(Graphics graphics);

    public abstract int getWidth();

    public abstract int getHeight();
}
