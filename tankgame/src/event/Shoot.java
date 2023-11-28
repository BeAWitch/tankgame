package event;

public class Shoot implements Runnable{

    private int x;
    private int y;
    private int direction;
    private int speed = 1;
    private boolean isAlive=true;

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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Shoot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {
        while(isAlive){
            // 每隔50ms改变一次位置
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direction) {
                case 0 ->// 上
                        y -= speed;
                case 1 ->// 右
                        x += speed;
                case 2 ->// 下
                        y += speed;
                case 3 ->// 左
                        x -= speed;
            }
            if(!(x>=0 && x<=1000 && y>=0 && y<=750)) {// 边界判断
                isAlive=false;
            }
        }
    }
}
