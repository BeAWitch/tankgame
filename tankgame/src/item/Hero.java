package item;

import event.Shoot;

public class Hero extends Tank {
    private Shoot shoot = null;
    private int shootSize = 5;

    public Hero() {
    }

    public Hero(int x, int y) {
        super(x, y);
    }

    public void shooting() {
        // 限制子弹数目
        if (getShoots().size() >= shootSize) return;

        // 根据方向创建Shot对象
        switch (getDirection()) {
            case 0 ->// 上
                    shoot = new Shoot(getX() + 20, getY(), 0);
            case 1 ->// 右
                    shoot = new Shoot(getX() + 50, getY() + 30, 1);
            case 2 ->// 下
                    shoot = new Shoot(getX() + 20, getY() + 60, 2);
            case 3 ->// 左
                    shoot = new Shoot(getX() - 10, getY() + 30, 3);
        }

        getShoots().add(shoot);
        // 启动线程
        new Thread(shoot).start();
    }
}
