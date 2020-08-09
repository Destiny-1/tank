package com.zcyu.tank;

import com.zcyu.abstrackFactory.GameFactory;
import com.zcyu.abstrackFactory.UglyFactory;

public class TankStart {

    public static void main(String[] args) throws Exception {

        TankFrame frame = new TankFrame();
        int tankAmount = Integer.valueOf(PropertiesMgr.INSTANCE.getConfigValue("tank.amount"));

        GameFactory factory = new UglyFactory();

        for (int i = 0; i < tankAmount; i++) {
            frame.tanks.add(factory.createTank(50 + i * 80, 20, Dir.DOWN, frame));
        }
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
