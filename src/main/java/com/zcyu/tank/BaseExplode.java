package com.zcyu.tank;

import com.zcyu.abstrackFactory.ExplodeFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BaseExplode extends ExplodeFactory {
    private int x, y;
    private TankFrame tankFrame;

    public static int WIDTH;
    public static int HEIGHT;

    private int step = 0;

    public BaseExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

//    private static void BaseExplodeHandler (int x, int y, TankFrame tankFrame){
//        private static final BaseExplode baseExplode = new BaseExplode(x, y, tankFrame);
//    }


    public void paint(Graphics graphics) {
        BufferedImage bufferedImage = ResourceMgr.explode[step++];
        WIDTH = bufferedImage.getWidth();
        HEIGHT = bufferedImage.getHeight();
        graphics.drawImage(bufferedImage, this.x, this.y, null);
        if (step >= ResourceMgr.explode.length) {
            tankFrame.explodes.remove(this);
        }
    }


}
