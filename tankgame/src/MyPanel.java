import event.Shoot;
import item.EnemyTank;
import item.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    // 定义我的坦克
    Hero hero;
    // 敌人坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    public MyPanel() {
        // 初始化自己的坦克
        hero = new Hero(100, 100);
        hero.setSpeed(2);
        // 初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirection(2);
            Shoot shoot = new Shoot(enemyTank.getX() + 20, enemyTank.getY() + 60, 2);
            enemyTank.getShoots().add(shoot);
            new Thread(shoot).start();
            enemyTanks.add(enemyTank);
            new Thread(enemyTank).start();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        // 画坦克
        // hero
        if(hero.isAlive()) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 0);
        }
        // 画hero的子弹
        for (int i = 0; i < hero.getShoots().size(); i++) {
            Shoot shoot = hero.getShoots().get(i);
            if (shoot.isAlive()) {
                g.fillOval(shoot.getX(), shoot.getY(), 2, 2);
            } else {
                hero.getShoots().remove(shoot);
            }
        }

        // 敌方坦克
        for (EnemyTank enemyTank : enemyTanks) {
            // 判断是否存活
            if (!enemyTank.isAlive()) {
                continue;
            }
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
            for (int j = 0; j < enemyTank.getShoots().size(); j++) {
                Shoot shoot = enemyTank.getShoots().get(j);
                if (shoot.isAlive()) {
                    g.fillOval(shoot.getX(), shoot.getY(), 2, 2);
                } else {
                    enemyTank.getShoots().remove(shoot);
                }
            }
        }
    }
    // 绘制坦克

    /**
     * @param x         坦克左上角的x坐标
     * @param y         坦克左上角的x坐标
     * @param g         画笔
     * @param direction 朝向
     * @param type      类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        // 判断类型，设置不同颜色
        switch (type) {
            case 0 -> // 我们的坦克
                    g.setColor(Color.cyan);// 青色
            case 1 -> // 敌方的坦克
                    g.setColor(Color.yellow);
        }

        // 根据方向，绘制坦克
        switch (direction) {
            case 0 -> { // 向上
                g.fill3DRect(x, y, 10, 60, false);// 左履带
                g.fill3DRect(x + 30, y, 10, 60, false);// 右履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);// 炮管
            }
            case 1 -> { // 向右
                g.fill3DRect(x - 10, y + 10, 60, 10, false);// 上履带
                g.fill3DRect(x - 10, y + 40, 60, 10, false);// 下履带
                g.fill3DRect(x, y + 20, 40, 20, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x + 50, y + 30);// 炮管
            }
            case 2 -> { // 向下
                g.fill3DRect(x, y, 10, 60, false);// 左履带
                g.fill3DRect(x + 30, y, 10, 60, false);// 右履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);// 炮管
            }
            case 3 -> { // 向左
                g.fill3DRect(x - 10, y + 10, 60, 10, false);// 上履带
                g.fill3DRect(x - 10, y + 40, 60, 10, false);// 下履带
                g.fill3DRect(x, y + 20, 40, 20, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x - 10, y + 30);// 炮管
            }
            default -> System.out.println("暂时没有处理");
        }
    }

    public boolean hitTank(Shoot shoot, EnemyTank enemyTank) {
        switch (enemyTank.getDirection()) {
            case 0, 2 -> {// 上下
                if (shoot.getX() > enemyTank.getX() && shoot.getX() < enemyTank.getX() + 40
                        && shoot.getY() > enemyTank.getY() && shoot.getY() < enemyTank.getY() + 60) {
                    enemyTank.setAlive(false);
                    shoot.setAlive(false);
                    enemyTanks.remove(enemyTank);
                    return true;
                }
            }
            case 1, 3 -> {// 右左
                if (shoot.getX() > enemyTank.getX() && shoot.getX() < enemyTank.getX() + 60
                        && shoot.getY() > enemyTank.getY() && shoot.getY() < enemyTank.getY() + 40) {
                    enemyTank.setAlive(false);
                    shoot.setAlive(false);
                    enemyTanks.remove(enemyTank);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hitHero(Shoot shoot) {
        switch (hero.getDirection()) {
            case 0, 2 -> {// 上下
                if (shoot.getX() > hero.getX() && shoot.getX() < hero.getX() + 40
                        && shoot.getY() > hero.getY() && shoot.getY() < hero.getY() + 60) {
                    hero.setAlive(false);
                    shoot.setAlive(false);
                    return true;
                }
            }
            case 1, 3 -> {// 右左
                if (shoot.getX() > hero.getX() && shoot.getX() < hero.getX() + 60
                        && shoot.getY() > hero.getY() && shoot.getY() < hero.getY() + 40) {
                    hero.setAlive(false);
                    shoot.setAlive(false);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(hero.isAlive()){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> {
                    hero.setDirection(0);
                    if (hero.getY() > 0) {
                        hero.moveUp();
                    }
                }
                case KeyEvent.VK_D -> {
                    hero.setDirection(1);
                    if (hero.getX() - 10 + 60 < 1000) {
                        hero.moveRight();
                    }
                }
                case KeyEvent.VK_S -> {
                    hero.setDirection(2);
                    if (hero.getY() + 60 < 750) {
                        hero.moveDown();
                    }
                }
                case KeyEvent.VK_A -> {
                    hero.setDirection(3);
                    if (hero.getX() - 10 > 0) {
                        hero.moveLeft();
                    }
                }
                case KeyEvent.VK_J -> {
                    hero.shooting();
                }
            }

            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 判断是否击中了敌人坦克
            for (int i = 0; i < hero.getShoots().size(); i++) {
                for (int j = 0; j < enemyTanks.size(); j++) {
                    if (hitTank(hero.getShoots().get(i), enemyTanks.get(j)))
                        j--;
                }
            }

            // 判断是否击中了 hero
            for (int i = 0; hero.isAlive() && i < enemyTanks.size(); i++) {
                for (int j = 0; j < enemyTanks.get(i).getShoots().size(); j++) {
                    if (hitHero(enemyTanks.get(i).getShoots().get(j)))
                        break;
                }
            }

            this.repaint();
        }
    }
}
