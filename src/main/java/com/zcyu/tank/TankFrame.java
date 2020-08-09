package com.zcyu.tank;


import com.zcyu.abstrackFactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    GoodTank tank = new GoodTank(200, 400, Dir.DOWN,this);

    public GameFactory gameFactory = new UglyFactory();

    List<BulletFactory> bulletList = new ArrayList();

    List<BaseTank> tanks = new ArrayList();
    List<ExplodeFactory> explodes = new ArrayList();

    public static int WIDTH = 1080;
    public static int HEIGHT = 960;

    public TankFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setTitle("Tank War write by zcyu");
        this.setVisible(true);
        this.setIconImage(ResourceMgr.backGroundImage);
        this.addKeyListener(new MykeyListener());

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics graphics) {
        tank.paint(graphics);
        Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.drawString("子弹数量:" + bulletList.size(), 20, 60);
        graphics.drawString("敌人数量:" + tanks.size(), 20, 80);
        graphics.drawString("爆炸数量:" + explodes.size(), 20, 100);
        graphics.setColor(color);

        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(graphics);
        }
        for (int j = 0; j < tanks.size(); j++) {
            tanks.get(j).paint(graphics);
        }
        for (int k = 0; k < explodes.size(); k++) {
            explodes.get(k).paint(graphics);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bulletList.get(i).collideWith(tanks.get(j));
            }
        }
    }

    /**
     * 使用緩存技術一次性将整张画布画到屏幕上 解决闪烁问题
     */
    Image offsetImage = null;

    @Override
    public void update(Graphics g) {
        if (offsetImage == null) {
            offsetImage = this.createImage(WIDTH, HEIGHT);
        }
        Graphics gOffScreen = offsetImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offsetImage, 0, 0, null);
    }

    class MykeyListener extends KeyAdapter {
        Boolean left = false;
        Boolean right = false;
        Boolean up = false;
        Boolean down = false;

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
            }
            setTankDir();
        }

        private void setTankDir() {
            if (!left && !right && !up && !down) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);
                if (left) {
                    tank.setDir(Dir.LEFT);
                }
                if (right) {
                    tank.setDir(Dir.RIGHT);
                }
                if (up) {
                    tank.setDir(Dir.UP);
                }
                if (down) {
                    tank.setDir(Dir.DOWN);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_SPACE:
                    tank.fire();
            }
            setTankDir();
        }
    }
}
