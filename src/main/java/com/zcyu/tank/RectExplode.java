package com.zcyu.tank;

import com.zcyu.abstrackFactory.ExplodeFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RectExplode extends ExplodeFactory {
    private int x, y;
    private TankFrame tankFrame;

    public static int WIDTH;
    public static int HEIGHT;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.red);
        step++;
        graphics.fillOval(this.x, this.y, 10 * step, 10 * step);
        if (step > 5) {
            tankFrame.explodes.remove(this);
        }
        graphics.setColor(color);
    }


}
