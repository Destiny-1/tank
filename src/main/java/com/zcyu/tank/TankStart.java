package com.zcyu.tank;

public class TankStart {
    public static void main(String[] args) throws Exception{
        TankFrame frame = new TankFrame();
        for (int i = 0; i < 5; i++) {
            frame.tanks.add(new Tank(50 + i * 80, 20, Dir.DOWN, Group.BAD, frame));
        }
        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
