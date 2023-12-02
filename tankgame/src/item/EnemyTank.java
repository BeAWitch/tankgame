package item;

import event.Shoot;

public class EnemyTank extends Tank implements Runnable {
    public EnemyTank() {
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (isAlive()) {
            // 创建子弹
            if(getShoots().size()==0){
                Shoot shoot = null;
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
                // 启动
                new Thread(shoot).start();
            }
            // 根据方向移动
            switch (getDirection()) {
                case 0 -> {// 上
                    for (int i = 0; i < 20; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 1 -> {// 右
                    for (int i = 0; i < 20; i++) {
                        if (getX() - 10 + 60 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 2 -> {// 下
                    for (int i = 0; i < 20; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 3 -> {// 左
                    for (int i = 0; i < 20; i++) {
                        if (getX() - 10 > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

            // 随机改变方向
            setDirection((int) (Math.random() * 4));
        }
    }
}
