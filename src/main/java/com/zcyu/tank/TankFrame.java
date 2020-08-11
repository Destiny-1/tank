package com.zcyu.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    public static int WIDTH = 1080;
    public static int HEIGHT = 960;

    GameModel gameModel = GameModel.gm;
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
        gameModel.paint(graphics);
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
                gameModel.myTank.setMoving(false);
            } else {
                gameModel.myTank.setMoving(true);
                if (left) {
                    gameModel.myTank.setDir(Dir.LEFT);
                }
                if (right) {
                    gameModel.myTank.setDir(Dir.RIGHT);
                }
                if (up) {
                    gameModel.myTank.setDir(Dir.UP);
                }
                if (down) {
                    gameModel.myTank.setDir(Dir.DOWN);
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
                    gameModel.myTank.fire();
            }
            setTankDir();
        }
    }
}
