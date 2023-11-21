package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }

    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);// mp作为 KeyListener 的实例被传入
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel implements KeyListener {
    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    // 字符输出时调用
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 某个键按下时调用
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> y++;
            case KeyEvent.VK_UP -> y--;
            case KeyEvent.VK_RIGHT -> x++;
            case KeyEvent.VK_LEFT -> x--;
        }

        // 重绘
        this.repaint();
    }

    // 某个键释放时调用
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
