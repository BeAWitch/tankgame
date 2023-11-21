import item.Hero;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class MyPanel extends JPanel {
    // 定义我的坦克
    Hero hero = null;

    public MyPanel() {
        hero = new Hero(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        // 画坦克
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
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
                g.fill3DRect(x+30, y, 10, 60, false);// 右履带
                g.fill3DRect(x+10, y+10, 20, 40, false);// 车身
                g.fillOval(x+10, y+20, 20, 20);// 顶部圆盖
                g.drawLine(x+20,y+30,x+20,y);// 炮管
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }
}
