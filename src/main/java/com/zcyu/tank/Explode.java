package com.zcyu.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explode {
    private  int x, y ;
    private TankFrame tankFrame;

    public static int WIDTH;
    public static int HEIGHT ;

    private int step = 0;
    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(() -> new Audio( "audio/explode.wav").play()).start();
    }

    public void paint(Graphics graphics){
        BufferedImage bufferedImage = ResourceMgr.explode[step++];
        WIDTH = bufferedImage.getWidth();
        HEIGHT = bufferedImage.getHeight();
        graphics.drawImage(bufferedImage, this.x, this.y, null);
        if(step >= ResourceMgr.explode.length){
            tankFrame.explodes.remove(this);
        }
    }


}
