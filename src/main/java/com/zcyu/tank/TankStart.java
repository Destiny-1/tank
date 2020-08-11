package com.zcyu.tank;

public class TankStart {
    public static void main(String[] args) throws Exception {
        TankFrame frame = new TankFrame();
        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
