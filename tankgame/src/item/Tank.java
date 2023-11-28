package item;

import event.Shoot;

import java.util.ArrayList;

public class Tank {
    // 坦克坐标
    private int x;
    private int y;
    private int direction;// 上 0，右 1，下 2， 左 3
    private int speed;

    private Shoot shoot = null;
    private ArrayList<Shoot> Shoots=new ArrayList<>();
    public Shoot getShoot() {
        return shoot;
    }

    public void setShoot(Shoot shoot) {
        this.shoot = shoot;
    }

    public Tank() {

    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 移动
    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public ArrayList<Shoot> getShoots() {
        return Shoots;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void shooting() {
        // 根据方向创建Shot对象
        switch (direction) {
            case 0 ->// 上
                    shoot = new Shoot(x + 20, y, 0);
            case 1 ->// 右
                    shoot = new Shoot(x + 50, y + 30, 1);
            case 2 ->// 下
                    shoot = new Shoot(x + 20, y + 60, 2);
            case 3 ->// 左
                    shoot = new Shoot(x - 10, y + 30, 3);
        }
        // 启动线程
        new Thread(shoot).start();
    }
}
