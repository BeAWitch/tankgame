package item;

public class Tank {
    // 坦克坐标
    private int x;
    private int y;
    private int direction;// 上 0，右 1，下 2， 左 3
    private int speed;

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
}
