import item.Hero;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    // 定义我的坦克
    Hero hero = null;

    public MyPanel() {
        hero = new Hero(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);

    }
    // 绘制坦克
    /**
     *
     * @param x 坦克左上角的x坐标
     * @param y 坦克左上角的x坐标
     * @param g 画笔
     * @param direction 朝向
     * @param type 类型
     */
    public void drawTank(int x,int y,Graphics g,int direction,int type){
        // 判断类型，设置不同颜色
        switch(type){
            case 0: // 我们的坦克
                g.setColor(Color.cyan);// 青色
            case 1: // 敌方的坦克
                g.setColor(Color.yellow);
        }
    }
}
