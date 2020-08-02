package com.zcyu.tank;

import java.awt.*;

public class Explode {
    private  int x, y ;
    private  int width , height;
    private Image image;

    public Explode(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public void paint(Graphics graphics){
        graphics.drawImage(this.image, this.x, this.y, null);
    }

    public static void setExplodeList(int x, int y, TankFrame tankFrame){
        for (int i = 0; i < ResourceMgr.explode.length; i++) {
            tankFrame.explodes.add(new Explode(x, y, ResourceMgr.explode[i].getWidth(), ResourceMgr.explode[i].getHeight(), ResourceMgr.explode[i]));
        }
        new Audio("audio/explode.wav").play();
    }

}
