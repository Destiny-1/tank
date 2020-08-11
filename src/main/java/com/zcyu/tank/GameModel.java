package com.zcyu.tank;

import com.zcyu.cor.BulletTankCollider;
import com.zcyu.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    //    Tank tank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
    public static final GameModel gm = new GameModel();

    public List<GameObject> gameObjects = new ArrayList();

    ColliderChain colliderChain = new ColliderChain();

    Tank myTank;

    static {
        gm.init();
    }

    private GameModel() {
    }

    private void init() {
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

        for (int i = 0; i < 15; i++) {
            new Tank(50 + i * 80, 20, Dir.DOWN, Group.BAD);
        }

        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    public void paint(Graphics graphics) {
        myTank.paint(graphics);
        Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.setColor(color);

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(graphics);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                colliderChain.collide(gameObjects.get(i), gameObjects.get(j));
            }
        }
    }

    public void add(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        this.gameObjects.remove(gameObject);
    }
}
