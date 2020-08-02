package com.zcyu.tank;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank tank = new Tank(100, 100, Dir.DOWN, this);
    List<Bullet> bulletList = new ArrayList();

    public static int width = 800;
    public static int height = 600;

    private Boolean flag = true;
    public TankFrame(){
        this.setSize(width, height);
        this.setResizable(true);
        this.setTitle("Tank War write by zcyu");
        this.setVisible(true);
        this.addKeyListener(new MykeyListener());

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }
            public void windowClosing(WindowEvent e) {
                System.out.println("windowOpened");
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics graphics){
        tank.paint(graphics);
        Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.drawString("子弹数量:" + bulletList.size(), 20, 60);
        graphics.setColor(color);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(graphics);
        }
    }

    /**
     * 使用緩存技術一次性将整张画布画到屏幕上 解决闪烁问题
     * */
    Image offsetImage = null;
    @Override
    public void update(Graphics g) {
        if(offsetImage == null){
            offsetImage = this.createImage(width, height);
        }
        Graphics gOffScreen = offsetImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0, width, height);
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
            switch (e.getKeyCode()){
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

        private void setTankDir(){
            if(!left && !right && !up && !down){
                tank.setMoving(false);
            }else{
                tank.setMoving(true);
                if(left) {
                    tank.setDir(Dir.LEFT);
                }
                if(right){
                    tank.setDir(Dir.RIGHT);
                }
                if(up){
                    tank.setDir(Dir.UP);
                }
                if(down){
                    tank.setDir(Dir.DOWN);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
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
                case KeyEvent.VK_CONTROL:
                    tank.fire();
            }
            setTankDir();
        }
    }
}
