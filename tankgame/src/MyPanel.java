import item.EnemyTank;
import item.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener {
    // 定义我的坦克
    Hero hero = null;
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
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        // 画坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 0);
        for (int i = 0; i < enemyTankSize; i++) {
            drawTank(enemyTanks.get(i).getX(), enemyTanks.get(i).getY(), g, enemyTanks.get(i).getDirection(), 1);
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
            case 0: // 我们的坦克
                g.setColor(Color.cyan);// 青色
                break;
            case 1: // 敌方的坦克
                g.setColor(Color.yellow);
                break;
        }

        // 根据方向，绘制坦克
        switch (direction) {
            case 0: // 向上
                g.fill3DRect(x, y, 10, 60, false);// 左履带
                g.fill3DRect(x + 30, y, 10, 60, false);// 右履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);// 炮管
                break;
            case 1: // 向右
                g.fill3DRect(x - 10, y + 10, 60, 10, false);// 上履带
                g.fill3DRect(x - 10, y + 40, 60, 10, false);// 下履带
                g.fill3DRect(x, y + 20, 40, 20, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x + 50, y + 30);// 炮管
                break;
            case 2: // 向下
                g.fill3DRect(x, y, 10, 60, false);// 左履带
                g.fill3DRect(x + 30, y, 10, 60, false);// 右履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);// 炮管
                break;
            case 3: // 向左
                g.fill3DRect(x - 10, y + 10, 60, 10, false);// 上履带
                g.fill3DRect(x - 10, y + 40, 60, 10, false);// 下履带
                g.fill3DRect(x, y + 20, 40, 20, false);// 车身
                g.fillOval(x + 10, y + 20, 20, 20);// 顶部圆盖
                g.drawLine(x + 20, y + 30, x - 10, y + 30);// 炮管
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(0);
                hero.moveUp();
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(1);
                hero.moveRight();
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(2);
                hero.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(3);
                hero.moveLeft();
                break;
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
