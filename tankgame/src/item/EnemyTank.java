package item;

import event.Shoot;

import java.util.ArrayList;
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    private Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank() {
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public boolean isTouchOthers() {
        switch (this.getDirection()) {
            case 0, 2 -> { // 上，下
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if (enemyTank == this) continue;
                    if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                        if (this.getX() >= enemyTank.getX() - 40
                                && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() - 60
                                && this.getY() <= enemyTank.getY() + 60) {
                            return true;
                        }
                    } else if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                        if (this.getX() >= enemyTank.getX() - 10 - 60
                                && this.getX() <= enemyTank.getX() - 10 + 60
                                && this.getY() >= enemyTank.getY() + 10 - 40
                                && this.getY() <= enemyTank.getY() + 10 + 40) {
                            return true;
                        }
                    }
                }
            }
            case 1, 3 -> { // 右，左
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if (enemyTank == this) continue;
                    if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                        if (this.getX() - 10 >= enemyTank.getX() - 60
                                && this.getX() - 10 <= enemyTank.getX() + 40
                                && this.getY() + 10 >= enemyTank.getY() - 40
                                && this.getY() + 10 <= enemyTank.getY() + 60) {
                            return true;
                        }
                    } else if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                        if (this.getX() - 10 >= enemyTank.getX() - 60
                                && this.getX() - 10 <= enemyTank.getX() + 60
                                && this.getY() + 10 >= enemyTank.getY() - 40
                                && this.getY() + 10 <= enemyTank.getY() + 40) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (isAlive()) {
            // 创建子弹
            if (getShoots().size() == 0) {
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
                        if (isTouchOthers()) break;
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
                        if (isTouchOthers()) break;
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
                        if (isTouchOthers()) break;
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
                        if (isTouchOthers()) break;
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
