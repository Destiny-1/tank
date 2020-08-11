package com.zcyu.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explode extends GameObject {

    private int step = 0;

    public static int width, height;

    GameModel gm = GameModel.gm;
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        gm.add(this);
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics graphics) {
        BufferedImage bufferedImage = ResourceMgr.explode[step++];
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
        graphics.drawImage(bufferedImage, this.x, this.y, null);
        if (step >= ResourceMgr.explode.length) {
            gm.remove(this);
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


}
